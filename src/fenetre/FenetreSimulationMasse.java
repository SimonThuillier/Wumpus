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

public class FenetreSimulationMasse extends JFrame {
	
	private Contexte ceContexte;
	
	protected double pLargeurPanneauDroite = 0.25;
	protected double pHauteurPanneauHaut = 0.15;
	
	protected double pLargeurPanneauCentre = 0.7;
	protected double pHauteurPanneauCentre = 0.65;
	
	protected double pMargeX = 0.025;
	protected double pMargeY = 0.025;
	
	protected double pHauteurTexte1 = 0.04;
	protected double pLargeurTexte1 = 0.4;
	 
	protected double pHauteurTexte2 = 0.04;
	protected double pLargeurTexte2 = 0.15;
	
	protected double pLargeurTexte3 = 0.6;
	protected double pHauteurTexte3 = 0.04;
	
	protected double pHauteurBouton1 = 0.05;
	protected double pLargeurBouton1 = 0.25;
	
	protected double pHauteurPanneauBas = 0.0;
	
	
	private JPanel container = new JPanel();
	private Carte carteJeu;
	private  JPanel[] tab_jpanel = new JPanel[4];
	private  JButton[] tab_jbutton = new JButton[2];
	private JTextField[] tab_jtextfield = new JTextField[7];
	
	private BorderLayout layout = new BorderLayout();
	private String nomFenetre = "Simulation en masse";
	
	//protected jeuListener jeuListener = new jeuListener();
	private boolean simulationEnCours = false;
	private Timer timerFenetre;
	private TimerTask simulation;
	long delaiTour;
	
	public FenetreSimulationMasse(Contexte contexte){
		
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
	    
	    
	    
	    // creation du paneau haut
	    tab_jpanel[0] = new JPanel();
	    tab_jpanel[0].setPreferredSize(new Dimension(super.getWidth()-layout.getHgap(),((Double)(super.getHeight()*pHauteurPanneauHaut)).intValue() ) );
	    tab_jtextfield[0] = new JTextField();
	    tab_jtextfield[0].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[0].setEditable(false);
	    tab_jtextfield[0].setText("Nombre de tours de simulation : ");
	    
	    tab_jtextfield[1] = new JTextField();
	    tab_jtextfield[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue()));
	    tab_jtextfield[1].setEditable(true);
	    tab_jtextfield[1].setText(((Integer)contexte.NB_JEU_SIMULATION).toString());
	    
	    
	    tab_jpanel[0].add(tab_jtextfield[0]);
	    tab_jpanel[0].add(tab_jtextfield[1]);

	    // creation du panneau droit avec les differents boutons de controle
	    tab_jpanel[1] = new JPanel();
	    tab_jpanel[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurPanneauDroite)).intValue(), ((Double)(super.getHeight()*pHauteurPanneauCentre)).intValue() ) );
	    
	    tab_jbutton[0] = new JButton();
	    tab_jbutton[0].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[0].setText("Lancer simulation");
	    tab_jbutton[0].addActionListener(new lancerSimulation());
	    
	    tab_jbutton[1] = new JButton();
	    tab_jbutton[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[1].setText("Retour Menu principal");
	    tab_jbutton[1].addActionListener(new RetourMenuPrincipal());
	    
	    tab_jpanel[1].add(tab_jbutton[0]);
	    tab_jpanel[1].add(tab_jbutton[1]);
	    
	    // creation du panneau centre (message sur la simulation en cours)
	    tab_jpanel[3] = new JPanel();
	    tab_jpanel[3].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurPanneauCentre)).intValue(), ((Double)(super.getHeight()*pHauteurPanneauCentre)).intValue() ) );
	    tab_jtextfield[5] = new JTextField();
	    tab_jtextfield[5].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[5].setEditable(false);
	    tab_jtextfield[5].setText("En attente");
	    
	    tab_jtextfield[6] = new JTextField();
	    tab_jtextfield[6].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[6].setEditable(false);
	    tab_jtextfield[6].setText("/");
	    
	    tab_jpanel[3].add(tab_jtextfield[5]);
	    tab_jpanel[3].add(tab_jtextfield[6]);
	    
	    // creation du panneau bas (bordure basse)
	    tab_jpanel[2] = new JPanel();
	    tab_jpanel[2].setPreferredSize(new Dimension(super.getWidth(),((Double)(super.getHeight()*pHauteurPanneauBas)).intValue() ) );
	    
	    
	    container.add(tab_jpanel[1], layout.EAST);
	    container.add(tab_jpanel[3], layout.CENTER);
	    container.add(tab_jpanel[0], layout.NORTH);
	    container.add(tab_jpanel[2], layout.SOUTH);

	    

		super.setVisible(true);
		super.setFocusable(true);
		
		
		super.repaint();

		moteur();
		
		timerFenetre = new Timer();
	}
	
	private Integer controleNbTourSimulation(){
		Integer test;
		String entree;
		
		entree = (tab_jtextfield[1]).getText();
		try {test = Integer.parseInt(entree);}
		catch (NumberFormatException e){
			test = -1;
		}
		
		return test;
		
	}
	


	// 0 en cours, 1 : terminé par victoire, 2 terminé par mort, 3 terminé par abandon
    
    public void moteur(){
    	
    	//tab_jtextfield[0].setText(carteJeu.getMessageAventurier());

    	// a chaque fois qu'on appelle le moteur on retourne le focus sur la fenetre
    	super.requestFocusInWindow();
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
        	if (! simulationEnCours){
        		Integer nbSimulation = controleNbTourSimulation();
        		if (nbSimulation<=0){
        			tab_jtextfield[5].setText("Nombre de tours de simulation invalide : changez ce paramètre");
        		}
        		else{
        			ceContexte.NB_JEU_SIMULATION = nbSimulation;
        			ceContexte.EcrireFichierIni();	
        			tab_jtextfield[5].setText("Simulation en cours ...");
        			tab_jtextfield[5].repaint();
        			
        			int nbJeuxSimule = 0;
        			
        			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            		Date date = new Date();
            		String nomFichier = "SimulationMasse_" + dateFormat.format(date); 
            		System.out.println(nomFichier);
                  	FicStatistiqueHandler writerFichier = new FicStatistiqueHandler(nomFichier);
                  	simulationEnCours=true;
        			while(simulationEnCours && nbJeuxSimule < nbSimulation){
        				tab_jtextfield[6].setText((nbJeuxSimule+1) + "/" + nbSimulation);
        				tab_jtextfield[6].repaint();
        				moteur();
        				 carteJeu = new Carte(1,1,ceContexte);
        				 carteJeu.creerIA(); // creer l'ia du jeu
        				while(carteJeu.getEtatJeu()==0){
        					carteJeu.simulerIA();
        				}
       				 	if (nbJeuxSimule==0){
       				 		writerFichier.ecrireFichier(carteJeu.getEnteteStatistiqueCarte() + "\n");
       				 	} 
        				nbJeuxSimule++;
        				writerFichier.ecrireFichier(carteJeu.getStatistiqueCarte() + "\n");
        			}
        			ceContexte.EcrireFichierIni();
        			writerFichier.fermerFichier();
        			simulationEnCours = false;
        			tab_jtextfield[5].setText("En attente ...");
        		}
        	}
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





