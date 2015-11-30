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

    public boolean estaNaEspera() {
        Status s;
        for (Operacao op1 : operacoes) {
            for (Operacao op2 : op1.getVariavel().filaEspera) {
                if (op1.equals(op2)) {
                    return true;
                }
            }
        }

        return false;
    }

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

    public int getId() {
        return id;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

}
