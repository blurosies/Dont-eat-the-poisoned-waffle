package Vue;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class EcouteurSauve implements ActionListener {
     CollecteurEvenements control;

    EcouteurSauve(CollecteurEvenements controlP) {
        control=controlP;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        control.sauve();
    }
}
