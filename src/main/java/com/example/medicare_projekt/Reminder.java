package com.example.medicare_projekt;
import java.time.LocalDateTime;

public class Reminder {
    private String message;
    private LocalDateTime reminderTime;

    public Reminder(String message, LocalDateTime reminderTime) {
        this.message = message;
        this.reminderTime = reminderTime;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }
}