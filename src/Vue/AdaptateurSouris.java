package Vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdaptateurSouris extends MouseAdapter {
    NiveauGraphique niv;
    CollecteurEvenements control;

    AdaptateurSouris(NiveauGraphique nivP, CollecteurEvenements controlP){
        niv=nivP;
        control=controlP;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        int offsetX = niv.getoffsetX(); 
        int offsetY = niv.getoffsetY();
        int x = (e.getX()-offsetX) / niv.largeurCase();
        int y = (e.getY()-offsetY) / niv.hauteurCase();
        control.mangerG(x,y);
    }
    
}
