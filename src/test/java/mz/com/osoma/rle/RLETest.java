/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.rle;

import junit.framework.Assert;
import mz.com.osoma.FileHandler;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author feler
 */
public class RLETest {

    public RLETest() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testSomeMethod() {
    }

    @Test
    public void test1() {
        RLE rle = new RLE("  b");
        assertTrue(rle.beginRun());
    }

    @Test
    public void test2() {
        RLE rle = new RLE("oooh");

        rle.shift1();

        assertEquals('h', rle.sr);
    }

    @Test
    public void test3() {
        System.out.println("test3");
        RLE rle = new RLE("XXX");

        mz.com.osoma.rle.Character c = rle.findLongestRun();
        assertEquals("X", c.symbol);
        assertEquals(3, c.lenght);
    }

    @Test
    public void test4() {
        System.out.println("test4");
        RLE rle = new RLE();
        String str = rle.encode("XXXX");
        assertEquals("r4X", str);
    }

    @Test
    public void test5() {

        System.out.println("test5");
        RLE rle = new RLE();
        String str = rle.encode("  X");
        assertEquals("r2n1X", str);
    }

    @Test
    public void test6() {
        System.out.println("test6");
        RLE rle = new RLE();
        String str = rle.encode("ABC");
        assertEquals("n3ABC", str);
    }

    @Test
    public void test7() {

        System.out.println("test7");
        RLE rle = new RLE();
        String str = rle.encode("ABCXXX");
        assertEquals("n3ABCr3X", str);
    }

    @Test
    public void test8() {

        System.out.println("test8");
        RLE rle = new RLE();
        String str = rle.encode("XXXXABC");
        assertEquals("r4Xn3ABC", str);
    }

    @Test
    public void test9() {
        System.out.println("test9");
        String message = "  XXXX ABCGG";
        RLE rle = new RLE();
        String str = rle.encode(message);
        assertEquals("r2r4Xn6 ABCGG", str);
    }

    @Test
    public void test10() {
        System.out.println("test10");
        String message = "ABABBBC";
        RLE rle = new RLE();
        String str = rle.encode(message);
        assertEquals("n3ABAr3Bn1C", str);
    }

    @Test
    public void test13() {

        System.out.println("test13");
        String str = "   BBBCB";
        RLE rle = new RLE(str);
        String encoded = rle.encode(str);
        assertEquals("r3r3Bn2CB", encoded);
        String decoded = rle.decode(encoded);
        assertEquals(decoded, str);

    }

    @Test
    public void test12() {

        System.out.println("test12");
        String str = "ABABBBCG";
        RLE rle = new RLE(str);
        String encoded = rle.encode(str);
        assertEquals("n3ABAr3Bn2CG", encoded);
        String decoded = rle.decode(encoded);
        assertEquals(decoded, str);

    }

    @Test
    public void test14() {

        String message = FileHandler.readFile("src//main//resources//input.txt", true);
        
        message = message.substring(0, message.length() );
        RLE rle = new RLE(message);
        String encoded = rle.encode();
        
        System.out.println("encoded: "+encoded+"\n");
        System.out.println("\n\n");
        String decoded = rle.decode();
        
        
        Assert.assertEquals(message, decoded);
    }

}
