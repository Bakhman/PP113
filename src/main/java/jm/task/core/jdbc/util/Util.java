package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/db113";
    private static final String USERNAME = "bakhman";
    private static final String PASSWORD = "bakhmai";

    /*private static final String URL_KEY      = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";*/

    //private Connection conn;
    /*public Util() {
        try{
            String URL = "jdbc:mysql://localhost:3306/mydb";
            String USERNAME = "bakhman";
            String PASSWORD = "bakhmai";
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection OK!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR!!!");
        }
    }*/

    public static Connection openConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection OK!");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR!!!");
        }
        return conn;
    }

    /*public static Connection openConnect() {
        try{
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    /*public Connection getConn() {
        return conn;
    }*/
}
