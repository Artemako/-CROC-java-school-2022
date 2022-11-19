package ru.croc.tasks.task10;

import java.util.Date;

public class Bet {
    public int cost;
    public String username;
    public Date time;

    public Bet(int cost, String username, Date time) {
        this.cost = cost;
        this.username = username;
        this.time = time;
    }
    @Override
    public String toString(){
        return cost + ", " + username + ", " + time;
    }
}
