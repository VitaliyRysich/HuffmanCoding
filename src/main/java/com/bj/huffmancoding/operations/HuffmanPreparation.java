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
                double f1 = o1.getFrequency();
                double f2 = o2.getFrequency();
                return f1 < f2 ? -1 : f1 > f2 ? 1 : 0;
            });

    {
        Map<Character, Double>  frequencyMap = FileRW.getFrequencyMap();
        addNodesToQueue(frequencyMap);
        Node root = getHuffmanTree();
        fillCodingMaps(root, "", "");
    }

    public Map<Character, String> getEncodingMap() {
        return encodingMap;
    }
    public Map<String, Character> getDecodingMap() {
        return decodingMap;
    }

    //Wszystkie węzły z pliku dodajemy do kolejki
    private void addNodesToQueue(Map<Character, Double>  frequencyMap){
        frequencyMap.forEach((k, v) ->
                queue.add(new Node(k.toString(),v)));
    }

    //bierzemy 2 minimalne elementy i tworzymy nowy węzeł na podstawie nich
    private Node getHuffmanTree() {
        while (queue.size() > 1){
            Node first = queue.first();
            queue.remove(first);
            Node second = queue.first();
            queue.remove(second);

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
