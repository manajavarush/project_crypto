package com.javarush.cryptoanalyser;

import java.util.Scanner;

public class Menu {

    private Menu() {
    }

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease choose one of the options:");
        System.out.println("1)Encryption\n2)Decryption\n3)Brute Force\n4)Statistic analysis\n5)Exit\n");

        switch (scanner.nextLine()) {

            case "1", "1)", "1.", "one", "first", "Encryption" -> Encoding.encodeMethod();

            case "2", "2)", "2.", "two", "second", "Decryption" -> Decoding.decodeMethod();

            case "3", "3)", "3.", "three", "third", "Brute Force" -> BruteForce.bruteForceMethod();

            case "4", "4)", "4.", "four", "forth", "Statistical Analysis" -> StatisticalAnalysis.statAnMethod();

            case "5", "5)", "5.", "five", "fifth", "exit", "Exit" -> System.exit(0);

            default -> {
                System.out.println("\nYou should choose one option\n");
                showMenu();
            }
        }
    }
}
