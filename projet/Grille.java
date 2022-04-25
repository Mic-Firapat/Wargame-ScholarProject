package projet;

import java.awt.*;
import java.util.*;

import javax.swing.JFrame;

import java.awt.image.BufferedImage;

import java.io.Serializable;
//import java.io.ObjectOutputStream;
//import java.io.ObjectInputStream;

/**
 * Grille est la classe représentant la grille hexagonale.
 * @version 1.1
 */
public class Grille extends Config implements Serializable{

    /* Code d'identifiant généré aléatoirement avant d'être instancié
     * Produit en suivant la méthode interne des JVM
     * Utilisé par java pour donner un numéro de série unique aux objets
     * En théorie empêche un enregistrement de doublon
     * Permet d'assurer que l'objet en enregistré dans un fichier correspond
     * à la version qui cherche à le récupérer
     */
    private static final long serialVersionUID = 15846215L;
    
    private Case grille[][];
    private final int NB_CASES;

    protected  int longueurGrille =  30;
    protected  int hauteurGrille = 15;

    //Utilisées pour l'affichage du brouillard de guerre et pour les positions des ennemis
    //Cases visibles par les Héros
    private HashMap<Case,Case> liste_visibles = new HashMap<Case, Case>();
    //Cases visibles par les ennemies.
    private HashMap<Case,Case> liste_visibles_ennemis = new HashMap<Case,Case>();
    //Positions des persos
    private HashMap<Case,Case> ennemis = new HashMap<Case, Case>();
    private HashMap<Case,Case> heros =  new HashMap<Case,Case>();
    private boolean fog = false; //true si en jeu, false si en création

    /**
     * Constructeur de la classe Grille.
     * @since 0.0
     */
    public Grille() {
        NB_CASES = (LONGUEUR_MAX_GRILLE+hauteurGrille/2)*HAUTEUR_MAX_GRILLE;
        grille = new Case[NB_CASES][NB_CASES];
        calculTailleHexagone();
        initialiserGrille();
    }
    
    /**
     * Fonction d'initialisation de la Grille
     * @since 0.0
     */
    public void initialiserGrille() {
        int j;
        int debut = HAUTEUR_MAX_GRILLE/2, fin = LONGUEUR_MAX_GRILLE+HAUTEUR_MAX_GRILLE/2;
        int x = 0, y = 0, z = 0;
		
        for(int i = 0; i < HAUTEUR_MAX_GRILLE; i++){
            for (j = 0; j < debut; j++) {
                grille[i][j] = new Case(-1, -1, -1); /*remplissage*/
            }
            for (j = debut; j < fin; j++) {
                grille[i][j] = new Case(x, y, z);
                x++;
                y--;
            }
            for (j = fin; j < LONGUEUR_MAX_GRILLE+HAUTEUR_MAX_GRILLE/2; j++) {
                grille[i][j] = new Case(-1, -1, -1); /*remplissage*/
            }
            
            
            x = grille[i][debut].getX();
            if(i%2==1) {
                debut--;
                fin--;
                x--;
            }
            y = grille[i][debut+1].getY();
            z++;
        }	
    }

    /**
     * Fonction qui sera appelée pour randomniser la grille
     * 
     *
     * @since 1.1
     */

    public void randomGrille(){
	int j;
        int debut = HAUTEUR_MAX_GRILLE/2, fin = LONGUEUR_MAX_GRILLE+HAUTEUR_MAX_GRILLE/2;
        int x = 0, y = 0, z = 0;

	for(int i = 0; i < HAUTEUR_MAX_GRILLE; i++){
	    for (j = debut; j < fin; j++) {
		grille[i][j] = new Case(x, y, z);
		x++;
		y--;
	    }  
            x = grille[i][debut].getX();
            if(i%2==1) {
                debut--;
                fin--;
                x--;
            }
            y = grille[i][debut+1].getY();
            z++;
	}
	x = 0;
	y = 0;
	z = 0;
	debut = HAUTEUR_MAX_GRILLE / 2;
	fin =  longueurGrille + HAUTEUR_MAX_GRILLE / 2;

	for(int i = 0; i < hauteurGrille; i++){
	    for (j = debut; j < fin; j++) {
		if ( (grille[i][j].getType() != 4) && (grille[i][j].getType() != 5)){
		    this.changeTypeCase((x + (((int)(Math.random() * 5)+1 ) * positionGrilleX)),(y + (((int)(Math.random() * 5)+1 ) * positionGrilleY)), 6);
		}
	    }
	    x = grille[i][debut].getX();
            if(i%2==1) {
                debut--;
                fin--;
                x--;
            }
            y = grille[i][debut+1].getY();
            z++;
	}
	
	
    }

    

    /*------------------------------ Getteurs --------------------------------*/
    /**
     * Renvoie le dico permettant de récupérer les cases occupées par des Héros.
     * @return <code>HashMap</code>
     * @since 0.7
     */
    public HashMap<Case,Case> getPosHeros(){
        return heros;
    }

    /**
     * Renvoie le dico permettant de récupérer les cases occupées par des Monstres.
     * @return <code>HashMap</code>
     * @since 0.7
     */
    public HashMap<Case,Case> getPosEnnemis(){
        return ennemis;
    }
    /**
     * Renvoie le dico indiquant les cases visibles par les ennemis.
     * @return <code>HashMap</code>
     * @since 0.6
     */
    public HashMap<Case,Case> getVisibleEnnemis(){
        return liste_visibles_ennemis;
    }
    
