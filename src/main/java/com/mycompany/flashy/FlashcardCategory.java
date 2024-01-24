/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flashy;

import java.util.ArrayList;

/**
 * This class represents a category of flashcards.
 * It contains information about the flashcard category and a list of associated topics.
 * Topics are represented by instances of the FlashcardTopic class.
 * 
 * @author baigh
 */
public class FlashcardCategory {
    private String flashcardCategory;                          // Name of the flashcard category
    ArrayList<FlashcardTopic> flashcardTopicList = new ArrayList<FlashcardTopic>(); // List of associated topics
    
    /**
     * Constructor to initialize a FlashcardCategory object with a given category name.
     * @param flashcardCategory The name of the flashcard category.
     */
    public FlashcardCategory(String flashcardCategory) {
        setFlashcardCategory(flashcardCategory); // Initialize the category name
    }

    /**
     * Setter method to set the name of the flashcard category.
     * @param flashcardCategory The name of the flashcard category.
     */
    public void setFlashcardCategory(String flashcardCategory) {
        this.flashcardCategory = flashcardCategory; // Set the category name
    }

    /**
     * Getter method to retrieve the name of the flashcard category.
     * @return The name of the flashcard category.
     */
    public String getFlashcardCategory() {
        return flashcardCategory; // Return the category name
    }
    
    /**
     * Getter method to retrieve the count of topics associated with this category.
     * @return The number of topics in the category.
     */
    public int getTopicCount() {
        int topicCount = flashcardTopicList.size(); // Calculate the number of topics
        return topicCount; // Return the topic count
    }
    
    /**
     * Method to add a FlashcardTopic to the list of associated topics in this category.
     * @param flashcardTopic The FlashcardTopic to be added.
     */
    public void addFlashCardTopic(FlashcardTopic flashcardTopic) {
        flashcardTopicList.add(flashcardTopic); // Add the topic to the list
    }
    
    /**
     * Method to remove a FlashcardTopic from the list of associated topics in this category.
     * TODO: Implement functionality for removing topics (Currently, it is a placeholder).
     * @param flashcardTopic The FlashcardTopic to be removed.
     */
   
    /**
     * Getter method to retrieve the list of associated topics in this category.
     * @return An ArrayList of FlashcardTopic objects representing the associated topics.
     */
    public ArrayList<FlashcardTopic> getFlashcardTopicList() {
        return flashcardTopicList; // Return the list of associated topics
    }
}
