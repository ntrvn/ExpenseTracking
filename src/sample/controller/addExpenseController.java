package sample.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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

    // I'm still working on this
    @FXML private void addExpense(ActionEvent e) {
        String val = box.getSelectionModel().getSelectedItem();
        System.out.println(val);
        System.out.println(amount.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
