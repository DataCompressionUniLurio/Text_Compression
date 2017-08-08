/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.Aritmetric;


import java.util.ArrayList;
import static mz.com.osoma.Aritmetric.Acoding.Conversaobinario;
import static mz.com.osoma.Aritmetric.Acoding.binario;
import static mz.com.osoma.Aritmetric.Acoding.converterBin;
import static mz.com.osoma.Aritmetric.Acoding.decimal;

/**
 *
 * @author _aphie_d
 */
public class Arithmetic {

    public static ArrayList<Double> probability = new ArrayList<Double>();
    public static ArrayList<String> symbol = new ArrayList<String>();
    public static double result;
    public static String symbols;

    public static void sortString(ArrayList<String> c, ArrayList<Double> p) {
        for (int i = 0; i < c.size(); i++) {
            String s = "";
            s += c.get(i);
            for (int j = i + 1; j < c.size(); j++) {
                if (s.compareTo(c.get(j).toString()) > 0) {
                    s = "";
                    s += c.get(j);
                    c.set(j, c.get(i));
                    c.set(i, s);
                    double d = p.get(j);
                    p.set(j, p.get(i));
                    p.set(i, d);
                }
            }
        }
    }

    public static void compression(String word) {
        symbols=word;
        System.out.println("word a comprimir "+word);
        String temp1 = "";
        String temp2 = "";
        double size = 0;
        double counter = 0;
        boolean key;
//        ArrayList<Double> probability = new ArrayList<Double>();
//        ArrayList<String> symbol = new ArrayList<String>();
        for (int i = 0; i < symbols.length(); i++) {
            temp1 += symbols.charAt(i);
            key = symbol.contains(temp1);
            if (key) {
            } else {
                symbol.add(temp1);
                for (int j = 0; j < symbols.length(); j++) {
                    temp2 += symbols.charAt(j);
                    if (temp1.contains(temp2)) {
                        counter++;
                    }
                    temp2 = "";
                }
                size = (counter / symbols.length());
                probability.add(size);
            }
            counter = 0;
            temp1 = "";
        }
        sortString(symbol, probability);
        double temp = 0;
        ArrayList<Double> comulitive = new ArrayList<Double>();
        for (int i = 0; i < probability.size(); i++) {
            temp += probability.get(i);
            if (i == probability.size() - 1) {
                comulitive.add(1.0);
            } else {
                comulitive.add(temp);
            }
        }
        ArrayList<Double> com = new ArrayList<Double>();
        com.addAll(comulitive);
        double L = 0;
        double U = 0;
        for (int i = 0; i < symbols.length(); i++) {
            String str = "";
            str += symbols.charAt(i);
            int index = symbol.indexOf(str);
            if (index == 0) {
            } else {
                L = comulitive.get(index - 1);
            }
            U = comulitive.get(index);
            for (int k = 0; k < symbol.size(); k++) {
                comulitive.set(k, L + (U - L) * com.get(k));
            }
        }
        result = (L + U) / 2;
        System.out.println("intervalo de compressao--->" + result);
        binario(result);

    }

    public static void decompression(ArrayList<Double> prob,
            ArrayList<String> characters, Double res, int length) {
        double temp = 0.0;
        ArrayList<Double> comulitive = new ArrayList<Double>();
        for (int i = 0; i < prob.size(); i++) {
            temp += prob.get(i);
            if (i == prob.size() - 1) {
                comulitive.add(1.0);
            } else {
                comulitive.add(temp);
            }
        }
        ArrayList<Double> com = new ArrayList<Double>();
        com.addAll(comulitive);
        double L = 0;
        double U = 0;
        String str = "";
        for (int i = 0; i < length; i++) {
            int j;
            for (j = 0; j < characters.size(); j++) {
                if (res < comulitive.get(j)) {
                    str += characters.get(j);
                    break;
                }
            }
            if (j == 0) {
            } else {
                L = comulitive.get(j - 1);
            }
            U = comulitive.get(j);
            for (int k = 0; k < characters.size(); k++) {
                comulitive.set(k, L + (U - L) * com.get(k));
            }
        }
        System.out.println("Decompression----" + str);
    }

    public static void main(String[] args) {
        String input = "aab";
       compression(input);
     decimal(Conversaobinario.toString());
//        decompression(probability,
//                symbol, result, input.length());
        
       // converterBin(0.59375);

    }
}
