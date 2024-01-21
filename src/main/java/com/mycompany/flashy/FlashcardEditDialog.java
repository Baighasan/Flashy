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
    private JTextField txtQuestion;
    private JTextField txtAnswer;
    private JButton btnSave;
    private JButton btnCancel;
    private JButton btnDelete;
    private boolean isDeleted = false;
    private boolean isSaved = false;

    public FlashcardEditDialog(Frame parent, String question, String answer) {
        super(parent, "Edit Flashcard", true);
        setSize(400, 200);
        setLayout(new GridLayout(0, 1));

        txtQuestion = new JTextField(question);
        txtAnswer = new JTextField(answer);

        add(new JLabel(" Question:"));
        add(txtQuestion);
        add(new JLabel(" Answer:"));
        add(txtAnswer);

        btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSaved = true;
                setVisible(false);
            }
        });

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        
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
                    isDeleted = true;
                    setVisible(false);
                }
            }
        });
        

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(btnSave);
        buttonsPanel.add(btnCancel);
        buttonsPanel.add(btnDelete);
        add(buttonsPanel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
    }

    public boolean isSaved() {
        return isSaved;
    }

    public String getQuestion() {
        return txtQuestion.getText();
    }

    public String getAnswer() {
        return txtAnswer.getText();
    } public boolean isDeleted() {
        return isDeleted;
    }
}