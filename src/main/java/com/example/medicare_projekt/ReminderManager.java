package com.example.medicare_projekt;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderManager {
    private List<Reminder> reminders = new ArrayList<>();
    private Timer timer = new Timer(true);

    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
        scheduleReminder(reminder);
    }

    private void scheduleReminder(Reminder reminder) {
        long delay = reminder.getReminderTime().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli() - System.currentTimeMillis();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> showReminderWindow(reminder.getMessage()));
            }
        }, delay);
    }

    private void showReminderWindow(String message) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reminder.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            ReminderController controller = loader.getController();
            controller.setStage(stage);
            controller.setReminderText(message);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelReminders() {
        timer.cancel();
    }
}
