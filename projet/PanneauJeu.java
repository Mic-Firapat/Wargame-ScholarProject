package projet;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;


/**
 * PanneauJeu est le panneau utilisé en jeu. Il hérite de PanneauCommun.
 * @version 1.1
 */
@SuppressWarnings("serial")
public class PanneauJeu extends PanneauCommun{
	
    private Case herosSelect =  null;//null si pas de héros sélectionné, la case du héros sinon.
    private boolean mortTour = false; //Indique si des unités sont mortes ce tour
    
    /**
     * Constructeur de la classe PanneauJeu.
     * @param fenetre <code>FenetreJeu</code> la fenêtre principale
     * @param miniMap <code>MiniMap</code> la miniMap.
     * @param panelBas <code>JPanel</code> le panel du bas.
     * @since 0.2
     */
    public PanneauJeu (FenetreJeu fenetre, MiniMap miniMap, JPanel panelBas) {
    	super(fenetre, miniMap);
    	
    	panelBas.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();

    	
        // Components
        c.insets = new Insets(0, 40, 50, 0);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridheight = 2;
        labelIconCases.setIcon(new ImageIcon(tabCases[0].getScaledInstance(52, 60, Image.SCALE_SMOOTH)));
        panelBas.add(labelIconCases, c);
        
        c.gridx = 1;
        labelCases.setForeground(new Color(240, 255, 255));
        panelBas.add(labelCases, c);

        c.insets = new Insets(0, 30, 50, 30);
        
        c.gridheight = 1;
        c.gridx = 2;
        c.gridy = 1;
        labelNbTours.setForeground(new Color(240, 255, 255));
        labelNbTours.setText("Tour n°"+nbTours);
        panelBas.add(labelNbTours, c);
        
        c.gridx = 3;
        labelInfosSoldats.setForeground(new Color(240, 255, 255));
        labelInfosSoldats.setText("Héros restants : "+ensHeros.nbSoldats()+" / Monstres restants : "+ensMonstres.nbSoldats());
        panelBas.add(labelInfosSoldats, c);
        
        c.gridx = 4;
        c.gridheight = 2;
        labelControls.setForeground(new Color(240, 255, 255));
        labelControls.setText("<html>Clic gauche : se déplacer / sélectionner<br>Clic droit : désélectionner un héros<br>Scroll : zoomer / dézoomer<html>");
        panelBas.add(labelControls, c);

	
        infosSoldat.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
    }
    
    /**
     * Initialise la partie
     * @since 0.9
     */
    public void initialisePartie() {
    	nbTours = 0;
        labelNbTours.setText("Tour n°"+nbTours);
        labelInfosSoldats.setText("Héros restants : "+ensHeros.nbSoldats()+" / Monstres restants : "+ensMonstres.nbSoldats());

        }
    
    /**
     * Affiche la victoire du joueur
     * @since 0.8
     */
    public void victoire() {
    	new FenetreFinPartie(fenetrePrincipale, 1);
    	ensHeros.vider();
    	ensMonstres.vider();
    }

    /**
     * Affiche la défaite du joueur
     * @since 1.1
     */
    public void defaite() {
    	new FenetreFinPartie(fenetrePrincipale, 0);
    	ensHeros.vider();
    	ensMonstres.vider();
    }
    
    /**
     * Redéfinition de la méthode paintComponent de la classe PanneauJeu.
     * @param g Graphics pour l'affichage de la fenêtre.
     * @since 0.2
     */
    
    public void paintComponent(Graphics g) {
        grille.setFog(true);
        super.paintComponent(g);
        labelInfosSoldats.setText("Héros restants : "+ensHeros.nbSoldats()+" / Monstres restants : "+ensMonstres.nbSoldats());
        
        if (herosSelect != null){
            grille.afficheMobilite(herosSelect,g, tabCases);
        }
        else {
            grille.afficheMobilite(x,y,g, tabCases);
        }
    }
    
