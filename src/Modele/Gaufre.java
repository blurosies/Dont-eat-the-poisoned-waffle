package Modele;

public class Gaufre implements Cloneable{
    private int grilleGaufre[][];
    private int nb_ligne;
    private int nb_colonne;
    private int x_poison;
    private int y_poison;


    Gaufre(int ligne,int colonne){
        this.nb_ligne=ligne;
        this.nb_colonne=colonne;
        this.grilleGaufre = new int[ligne][colonne];
        this.x_poison=0;
        this.y_poison=0;
        initGaufre();
    }

    private void initGaufre(){
        for(int i=0;i<this.nb_ligne;i++){
            for(int j=0;j<this.nb_colonne;j++){
                this.grilleGaufre[i][j]=0;
            }
        }
    }
    public int getcolonne(){
        return this.nb_colonne;
    }
    public int getligne(){
        return this.nb_ligne;
    }


    public boolean estMangee(int ligne,int colonne){
        if(this.grilleGaufre[ligne][colonne]==1){
            return true;
        }
        else {
            return false;
        }
    }
    private void mangerCase(int ligne ,int colonne){
        this.grilleGaufre[ligne][colonne]=1;
    }
    public int getXPoison(){
        return x_poison;
    }
    public int getYPoison(){
        return y_poison;
    }
    public Coup mangeGaufre(int ligne,int colonne){
        Coup current = new Coup(ligne, colonne);
        for(int i =ligne;i<this.nb_ligne;i++){
            for(int j =colonne;j<this.nb_colonne;j++){
                if(!estMangee(i, j)){
                    current.ajouteCase(i,j);
                    mangerCase(i, j);
                }
            }
        }
        return current;
    }
    public void restaureCaseGaufre(int ligne,int colonne){
        grilleGaufre[ligne][colonne]=0;
    }
    public boolean confPerdant(){
        if(estMangee(1,1) && estMangee(0,1) && estMangee(1,0)){
            return true;
        }
        return false;
    }
    public boolean coupJouable(int ligne,int colonne){
        if(ligne>=nb_ligne|| ligne<0 || colonne<0 || colonne >=nb_colonne){
            return false;
        }
        if(estMangee(ligne, colonne)!=true){
            return true;
        }
        return false;
    }
    public Gaufre clone(){
        try {
            Gaufre resultat= (Gaufre) super.clone();
            resultat.grilleGaufre= new int[this.nb_ligne][];
            for(int i=0;i<this.nb_ligne;i++){
                resultat.grilleGaufre[i]=this.grilleGaufre[i].clone();
            }
            return resultat;
        } catch (CloneNotSupportedException e) {
            System.out.println("Explosion");
        }
        return null;
    }
    void affichageMatriceDebug(){
        for(int i=0;i<nb_ligne;i++){
            for(int j=0;j<nb_colonne;j++){
                if(i==getXPoison() && j==getYPoison()){
                    System.out.print("X ");
                }
                else{
                    System.out.print(grilleGaufre[i][j]+" ");
                }
            }
            System.out.println();
        }
    }
    boolean posGagnante(){
        if (this.estMangee(1, 1)==false) 
                return false; 

        boolean ligne = estMangee(1,0 );
        boolean colonne = estMangee(0, 1);

        if (ligne==false && colonne==false)
            return false;
        
        if (ligne==true && colonne==true)
            return false;
            
        if (((ligne==false && colonne==true) || (ligne==true && colonne==false)) && estMangee(1,1))
            return true;
        return false;
    }
}