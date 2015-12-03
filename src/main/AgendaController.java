/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author romulo
 */
public class AgendaController {
    
    private Agenda agenda;
    private Agenda historia;
    
    public Agenda getAgenda() {
        return agenda;
    }
    
    public Agenda getHistoria() {
        return historia;
    }
    
    void parseAgendaFromFile(String url) throws FileNotFoundException, IOException {
        String linha;
        String[] operacoes;
        Operacao operacao;
        FileReader fileReader;
        BufferedReader bufferedReader;
        Transacao transacao;
        int idTransacao;
        Variavel variavel;
        Character valor;
        
        agenda = new Agenda();
        agenda.setIndice(0);
        
        fileReader = new FileReader(url);
        bufferedReader = new BufferedReader(fileReader);
        while ((linha = bufferedReader.readLine()) != null) {
            operacoes = linha.replace(" ", "").split(";");
            for (int i = 0; i < operacoes.length; i++) {
                String operacaoStr = operacoes[i];
                idTransacao = Character.getNumericValue(operacaoStr.charAt(1));
                transacao = agenda.getTransacao(idTransacao);
                if (transacao == null) {
                    transacao = new Transacao(idTransacao);
                    transacao.setIndice(i);
                    agenda.addTransacao(transacao);
                }
                switch (operacaoStr.charAt(0)) {
                    case 'W':
                        operacao = new Operacao(Tipo.WRITE, transacao);
                        valor = operacaoStr.charAt(3);
                        variavel = agenda.getVariavel(valor);
                        if (variavel == null) {
                            variavel = new Variavel(valor);
                            agenda.addVariavel(variavel);
                        }
                        operacao.setVariavel(variavel);
                        break;
                    case 'R':
                        operacao = new Operacao(Tipo.READ, transacao);
                        valor = operacaoStr.charAt(3);
                        variavel = agenda.getVariavel(valor);
                        if (variavel == null) {
                            variavel = new Variavel(valor);
                            agenda.addVariavel(variavel);
                        }
                        operacao.setVariavel(variavel);
                        break;
                    default:
                        operacao = new Operacao(Tipo.COMMIT, transacao);
                        break;
                }
                transacao.addOperacao(operacao);
                agenda.addOperacao(operacao);
            }
        }
    }
    
    public void executar() {
        historia = new Agenda();
        Operacao operacao;
        
        while ((operacao = agenda.getNextOperacao()) != null) {
            switch (operacao.getTipo()) {
                case COMMIT:
                    operacao.setExecutada(true);
                    operacao.getTransacao().unlockAll();
                    break;
                case READ:
                    operacao.getVariavel().getSharedLock(operacao);
                    break;
                case WRITE:
                    operacao.getVariavel().getExclusiveLock(operacao);
                    break;
            }
            historia.addOperacao(operacao);
        }
        if (historia.getOperacoes().size() == agenda.getOperacoes().size()) {
            //sucesso
        } else {
            //abort
        }
    }

    public void abortar(Transacao t) {
        ArrayList<Variavel> listVar = new ArrayList<>();
        ArrayList<Integer> listPos = new ArrayList<>();
        ArrayList<Transacao> listTran = new ArrayList<>();
        int qtdVar;
        int pos;
        Transacao tran;
        Variavel v1;
        Variavel v2;
        Operacao op;
        Agenda nova = new Agenda();

        pos = historia.getOperacoes().size();
        nova.setIndice(historia.getIndice());
        listTran.add(t);

        //Verifica quais variaveis foram alteradas
        for (int i = 0; i < pos; i++) {
            op = historia.getOperacoes().get(i);
            if ((op.getTransacao().getId() == t.getId()) && (op.getTipo().toString().equals("W"))) {
                if (!(listVar.contains(op.getVariavel()))) {
                    listVar.add(op.getVariavel());
                    listPos.add(i);
                }
            }

        }
        qtdVar = listVar.size();

        //Verifica quais transacoes foram afetadas
        for (int i = 0; i < qtdVar; i++) {

            for (int j = listPos.get(i); j < pos; j++) {
                v1 = historia.getOperacoes().get(j).getVariavel();
                v2 = listVar.get(i);
                if (v1.toString().equals(v2.toString())) {
                    tran = historia.getOperacoes().get(j).getTransacao();
                    tran.unlockAll(false);
                    listTran.add(tran);
                }

            }
        }

        //Realocando op abortadas
        for (int i = 0; i < pos; i++) {
            op = historia.getOperacoes().get(i);
            if ((listTran.contains(op.getTransacao()))) {
                historia.getOperacoes().remove(i);
                i--;
                pos--;
//altero ind na agenda            
            }
        }

    }
}
