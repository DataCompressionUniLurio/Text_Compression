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
public class PriorityQ {

    SortedList theList;

    public PriorityQ() {
        theList = new SortedList();
    }

    public void insert(HuffmanTree item) {
        theList.insert(item);

    }

    public HuffmanTree remove() {
        return theList.remove();
    }

    public boolean isEmpty() {
        return theList.isEmpty();
    }

    public int size() {
        return theList.size();
    }


}