    /**
     * Renvoie le dico indiquant les cases visibles par le joueur.
     * @return <code>HashMap</code>
     * @since 0.6
     */
    public HashMap<Case,Case> getVisible(){
        return liste_visibles;
    }
    
    /**
     * Renvoie la valeur décrivant l'état du brouillard de guerre.
     * @return <code>boolean</code>
     * @since 0.6
     */
    public boolean getFog(){
        return fog;
    }
    /**
     * Renvoie l'entier décrivant la hauteur de la grille (en nombre de cases)
     * @return hauteurGrille <code>int</code>
     * @since 0.5
     */
    public int getHauteur() {
		return hauteurGrille;
	}
	
    /**
     * Renvoie l'entier décrivant la longueur de la grille (en nombre de cases)
     * @return hauteurGrille <code>int</code>
     * @since 0.5
     */
	public int getLongueur() {
		return longueurGrille;
	}
	
	/**
     * Renvoie l'entier décrivant l'abscisse de la première case de la grille
     * @return positionGrilleX <code>int</code>
     * @since 0.5
     */
	public int getPositionX() {
		return positionGrilleX;
	}
	
	/**
     * Renvoie l'entier décrivant l'ordonné de la première case de la grille
     * @return positionGrilleY <code>int</code>
     * @since 0.5
     */
	public int getPositionY() {
		return positionGrilleY;
	}
	
	
    /**
     * Renvoie un pointeur sur l'objet Case aux coordonnées x,y,z.
     * @param x valeur entière de la coordonnée x.
     * @param y valeur entière de la coordonnée y.
     * @param z valeur entière de la coordonnée z.
     * @return un pointeur sur la case aux coordonnées x,y,z.
     * @since 0.0
     */
    public Case getCase(int x, int y, int z) {
        int i = z;
        int j = x+HAUTEUR_MAX_GRILLE/2;
        if(i<0 || i>=hauteurGrille || j<0 || j>=longueurGrille+(int)((HAUTEUR_MAX_GRILLE-i)/2.+0.5) || grille[i][j].getZ()==-1)
            return null;
        return grille[i][j];
    }

	
	

    /*----------------------------- Setteurs -----------------------------*/
	
    /**
     * Modifie le booléen indiquant si le brouillard de guerre doit être utilisé
     * @param b <code>boolean</code> nouvelle valeur de fog.
     * @since 0.6
     */
    public void setFog(boolean b){
        fog = b;
    }
    
    /**
     * Modifie l'entier décrivant la longueur de la grille (en nombre de cases)
     * @param hauteur <code>int</code> nouvelle hauteur
     * @return -1 en cas d'erreur
     * @since 0.5
     */
    public int setHauteur(int hauteur) {
        if (hauteur <= HAUTEUR_MAX_GRILLE && hauteur >=3){
            hauteurGrille = hauteur;
            return 0;
        }
        else if (hauteur > HAUTEUR_MAX_GRILLE){
            hauteurGrille = HAUTEUR_MAX_GRILLE;
            return -1;
        }
        else {
            hauteurGrille = 3;
            return -1;
        }
    }
	
	/**
     * Modifie l'entier décrivant la longueur de la grille (en nombre de cases)
     * @param longueur <code>int</code> nouvelle longueur
     * @return -1 en cas d'erreur
     * @since 0.5
     */
	public int setLongueur(int longueur) {
            if (longueur <= LONGUEUR_MAX_GRILLE && longueur >= 3){
            	longueurGrille = longueur;
            	return 0;
            }
            else if (longueur > LONGUEUR_MAX_GRILLE){
                longueurGrille = LONGUEUR_MAX_GRILLE;
                return -1;
            }
            else {
                longueurGrille =  3;
                return -1;
            }
	}
	

	/**
     * Modifie l'entier décrivant l'abscisse de la première case de la grille
     * @param x <code>int</code> nouvel abscisse
     * @since 0.5
     */
	public void setPositionX(int x) {
		if (x <=  1000 && x >= -longueurGrille*longueurHexagone-750){
			positionGrilleX = x;
		}
		else if (x > 1000){
			positionGrilleX = 1000;
        }
        else {
        	positionGrilleX = -longueurGrille*longueurHexagone-750;
        }
	}
	
	/**
     * Modifie l'entier décrivant l'ordonné de la première case de la grille
     * @param y <code>int</code> nouvelle ordonnée
     * @since 0.5
     */
	public void setPositionY(int y) {
		if (y <=  400 && y >= -hauteurGrille*hauteurHexagone-100){
			positionGrilleY = y;
		}
		else if (y > 400){
			positionGrilleY = 400;
        }
        else {
        	positionGrilleY = -hauteurGrille*hauteurHexagone-100;
        }
	}
    
    
    
	/*------------------------- Gestion du zoom --------------------------*/
    
    
    /**
     * Calcule le nouveau zoom
     * @param z : valeur de zoom ajoutér ou soustraite
     * @since 0.2
     */
	public void changeZoom(int z) {
		zoom = zoom - z*2;
		if(zoom<0) zoom = 0;
		if(zoom>100) zoom = 100;
		calculTailleHexagone();
	}
	
	
	/**
     * Calcule la nouvelle taille de l'hexagone après une modification du zoom
     * @since 0.2
     */
	public static void calculTailleHexagone() {
		hauteurHexagone = zoom*40/100 +10;
		longueurHexagone = (int)((Math.sqrt(3)*hauteurHexagone)/2.+1);
	}
    
    
    
