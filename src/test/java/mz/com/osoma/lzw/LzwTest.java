/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.lzw;

import java.util.ArrayList;
import java.util.List;
import mz.com.osoma.FileHandler;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author aphie_d
 */
public class LzwTest {
    
    @Test
    public void test1(){
        String message = FileHandler.readFile("src/main/resources/input.txt", true);
        message = message.substring(0, message.length() - 1);
        Lzw lzw = new Lzw();
        
        String encoded = lzw.encode(message);
        String decoded = lzw.decode(encoded);
        assertEquals(message, decoded);
    
    }
}
