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
        
        String message = "AAAAAAAAAAAAAAABBBBBBBCCCCCCDDDDDDEEEEE";
        String encoded = sf.encode(message);
        assertEquals(encoded, "00000000000000000000000000000001010101010101110110110110110110100100100100100100111111111111111");
    }
    
}
