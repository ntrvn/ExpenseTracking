package sample.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.User;

public class loginController implements Initializable {
    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;


    // when user click on log in
    // verify user input against database and then send user to dashboard page
    @FXML private void userLogin(ActionEvent e) {
        Stage stage;
        Parent root;
        // get data from database
        User user = User.getUser(username.getText());

        // verify user input against data from database
        if (user.getName().equals("")) {
            System.out.println("User is not in database");
        } else {
            if (user.getPassword().equals(password.getText())) {
                try {
                    FXMLLoader loader = new FXMLLoader(new File("src/sample/view/dashboard.fxml").toURI().toURL());
                    root = loader.load();
                    stage = (Stage) login.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ie) {
                    System.out.println(ie.getMessage());
                }
            } else {
                System.out.println("Incorrect Password");
            }
        }
    }


    // When user click on register, will send to register page
    @FXML private void register(ActionEvent e) {
        Stage stage;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/sample/view/register.fxml").toURI().toURL());
            root = loader.load();
            stage = (Stage) login.getScene().getWindow();
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
