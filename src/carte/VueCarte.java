package carte;

import wumpus.Contexte;
import graphisme.Graphisme;
import graphisme.Graphisme.ElementGraphique;
import graphisme.GraphismeVanilla;

import javax.swing.JPanel;

import agent.Agent;
// import agent.AgentAventurier;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Image;

//classe gérant la vue de carte
public class VueCarte extends JPanel{
	public Contexte contexteCarte;
	private Carte cetteCarte;
	private long idVue;
	private Graphisme graphisme;
	private int epaisseurEntreCase;
	private int[] nbCases;
	private int[] resolution;
	private Case[][] cases;
	private int[] tailleCase;
	private int[][][] tableauPositionCase;
	private Agent agent;
	
	public VueCarte(Contexte ceContexte,Carte carte,long id,int nbPixelx,int nbPixely){
		super();
		cetteCarte = carte;
		idVue = id;
		contexteCarte = ceContexte;
		epaisseurEntreCase = contexteCarte.EPAISSEUR_FRONTIERE_CASE;
		// on determine les graphismes à utiliser
		switch(contexteCarte.TEMPLATE_GRAPHIQUE){
		case "VANILLA" : graphisme = new GraphismeVanilla();
		break;
		default : break;
		}
		cases = cetteCarte.getCases(idVue);
		nbCases = cetteCarte.getNbCases();
		agent = cetteCarte.getAgent(idVue);
		resolution=new int[2];
		resolution[0] = nbPixelx;
		resolution[1] = nbPixely;
		// on redefinit la resolution a resolution immediatement inferieure et permettant un affichage optimisé
		ajusterResolution();
		// calcul du tableau des positions
		tableauPositionCase = new int[nbCases[0]][nbCases[1]][2];
		calculTableauPositionCase();
		// taille des cases
		tailleCase=new int[2];
		tailleCase[0] = resolution[0]/nbCases[1] - epaisseurEntreCase;
		tailleCase[1] = resolution[1]/nbCases[0] - epaisseurEntreCase;
	}
	
