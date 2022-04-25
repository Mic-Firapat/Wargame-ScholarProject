package projet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Case est la classe représentant une case hexagonale de la grille.
 * @version 1.1
 */
public class Case extends Config implements Serializable{

    /* Code d'identifiant généré aléatoirement avant d'être instancié
     * Produit en suivant la méthode interne des JVM
     * Utilisé par java pour donner un numéro de série unique aux objets
     * En théorie empêche un enregistrement de doublon
     * Permet d'assurer que l'objet en enregistré dans un fichier correspond
     * à la version qui cherche à le récupérer
     */
    private static final long serialVersionUID = -4967728314L;

    private Point coordonnees;
    private int type;
    private int cout = 1; // Cout pour traverser/entrer sur l'hexagone 
    
    private Heros heros = null; // pour savoir si il y a une unité sur la case
    private Monstre monstre = null;


    /**
     * Constructeur de la classe Case.
     * @param x Valeur de la coordonnnée x de la Case.
     * @param y Valeur de la coordonnée y de la Case.
     * @param z Valeur de la coordonnée z de la Case.
     * @since 0.0
     */
    public Case(int x, int y, int z) {
        coordonnees = new Point(x, y, z);
        //Choix aléatoire du type de la case
        type = 0;
        if ((int)(Math.random()*30) == 1)
            type = 4;
        if ((int)(Math.random()*30) == 1)
            type = 5;
        if ((int)(Math.random()*10) == 1)
            type = 1;
        if ((int)(Math.random()*10) == 1)
            type = 2;
        if ((int)(Math.random()*10) == 1)
            type = 3;
	 if ((int)(Math.random()*30) == 1)
            type = 6;
	 if ((int)(Math.random()*30) == 1)
            type = 7;

        //Etendue d'eau
        /*if(getX() > 5 && getX() < 15 &&
        		getY() < -10 && getY() > -25 &&
        		getZ() > 5 && getZ() < 20) {
        	type = 6;

        	if(getX() > 6 && getX() < 13 &&
            		getY() < -11 && getY() > -23 &&
            		getZ() > 7 && getZ() < 15)
        		type = 7;
			}*/
        //Calcul du cout de la case en fonction du type
        actualiseCout();
        //Placement initial de Heros et de Monstre pour les tests.
        /*
        if (x == 6 && z == 5) {
        	heros = new Heros(1);
        }
        if (x == 9 && z == 10) monstre = new Monstre(4);
        */
    }

    /**
     * Actualise le cout de la case. A appeler à chaque changement de type.
     * @since 0.7
     */
    public void actualiseCout(){
        if (type == 0){
            cout = 1;
        }
        else if (type >=1 && type <=3 || type ==6){
        	//Un arbre ou de l'eau peu profonde
            cout = 2;
        }
    }
    
    /**
     * Indique si les projectiles peuvent traverser la case.
     * Pour l'instant une case est traversable si c'est de l'herbe ou de l'eau.
     * @return <code>boolean</code>
     * @since 0.5
     */
    public boolean pTraversable(){
        return (type == 0 || type == 6 || type == 7);
    }
    
    
    /**
     * Indique si la case est traversable ou non.
     * Pour l'instant pour les tests, une case est traversable si c'est de l'herbe ou un arbre ou eau peu profonde.
     * @return <code>boolean</code> décrivant si la <code>Case</code> est traversable ou pas.
     * @since 0.3
     */
    public boolean traversable(){        
    	return (type == 0 || (type >=1 && type <= 3) || type == 6);
    }

    
    /**
     * Indique si la case est traversable ou pas pour un <code>Heros</code>.
     * @return <code>boolean</code> décrivant si la <code>Case</code> est traversable.
     * @since 0.5
     */
    public boolean hTraversable(){
        return (monstre == null && traversable());
    }

