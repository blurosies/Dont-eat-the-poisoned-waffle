package Modele;

public class IAArbreEtOu extends IA {

    @Override
    public Coup joue() {
        Coup meilleurCoup = null;

        // On parcourt toutes les cases de la gaufre
        for (int l = 0; l < gaufre.getligne(); l++) {
            for (int c = 0; c < gaufre.getcolonne(); c++) {
                
                // Si la case peut être mangée (et n'est pas la case empoisonnée 0,0)
                if ((l != 0 || c != 0) && !gaufre.estMangee(l, c)) {
                    
                    Gaufre simulation = gaufre.clone();
                    simulation.mangeGaufre(l, c);
                    
                    // On demande : "Si je joue ça, est-ce que le joueur B va perdre ?"
                    // (On appelle calculJoueurB car c'est au tour de B de jouer dans la simulation)
                    if (calculJoueurB(simulation)) {
                        return new Coup(l,c); // On a trouvé un coup gagnant !
                    }
                    
                    // Optionnel : on garde un coup par défaut au cas où aucun n'est gagnant
                    if (meilleurCoup == null) meilleurCoup = new Coup(l,c);
                }
            }
        }
        if(meilleurCoup==null){
            return new Coup(0,0);
        }
        return meilleurCoup;
    }

    // Le Joueur B joue : il veut que A perde (RETOURNE VRAI SI A GAGNE)
    private boolean calculJoueurB(Gaufre g) {
        if (g.confPerdant()) return true; // Si B ne peut plus jouer, A a gagné

        // Pour que A gagne, il faut que TOUS les coups de B mènent à une victoire de A
        for (int l = 0; l < g.getligne(); l++) {
            for (int c = 0; c < g.getcolonne(); c++) {
                if ((l != 0 || c != 0) && !g.estMangee(l, c)) {
                    Gaufre copie = g.clone();
                    copie.mangeGaufre(l, c);
                    
                    // Si B trouve UN coup qui fait perdre A, alors ce n'est pas une position gagnante
                    if (!calculJoueurA(copie)) return false; 
                }
            }
        }
        return true;
    }

    // Le Joueur A joue : il veut gagner (RETOURNE VRAI SI A GAGNE)
    private boolean calculJoueurA(Gaufre g) {
        if (g.confPerdant()) return false; // Si A ne peut plus jouer, il a perdu

        // Il suffit d'UN SEUL coup pour que A gagne
        for (int l = 0; l < g.getligne(); l++) {
            for (int c = 0; c < g.getcolonne(); c++) {
                if ((l != 0 || c != 0) && !g.estMangee(l, c)) {
                    Gaufre copie = g.clone();
                    copie.mangeGaufre(l, c);
                    if (calculJoueurB(copie)) return true;
                }
            }
        }
        return false;
    }
}