package fenetre;

import wumpus.Contexte;

// import java.util.Double;




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import java.awt.Font;

import graphisme.GraphismeVanilla;

public class MenuPrincipal extends JFrame {
	
	private Contexte ceContexte;
	
	protected double pLargeurPanneauDroite = 0.25;
	protected double pHauteurPanneauHaut = 0.25;
	
	protected double pMargeX = 0.025;
	protected double pMargeY = 0.1;
	
	protected double pLargeurCarte = 0.7;
	protected double pHauteurCarte = 0.65;
	
	protected double pHauteurTexte1 = 0.04;
	protected double pLargeurTexte1 = 0.8;
	
	protected double pHauteurTexte2 = 0.04;
	protected double pLargeurTexte2 = 0.15;
	
	protected double pHauteurBouton1 = 0.05;
	protected double pLargeurBouton1 = 0.25;
	
	protected double pHauteurPanneauBas = 0.0;
	
	
	private JPanel container = new JPanel();
	private  JPanel[] tab_jpanel = new JPanel[3];
	private  JButton[] tab_jbutton = new JButton[6];
	private JTextField[] tab_jtextfield = new JTextField[5];
	
	private BorderLayout layout = new BorderLayout();
	private String nomFenetre = "Menu principal";
	
	private GraphismeVanilla graphisme = new GraphismeVanilla();
	
	public MenuPrincipal(Contexte contexte){
		
		super();
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setResizable(true);
		super.setTitle(nomFenetre);
		this.ceContexte = contexte;
		super.setBounds(0, 0, ceContexte.LARGEUR_FENETRE, ceContexte.HAUTEUR_FENETRE);
		super.setLocationByPlatform(true);
		
		 //On définit le layout à utiliser sur le content pane
		super.setContentPane(container);
	    this.setLayout(layout);
	    layout.setHgap( ((Double)(super.getWidth()*pMargeX)).intValue() );
	    layout.setVgap( ((Double)(super.getHeight()*pMargeY)).intValue() );
	    
	    
	    // creation du paneau haut
	    tab_jpanel[0] = new JPanel();
	    tab_jpanel[0].setPreferredSize(new Dimension(super.getWidth()-layout.getHgap(),((Double)(super.getHeight()*pHauteurPanneauHaut)).intValue() ) );
	    tab_jtextfield[0] = new JTextField();
	    tab_jtextfield[0].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurPanneauHaut*0.5)).intValue() ));
	    tab_jtextfield[0].setEditable(false);
	    
	    tab_jtextfield[0].setBorder(BorderFactory.createEmptyBorder());
	    tab_jtextfield[0].setFont(new Font("Calibri", Font.BOLD,40));
	    tab_jtextfield[0].setHorizontalAlignment(JTextField.CENTER);
	    tab_jtextfield[0].setText("Projet IA Wumpus - version de cours");
	        
	    tab_jpanel[0].add(tab_jtextfield[0]);

	    // creation du panneau droit avec les differents boutons de controle
	    tab_jpanel[1] = new JPanel();
	    tab_jpanel[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurPanneauDroite)).intValue(), ((Double)(super.getHeight()*pHauteurCarte)).intValue() ) );
	    
	    tab_jbutton[0] = new JButton();
	    tab_jbutton[0].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[0].setText("Nouveau Jeu Libre");
	    tab_jbutton[0].addActionListener(new JeuLibreListener());
	   
	    tab_jbutton[1] = new JButton();
	    tab_jbutton[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[1].setText("Nouvelle Simulation Graphique");
	    tab_jbutton[1].addActionListener(new SimulationGraphique());
	    
	    tab_jbutton[2] = new JButton();
	    tab_jbutton[2].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[2].setText("Nouvelle Simulation en masse");
	    tab_jbutton[2].addActionListener(new SimulationMasse());
	    
	    tab_jbutton[3] = new JButton();
	    tab_jbutton[3].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[3].setText("Modification du contexte");
	    tab_jbutton[3].addActionListener(new ModificationContexte());
	    
	    tab_jbutton[4] = new JButton();
	    tab_jbutton[4].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[4].setText("A propos");
	    tab_jbutton[4].addActionListener(new APropos());
	    
	    tab_jbutton[5] = new JButton();
	    tab_jbutton[5].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[5].setText("Aide");
	    tab_jbutton[5].addActionListener(new Aide());
	    
	    tab_jpanel[1].add(tab_jbutton[0]);
	    tab_jpanel[1].add(tab_jbutton[1]);
	    tab_jpanel[1].add(tab_jbutton[2]);
	    tab_jpanel[1].add(tab_jbutton[3]);
	    tab_jpanel[1].add(tab_jbutton[4]);
	    tab_jpanel[1].add(tab_jbutton[5]);
	    
	    // creation du panneau bas (bordure basse)
	    tab_jpanel[2] = new JPanel();
	    tab_jpanel[2].setPreferredSize(new Dimension(super.getWidth(),((Double)(super.getHeight()*pHauteurPanneauBas)).intValue() ) );
	    
	    
	    container.add(tab_jpanel[1], layout.CENTER);
	    container.add(tab_jpanel[0], layout.NORTH);
	    container.add(tab_jpanel[2], layout.SOUTH);

		
		super.setVisible(true);
		super.setFocusable(true);
		
	}
	
	class FenetreListener implements ComponentListener {
		public void componentResized(ComponentEvent e) {
    
		}
		public void componentHidden(ComponentEvent e) {           
		}
		public void componentShown(ComponentEvent e) {           
		}
		public void componentMoved(ComponentEvent e) {           
		}
	}
	
	public void fermerFenetre(){
	    super.dispose();
	}
    
    class JeuLibreListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	fermerFenetre();
        	FenetreJeu fenetreJeu = new FenetreJeu(ceContexte);
        }
      }
   
    class SimulationGraphique implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	fermerFenetre();
        	FenetreJeuBoucle fenetre = new FenetreJeuBoucle(ceContexte);
        }
      }
    
    class SimulationMasse implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	fermerFenetre();
        	FenetreSimulationMasse fenetre = new FenetreSimulationMasse(ceContexte);
        }
      }
    
    class ModificationContexte implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	fermerFenetre();
        	FenetreContexte fenetre = new FenetreContexte(ceContexte);
        }
      }
   
    class APropos implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	fermerFenetre();
        	FenetreAPropos fenetre = new FenetreAPropos(ceContexte);
        }
      }
    
    class Aide implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	fermerFenetre();
        	FenetreAide fenetre = new FenetreAide(ceContexte);
        }
      }
    
}





