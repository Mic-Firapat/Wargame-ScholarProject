package projet;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * PanneauCommun est le panneau contenant toutes les méthodes communes à PanneauJeu et PanneauCreation. Il hérite de JPanel
 * @version 1.1
 */
@SuppressWarnings("serial")
public class PanneauCommun extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener{
	
	
    public static Grille grille = new Grille();
    
    public static EnsembleSoldat ensHeros = new EnsembleSoldat();
    public static  EnsembleSoldat ensMonstres = new EnsembleSoldat();; 
    
    protected FenetreJeu fenetrePrincipale;
    
    protected BufferedImage fondPlateau;
    protected BufferedImage tabCases[];

    protected MiniMap miniMap;
    
    protected JPanel panelBas;
    // Labels qui composeront le panelBas :
    protected JLabel labelIconCases;
    protected JLabel labelCases;
    protected JLabel labelControls;
    protected JLabel labelInfosSoldats;
    protected JLabel labelNbTours;
    
	
    protected JPanel infosSoldat;
    protected LabelInfoSoldat labelHeros;
    protected LabelInfoSoldat labelMonstre;
    
    
    protected int x=0;
    protected int y=0;
    protected int x2=0;
    protected int y2=0;
    
    public static int nbTours = 1;
    


    //protected JLabel label;

    /**
     * Constructeur de la classe PanneauCommun.
     * @param fenetre <code>FenetreJeu</code> la fenêtre principale
     * @param miniMap <code>MiniMap</code> la mini map.
     * @since 0.5
     */
    public PanneauCommun (FenetreJeu fenetre, MiniMap miniMap) {
    	super(new BorderLayout());
        setPreferredSize(new Dimension(1400, 650));
        setOpaque(true);
        setBackground(new Color(106, 123, 106));
        
        fenetrePrincipale = fenetre;
        
        this.miniMap = miniMap;
        miniMap.lier(grille);
       
        
        tabCases = new BufferedImage[25];
        try {
            tabCases[0] = ImageIO.read(this.getClass().getResource("images/cases/herbe.png"));	
            tabCases[1] = ImageIO.read(this.getClass().getResource("images/cases/arbre_rouge.png"));	
            tabCases[2] = ImageIO.read(this.getClass().getResource("images/cases/arbre_jaune.png"));
            tabCases[3] = ImageIO.read(this.getClass().getResource("images/cases/arbre_vert.png"));
            tabCases[4] = ImageIO.read(this.getClass().getResource("images/cases/rocher1.png"));
            tabCases[5] = ImageIO.read(this.getClass().getResource("images/cases/rocher2.png"));
            tabCases[6] = ImageIO.read(this.getClass().getResource("images/cases/eau.png"));
            tabCases[7] = ImageIO.read(this.getClass().getResource("images/cases/eau_profonde.png"));
            tabCases[8] = ImageIO.read(this.getClass().getResource("images/cases/fantassin.png"));
            tabCases[9] = ImageIO.read(this.getClass().getResource("images/cases/archer.png"));
            tabCases[10] = ImageIO.read(this.getClass().getResource("images/cases/lancier.png"));
            tabCases[11] = ImageIO.read(this.getClass().getResource("images/cases/pretre.png"));
            tabCases[12] = ImageIO.read(this.getClass().getResource("images/cases/ork.png"));
            tabCases[13] = ImageIO.read(this.getClass().getResource("images/cases/gobelin_archer.png"));
            tabCases[14] = ImageIO.read(this.getClass().getResource("images/cases/kobold.png"));
            tabCases[15] = ImageIO.read(this.getClass().getResource("images/cases/gobelin_shaman.png"));
            tabCases[16] = ImageIO.read(this.getClass().getResource("images/cases/fantassin_selectionne.png"));
            tabCases[17] = ImageIO.read(this.getClass().getResource("images/cases/archer_selectionne.png"));
            tabCases[18] = ImageIO.read(this.getClass().getResource("images/cases/lancier_selectionne.png"));
            tabCases[19] = ImageIO.read(this.getClass().getResource("images/cases/pretre_selectionne.png"));
            
            
            fondPlateau = ImageIO.read(this.getClass().getResource("images/menus/fond_plateau.png"));
			

        }catch (Exception e) {
            System.out.println("Images introuvables.");
            e.printStackTrace();
        }
        
        labelIconCases = new JLabel();
        labelIconCases.setPreferredSize(new Dimension(60, 60));
        
        labelCases = new JLabel("Case : (            ,             ,            )");
        labelCases.setFont(new Font("Serif", Font.BOLD, 14));
        labelCases.setPreferredSize(new Dimension(300, 75));
        
        labelControls = new JLabel();
        labelControls.setFont(new Font("Serif", Font.BOLD, 14));
        
        labelInfosSoldats = new JLabel();
        labelInfosSoldats.setFont(new Font("Serif", Font.BOLD, 14));
        
        labelNbTours = new JLabel();
        labelNbTours.setFont(new Font("Serif", Font.BOLD, 14));
        
        
        
        
        
        infosSoldat = new JPanel();
        infosSoldat.setPreferredSize(new Dimension(350, 550));
        infosSoldat.setOpaque(false);
        add(infosSoldat, BorderLayout.EAST);
        infosSoldat.setLayout(new BoxLayout(infosSoldat, BoxLayout.LINE_AXIS));
        
        labelHeros = new LabelInfoSoldat(1);
    	infosSoldat.add(labelHeros);

        labelMonstre = new LabelInfoSoldat(2);
    	infosSoldat.add(labelMonstre);
        
        
        
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        
    }
    
    
    /**
     * Affiche les infos liées à case actuellement survolé.
     * @since 0.1
     */
    public void afficheInfosCase() {
        Case c = grille.chercheCase(x, y);
        if (c == null) {
        	labelIconCases.setIcon(null);
            labelCases.setText("Case : (           ,            ,           )");
            }
        else {
        	labelIconCases.setIcon(new ImageIcon(tabCases[c.getType()].getScaledInstance(52, 60, Image.SCALE_SMOOTH)));
            labelCases.setText(""+c);
            if (c.getHeros() != null) {
                remplirLabelHeros(c.getHeros());
                labelHeros.setVisible(true);
            }
            else 
                labelHeros.setVisible(false);
            if (c.getMonstre() != null && (!grille.getFog() || grille.getVisible().containsKey(c))){
                remplirLabelMonstre(c.getMonstre());
                labelMonstre.setVisible(true);
            }
            else 
                labelMonstre.setVisible(false);
        }
    }

