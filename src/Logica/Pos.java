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
public class Pos {

    Nodo raiz;
    ArrayList[] next;
    
    public Pos(Nodo raiz, int tPos){
        this.raiz = raiz;
        anulable(raiz);
        primeraPos(raiz);
        ultimaPos(raiz);
        sPos(tPos);
    }

    private void anulable(Nodo raiz) {
        String dato = raiz.getDato();
        if (raiz.getIzq() != null) {
            anulable(raiz.getIzq());
        }
        if (raiz.getDer() != null) {
            anulable(raiz.getDer());
        }
        switch (dato) {
            case "|":
                raiz.anulable = raiz.getIzq().anulable || raiz.getDer().anulable;
                break;
            case ".":
                raiz.anulable = raiz.getIzq().anulable && raiz.getDer().anulable;
                break;
            case "+":
                raiz.anulable = false;
                break;
            case "*":
                raiz.anulable = true;
                break;
            case "?":
                raiz.anulable = true;
                break;
            case "&":
                raiz.anulable = true;
                break;
            default:
                raiz.anulable = false;
                break;
        }
    }

    private void primeraPos(Nodo raiz) {
        String dato = raiz.getDato();
        if (raiz.getIzq() != null) {
            primeraPos(raiz.getIzq());
        }
        if (raiz.getDer() != null) {
            primeraPos(raiz.getDer());
        }
        switch (dato) {
            case "|":
                raiz.pPos = raiz.getIzq().pPos;
                raiz.pPos.addAll(raiz.getDer().pPos);
                break;
            case ".":
                if (raiz.getIzq().anulable) {
                    raiz.pPos = raiz.getIzq().pPos;
                    raiz.pPos.addAll(raiz.getDer().pPos);
                } else {
                    raiz.pPos = raiz.getIzq().pPos;
                }
                break;
            case "+":
                raiz.pPos = raiz.getIzq().pPos;
                break;
            case "*":
                raiz.pPos = raiz.getIzq().pPos;
                break;
            case "?":
                raiz.pPos = raiz.getIzq().pPos;
                break;
            case "&":
                raiz.pPos = new ArrayList<>();
                break;
            default:
                raiz.pPos = new ArrayList<>();
                raiz.pPos.add(raiz.pos);
                break;
        }
    }
    
    private void ultimaPos(Nodo raiz) {
        String dato = raiz.getDato();
        if (raiz.getIzq() != null) {
            ultimaPos(raiz.getIzq());
        }
        if (raiz.getDer() != null) {
            ultimaPos(raiz.getDer());
        }
        switch (dato) {
            case "|":
                raiz.uPos = raiz.getIzq().uPos;
                raiz.uPos.addAll(raiz.getDer().uPos);
                break;
            case ".":
                if (raiz.getDer().anulable) {
                    raiz.uPos = raiz.getIzq().uPos;
                    raiz.uPos.addAll(raiz.getDer().uPos);
                } else {
                    raiz.uPos = raiz.getDer().uPos;
                }
                break;
            case "+":
                raiz.uPos = raiz.getIzq().uPos;
                break;
            case "*":
                raiz.uPos = raiz.getIzq().uPos;
                break;
            case "?":
                raiz.uPos = raiz.getIzq().uPos;
                break;
            case "&":
                raiz.uPos = new ArrayList<>();
                break;
            default:
                raiz.uPos = new ArrayList<>();
                raiz.uPos.add(raiz.pos);
                break;
        }
    }
    
    private void sPos(int tPos){
        next = new ArrayList[tPos];
        for (int i = next.length-1; i >= 0; i--){
            next[i] = new ArrayList<>();
        }
        siguientePos(raiz);
    }
    
    private void siguientePos(Nodo raiz){
        String dato = raiz.getDato();
        if (raiz.getIzq() != null) {
            siguientePos(raiz.getIzq());
        }
        if (raiz.getDer() != null) {
            siguientePos(raiz.getDer());
        }
        switch (dato) {
            case ".":
                ArrayList<Integer> upos = raiz.getIzq().uPos;
                ArrayList<Integer> ppos = raiz.getDer().pPos;
                for (int i = 0; i < upos.size(); i++){
                    for (int j = 0; j < ppos.size(); j++){
                        next[upos.get(i)].add(ppos.get(j));
                    }
                }
                break;
            case "+":
                ArrayList<Integer> mppos = raiz.getIzq().pPos;
                ArrayList<Integer> mupos = raiz.getIzq().uPos;
                for (int i = 0; i < mupos.size(); i++){
                    for (int j = 0; j < mppos.size(); j++){
                        next[mupos.get(i)].add(mppos.get(j));
                    }
                }
                break;
            case "*":
                ArrayList<Integer> appos = raiz.getIzq().pPos;
                ArrayList<Integer> aupos = raiz.getIzq().uPos;
                for (int i = 0; i < aupos.size(); i++){
                    for (int j = 0; j < appos.size(); j++){
                        next[aupos.get(i)].add(appos.get(j));
                    }
                }
                break;
        }
    }
    
    
    
    public Nodo getArbolPos(){
        return raiz;
    }
    
    public ArrayList[] getSiguientePos(){
        return next;
    }
    
}
