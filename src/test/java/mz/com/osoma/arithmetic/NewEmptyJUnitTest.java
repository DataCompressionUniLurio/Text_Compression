/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.arithmetic;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aphie_d
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    
    @Test
    public void test1(){
    
        Arithmetic cl = new Arithmetic();
        
        String bin = Arithmetic.encoding(0.325675675372567);
        
        assertEquals("111110101001101011111011110110", bin);
  
    
    }
    
    @Test
    public void test2(){
    
        Arithmetic cl = new Arithmetic();
        
        
        float bin = Arithmetic.decoding("111110101001101011111011110110");
        float delta = (float) (0.325675675372567 - bin);
        
        assertEquals(0.325675675372567, bin, delta);
  
    
    }
}
