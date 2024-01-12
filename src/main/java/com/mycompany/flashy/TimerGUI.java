/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.flashy;

/**
 *
 * @author arpan
 */
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class TimerGUI extends javax.swing.JFrame {
private PomodoroTimer pomodoroTimer;
    /**
     * Creates new form TimerGUI
     */
    public TimerGUI() {
        initComponents();
        pomodoroTimer = new PomodoroTimer(lblTimerDisplay, btnStartTimer, lblPomodoroCountDisplay);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTimerDisplay = new javax.swing.JLabel();
        btnStartTimer = new javax.swing.JButton();
        cboxTimerSelection = new javax.swing.JComboBox<>();
        lblPomodoroCountDisplay = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTimerDisplay.setFont(new java.awt.Font("Swis721 BT", 1, 24)); // NOI18N
        lblTimerDisplay.setText("25:00");

        btnStartTimer.setText("Start Timer");
        btnStartTimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartTimerActionPerformed(evt);
            }
        });

        cboxTimerSelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "25 Min", "50 Min" }));

        lblPomodoroCountDisplay.setText("You have completed 0 pomodoro sessions!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTimerDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cboxTimerSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnStartTimer)
                        .addGap(121, 121, 121))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblPomodoroCountDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(lblTimerDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStartTimer)
                    .addComponent(cboxTimerSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(lblPomodoroCountDisplay)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartTimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartTimerActionPerformed
        String buttonText = btnStartTimer.getText();
        int selectedTimeInt = 0;
        int breakTime = 0;
        if (buttonText.equals("Start Timer")) {
            btnStartTimer.setText("Pause");
            String selectedTime = (String) cboxTimerSelection.getSelectedItem();
            if (selectedTime.equals("25 Min")) {
                selectedTimeInt = 25;
                breakTime = 5;
            } else if (selectedTime.equals("50 Min")) {
                selectedTimeInt = 50;
                breakTime= 10;
            }
            pomodoroTimer.setSessionLengths(1, breakTime); // Set to default or get values from user input
            pomodoroTimer.startSession();
            // Start initial timer
        } else if (buttonText.equals("Pause")) {
            btnStartTimer.setText("Resume");
            pomodoroTimer.pauseSession();
            // Pause timer
        } else if (buttonText.equals("Resume")) {
            btnStartTimer.setText("Pause");
            pomodoroTimer.resumeSession();
            // Resume timer
        }
        
    }//GEN-LAST:event_btnStartTimerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TimerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartTimer;
    private javax.swing.JComboBox<String> cboxTimerSelection;
    private javax.swing.JLabel lblPomodoroCountDisplay;
    private javax.swing.JLabel lblTimerDisplay;
    // End of variables declaration//GEN-END:variables
}
