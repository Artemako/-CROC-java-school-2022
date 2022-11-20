package src.ru.croc.tasks.task10;

import java.time.LocalDateTime;
import java.util.Date;

public class Bet {
    public int cost;
    public String username;
    public LocalDateTime time;

    public Bet(int cost, String username, LocalDateTime time) {
        this.cost = cost;
        this.username = username;
        this.time = time;
    }
    @Override
    public String toString(){
        return cost + ", " + username + ", " + time;
    }
}
