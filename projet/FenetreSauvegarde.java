package projet;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;


// Imput de gestion de fichier pour les sauvegardes
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

/**
 * FenetreSauvegarde est la classe définissant la fenêtre donnant accès aux slots de sauvegarde.
 * Elle hérite de JDialog
 * @version 1.1
 */
@SuppressWarnings("serial")
public class FenetreSauvegarde extends JDialog{
	
    int mode; // MODE : 1 pour sauvegarder, 2 pour charger
	
    JPanel panel;
    BufferedImage fond;
	
    /**
     * Constructeur de la classe FenetreSauvegarde
     * @param parent <code>FenetreJeu</code> la JFrame parent.
     * @param mode <code>int</code> le mode de la fenetre.
     * @since 0.5
     */
    public FenetreSauvegarde(FenetreJeu parent, int mode) {
        super(parent, "Sauvegardes", true);
        setBounds(parent.getSize().width/2-100, parent.getSize().height/2-250, 400, 700);
        this.mode = mode;
        
        try {
            fond = ImageIO.read(this.getClass().getResource("images/menus/fond_sauvegarde.png"));

        }catch (Exception e) {
            System.out.println("Images introuvables.");
            e.printStackTrace();
        }
       
        int bouton;
        if(mode == 2)
        	bouton = 5;
        else bouton = 2;
        
        
        Bouton slot1 = new Bouton("Slot 1", bouton);
        slot1.setPreferredSize(new Dimension(350, 100));
        slot1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		
        		if (mode == 1) { //sauvegarde
        			FenetreConfirmation f = new FenetreConfirmation(parent, "Sauvegarde", "Écraser la sauvegarde précedente ?");
        			if (f.getConfirmation() == true) {
        				sauvegarder("./Sauvegardes/fichier1.ser", PanneauCommun.grille);
        				new FenetreInformation(parent, "Sauvegarde", "Sauvegarde effectuée !", 0);
        				setVisible(false); 
                		dispose();
        			}
        		}
        		else { 
        			PanneauCommun.grille = charger("./Sauvegardes/fichier1.ser");
        			parent.modeJeu();
            		setVisible(false); 
            		dispose();
        		}
        	}
        }
        		);


        Bouton slot2 = new Bouton("Slot 2", bouton);
        slot2.setPreferredSize(new Dimension(350, 100));
        slot2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){

        		if (mode == 1) { // sauvegarde
        			FenetreConfirmation f = new FenetreConfirmation(parent, "Sauvegarde", "Écraser la sauvegarde précedente ?");

        			if (f.getConfirmation() == true) {
        				sauvegarder("./Sauvegardes/fichier2.ser", PanneauCommun.grille);
        				new FenetreInformation(parent, "Sauvegarde", "Sauvegarde effectuée !", 0);
        				setVisible(false); 
                		dispose();
        			}
        		}
        		else { 
        			PanneauCommun.grille = charger("./Sauvegardes/fichier2.ser");
        			parent.modeJeu();
            		setVisible(false); 
            		dispose();
        		}
        	}
        }
        		);

        Bouton slot3 = new Bouton("Slot 3", bouton);
        slot3.setPreferredSize(new Dimension(350, 100));
        slot3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		
        		if (mode == 1)  {// sauvegarde
        			FenetreConfirmation f = new FenetreConfirmation(parent, "Sauvegarde", "Écraser la sauvegarde précedente ?");

        			if (f.getConfirmation() == true) {
        				sauvegarder("./Sauvegardes/fichier3.ser", PanneauCommun.grille);
        				new FenetreInformation(parent, "Sauvegarde", "Sauvegarde effectuée !", 0);
                		setVisible(false); 
                		dispose();
        			}
        		}
        		else { // chargement
        			PanneauCommun.grille = charger("./Sauvegardes/fichier3.ser");
        			parent.modeJeu();
            		setVisible(false); 
            		dispose();
        		}

        	}
        }
        		);

        Bouton annuler;
        if (mode == 1)
        	annuler = new Bouton("Annuler", 0);
        else annuler = new Bouton("Annuler", 1);

        annuler.setPreferredSize(new Dimension(200, 50));
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                setVisible(false); 
                dispose();	
            }
        }
            );

        

        JLabel messagePrevention = new JLabel("<html>Avant toute manipulation, assurez-vous d'avoir le dossier \"Sauvegardes\" dans le répertoire courant.</html>");
        messagePrevention.setPreferredSize(new Dimension(350, 100));
        messagePrevention.setForeground(new Color(160, 140, 180));
        messagePrevention.setFont(new Font("Serif", Font.BOLD, 14));
        
        JLabel label;
        if (mode == 1) label = new JLabel("Choisissez un slot où sauvegarder :");
        else label = new JLabel("Choisissez une sauvegarde :");
        label.setForeground(new Color(240, 230, 255));
        label.setFont(new Font("Serif", Font.BOLD, 14));
        

        panel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(fond, 0, 0, 400, 700, this);
            }
        };
        

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

		
        // Components

        

        c.insets = new Insets(-70, 30, 0, 0);
        panel.add(messagePrevention, c);

        c.insets = new Insets(20, 0, 10, 0);
        c.gridy = 1;
        panel.add(label, c);
		
        c.gridy = 2;
        c.insets = new Insets(20, 0, 0, 0);
        panel.add(slot1, c);

        c.gridy = 3;
        panel.add(slot2, c);

        c.gridy = 4;
        panel.add(slot3, c);
		
        c.gridy = 5;
        c.insets = new Insets(30, 0, 0, 0);
        panel.add(annuler, c);

        
        
        add(panel);

        setVisible(true); 

    }
	
	
    /**
     * Fonction qui va traiter l'enregistrement de la partie
     * @param nomFichier <code>String</code>
     * @param g <code>Grille</code>
     * @since 0.9
     */
	
    public void sauvegarder(String nomFichier, Grille g) {
    	// Récupération fichier
    	FileOutputStream fichier = null;

    	// init flux 
    	ObjectOutputStream ecriture = null;

    	try {

    		//ressource = getClass().getResource(nomFichier);

    		fichier = new FileOutputStream(nomFichier);

    		// On essaie d'ouvrir le flux
    		ecriture = new ObjectOutputStream(fichier);

    		// On enregistre l'objet en écrivant dans le fichier
    		ecriture.writeObject(g);
    		ecriture.writeObject(PanneauCommun.ensHeros);
    		ecriture.writeObject(PanneauCommun.ensMonstres);
    		ecriture.write(PanneauCommun.nbTours);
    		System.out.println("On a écrit dans le fichier ");

    	} catch (Exception e){
    		System.out.println("Erreur sauvegarde :"+e.getMessage());
    		e.printStackTrace();

    	} finally {

    		if (ecriture != null) {
    			try {
    				ecriture.close();
    				fichier.close();
    			} catch (Exception e2){
    				System.out.println("Erreur fermeture fichier :"+e2.getMessage());
    				e2.printStackTrace();
    			}
    		}

    	}

    }

    /**
     * Fonction qui va traiter le chargement d'un fichier
     * @param nomFichier <code>String</code>
     * @return <code>Grille</code>
     * @since 0.9
     */

    public Grille charger(String nomFichier) {
    	Grille g = null;

    	// Récupération fichier
    	FileInputStream fichier = null;

    	// init flux 
    	ObjectInputStream lecture = null;

    	try {

    		// Ouverture de flux
    		fichier = new FileInputStream(nomFichier);

    		// On ouvre le flux
    		lecture = new ObjectInputStream(fichier);

    		// On récupère la grille dans le fichier
    		g = (Grille)lecture.readObject();
    		PanneauCommun.ensHeros = (EnsembleSoldat)lecture.readObject();
    		PanneauCommun.ensMonstres = (EnsembleSoldat)lecture.readObject();
    		PanneauCommun.nbTours = lecture.read();
    		repaint();

    	} catch (Exception e){
    		System.out.println("Erreur chargement :"+e.getMessage());
    		e.printStackTrace();
    	} finally {
    		if (lecture != null){
    			try {
    				lecture.close();
    				fichier.close();
    			} catch (Exception e2){
    				System.out.println("Erreur fermeture du fichier :"+e2.getMessage());
    				e2.printStackTrace();
    			}
    		}
    	}
	
	return g;
    }
    
    
    
	
}
