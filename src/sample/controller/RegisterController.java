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

public class RegisterController implements Initializable {

    @FXML
    private TextField usernameR;

    @FXML
    private PasswordField passwordR;

    @FXML
    private TextField income;

    @FXML
    private Button register;

    // when user click on register
    // add new user to database
    @FXML private void register(ActionEvent e) {
        // create new user
        User user = new User(usernameR.getText(), passwordR.getText(), Integer.parseInt(income.getText()));
        // add new user to database
        user.insertToDatabase();
        User temp = User.getUser(usernameR.getText());
        Expense newTable = new Expense();
        newTable.insertToDatabase(Main.userID);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your Account is created!", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
        // back to login to login
        Stage stage;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/sample/view/login.fxml").toURI().toURL());
            root = loader.load();
            stage = (Stage) register.getScene().getWindow();
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
