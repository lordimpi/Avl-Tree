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
public class ABB extends Arbol {

    public Nodo insertar(Nodo arbol, Comparable elemento) {
        if (arbol == null) {
            arbol = new Nodo(elemento);
        } else if (elemento.compareTo(arbol.getElemento()) < 0) {
            arbol.setIzq(insertar(arbol.getIzq(), elemento));
        } else if (elemento.compareTo(arbol.getElemento()) > 0) {
            arbol.setDer(insertar(arbol.getDer(), elemento));
        }
        arbol.setFactorEquilibrio(mFE(factorEquilibrio(arbol.getIzq()), factorEquilibrio(arbol.getDer())));
        return arbol;
    }

    public boolean remover1(Nodo nodo) {
        Nodo izqH = nodo.getPadre().getIzq();
        Nodo derH = nodo.getPadre().getDer();
        if (izqH == nodo) {
            nodo.getPadre().setIzq(null);
            return true;
        }
        if (derH == nodo) {
            nodo.getPadre().setDer(null);
            return true;
        }
        return false;
    }

    public boolean remover2(Nodo nodo) {
        Nodo actual = nodo.getIzq() != null ? nodo.getIzq() : nodo.getDer();
        if (nodo == raiz) {
            raiz = actual;
            return true;
        }
        if (nodo.getPadre().getIzq() != null) {
            Nodo izqH = nodo.getPadre().getIzq();
            if (izqH == nodo) {
                nodo.getPadre().setIzq(actual);
                actual.setPadre(nodo.getPadre());
                nodo.setDer(null);
                nodo.setIzq(null);
                return true;
            }
        }
        if (nodo.getPadre().getDer() != null) {
            Nodo derH = nodo.getPadre().getDer();
            if (derH == nodo) {
                nodo.getPadre().setDer(actual);
                actual.setPadre(nodo.getPadre());
                nodo.setDer(null);
                nodo.setIzq(null);
                return true;
            }
        }
        return false;
    }

    public boolean remover3(Nodo nodo) {
        Nodo masDerecho = masDer(nodo.getIzq());
        if (masDerecho != null) {
            nodo.setElemento(masDerecho.getElemento());
            return remover(masDerecho);
        }
        return false;
    }
}
