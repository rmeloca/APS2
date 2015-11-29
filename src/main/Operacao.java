/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author romulo
 */
public class Operacao {

    private final Tipo tipo;
    private Variavel variavel;
    private final Transacao transacao;

    public Operacao(Tipo tipo, Transacao transacao) {
        this.tipo = tipo;
        this.transacao = transacao;
        variavel = null;
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
}
