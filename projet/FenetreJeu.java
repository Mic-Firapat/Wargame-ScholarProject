package projet;



import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * FenetreJeu est une classe qui représente la fenêtre principale utilisée pour l'affichage. Elle hérite de JFrame.
 * @version 0.9
 */
@SuppressWarnings("serial")
public class FenetreJeu extends JFrame implements ComponentListener {

    private static FenetreJeu fenetre;
    
    // mode creation
    private PanneauCreation plateauCreation;
    private JPanel panelHautCreation;
    private JPanel panelBasCreation;
    
    // mode jeu
    private PanneauJeu plateauJeu;
    private JPanel panelHautJeu;
    private JPanel panelBasJeu;

	
    // images
    private BufferedImage banniereHautCreation;
    private BufferedImage banniereBasCreation;
    private BufferedImage banniereHautJeu;
    private BufferedImage banniereBasJeu;
	


    /**
     * Constructeur de la classe FenetreJeu
     * @since 0.2
     */
    public FenetreJeu() {
        setTitle("wargame");
        setBounds(100, 50, 1400, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        
        try {
            banniereHautJeu = ImageIO.read(this.getClass().getResource("images/menus/banniere_haut_jeu.png"));
            banniereBasJeu = ImageIO.read(this.getClass().getResource("images/menus/banniere_bas_jeu.png"));
            banniereHautCreation = ImageIO.read(this.getClass().getResource("images/menus/banniere_haut_creation.png"));
            banniereBasCreation = ImageIO.read(this.getClass().getResource("images/menus/banniere_bas_creation.png"));
            
        }catch (Exception e) {
            System.out.println("Images introuvables.");
            e.printStackTrace();
        }
        
        
        // miniMap
        
        MiniMap miniMap = new MiniMap();
        MiniMap miniMap2 = new MiniMap();
        
        
        
        /* ---------------- initialisation du mode création -------------- */
        
        // panel du haut
        
        panelHautCreation = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(banniereHautCreation, 0, 0, 2000, 150, this);
            }
        };
        panelHautCreation.setPreferredSize(new Dimension(2000, 125));
		 
		

