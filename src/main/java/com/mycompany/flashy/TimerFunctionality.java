package com.mycompany.flashy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class TimerFunctionality {

    private Timer studyTimer; // Timer for the study session
    private Timer breakTimer; // Timer for the break session
    private long startTime;
    private long totalPauseTime;
    private long lastPauseTime;
    private JLabel timerLabel;
    private int studySessionTime; // Study session time in minutes
    private int breakSessionTime; // Break session time in minutes
    private boolean isPaused;
    private int completedPomodoros; // Track completed pomodoro sessions
    private boolean breakPopupShown; // Track if the break popup is shown

    public TimerFunctionality(JFrame frame, JLabel timerLabel, int studySessionTime) {
        this.timerLabel = timerLabel;
        this.studySessionTime = studySessionTime;
        this.isPaused = false;
        this.completedPomodoros = 0; // Initialize completed pomodoros to 0
        this.breakPopupShown = false;

        // Set break session time based on study session time
        if (studySessionTime == 25) {
            this.breakSessionTime = 5;
        } else if (studySessionTime == 50) {
            this.breakSessionTime = 10;
        } else {
            timerLabel.setText("Invalid duration. Please choose either 25 or 50 minutes.");
            return;
        }

        // Create the study session timer
        this.studyTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudyTimer();
            }
        });

        // Create the break session timer
        this.breakTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBreakTimer();
            }
        });
    }

    public void startPomodoro() {
        this.startTime = System.currentTimeMillis();
        this.totalPauseTime = 0;
        this.lastPauseTime = 0;
        this.isPaused = false;
        this.breakPopupShown = false;
        studyTimer.start();
        updateStudyTimer(); // Immediate update
    }

    private void updateStudyTimer() {
        long elapsed = System.currentTimeMillis() - startTime;
        long timeLeft = studySessionTime * 200 - (elapsed - totalPauseTime);

        if (timeLeft <= 0) {
            studyTimer.stop();
            timerLabel.setText("00:00");

            // Play the sound when the study session timer reaches zero
            playSound("src\\main\\java\\com\\mycompany\\flashy\\Alarm-Slow-A1-www.fesliyanstudios.com.wav");

            completedPomodoros++; // Increment completed pomodoros

            // Show the break popup and wait for user action
            showBreakPopup();

            return;
        }

        long minutesLeft = timeLeft / 60000;
        long secondsLeft = (timeLeft / 1000) % 60;
        timerLabel.setText(String.format("%02d:%02d", minutesLeft, secondsLeft));
    }

    private void showBreakPopup() {
        if (!breakPopupShown) {
            breakPopupShown = true;
            int option = JOptionPane.showConfirmDialog(timerLabel.getParent(), "Pomodoro complete. Take a break!\nClick 'OK' to start the break session.",
                    "Break Time", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                // Start the break session timer after user clicks "OK"
                startBreakSession();
            } else {
                // If the user cancels, reset the study session timer
                startPomodoro();
            }
        }
    }

    private void startBreakSession() {
        this.startTime = System.currentTimeMillis() + 1000; // Start the break timer with a 1-second delay
        totalPauseTime = 0; // Reset the total pause time
        lastPauseTime = 0; // Reset the last pause time

        // Set the timer label to display the initial break session time (5 minutes)
        timerLabel.setText(String.format("Next: %02d:%02d", breakSessionTime, 0));

        // Start the break session timer
        breakTimer.start();
    }

    private void updateBreakTimer() {
        long elapsed = System.currentTimeMillis() - startTime;
        long timeLeft = breakSessionTime * 60000 - (elapsed - totalPauseTime);

        if (timeLeft <= 0) {
            breakTimer.stop();
            timerLabel.setText("00:00");

            // Play the sound when the break session timer reaches zero
            playSound("src\\main\\java\\com\\mycompany\\flashy\\Break-Sound.wav");

            // Reset the study session time for the next Pomodoro
            studySessionTime = 25;
            timerLabel.setText("Next: 25:00");
            startPomodoro();

            return;
        }

        long minutesLeft = timeLeft / 60000;
        long secondsLeft = (timeLeft / 1000) % 60;
        timerLabel.setText(String.format("%02d:%02d", minutesLeft, secondsLeft));
    }

    // Method to play a sound
    private void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void pauseTimer() {
        if (!isPaused) {
            studyTimer.stop();
            breakTimer.stop();
            lastPauseTime = System.currentTimeMillis();
            isPaused = true;
        }
    }

    public void resumeTimer() {
        if (isPaused) {
            totalPauseTime += System.currentTimeMillis() - lastPauseTime;
            if (studyTimer.isRunning()) {
                studyTimer.start();
            } else if (breakTimer.isRunning()) {
                breakTimer.start();
            }
            isPaused = false;
        }
    }

    public void stopTimer() {
        studyTimer.stop();
        breakTimer.stop();
        timerLabel.setText("00:00");
    }
}
