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

        String textFromFile = readString("words.txt");
        String[] arrayOfWords = textFromFile.split(",");
        Set<String> words = new HashSet<>(List.of(arrayOfWords));

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
