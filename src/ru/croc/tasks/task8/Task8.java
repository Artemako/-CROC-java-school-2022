package src.ru.croc.tasks.task8;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a double: ");
        try {
            BigDecimal cost = BigDecimal.valueOf(in.nextDouble());
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.JAPANESE);
            System.out.println("Result: " + format.format(cost));
        } catch (InputMismatchException e) {
            System.out.print("Invalid number entry.");
        }
    }
}
