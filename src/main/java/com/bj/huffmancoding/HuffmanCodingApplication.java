package com.bj.huffmancoding;

import com.bj.huffmancoding.io.FileRW;
import com.bj.huffmancoding.operations.HuffmanPreparation;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class HuffmanCodingApplication {

    public static void main(String[] args) throws IOException {

        HuffmanPreparation huffmanPreparation = new HuffmanPreparation();
        //encoding
        String inputText = FileRW.getFileContent("src/main/resources/input/inputText.txt");
        String compressedString = huffmanPreparation.compressString(inputText);
        FileRW.writeContentToFile("src/main/resources/output/encodedText.txt", compressedString);

    }

}
