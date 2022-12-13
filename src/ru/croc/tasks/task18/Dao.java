package src.ru.croc.tasks.task18;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    Connection connection;

    public Dao(Connection connection) {
        this.connection = connection;
    }

    public Product findProduct(String article) throws SQLException {
        Product returnProduct = new Product();
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM Products p WHERE p.id = ?")){
            statement.setString(1, article);
            try (ResultSet result = statement.executeQuery()){
                while (result.next()) {
                    returnProduct.setArticle(result.getString("id"));
                    returnProduct.setNameProduct(result.getString("name"));
                    returnProduct.setCostProduct(result.getInt("cost"));
                }
            }
        }
        if (returnProduct.getArticle() == null)
            return null;
        return returnProduct;
    }

    public Product createProduct(Product product) throws SQLException {
        if (findProduct(product.getArticle()) != null)
            System.err.println("Этот продукт уже существует в базе данных");
        else {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Products VALUES(?,?,?)")) {
                statement.setString(1, product.getArticle());
                statement.setString(2, product.getNameProduct());
                statement.setInt(3, product.getCostProduct());
                statement.execute();
            }
        }
        return product;
    }

    public Product updateProduct(Product product) throws SQLException {
        if (findProduct(product.getArticle()) == null)
            System.err.println("Такого продукта нет в базе данных");
        else {
            try (PreparedStatement statement = connection.prepareStatement
                    ("UPDATE Products SET name = ?, cost = ? WHERE id = ?")) {
                statement.setString(1, product.getNameProduct());
                statement.setInt(2, product.getCostProduct());
                statement.setString(3, product.getArticle());
                statement.execute();
            }
        }
        return product;
    }

    public void deleteProduct(String article) throws SQLException {
        if (findProduct(article) == null)
            System.err.println("Такого продукта нет в базе данных");
        else {
            try (PreparedStatement statement = connection.prepareStatement
                    ("DELETE FROM Orders o WHERE o.article = ?; " +
                            "DELETE FROM Products p WHERE p.id = ?")) {
                statement.setString(1, article);
                statement.setString(2, article);
                statement.execute();
            }
        }
    }

    public Order createOrder(String userLogin, List<Product> products) throws SQLException {
        int id = getIdLastOrder() + 1;
        ArrayList<String> productsArticles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement
                ("INSERT INTO Orders VALUES(?,?,?)")) {
            for (Product product : products) {
                if (findProduct(product.getArticle()) == null)
                    System.err.println("Такого продукта нет в базе данных");
                productsArticles.add(product.getArticle());
                statement.setInt(1, id);
                statement.setString(2, userLogin);
                statement.setString(3, product.getArticle());
                statement.execute();
            }
        }
        return new Order(id, userLogin, productsArticles);
    }
    public int getIdLastOrder() throws SQLException {
        try (Statement statement = connection.createStatement()){
            try (ResultSet result = statement.executeQuery("SELECT id FROM Orders o ORDER BY o.id DESC")) {
                result.next();
                return result.getInt("ID");
            }
        }
    }
}
