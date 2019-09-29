/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Alber
 */
public class Nodo {
    private String dato;
    private int pos;
    private Nodo izq,der;
    public Nodo(String dato){
        this.dato = dato;
        izq = null;
        der = null;
    }
//    public Nodo(String dato, int pos){
//        this.dato = dato;
//        this.pos = pos;
//        izq = null;
//        der = null;
//    }

    public String getDato() {
        return dato;
    }
    
    public int getPos(){
        return pos;
    }

    public Nodo getIzq() {
        return izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public void setDer(Nodo der) {
        this.der = der;
    } 
}
