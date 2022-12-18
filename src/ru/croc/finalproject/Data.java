package src.ru.croc.finalproject;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    public Word currentWord;
    public ArrayList<Word> testWords = new ArrayList<>();
    public String testStatus = "off";
    public int numberOfQuestion = 0;
    public int numberOfRightAnswers = 0;
    public int globalNumberOfQuestion = 0;
    public int globalNumberOfRightAnswers = 0;

    public Data(){
        this.currentWord = new Word();
        this.testWords = new ArrayList<>();
        this.testStatus = "off";
        this.numberOfQuestion = 0;
        this.numberOfRightAnswers = 0;
        this.globalNumberOfQuestion = 0;
        this.globalNumberOfRightAnswers = 0;
    }
}
