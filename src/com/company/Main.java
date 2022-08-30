package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        if (args.length != 0) {
            System.out.println("This game has no input options.");
        } else {
            possibleAnswers.addAll(readFileIntoList("src/com/company/word_lists/possible_answers.txt"));
            allowedWords.addAll(readFileIntoList("src/com/company/word_lists/allowed_guesses.txt"));
            allowedWords.addAll(possibleAnswers);

            String answer = generateAnswer();

            Game game = new Game(answer);
            GUI display = new GUI("Wordle!", game);
            display.pack();
            display.setVisible(true);
        }
    }

    public static List<String> readFileIntoList(String file) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static String generateAnswer() {
        Random random = new Random();
        return possibleAnswers.get(random.nextInt(possibleAnswers.size())).toUpperCase();
    }

    static List<String> possibleAnswers = new ArrayList<>();
    static List<String> allowedWords = new ArrayList<>();
}
