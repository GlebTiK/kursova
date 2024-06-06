package kpi.hlibsukhoruchkin.kursova.sql;

import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static Connection instance;
    private DSLContext dslContext;
    private java.sql.Connection connection;

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/kursova?allowPublicKeyRetrieval=true&useSSL=false&autoReconnect=true";

    // Private constructor to prevent instantiation
    private Connection() throws SQLException, ClassNotFoundException {
        init();
    }

    // Public method to provide access to the instance
    public static synchronized Connection getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    // Initialize the connection and DSLContext
    private void init() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded!");
        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        this.dslContext = DSL.using(connection, SQLDialect.MYSQL);
    }

    // Method to get the DSLContext
    public DSLContext getDSLContext() {
        return dslContext;
    }

    // Method to close the connection
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
