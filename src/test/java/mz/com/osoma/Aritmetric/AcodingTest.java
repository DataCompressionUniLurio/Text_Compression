/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.Aritmetric;

import static mz.com.osoma.Aritmetric.Acoding.Conversaobinario;
import static mz.com.osoma.Aritmetric.Acoding.binario;
import static mz.com.osoma.Aritmetric.Acoding.converterBin;
import static mz.com.osoma.Aritmetric.Acoding.decimal;
import static mz.com.osoma.Aritmetric.Acoding.vezesMultiplicadas;

import static mz.com.osoma.Aritmetric.Arithmetic.result;
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
public class AcodingTest {
    
    public AcodingTest() {
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
     * Test of converterBin method, of class Acoding.
     */
  @Test
    public void test3() {
        System.out.println("test3");
        Acoding ac = new Acoding();
        
      binario(0.203125);
      assertEquals("00111110010100000000000000000000",Conversaobinario.toString());
      
        
    }
    
    @Test
    public void test4() {
        System.out.println("test3");
        Acoding ac = new Acoding();
        
      decimal("00111110010100000000000000000000");
      //assertEquals("00111110010100000000000000000000",Conversaobinario.toString());
      
        
    }
    
}
