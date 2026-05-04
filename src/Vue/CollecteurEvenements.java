package Vue;

public interface CollecteurEvenements {
    void annule();
    void refais();
    void nouvPartie(int h, int l);
    void restaure();
    void sauve();
    void mangerG(int x, int y);
    void ia();
    public void ajouteInterfaceGraphique(InterfaceGraphique v);
    public void toggleIA();
    public void toggleJoueur();
    public void incrementer();
    public void decrementer();
}
