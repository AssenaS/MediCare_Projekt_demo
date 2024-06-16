package com.example.medicare_projekt;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderController {
    private List<Reminder> reminders = new ArrayList<>();
    private Timer timer = new Timer(true);

    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
        scheduleReminder(reminder);
    }

    private void scheduleReminder(Reminder reminder) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Erinnerung: " + reminder.getMessage());
            }
        }, reminder.getReminderTime().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli() - System.currentTimeMillis());
    }

    public void cancelReminders() {
        timer.cancel();
    }
}