    /**
     * Clic gauche. Beaucoup de cas possibles.
     * @param e <code>MouseEvent</code> : évènement de la souris
     * @since 0.6
     */
    public void mouseClicked(MouseEvent e){
        if (herosSelect != null && herosSelect.getHeros() == null){
            //On gère un cas étrange
            herosSelect = null;
        }
        if (e.getButton() == MouseEvent.BUTTON3) { //Si clic droit alors on enlève la sélection
            herosSelect = null;
        }
        else{
            x = e.getX();
            y = e.getY();
            Case c = grille.chercheCase(x,y);
            if (c!=null){ //On a cliqué sur une case valide
            	
                if (c.getMonstre() != null){
                    //Si clique sur un monstre
                    if (herosSelect != null){
                        //On a un héros de sélectionné
                        if (herosSelect.getHeros().getnbAT() > 0){
                            tentativeJoueurFrappe(c);
			  
                           enleverMorts();
                            labelMonstre.afficher(c.getMonstre());
                        }
                    }
                }
                else if (c.getHeros() != null && c!=herosSelect){
                    //Si clique sur un héros
                    if (herosSelect != null){
                        //On a un héros sélectionné
                        if (herosSelect.getHeros().getnbHEAL() > 0){
                            tentativeJoueurSoin(c);
                        }
                        //Notre héros ne peut pas soigner.On change simplement de Heros sélectionné.
                        else herosSelect = c;
                    }
                    //Sélection du Héros si pas déjà de Héros sélectionné
                    else if (c.getHeros() != null) herosSelect = c;                    
                }
                else if (herosSelect != null){ // Si héros sélectionné
                    //On le déplace sur la case cliquée si elle est atteignable et vide
                    HashMap<Case,Case> o = grille.recherche(herosSelect, c,herosSelect.getHeros().getPM(),false);
                    if (o.containsKey(c) && c.getHeros() == null){
			
                        c.setHeros(herosSelect.getHeros());
                        herosSelect.setHeros(null);
                        c.getHeros().deplacement(grille.distance(herosSelect,c,o));
                        herosSelect = c;
                    }
                }
                if (herosSelect != null && herosSelect.getHeros() != null){
                    //Si plus d'actions possibles on lâche le Héros
                    //sinon on le garde
                    if (!herosSelect.getHeros().peutJouer()){
                        herosSelect = null;
                    }
                }
            }
        }
        repaint();
    }
    
    /**
     * Méthode appelée à chaque fin de tour.
     * Redonne aux soldats leurs capacités pour le prochain tour et lance le tour des ennemis.
     * @since 0.7
     */
    public void finTour(){
        for (Case c : grille.getPosHeros().keySet()){
            c.getHeros().newTour();
        }
        herosSelect = null;

        tourEnnemi();
        for (int i = 0; i < ensMonstres.nbSoldats; i++){
            ensMonstres.getSoldat(i).newTour();
        }
       
        if (!mortTour){
             new FenetreInformation(fenetrePrincipale, "Fin de tour des Monstres", "Aucun Héros n'est mort ce tour...", 0);
        }
        mortTour = false;
        nbTours++;
        labelNbTours.setText("Tour n°"+nbTours);
        
        repaint();
    }

    /**
     * Méthode qui fait joueur leur tour aux ennemis;
     * @since 1.1
     */
    public void tourEnnemi(){
        //Insérer ici les actions faites par les ennemis pendant leur tour de jeu.
        int dist, dist_min, indice_min;
        Monstre m;
        Heros h;
        HashMap<Case,Case> a;
        HashMap<Case,Case> liste_visibles_monstre;
        for (int i = 0; i < ensMonstres.nbSoldats && ensHeros.nbSoldats > 0; i++){
            dist_min = -1;
            indice_min = -1;
            m = (Monstre) ensMonstres.getSoldat(i);
            if (m != null && m.getPV() > 0){//Juste au cas où
                //Premier filtre des cases accessibles par le monstre
                a = grille.recherche(m.getCase(), null, m.getPM() + m.getPO(), true);
                liste_visibles_monstre = grille.getVisibleEnnemis();
                for (Case cle : a.keySet()){
                    if (liste_visibles_monstre.containsKey(cle)){
                        //Si la case est visible
                        if (m.getnbHEAL() > 0 && cle.getMonstre() != null && cle.getMonstre().getPVmax() != cle.getMonstre().getPV()){
                            //Si le monstre peut soigner et que la case contient un monstre blessé
                            tentativeMonstreSoin(m,cle, a);
                        }
                        else if (m.getnbAT() > 0 && cle.getHeros() != null && cle.getHeros().getPV() > 0){
                            //Si le monstre peut attaquer et que la case contient un Heros
                            tentativeMonstreFrappe(m,cle,a);
                            enleverMorts();
                        }
                    }
                }
                //Puis déplacement vers un héros visible proche
                for (int j = 0; j < ensHeros.nbSoldats; j++){
                    
                    h = (Heros) ensHeros.getSoldat(j);
                    if (h != null && h.getPV() > 0 && liste_visibles_monstre.containsKey(h.getCase())){
                        //Pour que le calcul de distance se fasse bien on enlève le monstre puis le remet
                        m.getCase().setMonstre(null);                        
                        dist = grille.distance(m.getCase(), h.getCase());
                        m.getCase().setMonstre(m);
                        if (dist > 0 && (dist < dist_min || dist_min < 0)){
                            dist_min = dist;
                            indice_min = j;
                        }
                    }
                }
                if (indice_min != -1 && dist_min > m.getPO()){
                    //Pour que la recherche se fasse bien on enlève puis remet le monstre
                    m.getCase().setMonstre(null);
                    a = grille.recherche(m.getCase(), ensHeros.getSoldat(indice_min).getCase(), -1, false);
                    m.getCase().setMonstre(m);
                    Case actuel = ensHeros.getSoldat(indice_min).getCase();
                    while (actuel != null && (dist_min > m.getPM() || actuel.getMonstre() != null || actuel.getHeros() != null)){
                        dist_min = dist_min - actuel.getCout();
                        actuel = a.get(actuel);
                    }
                    if (actuel != null){
                        //On déplace le monstre sur la case atteignable la plus proche du héros le plus proche
                        Case tmp = m.getCase();
                        actuel.setMonstre(m);
                        tmp.setMonstre(null);
                        m.deplacement(dist_min);
                    }
                }
            }
        }
    }
    
