package src.ru.croc.tasks.task10;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Task10 {
    public static Lot lot = new Lot(LocalDateTime.now().plus(40, ChronoUnit.MILLIS));

    public static void main(String[] args) {
        try {
            Thread[] allThreads = new Thread[3];
            for (int i = 0; i < 3; i++) {
                allThreads[i] = new Thread(new CreateThread());
                allThreads[i].start();
            }

            for (Thread thread : allThreads) {
                thread.join();
            }

            System.out.println(lot.getWinner());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
