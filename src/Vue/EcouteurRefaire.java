package Vue;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class EcouteurRefaire implements ActionListener {
    CollecteurEvenements control;

    EcouteurRefaire(CollecteurEvenements controlP) {
        control=controlP;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        control.refais();
    }
}
