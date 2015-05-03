package agent;

import ia.*;

import carte.Case.ObservationCase;
import carte.Direction;
import agent.Action;

// le personnage du jeu
public class Agent{
	ObservationCase observationCaseActuelle; // case que le personnage observe a ce tour ci
	Integer[] positionActuelle = new Integer[2];
	Direction orientation;
	int nbFleches;
	int nbMaxFleches;
	boolean bloque;
	private int etat; // 0 vivant en recherche, 1 mort,2 abandon,3 gagné
	public int[] nbCases;
	private Ia IAAgent; // l'ia du personnage
	String type;
	Action derniereAction;
	
	public Agent(int[] nbCases,int nbMaxFleches){
		bloque = false;
		etat = 1;
		this.nbCases = nbCases;
		this.nbMaxFleches=nbMaxFleches;
		this.nbFleches=nbMaxFleches;
	}
	
	public int getNbFleches(){
		return nbFleches;
	}
	
	public int getNbMaxFleches(){
		return nbMaxFleches;
	}
	
	public void setDerniereAction(Action action){
		derniereAction = action;
	}
	
	// a appeler lors d'un premier placement
	public int setPositionCase(Integer[] casePlacement){
		positionActuelle = casePlacement;
		return 1;
	}
	public int setPositionCase(int i,int j){
		positionActuelle[0] = (Integer)i;
		positionActuelle[1] = (Integer)j;
		return 1;
	}
	
	public Integer[] getPositionCase(){
		return positionActuelle;
	}
	public int setObservationCase(ObservationCase casePlacement){
		observationCaseActuelle = casePlacement;
		return 1;
	}
	public ObservationCase getObservationCase(){
		return observationCaseActuelle;
	}
	
	
	public String getType(){
		return type;
	}
	
	public int geti(){
		int i=0;
		i = positionActuelle[0];
		return i;
	}
	
	public int getj(){
		int j=0;
		j = positionActuelle[1];
		return j;
	}
	
	public Direction getOrientation(){
		return orientation;
	}
	public void tirerFleche(){
		if (nbFleches > 0){
			nbFleches--;
		}
	}
	public int getnbFleches(){
		return this.nbFleches;
	}
	
	public void initialiserIA(String IaActive){
		if (IAAgent==null){
			switch(IaActive){
			case "IaSimple1" :
				IAAgent = new IaSimple1(nbCases);
				observer();
			break;
			case "IaSimple2" :
				IAAgent = new IaSimple2(nbCases);
				observer();
			break;
			case "IaSimple3" :
				IAAgent = new IaSimple3(nbCases);
				observer();
			break;
			case "IaKB1" :
				IAAgent = new IaKB1(nbCases);
				observer();
			break;
			case "IaKB2" :
				IAAgent = new IaKB2(nbCases);
				observer();
			break;
			case "IaKB3" :
				IAAgent = new IaKB3(nbCases);
				observer();
			break;
			case "IaPerformance1" :
				IAAgent = new IaPerformance1(nbCases);
				observer();
			break;
			case "IaPerformance2" :
				IAAgent = new IaPerformance2(nbCases);
				observer();
			break;
			case "IaPerformance3" :
				IAAgent = new IaPerformance3(nbCases);
				observer();
			break;
			default : 
				throw (new java.lang.NullPointerException("l ia " + IaActive +" n existe pas dans le projet"));		
			
			
			}
		}
	}
	
	// methode permettant de transmettre les informations de la case actuelle à la KB
	public void observer(){
		IAAgent.observer(positionActuelle,observationCaseActuelle);	
	}
	
	public Action deciderActionSuivante(){
		Action monAction;	
		monAction = IAAgent.deciderActionSuivante();
		return monAction;
	}
	
	public void setOrientation(Direction orientation){
		this.orientation=orientation;
	}
	public void setBloque(boolean bloque){
		this.bloque=bloque;
	}
	
	public void setEtat(int etat){
		this.etat=etat;
	}
	
	public int getEtat(){
		return this.etat;
	}
	

}
