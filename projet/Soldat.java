package projet;

import java.io.Serializable;

/**
 * Soldat est la classe définissant les soldats (héros ou monstres). Elle implémente ISoldat. 
 * @version 1.1
 */
@SuppressWarnings("serial")

public class Soldat implements ISoldat, Serializable{

    
    private int PV;
    protected int PVmax;//pas de setteur
    private int PM;//mobilité
    protected int PMmax;//pas de setteur
    private int PO;//portée d'attaque
    private int PD;//dégats
    private int TU;//type d'unité
    private int HEAL; //Nombre de pv soignés par le soldat quand il soigne un allié

    //nombre d'attaques possibles du soldat en un tour.
    private int nbAT;
    protected int nbATmax;//pas de setteur
    //nombre de soins possibles du soldat en un tour.
    private int nbHEAL;
    protected int nbHEALmax;//pas de setteur
    
    protected Case emplacement;//Case sur laqualle se trouve le soldat
    

    /**
     * Constructeur de la classe Soldat.
     * @since 0.5
     */

    public Soldat(){
        //Stats de base
    	PV = 0;
        PVmax = 0;
        PM = 0;
        PMmax = 0;
        PO = 0;
        nbAT = 1;
        nbATmax = 1;
        HEAL = 0;
        nbHEAL = 0;
        nbHEALmax = 0;
    }


    /*Les getters */
    /**
     * Renvoie l'entier décrivant la quantité de soins fournie par l'unité.
     * @return <code>int</code> HEAL.
     * @since 0.6
     */
    public int getHEAL(){
        return HEAL;
    }
    /**
     * Renvoie l'entier décrivant le nombre d'attaques restantes de l'unité.
     * @return <code>int</code> nbAT.
     * @since 0.6
     */
    public int getnbAT(){
        return nbAT;
    }

    /**
     * Renvoie l'entier décrivant le nombre d'attaques maximum de l'unité.
     * @return <code>int</code> nbATmax.
     * @since 0.6
     */
    public int getnbATmax(){
        return nbATmax;
    }

    /**
     * Renvoie l'entier décrivant le nombre de soins restants de l'unité.
     * @return <code>int</code> nbHEAL.
     * @since 0.6
     */
    public int getnbHEAL(){
        return nbHEAL;
    }

    /**
     * Renvoie l'entier décrivant le nombre de soins maximum de l'unité.
     * @return <code>int</code> nbHEALmax.
     * @since 0.6
     */
    public int getnbHEALmax(){
        return nbHEALmax;
    }

    /**
     * Renvoie l'entier décrivant le type d'unité
     * @return Type de l'unité <code>int</code>
     * @since 0.5
     */

    public int getTU(){
	return TU;
    }

    /**
     * Renvoie l'entier décrivant la portée du soldat
     * @return PO <code>int</code>
     * @since 0.5
     */
    
    public int getPO(){
	return PO;
    }

    /**
     * Renvoie l'entier décrivant les points de mouvement restants du soldat
     * @return PM <code>int</code>
     * @since 0.5
     */
    
    public int getPM(){
	return PM;
    }

    /**
     * Renvoie l'entier décrivant les points de mouvement du soldat
     * @return PMmax <code>int</code>
     * @since 0.6
     */
    
    public int getPMmax(){
	return PMmax;
    }

    /**
     * Renvoie l'entier décrivant les points de vie actuels du soldat
     * @return PV <code>int</code>
     * @since 0.5
     */
    
    public int getPV(){
	return PV;
    }

    /**
     * Renvoie l'entier décrivant les points de vie maximum du soldat.
     * @return PVmax <code>int</code>
     * @since 0.5
     */
    
    public int getPVmax(){
	return PVmax;
    }


    /**
     * Renvoie l'entier décrivant les dégâts du soldat
     * @return PD <code>int</code>
     * @since 0.5
     */
    
    public int getPD(){
	return PD;
    }
    
    
    /**
     * Renvoie la case sur laquelle se trouve le soldat
     * @return emplacement <code>Case</code>
     * @since 0.9
     */
    
    public Case getCase(){
	return emplacement;
    }

    
    /*---------------------------------------------------------------------*/
    /*Les setters */

    /**
     * Modifie la quantité de soins fournie par l'unité quand elle soigne un allié.
     * @param h <code>int</code> la nouvelle quantité de soins.
     * @since 0.6
     */
    public void setHEAL(int h){
        if (h < 0) HEAL = 0;
        else HEAL = h;
    }
    
