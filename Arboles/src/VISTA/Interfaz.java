/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import MODELO.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.ScrollPane;

/**
 *
 * @author Santiago
 */
public class Interfaz extends JFrame {

    private static final long serialVersionUID = 1L;
    private Arbol objArbol;
    private GraficaArbol ga;
    private MiExcepcion mie;
    private final JPanel contentPane;
    private JTextField txt_Insertar;
    private JTextField txt_Eliminar;
    private JTextField txt_Buscar;
    private ButtonGroup bg_Arbol = new ButtonGroup();
    private ButtonGroup bg_Tipo = new ButtonGroup();

    public Interfaz() {
        setResizable(false);
        setTitle("arboles binarios de busqueda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 578, 429);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JRadioButton rdb_Abb = new JRadioButton("ABB");
        rdb_Abb.setBounds(6, 34, 60, 23);
        contentPane.add(rdb_Abb);

        JLabel lblArbol = new JLabel("Arbol");
        lblArbol.setBounds(10, 11, 46, 14);
        contentPane.add(lblArbol);

        JRadioButton rdb_AVL = new JRadioButton("AVL");
        rdb_AVL.setBounds(6, 60, 60, 23);
        contentPane.add(rdb_AVL);

        JLabel lblTipoArbol = new JLabel("Tipo arbol");
        lblTipoArbol.setBounds(66, 11, 74, 14);
        contentPane.add(lblTipoArbol);

        JRadioButton rdb_Letra = new JRadioButton("Letras");
        rdb_Letra.setBounds(68, 34, 87, 23);
        contentPane.add(rdb_Letra);

        JRadioButton rdb_Numero = new JRadioButton("Numeros");
        rdb_Numero.setBounds(68, 60, 87, 23);
        contentPane.add(rdb_Numero);

        txt_Insertar = new JTextField();
        txt_Insertar.setBounds(161, 29, 86, 20);
        contentPane.add(txt_Insertar);
        txt_Insertar.setColumns(10);

        txt_Eliminar = new JTextField();
        txt_Eliminar.setBounds(161, 61, 86, 20);
        contentPane.add(txt_Eliminar);
        txt_Eliminar.setColumns(10);

        txt_Buscar = new JTextField();
        txt_Buscar.setBounds(367, 29, 86, 20);
        contentPane.add(txt_Buscar);
        txt_Buscar.setColumns(10);

        bg_Arbol.add(rdb_AVL);
        bg_Arbol.add(rdb_Abb);

        bg_Tipo.add(rdb_Letra);
        bg_Tipo.add(rdb_Numero);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setBounds(6, 315, 443, 80);
        contentPane.add(textArea);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBounds(6, 89, 546, 220);
        contentPane.add(scrollPane);

        JButton btn_Insertar = new JButton("Insertar");
        btn_Insertar.setBounds(257, 28, 89, 23);
        btn_Insertar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                try {
                    if ((!rdb_Abb.isSelected() && !rdb_AVL.isSelected()) || (!rdb_Letra.isSelected() && !rdb_Numero.isSelected())) {
                        JOptionPane.showMessageDialog(null, "Seleccion un tipo de arbol antes de insertar", "Error", 0);
                    } else {
                        if (txt_Insertar.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Campos vacios", "Error", 0);
                        } else if (objArbol.getRaiz() == null) {
                            if (rdb_Abb.isSelected()) {
                                objArbol = new ABB();
                                if (rdb_Numero.isSelected()) {
                                    String texto = txt_Insertar.getText();
                                    if (mie.soloNumeros(texto)) {
                                        objArbol.insertar(Integer.parseInt(texto));
                                        rdb_Abb.setEnabled(false);
                                        rdb_AVL.setEnabled(false);
                                        rdb_Letra.setEnabled(false);
                                        rdb_Numero.setEnabled(false);
                                        ga = new GraficaArbol(objArbol);
                                        scrollPane.add(ga);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "El formato del valor a ingresar no es correcto, intentelo nuevamente", "Error", 0);
                                    }
                                } else if (rdb_Letra.isSelected()) {
                                    String texto = txt_Insertar.getText();
                                    texto = texto.toUpperCase();
                                    if (mie.soloLetras(texto)) {
                                        objArbol.insertar(texto);
                                        rdb_Abb.setEnabled(false);
                                        rdb_AVL.setEnabled(false);
                                        rdb_Letra.setEnabled(false);
                                        rdb_Numero.setEnabled(false);
                                        ga = new GraficaArbol(objArbol);
                                        scrollPane.add(ga);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "El formato del valor a ingresar no es correcto, intentelo nuevamente", "Error", 0);
                                    }
                                }
                            } else if (rdb_AVL.isSelected()) {
                                objArbol = new AVL();
                                if (rdb_Numero.isSelected()) {
                                    String texto = txt_Insertar.getText();
                                    if (mie.soloNumeros(texto)) {
                                        objArbol.insertar(Integer.parseInt(texto));
                                        rdb_Abb.setEnabled(false);
                                        rdb_AVL.setEnabled(false);
                                        rdb_Letra.setEnabled(false);
                                        rdb_Numero.setEnabled(false);
                                        ga = new GraficaArbol(objArbol);
                                        scrollPane.add(ga);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "El formato del valor a ingresar no es correcto, intentelo nuevamente", "Error", 0);
                                    }
                                } else if (rdb_Letra.isSelected()) {
                                    String texto = txt_Insertar.getText();
                                    texto = texto.toUpperCase();
                                    if (mie.soloLetras(texto)) {
                                        objArbol.insertar(texto);
                                        rdb_Abb.setEnabled(false);
                                        rdb_AVL.setEnabled(false);
                                        rdb_Letra.setEnabled(false);
                                        rdb_Numero.setEnabled(false);
                                        ga = new GraficaArbol(objArbol);
                                        scrollPane.add(ga);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "El formato del valor a ingresar no es correcto, intentelo nuevamente", "Error", 0);
                                    }
                                }
                            }
                        } else {
                            if (rdb_Numero.isSelected()) {
                                String texto = txt_Insertar.getText();
                                if (mie.soloNumeros(texto)) {
                                    objArbol.insertar(Integer.parseInt(texto));
                                    ga = new GraficaArbol(objArbol);
                                    scrollPane.add(ga);
                                } else {
                                    JOptionPane.showMessageDialog(null, "El formato del valor a ingresar no es correcto, intentelo nuevamente", "Error", 0);
                                }
                            } else if (rdb_Letra.isSelected()) {
                                String texto = txt_Insertar.getText();
                                texto = texto.toUpperCase();
                                if (mie.soloLetras(texto)) {
                                    objArbol.insertar(texto);
                                    ga = new GraficaArbol(objArbol);
                                    scrollPane.add(ga);
                                } else {
                                    JOptionPane.showMessageDialog(null, "El formato del valor a ingresar no es correcto, intentelo nuevamente", "Error", 0);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                txt_Insertar.setText("");
            }
        });
        contentPane.add(btn_Insertar);