    /*--------------------------------------------------------------------------------*/
    //Méthodes utilisées par les monstres qui tentent des actions
    /**
     * Méthode qui permet au monstre de soigner un allié (ou d'essayer) situé en Case c.
     * @param m <code>Monstre</code> soigneur
     * @param c <code>Case</code>
     * @param a <code>HashMap</code>
     * @since 1.1
     */
    public void tentativeMonstreSoin(Monstre m, Case c, HashMap<Case,Case> a){
        //On regarde si la cible est à portée de soin
        HashMap<Case,Case> o = grille.recherche(m.getCase(), c, m.getPM(),false);
        //Pour savoir si la case est visable depuis une case atteignable
        HashMap<Case,Case> v;

        v = grille.recherche(m.getCase(),c,m.getPO(), true);
        if (grille.visable(m.getCase(),c,v)){
            //On peut soigner sans bouger
            m.soigne(c.getMonstre());
        }
        else {
            for (Case cle : o.keySet()){
                //Pour chaque case atteignable
                if (cle.getMonstre() == null || cle == m.getCase()){
                    //Le Monstre peut aller sur cette case
                    v = grille.recherche(cle,c,m.getPO(),true);
                    if (grille.visable(cle,c,v)){
                        //Case à portée et visable
                        //Déplacement pour le soin
                        Case tmp = m.getCase();
                        cle.setMonstre(m);
                        tmp.setMonstre(null);
                        m.deplacement(grille.distance(tmp,cle,o));
                        //Soin
                        m.soigne(c.getMonstre());
                        
                        //Pour sortir de la boucle
                        o = null;
                        break;
                        }
                }
            }
        }
    }
    

    /**
     * Méthode qui permet à un Monstre d'attaquer une unité (ou d'essayer) située en Case c
     * @param m <code>Monstre</code>
     * @param c <code>Case</code>
     * @param a <code>HashMap</code>
     * @since 1.1
     */
    public void tentativeMonstreFrappe(Monstre m,Case c, HashMap<Case,Case> a){
        // On regarde si la cible est à portée d'attaque
        HashMap<Case,Case> o = grille.recherche(m.getCase(), c, m.getPM(),false);
        //Pour savoir si la case est visable depuis une case atteignable
        HashMap<Case,Case> v;
        v = grille.recherche(m.getCase(), c, m.getPO(), true);
        if (grille.visable(m.getCase(),c,v)){
            //On peut attaquer sans bouger
            m.attaque(c.getHeros());
        }
        else {
            for (Case cle : o.keySet()){
                //Pour chaque case atteignable
                if (cle.getMonstre() == null || cle == m.getCase()){
                    //Le Monstre peut aller sur cette case
                    v = grille.recherche(cle,c,m.getPO(),true);
                    if (grille.visable(cle,c,v)){
                        //Case à portée et visable
                        //Déplacement pour l'attaque
                        Case tmp = m.getCase();
                        cle.setMonstre(m);
                        tmp.setMonstre(null);
                        m.deplacement(grille.distance(tmp,cle,o));
                        //Attaque
                        m.attaque(c.getHeros());
                            
                        //Pour sortir de la boucle
                        break;
                    }
                }
            }
        }
    }
    
