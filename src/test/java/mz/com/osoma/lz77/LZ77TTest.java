/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.lz77;

import mz.com.osoma.FileHandler;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author feler
 */
public class LZ77TTest {

    public LZ77TTest() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

//    @Test
//    public void test() {
//
//        String message = FileHandler.readFile("src//main//resources//input.txt", true);
//        message = message.substring(0, message.length());
//
//        LZ77T lz = new LZ77T();
//
//        String encoded = lz.encode(message);
//        String decoded = lz.decode(encoded);
//
//        assertTrue(decoded.equals(encoded));
//    }
    @Test
    public void test2(){
    
//        String message = FileHandler.readFile("src//main//resources//input.txt", true);
//        String enc = FileHandler.readFile("src//main//resources//outLz.txt", true);
//        message = message.substring(0, message.length());
//        enc = enc.substring(0, enc.length());
//
//        LZ77T lz = new LZ77T();
//
//        String encoded = lz.encode(message);
//        String decoded = lz.decode(enc);
//
//        
//        char[] array =  encoded.toCharArray();
//        
//        String problem = "";
//        int index =0;
//        for (int i = 0; i < array.length; i++) {
//            if(array[i] != enc.charAt(i)){
//                for (int j = i; j < i+10; j++) {
//                   problem += array[j];
//                }
//                index = i;
//                break;
//            }
//        }
        
//        System.out.println("------> "+problem+" index "+index);
//        System.out.println("normal "+enc.substring(index, index+10));
//        System.out.println(encoded);
//        System.out.println("\n\n =======> menc len"+encoded.length()+" file len"+enc.length());
//         System.out.println("\n\n =======> last menc ["+(int)encoded.charAt(encoded.length()-2)+"] file last ["+(int)enc.charAt(enc.length()-2)+"] jjj");
//        assertTrue(enc.equals(encoded));
    
        
//        assertTrue(message.equals(decoded));
    }
    
    @Test
    
   public void tedd()
   {
        String message = FileHandler.readFile("src//main//resources//input.txt", true);
        message = message.substring(0, message.length());
        
        System.out.println("len "+message.length());
   }

}
