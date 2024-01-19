/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.flashy;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author arpan
 */
public class FlashcardEditing extends javax.swing.JFrame {
    private List<FlashcardCategory> categories;
    /**
     * Creates new form FlashcardEditing
     */
    public FlashcardEditing() {
    FlashcardReading flashcardReading = new FlashcardReading();
        flashcardReading.loadCategories();
        categories = flashcardReading.returnCategories();

        initComponents();
        populateCategoryComboBox();

        // Add an ItemListener to the category combo box
        cboxCategorySelection.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    int selectedIndex = cboxCategorySelection.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        FlashcardCategory selectedCategory = categories.get(selectedIndex);
                        populateTopicComboBox(selectedCategory);
                    }
                }
            }
        });

        // Initial population of topics if categories exist
        if (!categories.isEmpty()) {
            populateTopicComboBox(categories.get(0));
        }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboxCategorySelection = new javax.swing.JComboBox<>();
        cboxTopicSelection = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        flashcardTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        cboxCategorySelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboxTopicSelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSearch.setText("jButton1");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        flashcardTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(flashcardTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(cboxCategorySelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(cboxTopicSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnSearch)
                .addContainerGap(120, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboxCategorySelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxTopicSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String selectedCategoryName = cboxCategorySelection.getSelectedItem().toString();
String selectedTopicName = cboxTopicSelection.getSelectedItem().toString();

FlashcardTopic selectedTopic = findTopicByCategoryAndName(selectedCategoryName, selectedTopicName);
if (selectedTopic != null) {
    ArrayList<Flashcard> flashcards = selectedTopic.getFlashcardList();
    if (flashcards.isEmpty()) {
        // No flashcards available for the selected topic
        JOptionPane.showMessageDialog(this, "No flashcards available for the selected topic.");
    } else {
        // Create a two-dimensional array to store flashcard data
        String[][] data = new String[flashcards.size()][2];

        for (int i = 0; i < flashcards.size(); i++) {
            Flashcard flashcard = flashcards.get(i);
            data[i][0] = flashcard.getQuestion();
            data[i][1] = flashcard.getAnswer();
        }

        // Create a table model and set it to the JTable
        DefaultTableModel tableModel = new DefaultTableModel(data, new String[]{"Question", "Answer"});
        flashcardTable.setModel(tableModel);
    }
} else {
    JOptionPane.showMessageDialog(this, "No topic found or no flashcards available for the selected topic.");
}
    }//GEN-LAST:event_btnSearchActionPerformed

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
            java.util.logging.Logger.getLogger(FlashcardEditing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FlashcardEditing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FlashcardEditing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FlashcardEditing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FlashcardEditing().setVisible(true);
            }
        });
    } private void populateCategoryComboBox() {
        DefaultComboBoxModel<String> categoryModel = new DefaultComboBoxModel<>();
        for (FlashcardCategory category : categories) {
            categoryModel.addElement(category.getFlashcardCategory());
        }
        cboxCategorySelection.setModel(categoryModel);
    }

    private void populateTopicComboBox(FlashcardCategory category) {
        DefaultComboBoxModel<String> topicModel = new DefaultComboBoxModel<>();
        ArrayList<FlashcardTopic> topicList = category.getFlashcardTopicList();
        for (FlashcardTopic topic : topicList) {
            topicModel.addElement(topic.getTopicName());
        }
        cboxTopicSelection.setModel(topicModel);
    } private FlashcardTopic findTopicByCategoryAndName(String categoryName, String topicName) {
    for (FlashcardCategory category : categories) {
        if (category.getFlashcardCategory().equals(categoryName)) {
            for (FlashcardTopic topic : category.getFlashcardTopicList()) {
                if (topic.getTopicName().equals(topicName)) {
                    return topic;
                }
            }
        }
    }
    return null;
}  private void printFlashcards(FlashcardTopic topic) {
    ArrayList<Flashcard> flashcards = topic.getFlashcardList();
    if (flashcards.isEmpty()) {
        System.out.println("No flashcards available for the topic: " + topic.getTopicName());
        return;
    }
    
    for (Flashcard flashcard : flashcards) {
        System.out.println("Question: " + flashcard.getQuestion());
        System.out.println("Answer: " + flashcard.getAnswer());
        System.out.println("---");
    }
} 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cboxCategorySelection;
    private javax.swing.JComboBox<String> cboxTopicSelection;
    private javax.swing.JTable flashcardTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
