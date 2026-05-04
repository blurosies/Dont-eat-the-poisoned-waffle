package Modele;

import java.util.Random;


public class IAAleatoire extends IA  {
    Random r;

    public IAAleatoire() {
		r = new Random();
	}

    @Override
	public Coup joue() {
        int rand = r.nextInt(gaufre.getligne()*gaufre.getcolonne());
        int l = rand/gaufre.getcolonne();
        int c = rand%gaufre.getligne();
        while(!gaufre.coupJouable(l,c)){
            rand = r.nextInt(gaufre.getligne()*gaufre.getcolonne());
            l = rand/gaufre.getcolonne();
            c = rand%gaufre.getligne();
        }

        Coup coup = new Coup(l,c);
        return coup;
    }
}
