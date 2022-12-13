package src.ru.croc.tasks.task18;

public class Product {
    private String article;
    private String nameProduct;
    private int costProduct;

    public Product(String article, String nameProduct, int costProduct) {
        this.article = article;
        this.nameProduct = nameProduct;
        this.costProduct = costProduct;
    }

    public Product() {

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

    public void setArticle(String article) {
        this.article = article;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public void setCostProduct(int costProduct) {
        this.costProduct = costProduct;
    }
}
