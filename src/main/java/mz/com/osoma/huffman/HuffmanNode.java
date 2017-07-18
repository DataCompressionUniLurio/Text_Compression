
package mz.com.osoma.huffman;

/**
 *
 * @author feler
 */
public class HuffmanNode implements Comparable<Object>{
    
    public String character;
    public int frequency;
    public HuffmanNode leftChild;
    public HuffmanNode rightChild;

    public HuffmanNode(String character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public HuffmanNode(int frequency) {
        this.frequency = frequency;
    }
    
    @Override
    public String toString(){
        return "{"+character+", "+frequency+"}";
    }
    
    public int compareTo(Object o)
	{
		HuffmanNode node = (HuffmanNode)o;
		return (this.frequency - node.frequency);
	}
    
}
