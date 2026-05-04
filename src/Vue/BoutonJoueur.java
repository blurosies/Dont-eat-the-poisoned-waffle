package Vue;

import javax.swing.*;

import Patterns.Observateur;
import Modele.*;

public class BoutonJoueur extends JButton implements Observateur {
    JeuGaufre jeu;
    
    BoutonJoueur(JeuGaufre jeuP){
        jeu = jeuP;
		setText("");
		jeu.ajouteObservateur(this);
		setEnabled(jeu.peutRedimensionner());
    }

    public void miseAJour(){
        setEnabled(jeu.peutRedimensionner());
    }
}
