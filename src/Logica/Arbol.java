/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Alber
 */
public class Arbol {

    private Nodo raiz = null;
    private ArrayList nodos;
    private ArrayList exp;
    private ArrayList pila;
    private int alt;
    ArrayList<Nodo> p1 = new ArrayList<>();

    public Arbol(ArrayList exp) {
        this.exp = exp;
        String[] cadena = Arrays.stream(exp.toArray()).toArray(String[]::new);
//        raiz = arbol(cadena);
//        iniciarNodos();
        exp.add(0, "(");
        exp.add(")");
        raiz = generarArbol(null);
    }

//    private void iniciarNodos() {
//        nodos = new ArrayList();
//        int i = 1;
//        for (Iterator it = exp.iterator(); it.hasNext();) {
//            String dato = String.valueOf(it.next());
//            if (!(dato.equals("(") || dato.equals(")"))) {
//                if (isSym(dato)) {
//                    nodos.add(new Nodo(dato));
//                } else {
//                    nodos.add(new Nodo(dato, i++));
//                }
//            }
//        }
//    }
    private Nodo generarArbol(Nodo p) {
        int abre, cierra;
        pila = new ArrayList();
        int j = 0;
        
        String[] cadena = Arrays.stream(exp.toArray()).toArray(String[]::new);

        for (int i = cadena.length-1; i >= 0; i--) {
            String dato = cadena[i];
            if (dato.equals(")")) {
                pila.add(i);
            } else if (dato.equals("(")) {
                cierra = (int) pila.remove(pila.size() - 1);
                abre = i;
                String[] cadena2 = Arrays.copyOfRange(cadena, abre+1, cierra);
                p = arbol(cadena2);
                p1.add(p);
                ArrayList temp = new ArrayList();
                temp.addAll(exp.subList(0, i));
                temp.add(p.toString());
                temp.addAll(exp.subList(cierra+1, exp.size()));
                exp = temp;
                return generarArbol(p);
            }
        }
        return p;
    }

    private boolean isSym(String str) {
        return str.equals("&") | str.equals("|") | str.equals("*") | str.equals("+") | str.equals("?")
                || str.equals("(") || str.equals(")") || str.equals(".");
    }

    private Nodo arbol(String[] cadena) {
        return alternancia(cadena);
    }

    private Nodo alternancia(String[] cadena) {
        for (int i = cadena.length-1; i >= 0; i--) {
            if (cadena[i].equals("|")) {
                Nodo alt = new Nodo(cadena[i]);
                alt.setIzq(arbol(Arrays.copyOfRange(cadena, 0, i)));
                alt.setDer(arbol(Arrays.copyOfRange(cadena, i + 1, cadena.length)));
                return alt;
            }
        }
        return concat(cadena);
    }

    private Nodo concat(String[] cadena) {
        for (int i = cadena.length-1; i >= 0; i--) {
            if (cadena[i].equals(".")) {
                Nodo conc = new Nodo(cadena[i]);
                conc.setIzq(arbol(Arrays.copyOfRange(cadena, 0, i)));
                conc.setDer(arbol(Arrays.copyOfRange(cadena, i + 1, cadena.length)));
                return conc;
            }
        }
        return astAddInt(cadena);
    }

    private Nodo astAddInt(String[] cadena) {
        for (int i = cadena.length-1; i >= 0; i--) {
            if (cadena[i].equals("*") || cadena[i].equals("+") || cadena[i].equals("?")) {
                Nodo nodo = new Nodo(cadena[i]);
                nodo.setIzq(arbol(Arrays.copyOfRange(cadena, 0, i)));
                return nodo;
            }
        }
        return hoja(cadena);
    }

    private Nodo hoja(String[] cadena) {
        if (cadena[0].length() > 1){
            return arbolito(cadena[0]);
        }
        return new Nodo(cadena[0]);
    }
    
    private Nodo arbolito(String nodito){
        for (int i = 0; i < p1.size(); i++){
            if (p1.get(i).toString().equals(nodito)){
                return p1.get(i);
            }
        }
        return null;
    }
    
    
    
    public Nodo getRaiz(){
        return raiz;
    }
    
    private void altura (Nodo aux,int nivel)  {
        if (aux != null) {    
            altura(aux.getIzq(),nivel+1);
            alt = nivel;
            altura(aux.getDer(),nivel+1);
        }
    }
   
    public int getAltura(){
        altura(raiz,1);
        return alt;
    }
}
