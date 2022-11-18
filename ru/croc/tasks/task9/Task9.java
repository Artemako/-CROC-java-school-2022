package ru.croc.tasks.task9;

import java.util.Scanner;

public class Task9 {
    private static String myHash = "40682260CC011947FC2D0B1A927138C5";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            int numberOfThreads = in.nextInt();
            int lenPassword = 7;

            Thread[] allThreads = new Thread[numberOfThreads];

            System.out.println("Поиск  начался. " + numberOfThreads);
            for (int i = 0; i < numberOfThreads; i++) {
                allThreads[i] = new Thread(new HackerThread(i, numberOfThreads, lenPassword, myHash));
                allThreads[i].start();
            }

            for (int i = 0; i < numberOfThreads; ++i) {
                allThreads[i].join();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
