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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romulo
 */
public class Main {

    public static void main(String[] args) {
        String linha;
        String[] operacoes;
        Operacao operacao;
        FileReader fileReader;
        BufferedReader bufferedReader;
        Agenda agenda;

        agenda = new Agenda();
        try {
            fileReader = new FileReader(Main.class.getResource("/arquivos/schedule1.txt").getFile());
            bufferedReader = new BufferedReader(fileReader);
            while (true) {
                linha = bufferedReader.readLine();
                if (linha == null) {
                    break;
                }
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

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void lockShared(Transacao transacao, Character variavel) {

    }

    void lockExclusive(Transacao transacao, Character variavel) {

    }

    void lock(Character variavel) {

    }

    //fila-wait
    
    void unlock(Character variavel) {

    }

}
