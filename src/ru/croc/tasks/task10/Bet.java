package src.ru.croc.tasks.task10;

import java.time.LocalDateTime;
import java.util.Date;

public class Bet {
    public int cost;
    public String username;

    public Bet(int cost, String username) {
        this.cost = cost;
        this.username = username;
    }
    @Override
    public String toString(){
        return cost + ", " + username;
    }
}
