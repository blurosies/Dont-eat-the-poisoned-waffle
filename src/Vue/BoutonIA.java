package Vue;

import javax.swing.*;

import Patterns.Observateur;
import Modele.*;

public class BoutonIA extends JToggleButton implements Observateur {
    JeuGaufre jeu;
    
    BoutonIA(JeuGaufre jeuP){
        jeu = jeuP;
		setText("");
		jeu.ajouteObservateur(this);
		setEnabled(jeu.peutRedimensionner());
    }

    public void miseAJour(){
        setEnabled(jeu.peutRedimensionner());
    }
}
