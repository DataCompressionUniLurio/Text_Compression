/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.shannonfano;

/**
 *
 * @author feler
 */
public class CharacterFreq {

    public String character;
    public int frequency;

    public CharacterFreq(String character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public String toString() {
        return "[" + character + ", " + frequency + "]";
    }

}
