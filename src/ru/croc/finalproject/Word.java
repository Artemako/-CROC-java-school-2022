package src.ru.croc.finalproject;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Word {
    private String content;
    private String transcription;

    Word() {
        this.content = "";
    }

    public String getContent() {
        return content;
    }

    public String getTranscription() {
        return transcription;
    }

    public void chooseRandomWord() throws IOException {
        RandomAccessFile file = new RandomAccessFile("src/ru/croc/finalproject/words.txt", "r");
        long randomLocation = (long) (Math.random() * file.length());
        file.seek(randomLocation);
        file.readLine();
        String randomLine = file.readLine();
        file.close();
        this.content = randomLine;
    }

    public boolean createWordForTest(){
        String sURL = "https://dictionary.yandex.net/dicservice.json/lookup?ui=ru&text="
                + this.content + "&lang=en-ru&flags=23";

        JSONObject json = null;
        try {
            json = new JSONObject(IOUtils.toString(new URL(sURL), StandardCharsets.UTF_8));
            JSONArray items = json.getJSONArray("def");
            JSONObject item = (JSONObject) items.get(0);
            this.transcription = item.getString("ts");
            this.content = item.getString("text");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
