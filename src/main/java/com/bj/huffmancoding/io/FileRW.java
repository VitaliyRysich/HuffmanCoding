package com.bj.huffmancoding.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileRW {

    public static Map<Character, Double> getFrequencyMap() {
        String path = "src/main/resources/frequencies.txt";
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map<Character, Double> frequencyMap = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line[] = scanner.nextLine().split("-");
            char c = line[0].equals("\\n") ? '\n': line[0].charAt(0);
            double f = Double.valueOf(line[1]);
            frequencyMap.put(c,f);
        }
        return frequencyMap;
    }

    public static String getFileContent(String path) throws IOException {
        String s = Files.readString(Paths.get(path), StandardCharsets.US_ASCII);
        String s1 = s.replaceAll("\r", "");
        return s1;
    }

    public static void writeContentToFile(String path, String content) throws IOException {
        Files.write( Paths.get(path), content.getBytes());
    }
}
