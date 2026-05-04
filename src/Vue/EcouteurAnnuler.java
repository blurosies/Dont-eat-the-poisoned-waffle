package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurAnnuler implements ActionListener {
    CollecteurEvenements control;

    EcouteurAnnuler(CollecteurEvenements controlP) {
        control=controlP;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        control.annule();
    }
    
}
