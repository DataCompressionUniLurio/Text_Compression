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
import mz.com.osoma.huffman.HuffmanNode;

/**
 *
 * @author feler
 */
public class ShannoFano {

    private HuffmanNode root;
    private String[] codeTable;
    private String huffmanCode;
    private CharacterFreq[] frequency;
    private int totalItems;
    private String message;

    public ShannoFano(String message) {

        huffmanCode = "";
        codeTable = new String[128]; //going to keep it ASCII for simplicity
        this.message = message;
    }

    public ShannoFano() {

        huffmanCode = "";
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
        return result;
    }

    public void shannoFano(CharacterFreq[] array) {

        System.out.println("\n\n-----------------------------");
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

        for (CharacterFreq a : arrayLeft) {
            char temp = a.character.charAt(0);
            if (codeTable[temp] == null) {
                codeTable[temp] = "";
            }
            codeTable[temp] = codeTable[temp] + '0';
        }

        for (CharacterFreq a : arrayright) {
            char temp = a.character.charAt(0);
            if (codeTable[temp] == null) {
                codeTable[temp] = "";
            }
            codeTable[temp] = codeTable[temp] + '1';
        }

        shannoFano(arrayLeft);
        System.out.println("segunda recursao");
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

    public String decode(String codedMessage) {
        return "";
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

}
