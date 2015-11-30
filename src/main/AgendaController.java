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

    void getAgendaFromFile(String url) throws FileNotFoundException, IOException {
        String linha;
        String[] operacoes;
        Operacao operacao;
        FileReader fileReader;
        BufferedReader bufferedReader;
        Transacao transacao;

        agenda = new Agenda();
        agenda.setIndice(0);

        fileReader = new FileReader(url);
        bufferedReader = new BufferedReader(fileReader);
        while ((linha = bufferedReader.readLine()) != null) {
            operacoes = linha.replace(" ", "").split(";");
            for (int i = 0; i < operacoes.length; i++) {
                String operacaoStr = operacoes[i];
                transacao = new Transacao((int) operacaoStr.charAt(1));
                transacao.setIndice(i);
                switch (operacaoStr.charAt(0)) {
                    case 'W':
                        operacao = new Operacao(Tipo.WRITE, transacao);
                        operacao.setVariavel(new Variavel(operacaoStr.charAt(3)));
                        break;
                    case 'R':
                        operacao = new Operacao(Tipo.READ, transacao);
                        operacao.setVariavel(new Variavel(operacaoStr.charAt(3)));
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
        for (int i = 0; i < agenda.getOperacoes().size(); i++) {
            operacao = agenda.getOperacoes().get(i);
            historia.addOperacao(operacao);
        }
    }

    public Agenda getHistoria() {
        return historia;
    }
}
