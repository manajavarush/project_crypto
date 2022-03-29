package com.javarush.cryptoanalyser;

import java.util.Scanner;

import static com.javarush.cryptoanalyser.Functional.*;

public class Decoding {

    private Decoding() {}

    public static void decodeMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(FILE_LOCATION);
        String path = scanner.nextLine();

        int key = validKey();

        String content = readString(path);

        if (content != null) {
            writeString(path, decode(content, key));
        }

        System.out.println(PROGRAM_COMPLETED);
    }

    public static String decode(String text, int key) {
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
                                ? (SYMBOLS[(index - key) % SYMBOLS_LENGTH])
                                : (SYMBOLS[SYMBOLS_LENGTH + index - key]));
            }
        }
        return crypto.toString();
    }
}
