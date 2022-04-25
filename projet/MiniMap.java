package projet;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * MiniMap est la classe définissant le panel contenant la mini-map.
 * Elle hérite de JPanel
 * @version 0.8
 */
@SuppressWarnings("serial")
public class MiniMap extends JPanel{

    private static Grille grille;
    private BufferedImage tabMiniCases[]; // mini-cases
    private static int tailleMiniCases;
    private static int positionX;
    private static int positionY;
	
    /**
     * Constructeur de la classe MiniMap
     * @since 0.5
     */
    public MiniMap() {
        setPreferredSize(new Dimension(300, 125));
        setBackground(Color.black);
        
        tabMiniCases = new BufferedImage[20];
        try {
            tabMiniCases[0] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_herbe.png"));	
            tabMiniCases[1] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_arbre_rouge.png"));	
            tabMiniCases[2] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_arbre_jaune.png"));
            tabMiniCases[3] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_arbre_vert.png"));
            tabMiniCases[4] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_rocher.png"));
            tabMiniCases[5] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_rocher.png"));
            tabMiniCases[6] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_eau.png"));
            tabMiniCases[7] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_eau_profonde.png"));
            tabMiniCases[8] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_heros.png"));
            tabMiniCases[9] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_heros.png"));
            tabMiniCases[10] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_heros.png"));
            tabMiniCases[11] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_heros.png"));
            tabMiniCases[12] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_monstre.png"));
            tabMiniCases[13] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_monstre.png"));
            tabMiniCases[14] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_monstre.png"));
            tabMiniCases[15] = ImageIO.read(this.getClass().getResource("images/mini_cases/mini_monstre.png"));
			
        }catch (Exception e) {
            System.out.println("Images introuvables.");
            e.printStackTrace();
        }
    }
	
    /**
     * Récupère la grille modèle (appelée à l'initialisation de celle-ci)
     * @param g <code>Grille</code> la Grille à récupérer.
     * @since 0.5
     */
    public void lier(Grille g) {
        grille = g;
        calculeTailleCases();
    }
	
    /**
     * Calcule la taille des mini-cases de la mini-map
     * @since 0.5
     */
    public void calculeTailleCases() {
        double x = 300./(grille.getLongueur()*2); // taille d'une longueur d'hexagone en pixel
        double y = 125./(grille.getHauteur()*1.5); // taille d'une hauteur d'hexagone en pixel
        x = x*2/Math.sqrt(3);
        if(x < y) tailleMiniCases = (int)x;
        else tailleMiniCases = (int)y;
        positionX = (int)((300-(grille.getLongueur()*Math.sqrt(3)*tailleMiniCases))/2.);
        positionY = (int)((125-grille.getHauteur()*tailleMiniCases*1.5)/2.);
    }
	
    /**
     * Redéfinition de la méthode paintComponent de la classe MiniMap.
     * @param g Graphics pour l'affichage de la fenêtre.
     * @since 0.5
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        grille.afficherMiniMap(g, tailleMiniCases, positionX, positionY, tabMiniCases);
        
    }
	
}
