/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.adaptivehuffman;

import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
    public void test1() {
        
        String str = "A";
        AdaptiveHuffman ah = new AdaptiveHuffman("A".toCharArray());
        assertEquals('0'+Integer.toBinaryString((int)'A'), ah.getCode(str.charAt(0)));
        
    }
 
    
    @Test
    public void test2() {
        
        String str = "AAA";
        AdaptiveHuffman ah = new AdaptiveHuffman("AAA".toCharArray());
        String aBin = Integer.toBinaryString((int)'A');
        
        
//        System.out.println(ah.encode());
        assertEquals("0"+aBin+"11", ah.encode().toArray()[0]+""+ah.encode().toArray()[1]+""+ah.encode().toArray()[2]);
        assertEquals("AAA", ah.decode());
    }
}
