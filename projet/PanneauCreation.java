package projet;

import java.awt.*;
import javax.swing.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;

/**
 * PanneauCreation est le panneau utilisé lors du mode création de map. Il hérite de PanneauCommun.
 * @version 0.9
 */
@SuppressWarnings("serial")
public class PanneauCreation extends PanneauCommun implements ActionListener, ItemListener{
	

    private int typeSelect = 0;

    private JPanel radioButtons;
    
    
    private BufferedImage fond;
    private JRadioButton herbe;
    private JRadioButton arbreRouge;
    private JRadioButton arbreJaune;
    private JRadioButton arbreVert;
    private JRadioButton rocher1;
    private JRadioButton rocher2;
    private JRadioButton eau;
    private JRadioButton eauProfonde;
	
    private JRadioButton fantassin;
    private JRadioButton archer;
    private JRadioButton lancier;
    private JRadioButton pretre;
    private JRadioButton ork;
    private JRadioButton gobelinArcher;
    private JRadioButton kobold;
    private JRadioButton gobelinShaman;
    
    private boolean clicGauche = false;
    private boolean clicDroit = false;
	

    private ImageIcon herbeIcon;
    private ImageIcon arbreRougeIcon;
    private ImageIcon arbreJauneIcon;
    private ImageIcon arbreVertIcon;
    private ImageIcon rocher1Icon;
    private ImageIcon rocher2Icon;
    private ImageIcon eauIcon;
    private ImageIcon eauProfondeIcon;
    private ImageIcon fantassinIcon;
    private ImageIcon archerIcon;
    private ImageIcon lancierIcon;
    private ImageIcon pretreIcon;
    private ImageIcon orkIcon;
    private ImageIcon gobelinArcherIcon;
    private ImageIcon koboldIcon;
    private ImageIcon gobelinShamanIcon;
	

	
    /**
     * Constructeur de la classe PanneauCorrection.
     * @param fenetre <code>FenetreJeu</code> la fenêtre principale
     * @param miniMap <code>MiniMap</code> la mini Map.
     * @param panelBas <code>JPanel</code> le panel du bas.
     * @since 0.5
     */
    public PanneauCreation (FenetreJeu fenetre, MiniMap miniMap, JPanel panelBas) {
    	super(fenetre, miniMap);

    	this.panelBas = panelBas;
    	
    	setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 0));
    	
    	
    	try {
            fondPlateau = ImageIO.read(this.getClass().getResource("images/menus/fond_plateau_creation.png"));
            fond = ImageIO.read(this.getClass().getResource("images/menus/fond_creation.png"));
    	}catch (Exception e) {
            System.out.println("Images introuvables.");
            e.printStackTrace();
        }
    	
    	herbeIcon = new ImageIcon(tabCases[0].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	arbreRougeIcon = new ImageIcon(tabCases[1].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	arbreJauneIcon = new ImageIcon(tabCases[2].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	arbreVertIcon = new ImageIcon(tabCases[3].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	rocher1Icon = new ImageIcon(tabCases[4].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	rocher2Icon = new ImageIcon(tabCases[5].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	eauIcon = new ImageIcon(tabCases[6].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	eauProfondeIcon = new ImageIcon(tabCases[7].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	fantassinIcon = new ImageIcon(tabCases[8].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	archerIcon = new ImageIcon(tabCases[9].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	lancierIcon = new ImageIcon(tabCases[10].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	pretreIcon = new ImageIcon(tabCases[11].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	orkIcon = new ImageIcon(tabCases[12].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	gobelinArcherIcon = new ImageIcon(tabCases[13].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	koboldIcon = new ImageIcon(tabCases[14].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	gobelinShamanIcon = new ImageIcon(tabCases[15].getScaledInstance(20, 23, Image.SCALE_SMOOTH));
    	
    	
    	
    	radioButtons = new JPanel(){
            public void paintComponent(Graphics g) {
                g.drawImage(fond, 0, 0, 200, 1500, this);
            }
        };
    	radioButtons.setPreferredSize(new Dimension(200, 1500));
    	
    	ButtonGroup groupe = new ButtonGroup();
		
    	
        herbe = new JRadioButton("herbe", true);
        groupe.add(herbe);
        herbe.addItemListener(this);
        herbe.addActionListener(this);
        herbe.setOpaque(false);
        herbe.setForeground(new Color(80, 40, 80));
        
		
        arbreRouge = new JRadioButton("arbre rouge");
        groupe.add(arbreRouge);
        arbreRouge.addItemListener(this);
        arbreRouge.addActionListener(this);
        arbreRouge.setOpaque(false);
        arbreRouge.setForeground(Color.white);
		
        arbreJaune = new JRadioButton("arbre jaune");
        groupe.add(arbreJaune);
        arbreJaune.addItemListener(this);
        arbreJaune.addActionListener(this);
        arbreJaune.setOpaque(false);
        arbreJaune.setForeground(Color.white);
		
        arbreVert = new JRadioButton("arbre vert");
        groupe.add(arbreVert);
        arbreVert.addItemListener(this);
        arbreVert.addActionListener(this);
        arbreVert.setOpaque(false);
        arbreVert.setForeground(Color.white);
		
        rocher1 = new JRadioButton("rocher 1");
        groupe.add(rocher1);
        rocher1.addItemListener(this);
        rocher1.addActionListener(this);
        rocher1.setOpaque(false);
        rocher1.setForeground(Color.white);
		
        rocher2 = new JRadioButton("rocher 2");
        groupe.add(rocher2);
        rocher2.addItemListener(this);
        rocher2.addActionListener(this);
        rocher2.setOpaque(false);
        rocher2.setForeground(Color.white);
		
        eau = new JRadioButton("eau");
        groupe.add(eau);
        eau.addItemListener(this);
        eau.addActionListener(this);
        eau.setOpaque(false);
        eau.setForeground(Color.white);

        eauProfonde = new JRadioButton("eau profonde");
        groupe.add(eauProfonde);
        eauProfonde.addItemListener(this);
        eauProfonde.addActionListener(this);
        eauProfonde.setOpaque(false);
        eauProfonde.setForeground(Color.white);
        
        fantassin = new JRadioButton("fantassin");
        groupe.add(fantassin);
        fantassin.addItemListener(this);
        fantassin.addActionListener(this);
        fantassin.setOpaque(false);
        fantassin.setForeground(Color.white);

        archer = new JRadioButton("archer");
        groupe.add(archer);
        archer.addItemListener(this);
        archer.addActionListener(this);
        archer.setOpaque(false);
        archer.setForeground(Color.white);

        lancier = new JRadioButton("lancier");
        groupe.add(lancier);
        lancier.addItemListener(this);
        lancier.addActionListener(this);
        lancier.setOpaque(false);
        lancier.setForeground(Color.white);
        
        pretre = new JRadioButton("pretre");
        groupe.add(pretre);
        pretre.addItemListener(this);
        pretre.addActionListener(this);
        pretre.setOpaque(false);
        pretre.setForeground(Color.white);
		
        ork = new JRadioButton("ork");
        groupe.add(ork);
        ork.addItemListener(this);
        ork.addActionListener(this);
        ork.setOpaque(false);
        ork.setForeground(Color.white);

        gobelinArcher = new JRadioButton("gobelin archer");
        groupe.add(gobelinArcher);
        gobelinArcher.addItemListener(this);
        gobelinArcher.addActionListener(this);
        gobelinArcher.setOpaque(false);
        gobelinArcher.setForeground(Color.white);

        kobold = new JRadioButton("kobold");
        groupe.add(kobold);
        kobold.addItemListener(this);
        kobold.addActionListener(this);
        kobold.setOpaque(false);
        kobold.setForeground(Color.white);
        

        gobelinShaman = new JRadioButton("gobelin shaman");
        groupe.add(gobelinShaman);
        gobelinShaman.addItemListener(this);
        gobelinShaman.addActionListener(this);
        gobelinShaman.setOpaque(false);
        gobelinShaman.setForeground(Color.white);
		
		
        Bouton retrecir = new Bouton("Rétrécir la grille", 1);
        retrecir.setPreferredSize(new Dimension(100, 45));
        retrecir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	int a, b;
                a = grille.setHauteur(grille.getHauteur()-1);
                b = grille.setLongueur(grille.getLongueur()-1);
                if(a !=-1 || b != -1)
                	miniMap.calculeTailleCases();
                else new FenetreInformation(fenetre, "Erreur", "Impossible de rétrécir la grille, taille minimum atteinte.", 2);
                repaint();
            }
        }
            );

        Bouton agrandir = new Bouton("Agrandir la grille", 1);
        agrandir.setPreferredSize(new Dimension(100, 45));
        agrandir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	int a, b;
                a = grille.setHauteur(grille.getHauteur()+1);
                b = grille.setLongueur(grille.getLongueur()+1);
                if(a !=-1 || b != -1)
                	miniMap.calculeTailleCases();
                else new FenetreInformation(fenetre, "Erreur", "Impossible d'agrandir la grille, taille maximum atteinte.", 2);
                repaint();
            }
        }
            );
		
		
        radioButtons.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

    	
        // Components
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 10, 5, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.gridwidth = 2;
        JLabel l1 = new JLabel("Cases : ");
        l1.setForeground(Color.white);
        radioButtons.add(l1, c);

        c.insets = new Insets(0, 10, 20, 0);
        c.gridy = 1;
        JLabel l2 = new JLabel("(clic droit pour placer)");
        l2.setForeground(new Color(80, 40, 80));
        radioButtons.add(l2, c);
        
        c.insets = new Insets(3, 10, 0, 0);

        c.gridy = 2;
        c.gridwidth = 1;
        radioButtons.add(new JLabel(herbeIcon), c);
        c.gridx = 1;
        radioButtons.add(herbe, c);

        c.gridx = 0;
        c.gridy = 3;
        radioButtons.add(new JLabel(arbreRougeIcon), c);
        c.gridx = 1;
        radioButtons.add(arbreRouge, c);

        c.gridx = 0;
        c.gridy = 4;
        radioButtons.add(new JLabel(arbreJauneIcon), c);
        c.gridx = 1;
        radioButtons.add(arbreJaune, c);

        c.gridx = 0;
        c.gridy = 5;
        radioButtons.add(new JLabel(arbreVertIcon), c);
        c.gridx = 1;
        radioButtons.add(arbreVert, c);

        c.gridx = 0;
        c.gridy = 6;
        radioButtons.add(new JLabel(rocher1Icon), c);
        c.gridx = 1;
        radioButtons.add(rocher1, c);

        c.gridx = 0;
        c.gridy = 7;
        radioButtons.add(new JLabel(rocher2Icon), c);
        c.gridx = 1;
        radioButtons.add(rocher2, c);

        c.gridx = 0;
        c.gridy = 8;
        radioButtons.add(new JLabel(eauIcon), c);
        c.gridx = 1;
        radioButtons.add(eau, c);

        c.gridx = 0;
        c.gridy = 9;
        radioButtons.add(new JLabel(eauProfondeIcon), c);
        c.gridx = 1;
        radioButtons.add(eauProfonde, c);
        
        c.gridx = 0;
        c.gridy = 10;
        radioButtons.add(new JLabel(fantassinIcon), c);
        c.gridx = 1;
        radioButtons.add(fantassin, c);

        c.gridx = 0;
        c.gridy = 11;
        radioButtons.add(new JLabel(archerIcon), c);
        c.gridx = 1;
        radioButtons.add(archer, c);

        c.gridx = 0;
        c.gridy = 12;
        radioButtons.add(new JLabel(lancierIcon), c);
        c.gridx = 1;
        radioButtons.add(lancier, c);

        c.gridx = 0;
        c.gridy = 13;
        radioButtons.add(new JLabel(pretreIcon), c);
        c.gridx = 1;
        radioButtons.add(pretre, c);
        
        c.gridx = 0;
        c.gridy = 14;
        radioButtons.add(new JLabel(orkIcon), c);
        c.gridx = 1;
        radioButtons.add(ork, c);

        c.gridx = 0;
        c.gridy = 15;
        radioButtons.add(new JLabel(gobelinArcherIcon), c);
        c.gridx = 1;
        radioButtons.add(gobelinArcher, c);

        c.gridx = 0;
        c.gridy = 16;
        radioButtons.add(new JLabel(koboldIcon), c);
        c.gridx = 1;
        radioButtons.add(kobold, c);
        
        c.gridx = 0;
        c.gridy = 17;
        radioButtons.add(new JLabel(gobelinShamanIcon), c);
        c.gridx = 1;
        radioButtons.add(gobelinShaman, c);
        

        c.insets = new Insets(20, 10, 0, 0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 18;
        radioButtons.add(retrecir, c);

        c.insets = new Insets(0, 10, 0, 0);
        c.gridy = 19;
        radioButtons.add(agrandir, c);

		
		
        add(radioButtons, BorderLayout.WEST);
	
        
        panelBas.setLayout(new GridBagLayout());
        c = new GridBagConstraints();

    	
        // Components
        c.insets = new Insets(0, 40, 50, 0);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridheight = 2;
        labelIconCases.setIcon(new ImageIcon(tabCases[0].getScaledInstance(52, 60, Image.SCALE_SMOOTH)));
        panelBas.add(labelIconCases, c);
        
        c.gridx = 1;
        labelCases.setForeground(new Color(240, 230, 255));
        panelBas.add(labelCases, c);

        c.insets = new Insets(0, 40, 50, 40);

        c.gridheight = 1;
        c.gridx = 2;
        c.gridy = 1;
        labelInfosSoldats.setForeground(new Color(240, 230, 255));
        labelInfosSoldats.setText("Héros placés : "+ensHeros.nbSoldats+" / Monstres placés : "+ensMonstres.nbSoldats);
        panelBas.add(labelInfosSoldats, c);
        
        c.gridx = 3;
        c.gridheight = 2;

        labelControls.setForeground(new Color(240, 230, 255));
        labelControls.setText("<html>Clic gauche : se déplacer<br>Clic droit : placer un élément sur la map<br>Scroll : zoomer / dézoomer<html>");
        panelBas.add(labelControls, c);
        

        infosSoldat.setPreferredSize(new Dimension(550, 550));
        infosSoldat.setBorder(BorderFactory.createEmptyBorder(0, 250, 0, 50));
        
        
    }
    
    
    /**
     * Vérifie que les conditions sont respectée pour passer en mode jeu, affiche un message d'erreur sinon
     * @return boolean
     * @since 0.9
     */
    public boolean verifConditions() {
    	if(ensHeros.nbSoldats > 0 && ensMonstres.nbSoldats > 0)
    		return true;
    	else new FenetreInformation(fenetrePrincipale, "Erreur", "Placez au moins un héros et un monstre pour pouvoir lancer la partie.", 2);
    	return false;
    }
		
    
    /**
     * Retir tout Soldat de la map
     * @since 0.9
     */
    public void retirSoldatsMap() {
    	ensHeros.vider();
    	ensMonstres.vider();
    	repaint();
    }
    

    /**
     * Gère les situations ou l'état d'un item a changé.
     * @param e ItemEvent
     * @since 0.2
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
        JRadioButton bouton = (JRadioButton)source;

        if (e.getStateChange() == ItemEvent.SELECTED) {
            bouton.setForeground(new Color(80, 40, 80));
        }
        else bouton.setForeground(Color.white);
		
        if (source == herbe) typeSelect = 0;
        else if (source == arbreRouge) typeSelect = 1;
        else if (source == arbreJaune) typeSelect = 2;
        else if (source == arbreVert) typeSelect = 3;
        else if (source == rocher1) typeSelect = 4;
        else if (source == rocher2) typeSelect = 5;
        else if (source == eau) typeSelect = 6;
        else if (source == eauProfonde) typeSelect = 7;
        else if (source == fantassin) typeSelect = 8;
        else if (source == archer) typeSelect = 9;
        else if (source == lancier) typeSelect = 10;
        else if (source == pretre) typeSelect = 11;
        else if (source == ork) typeSelect = 12;
        else if (source == gobelinArcher) typeSelect = 13;
        else if (source == kobold) typeSelect = 14;
        else if (source == gobelinShaman) typeSelect = 15;
    }
    
    /**
     * Change le type ou le soldat sur la case sur laquelle on a cliqué.
     * @param e MouseEvent: évènement de la souris.
     * @since 0.2
     */
    public void mouseClicked(MouseEvent e) {
    	if(e.getButton() == MouseEvent.BUTTON3) {/* si clic droit */
    		Soldat s;
    		x = e.getX();
    		y = e.getY();    
    		if(typeSelect <= 7) { // type de case
    			s = grille.changeTypeCase(x, y, typeSelect);
    			if (s != null) { // si un soldat à été retiré
    				ensHeros.supprimerSoldat(s); // on le supprime si il est dans ensHeros
    				ensMonstres.supprimerSoldat(s); // on le supprime si il est dans ensMonstres
    			       
    			}
    		}
    		else {// insertion de Soldat
    			s = grille.retirSoldat(x, y); // on retir le potentiel soldat déjà présent
    			if (s != null) { // si un soldat à été retiré
    				ensHeros.supprimerSoldat(s); // on le supprime si il est dans ensHeros
    				ensMonstres.supprimerSoldat(s); // on le supprime si il est dans ensMonstres
    			}
    			s = grille.placeSoldat(x, y, typeSelect); // on insère
    			if (s != null) {
    				if(typeSelect <= 11) { // Héros
    					if (ensHeros.ajouterSoldat(s) == -1) {
    						new FenetreInformation(fenetrePrincipale, "Erreur", "Nombre maximal de Héros atteint.", 2);
    						grille.retirSoldat(x, y);
    					}
    				}
    				else if (ensMonstres.ajouterSoldat(s) == -1) { // Monstres
						new FenetreInformation(fenetrePrincipale, "Erreur", "Nombre maximal de Monstres atteint.", 2);
						grille.retirSoldat(x, y);
    				}
    			}
    		}
    		labelInfosSoldats.setText("Héros placés : "+ensHeros.nbSoldats+" / Monstres placés : "+ensMonstres.nbSoldats);
    	}
        repaint();
    }

    

    /**
     * Gère les actions à effectuer lors d'un déplacement de souris avec le clic enfoncé.
     * Permet le déplacement la grille et facilite le placement manuel des cases
     * @param e MouseEvent : évènement de la souris.
     * @since 0.2
     */
    public void mouseDragged(MouseEvent e) {
    	x2 = x;
    	y2 = y;
    	x = e.getX();
    	y = e.getY();
    	if(clicGauche) { // si clic gauche 
            grille.setPositionX(grille.getPositionX() + x - x2);
            grille.setPositionY(grille.getPositionY() + y - y2);
    	} 
    	else if(clicDroit) { // si clic droit
    		Soldat s;
    		x = e.getX();
    		y = e.getY();    
    		if(typeSelect <= 7) { // type de case
    			s = grille.changeTypeCase(x, y, typeSelect);
    			if (s != null) { // si un soldat à été retiré
    				ensHeros.supprimerSoldat(s); // on le supprime si il est dans ensHeros
    				ensMonstres.supprimerSoldat(s); // on le supprime si il est dans ensMonstres
    			       
    			}
    		}
    		else {// insertion de Soldat
    			s = grille.retirSoldat(x, y); // on retir le potentiel soldat déjà présent
    			if (s != null) { // si un soldat à été retiré
    				ensHeros.supprimerSoldat(s); // on le supprime si il est dans ensHeros
    				ensMonstres.supprimerSoldat(s); // on le supprime si il est dans ensMonstres
    			}
    			s = grille.placeSoldat(x, y, typeSelect); // on insère
    			if (s != null) {
    				if(typeSelect <= 11) { // Héros
    					if (ensHeros.ajouterSoldat(s) == -1) {
    						new FenetreInformation(fenetrePrincipale, "Erreur", "Nombre maximal de Héros atteint.", 2);
    						grille.retirSoldat(x, y);
    					}
    				}
    				else if (ensMonstres.ajouterSoldat(s) == -1) { // Monstres
						new FenetreInformation(fenetrePrincipale, "Erreur", "Nombre maximal de Monstres atteint.", 2);
						grille.retirSoldat(x, y);
    				}
    			}
    		}
    		labelInfosSoldats.setText("Héros placés : "+ensHeros.nbSoldats+" / Monstres placés : "+ensMonstres.nbSoldats);
    	}
    	

        if (x > getSize().width - 450) {
            add(infosSoldat, BorderLayout.WEST);
    	}
    	else {
            add(infosSoldat, BorderLayout.EAST);
    	}

        afficheInfosCase();
    	repaint();
    }


    
    
    /**
     * Gère les actions à effectuer lors d'un clic de souris sur un composant.
     * @param e MouseEvent : évènement de la souris.
     * @since 0.5
     */
    public void mousePressed(MouseEvent e) {
    	if(e.getButton() == MouseEvent.BUTTON1) /* si clic gauche */
            clicGauche = true;
    	if(e.getButton() == MouseEvent.BUTTON3) /* si clic droit */
            clicDroit = true;
    }


    /**
     * Gère les actions à effectuer lors d'un relâchement de clic de souris sur un composant..
     * @param e MouseEvent : évènement de la souris.
     * @since 0.5
     */
    public void mouseReleased(MouseEvent e) {
    	clicGauche = false;
    	clicDroit = false;
    }

    
    
    
	
    /**
     * Redéfinition de la méthode paintComponent de la classe PanneauCreation.
     * @param g Graphics pour l'affichage de la fenêtre.
     * @since 0.
     */
    
    public void paintComponent(Graphics g) {
        grille.setFog(false);
        super.paintComponent(g);
        labelInfosSoldats.setText("Héros placés : "+ensHeros.nbSoldats+" / Monstres placés : "+ensMonstres.nbSoldats);
    }



    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
		
    }
    

}
