

import Vue.CollecteurEvenements;
import Vue.InterfaceGraphique;
import Modele.IA;
import Modele.JeuGaufre;
import Controlleur.ControlEvents;

public class Main {
    public static void main(String[] args) {

        JeuGaufre jG = new JeuGaufre(6, 6);
        IA ia = IA.nouvelle(jG,"ArbreEtOu");
        CollecteurEvenements control = new ControlEvents(jG,ia);
        InterfaceGraphique iG = new InterfaceGraphique(jG, control);
        iG.run();
            
    }
}   
