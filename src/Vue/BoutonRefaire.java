package Vue;

import javax.swing.*;

import Patterns.Observateur;
import Modele.*;

public class BoutonRefaire extends JButton implements Observateur {
    JeuGaufre jeu;
    
    BoutonRefaire(JeuGaufre jeuP){
        jeu = jeuP;
		setText("");
		jeu.ajouteObservateur(this);
		setEnabled(jeu.peutRefaire());
    }

    public void miseAJour(){
        setEnabled(jeu.peutRefaire());
    }
}