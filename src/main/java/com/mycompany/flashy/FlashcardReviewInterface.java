/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.flashy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author arpan
 */
public class FlashcardReviewInterface extends javax.swing.JFrame {

    private FlashcardTopic topic;
    private Flashcard currentFlashcard;
    private List<Flashcard> sessionFlashcards;
    private List<Flashcard> hardFlashcards;
    private int easyFlashcardCount; // Counter for 'Easy' flashcards
    private Queue<Flashcard> hardFlashcardsQueue;
    private int currentIndex; // Index to track the current flashcard

    /**
     * Creates new form FlashcardReviewInterface
     */
    public FlashcardReviewInterface(FlashcardTopic topic) {
            
        this.topic = topic;
        this.sessionFlashcards = new ArrayList<>(topic.getFlashcardList()); // Copy all flashcards to session list\
        for (Flashcard flashcard : sessionFlashcards) {
    System.out.println(flashcard.getQuestion());
}
        
        this.hardFlashcards = new ArrayList<>(); // Initialize the list for 'hard' flashcards
        this.easyFlashcardCount = 0; // Initialize 'Easy' flashcard counter
        this.hardFlashcardsQueue = new LinkedList<>();
        this.currentIndex = 0; // Initialize the current index
        if (!sessionFlashcards.isEmpty()) {
            currentFlashcard = sessionFlashcards.get(currentIndex); // Start with the first flashcard
        }
        initComponents();
        lblFlashcardCount.setText("Total Flashcards: " + sessionFlashcards.size());
    lblFlashcardsRemaining.setText("Flashcards Remaining: " + sessionFlashcards.size());
        displayFlashcard();
        // Optionally, use topic to populate UI elements, like a list of flashcards
        //populateFlashcards();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        txtQuestionDisplay = new javax.swing.JTextArea();
        lblQuestion = new javax.swing.JLabel();
        lblFlashcardCount = new javax.swing.JLabel();
        lblFlashcardsRemaining = new javax.swing.JLabel();
        pnlReviewBottom = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        btnShowAnswer = new javax.swing.JButton();
        btnEasy = new javax.swing.JButton();
        btnHard = new javax.swing.JButton();
        pnlReviewTop = new javax.swing.JPanel();
        imgQuestion = new javax.swing.JLabel();
        imgAnswer = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAnswerDisplay = new javax.swing.JTextArea();
        lblAnswer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtQuestionDisplay.setColumns(20);
        txtQuestionDisplay.setRows(5);
        jScrollPane2.setViewportView(txtQuestionDisplay);
        txtQuestionDisplay.setLineWrap(true);  // Enable line wrap
        txtQuestionDisplay.setWrapStyleWord(true);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 510, 139));

        lblQuestion.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lblQuestion.setText("Question");
        getContentPane().add(lblQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 84, 20));

        lblFlashcardCount.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblFlashcardCount.setText("Total Flashcard Count: 0");
        getContentPane().add(lblFlashcardCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 420, 168, -1));

        lblFlashcardsRemaining.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblFlashcardsRemaining.setText("Flashcards Remaining: 0");
        getContentPane().add(lblFlashcardsRemaining, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 440, 168, -1));

        pnlReviewBottom.setBackground(new java.awt.Color(204, 204, 255));
        pnlReviewBottom.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnBack.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnShowAnswer.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        btnShowAnswer.setText("Show Answer");
        btnShowAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAnswerActionPerformed(evt);
            }
        });

        btnEasy.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        btnEasy.setText("Easy");
        btnEasy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEasyActionPerformed(evt);
            }
        });

        btnHard.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        btnHard.setText("Hard");
        btnHard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlReviewBottomLayout = new javax.swing.GroupLayout(pnlReviewBottom);
        pnlReviewBottom.setLayout(pnlReviewBottomLayout);
        pnlReviewBottomLayout.setHorizontalGroup(
            pnlReviewBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReviewBottomLayout.createSequentialGroup()
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnShowAnswer)
                .addGap(16, 16, 16))
            .addGroup(pnlReviewBottomLayout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(btnEasy, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(btnHard, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        pnlReviewBottomLayout.setVerticalGroup(
            pnlReviewBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReviewBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlReviewBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHard, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnEasy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlReviewBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnShowAnswer, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(pnlReviewBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 750, 90));

        pnlReviewTop.setBackground(new java.awt.Color(255, 204, 204));
        pnlReviewTop.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 153), 2));
        pnlReviewTop.setForeground(new java.awt.Color(255, 204, 204));

        imgQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emoji.png"))); // NOI18N
        imgQuestion.setText("jLabel1");

        imgAnswer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rsz_4877819.png"))); // NOI18N
        imgAnswer.setText("jLabel2");

        txtAnswerDisplay.setColumns(20);
        txtAnswerDisplay.setRows(5);
        jScrollPane3.setViewportView(txtAnswerDisplay);
        txtAnswerDisplay.setLineWrap(true);  // Enable line wrap
        txtAnswerDisplay.setWrapStyleWord(true);

        lblAnswer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAnswer.setText("Answer");

        javax.swing.GroupLayout pnlReviewTopLayout = new javax.swing.GroupLayout(pnlReviewTop);
        pnlReviewTop.setLayout(pnlReviewTopLayout);
        pnlReviewTopLayout.setHorizontalGroup(
            pnlReviewTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReviewTopLayout.createSequentialGroup()
                .addGroup(pnlReviewTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlReviewTopLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imgQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlReviewTopLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(imgAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(pnlReviewTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        pnlReviewTopLayout.setVerticalGroup(
            pnlReviewTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReviewTopLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(imgQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlReviewTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlReviewTopLayout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(imgAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlReviewTopLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                        .addComponent(lblAnswer)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );

        getContentPane().add(pnlReviewTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAnswerActionPerformed
        // TODO add your handling code here:
         txtAnswerDisplay.setText(currentFlashcard.getAnswer());
        btnShowAnswer.setEnabled(false);
        btnHard.setEnabled(true);
        btnEasy.setEnabled(true);
    }//GEN-LAST:event_btnShowAnswerActionPerformed

    private void btnEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEasyActionPerformed
        // TODO add your handling code here:
          sessionFlashcards.remove(currentIndex);
    easyFlashcardCount++;
    lblFlashcardsRemaining.setText("Flashcards Remaining: " + (sessionFlashcards.size() - currentIndex));
    checkAndDisplayNextFlashcard();
    }//GEN-LAST:event_btnEasyActionPerformed

    private void btnHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHardActionPerformed
        // TODO add your handling code here:
        lblFlashcardsRemaining.setText("Flashcards Remaining: " + (sessionFlashcards.size() - currentIndex));
       checkAndDisplayNextFlashcard();
    }//GEN-LAST:event_btnHardActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
       this.dispose();
        //FlashCardDisplayGUI displayGUI = new FlashCardDisplayGUI(); // Create a new instance of FlashCardDisplayGUI
        //displayGUI.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(FlashcardReviewInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FlashcardReviewInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FlashcardReviewInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FlashcardReviewInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Create an instance of FlashcardTopic and pass it to the constructor

            }
        });
    }

  private void displayFlashcard() {
        if (currentFlashcard != null) {
            txtQuestionDisplay.setText(currentFlashcard.getQuestion()); // Display the question
            txtAnswerDisplay.setText(""); // Clear the answer field
            btnShowAnswer.setEnabled(true);
            btnHard.setEnabled(false);
            btnEasy.setEnabled(false);
        } else {
            txtQuestionDisplay.setText("You have finished reviewing your flashcards for this session.");
            txtAnswerDisplay.setText("");
            btnShowAnswer.setEnabled(false);
            btnHard.setEnabled(false);
            btnEasy.setEnabled(false);
            btnBack.setEnabled(true); // Enable the "Back" button
        }
    }

    private void checkAndDisplayNextFlashcard() {
        // If there are no more flashcards in the session, check for hard flashcards
        if (hardFlashcards.contains(currentFlashcard)) {
        sessionFlashcards.add(currentFlashcard);  // Re-add the hard flashcard at the end of the session
        hardFlashcards.remove(currentFlashcard);  // Remove from the hardFlashcards list to prevent duplicates
    }

    // Check if there are flashcards left in the session
    if (currentIndex < sessionFlashcards.size()) {
        currentFlashcard = sessionFlashcards.get(currentIndex);  // Get the next flashcard
        displayFlashcard();
    } else {
        // No more flashcards in the session, display completion message
        currentFlashcard = null;
        displayFlashcard();
    }
    if (currentFlashcard == null) {
        lblFlashcardsRemaining.setText("Flashcards Remaining: 0");
    }

    // Increment the currentIndex for the next call to this method
    //currentIndex++;
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEasy;
    private javax.swing.JButton btnHard;
    private javax.swing.JButton btnShowAnswer;
    private javax.swing.JLabel imgAnswer;
    private javax.swing.JLabel imgQuestion;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAnswer;
    private javax.swing.JLabel lblFlashcardCount;
    private javax.swing.JLabel lblFlashcardsRemaining;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JPanel pnlReviewBottom;
    private javax.swing.JPanel pnlReviewTop;
    private javax.swing.JTextArea txtAnswerDisplay;
    private javax.swing.JTextArea txtQuestionDisplay;
    // End of variables declaration//GEN-END:variables
}
