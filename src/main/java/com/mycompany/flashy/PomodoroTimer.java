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
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, "Time for a break!");
                            promptNextAction();
                        });
                    } else {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, "Break over, ready to study?");
                            promptNextAction();
                        });
                    }
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
        if (isStudySession) {
            int response = JOptionPane.showConfirmDialog(null, 
                "Study session complete. Would you like to start your break?", 
                "Pomodoro Timer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.OK_OPTION) {
                isStudySession = false; // Start break session
                startSession();
            } else {
                resetTimer(); // User chose to not continue, reset timer
            }
        } else {
            // For break session, automatically reset to study session
            isStudySession = true;
            resetTimer();
        }
    }

    private void resetTimer() {
        isStudySession = true; // Reset to study session
        sessionCount = 0; // Reset the session count
        isPaused = false; // Reset the pause state

        // Set the timer label to the study duration
        int studyMinutes = studyLength / 60;
        int studySeconds = studyLength % 60;
        String timeString = String.format("%02d:%02d", studyMinutes, studySeconds);
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(timeString);
        });

        // Signal GUI to update the start button text
        // This can be done by invoking a callback or using another approach
        // For example, if you have a callback method like updateStartButton()
        // You can call it here. If not, you'll need to handle it in your GUI class.
        updateStartButton("Start Timer");
    }

    // Placeholder for the updateStartButton method - implement this in your GUI class
    private void updateStartButton(String text) {
        // Implementation depends on your GUI structure.
        // For example, you might have a method in your GUI class that updates the button text
        // and you can call it here.
    }
}
