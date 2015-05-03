package fenetre;

import wumpus.Contexte;

// import java.util.Double;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class FenetreContexte extends JFrame {
	
	private Contexte ceContexte;
	
	protected double pLargeurPanneauDroite = 0.25;
	protected double pHauteurPanneauHaut = 0.15;
	
	protected double pLargeurPanneauCentre = 0.7;
	protected double pHauteurPanneauCentre = 0.75;
	
	protected double pMargeX = 0.025;
	protected double pMargeY = 0.025;
	
	protected double pHauteurTexte1 = 0.04;
	protected double pLargeurTexte1 = 0.3;
	 
	protected double pHauteurTexte2 = 0.04;
	protected double pLargeurTexte2 = 0.35;
	
	protected double pLargeurTexte3 = 0.4;
	protected double pHauteurTexte3 = 0.04;
	
	protected double pHauteurBouton1 = 0.05;
	protected double pLargeurBouton1 = 0.25;
	
	protected double pHauteurPanneauBas = 0.0;
	
	
	private JPanel container = new JPanel();
	private  JPanel[] tab_jpanel = new JPanel[4];
	private  JButton[] tab_jbutton = new JButton[3];
	private JTextField[] tab_jtextfield = new JTextField[30];
	private JComboBox <String>[] tab_jcombobox = new JComboBox [5];
	
	private BorderLayout layout = new BorderLayout();
	private String nomFenetre = "Modification du contexte";
	
	
	public FenetreContexte(Contexte contexte){
		
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
	    tab_jtextfield[0].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[0].setEditable(false);
	    tab_jtextfield[0].setText("Message : ");
	    
	    tab_jtextfield[1] = new JTextField();
	    tab_jtextfield[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*0.9)).intValue(), ((Double)(super.getHeight()*pHauteurTexte3)).intValue()));
	    tab_jtextfield[1].setEditable(true);
	    tab_jtextfield[1].setText("");
	    
	    
	    tab_jpanel[0].add(tab_jtextfield[0]);
	    tab_jpanel[0].add(tab_jtextfield[1]);

	    // creation du panneau droit avec les differents boutons de controle
	    tab_jpanel[1] = new JPanel();
	    tab_jpanel[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurPanneauDroite)).intValue(), ((Double)(super.getHeight()*pHauteurPanneauCentre)).intValue() ) );
	    
	    tab_jbutton[0] = new JButton();
	    tab_jbutton[0].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[0].setText("Enregistrer");
	    tab_jbutton[0].addActionListener(new EnregistrerValeurs());
	    
	    tab_jbutton[1] = new JButton();
	    tab_jbutton[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[1].setText("Retour valeurs contexte");
	    tab_jbutton[1].addActionListener(new SynchroniserValeurs());
	    
	    tab_jbutton[2] = new JButton();
	    tab_jbutton[2].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[2].setText("Retour Menu principal");
	    tab_jbutton[2].addActionListener(new RetourMenuPrincipal());
	    
	    tab_jpanel[1].add(tab_jbutton[0]);
	    tab_jpanel[1].add(tab_jbutton[1]);
	    tab_jpanel[1].add(tab_jbutton[2]);
	    
	    // creation du panneau centre (message sur la simulation en cours)
	    tab_jpanel[3] = new JPanel();
	    tab_jpanel[3].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurPanneauCentre)).intValue(), ((Double)(super.getHeight()*pHauteurPanneauCentre)).intValue() ) );
	    tab_jtextfield[2] = new JTextField();
	    tab_jtextfield[2].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[2].setEditable(false);
	    tab_jtextfield[2].setText("Largeur Fenetre");
	    
	    tab_jtextfield[3] = new JTextField();
	    tab_jtextfield[3].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[3].setEditable(true);
	    tab_jtextfield[3].setText(((Integer)ceContexte.LARGEUR_FENETRE).toString());
	    
	    tab_jtextfield[4] = new JTextField();
	    tab_jtextfield[4].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[4].setEditable(false);
	    tab_jtextfield[4].setText("Hauteur Fenetre");
	    
	    tab_jtextfield[5] = new JTextField();
	    tab_jtextfield[5].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[5].setEditable(true);
	    tab_jtextfield[5].setText(((Integer)ceContexte.HAUTEUR_FENETRE).toString());
	    
	    tab_jtextfield[6] = new JTextField();
	    tab_jtextfield[6].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[6].setEditable(false);
	    tab_jtextfield[6].setText("Nb cases vertical");
	    
	    tab_jtextfield[7] = new JTextField();
	    tab_jtextfield[7].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[7].setEditable(true);
	    tab_jtextfield[7].setText(((Integer)ceContexte.NB_CASES_LIGNE).toString());
	    
	    tab_jtextfield[8] = new JTextField();
	    tab_jtextfield[8].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[8].setEditable(false);
	    tab_jtextfield[8].setText("Nb cases horizontal");
	    
	    tab_jtextfield[9] = new JTextField();
	    tab_jtextfield[9].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[9].setEditable(true);
	    tab_jtextfield[9].setText(((Integer)ceContexte.NB_CASES_COLONNE).toString());
	    
	    tab_jtextfield[10] = new JTextField();
	    tab_jtextfield[10].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[10].setEditable(false);
	    tab_jtextfield[10].setText("Probabilité de puit");
	    
	    tab_jtextfield[11] = new JTextField();
	    tab_jtextfield[11].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[11].setEditable(true);
	    tab_jtextfield[11].setText(((Double)ceContexte.PROBA_PUIT).toString());
	    
	    tab_jtextfield[12] = new JTextField();
	    tab_jtextfield[12].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[12].setEditable(false);
	    tab_jtextfield[12].setText("Type d'aventurier");
	    
	    /* tab_jtextfield[13] = new JTextField();
	    tab_jtextfield[13].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[13].setEditable(true);
	    tab_jtextfield[13].setText(ceContexte.TYPE_AVENTURIER); */
	    
	    tab_jcombobox[0] = new JComboBox <String>();
	    tab_jcombobox[0].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jcombobox[0].setEditable(false);
	    tab_jcombobox[0].setEnabled(true);
	    tab_jcombobox[0].addItem("AVENTURIER");
	    tab_jcombobox[0].addItem("MARIO");
	    tab_jcombobox[0].setSelectedItem(ceContexte.TYPE_AVENTURIER);
	    
	    tab_jtextfield[14] = new JTextField();
	    tab_jtextfield[14].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[14].setEditable(false);
	    tab_jtextfield[14].setText("Template graphique");
	    
	    tab_jcombobox[1] = new JComboBox <String>();
	    tab_jcombobox[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jcombobox[1].setEditable(false);
	    tab_jcombobox[1].setEnabled(true);
	    tab_jcombobox[1].addItem("VANILLA");
	    tab_jcombobox[1].setSelectedItem(ceContexte.TEMPLATE_GRAPHIQUE);
	    
	    /* tab_jtextfield[15] = new JTextField();
	    tab_jtextfield[15].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[15].setEditable(true);
	    tab_jtextfield[15].setText(ceContexte.TEMPLATE_GRAPHIQUE); */
	    
	    tab_jtextfield[16] = new JTextField();
	    tab_jtextfield[16].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[16].setEditable(false);
	    tab_jtextfield[16].setText("Epaisseur de frontiere entre cases");
	    
	    tab_jtextfield[17] = new JTextField();
	    tab_jtextfield[17].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[17].setEditable(true);
	    tab_jtextfield[17].setText(((Integer)ceContexte.EPAISSEUR_FRONTIERE_CASE).toString());
	    
	    tab_jtextfield[18] = new JTextField();
	    tab_jtextfield[18].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[18].setEditable(false);
	    tab_jtextfield[18].setText("Pause entre tours en ms (simulation graphique)");
	    
	    tab_jtextfield[19] = new JTextField();
	    tab_jtextfield[19].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[19].setEditable(true);
	    tab_jtextfield[19].setText(((Long)ceContexte.DELAI_TOUR_SIMULATION).toString());
	    
	    tab_jtextfield[20] = new JTextField();
	    tab_jtextfield[20].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[20].setEditable(false);
	    tab_jtextfield[20].setText("Nombre de parties simulées (simulation en masse)");
	    
	    tab_jtextfield[21] = new JTextField();
	    tab_jtextfield[21].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[21].setEditable(true);
	    tab_jtextfield[21].setText(((Integer)ceContexte.NB_JEU_SIMULATION).toString());
	    
	    tab_jtextfield[22] = new JTextField();
	    tab_jtextfield[22].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[22].setEditable(false);
	    tab_jtextfield[22].setText("Nombre de fleches");
	    
	    tab_jtextfield[23] = new JTextField();
	    tab_jtextfield[23].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[23].setEditable(true);
	    tab_jtextfield[23].setText(((Integer)ceContexte.NB_FLECHE).toString());
	    
	    tab_jtextfield[24] = new JTextField();
	    tab_jtextfield[24].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[24].setEditable(false);
	    tab_jtextfield[24].setText("IA active");
	    
	    tab_jcombobox[2] = new JComboBox <String>();
	    tab_jcombobox[2].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jcombobox[2].setEditable(false);
	    tab_jcombobox[2].setEnabled(true);
	    tab_jcombobox[2].addItem("IaSimple1");
	    tab_jcombobox[2].addItem("IaSimple2");
	    tab_jcombobox[2].addItem("IaSimple3");
	    tab_jcombobox[2].addItem("IaKB1");
	    tab_jcombobox[2].addItem("IaKB2");
	    tab_jcombobox[2].addItem("IaKB3");
	    tab_jcombobox[2].addItem("IaPerformance1");
	    tab_jcombobox[2].addItem("IaPerformance2");
	    tab_jcombobox[2].addItem("IaPerformance3");
	    tab_jcombobox[2].setSelectedItem(ceContexte.IA_ACTIVE);
	    
	    /* tab_jtextfield[25] = new JTextField();
	    tab_jtextfield[25].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[25].setEditable(true);
	    tab_jtextfield[25].setText(ceContexte.IA_ACTIVE); */
	    
	    tab_jtextfield[26] = new JTextField();
	    tab_jtextfield[26].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[26].setEditable(false);
	    tab_jtextfield[26].setText("Coefficient Nb Max tours");
	    
	    tab_jtextfield[27] = new JTextField();
	    tab_jtextfield[27].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[27].setEditable(true);
	    tab_jtextfield[27].setText(((Double)ceContexte.COEFF_PERFORMANCE_MIN).toString());
	    
	    tab_jtextfield[28] = new JTextField();
	    tab_jtextfield[28].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[28].setEditable(false);
	    tab_jtextfield[28].setText("Wumpus Active");
	    
	    tab_jcombobox[3] = new JComboBox <String>();
	    tab_jcombobox[3].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jcombobox[3].setEditable(false);
	    tab_jcombobox[3].setEnabled(true);
	    tab_jcombobox[3].addItem("OUI");
	    tab_jcombobox[3].addItem("NON");
	    tab_jcombobox[3].setSelectedItem(ceContexte.WUMPUS_ACTIVE);
	    
	    
	    tab_jpanel[3].add(tab_jtextfield[2]);
	    tab_jpanel[3].add(tab_jtextfield[3]);
	    tab_jpanel[3].add(tab_jtextfield[4]);
	    tab_jpanel[3].add(tab_jtextfield[5]);
	    tab_jpanel[3].add(tab_jtextfield[6]);
	    tab_jpanel[3].add(tab_jtextfield[7]);
	    tab_jpanel[3].add(tab_jtextfield[8]);
	    tab_jpanel[3].add(tab_jtextfield[9]);
	    tab_jpanel[3].add(tab_jtextfield[10]);
	    tab_jpanel[3].add(tab_jtextfield[11]);
	    tab_jpanel[3].add(tab_jtextfield[12]);
	    // tab_jpanel[3].add(tab_jtextfield[13]);
	    tab_jpanel[3].add(tab_jcombobox[0]);
	    tab_jpanel[3].add(tab_jtextfield[14]);
	    // tab_jpanel[3].add(tab_jtextfield[15]);
	    tab_jpanel[3].add(tab_jcombobox[1]);
	    tab_jpanel[3].add(tab_jtextfield[16]);
	    tab_jpanel[3].add(tab_jtextfield[17]);
	    tab_jpanel[3].add(tab_jtextfield[18]);
	    tab_jpanel[3].add(tab_jtextfield[19]);
	    tab_jpanel[3].add(tab_jtextfield[20]);
	    tab_jpanel[3].add(tab_jtextfield[21]);
	    tab_jpanel[3].add(tab_jtextfield[22]);
	    tab_jpanel[3].add(tab_jtextfield[23]);
	    tab_jpanel[3].add(tab_jtextfield[24]);
	    // tab_jpanel[3].add(tab_jtextfield[25]);
	    tab_jpanel[3].add(tab_jcombobox[2]);
	    tab_jpanel[3].add(tab_jtextfield[26]);
	    tab_jpanel[3].add(tab_jtextfield[27]);
	    tab_jpanel[3].add(tab_jtextfield[28]);
	    tab_jpanel[3].add(tab_jcombobox[3]);
	    
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
	}
	
    class EnregistrerValeurs implements ActionListener {
        public void actionPerformed(ActionEvent arg0){

        	Integer intTest = 0;
        	Double doubleTest;
        	Long longTest;
        	
        	String valeur;
        	String messageErr="";
        	int nbInvalide = 0;
        	
        	Border borderRouge = BorderFactory.createLineBorder(Color.red);
        	Border borderGris = BorderFactory.createLineBorder(Color.gray); 
        	
        	tab_jtextfield[3].setBorder(borderGris);
        	valeur = tab_jtextfield[3].getText();
        	try{
        		intTest = Integer.parseInt(valeur);
        		if(intTest <400 | intTest >1600){
        			messageErr = messageErr + "La largeur fenetre doit etre dans [400;1600]. ";
            		nbInvalide++;
            		tab_jtextfield[3].setBorder(borderRouge);
        		}
        		else {
        			ceContexte.LARGEUR_FENETRE = intTest;
        		}
        	}
        	catch (NumberFormatException e){
        		messageErr = messageErr + "La largeur fenetre n'est pas un nombre. ";
        		nbInvalide++;
        		tab_jtextfield[3].setBorder(borderRouge);
        	}
        	
        	tab_jtextfield[5].setBorder(borderGris);
        	valeur = tab_jtextfield[5].getText();
        	try{
        		intTest = Integer.parseInt(valeur);
        		if(intTest <400 | intTest >1600){
        			messageErr = messageErr + "La hauteur fenetre doit etre dans [400;1600]. ";
            		nbInvalide++;
            		tab_jtextfield[5].setBorder(borderRouge);
        		}
        		else {
        			ceContexte.HAUTEUR_FENETRE = intTest;
        		}
        	}
        	catch (NumberFormatException e){
        		messageErr = messageErr + "La hauteur fenetre n'est pas un nombre. ";
        		nbInvalide++;
        		tab_jtextfield[5].setBorder(borderRouge);
        	}
        	
        	tab_jtextfield[7].setBorder(borderGris);
        	valeur = tab_jtextfield[7].getText();
        	try{
        		intTest = Integer.parseInt(valeur);
        		if(intTest <2 | intTest >100){
        			messageErr = messageErr + "Le nombre de cases vertical doit etre dans [2;100]. ";
            		nbInvalide++;
            		tab_jtextfield[7].setBorder(borderRouge);
        		}
        		else {
        			ceContexte.NB_CASES_LIGNE = intTest;
        		}
        	}
        	catch (NumberFormatException e){
        		messageErr = messageErr + "Le nb de cases vertical n'est pas un nombre. ";
        		nbInvalide++;
        		tab_jtextfield[7].setBorder(borderRouge);
        	}
        	
        	tab_jtextfield[9].setBorder(borderGris);
        	valeur = tab_jtextfield[9].getText();
        	try{
        		intTest = Integer.parseInt(valeur);
        		if(intTest <2 | intTest >100){
        			messageErr = messageErr + "Le nombre de cases horizontal doit etre dans [2;100]. ";
            		nbInvalide++;
            		tab_jtextfield[9].setBorder(borderRouge);
        		}
        		else {
        			ceContexte.NB_CASES_COLONNE = intTest;
        		}
        	}
        	catch (NumberFormatException e){
        		messageErr = messageErr + "Le nb de cases horizontal n'est pas un nombre. ";
        		nbInvalide++;
        		tab_jtextfield[9].setBorder(borderRouge);
        	}
        	
        	tab_jtextfield[11].setBorder(borderGris);
        	valeur = tab_jtextfield[11].getText();
        	try{
        		doubleTest = Double.parseDouble(valeur);
        		if (doubleTest <0 | doubleTest>=1){
            		messageErr = messageErr + "La probabilité de puit doit etre dans [0;1[. ";
            		nbInvalide++;
            		tab_jtextfield[11].setBorder(borderRouge);
        		}
        		else{
        			ceContexte.PROBA_PUIT = doubleTest;
        		}
        	}
        	catch (NumberFormatException e){
        		messageErr = messageErr + "La probabilité de puit n'est pas un nombre. ";
        		nbInvalide++;
        		tab_jtextfield[11].setBorder(borderRouge);
        	}
        	
        	// tab_jtextfield[13].setBorder(borderGris);
        	// valeur = tab_jtextfield[13].getText();
        	tab_jcombobox[0].setBorder(borderGris);
        	valeur = (String)tab_jcombobox[0].getSelectedItem();
        	
        	if (valeur.compareTo("AVENTURIER")==0 | valeur.compareTo("MARIO")==0){
        		ceContexte.TYPE_AVENTURIER=valeur;
        	}
        	else{
        		messageErr = messageErr + "Seul les types de personnage AVENTURIER et MARIO sont accessibles pour le moment.";
        		nbInvalide++;
        		// tab_jtextfield[13].setBorder(borderRouge);
        		tab_jcombobox[0].setBorder(borderRouge);
        	}
        	
        	//tab_jtextfield[15].setBorder(borderGris);
        	// valeur = tab_jtextfield[15].getText();
        	tab_jcombobox[1].setBorder(borderGris);
        	valeur = (String)tab_jcombobox[1].getSelectedItem();

        	if (valeur.compareTo("VANILLA")==0){
        			ceContexte.TEMPLATE_GRAPHIQUE=valeur;
        	}
        	else{
        		messageErr = messageErr + "Seul le template VANILLA est autorisé pour le moment. ";
        		nbInvalide++;
        		// tab_jtextfield[15].setBorder(borderRouge);
        		tab_jcombobox[1].setBorder(borderRouge);
        	}
        	
        	tab_jtextfield[17].setBorder(borderGris);
        	valeur = tab_jtextfield[17].getText();
        	try{
        		intTest = Integer.parseInt(valeur);
        		ceContexte.EPAISSEUR_FRONTIERE_CASE = intTest;
        	}
        	catch (NumberFormatException e){
        		messageErr = messageErr + "L'epaisseur de frontiere n'est pas un nombre. ";
        		nbInvalide++;
        		tab_jtextfield[17].setBorder(borderRouge);
        	}
        	
        	tab_jtextfield[19].setBorder(borderGris);
        	valeur = tab_jtextfield[19].getText();
        	try{
        		longTest = Long.parseLong(valeur);
        		if (longTest <1 | longTest>10000){
            		messageErr = messageErr + "Le delai entre tours de simulation doit etre dans [1;10000]. ";
            		nbInvalide++;
            		tab_jtextfield[19].setBorder(borderRouge);
        		}
        		else{
        			ceContexte.DELAI_TOUR_SIMULATION = longTest;
        		}
        	}
        	catch (NumberFormatException e){
        		messageErr = messageErr + "Le delai entre tours de simulation n'est pas un nombre. ";
        		nbInvalide++;
        		tab_jtextfield[19].setBorder(borderRouge);
        	}
        	
        	tab_jtextfield[21].setBorder(borderGris);
        	valeur = tab_jtextfield[21].getText();
        	try{
        		intTest = Integer.parseInt(valeur);
        		if (intTest <1 | intTest>100000){
            		messageErr = messageErr + "Le nombre de jeux de simulation doit etre dans [1;100000]. ";
            		nbInvalide++;
            		tab_jtextfield[21].setBorder(borderRouge);
        		}
        		else{
        			ceContexte.NB_JEU_SIMULATION = intTest;
        		}
        	}
        	catch (NumberFormatException e){
        		messageErr = messageErr + "Le nombre de jeux de simulation n'est pas un nombre. ";
        		nbInvalide++;
        		tab_jtextfield[21].setBorder(borderRouge);
        	}
        	
        	tab_jtextfield[23].setBorder(borderGris);
        	valeur = tab_jtextfield[23].getText();
        	try{
        		intTest = Integer.parseInt(valeur);
        		if(intTest <0 | intTest >100){
        			messageErr = messageErr + "Le nombre de fleches doit être dans [0;100]. ";
            		nbInvalide++;
            		tab_jtextfield[23].setBorder(borderRouge);
        		}
        		else {
        			ceContexte.NB_FLECHE = intTest;
        		}
        	}
        	catch (NumberFormatException e){
        		messageErr = messageErr + "Le nb de fleches n'est pas un nombre. ";
        		nbInvalide++;
        		tab_jtextfield[23].setBorder(borderRouge);
        	}
        	
        	tab_jcombobox[2].setBorder(borderGris);
        	valeur = (String)tab_jcombobox[2].getSelectedItem();
        	if (true){
        		ceContexte.IA_ACTIVE=valeur;	
        	}
        	else{
        		messageErr = messageErr + "Les IAs autorisées sont : .";
        		nbInvalide++;
        		tab_jcombobox[2].setBorder(borderRouge);
        	}
        	
        	tab_jtextfield[27].setBorder(borderGris);
        	valeur = tab_jtextfield[27].getText();
        	try{
        		doubleTest = Double.parseDouble(valeur);
        		if (doubleTest <0.1 | doubleTest>10){
            		messageErr = messageErr + "Le coefficient Nb Max tours doit etre dans [0.1;10]. ";
            		nbInvalide++;
            		tab_jtextfield[27].setBorder(borderRouge);
        		}
        		else{
        			ceContexte.COEFF_PERFORMANCE_MIN = doubleTest;
        		}
        	}
        	catch (NumberFormatException e){
        		messageErr = messageErr + "Le coefficient de performance min n'est pas un nombre. ";
        		nbInvalide++;
        		tab_jtextfield[27].setBorder(borderRouge);
        	}
        	
        	tab_jcombobox[3].setBorder(borderGris);
        	valeur = (String)tab_jcombobox[3].getSelectedItem();
        	ceContexte.WUMPUS_ACTIVE=valeur;	

        	
        	if (nbInvalide==0){
        		tab_jtextfield[1].setText("");
        		ceContexte.EcrireFichierIni();
        	}   	
        	else{
        		tab_jtextfield[1].setText(messageErr);
        	}
        	
        }
      }
	
    class SynchroniserValeurs implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	
        	ceContexte.lireFichierIni();
        	
        	tab_jtextfield[3].setText(((Integer)ceContexte.LARGEUR_FENETRE).toString());
        	tab_jtextfield[5].setText(((Integer)ceContexte.HAUTEUR_FENETRE).toString());
        	tab_jtextfield[7].setText(((Integer)ceContexte.NB_CASES_LIGNE).toString());
        	tab_jtextfield[9].setText(((Integer)ceContexte.NB_CASES_COLONNE).toString());
        	tab_jtextfield[11].setText(((Double)ceContexte.PROBA_PUIT).toString());
        	// tab_jtextfield[13].setText(ceContexte.TYPE_AVENTURIER);
        	tab_jcombobox[0].setSelectedItem(ceContexte.TYPE_AVENTURIER);
        	// tab_jtextfield[15].setText(ceContexte.TEMPLATE_GRAPHIQUE);
        	tab_jcombobox[1].setSelectedItem(ceContexte.TEMPLATE_GRAPHIQUE);
        	tab_jtextfield[17].setText(((Integer)ceContexte.EPAISSEUR_FRONTIERE_CASE).toString());
        	tab_jtextfield[19].setText(((Long)ceContexte.DELAI_TOUR_SIMULATION).toString());
        	tab_jtextfield[21].setText(((Integer)ceContexte.NB_JEU_SIMULATION).toString());
        	tab_jtextfield[23].setText(((Integer)ceContexte.NB_FLECHE).toString());
        	//tab_jtextfield[25].setText(ceContexte.IA_ACTIVE);
        	tab_jcombobox[2].setSelectedItem(ceContexte.IA_ACTIVE);
        	tab_jtextfield[27].setText(((Double)ceContexte.COEFF_PERFORMANCE_MIN).toString());
        	tab_jcombobox[2].setSelectedItem(ceContexte.WUMPUS_ACTIVE);
        	
        	Border borderGris = BorderFactory.createLineBorder(Color.gray); 
        	tab_jtextfield[3].setBorder(borderGris);
        	tab_jtextfield[5].setBorder(borderGris);
        	tab_jtextfield[7].setBorder(borderGris);
        	tab_jtextfield[9].setBorder(borderGris);
        	tab_jtextfield[11].setBorder(borderGris);
        	// tab_jtextfield[13].setBorder(borderGris);
        	tab_jcombobox[0].setBorder(borderGris);
        	// tab_jtextfield[15].setBorder(borderGris);
        	tab_jcombobox[1].setBorder(borderGris);
        	tab_jtextfield[17].setBorder(borderGris);
        	tab_jtextfield[19].setBorder(borderGris);
        	tab_jtextfield[21].setBorder(borderGris);
        	tab_jtextfield[23].setBorder(borderGris);
        	// tab_jtextfield[25].setBorder(borderGris);
        	tab_jcombobox[2].setBorder(borderGris);
        	tab_jtextfield[27].setBorder(borderGris);
        	tab_jcombobox[3].setBorder(borderGris);
        	
        	tab_jtextfield[1].setText("");
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





