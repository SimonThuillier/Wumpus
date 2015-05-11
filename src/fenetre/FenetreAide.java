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

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import graphisme.GraphismeVanilla;

public class FenetreAide extends JFrame {
	
	private Contexte ceContexte;
	
	protected double pHauteurPanneauHaut = 0.1;
	
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
	private String nomFenetre = "Aide";
	
	private GraphismeVanilla graphisme = new GraphismeVanilla();
	
	public FenetreAide(Contexte contexte){
		
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
	    
	    tab_jbutton[0] = new JButton();
	    tab_jbutton[0].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[0].setText("Retour Menu principal");
	    tab_jbutton[0].addActionListener(new RetourMenuPrincipal());
	   
	    tab_jpanel[0].add(tab_jbutton[0]);
	    
	    tab_jpanel[1] = new JPanel();
	    tab_jpanel[1].setPreferredSize(new Dimension(super.getWidth()-layout.getHgap(),((Double)(super.getHeight()*(1-pHauteurPanneauHaut))).intValue() ) );
	    
	    tab_jtextarea[0] = new JTextArea();
	    tab_jtextarea[0].setBorder(BorderFactory.createEmptyBorder());
	    tab_jtextarea[0].setBackground(null);
	    tab_jtextarea[0].setEditable(false);
	    // tab_jtextfield[1].setFont(new Font("Calibri", Font.BOLD,40));
	    //tab_jtextarea[1].set
	    
	    JScrollPane scroll = new JScrollPane(tab_jpanel[1]);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


	    
	    String texteAPropos="";
	    
	    texteAPropos = texteAPropos + "Controles de jeu libre : " + "\r\n";
	    texteAPropos = texteAPropos + "Deplacements avec les fleches clavier " + "\r\n";
	    texteAPropos = texteAPropos + "Tirer des fleches : Haut : Z, Droite : D, Bas : X, Gauche : Q\r\n";
	    texteAPropos = texteAPropos + "Ramasser le lingot : Espace\r\n \r\n "
	    		+ "Statistiques : \r\n"
	    		+ " Elles sont stockées dans le dossier statistiques depuis la racine de l'application (emplacement du .jar)" + "\r\n\r\n";
	    
	    texteAPropos = texteAPropos + "Contexte :  \r\n "
	    		+ "Seul le fichier contexte.ini du dossier contexte est chargé, mais vous pouvez en faire des copies à utiliser ensuite. \r\n";

	    
	    tab_jtextarea[0].setText(texteAPropos);
	    tab_jpanel[1].add(tab_jtextarea[0]);
	    
	    
	    
	    
	    container.add(tab_jpanel[0], layout.NORTH);
	    container.add(scroll, layout.CENTER);


		
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





