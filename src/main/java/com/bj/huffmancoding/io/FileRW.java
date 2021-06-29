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

    public static String getFileContent(String path) throws IOException {
        String s = Files.readString(Paths.get(path), StandardCharsets.US_ASCII);
        String s1 = s.replaceAll("\r", "");
        return s1;
    }

    public static void writeContentToFile(String path, String content) throws IOException {
        Files.write( Paths.get(path), content.getBytes());
    }
}
