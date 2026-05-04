package Vue;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class AdaptateurClavier extends KeyAdapter{
    CollecteurEvenements control;
    public AdaptateurClavier(CollecteurEvenements c){
        control=c;
    }
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_A){
            control.ia();
        }
    }
    
}
