/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.huffman;

/**
 *
 * @author feler
 */
public class HuffmanTreeApp {


    public static void main(String[] args) {

        
        System.out.println((int) 'S');
//        String message = "SUSIE SAYS IT IS EASY\n";
         String message = "Alleluia, praise the LORD!!!";
         //String message = "a fast runner need never be afraid of the dark";
         
         
        
        
        HuffmanTree tree = new HuffmanTree(message);

        String encoded = new String();
        encoded = tree.encode(message);
        String notCompressedMessage = "";
        for (int i = 0; i < message.length(); i++) {
            notCompressedMessage += Integer.toBinaryString((int) message.charAt(i));
        }
        System.out.println("Not Compressed: "+notCompressedMessage);
        System.out.println("Compressed: "+encoded);
        
        String decoded = new String();
        decoded = tree.decode(encoded);
        System.out.println(decoded);
    }

}
