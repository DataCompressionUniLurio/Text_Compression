package mz.com.osoma.huffman;

import java.util.Hashtable;
import java.util.Stack;

/**
 *
 * @author feler
 */
public class HuffmanTree {

    private HuffmanNode root;
    private String[] codeTable;
    private String huffmanCode;			//used to generate table with "0"s and "1"s

    public HuffmanTree() {
        root = null;
        codeTable = new String[128];
    }

    public HuffmanTree(String message) {

        huffmanCode = new String();
        codeTable = new String[128]; //going to keep it ASCII for simplicity

        Hashtable<String, String> frequency = buildFrequencyTable(message);
        PriorityQ queue = new PriorityQ();
        for (String key : frequency.keySet()) {
            int freq = Integer.parseInt(frequency.get(key));
            HuffmanNode node = new HuffmanNode(key, freq);
            HuffmanTree tree = new HuffmanTree(node);
            queue.insert(tree);
        }
      
        while (queue.size() > 1) {

            HuffmanTree child1 = queue.remove();
            HuffmanTree child2 = queue.remove();

            int child1Freq = child1.getRoot().frequency;
            int child2Freq = child2.getRoot().frequency;
            int sumFreq = child1Freq + child2Freq;

            HuffmanNode newNode = new HuffmanNode(sumFreq);

            newNode.leftChild = child1.getRoot();
            newNode.rightChild = child2.getRoot();

            HuffmanTree newTree = new HuffmanTree(newNode);

            queue.insert(newTree);

        }

        HuffmanTree finalTree = queue.remove();
        System.out.println(finalTree);

        root = finalTree.getRoot();

        System.out.println(" done");

        System.out.print("Creating code table...");
        createTable(root);

        System.out.println(" done");
    }

    public HuffmanTree(HuffmanNode node) {
        root = node;
    }

    public HuffmanNode getRoot() {
        return root;
    }

    public void createTable(HuffmanNode localRoot) {

        if (localRoot.character != null) {
            char temp = localRoot.character.charAt(0);
            int index = (int) temp;
            codeTable[index] = huffmanCode;
            System.out.println("Generated (" + codeTable[index] + ") at index " + index + " character " + temp);
            return;
        } else {
            huffmanCode += "0";
            createTable(localRoot.leftChild);
            huffmanCode = huffmanCode.substring(0, huffmanCode.length() - 1);

            huffmanCode += "1";
            createTable(localRoot.rightChild);
            huffmanCode = huffmanCode.substring(0, huffmanCode.length() - 1);
        }

    }

    public String encode(String message) {

        String result = new String();

        for (int i = 0; i < message.length(); i++) {
            int index = (int) message.charAt(i);
            result += codeTable[index];
        }
        return result;
    }

    public String decode(String codedMessage) {

        String result = new String();
        HuffmanNode temp = root;
        int i = 0;
        while (i < codedMessage.length()) {
            if (temp.character == null) {
                if (codedMessage.charAt(i) == '0') {
                    temp = temp.leftChild;
                } else if (codedMessage.charAt(i) == '1') {
                    temp = temp.rightChild;
                }
                i++;
            } else {
                result += temp.character;
                temp = root;
            }
        }
        result += temp.character;	//parse the last character

        return result;

    }

    @Override
    public String toString() {

        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;

        StringBuilder builder = new StringBuilder(".......................................................\n");

        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++) {
                builder.append(" ");

            }

            while (globalStack.isEmpty() == false) {
                HuffmanNode temp = (HuffmanNode) globalStack.pop();
                if (temp != null) {

                    builder.append((temp.character != null) ? temp.character : temp.frequency);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null
                            || temp.rightChild != null) {
                        isRowEmpty = false;
                    }
                } else {
                    builder.append("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    builder.append(" ");
                }
            } //end while globalStack not empty
            builder.append("\n");
            nBlanks /= 2;
            while (localStack.isEmpty() == false) {
                globalStack.push(localStack.pop());
            }
        } //end while isRowEmpty is false

        builder.append(".......................................................\n");
        return builder.toString();
    }

    public Hashtable<String, String> buildFrequencyTable(String s) {

        Hashtable<String, String> ht = new Hashtable<String, String>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!ht.containsKey(c+"")) {
                ht.put(c+"", 1+"");
            } else {
                int increment = Integer.parseInt(ht.get(c+""))+ 1;
                ht.put(c+"", increment+"");
            }
        }
        return ht ;
    }

    
}
