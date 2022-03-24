package com.javarush.cryptoanalyser;

import java.util.Scanner;
import static com.javarush.cryptoanalyser.OtherFunctional.*;

public class Decoding {

    private Decoding() {}

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
}
