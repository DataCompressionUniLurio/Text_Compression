/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.shannonfano;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author feler
 */
public class ShannoFanoTest {
    
    public ShannoFanoTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testSomeMethod() {
    }
    
    @Test
    public void test(){
        ShannoFano sf = new ShannoFano();
        
//        String message = "AAAAAAAAAAAAAAABBBBBBBCCCCCCDDDDDDEEEEE";
        
        String message = "MIGGGGEEEL";
        
        for (int i = 0; i < message.length(); i++) {
             System.out.print(Integer.toBinaryString((int) message.charAt(i)));
        }
        System.out.println("");
        
        String encoded = sf.encode(message);
        System.out.println(encoded);
        
        
        String decoded = sf.decode(encoded);
        assertEquals(message, decoded);
    }
    
}
