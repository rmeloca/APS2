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
            operacao.setExecutada(true);
            historia.addOperacao(operacao);
        }
        if (historia.getOperacoes().size() == agenda.getOperacoes().size()) {
            //sucesso
        } else {
            //abort
        }
    }
}
