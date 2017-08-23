package mz.com.osoma.adaptivehuffman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import mz.com.osoma.compressable;

public class AdaptiveHuffman implements compressable{

    private Node nytNode;
    private Node root;
    private char[] codeStr;
   
    
    
    private ArrayList<Character> alreadyExist; //list of existent symbols
    ArrayList<Node> nodeList; //collection of all nodes in the tree ordered by frequency descending
    private String tempCode = "";
    int totalnodes = 0; //keeps track of the inorder number for horiz. scaling 
    int maxheight = 0;//keeps track of the depth of the tree for vert. scaling

    protected ArrayList<String> friendlyOutput;

    public AdaptiveHuffman(){}
    public AdaptiveHuffman(char[] codeStr) {
        this.codeStr = codeStr;
        alreadyExist = new ArrayList<>();
        nodeList = new ArrayList<>();
        friendlyOutput = new ArrayList<>();

        //Initialize the nyt Node.
        nytNode = new Node("NYT", 0);
        nytNode.parent = null;
        root = nytNode;
        nodeList.add(nytNode);
    }

    public ArrayList<String> encode() {
        ArrayList<String> result = new ArrayList<>();
        //result.add("0");
        char temp;
        //run through all symbols to be compressed
        for (int i = 0; i < codeStr.length; i++) { 
            temp = codeStr[i]; //get the i'th symbol
            result.add(getCode(temp)); //output the uncompressed or compressed code for the symbol in temp 
            this.friendlyOutput.add(getCodeFriendly(temp)); //just for didatic reasons
            updateTree(temp); //
        }
        return result;
    }

    public String decode() {
        String result = "";
        String symbol;
        char temp;
        Node p = getRoot();

        //The first symbol is of course NEW, so find it by ASCII
        symbol = getByAsc(0);
        result += symbol;
        System.out.println(symbol);
        updateTree(symbol.charAt(0));
        p = getRoot();

        for (int i = 8; i < codeStr.length; i++) {
            temp = codeStr[i];
            
            //traverse the tree
            if (temp == '0') {
                p = p.left;
            } else {
                p = p.right;
            }
            //check if it's not a inner node
            symbol = visit(p);
            //it's not a inner node
            if (symbol != null) {
                if (symbol == "NYT") { //it's a NYT
                    symbol = getByAsc(i); //get uncompressed code
                    i += 8; //skip the next 8 bits
                }
                result += symbol; //output the uncompressed code
                updateTree(symbol.charAt(0));//update the tree
                p = getRoot(); //go back to the root
            }
        }

        return result;
    }

    private void updateTree(char c) {
        //make the last inserted node as old. Only for draw reasons
        recursiveTurnNodeToOld();
        Node toBeUpdated = null;
        //if it's a new symbol
        if (!isAlreadyExist(c)) {
            //create a inner node
            Node innerNode = new Node(null, 1);
            //create a new node
            Node newNode = new Node(String.valueOf(c), 1);
            //make the newly inserted node as new. Only for draw reasons
            newNode.isNew = true;
            //make nyt be the left node of the new inner node
            innerNode.left = nytNode;
            //make the new node be right node of the new inner node
            innerNode.right = newNode;
            //make the parent of the new inner node be the parent of the nyt
            innerNode.parent = nytNode.parent;
            //at this point inner node has changed positions with NYT, but the parent of NYT still points do NYT
            
            //special case for the first insertion
            if (nytNode.parent == null) {
                root = innerNode;
            } else {
                nytNode.parent.left = innerNode;
            }
            //now the previous parent of NYT points to the new inner node that is now parent of NYT and the new node, but these two still don't have it as their parent
            
            //make both nyt and the new node be children of the new inner node
            nytNode.parent = innerNode;
            newNode.parent = innerNode;
            //the tree structure is now stable
            
            //insert both new node and inner node in the node list always at the second position of the arraylist. this will be useful to the update
            nodeList.add(1, innerNode);
            nodeList.add(1, newNode);
            //insert the new symbol in the list of symbols
            alreadyExist.add(c);
            toBeUpdated = innerNode.parent;
        } else {
            //the symbol already exists and will be checked for update
            toBeUpdated = findNode(c);
            //make the newly uupdated node as new. Only for draw reasons
            toBeUpdated.isNew = true;
        }
        //loop to guarantee the sibling property 
        //look for the node closer to the tail of the arraylist with the same frequency as the one to updated, because after the update that one will break the sibling property
        //loop will run until toBeUpdated == null, i.e, will reach the root
        while (toBeUpdated != null) {
            //get the first node with the same frequency as the one to be updated
            Node toBeCompared = findToBeCompared(toBeUpdated.frequency);
            //if the node to be updated and the most recently added with same frequency  are not the same and they are not parent/child the node to be updated will brak the sibling property after the update, so they must be swaped
            if (toBeUpdated != toBeCompared && toBeUpdated.parent != toBeCompared ) {
                swapNode(toBeUpdated, toBeCompared);
            }
            toBeUpdated.frequency++;
            //go up a level
            toBeUpdated = toBeUpdated.parent;
        }
    }
    
