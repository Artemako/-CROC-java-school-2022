package src.ru.croc.tasks.task17;

public class Order {
    private int id;
    private String username;
    private String article;

    public Order(int id, String username, String article) {
        this.id = id;
        this.username = username;
        this.article = article;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getArticle() {
        return article;
    }
}
