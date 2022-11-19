package ru.croc.tasks.task10;

import java.util.Date;

public class Lot{
    private volatile int currentCost;
    private volatile String username;
    private final Date timeEnd;

    public Lot(Date timeEnd) {
        this.timeEnd = timeEnd;
        this.currentCost = 0;
        this.username = "admin";
    }

    public Lot(int currentCost, String username, Date timeEnd) {
        this.timeEnd = timeEnd;
        this.currentCost = currentCost;
        this.username = username;
    }


    public void acceptTheBet(Bet bet) {
        if (bet.cost > this.currentCost && bet.time.before(this.timeEnd)) {
            synchronized (this) {
                if (bet.cost > this.currentCost && bet.time.before(this.timeEnd)) {
                    this.currentCost = bet.cost;
                    this.username = bet.username;
                    System.out.println("The bet (" + bet + ") accepted");
                }
            }
        } else if (bet.cost > this.currentCost) {
            System.out.println("The cost of this bet (" + bet + ") is less than the current one.");
        } else {
            System.out.println("The bid (" + bet + ") was not accepted because the auction ended.");
        }
    }

    public String getWinner(Date date) {
        if (date.after(timeEnd))
            return "The winner of auction is " + this.username;
        else
            return "The auction is not over yet";
    }
}