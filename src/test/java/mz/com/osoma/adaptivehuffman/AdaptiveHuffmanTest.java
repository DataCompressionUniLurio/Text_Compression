



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.adaptivehuffman;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import mz.com.osoma.FileHandler;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author feler
 */
public class AdaptiveHuffmanTest {
    
    public AdaptiveHuffmanTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testEncode() {
        
        String message = FileHandler.readFile("src/main/resources/input.txt", true);
        message = message.substring(0, message.length() - 1);
        
        AdaptiveHuffman ah = new AdaptiveHuffman(message.toCharArray());
        
        ah.encode();
        String decoded = ah.decode();
        assertEquals(message, decoded);
        
        
//        List<String> out = new ArrayList<>();
//        out.add('0'+Integer.toBinaryString('A'));
//        out.add("1");
//        out.add("1");
//        assertEquals(out, ah.encode());
    }
 
    
    @Test
    public void test2() {
        
        
//        
//        String str = "AAA";
//        AdaptiveHuffman ah = new AdaptiveHuffman("AAA".toCharArray());
//        String aBin = Integer.toBinaryString((int)'A');
        
        
//        System.out.println(ah.encode());
//        assertEquals("0"+aBin+"11", ah.encode().toArray()[0]+""+ah.encode().toArray()[1]+""+ah.encode().toArray()[2]);
//        assertEquals("AAA", ah.decode());
    }
}
