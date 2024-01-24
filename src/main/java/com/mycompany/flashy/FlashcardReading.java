/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flashy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlashcardReading {
// List to store all flashcard categories
    private List<FlashcardCategory> allCategories = new ArrayList<>();
 // Method to load flashcard categories
    public void loadCategories() {
        // Define the path to the 'Flashcards' folder
        String flashcardsFolderPath = "Flashcards";
        File flashcardsFolder = new File(flashcardsFolderPath);

        // Check if the 'Flashcards' folder exists and is a directory
        if (flashcardsFolder.exists() && flashcardsFolder.isDirectory()) {
            // Create a map to store flashcard categories
            Map<String, FlashcardCategory> categoryMap = new HashMap<>();

            // List all subdirectories within 'Flashcards' folder
            File[] categoryFolders = flashcardsFolder.listFiles(File::isDirectory);
            if (categoryFolders != null) {
                // Iterate through each category folder
                for (File categoryFolder : categoryFolders) {
                    String categoryName = categoryFolder.getName();
                    FlashcardCategory flashcardCategory = categoryMap.get(categoryName);
                    if (flashcardCategory == null) {
                        // Create a new flashcard category if it doesn't exist
                        flashcardCategory = new FlashcardCategory(categoryName);
                        categoryMap.put(categoryName, flashcardCategory);
                    }

                    // List all subdirectories within each category folder
                    File[] topicFolders = categoryFolder.listFiles(File::isDirectory);
                    if (topicFolders != null) {
                        // Iterate through each topic folder within the category
                        for (File topicFolder : topicFolders) {
                            File flashcardsFile = new File(topicFolder, "flashcards.json");

                            // Check if 'flashcards.json' file exists
                            if (flashcardsFile.exists() && flashcardsFile.isFile()) {
                                // Create a flashcard topic for this folder
                                FlashcardTopic flashcardTopic = new FlashcardTopic(topicFolder.getName());
                                
                                // Load flashcards from the JSON file
                                createFlashcardsFromJsonFile(categoryName, flashcardTopic, flashcardsFile);
                                
                                // Add the flashcard topic to the category
                                flashcardCategory.addFlashCardTopic(flashcardTopic);
                            }
                        }
                    }

                    // Add the category to the list of all categories
                    allCategories.add(flashcardCategory);
                }
            }
        }
    }

    // Main method to load categories 
    public static void main(String[] args) {
        FlashcardReading flashcardReading = new FlashcardReading();
        flashcardReading.loadCategories();

    }

    // Method to return all loaded categories   
    public List<FlashcardCategory> returnCategories() {
        return allCategories;
    }
     // Method to create flashcards from a JSON file
    private static void createFlashcardsFromJsonFile(String category, FlashcardTopic flashcardTopic, File jsonFile) {
         ObjectMapper objectMapper = new ObjectMapper();

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            // Parse the JSON content into a JSON node
            JsonNode jsonNode = objectMapper.readTree(jsonContent.toString());
            if (jsonNode.isArray()) {
                // If it's an array, cast it to an ArrayNode
                ArrayNode arrayNode = (ArrayNode) jsonNode;
                // Process the flashcard nodes within the JSON array
                processFlashcardNode(category, flashcardTopic, arrayNode, 0); // Start recursion from the first node
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    // Method to save a category along with its flashcards to a JSON file
    public void saveCategory(FlashcardCategory category) {
        String categoryName = category.getFlashcardCategory();
        for (FlashcardTopic topic : category.getFlashcardTopicList()) {
            String topicName = topic.getTopicName();
            List<Flashcard> flashcards = topic.getFlashcardList();

            // Define the JSON file path for the current topic
            String jsonFilePath = "Flashcards\\" + categoryName + "\\" + topicName + "\\flashcards.json";
            File jsonFile = new File(jsonFilePath);
            
            // Save flashcards to the JSON file
            saveFlashcardsToJsonFile(flashcards, jsonFile);
        }
    }
    // Method to save flashcards to a JSON file
    private void saveFlashcardsToJsonFile(List<Flashcard> flashcards, File jsonFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Create an array node to hold the flashcards
            ArrayNode flashcardArray = objectMapper.createArrayNode();

            // Iterate through each flashcard and convert it to a JSON object
            for (Flashcard flashcard : flashcards) {
                ObjectNode flashcardNode = JsonNodeFactory.instance.objectNode();
                flashcardNode.put("flashCardCategory", flashcard.getFlashCardCategory());
                flashcardNode.put("question", flashcard.getQuestion());
                flashcardNode.put("answer", flashcard.getAnswer());
                flashcardNode.put("topic", flashcard.getTopic());
                flashcardNode.put("status", flashcard.getStatus());

                // Add the flashcard object to the array node
                flashcardArray.add(flashcardNode);
            }

            // Overwrite the existing file content with the new flashcard array
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, flashcardArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method to delete a flashcard by category, topic, and question
    public boolean deleteFlashcard(String categoryName, String topicName, String question) {
        loadCategories();

        for (FlashcardCategory category : allCategories) {
            if (category.getFlashcardCategory().equals(categoryName)) {
                for (FlashcardTopic topic : category.getFlashcardTopicList()) {
                    if (topic.getTopicName().equals(topicName)) {
                        List<Flashcard> flashcards = topic.getFlashcardList();
                        for (int i = 0; i < flashcards.size(); i++) {
                            if (flashcards.get(i).getQuestion().equals(question)) {
                                flashcards.remove(i); // Remove the flashcard from the list
                                saveCategory(category); // Update the JSON file
                                return true; // Return true if deletion is successful
                            }
                        }
                    }
                }
            }
        }
        return false; // Return false if flashcard is not found
    
    }
    // Recursive method to process flashcard nodes in JSON
    private static void processFlashcardNode(String category, FlashcardTopic flashcardTopic, ArrayNode arrayNode, int index) {
       if (index < arrayNode.size()) {
            JsonNode flashcardNode = arrayNode.get(index);
            String question = flashcardNode.path("question").asText(null);
            String answer = flashcardNode.path("answer").asText(null);
            boolean status = flashcardNode.path("status").asBoolean(true); // default to true if not present

            if (question != null && answer != null) { // check if question and answer are present
                Flashcard flashcard = new Flashcard(category, flashcardTopic.getTopicName(), question, answer);
                flashcardTopic.addFlashCard(flashcard);
            }

            processFlashcardNode(category, flashcardTopic, arrayNode, index + 1); // Process the next node
        }
    }
}
