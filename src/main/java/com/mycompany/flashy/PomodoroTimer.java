package com.mycompany.flashy;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

// PomodoroTimer class manages the functionality of a Pomodoro timer,
// which alternates between study sessions and break sessions.
public class PomodoroTimer {

    // Member variables for the PomodoroTimer class
    private RingProgressBar progressBar; // Progress bar to display the timer's progress visually.
    private int studyLength; // Duration of study sessions in seconds.
    private int breakLength; // Duration of break sessions in seconds.
    private static int globalSessionCount = 0; // Tracks the number of completed study sessions.
    private Timer timer; // Timer object to schedule tasks.
    private TimerTask currentTask; // The current timer task that's running.
    private boolean isStudySession; // Flag to indicate if the current session is a study session.
    private long remainingTimeInSeconds; // Time remaining in the current session in seconds.
    private boolean isPaused; // Flag to indicate if the timer is currently paused.
    private JLabel timerLabel; // Label to display the remaining time.
    private JButton btnStartTimer; // Button to start or pause the timer.
    private JLabel lblPomodoroCountDisplay; // Label to display the number of completed sessions.
    private JLabel lblFocusStatus; // Label to display if it's study time or break time.
    private JComboBox cboxTimerSelection; // ComboBox to allow selection of different timer durations.
    private boolean hasSkippedWhilePaused = false; // Flag to indicate if the session was skipped while paused.

    // Constructor for the PomodoroTimer class.
    public PomodoroTimer(JLabel timerLabel, JButton btnStartTimer, JLabel lblPomodoroCountDisplay, JLabel lblFocusStatus, JComboBox cboxTimerSelection, RingProgressBar progressBar) {
        // Initialize member variables with provided arguments.
        this.btnStartTimer = btnStartTimer;
        this.lblFocusStatus = lblFocusStatus;
        this.lblPomodoroCountDisplay = lblPomodoroCountDisplay;
        this.cboxTimerSelection = cboxTimerSelection;
        this.studyLength = 25 * 60; // Default to 25 minutes for study sessions.
        this.breakLength = 5 * 60; // Default to 5 minutes for break sessions.
        this.timerLabel = timerLabel;
        this.isStudySession = true; // Start with a study session.
        this.isPaused = false; // Timer is not paused initially.
        updateComboBoxVisibility(); // Update the visibility of the combo box based on timer state.
        this.progressBar = progressBar;
    }

    // Method to update the visibility of the combo box based on the timer's running state.
    private void updateComboBoxVisibility() {
        if (!isTimerRunning()) {
            cboxTimerSelection.setEnabled(true); // Enable the combo box when the timer is not running.
        } else {
            cboxTimerSelection.setEnabled(false); // Disable the combo box when the timer is running.
        }
    }

    // Method to set the duration of study and break sessions.
    public void setSessionLengths(int studyMinutes, int breakMinutes) {
        this.studyLength = studyMinutes * 60; // Convert minutes to seconds for study sessions.
        this.breakLength = breakMinutes * 60; // Convert minutes to seconds for break sessions.
    }

    // Method to start a Pomodoro session (either study or break).
    public void startSession() {
        if (!isPaused) {
            // If not paused, set the remaining time to the length of the current session.
            remainingTimeInSeconds = isStudySession ? studyLength : breakLength;
        }

        // Update the focus status label based on the type of session.
        if (isStudySession) {
            lblFocusStatus.setText("Study Time!");
        } else {
            lblFocusStatus.setText("Break Time!");
        }

        // Change the text of the start button to indicate that the timer can be paused.
        updateStartButton("Pause");

        // Start the timer and the progress bar update.
        startTimer();
        startProgressBarUpdate();
        // Update the visibility of the timer selection combo box.
        updateComboBoxVisibility();
    }

