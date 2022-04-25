package projet;

import java.io.Serializable;

/**
 * Point est la classe représentant le point qui définit le centre d'une case.
 * @version 0.3
 */
@SuppressWarnings("serial")
public class Point implements Serializable{

    private int x, y ,z;

    /**
     * Constructeur de la classe Point.
     * @param x valeur de la coordonnée x.
     * @param y valeur de la coordonnée y.
     * @param z valeur de la coordonnée z.
     * @since 0.0
     */
    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    

    /*Getteurs*/
    /**
     * Renvoie la valeur x.
     * @return la valeur entière de la coordonnée x.
     * @since 0.0
     */
    public int getX() {
        return x;
    }

    /**
     * Renvoie la valeur y.
     * @return la valeur entière de la coordonnée y.
     * @since 0.0
     */
    public int getY() {
        return y;
    }

    /**
     * Renvoie la valeur z.
     * @return la valeur entière de la coordonnée z.
     * @since 0.0
     */
    public int getZ() {
        return z;
    }
    
    /*Setteurs*/

    /**
     * Met à jour la coordonnée x.
     * @param x : la nouvelle valeur de x.
     * @since 0.1
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Met à jour la coordonnée y.
     * @param y la nouvelle valeur de y.
     * @since 0.1
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Met à jour la coordonnée z.
     * @param z la nouvelle valeur de z.
     * @since 0.1
     */
    public void setZ(int z){
        this.z = z;
    }

    /**
     * Surcharge de toString pour afficher les tests.
     * @return Renvoie la chaîne correspondante.
     * @since 0.3
     */

    public String toString(){
	return "Point : ("+this.getX()+", "+this.getY()+", "+this.getZ()+")\n";
    }
}