    /**
     * Calcule et modifie la position de la grille après une modification du zoom pour éviter un décalage
     * @param c Case pointée par la souris avant le décalage du zoom.
     * @param ancienHX : ancienne coordonnée x de cette case
     * @param ancienHY : ancienne coordonnée y de cette case
     * @since 0.2
     */
    public void calculNouvellePosition(Case c, int ancienHX, int ancienHY) {
		positionGrilleX = positionGrilleX + ancienHX - c.getAxialX();
		positionGrilleY = positionGrilleY + ancienHY - c.getAxialY();
    }
    
    
    
    /*--------------------------- Voisins -----------------------------*/
    
    
    /**
     * Renvoie la liste des voisins de la Case passée en paramètre.
     * <code>null</code> pour les Cases non valides.
     * @param c <code>Case</code> dont on veut les voisins.
     * @return liste des voisins de longueur 6
     * @since 0.1
     */
    public Case[] voisins(Case c){
        Case[] voisins = {voisinDroit(c), voisinGauche(c), voisinBasDroit(c), voisinHautDroit(c), voisinBasGauche(c), voisinHautGauche(c)};
        return voisins;
    }
    
    /**
     * Renvoie un pointeur sur l'objet Case étant le voisin droit du paramètre.
     * @param c <code>Case</code> dont on veut le voisin.
     * @return le voisin ou <code>null</code> si pas valide.
     * @since 0.0
     */
    public Case voisinDroit(Case c) {
        return getCase(c.getX()+1, c.getY()-1, c.getZ());
    }

    /**
     * Renvoie un pointeur sur l'objet Case étant le voisin bas-droit du paramètre.
     * @param c Case dont on veut le voisin.
     * @return le voisin ou <code>null</code> si pas valide.
     * @since 0.0
     */
    public Case voisinBasDroit(Case c) {
        return getCase(c.getX(), c.getY()-1, c.getZ()+1);
    }
    
    /**
     * Renvoie un pointeur sur l'objet Case étant le voisin bas-gauche du paramètre.
     * @param c Case dont on veut le voisin.
     * @return le voisin ou <code>null</code> si pas valide.
     * @since 0.0
     */
    public Case voisinBasGauche(Case c) {
        return getCase(c.getX()-1, c.getY(), c.getZ()+1);
    }

    /**
     * Renvoie un pointeur sur l'objet Case étant le voisin gauche du paramètre.
     * @param c Case dont on veut le voisin.
     * @return le voisin ou <code>null</code> si pas valide.
     * @since 0.0
     */
    public Case voisinGauche(Case c) {
        return getCase(c.getX()-1, c.getY()+1, c.getZ());
    }

    /**
     * Renvoie un pointeur sur l'objet Case étant le voisin haut-gauche du paramètre.
     * @param c Case dont on veut le voisin.
     * @return le voisin ou <code>null</code> si pas valide.
     * @since 0.0
     */
    public Case voisinHautGauche(Case c) {
        return getCase(c.getX(), c.getY()+1, c.getZ()-1);
    }

    /**
     * Renvoie un pointeur sur l'objet Case étant le voisin haut-droit du paramètre.
     * @param c Case dont on veut le voisin.
     * @return le voisin ou <code>null</code> si pas valide.
     * @since 0.0
     */
    public Case voisinHautDroit(Case c) {
        return getCase(c.getX()+1, c.getY(), c.getZ()-1);
    }
    
    
    /*------------------------- Détection ---------------------------*/
    
    
    
    /** 
     * Retourne la case de la Grille pointée par la souris (coordonnées x, y) ou null si aucune case pointée.
     * @param x coordonnée x du curseur de la souris.
     * @param y coordonnée y du curseur de la souris.
     * @return un pointeur sur un objet Case correspondant à la case pointée actuellement.
     * @since 0.0
     */
    public Case chercheCase(int x, int y) {
        
        // on recentre
        x = x - positionGrilleX;
        y = y - positionGrilleY;
	
        double cX = ((Math.sqrt(3)/3 * x - 1./3 * y)/hauteurHexagone+0.5);
        double cY = (-(Math.sqrt(3)/3 * x + 1./3 * y)/hauteurHexagone+0.5);
        double cZ = (y*2./3/hauteurHexagone+0.5);

        // pour éviter les problèmes d'arrondit lors de la conversion en entier
        if(cX<0)cX--;
        if(cY<0)cY--;
        if(cZ<0)cZ--;
	
        // pour corriger les zones ambigues 
        if(Math.abs(0.5-Math.abs(cX%1)) > Math.abs(0.5-Math.abs(cY%1))) {
            if(Math.abs(0.5-Math.abs(cX%1)) > Math.abs(0.5-Math.abs(cZ%1))) {
                cX = -((int)cY+(int)cZ);
            }
            else cZ = -((int)cX+(int)cY);
        }

        return getCase((int)cX, (int)cY, (int)cZ);
    }

    

