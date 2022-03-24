package com.javarush.cryptoanalyser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class OtherFunctional {

    private OtherFunctional() {}

    public static final char[] SYMBOLS =
            ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-.,\"\\:!?() ").toCharArray();
    public static final int S_LENGTH = SYMBOLS.length;
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
}
