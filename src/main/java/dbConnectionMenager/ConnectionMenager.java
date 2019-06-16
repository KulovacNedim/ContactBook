package main.java.dbConnectionMenager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Singleton
public class ConnectionMenager {

    private static ConnectionMenager instance = null;

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/contact?useSSL=false&serverTimezone=UTC";

    private static Connection connection = null;

    private ConnectionMenager() {

    }

    public static ConnectionMenager getInstance() {
        if (instance == null) {
            instance = new ConnectionMenager();
        }
        return instance;
    }

    private static boolean openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return false;
        }
    }

    public static Connection getConnectionToDatabase() {
        if (connection == null) {
            if (openConnection()) {
                System.out.println("Connection opened.");
                return connection;
            } else {
                return null;
            }
        }
        return connection;
    }

    public void close() {
        System.out.println("Connection closed.");
        try {
            connection.close();
            connection = null;
        } catch (Exception e) {
        }
    }
}