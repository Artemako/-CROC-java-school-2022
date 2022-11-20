package src.ru.croc.tasks.task3;

import java.util.Locale;
import java.util.Scanner;

public class Task3 {
    static class Point {
        // обратим внимание, что можно ввести дробное число
        double x;
        double y;
    }

    public static void main(String[] args) {
        // .useLocale(Locale.US) - нужен для ввода дробных чисел через ТОЧКУ, а не запятую
        Scanner in = new Scanner(System.in).useLocale(Locale.ENGLISH);

        Point a = new Point();
        a.x = in.nextDouble();
        a.y = in.nextDouble();

        Point b = new Point();
        b.x = in.nextDouble();
        b.y = in.nextDouble();

        Point c = new Point();
        c.x = in.nextDouble();
        c.y = in.nextDouble();

        double square = Math.abs((a.x - c.x) * (b.y - a.y) - (a.x - b.x) * (c.y - a.y)) / 2;

        System.out.println("Площадь треугольника: " + square);

    }
}