    /*--------------------------- Affichage -----------------------------*/
    
    
    /**
     * Fonction d'affichage de la grille dans la fenêtre.
     * Appelle afficherGrilleFog si fog = true.
     * @param g <code>Graphics</code> pour l'affichage.
     * @param tabImages <code>BufferedImage[]</code> un tableau d'images.
     * @since 0.2
     */
    public void afficherGrille(Graphics g, BufferedImage[] tabImages) {
        if (fog){
            afficherGrilleFog(g,tabImages);
        }
        else{
            int debut = HAUTEUR_MAX_GRILLE/2, fin = longueurGrille+HAUTEUR_MAX_GRILLE/2;
            ennemis = new HashMap<Case, Case>();
            heros = new HashMap<Case, Case>();
		
            
            for(int i = 0; i < hauteurGrille; i++){
                
                for (int j = debut; j < fin; j++) {
                    if (grille[i][j].getZ() != -1) { // Pour éviter les cases de remplissage
            		
            		// si il y a un héros
            		Heros h = grille[i][j].getHeros();
                        
            		if(h != null){
                            heros.put(grille[i][j],null);
                            grille[i][j].afficheCase(g, tabImages[h.getTU()+7]);
                        }
            		
            		else {
                            // si il y a un monstre
                            Monstre m = grille[i][j].getMonstre();
                            if(m != null) {
                                ennemis.put(grille[i][j],null);
                                grille[i][j].afficheCase(g, tabImages[m.getTU()+11]);
                            }
                            
                            // si il n'y a personne
                            else grille[i][j].afficheCase(g, tabImages[grille[i][j].getType()]);
            		}
                    }
                }
                if(i%2==1) {
                    debut--;
                    fin --;
                }
            }
        }
    }
    

    /**
     * Fonction d'affichage de la grille avec brouillard de guerre. (Fog = Fog Of War)
     * @param g <code>Graphics</code> pour l'affichage.
     * @param tabImages <code>BufferedImage[]</code> un tableau d'images.
     * @since 0.6
     */
    public void afficherGrilleFog(Graphics g, BufferedImage[] tabImages){
        int debut = HAUTEUR_MAX_GRILLE/2, fin = longueurGrille+HAUTEUR_MAX_GRILLE/2;
        HashMap<Case,Case> o;
        liste_visibles = new HashMap<Case,Case>();
        liste_visibles_ennemis = new HashMap<Case,Case>();
        ennemis = new HashMap<Case, Case>();
        heros = new HashMap<Case, Case>();
        for(int i = 0; i < hauteurGrille; i++){
            
            for (int j = debut; j < fin; j++) {
            	if (grille[i][j].getZ() != -1) { // Pour éviter les cases de remplissage
                    
                    // si il y a un héros
            		Heros h = grille[i][j].getHeros();
            		if(h != null) {
                            heros.put(grille[i][j],null);
                            grille[i][j].afficheCase(g, tabImages[h.getTU()+7]);
                            //Si il peut plus jouer on le grise
                            if (!h.peutJouer()){
                                grille[i][j].afficheCaseSelectionnee(g, Color.black);
                            }
                            /*On récupère les cases visibles depuis ce Héros*/
                            o = recherche(grille[i][j],null,h.getPMmax() + h.getPO(), true);
                            for (Case cle : o.keySet()){
                                if (!liste_visibles.containsKey(cle) && visable(grille[i][j],cle,o)){ //visable prend en compte les obstacles
                                    liste_visibles.put(cle,null);
                                }
                            }
                        }
            		
            		else {
            			// si il y a un monstre
            			Monstre m = grille[i][j].getMonstre();
            			if(m != null) {
                                    //Si le monstre est visible on l'affiche de suite
                                    if (liste_visibles.containsKey(grille[i][j])){
                                        grille[i][j].afficheCase(g, tabImages[m.getTU()+11]);
                                    }
                                    //On se rappelle de sa position pour l'afficher si il est visible à la fin.
                                    ennemis.put(grille[i][j],null);
                                    /*On récupère les cases visibles depuis ce Monstre*/
                                    o = recherche(grille[i][j],null,m.getPMmax() + m.getPO(), true);
                                    for (Case cle : o.keySet()){
                                        if (!liste_visibles_ennemis.containsKey(cle) && visable(grille[i][j],cle,o)){ //visable prend en compte les obstacles
                                            liste_visibles_ennemis.put(cle,null);
                                        }
                                    }
                                }
            			
            			// si il n'y a personne
            			else grille[i][j].afficheCase(g, tabImages[grille[i][j].getType()]);
            		}
            	}
            }
            if(i%2==1) {
                debut--;
                fin --;
            }
        }
        /*Affichage des ennemis visibles*/
        for (Case cle : ennemis.keySet()){
            if (liste_visibles.containsKey(cle)){
                cle.afficheCase(g,tabImages[cle.getMonstre().getTU()+11]);
            }
            //Ennemi non visible, on affiche la case
            else {
                cle.afficheCase(g, tabImages[cle.getType()]);
            }
        }
        /*On assombrit les cases non visibles*/
        debut = HAUTEUR_MAX_GRILLE/2;
        fin = longueurGrille+HAUTEUR_MAX_GRILLE/2;
        for(int i = 0; i < hauteurGrille; i++){
            for (int j = debut; j < fin; j++) {
                if (grille[i][j].getZ() != -1) { // Pour éviter les cases de remplissage
                    if (!liste_visibles.containsKey(grille[i][j])){
                        grille[i][j].afficheBrouillardCase(g);
                    }
                }
            }
            if(i%2==1) {
                debut--;
                fin --;
            }
        }
    }
    


