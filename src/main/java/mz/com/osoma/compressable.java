/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma;

/**
 *
 * @author feler
 */
public interface compressable {
    public String decode(String encodedMessage);
    public String encode(String message);
}
