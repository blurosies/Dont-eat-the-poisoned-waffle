package Vue;

import javax.swing.*;

import Modele.*;
import Patterns.Observateur;

import java.awt.*;

public class InterfaceGraphique implements Runnable, Observateur {
    JeuGaufre jeu;
    JFrame frame;
    CollecteurEvenements control;
    NiveauGraphique niv;
    JLabel joueurcourant;
    boolean maximized;

    public InterfaceGraphique(JeuGaufre jeuP, CollecteurEvenements controlP){
        jeu=jeuP;
        control=controlP;
        jeu.ajouteObservateur(this);
    }

    public static void demarrer(JeuGaufre j, CollecteurEvenements c) {
		InterfaceGraphique vue = new InterfaceGraphique(j, c);
		c.ajouteInterfaceGraphique(vue);
		SwingUtilities.invokeLater(vue);
	}

	public void toggleFullscreen() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		if (maximized) {
			device.setFullScreenWindow(null);
			maximized = false;
		} else {
			device.setFullScreenWindow(frame);
			maximized = true;
		}
	}

    // creation de tous les boutons etc
	private JLabel creerLabel(String text) {
		JLabel label = new JLabel(text);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		return label;
	}

	private JButton creerButton(String text) {
		JButton bouton = new JButton(text);
		bouton.setAlignmentX(Component.CENTER_ALIGNMENT);
		return bouton;
	}

	private void icone_bouton(String image,String imagePressed,JButton b){
		b.setIcon(new ImageIcon("res/Images/"+image+".png"));
		b.setPressedIcon(new ImageIcon("res/Images/"+imagePressed+".png"));
		b.setContentAreaFilled(false); 
		b.setBorderPainted(false);
		b.setFocusPainted(false);
		b.setFocusable(false);
	}



    // methode run
    public void run(){
        frame = new JFrame("Gaufre");
		niv = new NiveauGraphique(jeu);
        JPanel contNV = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pannelhaut = new JPanel(new BorderLayout());
        JButton nouvPartie = creerButton(" ");
		contNV.add(nouvPartie);
		contNV.setOpaque(false);
		joueurcourant = creerLabel("Joueur courant : " + jeu.getJoueur()) ; 
		joueurcourant.setForeground(new Color(156, 53, 13));
		joueurcourant.setFont(new Font("SansSerif", Font.BOLD, 24));

		// sauve restaure ia
		JPanel contSR = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel pannelSR = new JPanel(new GridLayout(1,3,0,5));
		BoutonIA iaButton = new BoutonIA(jeu);
		JButton sauvegarder = creerButton(" ");
		JButton restaurer = creerButton(" ");
		pannelSR.add(sauvegarder);
		pannelSR.add(restaurer);
		pannelSR.add(iaButton);
		pannelSR.setOpaque(false);
		contSR.add(pannelSR);
		contSR.setOpaque(false);
		pannelhaut.add(contSR, BorderLayout.EAST);
		pannelhaut.add(contNV, BorderLayout.WEST);
		pannelhaut.add(joueurcourant,BorderLayout.CENTER);


		// annule refaire incr decr toggle joueur
		JPanel pannelbas = new JPanel(new BorderLayout());
		JPanel contAR = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        BoutonAnnuler annuler = new BoutonAnnuler(jeu);
        BoutonRefaire refaire = new BoutonRefaire(jeu);
		BoutonJoueur joueurbouton = new BoutonJoueur(jeu);
		BoutonIncrem incremente = new BoutonIncrem(jeu);
		BoutonDecrem decremente = new BoutonDecrem(jeu);
		JPanel pannelPM = new JPanel(new GridLayout(1,2,0,5));
		pannelPM.add(incremente);
		pannelPM.add(decremente);
		pannelPM.setOpaque(false);
        contAR.add(annuler);
        contAR.add(refaire);
		contAR.setOpaque(false);
        pannelbas.add(contAR, BorderLayout.CENTER);
		pannelbas.add(joueurbouton,BorderLayout.EAST);
		pannelbas.add(pannelPM,BorderLayout.WEST);


        // ajout des ecouteurs
        annuler.addActionListener(new EcouteurAnnuler(control));
        refaire.addActionListener(new EcouteurRefaire(control));
        sauvegarder.addActionListener(new EcouteurSauve(control));
        restaurer.addActionListener(new EcouteurRestaurer(control));
        nouvPartie.addActionListener(new EcouteurNouvPartie(control,jeu));
        niv.addMouseListener(new AdaptateurSouris(niv, control));
		iaButton.addActionListener(new EcouteurIA(control));
		joueurbouton.addActionListener(new EcouteurJoueur(control));
		incremente.addActionListener(new EcouteurIncrem(control));
		decremente.addActionListener(new EcouteurDecrem(control));
		frame.addKeyListener(new AdaptateurClavier(control));
		frame.setFocusable(true);

		// ajuster le niv pour esthetique
		JPanel contniv = new JPanel(new BorderLayout());
		contniv.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		contniv.add(niv,BorderLayout.CENTER);
		contniv.setOpaque(false);

		// set l'icone de tous les boutons
		icone_bouton("Undo", "UndoPressed", annuler);
		icone_bouton("Redo", "RedoPressed", refaire);
		icone_bouton("Restart", "RestartPressed", nouvPartie);
		icone_bouton("Save", "SavePressed", sauvegarder);
		icone_bouton("Restore", "RestorePressed", restaurer);
		icone_bouton("Joueur", "JoueurPressed", joueurbouton);
		icone_bouton("Plus", "Pluspressed", incremente);
		icone_bouton("minus", "minuspressed", decremente);


		// Jtogglebutton
		iaButton.setIcon(new ImageIcon("res/Images/AI.png"));
		iaButton.setSelectedIcon(new ImageIcon("res/Images/AIpressed.png"));
		iaButton.setContentAreaFilled(false); 
		iaButton.setBorderPainted(false);
		iaButton.setFocusPainted(false);
		iaButton.setFocusable(false);


		// ajout de frame
		pannelhaut.setOpaque(false);
		pannelbas.setOpaque(false);
        frame.setLayout(new BorderLayout());
		frame.add(pannelhaut, BorderLayout.NORTH);
        frame.add(pannelbas, BorderLayout.SOUTH);
		frame.add(contniv,BorderLayout.CENTER);

		frame.getContentPane().setBackground(new Color(238, 211, 149));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 900);
		frame.setVisible(true);


    }

	@Override
    public void miseAJour() {
        if (joueurcourant != null && !jeu.estTerminer()) {
            joueurcourant.setText("Joueur courant : " + jeu.getJoueur());
        } else {
			if (jeu.estTerminer()) {
				int gagnant = (jeu.getJoueur() == 1) ? 1 : 2;
            	joueurcourant.setText("Joueur "+gagnant+" remporte la partie!");
			}
		}
	}


}
