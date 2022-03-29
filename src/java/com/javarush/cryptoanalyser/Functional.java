package com.javarush.cryptoanalyser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Functional {

    private Functional() {}

    public static final char[] SYMBOLS =
            ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-.,\"\\:!?() ").toCharArray();
    public static final int SYMBOLS_LENGTH = SYMBOLS.length;
    public static final String FILE_LOCATION = "Please enter the file path";
    public static final String PROGRAM_COMPLETED = "This program has been successfully completed";

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

    public static int validKey() {
        int key;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number between 0 and 63");

        key = scanner.nextInt();

        while (key < 0 || key > SYMBOLS_LENGTH) {
            System.out.println("You have entered an invalid key. Enter a number between 0 and 63");
            key = scanner.nextInt();
        }

        return key;
    }
}
