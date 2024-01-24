/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.flashy;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author arpan
 */
public class FlashcardEditing extends javax.swing.JFrame {

    // A list to hold categories of flashcards
    private List<FlashcardCategory> categories;
    // A table model to dynamically control the data in the JTable
    private DefaultTableModel tableModel;

    /**
     * Creates new form FlashcardEditing
     */
    public FlashcardEditing() {

        // Creates an instance of FlashcardReading to load and return categories
        FlashcardReading flashcardReading = new FlashcardReading();
        flashcardReading.loadCategories();
        categories = flashcardReading.returnCategories();

        // Initialize the GUI components
        initComponents();

        // Populate the category combo box with categories
        populateCategoryComboBox();

        // Add an ItemListener to the category combo box to handle category selection events
        cboxCategorySelection.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                // If a category is selected
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    // Get the selected category index
                    int selectedIndex = cboxCategorySelection.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        // Get the selected category and populate the topic combo box based on the selected category
                        FlashcardCategory selectedCategory = categories.get(selectedIndex);
                        populateTopicComboBox(selectedCategory);
                    }
                }
            }
        });

        // If there are categories available, populate topics based on the first category
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

        sbar1 = new javax.swing.JScrollPane();
        tblFlashcardQuestions = new javax.swing.JTable();
        pnlFlashcardEditTop = new javax.swing.JPanel();
        cboxTopicSelection = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        cboxCategorySelection = new javax.swing.JComboBox<>();
        lblTopic = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        pnlFlashcardEditBottom = new javax.swing.JPanel();
        pnlFlashcardEditMiddle = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblFlashcardQuestions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        sbar1.setViewportView(tblFlashcardQuestions);

        getContentPane().add(sbar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 500, 360));

        pnlFlashcardEditTop.setBackground(new java.awt.Color(204, 204, 204));
        pnlFlashcardEditTop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cboxTopicSelection.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N

        btnSearch.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        cboxCategorySelection.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N

        lblTopic.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTopic.setForeground(new java.awt.Color(102, 102, 102));
        lblTopic.setText("Topic:");

        lblCategory.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCategory.setForeground(new java.awt.Color(102, 102, 102));
        lblCategory.setText("Category:");

        javax.swing.GroupLayout pnlFlashcardEditTopLayout = new javax.swing.GroupLayout(pnlFlashcardEditTop);
        pnlFlashcardEditTop.setLayout(pnlFlashcardEditTopLayout);
        pnlFlashcardEditTopLayout.setHorizontalGroup(
            pnlFlashcardEditTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFlashcardEditTopLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(pnlFlashcardEditTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxCategorySelection, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlFlashcardEditTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFlashcardEditTopLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(cboxTopicSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(pnlFlashcardEditTopLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(lblTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlFlashcardEditTopLayout.setVerticalGroup(
            pnlFlashcardEditTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFlashcardEditTopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFlashcardEditTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategory)
                    .addComponent(lblTopic))
                .addGap(4, 4, 4)
                .addGroup(pnlFlashcardEditTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboxTopicSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxCategorySelection, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(pnlFlashcardEditTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 80));

        pnlFlashcardEditBottom.setBackground(new java.awt.Color(255, 153, 153));
        pnlFlashcardEditBottom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        javax.swing.GroupLayout pnlFlashcardEditBottomLayout = new javax.swing.GroupLayout(pnlFlashcardEditBottom);
        pnlFlashcardEditBottom.setLayout(pnlFlashcardEditBottomLayout);
        pnlFlashcardEditBottomLayout.setHorizontalGroup(
            pnlFlashcardEditBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
        );
        pnlFlashcardEditBottomLayout.setVerticalGroup(
            pnlFlashcardEditBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 258, Short.MAX_VALUE)
        );

        getContentPane().add(pnlFlashcardEditBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 630, 260));

        pnlFlashcardEditMiddle.setBackground(new java.awt.Color(204, 204, 255));
        pnlFlashcardEditMiddle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));

        javax.swing.GroupLayout pnlFlashcardEditMiddleLayout = new javax.swing.GroupLayout(pnlFlashcardEditMiddle);
        pnlFlashcardEditMiddle.setLayout(pnlFlashcardEditMiddleLayout);
        pnlFlashcardEditMiddleLayout.setHorizontalGroup(
            pnlFlashcardEditMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
        );
        pnlFlashcardEditMiddleLayout.setVerticalGroup(
            pnlFlashcardEditMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );

        getContentPane().add(pnlFlashcardEditMiddle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 630, 190));

        pack();
    }// </editor-fold>//GEN-END:initComponents
     /**
     * Event handler for the Search button. This method is triggered when the
     * Search button is clicked. It is responsible for finding and displaying
     * flashcards based on the selected category and topic.
     */
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        // Inititalize variables to store category and topic name;
        String selectedCategoryName = cboxCategorySelection.getSelectedItem().toString();
        String selectedTopicName = cboxTopicSelection.getSelectedItem().toString();
        // Create topic object that will store all flashcards belonging to the topic
        FlashcardTopic selectedTopic = findTopicByCategoryAndName(selectedCategoryName, selectedTopicName);
        if (selectedTopic != null) { //Create array list of all flashcard objects in the topic object
            ArrayList<Flashcard> flashcards = selectedTopic.getFlashcardList();
            if (flashcards.isEmpty()) {
                // No flashcards available for the selected topic
                JOptionPane.showMessageDialog(this, "No flashcards available for the selected topic.");
            } else {
                // Create a two-dimensional array to store flashcard data, including an extra column for the edit buttons
                String[][] data = new String[flashcards.size()][3];

                for (int i = 0; i < flashcards.size(); i++) { //Iterate through all flashcards and display them for the user to see
                    Flashcard flashcard = flashcards.get(i);
                    data[i][0] = flashcard.getQuestion();
                    data[i][1] = flashcard.getAnswer();
                    data[i][2] = "Edit";  // Placeholder for the button
                }

                // Create a table model and set it to the JTable
                DefaultTableModel tableModel = new DefaultTableModel(data, new String[]{"Question", "Answer", "Edit"}) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return column == 2;  // Only the third column (Edit buttons) is editable
                    }
                };
                tblFlashcardQuestions.setModel(tableModel);
                tblFlashcardQuestions.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
                tblFlashcardQuestions.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox(), tblFlashcardQuestions));
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
    }
