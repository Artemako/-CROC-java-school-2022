package src.ru.croc.tasks.task17;


import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Task17 {
    public static void main(String[] args) {
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();

        readFileCSV(args[0],orders,products);

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:~/test", "sa", "")) {
            createTablesInDatabase(connection);
            addProductsToDatabase(connection, products);
            addOrdersToDatabase(connection,orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static void readFileCSV(String path,ArrayList<Order> orders, ArrayList<Product> product) {
        Scanner scanner;
        try {
            scanner = new Scanner(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] fields;
        HashSet<String> uniqueArticles = new HashSet<>();
        while (scanner.hasNextLine()) {
            fields = scanner.nextLine().split(",");
            orders.add(new Order(Integer.parseInt(fields[0]), fields[1], fields[2]));
            if (uniqueArticles.add(fields[2]))
                product.add(new Product(fields[2], fields[3], Integer.parseInt(fields[4])));
        }
    }
    static void createTablesInDatabase(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()){
            statement.execute("CREATE TABLE Products" +
                    "(id VARCHAR(255) PRIMARY KEY, name VARCHAR(255), cost INT);");
            statement.execute("CREATE TABLE Orders" +
                    "(id INT, username VARCHAR(255), article VARCHAR(255), " +
                    "foreign key (Article) references Products(id));");
        }
    }
    static void addProductsToDatabase(Connection connection, ArrayList<Product> products) throws SQLException{
        String sql = "INSERT INTO Products VALUES(?,?,?)";
        for (Product product : products) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, product.getArticle());
                statement.setString(2, product.getNameProduct());
                statement.setInt(3, product.getCostProduct());
                statement.execute();
            }
        }
    }

    static void addOrdersToDatabase(Connection connection, ArrayList<Order> orders) throws SQLException {
        String sql = "INSERT INTO Orders VALUES(?,?,?)";
        for (Order order : orders) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, order.getId());
                statement.setString(2, order.getUsername());
                statement.setString(3, order.getArticle());
                statement.execute();
            }
        }
    }
}
