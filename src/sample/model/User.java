package sample.model;

import sample.dataConnection.SQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class User {

    private String name;
    private String password;
    private String email;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User() {
        this.name = "";
        this.password = "";
        this.email = "";
    }

    public int insertToDatabase() {
        int errorCode = 0;
        SQLConnection sql = new SQLConnection();
        try {
            PreparedStatement statement = sql.prepareStatement("INSERT INTO users "
                    + "(email, password, name) " + "VALUES (?, ?, ?)");

            statement.setString(1, this.email);
            statement.setString(2, this.password);
            statement.setString(3, this.name);

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

    public User getUser(String id) {
        User u = new User();
        SQLConnection sql = new SQLConnection();
        try {
            PreparedStatement statement = sql.prepareStatement(
                    "SELECT * FROM users WHERE id=?"
            );
            statement.setInt(1, Integer.parseInt(id));
            sql.setStatement(statement);
            sql.executeQuery();
            ResultSet results = sql.getResults();
            while (results.next()) {
                u.setEmail(results.getString(0));
                u.setPassword(results.getString(1));
                u.setName(results.getString(2));
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

    public void setEmail(String email) {
        this.email = email;
    }

}
