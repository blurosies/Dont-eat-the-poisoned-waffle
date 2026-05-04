package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurIncrem implements ActionListener {
    CollecteurEvenements control;

    EcouteurIncrem(CollecteurEvenements controlP) {
        control=controlP;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        control.incrementer();
    }
    
}
