package com.javarush.cryptoanalyser;

import java.util.Scanner;

import static com.javarush.cryptoanalyser.Functional.*;

public class Encoding {

    private Encoding() {}

    public static void encodeMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(FILE_LOCATION);
        String path = scanner.nextLine();

        int key = validKey();
        String content = readString(path);

        if (content != null) {
            writeString(path, encode(content, key));
        }

        System.out.println(PROGRAM_COMPLETED);
    }

    public static String encode(String text, int key) {
        String characters = String.valueOf(SYMBOLS);
        char[] original = text.toCharArray();
        StringBuilder crypto = new StringBuilder();

        for (char c : original) {
            int index = characters.indexOf(c);

            if (c == '\n') {
                crypto.append(c);
            }

            if (index > -1) {
                crypto.append(SYMBOLS[(index + key) % SYMBOLS_LENGTH]);
            }
        }
        return crypto.toString();
    }
}
