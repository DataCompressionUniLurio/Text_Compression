/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.huffman;

/**
 *
 * @author feler
 */
public class SortedList implements Operacoes {

    private Link primeiro;
    private int totalDeElementos;

    public void LinkList() {
    }

    public boolean isEmpty() {
        return (primeiro == null);
    }

    private boolean posicaoOcupada(int posicao) {
        return posicao >= 0 && posicao < this.totalDeElementos;
    }


    @Override
    public void insert(HuffmanTree elemento) {

        Link actual = primeiro;
        Link anterior = null;

        while (actual != null && actual.dData.getRoot().frequency < elemento.getRoot().frequency) {
            anterior = actual;
            actual = actual.proxima;
        }

        Link nova = new Link(elemento);
        if (anterior == null) {
            primeiro = nova;
        } else {
            anterior.proxima = nova;
        }
        nova.proxima = actual;
        this.totalDeElementos++;
    }

    @Override
    public HuffmanTree remove() {

        if (!this.posicaoOcupada(0)) {
            throw new IllegalArgumentException("Posição não existe");
        }

        HuffmanTree temp = primeiro.dData;
        primeiro = primeiro.proxima;
        totalDeElementos--;
        return temp;

    }



    @Override
    public int size() {
        return this.totalDeElementos;
    }

    @Override
    public String toString() {
        // Verificando se a Lista está vazia
        if (this.totalDeElementos == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        Link atual = primeiro;
        // Percorrendo até o penúltimo elemento.
        for (int i = 0; i < this.totalDeElementos - 1; i++) {
            builder.append(atual.dData);
            builder.append(", ");
            atual = atual.proxima;
        }
        // último elemento
        builder.append(atual.dData);
        builder.append("]");
        return builder.toString();
    }

}
