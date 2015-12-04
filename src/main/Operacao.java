/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Timestamp;

/**
 *
 * @author romulo
 */
public class Operacao {

    private final Tipo tipo;
    private Variavel variavel;
    private final Transacao transacao;
    private boolean executada;
    private long tempoInicial;

    public Operacao(Tipo tipo, Transacao transacao) {
        this.tipo = tipo;
        this.transacao = transacao;
        variavel = null;
        executada = false;
        tempoInicial = -1;
    }

    public void setVariavel(Variavel variavel) {
        this.variavel = variavel;
    }

    public Variavel getVariavel() {
        return variavel;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public boolean isExecutada() {
        return executada;
    }

    public void setExecutada(boolean executada) {
        this.executada = executada;
    }

    public long getTempoInicial() {
        return tempoInicial;
    }

    public void setTempoInicial(long tempoInicial) {
        this.tempoInicial = tempoInicial;
    }

}
