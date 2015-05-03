package agent;

import ia.Ia;
import ia.IaKB1;
import ia.IaKB2;
import ia.IaKB3;
import ia.IaPerformance1;
import ia.IaPerformance2;
import ia.IaPerformance3;
import ia.IaSimple1;
import ia.IaSimple2;
import ia.IaSimple3;
import carte.Direction;


public class AgentAventurier extends Agent {
	private Ia IAAgent; // l'ia du personnage
	
	public AgentAventurier(int[] nbCases,int nbMaxFleches)
	{
		super(nbCases,nbMaxFleches);
		super.orientation = Direction.Droite;
		super.type = "AVENTURIER";
	}
	
	public AgentAventurier(int[] nbCases,String type,int nbMaxFleches)
	{
		super(nbCases,nbMaxFleches);
		super.orientation = Direction.Droite;
		super.type = type;
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
	
	public String parler(){
		return IAAgent.parler();
	}
	
}

