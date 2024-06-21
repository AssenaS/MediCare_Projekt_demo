package com.example.medicare_projekt;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class ReminderManager {

    MainWindowController mainWindowController;
    private List<Reminder> reminders = new ArrayList<>();
    private Timer timer = new Timer(true);

    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
        scheduleReminder(reminder);
    }

    public void setReminder(String message, LocalDateTime reminderTime) {
        Reminder reminder = new Reminder(message, reminderTime);
        reminders.add(reminder);
    }

    private void scheduleReminder(Reminder reminder) {
        long delay = reminder.getReminderTime().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli() - System.currentTimeMillis();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> showReminderWindow(reminder.getMessage(), reminder.getReminderTime()));
            }
        }, delay);
    }


    private void showReminderWindow(String message, LocalDateTime reminderTime) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Medikamenten-Erinnerung");
        alert.setHeaderText(null);
        alert.setContentText(message + " um " + reminderTime);
        alert.showAndWait();
    }

    public void cancelReminders() {
        timer.cancel();
    }


}