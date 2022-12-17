package src.ru.croc.finalproject;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    public Word currentWord;
    public ArrayList<Word> testWords = new ArrayList<>();
    public String testStatus = "off";
    public int numberOfQuestion = 0;

    public Data(Word currentWord, ArrayList<Word> testWords, String testStatus, int numberOfQuestion) {
        this.currentWord = currentWord;
        this.testWords = testWords;
        this.testStatus = testStatus;
        this.numberOfQuestion = numberOfQuestion;
    }

    public Data(){
        this.currentWord = new Word();
        this.testWords = new ArrayList<>();
        this.testStatus = "off";
        this.numberOfQuestion = 0;
    }
}
