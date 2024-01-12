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
        this.timerLabel = timerLabel;
        this.isStudySession = true; // Start with a study session
        this.isPaused = false;
    }

    public void setSessionLengths(int studyMinutes, int breakMinutes) {
        this.studyLength = 1 * 10;
        this.breakLength = breakMinutes * 60;
    }

    public void startSession() {
        if (!isPaused) {
            remainingTimeInSeconds = isStudySession ? studyLength : breakLength;
        }
        startTimer();
    }

    private void startTimer() {
        timer = new Timer(); // Create a new Timer instance
        currentTask = new TimerTask() {
            @Override
            public void run() {
                if (remainingTimeInSeconds < 0) {
                    timer.cancel();
                    timer = null;
                    if (isStudySession) {
                        sessionCount++;
                    }
                    promptNextAction();
                } else {
                    updateLabel(remainingTimeInSeconds);
                    remainingTimeInSeconds--;
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
            timerLabel.setText(timeString);
        });
    }

    private void promptNextAction() {
        isStudySession = !isStudySession;
        isPaused = false; // Ensure the timer starts fresh for the next session
        startSession();
    }
}
