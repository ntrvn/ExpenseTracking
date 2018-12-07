package sample.model;

import sample.Main;
import sample.dataConnection.SQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

// this expense model is to dealing with expense, add new expense to database
// get expense from database, and update expense
// I'm still working on this

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

    // When new user created, create new data row in expense table for that user
    public int insertToDatabase(int id) {
        int errorCode = 0;
        SQLConnection sql = new SQLConnection();
        try {
            PreparedStatement statement = sql.prepareStatement("insert into expense(rent,utilities,groceries,`eating-out`,`going-out`,gas,`coffee-tea`,`user-id`) value (0,0,0,0,0,0,0,?);");
            // the id is come from user table
            statement.setInt(1, id);
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

    // update new expense when user insert new expense
    public void updateExpense(String category, int amount) {
        SQLConnection sql = new SQLConnection();
        try {
            PreparedStatement statement = sql.prepareStatement("update expense " +
                    "set " + category +  " = " + category + " + " + amount +
                    " where `user-id`=?;");
            statement.setInt(1, Main.userID);
            sql.setStatement(statement);
            sql.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException scve) {
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sql.close();
        }
    }

    // get all expense of a user
    public Expense getExpense() {
        SQLConnection sql = new SQLConnection();
        Expense e = new Expense();
        try {
            PreparedStatement statement = sql.prepareStatement(
                    "SELECT * FROM expense WHERE `user-id`=?"
            );
            statement.setInt(1,Main.userID);
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
        catch (SQLException eee) {
            eee.printStackTrace();
        }
        finally {
            sql.close();
        }
        return e;
    }

    // setter and getter
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


    public int getRent() {
        return rent;
    }

    public int getUtilities() {
        return utilities;
    }

    public int getGroceries() {
        return groceries;
    }

    public int getEatingOut() {
        return eatingOut;
    }

    public int getGoingOut() {
        return goingOut;
    }

    public int getGas() {
        return gas;
    }

    public int getCoffeeTea() {
        return coffeeTea;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "rent=" + rent +
                ", utilities=" + utilities +
                ", groceries=" + groceries +
                ", eatingOut=" + eatingOut +
                ", goingOut=" + goingOut +
                ", gas=" + gas +
                ", coffeeTea=" + coffeeTea +
                '}';
    }
}
