package src.ru.croc.tasks.task2;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        // класс Scanner, который упрощает ввод данных (пришлось почитать)
        Scanner in = new Scanner(System.in);

        // ввод данных (будем предполагать, что можно только вводить целые числа, так как про это ничего не сказано)
        int begin = in.nextInt();
        int delta = in.nextInt();
        int amount = in.nextInt();

        int summa = 0;

        for (int step = 0; step < amount; step++){
            summa += begin;
            begin += delta;
        }

        System.out.println(summa);
    }
}