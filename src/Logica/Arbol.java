/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Alber
 */
public class Arbol {

    private Nodo raiz;
    private ArrayList nodos;
    private ArrayList exp;

    public Arbol(ArrayList exp) {
        this.exp = exp;
        iniciarNodos();
    }

    private void iniciarNodos() {
        nodos = new ArrayList();
        int i = 1;
        for (Iterator it = exp.iterator(); it.hasNext();) {
            String dato = String.valueOf(it.next());
            if (!(dato.equals("(") || dato.equals(")"))) {
                if (isSym(dato)) {
                    nodos.add(new Nodo(dato));
                } else {
                    System.out.println(dato + " " + i);
                    nodos.add(new Nodo(dato, i++));
                }
            }
        }
    }

    private boolean isSym(String str) {
        return str.equals("|") | str.equals("*") | str.equals("+") | str.equals("?")
                || str.equals("(") || str.equals(")") || str.equals(".");
    }

}
