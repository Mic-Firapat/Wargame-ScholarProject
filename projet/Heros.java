package projet;


import java.io.Serializable;
//import java.io.ObjectOutputStream;
//import java.io.ObjectInputStream;

/**
 * Heros est la classe définissant les héros.
 * @version 0.3
 */
public class Heros extends Soldat implements Serializable{

    /* Code d'identifiant généré aléatoirement avant d'être instancié
     * Produit en suivant la méthode interne des JVM
     * Utilisé par java pour donner un numéro de série unique aux objets
     * En théorie empêche un enregistrement de doublon
     * Permet d'assurer que l'objet en enregistré dans un fichier correspond
     * à la version qui cherche à le récupérer
     */
    private static final long serialVersionUID = 495572186L;


    /**
     * Constructeur de la classe Heros.
     * @param type <code>int</code> entier décrivant l'unité à créer.
     * @since 0.0
     */

    public Heros(int type){
	super();
	switch(type){

            //ATTENTION ORDRE IMPORTANT :
            //ON AFFECTE LE MAX PUIS LA STAT
	case(ID_FANTASSIN):
	    this.setTU(ID_FANTASSIN);
            PVmax = 20;
	    this.setPV(20);
            PMmax = 2;
            this.setPM(2);
	    this.setPO(1);
	    this.setPD(8);
	    break;

	case(ID_ARCHER):
	    this.setTU(ID_ARCHER);
            PVmax = 20;
	    this.setPV(20);
            PMmax = 2;
	    this.setPM(2);
            this.setPO(2);
	    this.setPD(5);
	    break;

	case(ID_LANCIER):
	    this.setTU(ID_LANCIER);
            PVmax = 25;
	    this.setPV(25);
            PMmax = 2;
	    this.setPM(2);           
	    this.setPO(2);
	    this.setPD(4);
	    break;

	case(ID_PRETRE):
	    this.setTU(ID_PRETRE);
            PVmax = 15;
	    this.setPV(15);
            PMmax = 2;
            this.setPM(2);
            this.setPO(2);
	    this.setPD(3);
            setHEAL(5);
            nbHEALmax = 1;
            setnbHEAL(1);
            break;
		    
		
	default: /*Par défaut on va créer un fantassin */
            this.setTU(ID_FANTASSIN);
            PVmax = 20;
	    this.setPV(20);
            PMmax = 2;
            this.setPM(2);
            this.setPO(1);
	    this.setPD(8);
	    break;
	}
    }

    /**
     * Redéfinition de toString
     * @return chaine de caractère décrivant l'unité
     * @since 0.0
     */

    public String toString(){
	String type_unite;
	switch(this.getTU()){

	case(ID_FANTASSIN):
	    type_unite = "Fantassin";
	    break;
	case(ID_ARCHER):
	    type_unite = "Archer";
	    break;
	case(ID_LANCIER):
	    type_unite = "Lancier";
	    break;
	case(ID_PRETRE):
	    type_unite = "Pretre";
	    break;
	default: /*On ne devrait pas rentrer dans ce cas mais par précaution */
	    type_unite = "INCONNUE";
	    break;
	}

	return "<"+type_unite+"> a :\n("+this.getPV()+") pv,\n("+this.getPM()+") pm,\n("+this.getPO()+") po,\n("+this.getPD()+") dégâts\nAppartient à la faction : "+this.getClass()+"\n";
    }

    /**
     * Renvoie le nom de la classe du monstre.
     * @return Renvoie la chaîne correspondante.
     * @since 0.1
     */

    public String getNom(){
    	String type_unite;
    	switch(this.getTU()){

    	case(ID_FANTASSIN):
    		type_unite = "Fantassin";
    	break;
    	case(ID_ARCHER):
    		type_unite = "Archer";
    	break;
    	case(ID_LANCIER):
    		type_unite = "Lancier";
    	break;
    	case(ID_PRETRE):
    		type_unite = "Pretre";
    	break;
    	default: /*On ne devrait pas rentrer dans ce cas mais par précaution */
    		type_unite = "INCONNUE";
    		break;
    	}
    	return type_unite;
    }
    
    
    /**
	 * Renvoie une description du héros.
	 * @return une description du héros
	 * @since 1.1
	 */

	public String getDescription(){
		String description;
		switch(this.getTU()){
		case(ID_FANTASSIN):
			description = "<html>Sa force et sa résistance font de lui un allié puissant. Il peut infliger 2 attaques en seul un tour.</html>";
		break;
		case(ID_ARCHER):
			description = "<html>Maître de son arc, il peut atteindre sa cible sur de grandes distances.</html>";
		break;
		case(ID_LANCIER):
			description = "<html>Sa lance lui permet d'avoir une grande portée d'attaque, couplée à sa forte résistance.</html>";
		break;
		case(ID_PRETRE):
			description = "<html>Bien que faible, il a la faculté unique de pouvoir soigner ses alliés.</html>";
		break;
		default: /*On ne devrait pas rentrer dans ce cas mais sait-on jamais */
			description = "";
			break;
		}
		return description;
	}
	
    
    /*
    /**
     * Surcharge de la méthode readObject afin de lire le format de sauvegarde définit par writeObject
     * @param ois <code>ObjectInputStream</code>
     * @since 0.3
     

    
    private void readObject(ObjectInputStream ois) throws Exception, ClassNotFoundException{

	this.setTU(ois.read());
	this.setPV(ois.read());
	this.PVmax = ois.read();
	this.setPM(ois.read());
	this.PMmax = ois.read();
	this.setPO(ois.read());
	this.setPD(ois.read());

    }

    /**
     * Surcharge de la méthode writeObject afin de prendre que des informations nécessaires
     * @param oos <code>ObjectInputStream</code>
     * @since 0.3
     

    private void writeObject(ObjectOutputStream oos) throws Exception{

	oos.write(this.getTU());
	oos.write(this.getPV());
	oos.write(PVmax);
	oos.write(this.getPM());
	oos.write(PMmax);
	oos.write(this.getPO());
	oos.write(this.getPD());
	
    }*/


}