// Method to populate the category combo box with categories.

    private void populateCategoryComboBox() {
        DefaultComboBoxModel<String> categoryModel = new DefaultComboBoxModel<>();

        // Loop through each category in the categories list.
        for (FlashcardCategory category : categories) {
            // Add the category's name to the combo box model.
            categoryModel.addElement(category.getFlashcardCategory());
        }

        // Set the combo box's model to the newly created model filled with category names.
        cboxCategorySelection.setModel(categoryModel);
    }
// Method to populate the topic combo box with topics based on the selected category.

    private void populateTopicComboBox(FlashcardCategory category) {
        DefaultComboBoxModel<String> topicModel = new DefaultComboBoxModel<>();

        // Retrieve the list of topics from the selected category.
        ArrayList<FlashcardTopic> topicList = category.getFlashcardTopicList();

        // Loop through each topic in the topic list.
        for (FlashcardTopic topic : topicList) {
            // Add the topic's name to the combo box model.
            topicModel.addElement(topic.getTopicName());
        }

        // Set the combo box's model to the newly created model filled with topic names.
        cboxTopicSelection.setModel(topicModel);
    }

    // Method to find a specific topic by its category name and topic name.
    private FlashcardTopic findTopicByCategoryAndName(String categoryName, String topicName) {
        // Loop through each category in the categories list.
        for (FlashcardCategory category : categories) {
            // Check if the current category's name matches the provided category name.
            if (category.getFlashcardCategory().equals(categoryName)) {
                // Loop through each topic in the current category's topic list.
                for (FlashcardTopic topic : category.getFlashcardTopicList()) {
                    // Check if the current topic's name matches the provided topic name.
                    if (topic.getTopicName().equals(topicName)) {
                        // Return the topic if it matches the provided names.
                        return topic;
                    }
                }
            }
        }
        // Return null if no matching topic is found.
        return null;
    }

    // Method to print out the flashcards of a specific topic.
    private void printFlashcards(FlashcardTopic topic) {
        // Retrieve the list of flashcards for the provided topic.
        ArrayList<Flashcard> flashcards = topic.getFlashcardList();

        // Check if the flashcard list is empty.
        if (flashcards.isEmpty()) {
            // Print a message indicating there are no flashcards for the topic.
            System.out.println("No flashcards available for the topic: " + topic.getTopicName());
            return;
        }

        // Loop through each flashcard in the flashcard list.
        for (Flashcard flashcard : flashcards) {
            // Print the question of the flashcard.
            System.out.println("Question: " + flashcard.getQuestion());
            // Print the answer of the flashcard.
            System.out.println("Answer: " + flashcard.getAnswer());
            // Print a separator line.
            System.out.println("---");
        }
    }

    // Method to update the flashcard table with the flashcards of a specific topic.
    private void updateFlashcardTable(FlashcardCategory category, FlashcardTopic topic) {
        // Retrieve the list of flashcards for the provided topic.
        ArrayList<Flashcard> flashcards = topic.getFlashcardList();

        // Create a 2D array to hold the data for the table (questions, answers, and edit buttons).
        String[][] data = new String[flashcards.size()][3];

        // Loop through each flashcard.
        for (int i = 0; i < flashcards.size(); i++) {
            // Get the current flashcard from the list.
            Flashcard flashcard = flashcards.get(i);

            // Set the question, answer, and a placeholder for the edit button in the data array.
            data[i][0] = flashcard.getQuestion();
            data[i][1] = flashcard.getAnswer();
            data[i][2] = "Edit";
        }

        // Retrieve the table model from the flashcard questions table.
        DefaultTableModel tableModel = (DefaultTableModel) tblFlashcardQuestions.getModel();

        // Set the data vector of the table model to the newly created data array and define the column names.
        tableModel.setDataVector(data, new String[]{"Question", "Answer", "Edit"});

        // Set a custom renderer for the 'Edit' button column to display buttons.
        tblFlashcardQuestions.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());

        // Set a custom editor for the 'Edit' button column to handle button clicks.
        tblFlashcardQuestions.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox(), tblFlashcardQuestions));

        // Notify the table model that the data has changed so the table updates.
        tableModel.fireTableDataChanged();
    }
    // ButtonRenderer class extends JButton and implements TableCellRenderer to customize button rendering in a table cell.

    class ButtonRenderer extends JButton implements TableCellRenderer {

        // Constructor of the ButtonRenderer class.
        public ButtonRenderer() {
            // Makes the button opaque to render its background.
            setOpaque(true);
        }

        // This method is overridden to customize the rendering of the cell that contains the button.
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            // Sets the text of the button based on the value of the object. If the value is null, sets text to empty.
            setText((value == null) ? "" : value.toString());

            // Returns the component (itself in this case, as it is a JButton).
            return this;
        }
    }
