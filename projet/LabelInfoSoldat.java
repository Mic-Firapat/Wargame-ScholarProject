package projet;

import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;


/**
 * LabelInfosSoldat est la classe constituant le label permettant d'afficher les informations sur un soldat.
 * Elle hérite de JPanel
 * @version 1.1
 */
@SuppressWarnings("serial")

public class LabelInfoSoldat extends JLabel {
	
	 
	ImageIcon fond;
    ImageIcon portraits[];
	
	JLabel labelPortrait;
	JLabel labelNom;
	JLabel labelDescription;
	JLabel labelPV;
	JLabel labelPVmax;
	JLabel labelAttaque;
	JLabel labelSoinTitre;
	JLabel labelSoin;
	
	
	
    
    public LabelInfoSoldat(int type){
    	super();
    	

        portraits = new ImageIcon[4];
        Color couleur = new Color(30, 50, 70);
    	
    	try {
    		if(type == 1) {
    			fond = new ImageIcon(ImageIO.read(this.getClass().getResource("images/soldats/heros.png")));
    			portraits[0] = new ImageIcon(ImageIO.read(this.getClass().getResource("images/soldats/portrait_fantassin.png")));
                portraits[1] = new ImageIcon(ImageIO.read(this.getClass().getResource("images/soldats/portrait_archer.png")));
                portraits[2] = new ImageIcon(ImageIO.read(this.getClass().getResource("images/soldats/portrait_lancier.png")));
                portraits[3] = new ImageIcon(ImageIO.read(this.getClass().getResource("images/soldats/portrait_pretre.png")));
    		}
    		else {
    			fond = new ImageIcon(ImageIO.read(this.getClass().getResource("images/soldats/monstre.png")));
    			portraits[0] = new ImageIcon(ImageIO.read(this.getClass().getResource("images/soldats/portrait_ork.png")));
                portraits[1] = new ImageIcon(ImageIO.read(this.getClass().getResource("images/soldats/portrait_gobelin_archer.png")));
                portraits[2] = new ImageIcon(ImageIO.read(this.getClass().getResource("images/soldats/portrait_kobold.png")));
                portraits[3] = new ImageIcon(ImageIO.read(this.getClass().getResource("images/soldats/portrait_gobelin_shaman.png")));
                couleur = new Color(240, 180, 180);
    		}
    	}
    	catch (Exception e) {
            System.out.println("Images introuvables.");
            e.printStackTrace();
        }


    	setIcon(fond);
    	setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        labelPortrait = new JLabel();
        
        labelNom = new JLabel();
		labelNom.setForeground(couleur);
        labelNom.setHorizontalAlignment(SwingConstants.CENTER);
        labelNom.setFont(new Font("Serif", Font.BOLD, 12)); 
        
    	labelDescription = new JLabel();
		labelDescription.setForeground(couleur);
        labelDescription.setPreferredSize(new Dimension(180, 75));
        labelDescription.setFont(new Font("Serif", Font.BOLD, 12)); 
        
        JLabel labelPVTitre = new JLabel("Points de vie :");
		labelPVTitre.setForeground(couleur);
        labelPVTitre.setFont(new Font("Serif", Font.BOLD, 12)); 
        labelPVTitre.setHorizontalAlignment(SwingConstants.CENTER);
        
        labelPV = new JLabel();
		labelPV.setForeground(couleur);
        labelPV.setHorizontalAlignment(SwingConstants.CENTER);
        labelPV.setFont(new Font("Serif", Font.BOLD, 12)); 
        
        labelPVmax = new JLabel();
		labelPVmax.setForeground(couleur);
		labelPVmax.setHorizontalAlignment(SwingConstants.CENTER);
        labelPVmax.setFont(new Font("Serif", Font.BOLD, 12)); 
        
        
        JLabel labelAttaqueTitre = new JLabel("Force d'attaque :");
		labelAttaqueTitre.setForeground(couleur);
        labelAttaqueTitre.setFont(new Font("Serif", Font.BOLD, 12)); 
        labelAttaqueTitre.setHorizontalAlignment(SwingConstants.CENTER);
        
        labelAttaque = new JLabel();
		labelAttaque.setForeground(couleur);
        labelAttaque.setHorizontalAlignment(SwingConstants.CENTER);
        labelAttaque.setFont(new Font("Serif", Font.BOLD, 12)); 
        
        labelSoinTitre = new JLabel();
		labelSoinTitre.setForeground(couleur);
        labelSoinTitre.setFont(new Font("Serif", Font.BOLD, 12)); 
        labelSoinTitre.setHorizontalAlignment(SwingConstants.CENTER);
        labelSoinTitre.setPreferredSize(new Dimension(10, 10));
        
        labelSoin = new JLabel();
		labelSoin.setForeground(couleur);
        labelSoin.setHorizontalAlignment(SwingConstants.CENTER);
        labelSoin.setFont(new Font("Serif", Font.BOLD, 12)); 
        labelSoin.setPreferredSize(new Dimension(10, 10));
        
        // Components
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;


        c.insets = new Insets(0, 0, 20, 0);
        add(labelPortrait, c);

        c.insets = new Insets(10, 0, 0, 0);
        c.gridy = 1;
        add(labelNom, c);
        
        c.gridy = 2;
        add(labelDescription, c);
        
        c.gridy = 3;
        add(labelPVTitre, c);
        
        c.insets = new Insets(10, 70, 0, 0);
        c.gridwidth = 1;
        c.gridy = 4;
        add(labelPV, c);

        c.insets = new Insets(10, -75, 0, 0);
        c.gridx = 1;
        add(labelPVmax, c);        

        c.insets = new Insets(10, 0, 0, 0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 5;
        add(labelAttaqueTitre, c); 
		
        c.gridy = 6;
        add(labelAttaque, c); 
        
        c.gridy = 7;
        add(labelSoinTitre, c); 
        
        c.gridy = 8;
        c.insets = new Insets(10, 0, 40, 0);
        add(labelSoin, c); 
        
        setVisible(false);
        
    }
    
    /**
     * Remplit et affiche le label d'info avec les informations concernants le monstre
     * @param m <code>Monstre</code>
     * @since 1.1
     */
    public void afficher(Monstre m){
    	if (m != null) {
    		labelPortrait.setIcon(portraits[m.getTU()-1]);
    		labelNom.setText(m.getNom());
    		labelDescription.setText(m.getDescription());
    		labelPV.setText(""+m.getPV());
    		labelPVmax.setText("/ "+m.getPVmax());
    		labelAttaque.setText(""+m.getPD());
    		if(m.getTU() == 4) {
    			labelSoinTitre.setText("Soin de PV :");
    			labelSoin.setText(""+m.getHEAL());
    		}
    		else {
    			labelSoinTitre.setText("");
    			labelSoin.setText("");
    		}
    	}
    }
    
    /**
     * Remplit et affiche le label d'info avec les informations concernants le héros
     * @param h <code>Hero</code>
     * @since 1.1
     */
    public void afficher(Heros h){
    	if (h != null) {
    		labelPortrait.setIcon(portraits[h.getTU()-1]);
    		labelNom.setText(h.getNom());
    		labelDescription.setText(h.getDescription());
    		labelPV.setText(""+h.getPV());
    		labelPVmax.setText("/ "+h.getPVmax());
    		labelAttaque.setText(""+h.getPD());
    		if(h.getTU() == 4) {
    			labelSoinTitre.setText("Soin de PV :");
    			labelSoin.setText(""+h.getHEAL());
    		}
    		else {
    			labelSoinTitre.setText("");
    			labelSoin.setText("");
    		}
    	}
    }
    
}
