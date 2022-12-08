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
public class AVL extends Arbol {

    public Nodo insertar(Nodo arbol, Comparable elemento) {
        if (arbol == null) {
            arbol = new Nodo(elemento, null, null);
        } else if (elemento.compareTo(arbol.getElemento()) < 0) {
            arbol.setIzq(insertar(arbol.getIzq(), elemento));
            if (factorEquilibrio(arbol.getDer()) - factorEquilibrio(arbol.getIzq()) == -2) {
                if ((elemento.compareTo(arbol.getIzq().getElemento()) < 0)) {
                    arbol = RotacionSimpleIzq(arbol);
                } else {
                    arbol = RotacionDobleIzq_Der(arbol);
                }
            }
        } else if (elemento.compareTo(arbol.getElemento()) > 0) {
            arbol.setDer(insertar(arbol.getDer(), elemento));
            if (factorEquilibrio(arbol.getDer()) - factorEquilibrio(arbol.getIzq()) == 2) {
                if (elemento.compareTo(arbol.getDer().getElemento()) > 0) {
                    arbol = RotacionSimpleDer(arbol);
                } else {
                    arbol = RotacionDobleDer_Izq(arbol);
                }
            }
        } else {;// En el caso de que sean valores duplicados no hace nada
        }
        arbol.setFactorEquilibrio(mFE(factorEquilibrio(arbol.getIzq()), factorEquilibrio(arbol.getDer())));
        return arbol;
    }

    private static Nodo RotacionSimpleIzq(Nodo arbol) {
        Nodo subArbol = arbol.getIzq();
        arbol.setIzq(subArbol.getDer());
        subArbol.setDer(arbol);
        arbol.setFactorEquilibrio(mFE(factorEquilibrio(arbol.getIzq()), factorEquilibrio(arbol.getDer())));
        subArbol.setFactorEquilibrio(mFE(factorEquilibrio(subArbol.getIzq()), arbol.getFactorEquilibrio()));
        return subArbol;
    }

    private static Nodo RotacionSimpleDer(Nodo subArbol) {
        Nodo arbol = subArbol.getDer();
        subArbol.setDer(arbol.getIzq());
        arbol.setIzq(subArbol);
        subArbol.setFactorEquilibrio(mFE(factorEquilibrio(subArbol.getIzq()), factorEquilibrio(subArbol.getDer())));
        arbol.setFactorEquilibrio(mFE(factorEquilibrio(arbol.getDer()), subArbol.getFactorEquilibrio()));
        return arbol;
    }

    private static Nodo RotacionDobleIzq_Der(Nodo arbol) {
        arbol.setIzq(RotacionSimpleDer(arbol.getIzq()));
        return RotacionSimpleIzq(arbol);
    }

    private static Nodo RotacionDobleDer_Izq(Nodo subArbol) {
        subArbol.setDer(RotacionSimpleIzq(subArbol.getDer()));
        return RotacionSimpleDer(subArbol);
    }

    public Nodo balancear(Nodo arbol) {
        if (arbol != null) {
            if (factorEquilibrio(arbol.getDer()) - factorEquilibrio(arbol.getIzq()) == -2) {
                if (factorEquilibrio(arbol.getIzq().getDer()) - factorEquilibrio(arbol.getIzq().getIzq()) <= 0) {
                    arbol = RotacionSimpleIzq(arbol);
                } else if (factorEquilibrio(arbol.getIzq().getDer()) - factorEquilibrio(arbol.getIzq().getIzq()) > 0) {
                    arbol = RotacionDobleDer_Izq(arbol);
                }
            } else if (factorEquilibrio(arbol.getDer()) - factorEquilibrio(arbol.getIzq()) == 2) {
                if (factorEquilibrio(arbol.getDer().getDer()) - factorEquilibrio(arbol.getDer().getIzq()) >= 0) {
                    arbol = RotacionSimpleDer(arbol);
                } else if (factorEquilibrio(arbol.getDer().getDer()) - factorEquilibrio(arbol.getDer().getIzq()) < 0) {
                    arbol = RotacionDobleIzq_Der(arbol);
                }
            }
            if (arbol.getIzq() != null) {
                arbol.setIzq(balancear(arbol.getIzq()));
            }
            if (arbol.getDer() != null) {
                arbol.setDer(balancear(arbol.getDer()));
            }
            arbol.setFactorEquilibrio(mFE(factorEquilibrio(arbol.getIzq()), factorEquilibrio(arbol.getDer())));
        }
        return arbol;
    }

    public boolean remover1(Nodo nodo) {
        Nodo izqH = nodo.getPadre().getIzq();
        Nodo derH = nodo.getPadre().getDer();
        if (izqH == nodo) {
            nodo.getPadre().setIzq(null);
            raiz = balancear(raiz);
            return true;
        }
        if (derH == nodo) {
            nodo.getPadre().setDer(null);
            raiz = balancear(raiz);
            return true;
        }
        return false;
    }

    public boolean remover2(Nodo nodo) {
        Nodo actual = nodo.getIzq() != null ? nodo.getIzq() : nodo.getDer();
        if (nodo == raiz) {
            raiz = actual;
            raiz = balancear(raiz);
            return true;
        }
        if (nodo.getPadre().getIzq() != null) {
            Nodo izqH = nodo.getPadre().getIzq();
            if (izqH == nodo) {
                nodo.getPadre().setIzq(actual);
                actual.setPadre(nodo.getPadre());
                nodo.setDer(null);
                nodo.setIzq(null);
                raiz = balancear(raiz);
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
                raiz = balancear(raiz);
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
