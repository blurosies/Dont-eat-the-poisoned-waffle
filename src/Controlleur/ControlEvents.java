package Controlleur;

import Vue.CollecteurEvenements;
import Vue.InterfaceGraphique;
import Modele.*;
public class ControlEvents implements CollecteurEvenements{
    JeuGaufre jeu;
    InterfaceGraphique vue;
    IA russian_IA;

    public ControlEvents(JeuGaufre jeuP,IA iap){
        jeu=jeuP;
        this.russian_IA = iap;
    }

    public void annule(){
        if (jeu.peutAnnuler()) {
            jeu.annuler();
        }
    }

    public void refais(){
        if (jeu.peutRefaire()) {
            jeu.refaire();
        }
    }

    public void nouvPartie(int h, int l){
        jeu.nouvellePartie(h, l);
    }


    public void sauve(){
        jeu.sauvegarder();
    }

    public void restaure(){
        jeu.restaurer();
    }

    public void ajouteInterfaceGraphique(InterfaceGraphique v) {
		vue = v;
	}

    public void mangerG(int x, int y){
        if(!jeu.getModeIA() || (jeu.getModeIA() && (jeu.getJoueur()!=jeu.getJoueurIA()))){
            boolean ajoue = jeu.joue(y,x);
            if (!ajoue) {
                // a faire: affichage que le coup n'est pas valide
            }
        }
    }
    public void ia(){
        if(jeu.getModeIA() && jeu.getJoueur()==jeu.getJoueurIA()){
            Coup c = russian_IA.elaboreCoup();
            boolean ajoue = jeu.joue(c.getLigne(),c.getColonne());
            if (!ajoue) {
                // a faire: affichage que le coup n'est pas valide
            }
        }
    }

    public void toggleIA(){
        jeu.setAI();
    }
    public void toggleJoueur(){
        jeu.changeJoueur();
    }

    public void incrementer(){
        jeu.increm();
    }

    public void decrementer(){
        jeu.decrem();
    }

}