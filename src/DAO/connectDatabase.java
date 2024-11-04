package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDatabase {

    private Connection conn;
    private String url;
    private String dbName;
    private String driver;

    private String userName;
    private String password;

    public connectDatabase() throws SQLException {
        url = "jdbc:mysql://localhost/";
        dbName = "qlbs";
        driver = "com.mysql.cj.jdbc.Driver";
        userName = "root";
        password = "1234";

    }

    public connectDatabase(String url, String dbName, String driver, String userName, String password) {
        this.url = url;
        this.dbName = dbName;
        this.driver = driver;
        this.userName = userName;
        this.password = password;
    }

    public void connect() throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName+ "?useSSL=false" + "&allowPublicKeyRetrieval=true", userName, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found");
        }

    }

    public void disconnect() throws SQLException {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException E) {
            }
        }
    }

    public Connection getConn() {
        return conn;
    }
    
			}
