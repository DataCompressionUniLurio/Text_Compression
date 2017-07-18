/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.huffman;

/**
 *
 * @author feler
 */
public interface Operacoes {
   
    public void insert(HuffmanTree elemento);
    
    public HuffmanTree remove();
    
    public int size();
}
