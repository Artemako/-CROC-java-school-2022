package src.ru.croc.finalproject;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class Bot extends TelegramLongPollingBot {

    public HashMap<Long, Data> data = new HashMap<>();
    public int totalNumberOfQuestion = 10;

    @Override
    public String getBotUsername() {
        return "TranscriptionTestBot";
    }

    @Override
    public String getBotToken() {
        return "5703873194:AAHCqB_tf4elELlF9q5yR47b6JAKGNwr0_0";
    }

    public static void main(String[] args) throws TelegramApiException, IOException {
        Bot bot = new Bot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            handleMessage(update.getMessage());
        }
    }


    private void handleMessage(Message message) {
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                if ("/start_test".equals(command)) {
                    System.out.println("start_test");
                    startTest(message);
                } else if ("/finish_test".equals(command)) {
                    System.out.println("finish_test");
                    finishTest(message);
                }
            }
        } else if (message.hasText()) {
            Data thisData = data.get(message.getChatId());
            if (thisData.testStatus.equals("active")) {
                checkAnswer(message);
                createTestQuestion(message);
            }
        }
    }

    private void startTest(Message message) {
        Data thisData = new Data();
        thisData.testStatus = "active";
        System.out.println(message.getChatId());
        data.put(message.getChatId(), thisData);

        try {
            execute(
                    SendMessage.builder()
                            .text("Запуск тестирования.")
                            .chatId(message.getChatId().toString())
                            .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        generateTestWords(message);
        thisData = data.get(message.getChatId());
        thisData.numberOfQuestion = 0;
        data.put(message.getChatId(), thisData);
        createTestQuestion(message);
    }

    private void finishTest(Message message) {
        Data thisData = data.get(message.getChatId());
        thisData.testStatus = "off";
        data.put(message.getChatId(), thisData);
        try {
            execute(
                    SendMessage.builder()
                            .text("Завершение тестирования.")
                            .chatId(message.getChatId().toString())
                            .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateTestWords(Message message) {
        try {
            for (int i = 0; i < 10; i++) {
                Data thisData = data.get(message.getChatId());
                Word word = new Word();
                word.chooseRandomWord();
                while (true) {
                    boolean flag = word.createWordForTest();
                    if (flag && !thisData.testWords.contains(word)) {
                        break;
                    }
                    word.chooseRandomWord();
                }
                thisData.testWords.add(word);
                data.put(message.getChatId(), thisData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkAnswer(Message message) {
        try {
            Data thisData = data.get(message.getChatId());

            if (message.getText().equals(thisData.currentWord.getContent())) {
                execute(
                        SendMessage.builder()
                                .text("Это правильный ответ.")
                                .chatId(message.getChatId().toString())
                                .build());

            } else {
                execute(
                        SendMessage.builder()
                                .text("Это неправильный ответ. Правильный: " + thisData.currentWord.getContent())
                                .chatId(message.getChatId().toString())
                                .build());
            }
            thisData.numberOfQuestion += 1;
            data.put(message.getChatId(), thisData);


        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    private void createTestQuestion(Message message) {
        try {
            Data thisData = data.get(message.getChatId());
            if (thisData.numberOfQuestion >= this.totalNumberOfQuestion) {
                finishTest(message);
            } else {
                thisData.currentWord = thisData.testWords.get(thisData.numberOfQuestion);
                System.out.println(thisData.currentWord.getContent() + " " + thisData.currentWord.getTranscription());
                execute(
                        SendMessage.builder()
                                .text((thisData.numberOfQuestion + 1) +
                                        ") Определите и напишите слово по транскрипции:\n" +
                                        "<code>" + thisData.currentWord.getTranscription() + "</code>")
                                .chatId(message.getChatId().toString())
                                .parseMode("HTML")
                                .build());
            }
            data.put(message.getChatId(), thisData);

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}