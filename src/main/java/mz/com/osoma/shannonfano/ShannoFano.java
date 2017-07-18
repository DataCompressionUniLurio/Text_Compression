/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.shannonfano;

import java.util.Hashtable;
import mz.com.osoma.huffman.HuffmanNode;

/**
 *
 * @author feler
 */
public class ShannoFano {

    private HuffmanNode root;
    private String[] codeTable;
    private String huffmanCode;

    public ShannoFano(String message) {

        huffmanCode = new String();
        codeTable = new String[128]; //going to keep it ASCII for simplicity

        Hashtable<String, String> frequency = buildFrequencyTable(message);
        
        int[] freq = new int[frequency.keySet().size()];
        int i = 0;
        for (String key : frequency.keySet()) {
            freq[i++] = Integer.parseInt(frequency.get(key));
        }
        
//        for

    }

    public String encode(String message) {
        return "";
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

}
