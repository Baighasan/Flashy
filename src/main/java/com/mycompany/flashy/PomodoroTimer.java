package com.mycompany.flashy;

import javax.swing.*;
import javax.swing.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroTimer {

    private RingProgressBar progressBar;
    private int studyLength;
    private int breakLength;
    private static int globalSessionCount = 0; // Global session count
    private Timer timer;
    private TimerTask currentTask;
    private boolean isStudySession;
    private long remainingTimeInSeconds;
    private boolean isPaused;
    private JLabel timerLabel;
    private JButton btnStartTimer;
    private JLabel lblPomodoroCountDisplay;
    private JLabel lblFocusStatus;
    private JComboBox cboxTimerSelection; // Add this component

    public PomodoroTimer(JLabel timerLabel, JButton btnStartTimer, JLabel lblPomodoroCountDisplay, JLabel lblFocusStatus, JComboBox cboxTimerSelection, RingProgressBar progressBar) {
        this.btnStartTimer = btnStartTimer;
        this.lblFocusStatus = lblFocusStatus;
        this.lblPomodoroCountDisplay = lblPomodoroCountDisplay;
        this.cboxTimerSelection = cboxTimerSelection; // Initialize the combo box
        this.studyLength = 25 * 60; // Default to 25 minutes
        this.breakLength = 5 * 60; // Default to 5 minutes
        this.timerLabel = timerLabel;
        this.isStudySession = true; // Start with a study session
        this.isPaused = false;
        updateComboBoxVisibility(); // Update the combo box visibility initially
        this.progressBar = progressBar;
    }

    private void updateComboBoxVisibility() {
        if (!isTimerRunning()) {
            cboxTimerSelection.setEnabled(true); // Enable the combo box when the timer is not running
        } else {
            cboxTimerSelection.setEnabled(false); // Disable the combo box when the timer is running
        }
    }

    public void setSessionLengths(int studyMinutes, int breakMinutes) {
        this.studyLength = studyMinutes * 60; // Convert to seconds
        this.breakLength = breakMinutes * 10; // Convert to seconds
    }

    public void startSession() {
        if (!isPaused) {
            remainingTimeInSeconds = isStudySession ? studyLength : breakLength;
        }
        startTimer();
        startProgressBarUpdate(); // Start updating the progress bar
        updateComboBoxVisibility();
    }

    private void startTimer() {
        int delay = 1000; // 1-second interval for updating the timer
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (remainingTimeInSeconds < 0) {
                    timer.stop();
                    if (isStudySession) {
                        globalSessionCount++; // Increment global session count
                        lblPomodoroCountDisplay.setText("You have completed " + globalSessionCount + " pomodoro sessions!");
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

                    // Update the progress bar here
                    int progress = (int) (((double) remainingTimeInSeconds / (isStudySession ? studyLength : breakLength)) * 100);
                    progressBar.setProgress(100 - progress); // Reverse progress to match the countdown
                }

                // Check if the button text is not "Start Timer" and hide cboxTimerSelection
                if (!btnStartTimer.getText().equals("Start Timer")) {
                    cboxTimerSelection.setEnabled(false);
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();
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
                lblFocusStatus.setText("Break Time!"); // Update the focus status
                startSession(); // Start the next session right away
            } else {
                resetTimer(); // User chose not to continue, reset timer
                cboxTimerSelection.setEnabled(true); // Enable the combo box when the user clicks "cancel"
            }
        } else {
            // For break session, automatically reset to the study session
            isStudySession = true;
            lblFocusStatus.setText("Study Time!"); // Update the focus status
            startSession(); // Start the next session right away
        }
        // Don't need to update combo box visibility here
    }

    private void resetTimer() {
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

        // Remove the code that grays out the button
        btnStartTimer.setEnabled(true);
        progressBar.setProgress(0); // Reset the progress bar to 0
    }

    // Rest of the class remains the same...
    // Placeholder for the updateStartButton method - implement this in your GUI class
    private void updateStartButton(String text) {
        // Implementation depends on your GUI structure.
        // For example, you might have a method in your GUI class that updates the button text
        // and you can call it here.
        btnStartTimer.setText(text);
    }

    private boolean isTimerRunning() {
        return currentTask != null && !isPaused;
    }

    private void startProgressBarUpdate() {
        int delay = 1000; // 1-second interval for updating the progress bar
        Timer progressBarTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isTimerRunning()) {
                    int progress = (int) (((double) remainingTimeInSeconds / (isStudySession ? studyLength : breakLength)) * 100);
                    progressBar.setProgress(progress);
                }
            }
        });
        progressBarTimer.setInitialDelay(0);
        progressBarTimer.start();
    }
}
