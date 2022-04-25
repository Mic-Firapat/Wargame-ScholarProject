package projet;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * FenetreInformation est la classe définissant la fenêtre qui afficher des infoemations
 * Elle hérite de JDialog
 * @version 0.9
 */
@SuppressWarnings("serial")
public class FenetreInformation extends JDialog{
	
	
    int mode; // MODE : 0 pour fond blanc, 1 pour fond rouge, 2 pour fond noir
	
    JPanel panel;
    BufferedImage imageFond;
    
	
    /**
     * Constructeur de la classe FenetreInformation
     * @param parent <code>JFrame</code> la JFrame parent.
     * @param titre <code>String</code> titre de la fenetre.
     * @param message <code>String</code> message à afficher dans la fenêtre.
     * @param fond <code>int</code> défini la couleur de fond
     * @since 0.9
     */
    public FenetreInformation(JFrame parent, String titre, String message, int fond) {
        super(parent, titre, true);
        setBounds(parent.getSize().width/2-250, parent.getSize().height/2+100, 700, 300);
         

        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(600, 75));
        label.setFont(new Font("Serif", Font.BOLD, 15));
        
        
        try {
        	if (fond == 2) {
        		imageFond = ImageIO.read(this.getClass().getResource("images/menus/fond_information_noir.png"));
                label.setForeground(new Color(255, 150, 150));
        	}
        	else if (fond == 1) {
        		imageFond = ImageIO.read(this.getClass().getResource("images/menus/fond_information_rouge.png"));
                label.setForeground(new Color(255, 200, 200));
        	} 
        	else {
        		imageFond = ImageIO.read(this.getClass().getResource("images/menus/fond_information_blanc.png"));
                label.setForeground(Color.white);
        	}

        }catch (Exception e) {
            System.out.println("Images introuvables.");
            e.printStackTrace();
        }
        
        
        Bouton boutonOk = new Bouton("OK", 0);;
        boutonOk.setPreferredSize(new Dimension(200, 60));
        boutonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                setVisible(false); 
                dispose();	
            }
        }
            );


        panel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(imageFond, 0, 0, 700, 300, this);
            }
        };
        

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

		
        // Components
        c.gridy = 0;
        panel.add(label, c);
		
        c.gridy = 1;
        c.insets = new Insets(30, 0, 0, 0);
        panel.add(boutonOk, c);

        add(panel);

        setVisible(true); 

    }
	
	
}
