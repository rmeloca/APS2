/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author romulo
 */
public class Main {

    public static void main(String[] args) {
        AgendaController agendaController = new AgendaController();
        try {
            agendaController.parseAgendaFromFile(JOptionPane.showInputDialog(null, "Caminho absoluto do arquivo"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        agendaController.getAgenda().imprimir();
        agendaController.executar();
        if (agendaController.getHistoria() != null) {
            agendaController.getHistoria().imprimir();
        }
    }
}
