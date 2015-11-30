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

    public void addOperacao(Operacao operacao) {
        operacoes.add(operacao);
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

}
