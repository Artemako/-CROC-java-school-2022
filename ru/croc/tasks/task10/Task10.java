package ru.croc.tasks.task10;

import java.util.Date;

public class Task10 {
    public static void main(String[] args) {
        Lot lot = new Lot(new Date(5_000_000_000L));

        lot.acceptTheBet(new Bet(100, "Artem", new Date(1_000_000_000L)));
        lot.acceptTheBet(new Bet(50, "Arta", new Date(2_000_000_000L)));
        lot.acceptTheBet(new Bet(100, "ArT", new Date(2_000_000_000L)));
        lot.acceptTheBet(new Bet(120, "AR", new Date(3_000_000_000L)));

        System.out.println(lot.getWinner(new Date(4_000_000_000L)));
        System.out.println(lot.getWinner(new Date(6_000_000_000L)));
    }
}
