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
    private ArrayList pila;

    public Arbol(ArrayList exp) {
        this.exp = exp;
        iniciarNodos();
        generarArbol();
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
                    nodos.add(new Nodo(dato, i++));
                }
            }
        }
    }
    
    private void generarArbol(){
        int abre, cierra, i = 0;
        pila = new ArrayList();
        
        //Se prepara la expresion colocandole parentesis al principio
        //y al final para que el mismo código sirva para todo
        exp.add(0, "("); exp.add(")");
        
        for (Iterator it = exp.iterator(); it.hasNext();){
            String dato = String.valueOf(it.next());
            if (dato.equals("(")){
                pila.add(i);
            }else if (dato.equals(")")){
                abre = (int) pila.remove(pila.size()-1);
                cierra = i;
            }
            i++;
        }
    }

    private boolean isSym(String str) {
        return str.equals("&") | str.equals("|") | str.equals("*") | str.equals("+") | str.equals("?")
                || str.equals("(") || str.equals(")") || str.equals(".");
    }

}
