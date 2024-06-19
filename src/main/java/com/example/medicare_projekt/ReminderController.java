package com.example.medicare_projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ReminderController {

    @FXML
    private Label reminderLabel;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setReminderText(String text) {
        reminderLabel.setText(text);
    }

    @FXML
    private void handleOkButtonAction() {
        stage.close();
    }
}
