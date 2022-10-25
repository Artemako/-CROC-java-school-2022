package ru.croc.lessons.lesson2;

public class Example1 {
    public static void main(String[] args) {
        // comment on one line

        /*
        System.out.println("Hello, Java World!");
         */

        /**
         * Документация
         * @param;
         * @return;
         */

        /*
        int x;
        x = 1;
        int y = x + 2;
        System.out.println(y);
         */

        final double PI = 3.14159;
        // PI = 3.14; // error

        /*
        class MyDate {
            // fields and methods
            int day = 1;
            int month = 1;
            int year;
        }

         */

        // иденфикатор (название переменной, класса или метода) может начинаться с latin, _, $

        /*
        PascalCase - название класса JAVA
        snake_case - название констант (верхний регистр) JAVA
        camelCase - название переменной JAVA
         */

        /*
        int x = 2021; // 10-ричная
        x = 2_021; // 10-ричная
        x = 03745; // 8-ричная
        x = 0x7E5; // 16-ричная
        x = 0b0111_1110_0101; // 2-ричная
         */

        /*
        double d = 3.14;
        float f = 3.14f;
        f = 2.15f;
         */

        // boolean truth = true;

        /*
        char = '\t';
         */

        /*
        String example = """
                Text
                    with
                        line breaks
                """
         */

        /*
        int i = 1;
        byte b = (byte) i;

        byte bb = 1;
        int ii = bb;
         */

        /*
        class MyDate {
            // fields and methods
            private int day = 1;
            private int month = 1;
            private int year = 2000;
            public MyDate(int day, int month, int year) {...}
        }
        MyDate today = new MyDate(14, 10, 2022);
         */

        /*
        int x = 1;
        int y = x++;
        System.out.println(y);

         */

        // System.out.println(-30%4);
        /* int x = 1 > 2 ? 5 : 7;
        System.out.println(x);
        */

        /*
        double d = 7d / 3;
        System.out.println(d);
        d *= 100;
        System.out.println(d);
        d = (int) d;
        d = (double) d;
        d *= 0.01;
        System.out.println(d);

         */

    }
}
