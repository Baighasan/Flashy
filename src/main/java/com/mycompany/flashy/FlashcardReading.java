/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flashy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FlashcardReading {
    public static void main(String[] args) {
        String flashcardsFolderPath = "C:\\Users\\arpan\\OneDrive\\Documents\\NetBeansProjects\\Flashy\\Flashcards";

        File flashcardsFolder = new File(flashcardsFolderPath);
        if (flashcardsFolder.exists() && flashcardsFolder.isDirectory()) {
            // List all category folders
            File[] categoryFolders = flashcardsFolder.listFiles(File::isDirectory);

            if (categoryFolders != null) {
                for (File categoryFolder : categoryFolders) {
                    // List all topic folders inside each category
                    File[] topicFolders = categoryFolder.listFiles(File::isDirectory);

                    if (topicFolders != null) {
                        for (File topicFolder : topicFolders) {
                            // Check for flashcards.json file in each topic folder
                            File flashcardsFile = new File(topicFolder, "flashcards.json");

                            if (flashcardsFile.exists() && flashcardsFile.isFile()) {
                                // Print the topic name before reading and creating flashcards
                                System.out.println("Topic: " + topicFolder.getName());

                                // Create a FlashcardTopic object for the current topic
                                FlashcardTopic flashcardTopic = new FlashcardTopic(topicFolder.getName());
                                

                                // Create flashcards and add them to the FlashcardTopic
                                createFlashcardsFromJsonFile(categoryFolder.getName(), flashcardTopic, flashcardsFile);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void createFlashcardsFromJsonFile(String category, FlashcardTopic flashcardTopic, File jsonFile) {
        ObjectMapper objectMapper = new ObjectMapper();

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Replace tab characters with spaces before parsing as JSON
                line = line.replace("\t", " ");

                // Parse each line as JSON
                JsonNode jsonNode = objectMapper.readTree(line);

                if (jsonNode.isArray()) {
                    for (JsonNode flashcardNode : jsonNode) {
                        String question = flashcardNode.get("question").asText();
                        String answer = flashcardNode.get("answer").asText();

                        // Create a flashcard with category, topic, question, and answer
                        Flashcard flashcard = new Flashcard(category, flashcardTopic.getTopicName(), question, answer);
                        // Add the flashcard to the FlashcardTopic
                        flashcardTopic.addFlashCard(flashcard);
                        
                    }
                }
            }
            System.out.println(flashcardTopic.getFlashcardCount());
            System.out.println(); // Add an empty line to separate topics
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

