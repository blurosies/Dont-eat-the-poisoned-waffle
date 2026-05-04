package Modele;

public abstract class IA {
    private JeuGaufre jeu;
    Gaufre gaufre;


    public static IA nouvelle(JeuGaufre j, String type) {
		IA resultat = null;
		// Méthode de fabrication pour l'IA, qui crée le bon objet selon la config
		switch (type) {
			case "Aleatoire":
				resultat = new IAAleatoire();
				break;
			case "ChoixCoupGagnant":
				resultat = new IAChoixCoupGagnant();
				break;
			case "ArbreEtOu":
				resultat = new IAArbreEtOu();
				break;
			default:
				System.out.println("IA de type " + type + " non supportée");
				return null;
		}
		if (resultat != null) {
			resultat.jeu = j;
		}
		return resultat;
	}

    public final Coup elaboreCoup() {
		gaufre = jeu.g.clone();
		return joue();
	}

	Coup joue() {
		return null;
	}
}
