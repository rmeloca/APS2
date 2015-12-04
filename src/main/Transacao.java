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
        indice = 0;
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
        return !operacao.getTipo().equals(Tipo.COMMIT) && operacao.getVariavel().getFilaEspera().contains(operacao);
    }

    public void addOperacao(Operacao operacao) {
        operacoes.add(operacao);
    }

    @Override
    public boolean equals(Object obj) {
        return ((Transacao) obj).id == id;
    }

    private List<Variavel> getVariaveis() {
        List<Variavel> variaveis = new ArrayList<>();
        for (Operacao operacao : operacoes) {
            if (!variaveis.contains(operacao.getVariavel())) {
                variaveis.add(operacao.getVariavel());
            }
        }
        return variaveis;
    }

    private Operacao getMaiorPrioridade(Variavel variavel) {
        Operacao menor = null;
        for (Operacao operacao : operacoes) {
            if (operacao.getVariavel().equals(variavel)) {
                if (operacao.getTipo().equals(Tipo.WRITE)) {
                    return operacao;
                } else if (menor == null) {
                    menor = operacao;
                }
            }
        }
        return menor;
    }

    void unlockAll() {
        unlockAll(true);
    }

    void unlockAll(boolean foramExecutadas) {
        Operacao operacao;
        for (Variavel variavel : getVariaveis()) {
            operacao = getMaiorPrioridade(variavel);
            if (!operacao.getTipo().equals(Tipo.COMMIT)) {
                operacao.getVariavel().unlock(operacao);
            }
            operacao.setExecutada(foramExecutadas);
        }
    }

}
