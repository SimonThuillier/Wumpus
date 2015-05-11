package ia;
import ia.IaUtil;
import ia.KnowledgeBase;
import ia.KnowledgeBase.veriteCase;

import java.util.Set;
import java.util.List;
import java.util.Collections;
import java.util.Vector;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Random;

import carte.Case.ObservationCase;
import agent.Action;

// premiere IA faites en se basant (de loin) sur le formalisme de la logique propositionnelle
public class IaSimple2 implements Ia {
	String[][] discussion; 
	private Integer[] nbCases;
	private Integer[] positionActuelle;
	private Integer[] positionPrecedente;
	private ObservationCase observationCaseActuelle;
	private int nbTours;
	private int nbMouvementsTour;
	
	// on declare l'ia à partir du monde ou elle devra opérer
	public IaSimple2 (int[] nbCases){
		// on connait les dimensions de la carte
		
		this.nbCases= new Integer[2];
		this.positionActuelle = new Integer[2];
		positionPrecedente = new Integer[2];
		positionPrecedente[0] = -1;
		
		this.nbCases[0] = (Integer)nbCases[0];
		this.nbCases[1] = (Integer)nbCases[1];
		nbTours = 0;
		nbMouvementsTour=0;
			
	}
	
	// une ia simple n'enregistre pas les informations de son environnement donc rien
	public void observer(Integer[] positionActuelle,ObservationCase observationCaseActuelle){
		this.observationCaseActuelle =observationCaseActuelle; 
	}
	
	// fonction de décision de l'Action suivante
	public Action deciderActionSuivante(){
		Action action =  Action.Abandonner;
		positionActuelle[0]=observationCaseActuelle.geti();
		positionActuelle[1]=observationCaseActuelle.getj();
		
		int CaseMinITour = nbTours;
		int CaseMinJTour = nbTours;
		
		int CaseMaxITour = nbCases[0] - nbTours -1;
		int CaseMaxJTour = nbCases[1] - nbTours -1;
		
		
		if (observationCaseActuelle.getaLoot()){
			action = Action.Ramasser;
		}
		else if (nbMouvementsTour == 2*(CaseMaxITour - CaseMinITour) + 2*(CaseMaxJTour - CaseMinJTour) ){
			// cas de transition en un coup ( transition pas dans un coin )
			if (positionActuelle[0]==CaseMinITour && positionActuelle[1]!=CaseMaxJTour){
				action = Action.AllerEnBas;
				nbMouvementsTour=1;
				nbTours++;
			}
			else if (positionActuelle[1]==CaseMaxJTour && positionActuelle[0]!=CaseMaxITour){
				action = Action.AllerAGauche;
				nbMouvementsTour=1;
				nbTours++;
			}
			else if (positionActuelle[0]==CaseMaxITour && positionActuelle[1]!=CaseMaxJTour){
				action = Action.AllerEnHaut;
				nbMouvementsTour=1;
				nbTours++;
			}
			else if (positionActuelle[1]==CaseMinJTour && positionActuelle[0]!=CaseMinITour){
				action = Action.AllerADroite;
				nbMouvementsTour=1;
				nbTours++;
			}
			// cas de transition complexe ( transition dans un coin en deux coup) : on avance d'un coup
			else if (positionActuelle[0]==CaseMinITour && positionActuelle[1]==CaseMinJTour){
				action = Action.AllerADroite;
			}
			else if (positionActuelle[0]==CaseMinITour && positionActuelle[1]==CaseMaxJTour){
				action = Action.AllerEnBas;
			}
			else if (positionActuelle[0]==CaseMaxITour && positionActuelle[1]==CaseMaxJTour){
				action = Action.AllerAGauche;
			}
			else if (positionActuelle[0]==CaseMaxITour && positionActuelle[1]==CaseMinJTour){
				action = Action.AllerEnHaut;
			}			
			
			System.out.println("transition : tour " + nbTours + " vers tour " + (nbTours+1) + " après " + nbMouvementsTour + " mouvements");
			
		}

		else if (positionActuelle[0]==CaseMinITour && positionActuelle[1]!=CaseMaxJTour){
				action = Action.AllerADroite;
				nbMouvementsTour++;
			}
		else if (positionActuelle[1]==CaseMaxJTour && positionActuelle[0]!=CaseMaxITour){
				action = Action.AllerEnBas;
				nbMouvementsTour++;
			}
		else if (positionActuelle[0]==CaseMaxITour && positionActuelle[1]!=CaseMinJTour){
				action = Action.AllerAGauche;
				nbMouvementsTour++;
			}
		else if (positionActuelle[1]==CaseMinJTour && positionActuelle[0]!=CaseMinITour){
				action = Action.AllerEnHaut;
				nbMouvementsTour++;
			}
		else{
			action = Action.Abandonner;
			}
		
		
		
		positionPrecedente = positionActuelle;
		return action;
	}
	
	// fonction qui simule des messages de perso 
		public String parler(){
			String reponse ="Je suis un automate simple ... ne m'en demandez pas trop";

			return reponse;
		}
	
}