    /**
     * Indique si la case est traversable ou pas pour un <code>Monstre</code>.
     * @return <code>boolean</code> décrivant si la <code>Case</code> est traversable.
     * @since 0.5
     */
    public boolean mTraversable(){
        return (heros == null && traversable());
    }
    
    
    /**
     * Redéfinition de toString
     * @return chaine de caractère décrivant la <code>Case</code>
     * @since 0.1
     */
    public String toString(){
        String s =  "<html>Case : ( x = "+getX()+" , y = "+getY()+" , z = "+getZ()+" )<br>";
        switch(type) {
        case 0 : 
	    s = s + "herbe<br>On pourrait s'y endormir.</html>";
	    break;
        case 1 : 
	    s = s + "arbre rouge<br>Mobilité divisée par "+cout+".</html>";
	    break;
        case 2 :
	    s = s + "arbre jaune<br>Mobilité divisée par "+cout+".</html>";
	    break;
        case 3 : 
	    s = s + "arbre vert<br>Mobilité divisée par "+cout+".</html>";
	    break;
        case 4 : 
	    s = s + "rocher<br>Il est infranchissable.</html>";
	    break;
        case 5 : 
	    s = s + "rocher<br>Il est infranchissable.</html>";
	    break;
        case 6 :
	    s = s + "eau peu profonde<br>Mobilité divisée par "+cout+".</html>";
	    break;
        case 7 :
	    s = s + "eau profonde<br>Gare à la noyade !</html>";
	    break;
	default :
	    s = s + "INCONNUE</html>";
        }

        return s;	
    }
    
    /**
     * Compare la Case courant avec le paramètre
     * @param c Case à comparer
     * @return <code>boolean</code> indiquant si les deux Cases sont égales.
     * @since 0.1
     */
    public boolean equals(Case c){
        if (c == null){
            return false;
        }
        return (getX() == c.getX() && getY() == c.getY() && getZ() == c.getZ());
    }
    
    
    /*------------------------------ Getteurs --------------------------------*/
    
    /**
     * Renvoie le pointeur sur le Héros qui se trouve sur la Case.
     * <code>null</code> si pas de Héros.
     * @return heros <code>Heros</code>
     * @since 0.5
     */
    public Heros getHeros(){
        return heros;
    }

    /**
     * Renvoie le pointeur sur le Monstres qui se trouve sur la Case.
     * <code>null</code> si pas de Monstre.
     * @return monstre <code>Monstre</code>
     * @since 0.5
     */
    public Monstre getMonstre(){
        return monstre;
    }
    
    
    /**
     * Renvoie l'entier décrivant le coût de déplacement sur cette Case.
     * @return cout <code>float</code>
     * @since 0.3
     */
    public int getCout(){
        actualiseCout();
        return cout;
    }
    
    
    /**
     * Renvoie l'entier décrivant le type de la Case courante.
     * @return type int.
     * @since 0.2
     */
    public int getType(){
        return type;
    }
    /**
     * Renvoie la valeur de la coordonnée x.
     * @return x la coordonnée de la Case.
     * @since 0.0
     */
    public int getX() {
        return coordonnees.getX();
    }

    /**
     * Renvoie la valeur de la coordonnée y.
     * @return y la coordonnée de la Case.
     * @since 0.0
     */
    public int getY() {
        return coordonnees.getY();
    }

    /**
     * Renvoie la valeur de la coordonnée z
     * @return z la coordonnée de la Case.
     * @since 0.0
     */
    public int getZ() {
        return coordonnees.getZ();
    }

    /**
     * Renvoie l'abscisse du centre de la case dans à la fenêtre
     * @return l'abscisse de la case
     * @since 0.0
     */
    public int getAxialX() {
        return (int)(hauteurHexagone * (Math.sqrt(3) * getX()+ Math.sqrt(3)/2 * getZ()))+positionGrilleX;
    }
	
    /**
     * Renvoie l'ordonné du centre de la case dans à la fenêtre
     * @return l'ordonné de la case
     * @since 0.0
     */
    public int getAxialY() {
        return (int)(hauteurHexagone * (3./2 * getZ()))+positionGrilleY;
    }
    
    
    /*----------------------------- Setteurs -----------------------------*/
    
    /**
     * Modifie le Héros présent sur la case.
     * Un Héros ne pouvant pas être sur la même case qu'un Monstre, monstre passe à null sans indications. Attention donc.
     * @param h <code>Heros</code> nouveau héros.
     * @since 0.5
     */
    public void setHeros(Heros h){
        monstre = null;
        heros = h;
        if (heros != null)
        	heros.setCase(this);
    }

    /**
     * Modifie le Monstre présent sur la case.
     * Un Héros ne pouvant pas être sur la même case qu'un Monstre, heros passe à null sans indications. Attention donc.
     * @param m <code>Monstre</code> nouveau Monstre;
     * @since 0.5
     */
    public void setMonstre(Monstre m){
        heros = null;
        monstre = m;
        if (monstre != null)
        	monstre.setCase(this);
    }
    
    /**
     * Modifie le type de la case.
     * @param i <code>int</code> nouveau type
     * @since 0.2
     */
    public void setType(int i){
    	type = i;
        actualiseCout();
    }

