package ru.croc.tasks.task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {

    public static void main(String[] args) {
        String text = setText();
        String noComments = removeJavaComments(text);
        System.out.println(noComments);
    }

    private static String removeJavaComments(String text) {
        // сначала пытался решить задачу по типу "Правильная скобочная последовательность" (только вместо скобок), а потом вспомнил что существуют "ужасные" регулярные выражения
        Pattern pattern = Pattern.compile("(/\\*([\\S\\s]+?)\\*/)|(//.*)");
        Matcher matcher = pattern.matcher(text);

        StringBuilder textWithoutJavaComments = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(textWithoutJavaComments, "");
        }
        matcher.appendTail(textWithoutJavaComments);

        return String.valueOf(textWithoutJavaComments);
    }

    public static String setText() {
        String text = """
                /*
                * My first ever program in Java!
                */
                class Hello { // class body starts here 

                    /* main method */
                    public static void main(String[] args/* we put command line arguments here*/) {
                        // this line prints my first greeting to the screen
                        System.out.println("Hi!"); // :)
                    }
                } // the end
                // to be continued...
                """;
        return text;
    }
}
