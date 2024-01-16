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
public class FlashcardTopic {
    private String topicName;
    ArrayList<Flashcard> flashcardList = new ArrayList<Flashcard>();
    
    public FlashcardTopic(String topicName) {
        setTopicName(topicName);
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public int getFlashcardCount() {
        int flashCardCount = flashcardList.size();
        return flashCardCount;
    }
    
    public void addFlashCard(Flashcard flashcard) {
        flashcardList.add(flashcard);
    }
    
    public void removeFlashcard(Flashcard flashcard) {
        // TODO add functionality
    }
    
    public ArrayList<Flashcard> getFlashcardList() {
        return flashcardList;
    }
}
