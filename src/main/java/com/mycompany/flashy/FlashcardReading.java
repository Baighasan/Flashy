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

    private List<FlashcardCategory> allCategories = new ArrayList<>();

    public void loadCategories() {
        String flashcardsFolderPath = "Flashcards";
        File flashcardsFolder = new File(flashcardsFolderPath);
        if (flashcardsFolder.exists() && flashcardsFolder.isDirectory()) {
            Map<String, FlashcardCategory> categoryMap = new HashMap<>();

            File[] categoryFolders = flashcardsFolder.listFiles(File::isDirectory);
            if (categoryFolders != null) {
                for (File categoryFolder : categoryFolders) {
                    String categoryName = categoryFolder.getName();
                    FlashcardCategory flashcardCategory = categoryMap.get(categoryName);
                    if (flashcardCategory == null) {
                        flashcardCategory = new FlashcardCategory(categoryName);
                        categoryMap.put(categoryName, flashcardCategory);
                    }

                    File[] topicFolders = categoryFolder.listFiles(File::isDirectory);
                    if (topicFolders != null) {
                        for (File topicFolder : topicFolders) {
                            File flashcardsFile = new File(topicFolder, "flashcards.json");

                            if (flashcardsFile.exists() && flashcardsFile.isFile()) {
                                FlashcardTopic flashcardTopic = new FlashcardTopic(topicFolder.getName());
                                createFlashcardsFromJsonFile(categoryName, flashcardTopic, flashcardsFile);
                                flashcardCategory.addFlashCardTopic(flashcardTopic);
                            }
                        }
                    }

                    allCategories.add(flashcardCategory);
                }
            }
        }
    }

    public static void main(String[] args) {
        FlashcardReading flashcardReading = new FlashcardReading();
        flashcardReading.loadCategories();
        flashcardReading.printCategories();
    }

    public List<FlashcardCategory> returnCategories() {
        return allCategories;
    }

    public void printCategories() {
        for (FlashcardCategory category : allCategories) {
            System.out.println(category.getFlashcardCategory());
        }
    }

    private static void createFlashcardsFromJsonFile(String category, FlashcardTopic flashcardTopic, File jsonFile) {
        ObjectMapper objectMapper = new ObjectMapper();

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            JsonNode jsonNode = objectMapper.readTree(jsonContent.toString());
            if (jsonNode.isArray()) {
                ArrayNode arrayNode = (ArrayNode) jsonNode;
                for (JsonNode flashcardNode : arrayNode) {
                    String question = flashcardNode.path("question").asText(null);
                    String answer = flashcardNode.path("answer").asText(null);
                    boolean status = flashcardNode.path("status").asBoolean(true); // default to true if not present

                    if (question != null && answer != null) { // check if question and answer are present
                        Flashcard flashcard = new Flashcard(category, flashcardTopic.getTopicName(), question, answer);
                        flashcardTopic.addFlashCard(flashcard);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCategory(FlashcardCategory category) {
        String categoryName = category.getFlashcardCategory();
        for (FlashcardTopic topic : category.getFlashcardTopicList()) {
            String topicName = topic.getTopicName();
            List<Flashcard> flashcards = topic.getFlashcardList();

            // Define the JSON file path for the current topic
            String jsonFilePath = "Flashy\\Flashcards\\" + categoryName + "\\" + topicName + "\\flashcards.json";
            File jsonFile = new File(jsonFilePath);
            saveFlashcardsToJsonFile(flashcards, jsonFile);
        }
    }

    private void saveFlashcardsToJsonFile(List<Flashcard> flashcards, File jsonFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ArrayNode flashcardArray = objectMapper.createArrayNode();

            for (Flashcard flashcard : flashcards) {
                ObjectNode flashcardNode = JsonNodeFactory.instance.objectNode();
                flashcardNode.put("flashCardCategory", flashcard.getFlashCardCategory());
                flashcardNode.put("question", flashcard.getQuestion());
                flashcardNode.put("answer", flashcard.getAnswer());
                flashcardNode.put("topic", flashcard.getTopic());
                flashcardNode.put("status", flashcard.getStatus());

                flashcardArray.add(flashcardNode);
            }

            // Overwrite the existing file content with the new flashcard array
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, flashcardArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteFlashcard(String categoryName, String topicName, String question) {
        loadCategories();
        System.out.println(categoryName);
        System.out.println(allCategories);
        for (FlashcardCategory category : allCategories) {
            System.out.println(category.getFlashcardCategory());
            if (category.getFlashcardCategory().equals(categoryName)) {
                System.out.println(category);
                for (FlashcardTopic topic : category.getFlashcardTopicList()) {
                    if (topic.getTopicName().equals(topicName)) {
                        List<Flashcard> flashcards = topic.getFlashcardList();
                        for (int i = 0; i < flashcards.size(); i++) {
                            if (flashcards.get(i).getQuestion().equals(question)) {
                                flashcards.remove(i); // Remove the flashcard from the list
                                saveCategory(category); // Update the JSON file
                                System.out.println("Flashcard deleted: " + question); // Debugging line
                                
                                return true; // Return true if deletion is successful
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Flashcard not found: " + question); // Debugging line
        return false; // Return false if flashcard is not found
    }
}
