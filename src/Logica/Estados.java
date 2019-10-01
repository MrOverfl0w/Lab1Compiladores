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
public class Estados {

    ArrayList[] estados = new ArrayList[5];
    String[][] TranD = new String[5][5];
    char letra = 'A';
    boolean[] marcado = new boolean[5];
    char[] nombre = new char[5];

    public Estados(Nodo raiz, ArrayList alfabeto, ArrayList<Nodo> Tpos, ArrayList[] sgtpos) {
        inicializar();
        Tabla(alfabeto);
        armarEstados(raiz, alfabeto, Tpos, sgtpos);
    }

    private void inicializar() {
        char let = letra;
        for (int i = 0; i < 5; i++) {
            estados[i] = new ArrayList();
            marcado[i] = false;
            nombre[i] = let++;
            for (int j = 0; j < 5; j++) {
                TranD[i][j] = "";
            }
        }
    }

    private void Tabla(ArrayList alfabeto) {
        for (int i = 0; i < alfabeto.size(); i++) {
            TranD[0][0] ="ESTADOS";
            TranD[0][i + 1] = alfabeto.get(i).toString();
        }
        TranD[1][0] = letra+"";
        nombre[0] = letra;
    }

    private void armarEstados(Nodo raiz, ArrayList alfabeto, ArrayList<Nodo> Tpos, ArrayList[] sgtpos) {
        ArrayList<Integer> A = raiz.pPos;
        ArrayList<Integer> R = null;
        estados[0].add(A);
        while (!completado(estados)) {
            int k = 1;
            for (int i = 0; i < alfabeto.size(); i++) {
                for (int j = 0; j < A.size(); j++) {
                    if (Tpos.get(A.get(j)).equals(alfabeto.get(i))) {
                        R.addAll(sgtpos[Tpos.get(A.get(j)).getPos()]);
                    }
                }
                if (!esta(R)) {
                    estados[k].add(R);
                    k++;
                    marcado[k] = false;
                    nombre[k] = letra;
                    letra++;
                    TranD[k][i + 1]=letra+"";
                } else {
                    TranD[k][i + 1] = nombre[k]+"";
                }
            }
            marcado[k - 1] = true;

        }
    }

    private boolean completado(ArrayList[] estados) {
        for (int i = 0; i < estados.length; i++) {
            if (!marcado[i] && !estados[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean esta(ArrayList<Integer> R) {
        for (int i = 0; i < estados.length; i++) {
            if (estados[i].equals(R) && !estados[i].isEmpty()) {
                return true;
            }
        }
        return false;
    }

}