	// prepare l'objet graphique representant la carte
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g; 
		boolean dessinInitialise = false;
		for (int i=0;i<nbCases[0];i++)
		{
			for (int j=0;j<nbCases[1];j++)
			{ 
				if ((!dessinInitialise) || cases[i][j].getaDessiner()){							
					if (cases[i][j].getRevelee()){
						if (cases[i][j].getaPuit()){
							g2d.setPaint((Paint)graphisme.elements.get(ElementGraphique.casePuit));
						}
						else if (cases[i][j].getaBrise() && cases[i][j].getsentMauvais()){
							g2d.setPaint((Paint)graphisme.elements.get(ElementGraphique.caseOdoranteBrise));
						}
						else if (cases[i][j].getaBrise()){
							g2d.setPaint((Paint)graphisme.elements.get(ElementGraphique.caseBrise));
						}
						else if (cases[i][j].getsentMauvais()){
							g2d.setPaint((Paint)graphisme.elements.get(ElementGraphique.caseOdorante));
						}
						else{
							g2d.setPaint((Paint)graphisme.elements.get(ElementGraphique.caseNormale));
						}
						g2d.fillRect((tableauPositionCase[i][j])[0],(tableauPositionCase[i][j])[1], tailleCase[0], tailleCase[1]);
						if (cases[i][j].getaLoot()){
							g.drawImage((Image)graphisme.elements.get(ElementGraphique.elementLingot), (tableauPositionCase[i][j])[0],(tableauPositionCase[i][j])[1],tailleCase[0], tailleCase[1], this);
						}
						else if (cases[i][j].getaWumpus()){
							if (! cases[i][j].getaWumpusMort()){
								g.drawImage((Image)graphisme.elements.get(ElementGraphique.agentWumpus), (tableauPositionCase[i][j])[0],(tableauPositionCase[i][j])[1],tailleCase[0], tailleCase[1], this);
							}
							else {
								g.drawImage((Image)graphisme.elements.get(ElementGraphique.agentWumpusMort), (tableauPositionCase[i][j])[0],(tableauPositionCase[i][j])[1],tailleCase[0], tailleCase[1], this);
							}
						}
						g.drawRect((tableauPositionCase[i][j])[0],(tableauPositionCase[i][j])[1], tailleCase[0], tailleCase[1]);	
					}
					else {
						g2d.setPaint((Paint)graphisme.elements.get(ElementGraphique.caseInexploree));
						g2d.fillRect((tableauPositionCase[i][j])[0],(tableauPositionCase[i][j])[1], tailleCase[0], tailleCase[1]);
					}
				}
			}		
		}
		g.drawImage(calculerImageAgent(), (tableauPositionCase[agent.geti()][agent.getj()])[0], (tableauPositionCase[agent.geti()][agent.getj()])[1],tailleCase[0], tailleCase[1], this);
	  }
	
	private int calculxCase(int j){
		int x = 0;
		x = (resolution[0]*j)/nbCases[1];
		return x;
	}
	
	private int calculyCase(int i){
		int y = 0;
		y = (resolution[1]*i)/nbCases[0];
		return y;
	}
	
	private int[] calculPositionCase(int i,int j){
		int[] position = new int[2];
		position[0]=calculxCase(j);
		position[1]=calculyCase(i);
		return position;
	}
	
	private void calculTableauPositionCase(){
		int i,j;
		for (i=0;i<nbCases[0];i++){
			for (j=0;j<nbCases[1];j++){
				tableauPositionCase[i][j][0] = calculxCase(j);
				tableauPositionCase[i][j][1] = calculyCase(i);
			}
		}
	}
	
	private void ajusterResolution(){
		resolution[0] = (resolution[0]/(nbCases[1]))*nbCases[1];
		resolution[1] = (resolution[1]/(nbCases[0]))*nbCases[0];	
	}
	
	private Image calculerImageAgent(){
		Image ImageAgent = null;
		Direction orientationAgent = agent.getOrientation();
		String typeAgent = agent.getType();
		
		switch(typeAgent){
		case "AVENTURIER":
			switch(orientationAgent){
			case Haut:
				ImageAgent=(Image)graphisme.elements.get(ElementGraphique.agentAventurierHaut);
				break;
			case Droite:
				ImageAgent=(Image)graphisme.elements.get(ElementGraphique.agentAventurierDroite);
				break;
			case Bas:
				ImageAgent=(Image)graphisme.elements.get(ElementGraphique.agentAventurierBas);
				break;
			case Gauche:
				ImageAgent=(Image)graphisme.elements.get(ElementGraphique.agentAventurierGauche);
				break;
				default:
				break;
			}
			break;
		case "MARIO":
			switch(orientationAgent){
			case Haut:
				ImageAgent=(Image)graphisme.elements.get(ElementGraphique.agentMarioHaut);
				break;
			case Droite:
				ImageAgent=(Image)graphisme.elements.get(ElementGraphique.agentMarioDroite);
				break;
			case Bas:
				ImageAgent=(Image)graphisme.elements.get(ElementGraphique.agentMarioBas);
				break;
			case Gauche:
				ImageAgent=(Image)graphisme.elements.get(ElementGraphique.agentMarioGauche);
				break;
				default:
				break;
			}
			break;	
		default:
			break;
		
		
		}
		return ImageAgent;
	}
	// redefinit et repaint la carte à sa nouvelle resolution
	public void redimensionner(int nbPixelx,int nbPixely){
		resolution[0] = nbPixelx;
		resolution[1] = nbPixely;
		ajusterResolution();
		calculTableauPositionCase();
		tailleCase[0] = resolution[0]/nbCases[1] - epaisseurEntreCase;
		tailleCase[1] = resolution[1]/nbCases[0] - epaisseurEntreCase;
		this.repaint();
	}
	
}
