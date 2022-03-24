package com.javarush.cryptoanalyser;

import java.util.*;
import static com.javarush.cryptoanalyser.OtherFunctional.*;

public class BruteForce {

    private BruteForce() {}

    public static void bruteForceMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(FILE_LOCATION);
        String path = scanner.nextLine();

        String content = readString(path);

        if (content != null) {
            writeString(path, bruteForce(content));
        }

        System.out.println(PROGRAM_COMPLETED);
    }

    public static String bruteForce(String cryptoText) {
        int key = 0;
        int maxWords = 0;
        int maxNumberOfSpaces = 0;
        String firstOption = null;
        String secondOption = null;
        String completeFailure = "Sorry, but we can't decoding this text";

/*      Так не работает, проблема с путями к файлу. После сборки исполняемого jar мой файл words находит
        только по абсолютному пути(такое решение будет работать только под Windows)

        String textFromFile = readString("src/java/com/javarush/cryptoanalyser/words.txt");
        String[] arrayOfWords = textFromFile.split(",");
        Set<String> words = new HashSet<>(List.of(arrayOfWords));
*/

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
            String decodingText = Decoding.decode(cryptoText, key);
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

        if (secondOption == null) {
            secondOption = firstOption;
        }

        return Objects.equals(firstOption, secondOption) ? firstOption : completeFailure;
    }
}
