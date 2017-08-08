/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.Aritmetric;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Jr
 */
public class ArithmeticTest {
    
    public ArithmeticTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sortString method, of class Arithmetic.
     */
    @Test
    public void testSortString() {
        System.out.println("sortString");
        ArrayList<String> c = null;
        ArrayList<Double> p = null;
        Arithmetic.sortString(c, p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compression method, of class Arithmetic.
     */
    @Test
    public void testCompression() {
        System.out.println("compression");
        String symbols = "";
        Arithmetic.compression(symbols);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decompression method, of class Arithmetic.
     */
    @Test
    public void testDecompression() {
        System.out.println("decompression");
        ArrayList<Double> prob = null;
        ArrayList<String> characters = null;
        Double res = null;
        int length = 0;
        Arithmetic.decompression(prob, characters, res, length);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Arithmetic.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Arithmetic.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
