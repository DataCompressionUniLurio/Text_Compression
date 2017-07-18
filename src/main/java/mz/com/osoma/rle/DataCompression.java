/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.rle;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author feler
 */
public class DataCompression {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String s = "aabbeedece";
        Enumeration characters; 
        Hashtable ht = new Hashtable();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if(!ht.containsKey((int)c)){
                ht.put((int) c, 1);
            }else{
                ht.put((int) c, (int)ht.get((int) c) + 1);
            }
        }
        
        characters = ht.keys();
        
        while(characters.hasMoreElements()){
            int c = (int) characters.nextElement();
            System.out.print((char)c);
        }
        
         System.out.println("");
         
        System.out.println("e = " + ht.get((int)'e'));
        
       
               
    }
    
}
