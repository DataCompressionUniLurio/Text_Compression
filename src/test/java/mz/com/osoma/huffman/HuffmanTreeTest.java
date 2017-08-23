/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.huffman;

import junit.framework.Assert;
import mz.com.osoma.FileHandler;
import org.junit.AfterClass;
import org.junit.Test;

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

        String message = FileHandler.readFile("src//main//resources//input.txt", true);
        message = message.substring(0, message.length());
        HuffmanTree tree = new HuffmanTree(message);

        String encoded = tree.encode(message);
        String decoded = tree.decode(encoded);

        Assert.assertEquals(message, decoded);
    }
}
