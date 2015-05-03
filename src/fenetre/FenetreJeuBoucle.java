package fenetre;

import wumpus.Contexte;

// import java.util.Double;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;

import carte.Carte;
import carte.VueCarte;
import fichier.FicStatistiqueHandler;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FenetreJeuBoucle extends JFrame {
	
	private Contexte ceContexte;
	
	protected double pLargeurPanneauDroite = 0.25;
	protected double pHauteurPanneauHaut = 0.25;
	
	protected double pMargeX = 0.025;
	protected double pMargeY = 0.025;
	
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
	private Carte carteJeu;
	private VueCarte vueCarte;
	private  JPanel[] tab_jpanel = new JPanel[3];
	private  JButton[] tab_jbutton = new JButton[6];
	private JTextField[] tab_jtextfield = new JTextField[5];
	
	private BorderLayout layout = new BorderLayout();
	private String nomFenetre = "Fenetre simulation graphique";
	
	//protected jeuListener jeuListener = new jeuListener();
	private boolean simulationEnCours = false;
	private Timer timerFenetre;
	private TimerTask simulation;
	long delaiTour;
	
	public FenetreJeuBoucle(Contexte contexte){
		
		super();
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setResizable(true);
		super.setTitle(nomFenetre);
		this.ceContexte = contexte;
		delaiTour = contexte.DELAI_TOUR_SIMULATION;
		super.setBounds(0, 0, ceContexte.LARGEUR_FENETRE, ceContexte.HAUTEUR_FENETRE);
		super.setLocationByPlatform(true);
		
		 //On définit le layout à utiliser sur le content pane
		super.setContentPane(container);
	    this.setLayout(layout);
	    layout.setHgap( ((Double)(super.getWidth()*pMargeX)).intValue() );
	    layout.setVgap( ((Double)(super.getHeight()*pMargeY)).intValue() );
	    

	    carteJeu = new Carte( ((Double)(super.getWidth()*pLargeurCarte)).intValue(), ((Double)(super.getHeight()*pHauteurCarte)).intValue(),ceContexte);
	    carteJeu.creerVue();
	    vueCarte = carteJeu.getVueCarte();
	    vueCarte.setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurCarte)).intValue(), ((Double)(super.getHeight()*pHauteurCarte)).intValue() ) );
	    // carteJeu.addKeyListener(new jeuListener());
	    
	    // creation du paneau haut
	    tab_jpanel[0] = new JPanel();
	    tab_jpanel[0].setPreferredSize(new Dimension(super.getWidth()-layout.getHgap(),((Double)(super.getHeight()*pHauteurPanneauHaut)).intValue() ) );
	    tab_jtextfield[0] = new JTextField();
	    tab_jtextfield[0].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[0].setEditable(false);
	    tab_jtextfield[0].setText("");
	    
	    tab_jtextfield[1] = new JTextField();
	    tab_jtextfield[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue()));
	    tab_jtextfield[1].setEditable(false);
	    tab_jtextfield[1].setText("");
	    
	    tab_jtextfield[2] = new JTextField();
	    tab_jtextfield[2].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue()));
	    tab_jtextfield[2].setEditable(false);
	    tab_jtextfield[2].setText("");
	    
	    tab_jtextfield[3] = new JTextField();
	    tab_jtextfield[3].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[3].setEditable(false);
	    tab_jtextfield[3].setText("");
	    
	    tab_jtextfield[4] = new JTextField();
	    tab_jtextfield[4].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[4].setEditable(false);
	    tab_jtextfield[4].setText("Fleches : ");
	    
	    
	    tab_jpanel[0].add(tab_jtextfield[0]);
	    tab_jpanel[0].add(tab_jtextfield[1]);
	    tab_jpanel[0].add(tab_jtextfield[2]);
	    tab_jpanel[0].add(tab_jtextfield[3]);
	    tab_jpanel[0].add(tab_jtextfield[4]);
	    
	    // creation du panneau droit avec les differents boutons de controle
	    tab_jpanel[1] = new JPanel();
	    tab_jpanel[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurPanneauDroite)).intValue(), ((Double)(super.getHeight()*pHauteurCarte)).intValue() ) );
	    
	    tab_jbutton[0] = new JButton();
	    tab_jbutton[0].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[0].setText("Arreter");
	    tab_jbutton[0].addActionListener(new arreterListener());
	    
	    tab_jbutton[1] = new JButton();
	    tab_jbutton[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[1].setText("Reveler");
	    tab_jbutton[1].addActionListener(new revelerListener());
	    
	    tab_jbutton[2] = new JButton();
	    tab_jbutton[2].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[2].setText("Nouvelle simulation");
	    tab_jbutton[2].addActionListener(new recommencerListener());
	    
	    tab_jbutton[3] = new JButton();
	    tab_jbutton[3].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[3].setText("Lancer simulation");
	    tab_jbutton[3].addActionListener(new lancerSimulation());
	    
	    tab_jbutton[4] = new JButton();
	    tab_jbutton[4].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[4].setText("Ecrire statistiques");
	    tab_jbutton[4].addActionListener(new ecrireStats());
	    
	    tab_jbutton[5] = new JButton();
	    tab_jbutton[5].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[5].setText("Retour Menu Principal");
	    tab_jbutton[5].addActionListener(new RetourMenuPrincipal());
	    
	    tab_jpanel[1].add(tab_jbutton[0]);
	    tab_jpanel[1].add(tab_jbutton[1]);
	    tab_jpanel[1].add(tab_jbutton[2]);
	    tab_jpanel[1].add(tab_jbutton[3]);
	    tab_jpanel[1].add(tab_jbutton[4]);
	    tab_jpanel[1].add(tab_jbutton[5]);
	    
	    
	    // creation du panneau bas (bordure basse)
	    tab_jpanel[2] = new JPanel();
	    tab_jpanel[2].setPreferredSize(new Dimension(super.getWidth(),((Double)(super.getHeight()*pHauteurPanneauBas)).intValue() ) );
	    
	    
	    container.add(tab_jpanel[1], layout.EAST);
	    container.add(vueCarte, layout.CENTER);
	    container.add(tab_jpanel[0], layout.NORTH);
	    container.add(tab_jpanel[2], layout.SOUTH);

		
		// super.setJMenuBar(new JMenuBar());
		// super.getJMenuBar().setBounds(0, 0, nbPixelx-20, 20);
	    carteJeu.creerIA(); // creer l'ia du jeu
	    vueCarte.setFocusable(true);
	    vueCarte.setVisible(true);
		super.setVisible(true);
		super.setFocusable(true);
		
		
		super.repaint();
		vueCarte.repaint();
		moteur();
		super.addComponentListener(new fenetreListener());
		
		timerFenetre = new Timer();
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
	
	class fenetreListener implements ComponentListener {
		public void componentResized(ComponentEvent e) {
			vueCarte.redimensionner( ((Double)(getWidth()*pLargeurCarte)).intValue(), ((Double)(getHeight()*pHauteurCarte)).intValue());      
		}
		public void componentHidden(ComponentEvent e) {           
		}
		public void componentShown(ComponentEvent e) {           
		}
		public void componentMoved(ComponentEvent e) {           
		}
	}

	// 0 en cours, 1 : terminé par victoire, 2 terminé par mort, 3 terminé par abandon
    
    public void moteur(){
    	
    	tab_jtextfield[0].setText(carteJeu.getMessageAventurier());
    	tab_jtextfield[1].setText(carteJeu.getMessageJeu());
    	tab_jtextfield[2].setText("Tour : " + carteJeu.getTour() + " / " + carteJeu.getMaxTour());
    	tab_jtextfield[3].setText("Score : " + carteJeu.getScore());
    	tab_jtextfield[4].setText("Fleches : " + carteJeu.getNbFleches() + "/" +carteJeu.getNbMaxFleches());

    	// a chaque fois qu'on appelle le moteur on retourne le focus sur la fenetre
    	super.requestFocusInWindow();
    	vueCarte.repaint();
    	super.repaint();   	
    }
    	
    class arreterListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	simulation.cancel();
        	timerFenetre.cancel();
        	simulationEnCours = false;
    		timerFenetre.purge();
        }
      }
    
    class lancerSimulation implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	if (! simulationEnCours && carteJeu.getEtatJeu() == 0){
              	timerFenetre.purge();
        		simulationEnCours = true;
        		simulation = new simulerTour();
        		timerFenetre.schedule(simulation,1,delaiTour); 		
        	}
         } 
      }	
    
    class ecrireStats implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	if (! simulationEnCours && carteJeu.getEtatJeu() != 0){
        		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        		Date date = new Date();
        		String nomFichier = "SimulationUnitaire_" + dateFormat.format(date); 
        		System.out.println(nomFichier);
        		
              	FicStatistiqueHandler writerFichier = new FicStatistiqueHandler(nomFichier);
              	writerFichier.ecrireFichier(carteJeu.getEnteteStatistiqueCarte() + "\n" + carteJeu.getStatistiqueCarte());
              	writerFichier.fermerFichier();
        	}
         } 
      }	
    
    class simulerTour extends TimerTask {
        public void run() {
          if (simulationEnCours && carteJeu.getEtatJeu() == 0){
        	  carteJeu.simulerIA();
              moteur();
          }
          else{
        	  simulationEnCours = false;
        	 simulation.cancel();
          	 timerFenetre.cancel();
          	 timerFenetre.purge();
          }
        }
      }
    	
        public void actionPerformed(ActionEvent arg0){
        	carteJeu.simulerIA();
        	moteur();
        }
    
    class revelerListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
          carteJeu.reveler();
          moteur();
        }
      }
    
    class recommencerListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        // on n'autorise à recommencer que si le jeu est fini
        if (carteJeu.getEtatJeu() != 0)	{
        	container.remove(vueCarte);
        	layout.removeLayoutComponent(vueCarte);
        	carteJeu = new Carte( ((Double)(getWidth()*pLargeurCarte)).intValue(), ((Double)(getHeight()*pHauteurCarte)).intValue(),ceContexte);
    	    carteJeu.creerVue();
    	    vueCarte = carteJeu.getVueCarte();
        	vueCarte.setPreferredSize(new Dimension(((Double)(getWidth()*pLargeurCarte)).intValue(), ((Double)(getHeight()*pHauteurCarte)).intValue() ) );
        	container.add(vueCarte, layout.CENTER);
        	carteJeu.creerIA(); // creer l'ia du jeu
        	vueCarte.setVisible(true);
        	vueCarte.setFocusable(true);
        	
        	vueCarte.repaint();
        	container.repaint();
        	setContentPane(container);
        	//this.this.setContentPane(container);
        	simulationEnCours = false;
          	timerFenetre=new Timer();
        }
        moteur();
        }
      }
   
}





