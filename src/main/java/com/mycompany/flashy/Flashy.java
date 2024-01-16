package com.mycompany.flashy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
            String directoryPath = "src/main/java/com/mycompany/flashy/" + flashcard.getFlashCardCategory() + "/" + flashcard.getTopic();
            File directory = new File(directoryPath);
            directory.mkdirs();

            // Create a JSON file with the flashcard details
            String filePath = directoryPath + "/flashcards.json";
            File file = new File(filePath);

            // Create JSON data manually with consistent formatting
            String jsonData = "{\n" +
                    "  \"question\": \"" + flashcard.getQuestion().replace("\"", "\\\"") + "\",\n" +
                    "  \"answer\": \"" + flashcard.getAnswer().replace("\"", "\\\"") + "\"\n" +
                    "}";

            // If the JSON file already exists, append the new flashcard data
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String existingJsonData = "";
                String line;
                while ((line = reader.readLine()) != null) {
                    existingJsonData += line;
                }
                reader.close();

                // Remove trailing square bracket and add a comma
                existingJsonData = existingJsonData.substring(0, existingJsonData.length() - 1);
                existingJsonData += ",\n";

                // Append the new flashcard data
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(existingJsonData + jsonData + "\n]");
                writer.close();
            } else {
                // If the file doesn't exist, create a new JSON file
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("[\n" + jsonData + "\n]");
                writer.close();
            }

            System.out.println("Flashcard saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
