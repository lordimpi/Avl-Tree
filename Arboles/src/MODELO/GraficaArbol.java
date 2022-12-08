/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author Santiago
 */
public class GraficaArbol extends JPanel{

    private Arbol arbol;
    private HashMap posicionNodos = null;
    private HashMap subArbolTamanio = null;
    private boolean calcular = true;
    private int padre2hijos = 20, hijo2hijo = 30;
    private Dimension vacio = new Dimension(0, 0);
    private FontMetrics fm = null;

    public GraficaArbol(Arbol arbol) {
        this.arbol = arbol;
        this.setBackground(Color.WHITE);
        posicionNodos = new HashMap();
        subArbolTamanio = new HashMap();
        calcular = true;
        if (arbol.getRaiz() != null) {
            setPreferredSize(new Dimension(arbol.getRaiz().getFactorEquilibrio() * 300, arbol.getRaiz().getFactorEquilibrio() * 50));
        }
        repaint();
    }

    public void calcularPosiciones() {
        posicionNodos.clear();
        subArbolTamanio.clear();
        Nodo raiz = arbol.getRaiz();
        if (raiz != null) {
            calcularTamanioSubArbol(raiz);
            calcularPosicion(raiz, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        fm = g.getFontMetrics();
        if (calcular) {
            calcularPosiciones();
            calcular = false;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth() / 2, padre2hijos);
        dibujarArbol(g2d, this.arbol.getRaiz(), Integer.MAX_VALUE, Integer.MAX_VALUE, fm.getLeading() + fm.getAscent());
        fm = null;
    }

    private Dimension calcularTamanioSubArbol(Nodo nodo) {
        if (nodo == null) {
            return new Dimension(0, 0);
        }

        Dimension li = calcularTamanioSubArbol(nodo.getIzq());
        Dimension ld = calcularTamanioSubArbol(nodo.getDer());

        int h = fm.getHeight() + padre2hijos + Math.max(li.height, ld.height);
        int w = li.width + hijo2hijo + ld.width;

        Dimension d = new Dimension(w, h);
        subArbolTamanio.put(nodo, d);
        return d;
    }

    private void calcularPosicion(Nodo nodo, int izquierda, int derecha, int top) {
        if (nodo == null) {
            return;
        }
        Dimension ld = (Dimension) subArbolTamanio.get(nodo.getIzq());
        if (ld == null) {
            ld = vacio;
        }
        Dimension rd = (Dimension) subArbolTamanio.get(nodo.getDer());
        if (rd == null) {
            rd = vacio;
        }
        int centro = 0;
        if (derecha != Integer.MAX_VALUE) {
            centro = derecha - rd.width - hijo2hijo / 2;
        } else if (izquierda != Integer.MAX_VALUE) {
            centro = izquierda + ld.width + hijo2hijo / 2;
        }
        int ancho = fm.stringWidth(nodo.getElemento().toString());
        posicionNodos.put(nodo, new Rectangle(centro - ancho / 2 - 3, top, ancho + 6, fm.getHeight()));
        calcularPosicion(nodo.getIzq(), Integer.MAX_VALUE, centro - hijo2hijo / 2, top + fm.getHeight() + padre2hijos);
        calcularPosicion(nodo.getDer(), centro + hijo2hijo / 2, Integer.MAX_VALUE, top + fm.getHeight() + padre2hijos);
    }

    private void dibujarArbol(Graphics2D g, Nodo nodo, int puntox, int puntoy, int yoffs) {
        if (nodo == null) {
            return;
        }
        Rectangle r = (Rectangle) posicionNodos.get(nodo);
        g.draw(r);
        g.drawString(nodo.getElemento().toString(), r.x + 3, r.y + yoffs);
        if (puntox != Integer.MAX_VALUE) {
            g.drawLine(puntox, puntoy, (int) (r.x + r.width / 2), r.y);
        }

        dibujarArbol(g, nodo.getIzq(), (int) (r.x + r.width / 2), r.y + r.height, yoffs);
        dibujarArbol(g, nodo.getDer(), (int) (r.x + r.width / 2), r.y + r.height, yoffs);

    }
}
