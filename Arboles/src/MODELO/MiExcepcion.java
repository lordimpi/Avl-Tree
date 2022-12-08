/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author Santiago
 */
public class MiExcepcion {

    public MiExcepcion() {
    }

    public boolean soloLetras(String texto) {
        if (texto.length() != 1) {
            return false;
        } else {
            char letra = texto.charAt(0);
            int ascii = (int) letra;
            if (ascii <= 90 && ascii >= 65 || ascii == 165) {
                return true;
            }
            return false;
        }
    }

    public boolean soloNumeros(String texto) {
        try {
            int numero = Integer.parseInt(texto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
