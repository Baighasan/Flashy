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
public class FlashcardCategory {
    private String flashcardCategory;
    ArrayList<FlashcardTopic> flashcardTopicList = new ArrayList<FlashcardTopic>();
    
    public FlashcardCategory(String flashcardCategory) {
        setFlashcardCategory(flashcardCategory);
    }

    public void setFlashcardCategory(String flashcardCategory) {
        this.flashcardCategory = flashcardCategory;
    }

    public String getFlashcardCategory() {
        return flashcardCategory;
    }
    
    public int getTopicCount() {
        int topicCount = flashcardTopicList.size();
        return topicCount;
    }
    
    public void addFlashCardTopic(FlashcardTopic flashcardTopic) {
        flashcardTopicList.add(flashcardTopic);
    }
    
    public void removeFlashcardTopic(FlashcardTopic flashcardTopic) {
        // TODO: Add functionality
    }
    
    public ArrayList<FlashcardTopic> getFlashcardTopicList() {
        return flashcardTopicList;
    }
}