        Bouton charger = new Bouton("Charger une partie", 2);
        charger.setPreferredSize(new Dimension(200, 75));
        charger.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new FenetreSauvegarde(fenetre, 2);
                miniMap.lier(PanneauCommun.grille);
                miniMap.calculeTailleCases();
            }
        }
            );
		
		
        Bouton mapAleatoire = new Bouton("Map aléatoire", 1);
        mapAleatoire.setPreferredSize(new Dimension(200, 75));
        mapAleatoire.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Map aléatoire");
                plateauCreation.retirSoldatsMap();
                PanneauCommun.grille.randomGrille();
                repaint();
                }
        }
        	);
		

        Bouton commencer = new Bouton("Commencer", 1);
        commencer.setPreferredSize(new Dimension(250, 87));
        commencer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	if(plateauCreation.verifConditions()) {
                	plateauJeu.initialisePartie();
            		modeJeu();
            	}
            }
        }
            );

		
		
        panelHautCreation.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
		

        // Components
        c.gridx = 0;        
        c.insets = new Insets(0, 50, 0, 0);
        panelHautCreation.add(miniMap, c);
        
        c.gridx = 1;
        c.insets = new Insets(-50, 120, 0, 0);
        panelHautCreation.add(charger, c);

        c.gridx = 2;
        c.gridwidth = 2;
        c.insets = new Insets(-81, 0, 0, 0);
        JLabel titre = new JLabel("CRÉATION DE LA MAP");
        titre.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
        titre.setForeground(new Color(255, 255, 255, 150));
        panelHautCreation.add(titre, c);

        
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(-75, 35, 0, 0);
        panelHautCreation.add(mapAleatoire, c);

        c.gridx = 3;
        c.insets = new Insets(-87, 10, 0, 0);
        panelHautCreation.add(commencer, c);
		
        add(panelHautCreation, BorderLayout.NORTH);

        
        
        // panel du bas
        
        panelBasCreation = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(banniereBasCreation, 0, 0, 2000, 150, this);
            }
        };
        panelBasCreation.setPreferredSize(new Dimension(2000, 125));
        panelBasCreation.setLayout(new FlowLayout());
        
		
        // plateau de jeu
		
        plateauCreation = new PanneauCreation(this, miniMap, panelBasCreation);
        add(plateauCreation, BorderLayout.CENTER);
        
        add(panelBasCreation, BorderLayout.SOUTH);
        
    	setLayout(new FlowLayout());
    	
    	


        /* ---------------- initialisation du mode jeu -------------- */
		
        // Panel du haut
		
        panelHautJeu = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(banniereHautJeu, 0, 0, 2000, 150, this);
            }
        };
		
        panelHautJeu.setPreferredSize(new Dimension(2000, 125));
        panelHautJeu.setVisible(false);
		
        Bouton terminerTour = new Bouton("Terminer le tour", 0);
        terminerTour.setPreferredSize(new Dimension(250, 87));
        terminerTour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                plateauJeu.finTour();
            }
        }
            );
		
	
        Bouton sauvegarder = new Bouton("Sauvegarder", 2);
        sauvegarder.setPreferredSize(new Dimension(200, 75));
        sauvegarder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new FenetreSauvegarde(fenetre, 1);
            }
        }
            );
		
        Bouton redemarrer = new Bouton("Redémarrer", 0);
        redemarrer.setPreferredSize(new Dimension(200, 75));
        redemarrer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	FenetreConfirmation f = new FenetreConfirmation(fenetre, "Redémarrer ?", "<html>Quitter la partie pour retourner au mode création ? <br><br>(Les données non sauvegardées seront perdues)</html>");
                if (f.getConfirmation()) {
                	plateauCreation.retirSoldatsMap();
                	modeCreation();
                }
            }
        }
            );

        panelHautJeu.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
		

        // Components
        c.gridx = 0;        
        c.insets = new Insets(0, 50, 0, 0);
        panelHautJeu.add(miniMap2, c);
        
        c.gridx = 1;
        c.insets = new Insets(38, 95, 0, 0);
        panelHautJeu.add(terminerTour, c);

        c.gridx = 2;
        c.insets = new Insets(-50, 60, 0, 0);
        panelHautJeu.add(sauvegarder, c);

        c.gridx = 3;
        c.insets = new Insets(-50, 10, 0, 0);
        panelHautJeu.add(redemarrer, c);

        add(panelHautJeu, BorderLayout.NORTH);


        
        // panel du bas
        
        panelBasJeu =  new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(banniereBasJeu, 0, 0, 2000, 150, this);
            }
        };
        panelBasJeu.setPreferredSize(new Dimension(2000, 125));
        panelBasJeu.setVisible(false);
        panelBasJeu.setLayout(new FlowLayout());
        
        
        
        // plateau de jeu

        
        plateauJeu = new PanneauJeu(this, miniMap2, panelBasJeu);
        plateauJeu.setVisible(false);
        add(plateauJeu, BorderLayout.CENTER);
		

        add(panelBasJeu, BorderLayout.SOUTH);
    	

    	
    	addComponentListener(this); // pour détecter les resize de la fenêtre

    }
    
    /**
     * Permet de passer en mode jeu
     * @since 0.5
     */
    public void modeJeu() {
        panelHautCreation.setVisible(false);
    	plateauCreation.setVisible(false);
    	panelBasCreation.setVisible(false);
        panelHautJeu.setVisible(true);
    	plateauJeu.setVisible(true);
    	panelBasJeu.setVisible(true);
    	repaint();
    }
    
    /**
     * Permet de passer en mode création
     * @since 0.5
     */
    public void modeCreation() {
        panelHautJeu.setVisible(false);
    	plateauJeu.setVisible(false);
    	panelBasJeu.setVisible(false);
        panelHautCreation.setVisible(true);
    	plateauCreation.setVisible(true);
    	panelBasCreation.setVisible(true);
    	repaint();
    }
    
    
    /**
     * Redéfinition de componentResize pour recadrer les panels en même tant que la fenêtre
     * @param e <code>ComponentEvent</code>
     * @since 0.5
     */
    @Override
    public void componentResized(ComponentEvent e) { /* Réadapte la taille de la fenêtre */
        Dimension d = fenetre.getSize();
        d.height = d.height-250; // on retire la taille de panelHaut et panelBas
        plateauJeu.setPreferredSize(d);
        plateauJeu.repaint();
        plateauCreation.setPreferredSize(d);
        plateauCreation.repaint();
		
    }

    
    /**
     * Méthode main appelée au lancement du programme.
     * @param args Les arguments du programme.
     * @since 0.0
     */
    public static void main(String[] args) {
        fenetre = new FenetreJeu();
        fenetre.setVisible(true);
    }


    
    @Override
    public void componentHidden(ComponentEvent arg0) {
        // TODO Auto-generated method stub
		
    }

    @Override
    public void componentMoved(ComponentEvent arg0) {
        // TODO Auto-generated method stub
		
    }

    @Override
    public void componentShown(ComponentEvent arg0) {
        // TODO Auto-generated method stub
		
    }


	
}