    // Method to start the timer.
    private void startTimer() {
        timer = new Timer(); // Create a new Timer instance.
        int updateInterval = 1000; // Timer will update every second.

        currentTask = new TimerTask() {
            @Override
            public void run() {
                // If the remaining time is less than 0, the session is over.
                if (remainingTimeInSeconds < 0) {
                    timer.cancel();
                    timer = null;
                    if (isStudySession) {
                        // If it was a study session, increment the session count and show a break message.
                        globalSessionCount++;
                        lblPomodoroCountDisplay.setText("You have completed " + globalSessionCount + " pomodoro sessions!");
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, "Time for a break!");
                            promptNextAction();
                        });
                    } else {
                        // If it was a break session, show a message that the study session is starting.
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, "Break over, ready to study?");
                            promptNextAction();
                        });
                    }
                } else {
                    // If time is remaining, update the label and decrement the remaining time.
                    updateLabel(remainingTimeInSeconds);
                    remainingTimeInSeconds--;

                    // Update the progress bar to reflect the remaining time.
                    updateProgressBar();
                }

                // Disable the timer selection combo box if the timer is running.
                if (!btnStartTimer.getText().equals("Start Timer")) {
                    cboxTimerSelection.setEnabled(false);
                }
            }
        };

        // Schedule the timer task to run at a fixed rate.
        timer.scheduleAtFixedRate(currentTask, 0, updateInterval);
    }

    // Method to pause the Pomodoro session.
    public void pauseSession() {
        if (currentTask != null) {
            // Cancel the current task and set the paused flag.
            currentTask.cancel();
            isPaused = true;
        }
    }

    // Method to resume the Pomodoro session.
    public void resumeSession() {
        if (isPaused) {
            // If the timer was paused, reset the paused flag and start the timer again.
            isPaused = false;
            startTimer();
        }
    }

    // Method to update the timer label with the remaining time.
    private void updateLabel(long remainingTimeInSeconds) {
        // Calculate the minutes and seconds from the remaining time.
        int minutes = (int) (remainingTimeInSeconds / 60);
        int seconds = (int) (remainingTimeInSeconds % 60);
        // Format the time as a string.
        String timeString = String.format("%02d:%02d", minutes, seconds);
        // Update the timer label with the formatted time.
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(timeString);
        });
    }

    // Method to prompt the user for the next action after a session completes.
    private void promptNextAction() {
        String message;
        String title;

        // Set the message and title based on whether it's a study session or a break session.
        if (isStudySession) {
            message = "Study session complete. Would you like to start your break?";
            title = "Pomodoro Timer";
        } else {
            message = "Break over, ready to study?";
            title = "Pomodoro Timer";
        }

        if (!hasSkippedWhilePaused) {
            // Show a confirmation dialog with the message and title.
            int response = JOptionPane.showConfirmDialog(null,
                    message, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            // Handle the user's response to the dialog.
            if (response == JOptionPane.OK_OPTION) {
                // If the user clicks "OK," toggle the session type and start the next session.
                if (isStudySession) {
                    isStudySession = false; // Start break session.
                    lblFocusStatus.setText("Break Time!"); // Update the focus status label.
                } else {
                    isStudySession = true; // Start study session.
                    lblFocusStatus.setText("Study Time!"); // Update the focus status label.
                }
                startSession(); // Start the next session.
            } else if (response == JOptionPane.CANCEL_OPTION) {
                // If the user clicks "Cancel," reset the timer to its default state.
                resetTimerToDefault();
                lblFocusStatus.setText("Time to Study!"); // Update the focus status label.
            }
        } else {
            // If a skip action occurred while paused, reset the flag.
            hasSkippedWhilePaused = false;
        }
    }

    // Method to reset the timer to its initial state.
    private void resetTimer() {
        isPaused = false; // Reset the paused flag.

        // Calculate and format the default study time.
        int studyMinutes = studyLength / 60;
        int studySeconds = studyLength % 60;
        String timeString = String.format("%02d:%02d", studyMinutes, studySeconds);
        // Update the timer label with the default study time.
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(timeString);
        });

        // Update the start button text to "Start Timer."
        updateStartButton("Start Timer");

        // Enable the start button and reset the progress bar.
        btnStartTimer.setEnabled(true);
        progressBar.setProgress(0);
    }

    // Method to update the text of the start/pause button.
    private void updateStartButton(String text) {
        SwingUtilities.invokeLater(() -> {
            btnStartTimer.setText(text);
        });
    }

    // Method to check if the timer is currently running.
    private boolean isTimerRunning() {
        return currentTask != null && !isPaused;
    }

    // Method to start updating the progress bar.
    private void startProgressBarUpdate() {
        Timer progressBarTimer = new Timer();
        progressBarTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // If the timer is running, update the progress bar.
                if (isTimerRunning()) {
                    updateProgressBar();
                }
            }
        }, 0, 1000); // Update the progress bar every second.
    }

    // Method to update the progress bar based on the remaining time.
    private void updateProgressBar() {
        // Calculate the total duration and progress per second.
        int totalDuration = isStudySession ? studyLength : breakLength;
        double progressPerSecond = 100.0 / totalDuration;
        double elapsedSeconds = (totalDuration - remainingTimeInSeconds);

        // Calculate and set the progress on the progress bar.
        int progress = (int) (elapsedSeconds * progressPerSecond);
        progressBar.setProgress(progress);
    }

    // Method to reset the timer to default study and break lengths.
    private void resetTimerToDefault() {
        // Reset study and break lengths to default values.
        studyLength = 25 * 60; // Default to 25 minutes for study sessions.
        breakLength = 5 * 60; // Default to 5 minutes for break sessions.
        isPaused = false; // Reset the paused flag.
        isStudySession = true; // Start with a study session.
        // Update the visibility of the timer selection combo box.
        updateComboBoxVisibility();

        // Calculate and format the default study time.
        int studyMinutes = studyLength / 60;
        int studySeconds = studyLength % 60;
        String timeString = String.format("%02d:%02d", studyMinutes, studySeconds);
        // Update the timer label with the default study time.
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(timeString);
        });

        // Update the start button text to "Start Timer" and enable the combo box for timer selection.
        updateStartButton("Start Timer");
        cboxTimerSelection.setEnabled(true);

        // Enable the start button and reset the progress bar.
        btnStartTimer.setEnabled(true);
        progressBar.setProgress(0);
    }

    // Method to skip the current session and prompt for the next action.
    public void skipSession() {
        if (isTimerRunning() || isPaused) {
            // Set the remaining time to 0 to end the current session.
            remainingTimeInSeconds = 0;
            if (timer != null) {
                // Cancel and nullify the timer.
                timer.cancel();
                timer = null;
            }
            currentTask = null; // Reset the current task.

            // Update the timer label and progress bar.
            updateLabel(remainingTimeInSeconds);
            updateProgressBar();

            // Reset the paused state and update the start button text.
            isPaused = false;
            updateStartButton("Start Timer");

            // Prompt for the next action, regardless of the paused state.
            promptNextAction();
        }
    }
}
