/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.lzw;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author aphie_d
 */
public class LzwTest {
    
    @Test
    public void test1(){
    
        Lzw cl = new Lzw();
        List<Integer> bin = Lzw.encoding("TOBEORNOTTOBEORTOBEORNOT");
        ArrayList<Integer> code = new ArrayList<Integer>();
        code.add(84);
        code.add(79);
        code.add(66);
        code.add(69);
        code.add(79);
        code.add(82);
        code.add(78);
        code.add(79);
        code.add(84);
        code.add(256);
        code.add(258);
        code.add(260);
        code.add(265);
        code.add(259);
        code.add(261);
        code.add(263);
        
        assertEquals(code, bin);
  
    
    }
}
