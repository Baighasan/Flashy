package com.mycompany.flashy;

import java.io.*;
import java.util.Scanner;


public class Flashy {

    public static void main(String[] args) {
        System.out.println("Flashy!");

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the category
        System.out.print("Enter the category: ");
        String category = scanner.nextLine();

        // Ask the user for the topic
        System.out.print("Enter the topic: ");
        String topic = scanner.nextLine();

        // Ask the user for the question
        System.out.print("Enter the question: ");
        String question = scanner.nextLine();

        // Ask the user for the answer
        System.out.print("Enter the answer: ");
        String answer = scanner.nextLine();

        // Create a Flashcard object with the user input
        Flashcard flashcard = new Flashcard(category, topic, question, answer);

        // Close the scanner
        scanner.close();

        // Save the flashcard details to a JSON file
        saveFlashcardToJson(flashcard);
    }

    private static void saveFlashcardToJson(Flashcard flashcard) {
    try {
        // Create a directory for the category and topic if they don't exist
        String rootDirectory = "Flashcards";
        String categoryDirectory = rootDirectory + "/" + flashcard.getFlashCardCategory();
        String topicDirectory = categoryDirectory + "/" + flashcard.getTopic();

        File root = new File(rootDirectory);
        File categoryDir = new File(categoryDirectory);
        File topicDir = new File(topicDirectory);

        if (!root.exists()) {
            root.mkdir();
        }
        
        if (!categoryDir.exists()) {
            categoryDir.mkdir();
        }

        if (!topicDir.exists()) {
            topicDir.mkdir();
        }

        // Create a JSON file with the flashcard details
        String filePath = topicDirectory + "/flashcards.json";
        File file = new File(filePath);

        // Create JSON data manually without newlines for compact formatting
        String jsonData = "{" +
                "\"question\": \"" + flashcard.getQuestion().replace("\"", "\\\"") + "\"," +
                "\"answer\": \"" + flashcard.getAnswer().replace("\"", "\\\"") + "\"" +
                "}";

        // If the JSON file already exists, append the new flashcard data
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder existingJsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                existingJsonData.append(line);
            }
            reader.close();

            // Remove trailing square bracket and add a comma
            String existingJsonStr = existingJsonData.toString();
            existingJsonStr = existingJsonStr.substring(0, existingJsonStr.length() - 1);
            existingJsonStr += ",";

            // Append the new flashcard data
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(existingJsonStr + jsonData + "]");
            writer.close();
        } else {
            // If the file doesn't exist, create a new JSON file with compact formatting
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("[" + jsonData + "]");
            writer.close();
        }

        System.out.println("Flashcard saved to: " + filePath);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
