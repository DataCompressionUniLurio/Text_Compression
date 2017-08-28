/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.rle;

import mz.com.osoma.compressable;

/**
 *
 * @author feler
 */
public class RLE implements compressable {

    StringBuilder str;

    public char sp;
    public char sq;
    public char sr;

    public int index;
    private String outEncoding;

    public RLE() {
        index = 0;
        this.outEncoding = "";
    }

    public RLE(String s) {
        this.str = new StringBuilder(s);

        index = 0;
        sp = str.charAt(index++);
        sq = str.charAt(index++);
        sr = str.charAt(index);
        this.outEncoding = "";
    }

    public String encode() {

        while (index < str.length()) {

            if (beginRun()) {
                Character ch = findLongestRun();
                this.outEncoding += outputRun(ch.lenght, ch.symbol);
            } else {
                Character ch = findLongestNonRun();
                this.outEncoding += outputNonRun(ch.lenght, ch.symbol);
            }

        }

        return this.outEncoding;
    }

    public boolean beginRun() {
        return (sp == sq && sq == ' ') || (sp == sq && sq == sr);
    }

    public void shift1() {
        this.sp = this.sq;
        this.sq = this.sr;

        if (index + 1 < this.str.length()) {
            sr = str.charAt(++index);
        } else {
            sr = '\u0000';
        }
    }

    public void shift2() {
        this.sp = this.sr;

        if ((index + 1) < str.length()) {
            sq = str.charAt(++index);
        } else {
            sq = '\u0000';
        }

        if ((index + 1) < str.length()) {
            sr = str.charAt(++index);
        } else {
            sr = '\u0000';
        }
    }

    public Character findLongestRun() {

        int runLength = 2;
        char symbol;

        if ((sp == sq && sq == ' ') && (sq != sr)) {
            symbol = ' ';
        } else {

            while ((index < str.length()) && (sp == sq && sq == sr)) {
                if (str.charAt(index) != sp) {
                    sr = str.charAt(index);
                    break;
                }
                runLength = runLength + 1;
                sr = str.charAt(index++);
            }
            symbol = sp;
        }

        if (index < str.length()) {
            shift2();
        }

        return new Character(new StringBuilder().append(symbol).toString(), runLength);
    }

    private String outputRun(int runlenght, String symbol) {

        StringBuilder output = new StringBuilder("r");
        if (symbol.charAt(0) == ' ') {
            output.append(runlenght);
        } else {
            output.append(runlenght);
            output.append(symbol);
        }
        return output.toString();
    }

    private Character findLongestNonRun() {
        StringBuilder word = new StringBuilder();

        word.append(sp);
        word.append(sq);

        int length = 2;

        while ((index < str.length()) && ((sp != sq) || (sp == sq && sq != ' ' && sq != sr))) {
            length++;
            shift1();
            word.append(sq);
        }

        if (index < str.length()) {
            int aux = length;
            length = length - 2;
            word.deleteCharAt(aux - 1);
            word.deleteCharAt(aux - 2);

            if (((sp == sq && sq == sr) && (index == str.length() - 1))) {
                index++;
            }

            if (((sp == sq && sq == sr) && (index == str.length())) && sp != '\u0000') {
                index = index - 1;
            }
        }

        return new Character(word.toString(), length);
    }

    public String outputNonRun(int lenght, String word) {
        return "n" + lenght + word;
    }

    public String decode() {

        int i = 0;
        char controSymbol = readNextSymbol(i);

        String result = "";

        System.out.println(this.outEncoding);
        while (i < this.outEncoding.length()) {
            char nextSymbol = '\u0000';
            if (controSymbol == 'r') {
                nextSymbol = readNextSymbol(i + 2);

                if (isControlSymbol(nextSymbol)) {
                    result += output(Integer.parseInt(readNextSymbol(i + 1) + ""), ' ');
                    i += 2;
                } else {
                    result += output(Integer.parseInt(readNextSymbol(i + 1) + ""), nextSymbol);
                    i += 2;
                }
            } else {
                result += outputNonRun(Integer.parseInt(readNextSymbol(i + 1) + ""), i + 2);
                i += Integer.parseInt(readNextSymbol(i + 1) + "") + 2;
                nextSymbol = readNextSymbol(i);
            }

            if (!isControlSymbol(nextSymbol)) {
                i++;
                nextSymbol = readNextSymbol(i);
            }
            controSymbol = nextSymbol;
        }

        return result;

    }

    public boolean isControlSymbol(char c) {
        return (c == 'n' || c == 'r');
    }

    public char readNextSymbol(int start) {
        char result = '\u0000';

        if (!this.outEncoding.isEmpty() && start + 1 <= this.outEncoding.length()) {
            result = this.outEncoding.charAt(start);
        }

        return result;
    }

    public String output(int k, char symbol) {
        String result = "";
        for (int i = 0; i < k; i++) {
            result += symbol;
        }
        return result;
    }

    public String outputNonRun(int k, int start) {
        String result = "";
        for (int i = k; i > 0; i--) {
            result += this.outEncoding.charAt(start);
            start++;
        }
        return result;
    }

    @Override
    public String encode(String message) {

        this.str = new StringBuilder(message);

        index = 0;
        sp = str.charAt(index++);
        sq = str.charAt(index++);
        sr = str.charAt(index);
        this.outEncoding = "";

        return encode();

    }

    @Override
    public String decode(String encodedMessage) {
        this.outEncoding = encodedMessage;
        return decode();
    }

}
