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
    private List<Transacao> transacoes;
    private List<Variavel> variaveis;
    private int indice;

    public Agenda() {
        operacoes = new ArrayList<>();
        transacoes = new ArrayList<>();
        variaveis = new ArrayList<>();
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

    public void addTransacao(Transacao transacao) {
        if (!transacoes.contains(transacao)) {
            transacoes.add(transacao);
        }
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public Transacao getTransacao(int id) {
        for (Transacao transacao : getTransacoes()) {
            if (transacao.getId() == id) {
                return transacao;
            }
        }
        return null;
    }

    public void addVariavel(Variavel variavel) {
        if (!variaveis.contains(variavel)) {
            variaveis.add(variavel);
        }
    }

    public List<Variavel> getVariaveis() {
        return variaveis;
    }

    public Variavel getVariavel(Character valor) {
        for (Variavel variavel : getVariaveis()) {
            if (variavel.getValor().equals(valor)) {
                return variavel;
            }
        }
        return null;
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

    public Operacao getNextOperacao() {
        Operacao operacao = null;
        for (int i = 0; i < operacoes.size(); i++) {
            operacao = operacoes.get(i);
            if (!operacao.isExecutada() && !operacao.getTransacao().estaNaEspera()) {
                indice = i;
                operacao.getTransacao().setIndice(i);
                return operacao;
            }
        }
        return operacao;
    }
}