    /**
     * Fonction d'affichage de la mini-map dans la fenêtre.
     * @param g <code>Graphics</code> pour l'affichage.
     * @param tailleMiniCases <code>int</code> taille des mini-cases.
     * @param positionX <code>int</code> abscisse de la première mini-case.
     * @param positionY <code>int</code> ordonnee de la première mini-case.
     * @param tabImages <code>BufferedImage[]</code> un tableau d'images.
     * @since 0.5
     */
    public void afficherMiniMap(Graphics g, int tailleMiniCases, int positionX, int positionY, BufferedImage[] tabImages) {
    	int debut = HAUTEUR_MAX_GRILLE/2, fin = longueurGrille+HAUTEUR_MAX_GRILLE/2;
        for(int i = 0; i < hauteurGrille; i++){
            
            for (int j = debut; j < fin; j++) {
            	if (grille[i][j].getZ() != -1) {// Pour éviter les cases de remplissage
            		grille[i][j].afficheMiniCase(g, tailleMiniCases, positionX, positionY, tabImages[grille[i][j].getType()]);

            		// si il y a un héros
            		Heros h = grille[i][j].getHeros();
            		if(h != null) grille[i][j].afficheMiniCase(g, tailleMiniCases, positionX, positionY, tabImages[h.getTU()+7]);
            		
            		else {
            			// si il y a un monstre
            			Monstre m = grille[i][j].getMonstre();
            			if(m != null && (fog == false || liste_visibles.containsKey(grille[i][j]))) grille[i][j].afficheMiniCase(g, tailleMiniCases, positionX, positionY, tabImages[m.getTU()+11]);
            			
            			// si il n'y a personne
            			else grille[i][j].afficheMiniCase(g, tailleMiniCases, positionX, positionY, tabImages[grille[i][j].getType()]);
            		}
            	}
            }
            if(i%2==1) {
                debut--;
                fin --;
            }
        }
    }
    
    
    /**
     * Affiche de manière particulière la case qu'on survole à la souris (hover).
     * @param x coordonnée x du curseur de la souris.
     * @param y coordonnée y du curseur de la souris.
     * @param g Graphics pour l'affichage dans la fenêtre.
     * @since 0.0
     */
    public void afficheSurvol(int x, int y, Graphics g) {
        Case c = chercheCase(x, y);
        if(!(c == null)) {
            c.afficheCaseSelectionnee(g, Color.red);
        }
	
    }
    
    
    /**
     * Si la case contient une unité, affiche sa portée de déplacement et les cases qu'elle peut attaquer (pour les Heros).
     * @param c <code>Case</code> la case à traiter.
     * @param g Graphics pour l'affichage dans la fenêtre.
     * @param tabImages <code>BufferedImage[]</code>
     * @since 0.5
     */
    public void afficheMobilite(Case c,  Graphics g,  BufferedImage[] tabImages) {
        if(!(c == null)) {
            HashMap<Case,Case> o; //Portée de déplacement
            HashMap<Case,Case> a; //Portée d'attaque + déplacement à vol d'oiseau
            Heros h = c.getHeros();
            Case[] voisins;
            if (h != null){
                o = recherche(c,null,h.getPM(),false);
                a = recherche(c,null,h.getPM() + h.getPO(), true);
                for (Case cle : a.keySet()){
                    if (o.containsKey(cle) && cle.getHeros() == null){
                        //La case est atteignable et vide
                        cle.afficheCaseSelectionnee(g, Color.cyan);
                    }
                    else {
                        voisins = voisins(cle);
                        for (Case v : voisins){
                            if (o.containsKey(v) && v.getHeros() == null){
                                //Une case voisine de la case non atteignable est atteignable et vide
                                if (cle.getMonstre() != null && h.getnbAT() > 0){
                                    //Monstre à portée
                                    cle.afficheCaseSelectionnee(g, Color.red);
                                }
                                else if (cle.getHeros() != null){
                                    if (h.getnbHEAL() > 0){
                                        //Allié qu'on peut soigner
                                        cle.afficheCaseSelectionnee(g, Color.green);
                                    }
                                }
                                else if (cle.traversable() && h.getnbAT() > 0){
                                    //Case à portée de frappe
                                    cle.afficheCaseSelectionnee(g, Color.red);
                                }
                                voisins = null;
                                break;
                            }
                            }
                        if (voisins != null && h.getPO() > 1){
                            //Aucune case voisine de la case non atteignable ne l'est
                            HashMap<Case,Case> v; //Pour savoir si la case est visable depuis une case atteignable
                            for (Case cle2 : o.keySet()){
                                //Pour chaque case atteignable
                                if (cle2.getHeros() == null || cle2 == c){//Le héros peut aller sur cette case
                                    v = recherche(cle2,cle,h.getPO(),true);
                                    if (cle.traversable() && visable(cle2,cle,v)){
                                        //Case non atteignable mais à portée et pouvant contenir une unité
                                        if (cle.getHeros() != null){
                                            //La case contient un autre heros
                                            if (h.getnbHEAL() > 0){
                                                //Le Heros courant peut soigner cet allié
                                                cle.afficheCaseSelectionnee(g, Color.green);
                                            }
                                        }
                                        else if (cle.getMonstre() != null && h.getnbAT() >0){
                                            //Le héros peut attaquer à distance et frapper cet ennemi
                                            cle.afficheCaseSelectionnee(g, Color.red);
                                        }
                                        else if (h.getnbAT() > 0){
                                            //Une case vide mais à portée de frappe
                                            cle.afficheCaseSelectionnee(g, Color.red);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                //La case du Heros mise en valeur
                c.afficheCase(g, tabImages[h.getTU()+15]);
            }
            /*Pour les monstres on affiche juste la portée de déplacement en brut 
              qui ne prend en compte que les obstacles et pas d'éventuels alliés en 
              arrière garde qui serait ici en surprise...*/
            else if (c.getMonstre() != null && (!fog || liste_visibles.containsKey(c))){
                o = recherche(c,null,c.getMonstre().getPM(),false);
                for (Case cle : o.keySet()){
                    cle.afficheCaseSelectionnee(g, new Color(100, 0, 70));
                }
            	c.afficheCaseSelectionnee(g, Color.red);
            }
        }	
    }

    /**
     * Affiche la portée de déplacement de l'unité sur la case et les cases qu'elle peut attaquer.
     * @param x coordonnée x du curseur de la souris.
     * @param y coordonnée y du curseur de la souris.
     * @param g <code>Graphics</code> pour l'affichage.
     * @param tabImages <code>BufferedImage[]</code>
     * @since 0.5
     */
    public void afficheMobilite(int x, int y, Graphics g,  BufferedImage[] tabImages) {
        afficheMobilite(chercheCase(x,y),g, tabImages);
    }

    
    /**
     * Change la position de la grille pour que la case soit centée dans la fenêtre
     * @param c <code>Case</code> case sur laquelle centrée
     * @param f <code>Fenetre</code> 
     * @since 0.9
     */
    public void centrer(Case c, JFrame f) {
    	zoom = 50;
    	calculTailleHexagone();
    	positionGrilleX = positionGrilleX-c.getAxialX()+(f.getSize().width/2);
    	positionGrilleY = positionGrilleY-c.getAxialY()+(f.getSize().height/4);
    }
    

    /**
     * Change le type et supprime l'éventuel soldat sur la case sous le curseur (dans le mode création).
     * @param x coordonnée x du curseur de la souris.
     * @param y coordonnée y du curseur de la souris.
     * @param type <code>int</code> pour la nouveau type.
     * @return Soldat en cas de retrait d'un soldat, null sinon
     * @since 0.2
     */
    public Soldat changeTypeCase(int x, int y, int type) {
        Case c = chercheCase(x, y);
        if(!(c == null)) {
            if(type <= 7) { 
                c.setType(type);
                return retirSoldat(c);
            }
            else return null;
        }
        else return null;
    }
    
    
    /**
     * Retir un heros/monstre de la case passée en paramètre.
     * @param c <code>Case</code>
     * @return le soldat retiré
     * @since 0.9
     */
    public Soldat retirSoldat(Case c) {
    	Heros h;
    	Monstre m;
    	if(!(c == null)) {
    		h = c.getHeros();
    		if(h != null) {
    			c.setHeros(null);
    			return h;
    		}
    		m = c.getMonstre();
    		if(m != null) {
    			c.setMonstre(null);
    			return m;
    		}
    		return null;	
    	}
    	return null;
    }
    
    /**
     * Retir un heros/monstre de la case sous le curseur (dans le mode création).
     * @param x coordonnée x du curseur de la souris.
     * @param y coordonnée y du curseur de la souris.
     * @return le soldat retiré
     * @since 0.9
     */
    public Soldat retirSoldat(int x, int y) {
    	return retirSoldat(chercheCase(x, y));
    }
    
    
    /**
     * Ajoute un heros/monstre sur la case sous le curseur (dans le mode création).
     * @param x coordonnée x du curseur de la souris.
     * @param y coordonnée y du curseur de la souris.
     * @param type <code>int</code>
     * @return le soldat placé
     * @since 0.9
     */
    public Soldat placeSoldat(int x, int y, int type) {
    	Heros h;
    	Monstre m;
        Case c = chercheCase(x, y);
        if(!(c == null) && c.traversable()) {
            if(type > 11) {
            	m = new Monstre(type-11);
                c.setMonstre(m);
                return m;
            }
            else if(type > 7) {
            	h = new Heros(type-7);
                c.setHeros(h);
                return h;
            }
            else
                return null;
        }
        return null;
    }
    
    
    
    /*--------------------- Parcours, distances etc... --------------------*/
    


    /**
     * Renvoie un dictionnaire liant une Case à son origine.
     * Parcours la grille pour trouver le plus court chemin entre le début et la fin (ou le début et toutes les cases atteignables si la fin ne l'est pas ou si elle est <code>null</code> .
     * @param vol_oiseau <code>true</code> si on passe à travers les cases non traversables, false sinon. (En pratique : true pour les projectiles et false pour les mouvements).
     * @param debut <code>Case</code> de départ du parcours.
     * @param fin <code>Case</code> à atteindre lors du parcours.
     * @param distancemax <code>int</code> indiquant la distance max de parcours considéré. Une valeur négative (usuellement -1 pour ne pas en tenir compte).
     * @return un dictionnaire avec pour clé les Cases de la Grille et pour valeurs la Case qui permet de les rejoindre en partant de départ.
     * @since 0.1
     */
    public HashMap<Case,Case> recherche(Case debut, Case fin, int distancemax,boolean vol_oiseau){
        Queue<Case> frontiere = new LinkedList<>();
        HashMap<Case,Case> origine = new HashMap<Case, Case>();
        HashMap<Case,Integer> cout_max = new HashMap<Case,Integer>();
        Case[] voisins;
        int cout;
        origine.put(debut, null);
        cout_max.put(debut, 0);
        frontiere.add(debut);
        Case actuel = frontiere.poll();
        
        /*Pour gérer le fait qu'un héros ne peut pas traverser une case contenant un monstre 
          (et inversement)*/
        boolean travers = false;;
        int typetravers = 0;
        if (debut.getHeros() != null){typetravers = 1;}
        else if (debut.getMonstre() != null){typetravers = 2;}

        /*Boucle de parcours*/
        while (actuel != null){
            if (actuel.equals(fin)){
                break;
            }
            voisins = voisins(actuel);
            for (int i = 0; i < voisins.length; i++){
                /*Choix de la méthode traversable à utiliser*/
                if (voisins[i] != null){
                    switch (typetravers){
                    case 1 :
                        travers = voisins[i].hTraversable();
                        break;
                    case 2 :
                        travers = voisins[i].mTraversable();
                        break;
                    default :
                        travers = voisins[i].traversable();
                        break;
                    }
                }
                if (voisins[i] != null && (vol_oiseau || travers)){
                    if (vol_oiseau){
                        //Si en vol d'oiseau, tout a un cout de 1
                        cout = cout_max.get(actuel) + 1;
                    }
                    else {
                        //Sinon on prend le cout pour marcher sur la case
                        cout = cout_max.get(actuel) + voisins[i].getCout();
                    }
                    if (distancemax < 0 || cout <= distancemax){
                        if (!cout_max.containsKey(voisins[i]) || cout < cout_max.get(voisins[i])){
                            cout_max.put(voisins[i],cout);
                            frontiere.add(voisins[i]);
                            origine.put(voisins[i], actuel);
                        }
                    }
                }
            }
            actuel = frontiere.poll();
        }
        //System.out.println("Fini recherche.");
        return origine;
    }
    
    
    /**
     * Renvoie la distance entre deux cases sans calcul préalable du pathfinding grâce à la méthode <code>recherche</code>.
     * Comprendre distance de mouvement, il ne s'agit pas de la distance directe à vol d'oiseau, les obstacles sont pris en compte.
     * Renvoie -1 si fin n'est pas atteignable depuis debut
     * @param debut Case de départ
     * @param fin Case de fin
     * @return <code>int</code> la distance entre les deux cases. -1 si pas atteignables.
     * @since 0.4
     */
    public int distance(Case debut, Case fin){
        return distance(debut, fin, recherche(debut,fin,-1,false));
    }
    
    
    /**
     * Renvoie la distance entre deux cases sans calcul préalable du pathfinding grâce à la méthode <code>recherche</code>.
     * Comprendre distance de mouvement.
     * Renvoie -1 si fin n'est pas atteignable depuis debut
     * @param debut Case de départ
     * @param fin Case de fin
     * @param vol_oiseau <code>boolean</code> pour savoir si le calcul de la distance est à vol d'oiseau ou en prenant en compte les obstacles.
     * @return <code>int</code> la distance entre les deux cases. -1 si pas atteignables.
     * @since 0.4
     */
    public int distance(Case debut, Case fin, boolean vol_oiseau){
        return distance(debut,fin,recherche(debut,fin,-1,vol_oiseau));
    }

    /**
     * Renvoie la distance entre deux cases sans considérer le cout de déplacement.
     * Renvoie -1 si pas atteignable
     * Doit être appelée avec un tableau origine effectué avec vol_oiseau = true.
     * @param debut <code>Case</code> case de début
     * @param fin <code>Case</code> case de fin
     * @param origine <code>HashMap</code> obtenu avec recherche().
     * @return <code>int</code>
     * @since 0.7
     */
    public int distanceSansCout(Case debut, Case fin, HashMap<Case,Case> origine){
        int dist = 0;
        if (!origine.containsKey(fin)){
            return -1;
        }
        Case actuel = fin;
        while (!actuel.equals(debut)){
            dist = dist + 1;
            actuel = origine.get(actuel);
        }
        return dist;
    }
    
    /**
     * Renvoie la distance entre deux cases avec calcul préalable du pathfinding grâce à la méthode <code>recherche</code>.
     * Renvoie -1 si pas atteignable depuis le debut. ATTENTION PEUT RENVOYER -1 MEME SI ATTEIGNABLE SI origine MAL FAIT (mauvaise distance max, autre fin etc...).
     * Comprendre distance de mouvement, il ne s'agit pas de la distance directe à vol d'oiseau, les obstacles sont pris en compte sauf si origine a été appelée avec vol_oiseau = true. Le cout des cases est toujours pris en compte.
     * Utiliser cette méthode plutôt que l'autre <code>distance</code> si le dictionnaire origine a un autre usage que simplment la distance.x
     * @param debut Case de départ
     * @param fin Case de fin
     * @param origine <code>Hashmap</code> le dico de pathfinding utilisé pour la distance
     * @return <code>int</code> la distance entre les deux cases. -1 si pas atteignables.
     * @since 0.4
     */
    public int distance(Case debut, Case fin, HashMap<Case,Case> origine){
        int dist = 0;
        if (!origine.containsKey(fin)){
            return -1;
        }
        Case actuel = fin;
        while (!actuel.equals(debut)){
            dist = dist + actuel.getCout();
            actuel = origine.get(actuel);
        }
        return dist;
    }
    
    
    /**
     * Renvoie un booléen indiquant si la case fin est visable par projectile depuis le début.
     * @param debut Case de départ
     * @param fin Case de fin
     * @return <code>booléen</code>.
     * @since 0.4
     */
    public boolean visable(Case debut, Case fin){
        return visable(debut, fin, recherche(debut,fin,-1,true));
    }
    
    
    /**
     * Renvoie un booléen indiquant si la case fin est visable par projectile depuis le début.
     * @param debut Case de départ
     * @param fin Case de fin
     * @param origine <code>Hashmap</code> le dico de pathfinding utilisé pour la distance. Appel précédent à <code>recherche</code> avec vol_oiseau = true.
     * @return <code>booléen</code>.
     * @since 0.4
     */
    public boolean visable(Case debut, Case fin, HashMap<Case,Case> origine){
        if (!origine.containsKey(fin)){
            return false;
        }
        Case actuel = fin;
        while (!actuel.equals(debut)){
            if (!actuel.pTraversable() && !actuel.equals(fin)){
                return false;
            }
            actuel = origine.get(actuel);
        }
        return true;
    }

    /**
     * Fais disparaître les unités mortes de la carte et renvoie un entier indiquant si la partie est finie.
     * 0 = pas fini
     * 1 = fini victoire des Heros
     * 2 = fini victoire des Monstres
     * @return <code>int</code>
     * @since 0.8
     * @deprecated
     */
    @Deprecated
    public int enleverMorts(){
        boolean fini1 = true;
        boolean fini2 = true;
        int retour = 0;
        int debut = HAUTEUR_MAX_GRILLE/2, fin = longueurGrille+HAUTEUR_MAX_GRILLE/2;
        for(int i = 0; i < hauteurGrille; i++){
            
            for (int j = debut; j < fin; j++) {
                if (grille[i][j].getZ() != -1) { // Pour éviter les cases de remplissage
                    
                    // si il y a un héros
                    Heros h = grille[i][j].getHeros();
                    if(h != null){
                        if (h.getPV() == 0){
                            grille[i][j].setHeros(null);
                        }
                        else fini1 = false;
                    }
                    
                    else {
                        // si il y a un monstre
                        Monstre m = grille[i][j].getMonstre();
                        if(m != null) {
                            if (m.getPV() == 0){
                                grille[i][j].setMonstre(null);
                            }
                            else fini2 = false;
                        }
                    }
                }
            }
            if(i%2==1) {
                debut--;
                fin --;
            }
        }
        if (fini1){
            //plus de Héros : défaite
            retour =  2;
        }
        else if (fini2){
            //plus de Monstres : victoire
            retour = 1;
        }
        return retour;
    }



    /* --- Fonctions de tests --- */


    /**
     * Surcharge de toString.
     * @return La chaîne représentant une grille.
     * @since 1.0
     */
    
    public String toString(){
	String s = "";
	
	for(int i = 0; i < 10 ; i++){
	    for(int j = 0; j < 10; j++){
		s = s + grille[i][j] + "\n";
	    }
	}
	return "Grille de longueur "+longueurGrille+" et de hauteur "+hauteurGrille+"\nJ'ai "+NB_CASES+" cases en tout!\n"+s;
    }
    
    /*
    
    /**
     * Surcharge de la méthode readObject afin de lire le format de sauvegarde définit par writeObject
     * @param ois <code>ObjectInputStream</code>
     * @since 1.0
     

    private void readObject(ObjectInputStream ois) throws Exception, ClassNotFoundException{
	//int debut = HAUTEUR_MAX_GRILLE/2, fin = LONGUEUR_MAX_GRILLE+HAUTEUR_MAX_GRILLE/2;
	//int x = 0, y = 0, z = 0;
	
	this.longueurGrille = ois.read();
	this.hauteurGrille = ois.read();
	this.grille[0][0] = (Case)ois.readObject();
	/*for(int i=0 ; i < hauteurGrille ; i++){
	    for( int j = 0; j < debut; j ++){
		this.grille[i][j] = (Case)ois.readObject();
		System.out.println(grille[i][j]);
	    }
	    for ( int j = debut ; j < fin ; j++ ) {
		this.grille[i][j] = (Case)ois.readObject();
		System.out.println(grille[i][j]);
		x++;
		y--;
	    }
	    for (int j = fin; j < LONGUEUR_MAX_GRILLE+HAUTEUR_MAX_GRILLE/2; j++) {
                this.grille[i][j] = (Case)ois.readObject();
		System.out.println(grille[i][j]);
	    }
    }

	

    /**
     * Surcharge de la méthode writeObject afin de prendre que des informations nécessaires
     * @param oos <code>ObjectInputStream</code>
     * @since 1.0
     

    private void writeObject(ObjectOutputStream oos) throws Exception{
	int debut = HAUTEUR_MAX_GRILLE/2, fin = LONGUEUR_MAX_GRILLE+HAUTEUR_MAX_GRILLE/2;
	int x = 0, y = 0, z = 0;
	
	oos.write(longueurGrille);
	oos.write(hauteurGrille);
	for(int i=0 ; i < hauteurGrille ; i++){
	    for( int j = 0; j < debut; j ++){
		oos.writeObject(grille[i][j]);
		System.out.println(grille[i][j]);
	    }
	    for ( int j = debut ; j < fin ; j++ ) {
		oos.writeObject(grille[i][j]);
		System.out.println(grille[i][j]);
		x++;
		y--;
	    }
	    for (int j = fin; j < LONGUEUR_MAX_GRILLE+HAUTEUR_MAX_GRILLE/2; j++) {
		oos.writeObject(grille[i][j]);
		System.out.println(grille[i][j]);
	    }
	}
    }*/
}


