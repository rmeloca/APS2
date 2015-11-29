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
public class Variavel {

    private final Character valor;
    private Status status;
    List<Operacao> executando;
    List<Operacao> filaEspera;

    public Variavel(Character valor) {
        this.valor = valor;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

}
