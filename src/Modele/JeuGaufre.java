package Modele;
import java.util.Stack;
import java.util.Iterator;
import Patterns.Observable;

public class JeuGaufre extends Observable {
    private int joueur;
    public Gaufre g;
    private boolean peutAnnuler;
    private boolean peutRefaire;
    private boolean estTerminer;
    private boolean redimensionner;
    private Stack<Coup> historiqueUndo;
    private Stack<Coup> historiqueRedo;
    private boolean mode_ia;
    private int joueur_IA=2;


    public JeuGaufre(int ligne,int colonne){
        this.joueur = 1;
        this.mode_ia = false;
        this.g = new Gaufre(ligne,colonne);
        this.peutAnnuler = false;
        this.peutRefaire = false;
        this.estTerminer = false;
        this.redimensionner=true;
        this.historiqueRedo = new Stack<>();
        this.historiqueUndo = new Stack<>();
    }
    public void setJoueur(int joueur){
        if(joueur==1){
            joueur=1;
        }
        else{ //L'iA joue en premier
            joueur=2;
        }
    }
    public void setAI(){
        mode_ia=!mode_ia;
        metAJour();
    }    
    public boolean getModeIA(){
        return this.mode_ia;
    }
    public int getJoueurIA(){
        return this.joueur_IA;
    }
    public int getJoueur(){
        return this.joueur;
    }
    public boolean estTerminer(){
        return this.estTerminer;
    }
    public boolean peutAnnuler(){
        return this.peutAnnuler;
    }
    public boolean peutRefaire(){
        return this.peutRefaire;
    }
    public void changeJoueur(){
        if(joueur==1){
            joueur = 2;
        }
        else{
            joueur = 1;
        }
        metAJour();
    }

        public void increm(){
        this.nouvellePartie(this.g.getligne()+1, this.g.getcolonne()+1);
        metAJour();
    }

    public void decrem(){
        this.nouvellePartie(this.g.getligne()-1, this.g.getcolonne()-1);
        metAJour();
    }

    public void nouvellePartie(int lignes,int colonnes){
        if (lignes>2&&colonnes>2) {
            this.joueur = 1;
            this.g = new Gaufre(lignes,colonnes);
            this.peutAnnuler = false;
            this.peutRefaire = false;
            this.estTerminer = false;
            this.redimensionner=true;
            this.historiqueRedo = new Stack<>();
            this.historiqueUndo = new Stack<>();
            metAJour();
        }
    }

    public boolean joue(int ligne,int colonne){
        if(this.g.coupJouable(ligne, colonne)){
            redimensionner=false;
            Coup c = this.g.mangeGaufre(ligne, colonne);
            this.historiqueUndo.add(c);
            peutAnnuler = true;
            peutRefaire=false;
            historiqueRedo.clear();
            if(this.g.estMangee(this.g.getXPoison(), this.g.getYPoison())){
                estTerminer=true;
                peutAnnuler=false;
                peutRefaire=false;
            }
            changeJoueur();
            metAJour();
            return true;
        }
        else{
            return false;
        }
    }
    public boolean peutRedimensionner(){
        return this.redimensionner;
    }

    public void annuler(){
        Coup ancien = historiqueUndo.pop();
        historiqueRedo.push(ancien);
        Iterator<Case> it = ancien.listeCase.iterator();
        peutRefaire = true;
        while (it.hasNext()) {
            Case current = it.next();
            this.g.restaureCaseGaufre(current.getLigne(), current.getColonne());
        }
        if(historiqueUndo.empty()){
            this.peutAnnuler = false ;
        }
        changeJoueur();
        metAJour();
    }
    public void refaire(){
        Coup ancien = historiqueRedo.pop();
        historiqueUndo.push(ancien);
        peutAnnuler=true;
        this.g.mangeGaufre(ancien.getLigne(),ancien.getColonne());
        if(historiqueRedo.empty()){
            this.peutRefaire = false;
        }
        changeJoueur();
        metAJour();
    }
public void sauvegarder(){
        try {
            java.io.PrintWriter writer = new java.io.PrintWriter("save_gaufre.txt");
            
            writer.println(this.g.getligne());
            writer.println(this.g.getcolonne());
            writer.println(this.joueur);
            
            writer.println(this.historiqueUndo.size());
            for (Coup c : this.historiqueUndo) {
                writer.println(c.listeCase.size()); 
                for (Case cs : c.listeCase) {
                    writer.println(cs.getLigne() + " " + cs.getColonne());
                }
            }

            writer.println(this.historiqueRedo.size());
            for (Coup c : this.historiqueRedo) {
                writer.println(c.listeCase.size());
                for (Case cs : c.listeCase) {
                    writer.println(cs.getLigne() + " " + cs.getColonne());
                }
            }
            
            writer.close();
        } catch (java.io.IOException e) {
            System.out.println("Erreur dnas save" + e.getMessage());
        }
    }


    public void restaurer(){
        try {
            java.io.File fichier = new java.io.File("save_gaufre.txt");
            java.util.Scanner scanner = new java.util.Scanner(fichier);

            int lignes = scanner.nextInt();
            int colonnes = scanner.nextInt();

            this.historiqueUndo.clear();
            this.historiqueRedo.clear();
            this.nouvellePartie(lignes, colonnes); 
            this.joueur = scanner.nextInt();

            int size_undo = scanner.nextInt();
            if(size_undo != 0){
                peutAnnuler = true;
            }
            for (int i = 0; i < size_undo; i++) {
                int nbCases = scanner.nextInt();
                Coup c = null;

                for (int j = 0; j < nbCases; j++) {
                    int ligne_u = scanner.nextInt();
                    int colonne_u = scanner.nextInt();
                    if (j == 0) {
                        c = new Coup(ligne_u, colonne_u);
                        this.g.mangeGaufre(ligne_u, colonne_u); 
                    } else {
                        c.ajouteCase(ligne_u, colonne_u);
                    }  
                }
                this.historiqueUndo.push(c);
            }

            int size_redo = scanner.nextInt();
            if(size_redo != 0){
                peutRefaire = true;
            }
            for (int i = 0; i < size_redo; i++) {
                int nbCases = scanner.nextInt();
                Coup c = null;
                for (int j = 0; j < nbCases; j++) {
                    int ligne_r = scanner.nextInt();
                    int colonne_r = scanner.nextInt();
                    if (j == 0)
                        c = new Coup(ligne_r, colonne_r);
                    else c.ajouteCase(ligne_r, colonne_r);
                }
                this.historiqueRedo.push(c);
            }
            scanner.close();
            redimensionner=false;
            metAJour();
        } catch (Exception e) {
            System.out.println("Erreur durant la restauration : " + e.getMessage());
        }
    }
}
