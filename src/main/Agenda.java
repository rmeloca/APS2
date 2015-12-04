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

    /**
     * Método construtor inicializa os atributos da agenda.
     */
    public Agenda() {
        operacoes = new ArrayList<>();
        transacoes = new ArrayList<>();
        variaveis = new ArrayList<>();
        indice = 0;
    }

    /**
     * Obtém a posição da agenda que está sendo executada.
     *
     * @return
     */
    public int getIndice() {
        return indice;
    }

    /**
     * Atualiza a posição da agenda que está sendo executada.
     *
     * @param indice
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }

    /**
     * Adiciona uma operação à lista de operações. O método adiciona na ordem em
     * que está o arquivo, da qual nunca é modificada.
     *
     * @param operacao
     */
    public void addOperacao(Operacao operacao) {
        if (!operacoes.contains(operacao)) {
            operacoes.add(operacao);
        }
    }

    /**
     * Retorna lista com as operações a serem executadas na agenda. Executa-se
     * de modo que não sabe-se as operações futuras.
     *
     * @return
     */
    public List<Operacao> getOperacoes() {
        return operacoes;
    }

    /**
     * Adiciona uma transação à agenda. Para controle interno.
     *
     * @param transacao
     */
    public void addTransacao(Transacao transacao) {
        if (!transacoes.contains(transacao)) {
            transacoes.add(transacao);
        }
    }

    /**
     * Obtém a lista de transações da agenda.
     *
     * @return
     */
    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    /**
     * Obtém a referência do objeto Transação que possui o id parametrizado.
     *
     * @param id
     * @return
     */
    public Transacao getTransacao(int id) {
        for (Transacao transacao : getTransacoes()) {
            if (transacao.getId() == id) {
                return transacao;
            }
        }
        return null;
    }

    /**
     * Adiciona uma variável na agenda.
     *
     * @param variavel
     */
    public void addVariavel(Variavel variavel) {
        if (!variaveis.contains(variavel)) {
            variaveis.add(variavel);
        }
    }

    /**
     * Retorna a lista de variáveis.
     *
     * @return
     */
    public List<Variavel> getVariaveis() {
        return variaveis;
    }

    /**
     * Obtém a referência da variável dado seu valor. Recurso sobre o qual
     * gerenciam-se os locks do 2PL. Utiliza-se um Character, poderiam ser
     * tuplas, celulas ou tabelas.
     *
     * @param valor
     * @return
     */
    public Variavel getVariavel(Character valor) {
        for (Variavel variavel : getVariaveis()) {
            if (variavel.getValor().equals(valor)) {
                return variavel;
            }
        }
        return null;
    }

    /**
     * Imprime uma agenda ou história.
     */
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

    /**
     * Retorna a próxima operação que pode ser executada, levando em
     * consideração a lista de espera.
     *
     * @return
     */
    public Operacao getNextOperacao() {
        Operacao operacao = null;
        for (int i = 0; i < operacoes.size(); i++) {
            operacao = operacoes.get(i);
            if (!operacao.isExecutada() && !operacao.getTransacao().estaNaEspera()) {
                indice = i;
                return operacao;
            }
        }
        return operacao;
    }
}
