package Modele;

import java.util.Random;

public class IAChoixCoupGagnant extends IA {
    Random r;

    public IAChoixCoupGagnant() {
		r = new Random();
	}

    @Override
    public Coup joue() {
        if(gaufre.confPerdant()){
            return new Coup(0,0);
        }
        if(gaufre.posGagnante()){
            if(!gaufre.estMangee(0,1))
                return new Coup(0,1);
            return new Coup(1,0);
        }
        int rand = r.nextInt(gaufre.getligne()*gaufre.getcolonne());
        int l = rand/gaufre.getcolonne();
        int c = rand%gaufre.getligne();

        while(!gaufre.coupJouable(l, c)){
            rand = r.nextInt(gaufre.getligne()*gaufre.getcolonne());
            l = rand/gaufre.getcolonne();
            c = rand%gaufre.getligne();
        }
        return new Coup(l,c);
    }
}
