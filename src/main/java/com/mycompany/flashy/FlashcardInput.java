/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.flashy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.*;
import java.nio.file.Paths;
import java.util.Comparator;
import javax.swing.*;

/**
 *
 * @author arpan
 */
public class FlashcardInput extends javax.swing.JFrame {
    private JComboBox<String> categoryComboBox;
    /**
     * Creates new form FlashcardInput
     */
    public FlashcardInput() {
        initComponents();
    updateCategoryComboBox();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        lblMessage = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtTopic = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Front (Question)");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 109, 140, 30));

        btnAdd.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 51, 51));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 420, 87, 33));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Topic: ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 30, -1, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Add Category");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 130, -1));

        btnRemove.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRemove.setText("Remove Category");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 480, 130));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Category:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 480, 130));
        getContentPane().add(lblMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(278, 347, 230, -1));

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        txtTopic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTopicActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(txtTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTopic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Back (Answer)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(624, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(160, 160, 160))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 750, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
       String category = (String) jComboBox1.getSelectedItem();
    String topic = txtTopic.getText();
    String question = jTextArea2.getText();
    String answer = jTextArea1.getText();

    ArrayList<String> errors = new ArrayList<>();

    if (category == null || category.trim().isEmpty()) {
        errors.add("Category is missing.");
    }

    if (topic == null || topic.trim().isEmpty()) {
        errors.add("Topic is missing.");
    } else if (!isValidName(topic)) {
        errors.add("Invalid topic name. Topic names cannot contain characters like \\/:*?\"<>|.");
    }

    if (question == null || question.trim().isEmpty()) {
        errors.add("Question is missing.");
    }

    if (answer == null || answer.trim().isEmpty()) {
        errors.add("Answer is missing.");
    }

    if (!errors.isEmpty()) {
        // Print error messages for missing inputs or inputs with invalid characters
        StringBuilder errorMessage = new StringBuilder("Errors:\n");
        for (String error : errors) {
            errorMessage.append(error).append("\n");
        }
        JOptionPane.showMessageDialog(this, errorMessage.toString(), "Input Error", JOptionPane.ERROR_MESSAGE);
    } else {
        Flashcard flashcard = new Flashcard(category, topic, question, answer);
        saveFlashcardToJson(flashcard);
    }  
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtTopicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTopicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTopicActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String category = JOptionPane.showInputDialog(this, "Enter a category:");

    // Check if the user entered a category and it's not empty
    if (category != null && !category.trim().isEmpty()) {
        // Check for invalid characters in the category name
        if (!isValidName(category)) {
            JOptionPane.showMessageDialog(this, "Invalid category name. Category names cannot contain characters like \\/:*?\"<>|.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } else {
            // Add the category to the jComboBox1
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) jComboBox1.getModel();
            model.addElement(category);
        }
    }  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        JFrame categoryFrame = new JFrame("Categories");
    
    // Create a JList to display the categories
    DefaultListModel<String> listModel = new DefaultListModel<>();
    for (int i = 0; i < jComboBox1.getItemCount(); i++) {
        listModel.addElement(jComboBox1.getItemAt(i).toString());
    }
    JList<String> categoryList = new JList<>(listModel);
    JScrollPane scrollPane = new JScrollPane(categoryList);

    // Create a button to remove the selected category
    JButton removeButton = new JButton("Remove");
    removeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedCategory = categoryList.getSelectedValue();
            if (selectedCategory != null) {
                // Delete the corresponding folder
                deleteCategoryFolder(selectedCategory);
                
                // Remove the selected item from jComboBox1
                jComboBox1.removeItem(selectedCategory);
                
                categoryFrame.dispose(); // Close the category frame
            }
        }
    });

    // Create a panel to hold the components using GridBagLayout
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10); // Add some padding
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    
    // Add the scroll pane to the panel
    panel.add(scrollPane, gbc);

    // Increment the y-coordinate for the remove button
    gbc.gridy++;
    gbc.weighty = 0.0;
    
    // Add the remove button to the panel
    panel.add(removeButton, gbc);

    // Add the panel to the frame
    categoryFrame.add(panel);

    // Set the default close operation to dispose on close
    categoryFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    // Pack and set the frame to be visible
    categoryFrame.pack();
    categoryFrame.setVisible(true);

    }//GEN-LAST:event_btnRemoveActionPerformed

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
            java.util.logging.Logger.getLogger(FlashcardInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FlashcardInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FlashcardInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FlashcardInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FlashcardInput().setVisible(true);
            }
        });
    } private void saveFlashcardToJson(Flashcard flashcard) {
    try {
        // Create a directory for the category and topic if they don't exist
        String rootDirectory = "Flashcards";
        String categoryDirectory = rootDirectory + "/" + flashcard.getFlashCardCategory();
        String topicDirectory = categoryDirectory + "/" + flashcard.getTopic();

        new File(rootDirectory).mkdirs();
        new File(categoryDirectory).mkdirs();
        new File(topicDirectory).mkdirs();

        // JSON file path
        String filePath = topicDirectory + "/flashcards.json";
        File file = new File(filePath);

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode flashcardsArray;

        if (file.exists() && file.length() != 0) {
            // Read existing data and convert to ArrayNode
            flashcardsArray = (ArrayNode) objectMapper.readTree(file);
        } else {
            // Create new ArrayNode
            flashcardsArray = objectMapper.createArrayNode();
        }

        // Create new flashcard node
        ObjectNode flashcardNode = objectMapper.createObjectNode();
        flashcardNode.put("flashCardCategory", flashcard.getFlashCardCategory());
        flashcardNode.put("question", flashcard.getQuestion());
        flashcardNode.put("answer", flashcard.getAnswer());
        flashcardNode.put("topic", flashcard.getTopic());
        flashcardNode.put("status", flashcard.getStatus());

        // Add new flashcard to array
        flashcardsArray.add(flashcardNode);

        // Write array back to file
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, flashcardsArray);

        lblMessage.setText("Flashcard saved to: " + filePath);
    } catch (IOException e) {
        e.printStackTrace();
    }
} private void deleteCategoryFolder(String category) {
    Path directory = Paths.get("C:\\Users\\arpan\\OneDrive\\Documents\\NetBeansProjects\\Flashy\\Flashcards", category);
    try {
        // Delete all contents of the directory recursively and then the directory itself
        if (Files.exists(directory)) { // Only proceed if the directory exists
            Files.walk(directory)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
            
            System.out.println("Category folder deleted successfully.");
        } else {
            System.out.println("Directory does not exist, nothing to delete.");
        }
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("An error occurred while deleting the category folder.");
    }
} private void updateCategoryComboBox() {
    String directoryPath = "Flashcards";
    File directory = new File(directoryPath);

    if (directory.exists() && directory.isDirectory()) {
        File[] categoryFolders = directory.listFiles(File::isDirectory);
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) jComboBox1.getModel();
        model.removeAllElements();

        for (File categoryFolder : categoryFolders) {
            model.addElement(categoryFolder.getName());
        }
    }
} private boolean isValidName(String name) {
    String invalidChars = "\\/:*?\"<>|";
    for (char c : invalidChars.toCharArray()) {
        if (name.indexOf(c) >= 0) {
            return false;
        }
    }
    return true;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JTextField txtTopic;
    // End of variables declaration//GEN-END:variables
}
