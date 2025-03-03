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
        Data thisData = data.get(message.getChatId());
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (command) {
                    case "/start_test":
                        System.out.println("start_test");
                        startTest(message);
                        break;
                    case "/finish_test":
                        System.out.println("finish_test");
                        if (thisData.testStatus.equals("off")) {
                            try {
                                execute(
                                        SendMessage.builder()
                                                .text("Вы еще не начали тестирование.")
                                                .chatId(message.getChatId().toString())
                                                .build());
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            finishTest(message);
                        }
                        break;
                    case "/help":
                        try {
                            execute(
                                    SendMessage.builder()
                                            .text("""
                                                    ВСЕ КОМАНДЫ.
                                                    <code>/start_test</code> - Начать тестирование
                                                    <code>/finish_test</code> - Завершить тестирование
                                                    <code>/help</code> - Помощь по командам
                                                    <code>/about</code> - О боте
                                                    <code>/statistics</code> - Ваша статистика
                                                    """
                                            )
                                            .chatId(message.getChatId().toString())
                                            .parseMode("HTML")
                                            .build());
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "/about":
                        try {
                            execute(
                                    SendMessage.builder()
                                            .text("""
                                                    ОБ БОТЕ.
                                                    Бот является ИП Акопяна Артёма на тему 'Тестирование на знание транскрипции слованглийского языка через чат-бота Telegram.'
                                                    Фантазии у автора нет, поэтому это всё."""
                                            )
                                            .chatId(message.getChatId().toString())
                                            .parseMode("HTML")
                                            .build());
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "/statistics":
                        watchStatistics(message);
                        break;
                }
            }
        } else if (message.hasText()) {
            if (thisData.testStatus.equals("active")) {
                checkAnswer(message);
                createTestQuestion(message);
            } else {
                try {
                    execute(
                            SendMessage.builder()
                                    .text("Я ничего не понял из того, что вы сейчас сказали.")
                                    .chatId(message.getChatId().toString())
                                    .build());
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    private void startTest(Message message) {
        Data thisData;
        if (data.get(message.getChatId()) == null) {
            thisData = new Data();
        } else {
            thisData = data.get(message.getChatId());
        }
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
        thisData.numberOfRightAnswers = 0;
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
        watchResult(message);
        addResults(message);
        //watchStatistics(message);
    }

    public double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }

    private void watchStatistics(Message message) {
        try {
            Data thisData = data.get(message.getChatId());
            execute(
                    SendMessage.builder()
                            .text("СТАТИСТИКА.\n" +
                                    "Общее количество вопросов: " + thisData.globalNumberOfQuestion + "\n" +
                                    "Общее количество правильных ответов: " + thisData.globalNumberOfRightAnswers + "\n" +
                                    "Процент правильных ответов: " + calculatePercentage((double)thisData.globalNumberOfRightAnswers, (double)thisData.globalNumberOfQuestion) + "%")
                            .chatId(message.getChatId().toString())
                            .parseMode("HTML")
                            .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void addResults(Message message) {
        Data thisData = data.get(message.getChatId());
        thisData.globalNumberOfQuestion = thisData.globalNumberOfQuestion + thisData.numberOfQuestion;
        thisData.globalNumberOfRightAnswers = thisData.globalNumberOfRightAnswers + thisData.numberOfRightAnswers;
        System.out.println(thisData.globalNumberOfQuestion + " " + thisData.globalNumberOfRightAnswers);
        data.put(message.getChatId(), thisData);
    }

    /*
    private void addToResultsDB(Message message) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:default", "sa", "")) {
            Data thisData = data.get(message.getChatId());
            PreparedStatement statement = connection.prepareStatement("INSERT INTO STATISTICS (ID, QUESTIONS, RIGHT_ANSWERS) VALUES(?,?,?)");
            statement.setString(1, message.getChatId().toString());
            statement.setInt(2, thisData.numberOfQuestion);
            statement.setInt(3, thisData.numberOfRightAnswers);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void watchResultsDB(Message message) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:default", "sa", "")) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT ID, QUESTIONS, RIGHT_ANSWERS FROM STATISTICS");
            while (result.next()) {
                int qq = result.getInt("QUESTIONS");
                int ra = result.getInt("RIGHT_ANSWERS");
                System.out.println(qq + " " + ra);
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


}

    private void createDB() {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:default", "sa", "")) {
            Statement statement = connection.createStatement();
            statement.execute("""
                    CREATE TABLE STATISTICS (
                    id VARCHAR(255) NOT NULL,
                    questions INTEGER NOT NULL DEFAULT 0,
                    right_answers INTEGER NOT NULL DEFAULT 0,
                    PRIMARY KEY(id)
                    )""");
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     */

    private void watchResult(Message message) {
        Data thisData = data.get(message.getChatId());
        try {
            execute(
                    SendMessage.builder()
                            .text("Результат.\n" +
                                    "✅ " + thisData.numberOfRightAnswers + " из " + thisData.numberOfQuestion)
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
                thisData.numberOfRightAnswers += 1;
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