    /**
     * Remplit le label avec les informations du héros.
     * @param h <code>Heros</code>
     * @since 0.5
     */
    public void remplirLabelHeros(Heros h) {
    	infosSoldat.setVisible(false); // pour actualiser
    	infosSoldat.setVisible(true);
    	labelHeros.afficher(h);
    }

    /**
     * Remplit le label avec les informations du monstre.
     * @param m <code>Monstre</code>
     * @since 0.5
     */
    public void remplirLabelMonstre(Monstre m) {
    	infosSoldat.setVisible(false); // pour actualiser
    	infosSoldat.setVisible(true);
    	labelMonstre.afficher(m);
    }



    /**
     * Rafraicht l'affichage lors d'un mouvement de souris.
     * @param e MouseEvent : évènement de la souris.
     * @since 0.0
     */
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        
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
     * Gère les actions à effectuer lors d'un déplacement de souris avec le clic gauche enfoncé.
     * Permet le déplacement de la grille
     * @param e MouseEvent : évènement de la souris.
     * @since 0.2
     */
    public void mouseDragged(MouseEvent e) {
    	x2 = x;
        y2 = y;
        x = e.getX();
        y = e.getY();
        grille.setPositionX(grille.getPositionX() + x - x2);
        grille.setPositionY(grille.getPositionY() + y - y2);
		
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
     * Permet de zoomer et dézoomer grace à la molette de la souris
     * @param e MouseEvent : évènement de la souris.
     * @since 0.2
     */
    public void mouseWheelMoved(MouseWheelEvent e) {
        Case c = grille.chercheCase(x, y);
        if(!(c == null)) {
            int cX = c.getAxialX();
            int cY = c.getAxialY();
            grille.changeZoom(e.getUnitsToScroll());
            grille.calculNouvellePosition(c, cX, cY);
            repaint();
        }
    }
	
    /**
     * Méthode paintComponent pour effecuer l'affichage.
     * @param g Graphics pour l'affichage de la fenêtre.
     * @since 0.2
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondPlateau, 0, 0, 2000, 1500, this);
        miniMap.repaint();

        grille.afficherGrille(g, tabCases);
        grille.afficheSurvol(x, y, g);
        
    }
    


    /**
     * Gère les actions à effectuer lors d'un clic de souris sur un composant.
     * @param e MouseEvent: évènement de la souris.
     * @since 0.0
     * Ne fait actuellement rien (pas le cas dans les classes filles).
     */
    public void mouseClicked(MouseEvent e) {}
    
    /**
     * Gère les actions à effectuer lors d'un clic de souris sur un composant.
     * @param e MouseEvent : évènement de la souris.
     * @since 0.0
     * Ne fait actuellement rien (pas le cas dans les classes filles).
     */
    public void mousePressed(MouseEvent e) {}


    /**
     * Gère les actions à effectuer lors d'un relâchement de clic de souris sur un composant..
     * @param e MouseEvent : évènement de la souris.
     * @since 0.0
     * Ne fait actuellement rien (pas le cas dans les classes filles).
     */
    public void mouseReleased(MouseEvent e) {}

    /**
     * Méthode qui gère les actions à effectuer quand la souris entre dans un composant.
     * @param e MouseEvent : évènement de la souris.
     * @since 0.0
     * Ne fait actuellement rien (pas le cas dans les classes filles).
     */
    public void mouseEntered(MouseEvent e) {}
    
    /**
     * Méthode qui gère les actions à effectuer quand la souris sort d'un composant.
     * @param e MouseEvent : évènement de la souris.
     * @since 0.0
     * @deprecated Ne fait actuellement rien
     */
    @Deprecated
    public void mouseExited(MouseEvent e) {}
	
}
