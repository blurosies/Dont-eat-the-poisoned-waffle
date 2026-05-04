package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurIA implements ActionListener {
    CollecteurEvenements control;

    EcouteurIA(CollecteurEvenements controlP) {
        control=controlP;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        control.toggleIA();
    }
    
}
