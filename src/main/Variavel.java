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
public class Variavel {

    private final Character valor;
    private Status status;
    private List<Operacao> executando;
    private List<Operacao> filaEspera;

    public Variavel(Character valor) {
        this.valor = valor;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public Character getValor() {
        return valor;
    }

    public void addExecutando(Operacao operacao) {
        executando.add(operacao);
    }

    public void addFilaEspera(Operacao operacao) {
        filaEspera.add(operacao);
    }

    public void getSharedLock(Operacao operacao) {
        switch (getStatus()) {
            case UNLOCKED:
                addExecutando(operacao);
                setStatus(Status.SHARE_LOCKED);
                break;
            case SHARE_LOCKED:
                addExecutando(operacao);
                break;
            default:
                addFilaEspera(operacao);
                break;
        }
    }

    public void getExclusiveLock(Operacao operacao) {
        if (getStatus().equals(Status.UNLOCKED)) {
            setStatus(Status.EXCLUSIVE_LOCKED);
            addExecutando(operacao);
        } else {
            addFilaEspera(operacao);
        }

    }

    public List<Operacao> nextExecutando() {
        ArrayList<Operacao> list = new ArrayList<>();
        int t = executando.size();

        return list;
    }

    public void unlock(Character variavel) {
        if (getStatus().equals(Status.EXCLUSIVE_LOCKED)) {

        }
    }
}
