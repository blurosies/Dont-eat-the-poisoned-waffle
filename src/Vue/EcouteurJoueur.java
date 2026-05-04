package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurJoueur implements ActionListener {
    CollecteurEvenements control;

    EcouteurJoueur(CollecteurEvenements controlP) {
        control=controlP;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        control.toggleJoueur();
    }
    
}