// Method to display a dialog for editing flashcards.

    private void showEditDialog(int row) {
        // Check if the row index is -1, which indicates no row is selected.
        if (row == -1) {
            // Show a message dialog indicating no row is selected.
            JOptionPane.showMessageDialog(this, "No row selected.");
            return;
        }

        // Retrieve the selected category and topic based on the selected indices in combo boxes.
        FlashcardCategory selectedCategory = categories.get(cboxCategorySelection.getSelectedIndex());
        FlashcardTopic selectedTopic = selectedCategory.getFlashcardTopicList().get(cboxTopicSelection.getSelectedIndex());
        // Retrieve the flashcard to be edited based on the row index.
        Flashcard flashcard = selectedTopic.getFlashcardList().get(row);

        // Create and display the edit dialog, passing the current question and answer of the flashcard.
        FlashcardEditDialog dialog = new FlashcardEditDialog(this, flashcard.getQuestion(), flashcard.getAnswer());
        dialog.setVisible(true);

        // After the dialog is closed, check if the flashcard was marked for deletion.
        if (dialog.isDeleted()) {
            // Retrieve necessary information for deleting the flashcard.
            String categoryName = selectedCategory.getFlashcardCategory();
            String topicName = selectedTopic.getTopicName();
            String question = flashcard.getQuestion();

            // Create a FlashcardReading object to perform deletion.
            FlashcardReading flashcardReading = new FlashcardReading();
            flashcardReading.loadCategories(); // Load categories if not already loaded.
            // Perform the deletion operation.
            boolean isDeleted = flashcardReading.deleteFlashcard(categoryName, topicName, question);

            // Check if the deletion was successful.
            if (isDeleted) {
                // Remove the flashcard from the list and update the table.
                selectedTopic.getFlashcardList().remove(row);
                updateFlashcardTable(selectedCategory, selectedTopic);
            } else {
                // Show an error message if deletion failed.
                JOptionPane.showMessageDialog(this, "Error deleting the flashcard.");
            }
        } else if (dialog.isSaved()) {
            // If the dialog was saved, retrieve the updated question and answer.
            String newQuestion = dialog.getQuestion();
            String newAnswer = dialog.getAnswer();

            // Check if the new question or answer is not empty.
            if (newQuestion != null && newAnswer != null && (!newQuestion.isEmpty() || !newAnswer.isEmpty())) {
                // Update the flashcard with the new question and answer.
                flashcard.setQuestion(newQuestion);
                flashcard.setAnswer(newAnswer);

                // Save the updated category.
                FlashcardReading flashcardReading = new FlashcardReading();
                flashcardReading.saveCategory(selectedCategory);

                // Update the table to show the updated flashcards.
                updateFlashcardTable(selectedCategory, selectedTopic);
            }
        }
    }

    // ButtonEditor class extends DefaultCellEditor to customize button behavior in a table cell.
