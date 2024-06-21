package com.example.medicare_projekt;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private LocalDate birthday;
    private ArrayList<Integer> index;
    private List<Reminder> reminders;

    public Patient(String name, LocalDate birthday, ArrayList<Integer> index) {
        this.name = name;
        this.birthday = birthday;
        this.index = index;
        this.reminders = new ArrayList<>();
    }

    public ArrayList<Integer> getIndex() {
        return index;
    }

    public void setIndex(ArrayList<Integer> index) {
        this.index = index;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addReminder(String message, LocalDateTime time) {
        Reminder reminder = new Reminder(message, time);
        reminders.add(reminder);
        scheduleReminder(reminder);
    }

    private void scheduleReminder(Reminder reminder) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                showReminder(reminder);
            }
        }, Date.from(reminder.getReminderTime().atZone(ZoneId.systemDefault()).toInstant()));
    }

    private void showReminder(Reminder reminder) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reminder");
            alert.setHeaderText("Reminder for " + name);
            alert.setContentText(reminder.getMessage());
            alert.showAndWait();
        });
    }

    @Override
    public String toString() {
        return "Patient: " + name + ", " + birthday + ", " + index;
    }

}