        JButton btn_Eliminar = new JButton("Eliminar");
        btn_Eliminar.setBounds(257, 60, 89, 23);
        btn_Eliminar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                try {
                    if ((!rdb_Abb.isSelected() && !rdb_AVL.isSelected()) || (!rdb_Letra.isSelected() && !rdb_Numero.isSelected()) || objArbol.getRaiz() == null) {
                        JOptionPane.showMessageDialog(null, "Seleccion un tipo de arbol antes de eliminar", "Error", 0);
                    } else {
                        if (txt_Eliminar.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Campos vacios", "Error", 0);
                        } else {
                            if (rdb_Numero.isSelected()) {
                                String texto = txt_Eliminar.getText();
                                if (mie.soloNumeros(texto)) {
                                    objArbol.remover(Integer.parseInt(texto));
                                    ga = new GraficaArbol(objArbol);
                                    scrollPane.add(ga);
                                } else {
                                    JOptionPane.showMessageDialog(null, "El formato del valor a ingresar no es correcto, intentelo nuevamente", "Error", 0);
                                }
                            } else if (rdb_Letra.isSelected()) {
                                String texto = txt_Eliminar.getText();
                                texto = texto.toUpperCase();
                                if (mie.soloLetras(texto)) {
                                    objArbol.remover(texto);
                                    ga = new GraficaArbol(objArbol);
                                    scrollPane.add(ga);
                                } else {
                                    JOptionPane.showMessageDialog(null, "El formato del valor a ingresar no es correcto, intentelo nuevamente", "Error", 0);
                                }
                            }
                        }
                    }
                    if (objArbol.getRaiz() == null) {
                        rdb_Abb.setEnabled(true);
                        rdb_AVL.setEnabled(true);
                        rdb_Letra.setEnabled(true);
                        rdb_Numero.setEnabled(true);
                    }
                } catch (Exception e) {
                    System.out.println("eliminar" + e.getMessage());
                }
                txt_Eliminar.setText("");
            }
        });
        contentPane.add(btn_Eliminar);

        JButton btn_Buscar = new JButton("Buscar");
        btn_Buscar.setBounds(463, 28, 89, 23);
        btn_Buscar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                try {
                    if ((!rdb_Abb.isSelected() && !rdb_AVL.isSelected()) || (!rdb_Letra.isSelected() && !rdb_Numero.isSelected()) || objArbol.getRaiz() == null) {
                        JOptionPane.showMessageDialog(null, "Seleccion un tipo de arbol antes de insertar", "Error", 0);
                    } else {
                        if (txt_Buscar.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Campos vacios", "Error", 0);
                        } else {
                            if (rdb_Numero.isSelected()) {
                                String texto = txt_Buscar.getText();
                                if (mie.soloNumeros(texto)) {
                                    textArea.setText(objArbol.buscarNodo(Integer.parseInt(texto)));
                                } else {
                                    JOptionPane.showMessageDialog(null, "El formato del valor a ingresar no es correcto, intentelo nuevamente", "Error", 0);
                                }
                            } else if (rdb_Letra.isSelected()) {
                                String texto = txt_Buscar.getText();
                                texto = texto.toUpperCase();
                                if (mie.soloLetras(texto)) {
                                    textArea.setText(objArbol.buscarNodo(texto));
                                } else {
                                    JOptionPane.showMessageDialog(null, "El formato del valor a ingresar no es correcto, intentelo nuevamente", "Error", 0);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                txt_Buscar.setText("");
            }
        });
        contentPane.add(btn_Buscar);

        JButton btnImprimir = new JButton("Imprimir");
        btnImprimir.setBounds(463, 60, 89, 23);
        btnImprimir.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                try {
                    if ((!rdb_Abb.isSelected() && !rdb_AVL.isSelected()) || (!rdb_Letra.isSelected() && !rdb_Numero.isSelected()) || objArbol.getRaiz() == null) {
                        JOptionPane.showMessageDialog(null, "Imposible generar los recorridos del arbol o no existe", "Error", 0);
                    } else {
                        textArea.setText("Preorden:" + objArbol.preOrden(objArbol.getRaiz())
                                + "\nInOrden:" + objArbol.inOrden(objArbol.getRaiz())
                                + "\nPostOrden:" + objArbol.postOrden(objArbol.getRaiz())
                                + "\nPor Niveles:" + objArbol.porNiveles());
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        contentPane.add(btnImprimir);

        JButton btn_Limpiar = new JButton("Limpiar");
        btn_Limpiar.setBounds(463, 372, 89, 23);
        btn_Limpiar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                try {
                    if (objArbol.getRaiz() != null) {
                        rdb_Abb.setEnabled(true);
                        rdb_AVL.setEnabled(true);
                        rdb_Letra.setEnabled(true);
                        rdb_Numero.setEnabled(true);
                        bg_Arbol.clearSelection();
                        bg_Tipo.clearSelection();
                        objArbol = new AVL();
                        textArea.setText("");
                        ga = new GraficaArbol(objArbol);
                        scrollPane.add(ga);
                        JOptionPane.showMessageDialog(null, "El arbol fue eliminado correctamente", "Notificacion", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "fue imposible limpiar/eliminar el arbol o no existe", "Error", 0);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "fue imposible limpiar/eliminar el arbol o no existe", "Error", 0);
                }
            }
        });
        contentPane.add(btn_Limpiar);

        JButton btn_Generar = new JButton("Generar");
        btn_Generar.setBounds(463, 315, 89, 23);
        btn_Generar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (objArbol.getRaiz() == null) {
                    JOptionPane.showMessageDialog(null, "Imposible generar el archivo o no existe informacion", "Error", 0);
                } else {
                    File f = new File("arbolSalida.txt");
                    PrintStream miconsola = null;
                    try {
                        miconsola = new PrintStream(f);
                        JOptionPane.showMessageDialog(null, "el archivo fue creado correctamente", "Notificacion", 1);
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(null, "Imposible generar el archivo o no existe informacion", "Error", 0);
                    }
                    System.setOut(miconsola);
                    objArbol.infoArbol();
                }
            }
        });
        contentPane.add(btn_Generar);

        objArbol = new AVL();
        mie = new MiExcepcion();
    }
}
