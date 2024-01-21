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
public class FlashcardCollection {
    ArrayList<FlashcardCategory> flashcardCategoryList = new ArrayList<FlashcardCategory>();
    
    public void addFlashCardCategory(FlashcardCategory flashcardCategory) {
        flashcardCategoryList.add(flashcardCategory);
    }
    
    public void removeFlashCardCategory(FlashcardCategory flashcardCategory) {
        // TODO: Add funcionality
    }
    
    public ArrayList<FlashcardCategory> getFlashcardCollection() {
        return flashcardCategoryList;
    }
}
