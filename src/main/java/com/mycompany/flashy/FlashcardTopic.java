/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flashy;

import java.util.ArrayList;

/**
 *
 * @author baigh
 */
// The FlashcardTopic class represents a topic containing a list of flashcards.
public class FlashcardTopic {
    // Private member variable to store the name of the topic.
    private String topicName;
    
    // ArrayList to store a list of flashcards associated with this topic.
    ArrayList<Flashcard> flashcardList = new ArrayList<Flashcard>();
    
    // Constructor for FlashcardTopic, initializes the topic with a name.
    public FlashcardTopic(String topicName) {
        // Sets the topic name using the setTopicName method.
        setTopicName(topicName);
    }

    // Method to set the name of the topic.
    public void setTopicName(String topicName) {
        // Assigns the provided topicName to the topicName member variable.
        this.topicName = topicName;
    }

    // Method to retrieve the name of the topic.
    public String getTopicName() {
        // Returns the topic name.
        return topicName;
    }

    // Method to retrieve the count of flashcards in this topic.
    public int getFlashcardCount() {
        // Gets the size of the flashcardList and stores it in flashCardCount.
        int flashCardCount = flashcardList.size();
        // Returns the count of flashcards.
        return flashCardCount;
    }
    
    // Method to add a flashcard to this topic.
    public void addFlashCard(Flashcard flashcard) {
        // Adds the provided flashcard to the flashcardList.
        flashcardList.add(flashcard);
    }
    
    // Method to remove a flashcard from this topic.
    public void removeFlashcard(Flashcard flashcard) {
        // TODO: Add functionality to remove the specified flashcard from the flashcardList.
    }
    
    // Method to get the list of flashcards in this topic.
    public ArrayList<Flashcard> getFlashcardList() {
        // Returns the flashcardList.
        return flashcardList;
    }
}