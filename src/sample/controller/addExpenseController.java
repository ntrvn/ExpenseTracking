package sample.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Expense;
import sample.model.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addExpenseController implements Initializable {

    @FXML
    private Button add;

    @FXML
    private ComboBox<String> box;

    @FXML
    private TextField amount;

    @FXML
    private Button back;

    // Handle when user try to add new expense
    @FXML private void addExpense(ActionEvent e) {
        String val = box.getSelectionModel().getSelectedItem();
        String para = "";
        // base on what category user picked, give appropriate parameter for the database
        if (!val.equals("null") && !amount.getText().equals("")) {
            if (val.equals("Rent")) {
                para = "rent";
            } else if (val.equals("Utilities")) {
                para = "utilities";
            } else if (val.equals("Grocery")) {
                para = "groceries";
            } else if (val.equals("Eating Out")) {
                para = "`eating-out`";
            } else if (val.equals("Going Out")) {
                para = "`going-out`";
            } else if (val.equals("Gas")) {
                para = "gas";
            } else {
                para = "`coffee-tea`";
            }
            // update expense to database
            Expense expense = new Expense();
            expense.updateExpense(para,Integer.parseInt(amount.getText()));
            // alert to let user know that new expense is updated
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Expense Added!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
    }

    // back to dashboard
    @FXML private void backToDashboard(ActionEvent e) {
        Stage stage;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/sample/view/dashboard.fxml").toURI().toURL());
            root = loader.load();
            stage = (Stage) this.back.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
