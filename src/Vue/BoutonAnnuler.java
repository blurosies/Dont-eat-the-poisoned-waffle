package Vue;

import javax.swing.*;

import Patterns.Observateur;
import Modele.*;

public class BoutonAnnuler extends JButton implements Observateur {
    JeuGaufre jeu;
    
    BoutonAnnuler(JeuGaufre jeuP){
        jeu = jeuP;
		setText("");
		jeu.ajouteObservateur(this);
		setEnabled(jeu.peutAnnuler());
    }

    public void miseAJour(){
        setEnabled(jeu.peutAnnuler());
    }
}
