package com.mycompany.flashy;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

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
    private boolean hasSkippedWhilePaused = false;

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
        this.breakLength = breakMinutes * 60; // Convert to seconds
    }

    public void startSession() {
        if (!isPaused) {
            remainingTimeInSeconds = isStudySession ? studyLength : breakLength;
        }

        if (isStudySession) {
            lblFocusStatus.setText("Study Time!"); // Set lblFocusStatus to "Study Time!"
        } else {
            lblFocusStatus.setText("Break Time!"); // Set lblFocusStatus to "Break Time!"
        }

        startTimer();
        startProgressBarUpdate(); // Start updating the progress bar
        updateComboBoxVisibility();
    }

    private void startTimer() {
        timer = new Timer(); // Create a new Timer instance
        int updateInterval = 1000; // Update the progress bar every 100 milliseconds

        currentTask = new TimerTask() {
            @Override
            public void run() {
                if (remainingTimeInSeconds < 0) {
                    timer.cancel();
                    timer = null;
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

                    // Calculate the progress and update the progress bar here
                    updateProgressBar();
                }

                // Check if the button text is not "Start Timer" and hide cboxTimerSelection
                if (!btnStartTimer.getText().equals("Start Timer")) {
                    cboxTimerSelection.setEnabled(false);
                }
            }
        };

        timer.scheduleAtFixedRate(currentTask, 0, updateInterval);
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

    String message;
    String title;

    if (isStudySession) {
        message = "Study session complete. Would you like to start your break?";
        title = "Pomodoro Timer";
    } else {
        message = "Break over, ready to study?";
        title = "Pomodoro Timer";
    }

    if (!hasSkippedWhilePaused) {
        int response = JOptionPane.showConfirmDialog(null,
                message, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.OK_OPTION) {
            if (isStudySession) {
                isStudySession = false; // Start break session
                lblFocusStatus.setText("Break Time!"); // Update the focus status
            } else {
                isStudySession = true; // Start study session
                lblFocusStatus.setText("Study Time!"); // Update the focus status
            }
            startSession(); // Start the next session right away
        } else if (response == JOptionPane.CANCEL_OPTION) {
            // User clicked "Cancel," reset the timer to default and clear lblFocusStatus
            resetTimerToDefault();
            lblFocusStatus.setText("Time to Study!"); // Set lblFocusStatus to an empty string
        }
    } else {
        // Reset the flag since a skip action has occurred while paused
        hasSkippedWhilePaused = false;
    }
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
        Timer progressBarTimer = new Timer();
        progressBarTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isTimerRunning()) {
                    // Progress is calculated based on the remaining time as updated in startTimer()
                    updateProgressBar();
                }
            }
        }, 0, 1000); // Update progress every second
    }

    private void updateProgressBar() {
        int totalDuration = isStudySession ? studyLength : breakLength;
        double progressPerSecond = 100.0 / totalDuration; // Progress per second
        double elapsedSeconds = (totalDuration - remainingTimeInSeconds);

        // Calculate the progress based on elapsed time and progress per second
        int progress = (int) (elapsedSeconds * progressPerSecond);
        progressBar.setProgress(progress);
    }

    private void resetTimerToDefault() {
        // Reset the timer to default study and break lengths
        studyLength = 25 * 60; // Default to 25 minutes
        breakLength = 5 * 60; // Default to 5 minutes
        isPaused = false; // Reset the pause state
        isStudySession = true; // Set it to study session
        updateComboBoxVisibility();

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

        // Enable the combo box for changing timer intervals
        cboxTimerSelection.setEnabled(true);

        // Remove the code that grays out the button
        btnStartTimer.setEnabled(true);
        progressBar.setProgress(0); // Reset the progress bar to 0
    }

    public void skipSession() {
    if (isTimerRunning() || isPaused) {
        remainingTimeInSeconds = 0;
        if (isTimerRunning()) {
            timer.cancel();
            timer = null;
        }
        updateLabel(remainingTimeInSeconds);
        updateProgressBar();
        if (!isPaused) {
            // Only prompt the next action if the timer is not paused
            promptNextAction();
        }
    }
}
}
