/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flashy;

/**
 *
 * @author arpan
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class FlashcardEditDialog extends JDialog {
    private JTextField txtQuestion;   // Text field for entering the question
    private JTextField txtAnswer;     // Text field for entering the answer
    private JButton btnSave;          // Button to save changes
    private JButton btnCancel;        // Button to cancel the edit
    private JButton btnDelete;        // Button to delete the flashcard
    private boolean isDeleted = false; // Flag to indicate if the flashcard is deleted
    private boolean isSaved = false;   // Flag to indicate if changes are saved

    public FlashcardEditDialog(Frame parent, String question, String answer) {
        super(parent, "Edit Flashcard", true);  // Create a modal dialog with a title
        setSize(400, 200);                      // Set the size of the dialog
        setLayout(new GridLayout(0, 1));        // Use a grid layout with variable rows and one column

        // Create text fields for entering question and answer
        txtQuestion = new JTextField(question);
        txtAnswer = new JTextField(answer);

        // Add labels and text fields for question and answer
        add(new JLabel(" Question:"));
        add(txtQuestion);
        add(new JLabel(" Answer:"));
        add(txtAnswer);

        // Create "Save" button and define its action
        btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSaved = true;     // Mark changes as saved
                setVisible(false);  // Close the dialog
            }
        });

        // Create "Cancel" button and define its action
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);  // Close the dialog without saving changes
            }
        });

        // Create "Delete" button and define its action
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                    FlashcardEditDialog.this,
                    "Are you sure you want to delete this flashcard?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    isDeleted = true;  // Mark the flashcard as deleted
                    setVisible(false); // Close the dialog
                }
            }
        });

        // Create a panel to hold the buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(btnSave);
        buttonsPanel.add(btnCancel);
        buttonsPanel.add(btnDelete);
        add(buttonsPanel);  // Add the panel with buttons to the dialog

        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Close the dialog when the user clicks the close button
        setLocationRelativeTo(parent);
    }

   public boolean isSaved() {
        return isSaved;
    }

    // Getter method to retrieve the edited question
    public String getQuestion() {
        return txtQuestion.getText();
    }

    // Getter method to retrieve the edited answer
    public String getAnswer() {
        return txtAnswer.getText();
    }

    // Getter method to check if the flashcard is deleted
    public boolean isDeleted() {
        return isDeleted;
    }
}