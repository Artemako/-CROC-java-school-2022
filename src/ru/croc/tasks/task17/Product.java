package src.ru.croc.tasks.task17;

public class Product {
    private String article;
    private String nameProduct;
    private int costProduct;

    public Product(String article, String nameProduct, int costProduct) {
        this.article = article;
        this.nameProduct = nameProduct;
        this.costProduct = costProduct;
    }

    public String getArticle() {
        return article;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public int getCostProduct() {
        return costProduct;
    }


}
