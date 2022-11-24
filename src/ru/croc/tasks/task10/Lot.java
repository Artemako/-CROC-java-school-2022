package src.ru.croc.tasks.task10;

import java.time.LocalDateTime;
import java.util.Date;

public class Lot{
    private volatile int currentCost;
    private volatile String username;
    public final LocalDateTime timeEnd;

    public Lot(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
        this.currentCost = 0;
        this.username = "admin";
    }

    public Lot(int currentCost, String username, LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
        this.currentCost = currentCost;
        this.username = username;
    }


    public void acceptTheBet(Bet bet) {
            synchronized (this) {
                if (bet.cost > this.currentCost && LocalDateTime.now().isBefore(this.timeEnd)) {
                    this.currentCost = bet.cost;
                    this.username = bet.username;
                    System.out.println("The bet (" + bet + ") accepted");
                } else if (bet.cost < this.currentCost) {
                    System.out.println("The cost of this bet (" + bet + ") is less than the current one.");
                } else {
                    System.out.println("The bid (" + bet + ") was not accepted because the auction ended.");
                }
            }
    }

    public String getWinner() {
        if (LocalDateTime.now().isAfter(timeEnd))
            return "The winner of auction is " + this.username + "with cost:" + this.currentCost;
        else
            return "The auction is not over yet";
    }
}