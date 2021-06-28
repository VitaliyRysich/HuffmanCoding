package com.bj.huffmancoding.operations;


import java.util.Map;
public class HuffmanCoding {

    private Map<Character, String> encodingMap;
    private Map<String, Character> decodingMap;

    public HuffmanCoding(){
        HuffmanPreparation huffmanPreparation = new HuffmanPreparation();
        encodingMap = huffmanPreparation.getEncodingMap();
        decodingMap = huffmanPreparation.getDecodingMap();
    }

    public String getCompressedString (String str){
        String compressed = "";
        for (int i = 0; i < str.length(); i++) {
            compressed += encodingMap.get(str.charAt(i));
        }
        return compressed;
    }

    public String extract (String compressed){
        String decompressed = "";
        String temp = "";
        for (int i = 0; i < compressed.length(); i++) {
            temp += compressed.charAt(i);
            if(decodingMap.containsKey(temp)){
                decompressed += decodingMap.get(temp);
                temp = "";
            }
        }
        return decompressed;
    }
}
