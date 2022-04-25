package projet;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

//Gestion son

import java.net.URL;
import javax.sound.sampled.*;

/**
 * FenetreFinPartie est la fenêtre qui s'affiche lors d'une victoire o d'une défaite
 * Elle hérite de JDialog
 * @version 0.9
 */
@SuppressWarnings("serial")
public class FenetreFinPartie extends JDialog{
	
	
    BufferedImage fond;
	
    /**
     * Constructeur de la classe FenetreSauvegarde
     * @param parent <code>FenetreJeu</code> la JFrame parent.
     * @param denouement <code>int</code> 1 pour victoire, sinon défaite
     * @since 0.8
     */
    public FenetreFinPartie(FenetreJeu parent, int denouement) {
        super(parent, "Sauvegardes", true);
        setBounds(parent.getSize().width/2-250, parent.getSize().height/2-150, 750, 550);
        setUndecorated(true);
        
        try {
        	if (denouement == 1)
        		fond = ImageIO.read(this.getClass().getResource("images/menus/victoire.png"));
        	else {

		    fond = ImageIO.read(this.getClass().getResource("images/menus/defaite.png"));
		}
        }catch (Exception e) {
            System.out.println("Images introuvables.");
            e.printStackTrace();
        }
        
        
        
        Bouton retourMenu;
        if (denouement == 1)
        	retourMenu = new Bouton("Retour au menu", 3);
        else retourMenu = new Bouton("Retour au menu", 4);
        retourMenu.setPreferredSize(new Dimension(250, 87));
        retourMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                setVisible(false); 
                dispose();	
                parent.modeCreation();
            }
        }
            );
		
	
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(fond, 0, 0, 700, 500, this);
            }
        };

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

		
        // Components
        c.insets = new Insets(200, -55, 0, 0); // Pour placer le bouton au bon endroit
        panel.add(retourMenu, c);

        add(panel);

        setBackground(new Color(0, 0, 0, 0)); // tansparent
        setVisible(true); 

    }
	
	
}
