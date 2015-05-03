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
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import java.awt.Font;

import graphisme.GraphismeVanilla;

public class FenetreAPropos extends JFrame {
	
	private Contexte ceContexte;
	
	protected double pHauteurPanneauHaut = 0.25;
	
	protected double pMargeX = 0.025;
	protected double pMargeY = 0.1;
	
	protected double pHauteurTexte1 = 0.04;
	protected double pLargeurTexte1 = 0.8;
	
	protected double pHauteurTexte2 = 0.04;
	protected double pLargeurTexte2 = 0.15;
	
	protected double pHauteurBouton1 = 0.05;
	protected double pLargeurBouton1 = 0.25;
	
	protected double pHauteurPanneauBas = 0.0;
	
	
	private JPanel container = new JPanel();
	private  JPanel[] tab_jpanel = new JPanel[2];
	private  JButton[] tab_jbutton = new JButton[1];
	private JTextField[] tab_jtextfield = new JTextField[1];
	private JTextArea[] tab_jtextarea = new JTextArea[1];
	
	private BorderLayout layout = new BorderLayout();
	private String nomFenetre = "A propos";
	
	private GraphismeVanilla graphisme = new GraphismeVanilla();
	
	public FenetreAPropos(Contexte contexte){
		
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
	    tab_jtextfield[0].setText("A Propos");
	    
	    tab_jbutton[0] = new JButton();
	    tab_jbutton[0].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[0].setText("Retour Menu principal");
	    tab_jbutton[0].addActionListener(new RetourMenuPrincipal());
	   
	        
	    tab_jpanel[0].add(tab_jtextfield[0]);
	    tab_jpanel[0].add(tab_jbutton[0]);
	    
	    tab_jpanel[1] = new JPanel();
	    tab_jpanel[1].setPreferredSize(new Dimension(super.getWidth()-layout.getHgap(),((Double)(super.getHeight()*(1-pHauteurPanneauHaut))).intValue() ) );
	    
	    tab_jtextarea[0] = new JTextArea();
	    tab_jtextarea[0].setBorder(BorderFactory.createEmptyBorder());
	    tab_jtextarea[0].setBackground(null);
	    tab_jtextarea[0].setEditable(false);
	    // tab_jtextfield[1].setFont(new Font("Calibri", Font.BOLD,40));
	    //tab_jtextarea[1].set
	    
	   
	   
	    
	    String texteAPropos="";
	    
	    texteAPropos = texteAPropos + "Projet IA Wumpus 2015 en licence CreativeCommons" + "\r\n\r\n";
	    texteAPropos = texteAPropos + "Projet creé par Simon Thuillier alias Belze88 sur OpenClassrooms." + "\r\n";
	    texteAPropos = texteAPropos + "Il s'agit d'un projet educatif visant à fournir une petite plateforme de développement d'ia sur le jeu Wumpus.\r\n "
	    		+ "Il permet de mettre en oeuvre les techniques développées dans le livre Intelligence artificielle de Stuart Russel et Peter Norvig, \r\n"
	    		+ " notamment les chapitres 2 à 10." + "\r\n\r\n";
	    texteAPropos = texteAPropos + "Je tiens à remercier : \r\n Aziz Smaoui et Anthony Martin pour leurs conseils en programmation. \r\n" + 
	    		"Mes professeurs d'automatique et notamment Claude Iung pour m'avoir donné le gout de la matiere. \r\n" + 
	    		"Isaac Asimov pour son cycle des robots et fondation.";

	    
	    tab_jtextarea[0].setText(texteAPropos);
	    tab_jpanel[1].add(tab_jtextarea[0]);
	    
	    
	    
	    
	    container.add(tab_jpanel[0], layout.NORTH);
	    container.add(tab_jpanel[1], layout.CENTER);


		
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
	
    class RetourMenuPrincipal implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	fermerFenetre();
        	MenuPrincipal fenetre = new MenuPrincipal(ceContexte);
        }
    }
    

}





