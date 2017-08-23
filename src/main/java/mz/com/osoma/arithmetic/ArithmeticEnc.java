/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.arithmetic;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author feler
 */
public class ArithmeticEnc {

    public ArrayList<Double> probability = new ArrayList<>();
    public ArrayList<String> symbol = new ArrayList<>();
    private int length;
//    private float decimal;
    
    public ArithmeticEnc(){
        this.length = 0; 
    }

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

    private String decimalToBinary(double decimalValue) {
        int intBits = Float.floatToRawIntBits((float) decimalValue); 
        String bits = Integer.toBinaryString(intBits);
 
        System.out.println("Decimal - Binario: " +bits);
        binaryToDecimal(bits);
        return bits;
    }

    private double binaryToDecimal(String bynaryValue) {
        long l = new BigInteger(bynaryValue, 2).longValue();
        float i = Float.intBitsToFloat((int) l);
        
        System.out.println("Binario - Decimal: " +i);
        return  i;
    }

    public String encode(String texto) {
        
        this.length = texto.length();

        System.out.println("Texto Bruto (Original):" + texto);
        String symbols = texto;
        String temp1 = "";
        String temp2 = "";
        double size = 0;
        double counter = 0;
        boolean key;
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
                size = (double) (counter / symbols.length());
                probability.add(size);
            }
            counter = 0;
            temp1 = "";
        }

        sortString(symbol, probability);
        double temp = 0;
        ArrayList<Double> comulitive = new ArrayList<>();
        for (int i = 0; i < probability.size(); i++) {
            temp += probability.get(i);
            if (i == probability.size() - 1) {
                comulitive.add(1.0);
            } else {
                comulitive.add(temp);
            }
        }
        ArrayList<Double> com = new ArrayList<>();
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
        System.out.println(L);
        System.out.println(U);
        float result = (float) ((L + U) / 2);
//        this.decimal = result;
        System.out.println("Decimal Escolhido: " + result);
        return decimalToBinary(result);
    }

    public String dencode(String encoded) {
        
        float decimal = (float) binaryToDecimal(encoded);
        
        ArrayList<float> comulitive = new ArrayList<>();
        double temp = 0;
        for(int i = 0; i < probability.size(); i++) {
            temp += probability.get(i);
            if(i == probability.size()-1) {
                comulitive.add(1.0);
            }
            else comulitive.add(temp);
        } //poe as probablidades na lista comulitive//no final poe 1
        
        ArrayList<Double> com = new ArrayList<>();
        com.addAll(comulitive); //adiciona todas as probablidades na lista com
        double low = 0;
        double high = 0;
        String str = "";
        
        System.out.println(comulitive);
        for (int i = 0; i < length; i++) { //Enquanto for menor o tamanho do texto
            System.out.println(comulitive);
            int j;
            for(j = 0; j < this.symbol.size(); j++) {//enquanto for menor que o numro de caracteres que la existem
//                System.out.println("Impriminto o J: " +j);
                if(decimal < comulitive.get(j)) {//se o decimal de ponto flutuante for menor que a probabilidade
                    str += symbol.get(j);//por o caracter na variavell
                    break;//se tirar o break o indice fica fora do intervalo
                    }
                }
            System.out.println("j==="+j);
            if (j == 0) {
//                System.out.println("Entro aqui");
                //se estiver na posicao 0 da palavra introduzida faz nada
            } else {
                    low = comulitive.get(j-1); 
//                    System.out.println("Agora estou no low");
            }
            
//            System.out.println("Acumulative length "+ comulitive.size());
//            System.out.println("J= "+j);
            
            
//            high = comulitive.get(j-1);
            high = comulitive.get(j);
            for (int k = 0; k < symbol.size(); k++) {
                    comulitive.set(k, low + (high - low) * com.get(k));
            }
            System.out.println("com depois de mod sub "+comulitive);
        }
        System.out.println("Texto Descomprimido: " +str);
        
        return str;
    }

    public static void main(String[] args) {

        ArithmeticEnc a = new ArithmeticEnc();
        String texto = "abc";
        String encoded = a.encode(texto);
        System.out.println(encoded);
        
        String decoded = a.dencode(encoded);
        
        System.out.println(decoded);
    }
}
