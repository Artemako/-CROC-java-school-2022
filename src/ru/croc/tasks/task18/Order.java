package src.ru.croc.tasks.task18;

import java.util.List;

public class Order {
    private int id;
    private String username;
    private List<String> article;

    public Order(int id, String username, List<String> article) {
        this.id = id;
        this.username = username;
        this.article = article;
    }
}
