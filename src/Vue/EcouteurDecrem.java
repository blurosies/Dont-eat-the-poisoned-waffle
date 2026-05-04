package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurDecrem implements ActionListener {
    CollecteurEvenements control;

    EcouteurDecrem(CollecteurEvenements controlP) {
        control=controlP;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        control.decrementer();
    }
    
}
