package projet;

import java.io.Serializable;
//import java.io.ObjectOutputStream;
//import java.io.ObjectInputStream;

/**
 * Monstre est la classe définissant les monstres
 * @version 0.3
 */
@SuppressWarnings("serial")
public class Monstre extends Soldat implements Serializable{

    /**
     * Constructeur de la classe Monstre.
     * @param type <code>int</code> type du monstre.
     * @since 0.0
     */
	public Monstre(int type){
		super();
		switch(type){

		//ATTENTION ORDRE IMPORTANT :
		//ON AFFECTE LE MAX PUIS LA STAT
		case(ID_ORK):
			this.setTU(ID_ORK);
			PVmax = 20;
			this.setPV(20);
			PMmax = 2;
			this.setPM(2);
			this.setPO(1);
			this.setPD(8);
			break;

		case(ID_GOBELIN_ARCHER):
			this.setTU(ID_GOBELIN_ARCHER);
			PVmax = 15;
			this.setPV(15);
			PMmax = 2;
			this.setPM(2);
			this.setPO(2);
			this.setPD(5);
			break;

		case(ID_KOBOLD):
			this.setTU(ID_KOBOLD);
			PVmax = 25;
			this.setPV(25);
			PMmax = 2;
			this.setPM(2);
			this.setPO(2);
			this.setPD(4);
			break;

		case(ID_GOBELIN_SHAMAN):
			this.setTU(ID_GOBELIN_SHAMAN);
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

		default: /*par défaut on va créer un ork */
			this.setTU(ID_ORK);
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
		case(ID_ORK):
			type_unite = "Ork";
		break;
		case(ID_GOBELIN_ARCHER):
			type_unite = "Gobelin archer";
		break;
		case(ID_KOBOLD):
			type_unite = "Kobold";
		break;
		case(ID_GOBELIN_SHAMAN):
			type_unite = "Gobelin Shaman";
		break;
		default: /*On ne devrait pas rentrer dans ce cas mais sait-on jamais */
			type_unite = "INCONNUE";
			break;
		}
		return "<"+type_unite+"> a :\n("+this.getPV()+") pv,\n("+this.getPM()+") pm,\n("+this.getPO()+") po,\n("+this.getPD()+") dégâts\nAppartient à la faction : "+this.getClass()+"\n";

	}

	/**
	 * Renvoie le nom de la classe du monstre.
	 * @return le nom de la classe du monstre.
	 * @since 0.1
	 */

	public String getNom(){
		String type_unite;
		switch(this.getTU()){
		case(ID_ORK):
			type_unite = "Ork";
		break;
		case(ID_GOBELIN_ARCHER):
			type_unite = "Gobelin archer";
		break;
		case(ID_KOBOLD):
			type_unite = "Kobold";
		break;
		case(ID_GOBELIN_SHAMAN):
			type_unite = "Gobelin Shaman";
		break;
		default: /*On ne devrait pas rentrer dans ce cas mais sait-on jamais */
			type_unite = "INCONNUE";
			break;
		}
		return type_unite;
	}
	
	
	/**
	 * Renvoie une description du monstre.
	 * @return une description du monstre
	 * @since 1.1
	 */

	public String getDescription(){
		String description;
		switch(this.getTU()){
		case(ID_ORK):
			description = "<html>Sa force et sa résistance font de lui un ennemi redoutable, bien que peu mobile.</html>";
		break;
		case(ID_GOBELIN_ARCHER):
			description = "<html>Maître de son arc, il peut atteindre sa cible sur de grandes distances.</html>";
		break;
		case(ID_KOBOLD):
			description = "<html>Sa lance lui permet d'avoir une grande portée d'attaque, couplée à sa forte résistance.</html>";
		break;
		case(ID_GOBELIN_SHAMAN):
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
