package src.ru.croc.tasks.task18;

import src.ru.croc.tasks.task17.Order;
import src.ru.croc.tasks.task17.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static src.ru.croc.tasks.task17.Task17.*;

public class Task18 {
    public static void main(String[] args) {
        // повторение кода из прошлой задачи
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();

        readFileCSV(args[0],orders,products);

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:~/test", "sa", "")) {
            createTablesInDatabase(connection);
            addProductsToDatabase(connection, products);
            addOrdersToDatabase(connection,orders);
            check(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void check(Connection connection) throws SQLException {
        Dao dao = new Dao(connection);
        dao.findProduct("Т1");
        dao.createProduct(new src.ru.croc.tasks.task18.Product("Т6", "Кулер", 250));
        dao.updateProduct(new src.ru.croc.tasks.task18.Product("Т6", "Кулер", 270));
        dao.deleteProduct("Т4");
    }
}
