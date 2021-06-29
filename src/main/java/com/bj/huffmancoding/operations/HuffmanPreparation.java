package com.bj.huffmancoding.operations;

import com.bj.huffmancoding.io.FileRW;
import com.bj.huffmancoding.tree.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class HuffmanPreparation {
    private Map<Character, String> encodingMap = new HashMap<>();
    private Map<String, Character> decodingMap = new HashMap<>();

    //kolejka
    private TreeSet<Node> queue = new TreeSet<>(
            (o1, o2) -> {
                int f1 = o1.getFrequency();
                int f2 = o2.getFrequency();
                String v1 = o1.getValue();
                String v2 = o2.getValue();
                return v1.equals(v2) ? 0 : f1 < f2 ? -1 : 1;
            });

    public Map<Character, String> getEncodingMap() {
        return encodingMap;
    }
    public Map<String, Character> getDecodingMap() {
        return decodingMap;
    }


    public String compressString(String str) {
        System.out.println("Budowanie mapy częstotliwości wystąpień:");
        Map<Character, Integer>  frequencyMap = getFrequencyMap(str);

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }

        System.out.println("Dodawanie węzłów do kolejki:");
        addNodesToQueue(frequencyMap);
        queue.forEach(System.out::println);

        System.out.println("Budowanie drzewa:");
        Node root = getHuffmanTree();

        System.out.println("Budowanie mapy kodowania:");
        fillCodingMaps(root, "", "");
        for (Map.Entry<Character, String> entry : encodingMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }

        System.out.println("Wynik:");
        String compressedString = getCompressedString(str);
        System.out.println(compressedString);

        return compressedString;
    }


    private String getCompressedString (String str){
        String compressed = "";
        for (int i = 0; i < str.length(); i++) {
            compressed += encodingMap.get(str.charAt(i));
        }
        return compressed;
    }


    private Map<Character, Integer> getFrequencyMap(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer val = map.get(c);
            if (val != null) {
                map.put(c, new Integer(val + 1));
            }
            else {
                map.put(c, 1);
            }
        }
        return map;
    }

    //Wszystkie węzły z pliku dodajemy do kolejki
    private void addNodesToQueue(Map<Character, Integer>  frequencyMap){
        frequencyMap.forEach((k, v) ->
                queue.add(new Node(k.toString(),v)));
    }

    //bierzemy 2 minimalne elementy i tworzymy nowy węzeł na podstawie nich
    private Node getHuffmanTree() {
        while (queue.size() > 1){
            Node first = queue.pollFirst();
            Node second = queue.pollFirst();

            Node newNode = new Node();
            newNode.setValue(first.getValue()+second.getValue());
            newNode.setFrequency(first.getFrequency()+second.getFrequency());
            newNode.setLeftNode(first);
            newNode.setRightNode(second);
            queue.add(newNode);
        }
        return queue.first();
    }

    //Kodowanie symboli na kod ciąg bitów
    private void fillCodingMaps(Node node, String codeBefore, String direction){
        if(node.isLeaf()){
            encodingMap.put(node.getValue().charAt(0), codeBefore + direction);
            decodingMap.put(codeBefore + direction, node.getValue().charAt(0));
        }
        else {
            fillCodingMaps(node.getLeftNode(), codeBefore + direction, "0");
            fillCodingMaps(node.getRightNode(), codeBefore + direction, "1");
        }
    }
}
