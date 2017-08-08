/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.Aritmetric;

import java.nio.Buffer;
import java.util.ArrayList;
import static mz.com.osoma.Aritmetric.Arithmetic.decompression;
import static mz.com.osoma.Aritmetric.Arithmetic.symbols;

/**
 *
 * @author _aphie_d
 */
public class Acoding {

    public static StringBuilder binario = new StringBuilder();
    public static StringBuilder Conversaobinario = new StringBuilder();
    public static StringBuilder ConversaoDecimal = new StringBuilder();
    public static Double aux;
    public static int vezesMultiplicadas = 1;
    public static int sinal;

    public static void binario(Double number) {

        aux = number * 2;
        if (aux < 1) {
            vezesMultiplicadas = vezesMultiplicadas + 1;
            binario(aux);
        } else {
//         System.out.println("aux "+aux);
//         System.out.println("vezes "+vezesMultiplicadas);
            number = aux - Math.floor(aux);
//         System.out.println("hdjhjd"+number);

            converterBin(number);
        }
    }

    public static void converterBin(Double number) {
        int inteiro = 0;
        if (aux != 1) {
            aux = number * 2;

        //System.out.println("---" + aux);
            inteiro = (int) Math.floor(aux);
            // System.out.println("o match---" + inteiro);
            binario.append(inteiro);

            number = aux - Math.floor(aux);

            converterBin(number);

        } else {

            inteiro = (int) Math.floor(aux);
            //System.out.println("o match---" + inteiro);

            binario.append(inteiro);
            binario.append(0);
            if (binario.length() < 23) {
                int diferenca = binario.length();

                int valorAcrescentar = 23 - diferenca;

                for (int i = 0; i < valorAcrescentar; i++) {
                    binario.append(0);
                }

            }

            if (binario.charAt(0) == '1') {
                sinal = 0;
            } else {
                sinal = 1;
            }

            int expoente = 127 - vezesMultiplicadas;

            String expoenteS = Integer.toBinaryString(expoente);

//            System.out.println(sinal);
////            System.out.println(0);
//            System.out.println(expoenteS);
//            System.out.println(binario);
            Conversaobinario.append(sinal);
            Conversaobinario.append(0);
            Conversaobinario.append(expoenteS);
            Conversaobinario.append(binario);
            System.out.println("Binario----->" + Conversaobinario.toString());
//                    decimal(ConversaoDecimal.toString());
        }

    }

    public static void decimal(String decimal) {
        char divSinal = decimal.charAt(0);
        StringBuilder expoenteBinario = new StringBuilder();
        StringBuilder manticaBinario = new StringBuilder();

        for (int i = 1; i < 9; i++) {
            expoenteBinario.append(decimal.charAt(i));

        }

        for (int i = 9; i < decimal.length(); i++) {
            manticaBinario.append(decimal.charAt(i));
        }

//     Integer.parseInt(expoenteBinario.)
        int parseInt = Integer.parseInt(expoenteBinario.toString(), 2);
//        System.out.println("-----------" + parseInt);
//        System.out.println(divSinal);
//        System.out.println(expoenteBinario.toString());
//        System.out.println(manticaBinario.toString());

        int expoenteReal = parseInt - 127;

        //System.out.println(expoenteReal);

        double somatorio = 0;

        int potencia = 1;
        double resultadoPotencia;

       // System.out.println("------------------------------------------------------" + Math.pow(2, -1 * (potencia)));

        for (int i = 0; i < manticaBinario.length(); i++) {
            int bin = 0;

            //System.out.println("" + manticaBinario.charAt(i));
            resultadoPotencia = (Character.getNumericValue((manticaBinario.charAt(i)))) * (Math.pow(2, -1 * (potencia)));
            //System.out.println("---" + resultadoPotencia);
            somatorio = somatorio + resultadoPotencia;

            potencia = potencia + 1;
        //String conversaoExpoente=Integer.toBinaryString(parseInt);

        }
        char sinalDecimal = 0;
        if (decimal.charAt(0) == '1') {
            sinalDecimal = '-';
        } else {
            sinalDecimal = '+';
        }

        double representacaoDecimal = 1 + somatorio;

        double resultadoDecimal = representacaoDecimal * Math.pow(2, expoenteReal);

        System.out.println("decimal - " + resultadoDecimal);

        
        decompression(Arithmetic.probability,
                Arithmetic.symbol, resultadoDecimal, symbols.length());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//       converterBin(0.78125);
//        decimal("00111110010100000000000000000000");
    }

}
