package com.mycompany.flashy;

import java.util.Timer;
import java.util.TimerTask;

public class Flashy {

    private static final long SECOND = 1000; // 1 second in milliseconds
    private static final long MINUTE = 60000; // 1 minute in milliseconds
    private static final long SHORT_STUDY_DURATION = 25 * MINUTE;
    private static final long LONG_STUDY_DURATION = 50 * MINUTE;
    private static final long SHORT_BREAK_DURATION = 5 * MINUTE;
    private static final long LONG_BREAK_DURATION = 10 * MINUTE;

    private Timer timer;
    private Timer countdownTimer;
    private boolean isStudyPeriod;
    private boolean isLongPeriod;

    public Flashy() {
        this.timer = new Timer();
        this.countdownTimer = new Timer();
        this.isStudyPeriod = true; // start with a study period
        this.isLongPeriod = false; // start with a short period
    }

    public void startPomodoro() {
        scheduleNextPeriod();
    }

    private void scheduleNextPeriod() {
        countdownTimer.cancel(); // Stop previous countdown
        countdownTimer = new Timer(); // Reset the countdown timer

        long periodDuration;
        if (isStudyPeriod) {
            periodDuration = isLongPeriod ? LONG_STUDY_DURATION : SHORT_STUDY_DURATION;
            System.out.print("Study period started for " + (periodDuration / MINUTE) + " minutes.  ");
        } else {
            periodDuration = isLongPeriod ? LONG_BREAK_DURATION : SHORT_BREAK_DURATION;
            System.out.print("Break period started for " + (periodDuration / MINUTE) + " minutes.  ");
        }

        startCountdown(periodDuration);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isStudyPeriod = !isStudyPeriod;
                if (isStudyPeriod) {
                    isLongPeriod = !isLongPeriod; // toggle between long and short periods
                }
                scheduleNextPeriod();
            }
        }, periodDuration);
    }

    private void startCountdown(long periodDuration) {
        countdownTimer.scheduleAtFixedRate(new TimerTask() {
            long timeLeft = periodDuration / SECOND;

            @Override
            public void run() {
                if (timeLeft > 0) {
                    long minutes = timeLeft / 60;
                    long seconds = timeLeft % 60;
                    System.out.printf("\rTime left: %02d:%02d     ", minutes, seconds); // Update on the same line
                    timeLeft--;
                } else {
                    this.cancel();
                }
            }
        }, 0, SECOND);
    }

    public static void main(String[] args) {
        System.out.println("Flashy!");
        System.out.println("Soham loves Hasn!");

        Flashy flashy = new Flashy();
        flashy.startPomodoro();
    }
}