    /*-----------------------------------------------------------------------------*/
    //Méthodes appelées dans mouseClicked pour effectuer les actions du joueur
    /**
     * Méthode qui permet au joueur de soigner un allié (ou d'essayer) situé en Case c.
     * @param c <code>Case</code>
     * @since 0.8
     */
    public void tentativeJoueurSoin(Case c){
        //On regarde si la cible est à portée de soin
        HashMap<Case,Case> o = grille.recherche(herosSelect, c, herosSelect.getHeros().getPM(),false);
        HashMap<Case,Case> a = grille.recherche(herosSelect, c, herosSelect.getHeros().getPM() + herosSelect.getHeros().getPO(), true);
        //Pour savoir si la case est visable depuis une case atteignable
        HashMap<Case,Case> v;
        if (a.containsKey(c)){
            //Premier test pour savoir si la case est à portée
            v = grille.recherche(herosSelect,c,herosSelect.getHeros().getPO(), true);
            if (grille.visable(herosSelect,c,v)){
                //On peut soigner sans bouger
                herosSelect.getHeros().soigne(c.getHeros());
            }
            else {
                for (Case cle : o.keySet()){
                    //Pour chaque case atteignable
                    if (cle.getHeros() == null || cle == herosSelect){
                        //Le Héros peut aller sur cette case
                        v = grille.recherche(cle,c,herosSelect.getHeros().getPO(),true);
                        if (grille.visable(cle,c,v)){
                            //Case à portée et visable
                            //Déplacement pour le soin
                            cle.setHeros(herosSelect.getHeros());
                            herosSelect.setHeros(null);
                            cle.getHeros().deplacement(grille.distance(herosSelect,cle,o));
                            herosSelect = cle;
                            //Soin
                            cle.getHeros().soigne(c.getHeros());
                            
                            //Pour sortir de la boucle
                            o = null;
                            break;
                        }
                    }
                }
                if (o != null){
                    //Allié non soignable, on le sélectionne
                    herosSelect = c;
                }
            }
        }
        //Le héros n'est pas à portée donc c'est la nouvelle sélection.
        else herosSelect = c;  
    }

    
    /**
     * Méthode qui permet au joueur d'attaquer une unité quand il clique sur un monstre dans la Case c.
     * @param c <code>Case</code>
     * @since 0.8
     */
    public void tentativeJoueurFrappe(Case c){
        // On regarde si la cible est à portée d'attaque
        HashMap<Case,Case> o = grille.recherche(herosSelect, c, herosSelect.getHeros().getPM(), false);
        HashMap<Case,Case> a = grille.recherche(herosSelect, c, herosSelect.getHeros().getPM() + herosSelect.getHeros().getPO(), true);
        //Pour savoir si la case est visable depuis une case atteignable
        HashMap<Case,Case> v;
        if (a.containsKey(c)){ //Premier test
            v = grille.recherche(herosSelect, c, herosSelect.getHeros().getPO(), true);
            if (grille.visable(herosSelect,c,v)){
                //On peut attaquer sans bouger
                herosSelect.getHeros().attaque(c.getMonstre());
            }
            else {
                for (Case cle : o.keySet()){
                    //Pour chaque case atteignable
                    if (cle.getHeros() == null || cle == herosSelect){
                        //Le Héros peut aller sur cette case
                        v = grille.recherche(cle,c,herosSelect.getHeros().getPO(),true);
                        if (grille.visable(cle,c,v)){
                            //Case à portée et visable
                            //Déplacement pour l'attaque
                            cle.setHeros(herosSelect.getHeros());
                            herosSelect.setHeros(null);
                            cle.getHeros().deplacement(grille.distance(herosSelect,cle,o));
                            herosSelect = cle;
                            //Attaque
                            cle.getHeros().attaque(c.getMonstre());
                            
                            //Pour sortir de la boucle
                            break;
                        }
                    }
                }
            }
        }
    }


    /** 
     * Retire les morts du champ de bataille.
     * @since 1.1
     */
    public void enleverMorts(){
        int nb = ensHeros.nbSoldats;
        for (int i = 0; i < nb; i++){
            if (ensHeros.getSoldat(i).getPV() == 0){
                mortTour = true;
		
                afficheRecap(ensHeros.getSoldat(i));
                ensHeros.supprimerSoldat(ensHeros.getSoldat(i));
                repaint();
                break;
                
            }
        }
        nb = ensMonstres.nbSoldats;
        for (int i = 0; i < nb; i++){
            if (ensMonstres.getSoldat(i).getPV() == 0){
		
                afficheRecap(ensMonstres.getSoldat(i));
                ensMonstres.supprimerSoldat(ensMonstres.getSoldat(i));
                repaint();
                break;
            }
        }
        if (ensMonstres.nbSoldats == 0)  victoire();
        else if (ensHeros.nbSoldats == 0)  defaite();
        
        
    }
    /**
     * Positionne la grille de manière à afficher un message associé à un héros
     * @param s <code>Soldat</code>
     * @since 0.9
     */
    public void afficheRecap(Soldat s) {
    	
        grille.centrer( s.getCase(), fenetrePrincipale);
        repaint();
        if (s instanceof Heros){
        	Heros h = (Heros)s;
            new FenetreInformation(fenetrePrincipale, "Mort héros", "Un "+h.getNom()+" est mort.", 2);
        }
        else {
        	Monstre m = (Monstre)s;
            new FenetreInformation(fenetrePrincipale, "Mort monstre", "Un "+m.getNom()+" est mort.", 1);
        }
        
    }
    
    
}
