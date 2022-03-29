package com.javarush.cryptoanalyser;

import java.util.*;

import static com.javarush.cryptoanalyser.Functional.*;

public class StatisticalAnalysis {

    private StatisticalAnalysis() {}

    public static void statAnMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the original file path");
        String firstPath = scanner.nextLine();
        System.out.println("Please enter the crypto file path");

        String secondPath = scanner.nextLine();
        showStatistics(firstPath, secondPath);

        System.out.println(PROGRAM_COMPLETED);
    }

    public static void showStatistics(String firstPath, String secondPath) {
        Map<Character, Integer> originalMap = new HashMap<>();
        System.out.println("\nStatistical analysis of the original text:");
        statAnalysis(firstPath, originalMap);
        System.out.println("------------------------------------------------------------------\n");
        System.out.println("Statistical analysis of the crypto text:");
        Map<Character, Integer> cryptoMap = new HashMap<>();
        statAnalysis(secondPath, cryptoMap);

        char originalChar = findMostFrequent(originalMap);
        char cryptoChar = findMostFrequent(cryptoMap);
        System.out.println("\nThe most frequent character in the original text = " + originalChar + "space");
        System.out.println("The most frequent character in the crypto text = " + cryptoChar);

        String symbols = String.valueOf(SYMBOLS);
        int key = symbols.indexOf(cryptoChar) + 1;
        System.out.println("You can decode this text with key=" + key + "\n");
    }

    public static void statAnalysis(String path, Map<Character, Integer> map) {
        ArrayList<Character> list = new ArrayList<>();
        String s = readString(path);
        char[] chars = s.toCharArray();
        String alphabet = String.valueOf(SYMBOLS);

        for (char c : chars) {
            int index = alphabet.indexOf(c);

            if (index > -1) {
                list.add(c);
            }
        }

        for (char c : list) {
            int count = Collections.frequency(list, c);
            int index = count * 100 / list.size();
            double minimumFrequency = 0.9;

            if (index > minimumFrequency) {
                map.put(c, index);
            }
        }

        for (var pair : map.entrySet()) {
            System.out.println("Letter: " + pair.getKey() + " = " + pair.getValue() + " percent");
        }
    }

    public static char findMostFrequent(Map<Character, Integer> map) {
        char c = 0;
        int max = 0;

        for (var entry : map.entrySet()) {
            if (max < entry.getValue()) {
                max = entry.getValue();
                c = entry.getKey();
            }
        }
        return c;
    }
}
