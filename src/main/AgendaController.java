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

    void getAgendaFromFile(String url) throws FileNotFoundException, IOException {
        String linha;
        String[] operacoes;
        Operacao operacao;
        FileReader fileReader;
        BufferedReader bufferedReader;

        agenda = new Agenda();

        fileReader = new FileReader(url);
        bufferedReader = new BufferedReader(fileReader);
        while ((linha = bufferedReader.readLine()) != null) {
            operacoes = linha.replace(" ", "").split(";");
            for (String operacaoStr : operacoes) {
                System.out.println(operacaoStr);
                if (operacaoStr.charAt(0) == 'W') {
                    operacao = new Operacao(Tipo.WRITE, new Transacao((int) operacaoStr.charAt(1)));
                } else {
                    operacao = new Operacao(Tipo.READ, new Transacao((int) operacaoStr.charAt(1)));
                }
                agenda.addOperacao(operacao);
            }
        }
    }
}
