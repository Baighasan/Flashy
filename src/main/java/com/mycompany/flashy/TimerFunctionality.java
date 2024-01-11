package com.mycompany.flashy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerFunctionality {

    private Timer timer;
    private long startTime;
    private long totalPauseTime;
    private long lastPauseTime;
    private JLabel timerLabel;
    private int sessionTime; // Session time in minutes
    private boolean isPaused;

    public TimerFunctionality(JFrame frame, JLabel timerLabel, int sessionTime) {
        this.timerLabel = timerLabel;
        this.sessionTime = sessionTime;
        this.isPaused = false;
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimer();
            }
        });
    }

    public void startPomodoro() {
        if (sessionTime != 25 && sessionTime != 50) {
            timerLabel.setText("Invalid duration. Please choose either 25 or 50 minutes.");
            return;
        }

        this.startTime = System.currentTimeMillis();
        this.totalPauseTime = 0;
        this.lastPauseTime = 0;
        this.isPaused = false;
        timer.start();
        updateTimer(); // Immediate update
    }

    private void updateTimer() {
        long elapsed = System.currentTimeMillis() - startTime;
        long timeLeft = sessionTime * 60000 - (elapsed - totalPauseTime);

        if (timeLeft <= 0) {
            timer.stop();
            timerLabel.setText("00:00");
            JOptionPane.showMessageDialog(timerLabel.getParent(), "Pomodoro complete. Take a break!");
            return;
        }

        long minutesLeft = timeLeft / 60000;
        long secondsLeft = (timeLeft / 1000) % 60;
        timerLabel.setText(String.format("%02d:%02d", minutesLeft, secondsLeft));
    }

    public void pauseTimer() {
        if (!isPaused) {
            timer.stop();
            lastPauseTime = System.currentTimeMillis();
            isPaused = true;
        }
    }

    public void resumeTimer() {
        if (isPaused) {
            totalPauseTime += System.currentTimeMillis() - lastPauseTime;
            timer.start();
            isPaused = false;
        }
    }

    public void stopTimer() {
        timer.stop();
        timerLabel.setText("00:00");
    }
}
