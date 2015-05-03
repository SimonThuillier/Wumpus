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
public class IaSimple1 implements Ia {
	String[][] discussion; 
	private Integer[] nbCases;
	private Integer[] positionActuelle;
	private Integer[] positionPrecedente;
	private ObservationCase observationCaseActuelle;
	private Action derniereAction;
	
	// on declare l'ia à partir du monde ou elle devra opérer
	public IaSimple1 (int[] nbCases){
		// on connait les dimensions de la carte
		
		this.nbCases= new Integer[2];
		this.positionActuelle = new Integer[2];
		positionPrecedente = new Integer[2];
		positionPrecedente[0] = -1;
		
		this.nbCases[0] = (Integer)nbCases[0];
		this.nbCases[1] = (Integer)nbCases[1];
		derniereAction=Action.AllerEnHaut;
			
	}
	
	// une ia simple n'enregistre pas les informations de son environnement donc rien
	public void observer(Integer[] positionActuelle,ObservationCase observationCaseActuelle){
		this.observationCaseActuelle = observationCaseActuelle;
		this.positionActuelle = positionActuelle;
	}
	
	// fonction de décision de l'Action suivante
	public Action deciderActionSuivante(){
		Action action = derniereAction;
		positionActuelle[0]=observationCaseActuelle.geti();
		positionActuelle[1]=observationCaseActuelle.getj();
		if (observationCaseActuelle.getaLoot()){
			action = Action.Ramasser;
		}
		else if (positionPrecedente[0]==positionActuelle[0] && positionPrecedente[1]==positionActuelle[1]){
			if (derniereAction==Action.AllerEnHaut){
				action = Action.AllerADroite;
			}
			else if (derniereAction==Action.AllerADroite){
				action = Action.AllerAGauche;
			}
			else if (derniereAction==Action.AllerEnBas){
				action = Action.AllerEnHaut;
			}
			else if (derniereAction==Action.AllerAGauche){
				action = Action.AllerEnBas;
			}
		}
		// si pas bloqué
		else{
			if (derniereAction==Action.AllerEnHaut){
				action = Action.AllerEnHaut;
			}
			else if (derniereAction==Action.AllerADroite){
				action = Action.AllerEnBas;
			}
			if (derniereAction==Action.AllerEnBas){
				action = Action.AllerEnBas;
			}
			if (derniereAction==Action.AllerAGauche){
				action = Action.AllerAGauche;
			}
		}
		
		
		
		positionPrecedente[0] = positionActuelle[0];
		positionPrecedente[1] = positionActuelle[1];
		
		derniereAction = action;
		return action;
	}
	
	// fonction qui simule des messages de perso 
		public String parler(){
			String reponse ="Je suis un automate simple ... ne m'en demandez pas trop";

			return reponse;
		}
	
}
