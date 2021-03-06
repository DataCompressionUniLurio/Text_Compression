/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.lzw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mz.com.osoma.compressable;

/**
 *
 * @author aphie_d
 */
public class Lzw implements compressable{
    
    /** Compress a string to a list of output symbols. */
    private  List<Integer> encoding(String uncompressedText) {
        System.out.println("Texto Bruto: " +uncompressedText);
        // Build the dictionary.
        int dictionarySize = 256;
        Map<String,Integer> dictionary = new HashMap<String,Integer>();
        for (int i = 0; i < 256; i++)
            dictionary.put("" + (char)i, i); //Constroi o dionario adicionando a representacao dos nr na tabela ASCII como chaves e a sua chave e o seu nr i intercativo.
        String word = "";
        List<Integer> code = new ArrayList<Integer>(); //lista que vai conter o codigo das plavras
        for (char c : uncompressedText.toCharArray()) {
            String wc = word + c;
            if (dictionary.containsKey(wc))//faz se  retorna true (se a palavra estiver no dicionario)
                word = wc;
            else {
                code.add(dictionary.get(word));
                // Add wc to the dictionary.
                dictionary.put(wc, dictionarySize++);
                word = "" + c;
            }
        }
        // Output the code for word.
        if (!word.equals(""))
            code.add(dictionary.get(word));
        System.out.println("Texto Comprimido: " +code);
        return code;
    }
 
    /** Decompress a list of output ks to a string. */
    private  String decoding(List<Integer> compressed) {
        // Build the dictionary.
        int dictionarySize = 256;
        Map<Integer,String> dictionary = new HashMap<Integer,String>();
        for (int i = 0; i < 256; i++)
            dictionary.put(i, "" + (char)i);
        String word = "" + (char)(int)compressed.remove(0);
        System.out.println("ggg"+word);
        String x = "" + (char)(int)compressed.get(1);
        StringBuffer decompressedText = new StringBuffer(word);
        for (int k : compressed) {
            String newEntry;
            if (dictionary.containsKey(k)){
                newEntry = dictionary.get(k);
            System.out.println("ffff"+newEntry);}
            else if (k == dictionarySize)
                newEntry = word + word.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);
            decompressedText.append(newEntry);
         
            // Add word+newEntry[0] to the dictionary.
            dictionary.put(dictionarySize++, word + newEntry.charAt(0));
            word = newEntry;
        }
        System.out.println("Texto Decompresso: " +decompressedText);
        return decompressedText.toString();
    }
 
    public static void main(String[] args) {
//        List<Integer> compressed = encoding("TOBEORNOTTOBEORTOBEORNOT");
//        String decompressed = decoding(compressed);
        
        Lzw lzw = new Lzw();
        
//        System.out.println(lzw.encode("TOBEORNOTTOBEORTOBEORNOT"));
        
        String message = "TOBEORNOTTOBEORTOBEORNOT";
        String encoded = lzw.encode(message);
        String decoded = lzw.decode(encoded);
        
        System.out.println(message);
        System.out.println(decoded);
        
        
    }

    @Override
    public String decode(String encodedMessage) {
        
        String[] split = encodedMessage.split(",");
        
        List<Integer> encoded = new ArrayList<>();
        for (String split1 : split) {
            encoded.add(Integer.parseInt(split1));
        }
        
        return decoding(encoded);

    }

    @Override
    public String encode(String message) {
        
        List<Integer> encoded = encoding(message);
        String result = "";
        for (int i = 0; i<encoded.size(); i++) {
            result += encoded.get(i).toString();
            if(i<encoded.size()-1){
                 result +=",";
            }
        }
        
        return result;
    }
}
