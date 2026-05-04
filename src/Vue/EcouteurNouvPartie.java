package Vue;

import java.awt.event.ActionListener;

import Modele.*;

import java.awt.event.ActionEvent;

public class EcouteurNouvPartie implements ActionListener {
    JeuGaufre jeu;
    CollecteurEvenements control;

    EcouteurNouvPartie(CollecteurEvenements controlP, JeuGaufre jeuP) {
        control=controlP;
        jeu=jeuP;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        int h=jeu.g.getligne();
        int l=jeu.g.getcolonne();
        control.nouvPartie(h,l);
    }
}
