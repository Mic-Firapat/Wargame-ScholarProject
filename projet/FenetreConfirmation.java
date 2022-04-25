package projet;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * FenetreConfirmation est la classe permettant d'ouvrir une fenetre pour demander la confirmation de l'utilisateur
 * Elle hérite de JDialog
 * @version 0.9
 */
@SuppressWarnings("serial")
public class FenetreConfirmation extends JDialog{
	
    boolean confirmation = false;
    
	
    JPanel panel;
    BufferedImage fond;
	
    /**
     * Constructeur de la classe FenetreConfirmation
     * @param parent <code>JFrame</code> la JFrame parent.
     * @param titre <code>String</code> titre de la fenetre.
     * @param message <code>String</code> message à afficher dans la fenêtre.
     * @since 0.8
     */
    public FenetreConfirmation(JFrame parent, String titre, String message) {
        super(parent, titre, true);
        setBounds(parent.getSize().width/2-250, parent.getSize().height/2-150, 700, 400);
        
        try {
            fond = ImageIO.read(this.getClass().getResource("images/menus/fond_confirmation.png"));

        }catch (Exception e) {
            System.out.println("Images introuvables.");
            e.printStackTrace();
        }
        
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setForeground(Color.white);
        label.setFont(new Font("Serif", Font.BOLD, 16));
        
        
        
        Bouton oui = new Bouton("Oui", 1);
		
        oui.setPreferredSize(new Dimension(200, 60));
        oui.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	confirmation = true;
                setVisible(false); 
                dispose();	
            }
        }
            );

        
        Bouton annuler = new Bouton("Annuler", 1);
		
        annuler.setPreferredSize(new Dimension(200, 60));
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	confirmation = false;
                setVisible(false); 
                dispose();	
            }
        }
            );


        panel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(fond, 0, 0, 700, 400, this);
            }
        };
        

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

		
        // Components
        c.gridwidth = 2; 
        c.insets = new Insets(40, 0, 70, 0);
        panel.add(label, c);

        c.gridy = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1; 
        panel.add(oui, c);
        
        c.gridx = 1;
        c.insets = new Insets(0, 30, 0, 0);
        panel.add(annuler, c); 

        add(panel);

        setVisible(true); 

    }
    
    /**
     * Renvoie la valeur de confirmation (si l'utilisateur a confirmé ou non)
     * @return confirmation <code>boolean</code>
     * @since 0.8
     */
    public boolean getConfirmation() {
    	return confirmation;
    }
	
}
