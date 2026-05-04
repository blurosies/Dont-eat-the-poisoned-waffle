package Vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.awt.*;

import Patterns.Observateur;
import Modele.*;
public class NiveauGraphique extends JComponent implements Observateur{
    Image gaufreN, gaufreP,gameOver;
    JeuGaufre jeu;
    int largeurCase;
    int hauteurCase;


    public NiveauGraphique(JeuGaufre jeuP){
        jeu=jeuP;
        jeu.ajouteObservateur(this);
        gaufreN=lisImage("G_normal");
        gaufreP=lisImage("G_poison");
        gameOver=lisImage("GO");

        
    }

    private Image lisImage(String nom) {
        File image = new File("res/Images/" + nom + ".png");
        try {
            // Chargement d'une image utilisable dans Swing
            return ImageIO.read(image);
        } catch (Exception e) {
            System.err.println("Impossible de charger l'image " + nom);
        }
        return null;

	}

    private void tracer(Graphics2D g, Image i, int x, int y, int l, int h) {
		g.drawImage(i, x, y, l, h, null);
	}

    // public void tracerCarreGaufre(Graphics2D draw,int x, int y, int h, int l, boolean p){
    //     Color gaufreColor = new Color(222, 148, 58);
    //     Color gaufrePoisonColor = new Color(126, 143, 20);
    //     Color ligneColor = new Color(102, 58, 5);
    //     if (p) {
    //         draw.setColor(gaufrePoisonColor);
            
    //     }else {

    //         draw.setColor(gaufreColor);
    //     }
    //     draw.fillRect(x, y, h, l);
    //     draw.setColor(ligneColor);
    //     draw.drawRect(x, y, h, l);
        
    // }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D draw= (Graphics2D) g;
        int winH=getSize().height;
        int winW=getSize().width;
        int hauteurG=jeu.g.getligne();
        int largeurG=jeu.g.getcolonne();
        hauteurCase=Math.min(winH, 140*hauteurG) / hauteurG;
        largeurCase=Math.min(winW, 140*largeurG) / largeurG;
        // decalages
        int offsetX= (winW- Math.min(winW, 140*largeurG))/2;
        int offsetY= (winH- Math.min(winH, 140*hauteurG))/2;
        for (int i = 0; i < hauteurG; i++) {
            for (int j = 0; j < largeurG; j++) {
                if (!jeu.g.estMangee(i, j)) {
                    // c la case empoisonnee
                    if (jeu.g.getXPoison()==i && jeu.g.getYPoison()==j) {
                        tracer(draw, gaufreP, j*largeurCase+offsetX, i*hauteurCase+offsetY, largeurCase, hauteurCase);
                    }else {

                        tracer(draw,gaufreN, j*largeurCase+offsetX, i*hauteurCase+offsetY, largeurCase, hauteurCase);
                    }
                }
            }
        } 
        if (jeu.estTerminer()) {
            int imgW = gameOver.getWidth(null);
            int imgH = gameOver.getHeight(null);
                
            int centreX = (winW - imgW) / 2;
            int centreY = (winH - imgH) / 2;

            tracer(draw, gameOver, centreX, centreY, imgW, imgH);  
        }
    }

    int hauteurCase() {
		return hauteurCase;
	}

	int largeurCase() {
		return largeurCase;
	}

    int getoffsetX(){
        int largeurG=jeu.g.getcolonne();
        return (getSize().width- Math.min(getSize().width, 140*largeurG))/2;

    }

    int getoffsetY(){
        int hauteurG=jeu.g.getligne();
        return (getSize().height- Math.min(getSize().height, 140*hauteurG))/2;
    }

    public void miseAJour(){
        repaint();
    }

}
