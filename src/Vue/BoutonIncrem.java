package Vue;

import javax.swing.*;

import Patterns.Observateur;
import Modele.*;

public class BoutonIncrem extends JButton implements Observateur {
    JeuGaufre jeu;
    
    BoutonIncrem(JeuGaufre jeuP){
        jeu = jeuP;
		setText("");
		jeu.ajouteObservateur(this);
		setEnabled(jeu.peutRedimensionner());
    }

    public void miseAJour(){
        setEnabled(jeu.peutRedimensionner());
    }
}
