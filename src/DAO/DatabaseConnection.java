package DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private Connection connection;
    private static DatabaseConnection instance = null;

    private DatabaseConnection() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/resources/config.properties"));

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Kết nối đến cơ sở dữ liệu thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc tệp cấu hình: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Lỗi khi kết nối đến cơ sở dữ liệu: " + e.getMessage());
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
     public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();
    }
}
