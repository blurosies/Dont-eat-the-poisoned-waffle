package Modele;
public class Case{
        private int ligne;
        private int colonne;
        
        Case(int lignep,int colonnep){
            ligne=lignep;
            colonne=colonnep;
        }
        public int getLigne() {
            return ligne;
        }
        public int getColonne() {
            return colonne;
        }
    }