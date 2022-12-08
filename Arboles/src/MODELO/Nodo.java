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
public class Nodo<T> 
{

    private T elemento;
    private Nodo<T> izq;
    private Nodo<T> der;
    private Nodo<T> padre;
    private int factorEquilibrio;
    private int nivel;

    public Nodo(T elemento) 
    {
        this.elemento = elemento;
        izq = null;
        der = null;
    }

    public Nodo(T elemento, Nodo izq, Nodo der) {
        this.elemento = elemento;
        this.izq = izq;
        this.der = der;
        factorEquilibrio = 0;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public Nodo<T> getIzq() {
        return izq;
    }

    public void setIzq(Nodo<T> izq) {
        this.izq = izq;
    }

    public Nodo<T> getDer() {
        return der;
    }

    public void setDer(Nodo<T> der) {
        this.der = der;
    }

    public int getFactorEquilibrio() {
        return factorEquilibrio;
    }

    public void setFactorEquilibrio(int factorEquilibrio) {
        this.factorEquilibrio = factorEquilibrio;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean esHoja() {
        if (izq == null && der == null) {
            return true;
        }
        return false;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo getTio() {
        try {
            if (this.getPadre().getPadre() != null) {
                if (this.getPadre() == this.getPadre().getPadre().getIzq() && this.getPadre().getPadre().getDer() != null) {
                    return this.getPadre().getPadre().getDer();
                }
                if (this.getPadre() == this.getPadre().getPadre().getDer() && this.getPadre().getPadre().getIzq() != null) {
                    return this.getPadre().getPadre().getIzq();
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
