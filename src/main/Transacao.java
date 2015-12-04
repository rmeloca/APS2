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
public class Transacao {

    private final int id;
    private List<Operacao> operacoes;
    private int indice;

    public Transacao(int id) {
        this.id = id;
        operacoes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public List<Operacao> getOperacoes() {
        return operacoes;
    }


    public boolean estaNaEspera() {
        Operacao operacao;

        operacao = operacoes.get(indice);
        if (!operacao.getTipo().equals(Tipo.COMMIT)) {
            operacao.getVariavel().getFilaEspera().contains(operacao);
            return true;
        }
        return false;
    }

    public void addOperacao(Operacao operacao) {
        operacoes.add(operacao);
    }

    @Override
    public boolean equals(Object obj) {
        return ((Transacao) obj).id == id;
    }

    void unlockAll() {
        unlockAll(true);
    }

    void unlockAll(boolean foramExecutadas) {
        for (Operacao operacao : operacoes) {
            operacao.getVariavel().unlock(operacao);
            operacao.setExecutada(foramExecutadas);
        }
    }

}
