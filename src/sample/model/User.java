package sample.model;

import sample.dataConnection.SQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class User {

    private String name;
    private String password;
    private int income;

    public User(String name, String password, int income) {
        this.name = name;
        this.password = password;
        this.income = income;
    }

    public User() {
        this.name = "";
        this.password = "";
        this.income = 0;
    }

    public int insertToDatabase() {
        int errorCode = 0;
        SQLConnection sql = new SQLConnection();
        try {
            PreparedStatement statement = sql.prepareStatement("INSERT INTO user "
                    + "(name,password,income) " + "VALUES (?, ?, ?)");

            statement.setString(1, this.name);
            statement.setString(2, this.password);
            statement.setInt(3,this.income);

            sql.setStatement(statement);
            sql.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException scve) {
            errorCode = 2;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            errorCode = 3;
        } finally {
            sql.close();
        }

        return errorCode;
    }

    public static User getUser(String name) {
        User u = new User();
        SQLConnection sql = new SQLConnection();
        try {
            PreparedStatement statement = sql.prepareStatement(
                    "SELECT * FROM user WHERE name=?"
            );
            statement.setString(1, name);
            sql.setStatement(statement);
            sql.executeQuery();
            ResultSet results = sql.getResults();
            while (results.next()) {
                u.setName(results.getString(2));
                u.setPassword(results.getString(3));
                u.setIncome(results.getInt(4));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            u = null;
        }
        finally {
            sql.close();
        }

        return u;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public void print() {
        System.out.println("name is: " + this.name);
        System.out.println("password is: " + this.password);
        System.out.println("income is: " + this.income);
    }

}
