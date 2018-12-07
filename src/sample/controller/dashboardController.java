package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    @FXML
    private Button add;

    // when user click on addExpense send user to addExpense page
    @FXML private void addExpense(ActionEvent e) {
        Stage stage;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/sample/view/addExpense.fxml").toURI().toURL());
            root = loader.load();
            stage = (Stage) add.getScene().getWindow();
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