    public void printNodes(){
        for(Node n:this.nodeList){
            System.out.print(n);
        }
        System.out.println("");
    }

    private boolean isAlreadyExist(char temp) {
        for (int i = 0; i < alreadyExist.size(); i++) {
            if (temp == alreadyExist.get(i)) {
                return true;
            }
        }
        return false;
    }

    //Get the symbol using the next 8 bit as a ASCII code.
    private String getByAsc(int index) {
        
        
        int asc = 0;
        int tempInt = 0;
        int originalIndex=index;
        for (int i = 7; i >= 0; i--) {
            if(originalIndex==0 && index < codeStr.length){
                
                System.out.println("------------------->"+index);
               
                tempInt = codeStr[index++] - 48;
            }
            else{
                tempInt = codeStr[++index] - 48;
            }
            
            asc += tempInt * Math.pow(2, i);
        }
        char ret = (char) asc;
        return String.valueOf(ret);
    }

    private String visit(Node p) {
        if (p.letter != null) {
            //The symbol has been found.
            return p.letter;
        }
        return null;
    }

    public String getCode(char c) {
        tempCode = "";
        //search the code in the tree
        getCodeByTree(getRoot(), String.valueOf(c), "");
        String result = tempCode;
        //if there's no symbol yet get the path code of NYT and output the uncompressed code of c
        if (result == "") {
            getCodeByTree(getRoot(), "NYT", "");
            result = "" + tempCode;
            result += toBinary(getAscii(c));
        }
        return result;
    }

    private String getCodeFriendly(char c) {
        tempCode = "";

        getCodeByTree(getRoot(), String.valueOf(c), "");
        String result = tempCode;
        if (result == "") {
            getCodeByTree(getRoot(), "NYT", "");
            result = "" + tempCode;
            result = result + "'" + c + "'";
        }
        return result + "\n";
    }

    //Find the existing node in the tree
    private Node findNode(char c) {
        String temp = String.valueOf(c);
        Node tempNode = null;
        for (int i = 0; i < nodeList.size(); i++) {
            tempNode = nodeList.get(i);
            if (tempNode.letter != null && tempNode.letter.equals(temp)) {
                return tempNode;
            }
        }
        return null;
    }

    private void swapNode(Node n1, Node n2) {
        //note that n1<n2
        //Swap the position in the list firstly
        int i1 = nodeList.indexOf(n1);
        int i2 = nodeList.indexOf(n2);
        nodeList.remove(n1);
        nodeList.remove(n2);
        nodeList.add(i1, n2);
        nodeList.add(i2, n1);

        //Swap the position in the tree then
        Node p1 = n1.parent;
        Node p2 = n2.parent;
        //If the two nodes have different parent node.
        if (p1 != p2) {
            if (p1.left == n1) {
                p1.left = n2;
            } else {
                p1.right = n2;
            }

            if (p2.left == n2) {
                p2.left = n1;
            } else {
                p2.right = n1;
            }
        } else {
            p1.left = n2;
            p1.right = n1;
        }
        n1.parent = p2;
        n2.parent = p1;

    }
    
