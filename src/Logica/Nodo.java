/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;

/**
 *
 * @author Alber
 */
public class Nodo {
    private String dato;
    public int pos;
    private Nodo izq,der;
    public boolean anulable;
    public ArrayList<Integer> pPos, uPos;
    
    public Nodo(String dato){
        this.dato = dato;
        izq = null;
        der = null;
        pPos = new ArrayList<>();
        uPos = new ArrayList<>();
    }

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
