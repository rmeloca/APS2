/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;

/**
 *
 * @author romulo
 */
public class Agenda {

    private List<Operacao> operacoes;

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
            System.out.println(operacao.getTipo().toString());
        }
    }

}