    //find the node with the same frequency as the passed
    private Node findToBeCompared(int frequency) {
        Node temp = null;
        for (int i = nodeList.size() - 1; i >= 0; i--) {
            temp = nodeList.get(i);
            if (temp.frequency == frequency) {
                break;
            }
        }
        return temp;
    }

    //recursivelly search for the code in the tree
    private void getCodeByTree(Node node, String letter, String code) {
        //Reach a leaf
        if (node.left == null && node.right == null) {
            if (node.letter != null && node.letter.equals(letter)) {
                tempCode = code;
            }
        } else {
            if (node.left != null) {
                getCodeByTree(node.left, letter, code + "0");
            }
            if (node.right != null) {
                getCodeByTree(node.right, letter, code + "1");
            }
        }
    }

    public static int getAscii(char c) {
        return (int) c;
    }

    public static String toBinary(int decimal) {
        String result = "";
        for (int i = 0; i < 8; i++) {
            if (decimal % 2 == 0) {
                result = "0" + result;
            } else {
                result = "1" + result;
            }
            decimal /= 2;
        }
        return result;
    }
//
//
//    protected static String catStr(ArrayList<String> l) {
//        String result = "";
//        for (String s : l) {
//            result += s;
//        }
//        return result;
//    }
    /**
     * @param args
     */
//    public static void main(String[] args) throws FileNotFoundException {
//        mainEncode();
//        mainDecode();
//
//    }
//
//    public static void mainDecode() {
//        String code = FileHandler.readFile("compressed.txt", false);
//        AdaptiveHuffman ah = new AdaptiveHuffman(code.toCharArray());
//        String result = ah.decode();
//        FileHandler.writeFile("output.txt", result, false);
//    }
//
//    public static void mainEncode() {
//        String text = FileHandler.readFile("input.txt", true);
//        text = text.substring(0, text.length() );
//        AdaptiveHuffman ah = new AdaptiveHuffman(text.toCharArray());
//        ArrayList<String> code = ah.encode();
//        FileHandler.writeFile("compressed.txt", catStr(code), true);
//        FileHandler.writeFile("compressedFriendly.txt", catStr(ah.friendlyOutput), true);
//        for(int i=0;i<code.size()-1;i++){
//            System.out.println("symbol="+text.charAt(i)+"\tcode="+code.get(i));
//        }
//    }

    /* DRAW METHODS */
    public int height() {
        return height(getRoot());
    }

    private int height(Node x) {
        if (x == null) {
            return -1;
        }

        return 1 + Math.max(height(x.left), height(x.right));
    }

    /**
     * @return the root
     */
    public Node getRoot() {
        return root;
    }

    public void recursiveTurnNodeToOld() {
        recursiveTurnNodeToFalse(this.root);
    }

    private void recursiveTurnNodeToFalse(Node n) {
        if (n != null) {
            recursiveTurnNodeToFalse(n.left);
            if (n.isNew) {
                n.isNew = false;
                return;
            }
            recursiveTurnNodeToFalse(n.right);
        }
    }

    public void computeNodePositions() {
        int depth = 1;
        inorder_traversal(getRoot(), depth);
    }

//traverses tree and computes x,y position of each node, stores it in the node
    public void inorder_traversal(Node n, int depth) {
        if (n != null) {
            inorder_traversal(n.left, depth + 1); //add 1 to depth (y coordinate) 
            n.xpos = totalnodes++; //x coord is node number in inorder traversal
            n.ypos = depth; // mark y coord as depth
            inorder_traversal(n.right, depth + 1);
        }
    }

    @Override
    public String decode(String encodedMessage) {
//        codeStr = encodedMessage.toCharArray();
        return decode();
    }

    @Override
    public String encode(String message) {
        
        this.codeStr = message.toCharArray();
        alreadyExist = new ArrayList<>();
        nodeList = new ArrayList<>();
        friendlyOutput = new ArrayList<>();

        //Initialize the nyt Node.
        nytNode = new Node("NYT", 0);
        nytNode.parent = null;
        root = nytNode;
        nodeList.add(nytNode);
        
        return listToString(encode());
    }
    
    private String listToString(ArrayList<String> encoded){
        String result = "";
        for (String s : encoded) {
            result += s;
            
        }
        return result;
    }

}
