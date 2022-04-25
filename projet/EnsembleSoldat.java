package projet;

import java.io.Serializable;

/**
 * EnsembleSoldat est la classe permettant de construire un ensanble de soldat
 * @version 1.0
 */
public class EnsembleSoldat implements Serializable{

    /* Code d'identifiant généré aléatoirement avant d'être instancié
     * Produit en suivant la méthode interne des JVM
     * Utilisé par java pour donner un numéro de série unique aux objets
     * En théorie empêche un enregistrement de doublon
     * Permet d'assurer que l'objet en enregistré dans un fichier correspond
     * à la version qui cherche à le récupérer
     */
    private static final long serialVersionUID = -769148253L;

    

	private Soldat tabSoldats[];
	public static final int TAILLEMAX = 100;
	public int nbSoldats = 0;
	
	/**
     * Constructeur de la classe EnsembleSoldat
     * @since 0.9
     */
	public EnsembleSoldat() {
		tabSoldats = new Soldat[TAILLEMAX];
	}
	
	
	/**
     * Ajoute un soldat à l'ensemble
     * @param s <code>Soldat</code> soldat à inserer
     * @return 0 si succès -1 sinon
     * @since 0.9
     */
	public int ajouterSoldat(Soldat s) {
		if(nbSoldats == TAILLEMAX) {
			return -1;
		}
		else {
			tabSoldats[nbSoldats] = s;
			nbSoldats++;
			return 0;
		}
	}
	
	
	/**
     * Supprime le soldat de l'ensemble et de la map si il se trouve dans l'ensemble
     * @param s <code>Soldat</code> soldat à supprimer
     * @since 0.9
     */
    public void supprimerSoldat(Soldat s) {
        boolean suppr = false;
        if(estVide()) return;
        for(int i = 0; i < nbSoldats-1; i++) {
            
            if( !suppr && tabSoldats[i].getCase().equals(s.getCase())) { // On supprime
                tabSoldats[i].getCase().setHeros(null);
                tabSoldats[i].getCase().setMonstre(null);
                suppr = true;
            }
            if (suppr) // On décale
                tabSoldats[i] = tabSoldats[i+1];
        }
	if ( !suppr && tabSoldats[nbSoldats-1].getCase().equals(s.getCase())){
            //Si c'est la dernière case
            tabSoldats[nbSoldats-1].getCase().setHeros(null);
            tabSoldats[nbSoldats-1].getCase().setMonstre(null);
            suppr = true;
        }
        if (suppr) { // On ne supprime pas si le soldat n'était pas dans le tableau
            tabSoldats[nbSoldats-1] = null;
            nbSoldats--;
        }
    }
    
    
    /**
     * Supprime les soldats de l'ensemble qui sont en dohors de la grille
     * @param grille <code>Grille</code>
     * @since 1.2
     */
    public void supprimerSoldatsHorsGrille(Grille grille) {
        if(estVide()) return;
        
        for(int i = nbSoldats-1; i >= 0; i--) {
        	if (tabSoldats[i].getCase().getX()+tabSoldats[i].getCase().getZ()/2 >= grille.getLongueur() 
        			|| tabSoldats[i].getCase().getZ() >= grille.getHauteur() ) {
        		supprimerSoldat(tabSoldats[i]);
        	}
        }
    }
    
	/**
     * Supprime tous les soldats de l'ensemble et de la map
     * @since 0.9
     */
	public void vider() {
		if(estVide()) return;

		for(int i = 0; i < nbSoldats; i++) {
			tabSoldats[i].getCase().setHeros(null);
			tabSoldats[i].getCase().setMonstre(null);
			tabSoldats[i] = null;
		}
		nbSoldats = 0;
	}
	
	/**
     * Renvoie le nombre de soldats de l'ensemble
     * @return nbSoldats <code>int</code>
     * @since 0.9
     */
	public int nbSoldats() {
		return nbSoldats;
	}
	
	/**
     * Renvoie le soldat à l'indice i
     * @param i  <code>int</code>
     * @return <code>Soldat</code>
     * @since 0.9
     */
	public Soldat getSoldat(int i) {
		return tabSoldats[i];
	}
	
	/**
     * Renvoie si l'ensemble est vide
     * @return <code>boolean</code>
     * @since 0.9
     */
	public boolean estVide() {
		if(nbSoldats == 0)
			return true;
		else return false;
	}
	
	
	
}