    /**
     * Modifie le coût de déplacement de la case.
     * @param c <code>float</code> nouveau coût.
     * @since 0.3
     */
    public void setCout(int c){
        cout = c;
    }
    
	
    /*---------------------------- Affichage --------------------------------*/

    
    /**
     * Affiche une case de la grille.
     * @param g Graphics pour l'affichage.
     * @param image BufferedImage image à afficher
     * @since 0.2
     */
    public void afficheCase(Graphics g, BufferedImage image) {
        int x = getAxialX() - longueurHexagone;
        int y = getAxialY() - hauteurHexagone;
        g.drawImage(image, x, y, x+longueurHexagone*2, y+hauteurHexagone*2+1, 0, 0, 520, 601, null);	
		
    }
    
    /**
     * Affiche un hexagone rouge en transparence pour indiquer que la case est actuellement survolée par le curseur / mise en surbrillance.
     * @param g Graphics pour l'affichage dans la fenêtre.
     * @param c Color couleur de la sélection.
     * @since 0.1
     */
    public void afficheCaseSelectionnee(Graphics g, Color c) {
        int centreX = getAxialX();
        int centreY = getAxialY();
        int x[] = { centreX, centreX+longueurHexagone, centreX+longueurHexagone, centreX, centreX-longueurHexagone, centreX-longueurHexagone};
        int y[] = { centreY-hauteurHexagone, centreY-hauteurHexagone/2, centreY+hauteurHexagone/2, centreY+hauteurHexagone, centreY+hauteurHexagone/2, centreY-hauteurHexagone/2};
        g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 100));
        g.fillPolygon(x, y, 6);
        g.setColor(c);
        g.drawPolygon(x, y, 6);
    }
    
    
    /**
     * Affiche un hexagone gris en transparence pour représenter le brouillard
     * @param g Graphics pour l'affichage dans la fenêtre.
     * @since 0.8
     */
    public void afficheBrouillardCase(Graphics g) {
        int centreX = getAxialX();
        int centreY = getAxialY();
        int x[] = { centreX, centreX+longueurHexagone, centreX+longueurHexagone, centreX, centreX-longueurHexagone, centreX-longueurHexagone};
        int y[] = { centreY-hauteurHexagone, centreY-hauteurHexagone/2, centreY+hauteurHexagone/2, centreY+hauteurHexagone, centreY+hauteurHexagone/2, centreY-hauteurHexagone/2};
        g.setColor(new Color(69, 69, 69, 100));
        g.fillPolygon(x, y, 6);
    }
    
    /**
     * Affiche une mini-case pour la mini-map
     * @param g Graphics pour l'affichage.
     * @param tailleMiniCases <code>int</code> la taille.
     * @param positionX <code>int</code> nom explicite.
     * @param positionY <code>int</code> nom explicite.
     * @param image BufferedImage image à afficher
     * @since 0.5
     */
    public void afficheMiniCase(Graphics g, int tailleMiniCases, int positionX, int positionY, BufferedImage image) {
    	int longueurMiniCases = (int)((Math.sqrt(3)*tailleMiniCases)/2.+1);
    	int x = (int)(tailleMiniCases * (Math.sqrt(3) * getX()+ Math.sqrt(3)/2 * getZ()))+positionX;
        int y = (int)(tailleMiniCases* (3./2 * getZ()))+positionY;
        g.drawImage(image, x, y, x+longueurMiniCases*2, y+tailleMiniCases*2+1, 0, 0, 100, 116, null);	
		
    }

    /* ---- Plus besoin de surcharger, problèmes résolu ---- */
    /*
    /**
     * Surcharge de la méthode readObject afin de lire le format de sauvegarde définit par writeObject
     * @param ois <code>ObjectInputStream</code>
     * @since 1.0
     

    private void readObject(ObjectInputStream ois) throws Exception, ClassNotFoundException{
	this.coordonnees = (Point)ois.readObject();
	this.type = ois.read();
	this.cout = ois.read();
	this.heros = (Heros)ois.readObject();
	this.monstre = (Monstre)ois.readObject();
    }

    /**
     * Surcharge de la méthode writeObject afin de prendre que des informations nécessaires
     * @param oos <code>ObjectInputStream</code>
     * @since 1.0
     

    private void writeObject(ObjectOutputStream oos) throws Exception{
	
	oos.writeObject(coordonnees);
	oos.write(type);
	oos.write(cout);
	oos.writeObject(heros);
	oos.writeObject(monstre);
	
	}*/

}
