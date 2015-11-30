/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author romulo
 */
public enum Tipo {
    READ, WRITE, COMMIT;

    @Override
    public String toString() {
        String retorno = null;
        switch (this) {
            case READ:
                retorno = "R";
                break;
            case WRITE:
                retorno = "W";
                break;
            case COMMIT:
                retorno = "C";
                break;
        }
        return retorno;
    }
}
