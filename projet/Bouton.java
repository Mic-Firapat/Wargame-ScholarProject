package projet;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;


/**
 * Bouton est la classe héritée de JButton qui définit les boutons de l'application
 * @version 0.9
 */
@SuppressWarnings("serial")
public class Bouton extends JButton implements MouseListener{

    int type; // 0 pour bouton jeu (bleu), 1 pour bouton mode creation (violet), 2 pour bouton sauvegarde (rose), 3 et 4 pour victoire et défaite, 5 slot de sauvegarde
	
    private BufferedImage image;
    private BufferedImage imageBase;
    private BufferedImage imageSurvol;
	
    private Color couleurBase; // couleur du text
    private Color couleurSurvol;

    /**
     * Constructeur de la classe Bouton.
     * @param nom <code>String</code> nom du bouton.
     * @param type <code>int</code> entier décrivant le type du bouton.
     * @since 0.5
     */
    public Bouton(String nom, int type){
        super(nom);
        
		setContentAreaFilled(false);
        setFont(new Font("Serif", Font.BOLD, 14));
        this.type = type;
        
        try {
            if (type == 0 || type == 3) { // bouton mode jeu
                this.imageBase = ImageIO.read(this.getClass().getResource("images/menus/bouton_jeu.png"));
                this.imageSurvol = ImageIO.read(this.getClass().getResource("images/menus/bouton_jeu_survol.png"));
                this.couleurBase = new Color(170, 200, 200);
                this.couleurSurvol = new Color(220, 255, 255);
                
            }
            else if (type == 1){ // bouton mode creation
                this.imageBase = ImageIO.read(this.getClass().getResource("images/menus/bouton_creation.png"));
                this.imageSurvol = ImageIO.read(this.getClass().getResource("images/menus/bouton_creation_survol.png"));
                this.couleurBase = new Color(230, 210, 230);
                this.couleurSurvol = new Color(250, 240, 255);
            }
            else { // bouton de sauvegardes
                this.imageBase = ImageIO.read(this.getClass().getResource("images/menus/bouton_sauvegarde.png"));
                this.imageSurvol = ImageIO.read(this.getClass().getResource("images/menus/bouton_sauvegarde_survol.png"));
                this.couleurBase = new Color(255, 200, 220);
                this.couleurSurvol = new Color(255, 230, 255);
            }
        }catch (Exception e) {  
            System.out.println("Images boutons introuvables.");
            e.printStackTrace();
        }
        
        if(type == 3 || type == 4) {
        	this.couleurBase = new Color(0, 0, 0);
            this.couleurSurvol = new Color(50, 50, 50);
        }
		
        this.image = imageBase;
        setForeground(couleurBase);
        
        this.addMouseListener(this);
        
    }
	
    /**
     * Redéfinition du paintComponent de la classe Bouton 
     * @param g <code>Graphics</code> nécessaire pour l'affichage.
     * @since 0.5
     */
    @Override
    public void paintComponent(Graphics g){
    	g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        super.paintComponent(g);
    }

    /**
     * Redéfinition de MouseEntered de la classe Bouton 
     * @param event <code>MouseEvent</code>
     * @since 0.5
     */
    @Override
    public void mouseEntered(MouseEvent event) {
        image = imageSurvol;
        setForeground(couleurSurvol);
    }

    /**
     * Redéfinition de MouseExited de la classe Bouton 
     * @param event <code>MouseEvent</code>
     * @since 0.5
     */
    @Override
    public void mouseExited(MouseEvent event) {
        image = imageBase;
        setForeground(couleurBase);
    }

    @Override
    public void mouseClicked(MouseEvent event) {}

    @Override
    public void mousePressed(MouseEvent event) {
    	if(type == 5)
    		setText("chargement en cours...");
    }
    
    @Override
    public void mouseReleased(MouseEvent event) {
    	if(type == 5)
    		setText("oops...");
    }

}