class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable table; // Reference to the table the button is in.

    // Constructor accepts a JCheckBox (used by the superclass) and a JTable.
        public ButtonEditor(JCheckBox checkBox, JTable table) { // Update the constructor to accept JTable
            super(checkBox);
        this.table = table; // Initialize the table reference.
        
        button = new JButton(); // Initialize the button.
        button.setOpaque(true); // Makes the button opaque.
        // Add an action listener to handle button clicks.
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the selected row index.
                int selectedRow = table.getSelectedRow();
                // Check if a row is selected.
                if (selectedRow != -1) {
                    // Stop cell editing.
                    fireEditingStopped();
                    // Call the edit dialog with the selected row.
                    showEditDialog(selectedRow);
                } else {
                    // If no row is selected, show an error message.
                    JOptionPane.showMessageDialog(button, "Please select a row to edit.");
                }
            }
        });
    }

      // This method is overridden to customize the component that should be used as the editor.
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        // Set the button's foreground and background colors based on selection status.
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        // Set the button's text.
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        // Return the button as the editor component.
        return button;
    }

       // This method returns the value contained in the editor.
    public Object getCellEditorValue() {
        isPushed = false; // Reset the isPushed flag.
        return label; // Return the label (button's text).
    }

    // This method is called when editing is stopped.
    public boolean stopCellEditing() {
        isPushed = false; // Reset the isPushed flag.
        return super.stopCellEditing(); // Call the superclass method.
    }

    // This method notifies listeners that editing has stopped.
    protected void fireEditingStopped() {
        super.fireEditingStopped(); // Call the superclass method.
    }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cboxCategorySelection;
    private javax.swing.JComboBox<String> cboxTopicSelection;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblTopic;
    private javax.swing.JPanel pnlFlashcardEditBottom;
    private javax.swing.JPanel pnlFlashcardEditMiddle;
    private javax.swing.JPanel pnlFlashcardEditTop;
    private javax.swing.JScrollPane sbar1;
    private javax.swing.JTable tblFlashcardQuestions;
    // End of variables declaration//GEN-END:variables
}
