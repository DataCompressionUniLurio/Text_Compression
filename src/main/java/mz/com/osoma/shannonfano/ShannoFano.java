/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.shannonfano;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;
import mz.com.osoma.huffman.HuffmanNode;

/**
 *
 * @author feler
 */
public class ShannoFano {

    private HuffmanNode root;
    private String[] codeTable;
//    private String huffmanCode;
    private CharacterFreq[] frequency;
    private int totalItems;
    private String message;

    public ShannoFano(String message) {

        codeTable = new String[128]; //going to keep it ASCII for simplicity
        this.message = message;
    }

    public ShannoFano() {
        codeTable = new String[128]; //going to keep it ASCII for simplicity
    }

    public String encode(String message) {

        this.message = message;

        createFrequencyTable(message);
        shannoFano(this.frequency);

        String result = new String();

        for (int i = 0; i < message.length(); i++) {
            int index = (int) message.charAt(i);
            result += codeTable[index];
        }

        createTree();
        System.out.println(this);
        return result;
    }
    
    public String decode(String codedMessage) {

        String result = new String();
        HuffmanNode temp = root;
        int i = 0;
        while (i < codedMessage.length()) {
            if (temp.character == null) {
                if (codedMessage.charAt(i) == '0') {
                    temp = temp.leftChild;
                } else if (codedMessage.charAt(i) == '1') {
                    temp = temp.rightChild;
                }
                i++;
            } else {
                result += temp.character;
                temp = root;
            }
        }
        result += temp.character;	//parse the last character

        return result;

    }

    private void createTree() {

        root = new HuffmanNode(sum(this.frequency));
        
        
        System.out.println("--------------------------------->"+sum(this.frequency));

        for (int i = 0; i < frequency.length; i++) {
            int index = (int) frequency[i].character.charAt(0);
            System.out.println("--------------------------------->"+codeTable[index]);
            String codeword = codeTable[index];

            HuffmanNode current = root;
            HuffmanNode parent = root;
            for (int j = 0; j < codeword.length() - 1; j++) {
                
                if (codeword.charAt(j) == '0') {

                    if (current.leftChild == null) {
                        current.leftChild = new HuffmanNode(0);
                    }

                    current = current.leftChild;
                }

                if (codeword.charAt(j) == '1') {
                    if (current.rightChild == null) {
                        current.rightChild = new HuffmanNode(0);
                    }
                    current = current.rightChild;
                }
                parent = current;
            }
            int j = codeword.length() - 1;
            
            if(j > 0)
            parent.frequency = parent.frequency + frequency[i].frequency;
            
            
            if (codeword.charAt(j) == '0') {
                if (current.leftChild == null) {
                    current.leftChild = new HuffmanNode(frequency[i].character);
                }
            }

            if (codeword.charAt(j) == '1') {
                if (current.rightChild == null) {
                    current.rightChild = new HuffmanNode(frequency[i].character);
                }
            }

            current = root;
        }
    }

    public void shannoFano(CharacterFreq[] array) {

        if (array.length < 3) {

            char tempL = array[0].character.charAt(0);
            if (codeTable[tempL] == null) {
                codeTable[tempL] = "";

            }
            codeTable[tempL] = codeTable[tempL] + '0';

            if (array.length > 1) {
                char tempR = array[1].character.charAt(0);
                if (codeTable[tempR] == null) {
                    codeTable[tempR] = "";
                }
                codeTable[tempR] = codeTable[tempR] + '1';
            }
            return;
        }
        System.out.println(Arrays.toString(array));
        int middle = middleIndex(array);

        System.out.println("middle " + middle);
        CharacterFreq[] arrayLeft = subArray(0, middle, array);
        System.out.println("left");
        System.out.println(Arrays.toString(arrayLeft));

        CharacterFreq[] arrayright = subArray(middle, array.length, array);
        System.out.println("right");
        System.out.println(Arrays.toString(arrayright));

        if (arrayLeft.length > 1) {
            for (CharacterFreq a : arrayLeft) {
                char temp = a.character.charAt(0);
                if (codeTable[temp] == null) {
                    codeTable[temp] = "";
                }
                codeTable[temp] = codeTable[temp] + '0';
            }
        }

        if (arrayright.length > 1) {
            for (CharacterFreq a : arrayright) {
                char temp = a.character.charAt(0);
                if (codeTable[temp] == null) {
                    codeTable[temp] = "";
                }
                codeTable[temp] = codeTable[temp] + '1';
            }
        }

        shannoFano(arrayLeft);
        shannoFano(arrayright);
    }

    public void createFrequencyTable(String message1) throws NumberFormatException {
        Hashtable<String, String> htFreq = buildFrequencyTable(message1);
        this.frequency = new CharacterFreq[htFreq.keySet().size()];
        for (String key : htFreq.keySet()) {
            this.insert(new CharacterFreq(key, Integer.parseInt(htFreq.get(key))));
        }
    }

    public int sum(CharacterFreq[] array) {
        int sum = 0;

        for (CharacterFreq cf : array) {
            sum += cf.frequency;
        }
        return sum;
    }

    public int middleIndex(CharacterFreq[] array) {

        if (array.length == 3) {
            return 1;
        }
        int totalSum = sum(array);
        int i = 0;
        int sum = 0;

        while (sum < totalSum / 2) {
            sum += array[i].frequency;
            i++;
        }

        return i;
    }

    public CharacterFreq[] subArray(int start, int end, CharacterFreq[] array) {

        List<CharacterFreq> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add(array[i]);
        }
        CharacterFreq[] subArray = list.toArray(new CharacterFreq[list.size()]);
        return subArray;
    }

    public Hashtable<String, String> buildFrequencyTable(String s) {

        Hashtable<String, String> ht = new Hashtable<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!ht.containsKey(c + "")) {
                ht.put(c + "", 1 + "");
            } else {
                int increment = Integer.parseInt(ht.get(c + "")) + 1;
                ht.put(c + "", increment + "");
            }
        }
        return ht;
    }

    public void insert(CharacterFreq item) {

        if (totalItems == 0) {
            frequency[totalItems++] = item;
        } else {

            int i;

            for (i = totalItems - 1; i >= 0; i--) {

                if (item.frequency > frequency[i].frequency) {
                    frequency[i + 1] = frequency[i];
                } else {
                    break;
                }
            }

            frequency[i + 1] = item;
            totalItems++;
        }
    }

    @Override
    public String toString() {

        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;

        StringBuilder builder = new StringBuilder(".......................................................\n");

        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++) {
                builder.append(" ");

            }

            while (globalStack.isEmpty() == false) {
                HuffmanNode temp = (HuffmanNode) globalStack.pop();
                if (temp != null) {

                    builder.append((temp.character != null) ? temp.character : temp.frequency);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null
                            || temp.rightChild != null) {
                        isRowEmpty = false;
                    }
                } else {
                    builder.append("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    builder.append(" ");
                }
            } //end while globalStack not empty
            builder.append("\n");
            nBlanks /= 2;
            while (localStack.isEmpty() == false) {
                globalStack.push(localStack.pop());
            }
        } //end while isRowEmpty is false

        builder.append(".......................................................\n");
        return builder.toString();
    }

}
