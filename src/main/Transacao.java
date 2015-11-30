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
public class Transacao {

    private final int id;
    List<Operacao> operacoes;

    public Transacao(int id) {
        this.id = id;
    }

}