    /**
     * Modifie le nb d'attaques de restantes de l'unité.
     * @param a <code>int</code> le nouveau nombre.
     * @since 0.6
     */
    public void setnbAT(int a){
        if (a < 0){
            nbAT = 0;
        }
        else if (a > nbATmax){
            nbAT = nbATmax;
        }
        else nbAT = a;
    }

    /**
     * Modifie le nb de soins restants de l'unité.
     * @param h <code>int</code> le nouveau nombre.
     * @since 0.6
     */
    public void setnbHEAL(int h){
        if (h < 0){
            nbHEAL = 0;
        }
        else if (h > nbHEALmax){
            nbHEAL = nbHEALmax;
        }
        else nbHEAL = h;
    }
    
    /**
     * Modifie l'entier décrivant le type de l'unité
     * @param tu <code>int</code> nouvelle valeur
     * @since 0.5
     */

    public void setTU(int tu){
	this.TU = tu;
    }

    /**
     * Modifie l'entier décrivant la portée du soldat
     * @param po <code>int</code> nouvelle valeur
     * @since 0.5
     */
    public void setPO(int po){
	this.PO = po;
    }

    /**
     * Modifie l'entier décrivant les points de déplacement du soldat
     * @param pm <code>int</code> nouvelle valeur
     * @since 0.5
     */
    public void setPM(int pm){
        if (pm >=0 && pm <= PMmax){
            this.PM = pm;
        }
        else if (pm < 0){
            this.PM = 0;
        }
        else {
            this.PM =  PMmax;
        }

    }

    /**
     * Modifie l'entier décrivant les points de vie du soldat
     * @param pv <code>int</code> nouvelle valeur
     * @since 0.5
     */
    public void setPV(int pv){
        if (pv >= 0 && pv <= PVmax){
            this.PV = pv;
        }
        else if (pv <0){
            this.PV = 0;
        }
        else {
            this.PV = PVmax;
        }
    }


     /**
     * Modifie l'entier décrivant les points de dégâts du soldat
     * @param pd <code>int</code> nouvelle valeur
     * @since 0.5
     */
    public void setPD(int pd){
        if (pd >=0){
            this.PD = pd;
        }
        else this.PD = 0;
    }

    
    /**
     * Change la case sur laquelle se trouve le soldat
     * @param c <code>Case</code>
     * @since 0.9
     */
    
    public void setCase(Case c){
    	emplacement = c;
    }
    


    /*--------------------------------------------------------------------*/
    //Diverses méthodes
    /**
     * Fonction de déplacement
     * Décrémente les PMs actuels du mouvement.
     * @param mouvement <code>int</code>
     * @since 0.5
     */
    public void deplacement(int mouvement){
        setPM(PM - mouvement);
    }

    /**
     * Fonction de dégâts
     * @param cible <code>Soldat</code> la cible de l'attaque.
     * @since 0.5
     */
    public void attaque(Soldat cible){
        if (nbAT > 0){
            cible.setPV(cible.getPV() - this.getPD() );
            nbAT--;
        }
    }

    /**
     * Fonction de soins
     * @param cible <code>Soldat</code> la cible du soin.
     * @since 0.5
     */
    public void soigne(Soldat cible){
        if (nbHEAL > 0){
            cible.setPV(cible.getPV() + this.getHEAL());
            nbHEAL--;
        }
    }

    /**
     * Fonction qui indique si un soldat a effectué une action ou pas ce tour ci.
     * @return <code>boolean</code>
     * @since 0.6
     */
    public boolean aJoue(){
        return !(PM == PMmax && nbAT == nbATmax && nbHEAL == nbHEALmax);
    }

    /**
     * Fonction qui indique si un soldat a encore une action possible ou pas ce tour ci.
     * @return <code>boolean</code>
     * @since 0.6
     */
    public boolean peutJouer(){
        return !(PM == 0 && nbAT == 0 && nbHEAL == 0);
    }

    /**
     * Fonction appelée à chaque fin de tour pour redonner à un soldat ses capacités pour le tour suivant.
     * Ne reçoit pas de paramètres et ne renvoie rien.
     * @since 0.6
     */
    public void newTour(){
        if (aJoue()){
            //Si le Soldat a joué on redresse ces valeurs
            PM = PMmax;
            nbAT = nbATmax;
            nbHEAL = nbHEALmax;
        }
        else {
            //Si le Soldat n'a pas joué, alors il se repose.
            repos();
        }
    }

    /**
     * Méthode appelée en cas de repos de l'unité.
     * Pour l'instant , soigne d'un montant fixe peut importe la classe.
     * Ne pas hésitez à la surcharger dans une classe fille.
     * @since 0.7
     */
    public void repos(){
        this.setPV(PV + 5);
    }
     
}
