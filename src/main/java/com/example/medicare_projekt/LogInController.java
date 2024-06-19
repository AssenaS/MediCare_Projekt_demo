package com.example.medicare_projekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {

    @FXML
    private TextField benutzerNameTextField;

    @FXML
    private TextField passwortTextField;

    @FXML
    private Button signInButton;

    private Stage stage;

    private Scene scene;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainScene(Scene hauptseiteScene) {
        this.scene = scene;
    }

    public void handleSigninButton(ActionEvent event) throws IOException {
        String passwort = passwortTextField.getText();
        String benutzername = benutzerNameTextField.getText();

        if (benutzername.equals("demo") && passwort.equals("1234")) {
            Stage loginStage =
                    (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("HauptFenster.fxml"));
            Parent hauptFenster = loader.load();
            Scene scene = new Scene(hauptFenster);
            Stage mainStage = new Stage();
            mainStage.setScene(scene);
            mainStage.setTitle("MediCare");
            mainStage.show();

            MainWindowController controller = loader.getController();
            controller.setStage(mainStage);
            controller.setMainScene(scene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Fehler");
            alert.setHeaderText(null);
            alert.setContentText("Falscher Benutzername oder Passwort!");
            alert.showAndWait();
        }
    }
    }
