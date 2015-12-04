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
        executando = new ArrayList<>();
        filaEspera = new ArrayList<>();
        status = Status.UNLOCKED;
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

    public List<Operacao> getFilaEspera() {
        return filaEspera;
    }

    boolean getSharedLock(Operacao operacao) {
        switch (getStatus()) {
            case UNLOCKED:
                addExecutando(operacao);
                setStatus(Status.SHARE_LOCKED);
                break;
            case SHARE_LOCKED:
                addExecutando(operacao);
                break;
            default:
                operacao.setTempoInicial(System.currentTimeMillis());
                addFilaEspera(operacao);
                return false;
        }
        return true;
    }

    boolean getExclusiveLock(Operacao operacao) {
        if (getStatus().equals(Status.UNLOCKED)) {
            setStatus(Status.EXCLUSIVE_LOCKED);
            addExecutando(operacao);
            return true;
        } else {
            operacao.setTempoInicial(System.currentTimeMillis());
            addFilaEspera(operacao);
        }
        return false;
    }

    //se for preciso manter a assinatura: transacao.getOperacoes.get(transacao.getIndice());
    public void unlock(Operacao operacao) {
        if (!executando.contains(operacao)) {
            return;
        }
        if (getStatus().equals(Status.EXCLUSIVE_LOCKED)) {
            executando.clear();
            setStatus(Status.UNLOCKED);
        } else if (getStatus().equals(Status.SHARE_LOCKED)) {
            executando.remove(operacao);
            //equals?
            //haverá apenas um objeto de mesmo tipo, então não será um problema remoção por comparação
            if (executando.isEmpty()) {
                setStatus(Status.UNLOCKED);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        return ((Variavel) obj).getValor().equals(valor);
    }

}
