/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulo
 */
public class Agenda {

    private List<Operacao> operacoes;
    private int indice;

    public Agenda() {
        operacoes = new ArrayList<>();
        indice = 0;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void addOperacao(Operacao operacao) {
        if (!operacoes.contains(operacao)) {
            operacoes.add(operacao);
        }
    }

    public List<Operacao> getOperacoes() {
        return operacoes;
    }

    public void imprimir() {
        for (Operacao operacao : operacoes) {
            System.out.print(operacao.getTipo().toString());
            System.out.print(operacao.getTransacao().getId());
            if (!operacao.getTipo().equals(Tipo.COMMIT)) {
                System.out.print("(" + operacao.getVariavel().getValor() + ")");
            }
            System.out.print("; ");
        }
        System.out.println();
    }

    public Operacao getNext() {

        return null;
    }
}
