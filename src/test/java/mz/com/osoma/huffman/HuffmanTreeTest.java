/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.huffman;

import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author feler
 */
public class HuffmanTreeTest {
    
    public HuffmanTreeTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testSomeMethod() {
    }
    
    
    @Test
    public void test() {

        String message = "Alleluia, praise the LORD!!!";
        HuffmanTree tree = new HuffmanTree(message);

        String encoded = tree.encode(message);
        String decoded = tree.decode(encoded);
        
        Assert.assertEquals(decoded, message);
    }
}
