package Modele;

import java.util.ArrayList;

public class Coup {
    private int ligne;
    private int colonne;
    ArrayList <Case>listeCase;

    Coup(int lignep,int colonnep){
        ligne=lignep;
        colonne=colonnep;
        listeCase=new ArrayList<>();
        listeCase.add(new Case(lignep, colonnep));
    }
    public int getLigne(){
        return ligne;
    }
    public int getColonne(){
        return colonne;
    }
    public void ajouteCase(int ligne,int colonne){
        listeCase.add(new Case(ligne, colonne));
    }

}
