/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flashy;

/**
 * This class represents a flashcard.
 * It contains information about the flashcard's category, topic, question, answer, and status.
 * 
 * @author baigh
 */
public class Flashcard {
    private String flashCardCategory; // Category of the flashcard
    private String question;         // The question on the flashcard
    private String answer;           // The answer to the flashcard question
    private String topic;            // The topic associated with the flashcard
    private boolean status = true;   // Status indicating if the flashcard is active or not (default is true)

    /**
     * Constructor to initialize a Flashcard object with specified attributes.
     * @param flashCardCategory The category of the flashcard.
     * @param topic The topic associated with the flashcard.
     * @param question The question on the flashcard.
     * @param answer The answer to the flashcard question.
     */
    public Flashcard(String flashCardCategory, String topic, String question, String answer) {
        setFlashCardCategory(flashCardCategory); // Initialize the flashcard category
        setQuestion(question);                   // Initialize the flashcard question
        setAnswer(answer);                       // Initialize the flashcard answer
        setFlashCardTopic(topic);                // Initialize the flashcard topic
        setStatus(status);                       // Initialize the flashcard status
    }
    
    /**
     * Setter method to set the category of the flashcard.
     * @param flashCardCategory The category of the flashcard.
     */
    public void setFlashCardCategory(String flashCardCategory) {
        this.flashCardCategory = flashCardCategory; // Set the flashcard category
    } 
    
    /**
     * Setter method to set the topic associated with the flashcard.
     * @param topic The topic associated with the flashcard.
     */
    public void setFlashCardTopic(String topic) {
        this.topic = topic; // Set the flashcard topic
    }

    /**
     * Getter method to retrieve the category of the flashcard.
     * @return The category of the flashcard.
     */
    public String getFlashCardCategory() {
        return flashCardCategory; // Return the flashcard category
    }

    /**
     * Setter method to set the question on the flashcard.
     * @param question The question on the flashcard.
     */
    public void setQuestion(String question) {
        this.question = question; // Set the flashcard question
    }

    /**
     * Getter method to retrieve the question on the flashcard.
     * @return The question on the flashcard.
     */
    public String getQuestion() {
        return question; // Return the flashcard question
    }

    /**
     * Setter method to set the answer to the flashcard question.
     * @param answer The answer to the flashcard question.
     */
    public void setAnswer(String answer) {
        this.answer = answer; // Set the flashcard answer
    }

    /**
     * Getter method to retrieve the answer to the flashcard question.
     * @return The answer to the flashcard question.
     */
    public String getAnswer() {
        return answer; // Return the flashcard answer
    }
    
    /**
     * Getter method to retrieve the topic associated with the flashcard.
     * @return The topic associated with the flashcard.
     */
    public String getTopic() {
        return topic; // Return the flashcard topic
    }

    /**
     * Setter method to set the status of the flashcard (active or not).
     * @param status The status of the flashcard.
     */
    public void setStatus(boolean status) {
        this.status = status; // Set the flashcard status
    }
    
    /**
     * Getter method to retrieve the status of the flashcard (active or not).
     * @return The status of the flashcard.
     */
    public boolean getStatus() {
        return status; // Return the flashcard status
    }
}
