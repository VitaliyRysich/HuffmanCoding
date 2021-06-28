package com.bj.huffmancoding;

import com.bj.huffmancoding.io.FileRW;
import com.bj.huffmancoding.operations.HuffmanCoding;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class HuffmanCodingApplication {

    public static void main(String[] args) throws IOException {

        HuffmanCoding huffmanCoding = new HuffmanCoding();
        //encoding
        String inputText = FileRW.getFileContent("src/main/resources/input/inputText.txt");
        String compressedString = huffmanCoding.getCompressedString(inputText);
        FileRW.writeContentToFile("src/main/resources/output/encodedText.txt", compressedString);

        //decoding
        String inputBytes = FileRW.getFileContent("src/main/resources/input/inputBytes.txt");
        String decodedString = huffmanCoding.extract(inputBytes);
        FileRW.writeContentToFile("src/main/resources//output/decodedBytes.txt", decodedString);

    }

}
