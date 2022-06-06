package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection conn = Util.openConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users (" +
                        "id       INT          NOT NULL AUTO_INCREMENT, " +
                        "name     VARCHAR(45)  NOT NULL, " +
                        "lastName VARCHAR(45)  NOT NULL, " +
                        "age      TINYINT      NOT NULL, PRIMARY KEY (id));";

        try (Statement stat = conn.createStatement()){
            stat.execute(query);
            stat.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                excep.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users;";

        try (Statement stat = conn.createStatement()){
            stat.execute(query);
            stat.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                excep.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users(name, lastName, age) values(?, ?, ?)";
        try (PreparedStatement pStat=conn.prepareStatement(query)){
            pStat.setString(1, name);
            pStat.setString(2, lastName);
            pStat.setByte(3, age);
            pStat.executeUpdate();
            pStat.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                excep.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id=?;";
        try (PreparedStatement pStat=conn.prepareStatement(query)){
            pStat.setLong(1, id);
            pStat.executeUpdate();
            pStat.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                excep.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users;";
        try(PreparedStatement pStat=conn.prepareStatement(query);
            ResultSet rSet = pStat.executeQuery()){
            while(rSet.next()){
                User user = new User();
                user.setId(rSet.getLong(1));
                user.setName(rSet.getString(2));
                user.setLastName(rSet.getString(3));
                user.setAge(rSet.getByte(4));
                users.add(user);
            }
            pStat.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                excep.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE users;";
        try(PreparedStatement pStat=conn.prepareStatement(query)){
            pStat.executeUpdate();
            pStat.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                excep.printStackTrace();
            }
        }
    }
}
