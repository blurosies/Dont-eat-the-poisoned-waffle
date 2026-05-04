package Vue;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class EcouteurRestaurer implements ActionListener {
     CollecteurEvenements control;

    EcouteurRestaurer(CollecteurEvenements controlP) {
        control=controlP;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        control.restaure();
    }
}
