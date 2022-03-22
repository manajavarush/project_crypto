package com.javarush.cryptoanalyser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Main {
    public static final String FILE_LOCATION = "Please enter the file path";
    public static final String PROGRAM_COMPLETED = "This program has been successfully completed";

    private static final char[] SYMBOLS =
            ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-.,\"\\:!?() ").toCharArray();

    private static final int S_LENGTH = SYMBOLS.length;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease choose one of the options:\n");
        System.out.println("1)Encryption\n2)Decryption\n3)Brute Force\n4)Statistic analysis\n5)Exit\n");

        switch (scanner.nextLine()) {

            case "1", "1)", "1.", "one", "first", "Encryption" -> encodeMethod();

            case "2", "2)", "2.", "two", "second", "Decryption" -> decodeMethod();

            case "3", "3)", "3.", "three", "third", "Brute Force" -> bruteForceMethod();

            case "5", "5)", "5.", "five", "fifth", "exit", "Exit" -> System.exit(0);

            default -> {System.out.println("\nYou should choose one option\n");
                main(args);
            }
        }
    }

    public static void writeString(String path, String content) {
        Path fileName = Path.of(path);

        try {
            Files.writeString(fileName, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readString(String path) {
        Path fileName = Path.of(path);
        String actual = null;

        try {
            actual = Files.readString(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actual;
    }


    public static void encodeMethod() {

        Scanner scanner = new Scanner(System.in);
        System.out.println(FILE_LOCATION);
        String path = scanner.nextLine();
        System.out.println("Please enter the key");
        int key = scanner.nextInt();

        String content = readString(path);

        if (content != null) {

            writeString(path, encode(content, key));
        }

        System.out.println(PROGRAM_COMPLETED);
    }

    public static void decodeMethod() {

        Scanner scanner = new Scanner(System.in);
        System.out.println(FILE_LOCATION);
        String path = scanner.nextLine();
        System.out.println("Please enter the key");
        int key = scanner.nextInt();

        String content = readString(path);

        if (content != null) {

            writeString(path, decode(content, key));
        }

        System.out.println(PROGRAM_COMPLETED);
    }


    public static void bruteForceMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(FILE_LOCATION);
        String path = scanner.nextLine();

        String content = readString(path);
        writeString(path, bruteForce(content));

        System.out.println(PROGRAM_COMPLETED);
    }


    public static String encode(String text, int key) {
        key = Math.abs(key) % S_LENGTH;
        String characters = String.valueOf(SYMBOLS);
        char[] original = text.toCharArray();
        StringBuilder crypto = new StringBuilder();

        for (char c : original) {
            int index = characters.indexOf(c);

            if (c == '\n') {
                crypto.append(c);
            }

            if (index > -1) {
                crypto.append(SYMBOLS[(index + key) % S_LENGTH]);
            }
        }
        return crypto.toString();
    }

    public static String decode(String text, int key) {
        key = Math.abs(key) % S_LENGTH;
        String characters = String.valueOf(SYMBOLS);
        char[] original = text.toCharArray();
        StringBuilder crypto = new StringBuilder();

        for (char c : original) {

            if (c == '\n') {
                crypto.append(c);
            }
            int index = characters.indexOf(c);

            if (index > -1) {
                crypto.append(
                        (index - key) > -1
                                ? (SYMBOLS[(index - key) % S_LENGTH])
                                : (SYMBOLS[S_LENGTH + index - key]));
            }
        }
        return crypto.toString();
    }

    public static String bruteForce(String cryptoText) {
        int key = 0;
        int maxWords = 0;
        int maxNumberOfSpaces = 0;
        String firstOption = null;
        String secondOption = null;
        String completeFailure = "Sorry, but we can't decoding this text";

        Set<String> words = new HashSet<>(Arrays.asList("with", "the", "be", "of", "and", "a","in", "to", "have", "it", "for","that", "you",
                "he", "on", "than","do", "at", "by", "not", "this","but", "from", "they", "his", "she","or", "which",
                "as", "we", "an","say", "will", "would", "can", "if","their", "go", "want", "there", "what","all", "too",
                "get", "her", "make", "who","out", "up", "see", "no", "time", "them", "some", "could", "so", "him", "large",
                "year", "into", "its", "then", "think", "my", "come", "more", "about", "now", "know", "last", "your",
                "me", "other", "give", "just", "should", "these", "those", "people", "two", "one", "three", "also",
                "either", "etc", "well", "only", "any", "new", "very", "when", "why", "may", "way", "look", "like", "use",
                "such", "how", "because", "good", "find", "man", "woman", "our", "you know", "must", "I", "us", "day",
                "off", "thing", "body", "still", "back", "five", "six", "seven", "eight", "nine", "ten", "life", "case",
                "great", "own", "self", "world", "feel", "again", "yes", "number", "web", "part", "Internet", "week",
                "bring", "begin", "always", "member", "house", "country", "city", "town", "person", "course", "really",
                "under", "shall", "leave", "most", "proud", "system", "keep", "fit", "mean", "group", "problem", "follow",
                "set", "every", "end", "state", "right", "left", "social", "mind", "while", "during", "whole", "become"));

        while (key < S_LENGTH - 1) {
            String decodingText = decode(cryptoText, key);
            String[] decodingArray = decodingText.split(" ");
            int numberOfSpaces = decodingArray.length;

            if (maxNumberOfSpaces < numberOfSpaces) {
                maxNumberOfSpaces = numberOfSpaces;
                firstOption = decodingText;
            }

            int numWords = 0;

            for (String word : words) {

                if (decodingText.contains(word)) {
                    numWords++;

                    if (maxWords < numWords) {
                        maxWords = numWords;
                        secondOption = decodingText;
                    }
                }
            }
            key++;
        }
        return Objects.equals(firstOption, secondOption) ? firstOption : completeFailure;
    }
}
