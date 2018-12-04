package sample.model;

import sample.dataConnection.SQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Expense {
    private int rent;
    private int utilities;
    private int groceries;
    private int eatingOut;
    private int goingOut;
    private int gas;
    private int coffeeTea;

    public Expense(int rent, int utilities, int groceries, int eatingOut, int goingOut, int gas, int coffeeTea) {
        this.rent = rent;
        this.utilities = utilities;
        this.groceries = groceries;
        this.eatingOut = eatingOut;
        this.goingOut = goingOut;
        this.gas = gas;
        this.coffeeTea = coffeeTea;
    }

    public Expense() {

    }

    public void updateExpense(String category, int amount) {
        SQLConnection sql = new SQLConnection();
        try {
            PreparedStatement statement = sql.prepareStatement("update expense " +
                    "set " + category +  " = " + category + " + " + amount +
                    "where `user-id` = 4;");
            sql.setStatement(statement);
            sql.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException scve) {
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sql.close();
        }
    }

    public void getExpense() {
        SQLConnection sql = new SQLConnection();
        Expense e = new Expense();
        try {
            PreparedStatement statement = sql.prepareStatement(
                    "SELECT * FROM expense WHERE `user-id` = 4"
            );
            sql.setStatement(statement);
            sql.executeQuery();
            ResultSet results = sql.getResults();
            while (results.next()) {
                e.setRent(results.getInt(2));
                e.setUtilities(results.getInt(3));
                e.setGroceries(results.getInt(4));
                e.setEatingOut(results.getInt(5));
                e.setGoingOut(results.getInt(6));
                e.setGas(results.getInt(7));
                e.setCoffeeTea(results.getInt(8));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            u = null;
        }
        finally {
            sql.close();
        }
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public void setUtilities(int utilities) {
        this.utilities = utilities;
    }

    public void setGroceries(int groceries) {
        this.groceries = groceries;
    }

    public void setEatingOut(int eatingOut) {
        this.eatingOut = eatingOut;
    }

    public void setGoingOut(int goingOut) {
        this.goingOut = goingOut;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }

    public void setCoffeeTea(int coffeeTea) {
        this.coffeeTea = coffeeTea;
    }
}
