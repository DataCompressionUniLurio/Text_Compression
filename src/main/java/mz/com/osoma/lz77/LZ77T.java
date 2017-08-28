/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.lz77;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import mz.com.osoma.FileHandler;
import mz.com.osoma.compressable;

/**
 *
 * @author feler
 */
public class LZ77T implements compressable {

    public static final int DEFAULT_BUFF_SIZE = 1024;
    protected int mBufferSize;
    protected Reader mIn;
    protected PrintWriter mOut;
    protected String out;
    protected StringBuffer mSearchBuffer;

    public LZ77T() {
        this(DEFAULT_BUFF_SIZE);
    }

    public LZ77T(int buffSize) {
        mBufferSize = buffSize;
        mSearchBuffer = new StringBuffer(mBufferSize);
    }

    private void trimSearchBuffer() {
        if (mSearchBuffer.length() > mBufferSize) {
            mSearchBuffer
                    = mSearchBuffer.delete(0, mSearchBuffer.length() - mBufferSize);
        }
    }

    @Override
    public String decode(String encodedMessage) {
        
        try {
            mIn = new BufferedReader(new FileReader("c://Aulas//input.txt.lz77"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LZ77T.class.getName()).log(Level.SEVERE, null, ex);
        }
    StreamTokenizer st = new StreamTokenizer(mIn);

    st.ordinaryChar((int)' ');
    st.ordinaryChar((int)'.');
    st.ordinaryChar((int)'-');
    st.ordinaryChar((int)'\n');
    st.wordChars((int)'\n', (int)'\n');
    st.wordChars((int)' ', (int)'}');

    int offset, length;
        try {
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                switch (st.ttype) {
                    case StreamTokenizer.TT_WORD:
                        mSearchBuffer.append(st.sval);
                        System.out.print(st.sval);
                        // Adjust search buffer size if necessary
                        trimSearchBuffer();
                        break;
                    case StreamTokenizer.TT_NUMBER:
                        offset = (int)st.nval; // set the offset
                        st.nextToken(); // get the separator (hopefully)
                        if (st.ttype == StreamTokenizer.TT_WORD) {
                            // we got a word instead of the separator,
                            // therefore the first number read was actually part of a word
                            mSearchBuffer.append(offset+st.sval);
                            System.out.print(offset+st.sval);
                            break; // break out of the switch
                        }
                        // if we got this far then we must be reading a
                        // substitution pointer
                        st.nextToken(); // get the length
                        length = (int)st.nval;
                        // output substring from search buffer
                        String output = mSearchBuffer.substring(offset, offset+length);
                        System.out.print(output);
                        mSearchBuffer.append(output);
                        // Adjust search buffer size if necessary
                        trimSearchBuffer();
                        break;
                    default:
                        // consume a '~'
                }
            }   } catch (IOException ex) {
            Logger.getLogger(LZ77T.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            mIn.close();
        } catch (IOException ex) {
            Logger.getLogger(LZ77T.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public String encode(String message) {

        // set up input and output
        out = "";

        int nextChar;
        String currentMatch = "";
        int matchIndex = 0, tempIndex = 0;

        for (int i = 0; i < message.length(); i++) {
            nextChar = (int) message.charAt(i);

            // look in our search buffer for a match
            tempIndex = mSearchBuffer.indexOf(currentMatch + (char) nextChar);
      // if match then append nextChar to currentMatch
            // and update index of match
            if (tempIndex != -1) {
                currentMatch += (char) nextChar;
                matchIndex = tempIndex;
            } else {
                // found longest match, now should we encode it?
                String codedString
                        = "~" + matchIndex + "~" + currentMatch.length() + "~" + (char) nextChar;
                String concat = currentMatch + (char) nextChar;
                // is coded string shorter than raw text?
                if (codedString.length() <= concat.length()) {
//	  mOut.print(codedString);
                    out += codedString;

                    mSearchBuffer.append(concat); // append to the search buffer
                    currentMatch = "";
                    matchIndex = 0;
                } else {
	  // otherwise, output chars one at a time from
                    // currentMatch until we find a new match or
                    // run out of chars
                    currentMatch = concat;
                    matchIndex = -1;
                    while (currentMatch.length() > 1 && matchIndex == -1) {
//	    mOut.print(currentMatch.charAt(0));
                        out += currentMatch.charAt(0);

                        mSearchBuffer.append(currentMatch.charAt(0));
                        currentMatch = currentMatch.substring(1, currentMatch.length());
                        matchIndex = mSearchBuffer.indexOf(currentMatch);
                    }
                }
                // Adjust search buffer size if necessary
                trimSearchBuffer();
            }
        }

        // flush any match we may have had when EOF encountered
        if (matchIndex != -1) {
            String codedString
                    = "~" + matchIndex + "~" + currentMatch.length();
            if (codedString.length() <= currentMatch.length()) {
                out += "~" + matchIndex + "~" + currentMatch.length();
            } else {
                out += currentMatch;
            }
        }

        return out;
    }

    
}
