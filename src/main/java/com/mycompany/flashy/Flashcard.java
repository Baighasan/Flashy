/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flashy;

/**
 *
 * @author baigh
 */
public class Flashcard {
    private String flashCardCategory;
    private String question;
    private String answer;
    private boolean status = true;

    public Flashcard(String flashCardCategory, String question, String answer) {
        setFlashCardCategory(flashCardCategory);
        setQuestion(question);
        setAnswer(answer);
    }
    
    public void setFlashCardCategory(String flashCardCategory) {
        this.flashCardCategory = flashCardCategory;
    }

    public String getFlashCardCategory() {
        return flashCardCategory;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public boolean getStatus() {
        return status;
    }
}
