/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.rle;

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
        RLE rle = new RLE("XXXX");
        rle.encode();
        assertEquals("r4X", rle.outEncoding);
    }

    @Test
    public void test5() {

        System.out.println("---Begin----");
        System.out.println("test5");
        RLE rle = new RLE("  X");
        rle.encode();
        assertEquals("r2n1X", rle.outEncoding);
        System.out.println("---End----");
    }

    @Test
    public void test6() {
        System.out.println("---Begin----");
        System.out.println("test6");
        RLE rle = new RLE("ABC");
        rle.encode();
        assertEquals("n3ABC", rle.outEncoding);
        System.out.println("---End----");
    }

    @Test
    public void test7() {

        System.out.println("test7");
        RLE rle = new RLE("ABCXXX");
        rle.encode();
        assertEquals("n3ABCr3X", rle.outEncoding);
    }
    
     @Test
    public void test8() {

        System.out.println("test8");
        RLE rle = new RLE("XXXXABC");
        rle.encode();
        assertEquals("r4Xn3ABC", rle.outEncoding);
    }
    
    
    
     @Test
    public void test9() {
        System.out.println("test9");
        RLE rle = new RLE("  XXXX ABCGG");
        rle.encode();
        assertEquals("r2r4Xn6 ABCGG", rle.outEncoding);
    }
    
    @Test
    public void test10(){
        System.out.println("test10");
        RLE rle = new RLE("ABABBBC");
        rle.encode();
        assertEquals("n3ABAr3Bn1C", rle.outEncoding);
    }
    
    @Test
    public void test11(){
    
//         RLE rle = new RLE("ABABBBC");
//         assertTrue(rle.isControlSymbol("n2"));
//         assertTrue(rle.isControlSymbol("r5"));
    }
    
    
     @Test
    public void test12(){
//        System.out.println("test12");
//        RLE rle = new RLE("ABABBBC");
//        rle.encode();
//        assertEquals("n3", rle.readNextSymbol(0));
    }
    
    @Test 
        public void test13(){
    
         System.out.println("test13");
        RLE rle = new RLE("   BBBCB");
        rle.encode();
        System.out.println("--------------------------------------------------------");
        rle.decode();
    
    }
    
}
