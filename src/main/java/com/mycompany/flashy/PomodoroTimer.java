package com.mycompany.flashy;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimer {
    private int studyLength;
    private int breakLength;
    private int sessionCount;
    private Timer timer;
    private TimerTask currentTask;
    private boolean isStudySession;
    private long remainingTimeInSeconds;
    private boolean isPaused;
    private JLabel timerLabel;

    public PomodoroTimer(JLabel timerLabel) {
        this.studyLength = 25 * 60; // Default to 25 minutes
        this.breakLength = 5 * 60; // Default to 5 minutes
        this.sessionCount = 0;
        this.timer = new Timer();
        this.timerLabel = timerLabel;
        this.isStudySession = true; // Start with a study session
        this.isPaused = false;
    }

    public void setSessionLengths(int studyMinutes, int breakMinutes) {
        this.studyLength = studyMinutes * 60;
        this.breakLength = breakMinutes * 60;
    }

    public void startSession() {
        if (!isPaused) {
            // If not paused, set session time
            remainingTimeInSeconds = isStudySession ? studyLength : breakLength;
        }
        startTimer();
    }

    private void startTimer() {
        if (currentTask != null) {
            currentTask.cancel();
        }
        currentTask = new TimerTask() {
            @Override
            public void run() {
                updateLabel(remainingTimeInSeconds);
                remainingTimeInSeconds--;
                if (remainingTimeInSeconds < 0) {
                    timer.cancel();
                    if (isStudySession) {
                        sessionCount++;
                        updateLabel("Study session completed. Total sessions: " + sessionCount);
                    } else {
                        updateLabel("Break completed.");
                    }
                    promptNextAction();
                }
            }
        };
        timer.scheduleAtFixedRate(currentTask, 0, 1000);
    }

    public void pauseSession() {
        if (currentTask != null) {
            currentTask.cancel();
            isPaused = true;
        }
    }

    public void resumeSession() {
        if (isPaused) {
            isPaused = false;
            startTimer();
        }
    }

    private void updateLabel(long remainingTimeInSeconds) {
        int minutes = (int) (remainingTimeInSeconds / 60);
        int seconds = (int) (remainingTimeInSeconds % 60);
        String timeString = String.format("%02d:%02d", minutes, seconds);
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText("Remaining time: " + timeString);
        });
    }

    private void updateLabel(String message) {
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(message);
        });
    }

    private void promptNextAction() {
        // This method should be replaced with GUI-based prompt
        isStudySession = !isStudySession;
        isPaused = false; // Ensure the timer starts fresh for the next session
        startSession();
    }
}
