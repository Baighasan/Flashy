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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlashcardReading {
    private List<FlashcardCategory> allCategories = new ArrayList<>();

    public void loadCategories() {
        String flashcardsFolderPath = "C:\\Users\\arpan\\OneDrive\\Documents\\NetBeansProjects\\Flashy\\Flashcards";
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
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace("\t", " ");
                JsonNode jsonNode = objectMapper.readTree(line);

                if (jsonNode.isArray()) {
                    for (JsonNode flashcardNode : jsonNode) {
                        String question = flashcardNode.get("question").asText();
                        String answer = flashcardNode.get("answer").asText();

                        Flashcard flashcard = new Flashcard(category, flashcardTopic.getTopicName(), question, answer);
                        flashcardTopic.addFlashCard(flashcard);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } public void saveCategory(FlashcardCategory category) {
    String categoryName = category.getFlashcardCategory();
    for (FlashcardTopic topic : category.getFlashcardTopicList()) {
        String topicName = topic.getTopicName();
        List<Flashcard> flashcards = topic.getFlashcardList();

        // Define the JSON file path for the current topic
        String jsonFilePath = "C:\\Users\\arpan\\OneDrive\\Documents\\NetBeansProjects\\Flashy\\Flashcards\\" + categoryName + "\\" + topicName + "\\flashcards.json";
        File jsonFile = new File(jsonFilePath);
        saveFlashcardsToJsonFile(flashcards, jsonFile);
    }
}

private void saveFlashcardsToJsonFile(List<Flashcard> flashcards, File jsonFile) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, flashcards);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
