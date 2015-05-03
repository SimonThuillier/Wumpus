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
public class IaPerformance3 implements Ia {
	String[][] discussion; 
	private int categorieReponse;
	private KnowledgeBase KB;
	private Integer[] nbCases;
	private Integer[] positionActuelle;
	private Integer[] positionPrecedente;
	private ObservationCase observationCaseActuelle;
	Random randomGenerator = new Random();
	List <Integer[]> chemin; // chemin : si un chemin a été calculé, l'environnement étant statique alors il est valable jusqu'a être entierement executé
	private Action derniereAction;
	
	// on declare l'ia à partir du monde ou elle devra opérer
	public IaPerformance3 (int[] nbCases){
		// on connait les dimensions de la carte
		
		this.nbCases= new Integer[2];
		this.positionActuelle = new Integer[2];
		positionPrecedente = new Integer[2];
		positionPrecedente[0] = -1;
		
		this.nbCases[0] = (Integer)nbCases[0];
		this.nbCases[1] = (Integer)nbCases[1];
		KB = new KnowledgeBase(this.nbCases);
		
		definirDiscussion();
		chemin=null;
		
		
	}
	
	// on observe la case actuelle, on memorise et on deduit des informations sur les cases suivantes
	public void observer(Integer[] positionActuelle,ObservationCase observationCaseActuelle){
		this.positionPrecedente = this.positionActuelle;
		this.positionActuelle=positionActuelle.clone();
		
		Integer[] positionObservation = new Integer[2];
		positionObservation[0] = (Integer)observationCaseActuelle.geti();
		positionObservation[1] = (Integer)observationCaseActuelle.getj();	
		this.observationCaseActuelle = observationCaseActuelle;
		
		KB.tell(positionObservation, veriteCase.Visite);
		
		categorieReponse = 6;
		
		if (IaUtil.positionsEgales(positionActuelle, positionPrecedente) && 
				(derniereAction ==Action.AllerEnHaut ||(derniereAction ==Action.AllerADroite)|| (derniereAction ==Action.AllerEnBas) || (derniereAction ==Action.AllerAGauche))){
			categorieReponse = 1;
		}
		if(observationCaseActuelle.getaLoot()){
			KB.tell(positionObservation, veriteCase.Tresor);
			categorieReponse = 0;	
		}
		if(observationCaseActuelle.getaWumpus() && ! observationCaseActuelle.getaWumpusMort()){
			KB.tell(positionObservation, veriteCase.Wumpus);
			categorieReponse = 2;
		}
		if(observationCaseActuelle.getaPuit()){
			KB.tell(positionObservation, veriteCase.Puit);
			categorieReponse = 3;
		}
		if(observationCaseActuelle.getsentMauvais()){
			KB.tell(positionObservation, veriteCase.Odeur);
			categorieReponse = 4;
		}
		if(observationCaseActuelle.getaBrise()){
			KB.tell(positionObservation, veriteCase.Brise);
			categorieReponse = 5;
		}
		// on infere des résultat dérivés des résultats acquis
		KB.inference(positionObservation);
		
		
	}
	
	// fonction de décision de l'Action suivante
	public Action deciderActionSuivante(){
		// on va en haut...
		Set <Action> ActionsOK= new TreeSet();
		Set <Action> ActionsRisquees=new TreeSet();
		Set <Action> ActionsInteressantes=new TreeSet();
		Action ActionSuivante = Action.Abandonner;
		int i = 0;
		int evaluationCase;
		Action ActionCorrespondante;
		Integer[] positionVoisine = {0,0};
		
		if(this.observationCaseActuelle.getaLoot()){
			derniereAction = Action.Ramasser;
			return Action.Ramasser;
		}
		
		ActionCorrespondante=Action.AllerEnHaut;
		derniereAction = Action.AllerEnHaut;
		
		if (chemin!=null && ! IaUtil.positionsEgales((Integer [])chemin.get(0),this.positionActuelle)){
			chemin = null;
		}
		
		if (chemin!=null){
			positionVoisine = (Integer [])chemin.get(1);
			ActionSuivante = IaUtil.fonctionInversionPosition(positionActuelle,positionVoisine);
			
			if (chemin.size()<3){
				// le chemin a été parcouru -> on l'annule
				chemin = null;
			}
			else{ // la pile d'execution du chemin diminue alors
				chemin.remove(0);
			}
			
		}
		
		for (i=0;i<4;i++){
			positionVoisine = KB.positionVoisine(positionActuelle,i);
			
			switch(i){
			case 0: ActionCorrespondante = Action.AllerEnHaut;
			break;
			case 1: ActionCorrespondante = Action.AllerADroite;
			break;
			case 2: ActionCorrespondante = Action.AllerEnBas;
			break;
			case 3: ActionCorrespondante = Action.AllerAGauche;
			break;
			}

			if (positionVoisine[0]!=-1){
				evaluationCase = evaluerCase(positionVoisine);
				System.out.println("case : " + i + " - evaluation : " + evaluationCase);
				// 0 : case connue : Action possible non interessante
				if (evaluationCase == 0){
					ActionsOK.add(ActionCorrespondante);	
				}
				// case sans risque et inconnue
				else if (evaluationCase == 1){
					ActionsOK.add(ActionCorrespondante);
					ActionsInteressantes.add(ActionCorrespondante);
					System.out.println("case : " + i + " - Action interessante : " + ActionCorrespondante);
				}
				else if (evaluationCase == 2){
					ActionsRisquees.add(ActionCorrespondante);
					System.out.println("case : " + i + " - Action risquée : " + ActionCorrespondante);
				}
			}
		}
		
		// on a determiné l'ensemble des choix possible 
		// maintenand on applique l'arbre de decision avec un peu de random ! 
		
		int randomInt;
		Object[] bloup;
		
		if (ActionsInteressantes.size()>0){
			randomInt = randomGenerator.nextInt(ActionsInteressantes.size());
			bloup= (Object[])(ActionsInteressantes.toArray());
			System.out.println("choix Action ok : " + randomInt + " - taille du choix : " + ActionsInteressantes.size());
			ActionSuivante= (Action)bloup[randomInt];
			
			// une Action immediatement interessante étant selectionnée on invalide le chemin actuel
			this.chemin=null;
		}
		else {
			// si ça marche ça garantit qu'un chemin sur vers une case inexplorée est trouvé
			if (this.explorerChemin1(20)){
				positionVoisine = (Integer [])chemin.get(1);
				ActionSuivante = IaUtil.fonctionInversionPosition(positionActuelle,positionVoisine);
				
				if (chemin.size()<3){
					// le chemin a été parcouru -> on l'annule
					chemin = null;
				}
				else{ // la pile d'execution du chemin diminue alors
					chemin.remove(0);
				}
			}
			else if (ActionsRisquees.size()>0){
				randomInt = randomGenerator.nextInt(ActionsRisquees.size());
				bloup= (Object[])(ActionsRisquees.toArray());
				System.out.println("choix Action risquée : " + randomInt + " - taille du choix : " + ActionsRisquees.size());
				ActionSuivante= (Action)bloup[randomInt];
			}
			else {
				ActionSuivante = Action.Abandonner;
			}
			
		}

		
		
		// sinon on abandonne pour l'instant
		
		derniereAction = ActionSuivante;;
		return ActionSuivante;
	}
	
	/* cette fonction interroge la KB et evalue si une case est
	-1 : indeterminable 
	0 : deja explorée
	1 : sans risque
	2 : risquéee
	3 : mortelle
	*/
	public int evaluerCase(Integer[] positionCase) {
		int eval = -1;
		
		if(KB.ask(positionCase, veriteCase.Puit) || KB.ask(positionCase, veriteCase.Wumpus)){
			eval = 3;
		}
		else if (KB.ask(positionCase, veriteCase.Puit_proba) || KB.ask(positionCase, veriteCase.Wumpus_proba)) {
			eval = 2;
		}
		else if (KB.ask(positionCase, veriteCase.Visite)){
			eval = 0;
		}
		else if(KB.ask(positionCase, veriteCase.Puit_nonproba) && KB.ask(positionCase, veriteCase.Wumpus_nonproba)){
			eval = 1;
		}
		
		
		return eval;
	}
	
	/*
 	algorithme d'exploration du chemin interessant
	 0 aucun chemin n'a été trouvé
	 1 un chemin a été trouvé et est stocké dans l'attribut chemin de la classe
	 Algorithme glouton optimisé
	 * */
	public boolean explorerChemin1(int horizonMax){
		
		System.out.println("exploration commencée");
		
		boolean trouve=false;
		Vector <LinkedList> chemins=new Vector(12);
		
		// initialisation du premier chemin
		LinkedList cheminInitial = new <Integer []> LinkedList(); // 
		cheminInitial.add(positionActuelle);
		chemins.add(cheminInitial);
		
		Integer[] positionInitiale;
		Integer[] cettePosition;
		
		int compteur = 0;
		int tailleMin = 2;
		
		// pour l'elagage2
		int iPosition;
		int jPosition;
		boolean doublon;
		
		// Action testée
		Action ActionCorrespondante;
		
		Set ActionsPossibles = new <Action> TreeSet();
		ActionsPossibles.add(Action.AllerEnHaut);
		ActionsPossibles.add(Action.AllerADroite);
		ActionsPossibles.add(Action.AllerEnBas);
		ActionsPossibles.add(Action.AllerAGauche);
		Iterator iterAction = ActionsPossibles.iterator();
		Iterator <LinkedList> iterChemins = chemins.iterator();
		Iterator <Integer[]> iterCeChemin;
		Iterator <LinkedList> iterCesChemins;
		
		// on alloue le vecteur produit par l'iteration
		Vector <LinkedList> cheminsProduit= new <LinkedList> Vector(12);
		
		// on alloue le cheminInitial pour la recherche
		LinkedList cheminRecherche;
		
		// on declare un chemin produit
		LinkedList cheminProduit;
		
		while (!trouve && compteur<horizonMax){
			System.out.println("iteration : " + compteur);
			
			
			// pour le stockage des nouveaux chemins
			cheminsProduit= new Vector(12);
			
			// on stocke les nouveaux chemins elligible
			while(iterChemins.hasNext()){
				cheminRecherche= ((LinkedList)((LinkedList)iterChemins.next()).clone());
				
				System.out.println("taille du chemin de recherche :" + cheminRecherche.size());
				
				positionInitiale = (Integer [])cheminRecherche.getLast();
				
				while (iterAction.hasNext()){
					ActionCorrespondante = (Action)iterAction.next();
					cettePosition = IaUtil.fonctionTransition(positionInitiale,ActionCorrespondante,nbCases);
					
					 System.out.println("position Initiale [i;j] : [" + positionInitiale[0] + ";" + positionInitiale[1] + "] - Action : " + ActionCorrespondante + " - position Finale [i;j] : [" + cettePosition[0] + ";" + cettePosition[1] + "]");
					// System.out.println("position i : " + cettePosition[0] + "position j : " + cettePosition[1] + " - evaluation : " + evaluerCase(cettePosition));
					
					// pour eviter les bouclage on ne selectionne que les chemins sans doublons de case
					 doublon = false;
					 // on commence par verifier que la transition n'était pas inutile (blocage sur un mur) 
					 if (IaUtil.positionsEgales(cettePosition,positionInitiale)){
						 doublon=true;
					 }
					 
					// on itere sur tout le chemin pour verifier si oui ou non il contient deja cette position
					 if (! doublon){
							iterCeChemin = cheminRecherche.iterator();
							while (! doublon && iterCeChemin.hasNext()){
								if(IaUtil.positionsEgales(cettePosition,(Integer [])iterCeChemin.next())){
									doublon=true;
								}
							}
					 }

					// pour eviter les doublons dans les chemins parcourus on ecarte les chemins en doublons vis à vis des autres (on ne teste que la derniere case)
					if (! doublon && cheminsProduit.size()>0){
						iterCesChemins = cheminsProduit.iterator();
						while (! doublon && iterCesChemins.hasNext()){
							if(IaUtil.positionsEgales(cettePosition,(Integer [])((LinkedList)iterCesChemins.next()).getLast())){
								doublon=true;
							}
						}
					}	
					
					
					
							
					
					// System.out.println("doublons ? : " + !cheminRecherche.contains(cettePosition));
					if (!doublon){
						if (evaluerCase(cettePosition)==0 ){
							 System.out.println("position ajoutée");
							cheminProduit = (LinkedList)cheminRecherche.clone();
							cheminProduit.add(cettePosition);
							
							cettePosition = (Integer [])cheminProduit.getLast();
							// System.out.println("position ajoutée : " + cettePosition[0] + "position j : " + cettePosition[1]);
							
							// stockage du nouveau chemin
							cheminsProduit.add(cheminProduit);
						}
						else if (evaluerCase(cettePosition)==1){
							System.out.println("position ajoutée");
							cheminProduit = (LinkedList)cheminRecherche.clone();
							cheminProduit.add(cettePosition);
							// stockage du nouveau chemin
							cheminsProduit.add(cheminProduit);
							
							// on a trouve ! 
							trouve = true;
							// un peu au hasard on attribue ce chemin comme chemin calcule de l'ia
							this.chemin = cheminProduit;
						}
					}
				}
				iterAction = ActionsPossibles.iterator();			
				
			}
			
			// en preparation de la prochaine iteration :
			chemins = (Vector)cheminsProduit.clone();
			cheminsProduit = new <LinkedList> Vector(12);
			iterChemins = chemins.iterator();
			
			compteur++;
			tailleMin++;
		}
		
		
		System.out.println("chemin trouvé : " + trouve);
		
		return trouve;
	}
	
	
	public void definirDiscussion(){
		// 0 : le perso a trouve le loot
		// 1 : le perso s'est cogne
		// 2 : le perso arrive dans la case du wumpus
		// 3 : le perso arrive dans un puit
		// 4 : le perso se trouve sur une case qui sent mauvais
		// 5 : le perso a avance et se trouve sur une case de brise
		// 6 : le perso a avancer et est sur une case normale
		
		//j : indice de categorie
		// i : indice dans la categorie
		
		int i,j; 
		discussion = new String[10][7];
		
			for (i=0;i<10;i++){
				discussion[i][0] = "J'AI TROUVE L OR !!!!!! JE SUIS RICHE, RICHE !!!!";		
			}
			for (i=0;i<10;i++){
				discussion[i][1] = "AIEEE !!! Je crois que c'est un mur ! ";		
			}
			for (i=0;i<10;i++){
				discussion[i][2] = "GRRRR...AAAHHHHHH...SCROUCHHHHH...SLUUURRpppp";		
			}
			for (i=0;i<10;i++){
				discussion[i][3] = "Je tommmmmbbbbbbbbbbbbbbeeeeeeeeeeeeeeeee";		
			}
			for (i=0;i<10;i++){
				discussion[i][4] = "Ca ne sent pas la rose par ici ...";		
			}
			for (i=0;i<10;i++){
				discussion[i][5] = "Ca vente par ici ....";		
			}
			for (i=0;i<10;i++){
				discussion[i][6] = "il fait beau non ?";		
			}	
			
			// autres messages personnalisé
			discussion[1][6] = "C'est tout noir ici ...";
			discussion[2][6] = "Quand j'aurai mon niveau 2 j'achéterai un duvet...";
			discussion[3][6] = "pour dormir dans les donjons sans jamais m'enrhumer !";
			discussion[4][6] = "Urge to become rich rising";
			discussion[5][6] = "J'aurai voulu être un artiste ! ";
			discussion[6][6] = "Moi ça va, et vous ?";
			discussion[7][6] = "1 km a pied ça use, ça use, 1 km à pied ça use les souliers";
			discussion[8][6] = "Bloups... j'ai encore trop bu hier...";
			discussion[9][6] = "Hého ???!!!";
			
			discussion[1][1] = "J'aurai du prendre mes lunettes moi !";
			discussion[2][1] = "OUCHH, en plein dans le petit orteil";
			discussion[3][1] = "Des Murs ici ???! moi qui suis claustrophobe!!! Viiiite ma ventoline ...";
			discussion[4][1] = "pfff, ils auraient au moins pu mettre du papier peint.";
			discussion[5][1] = "BIIIMMMMM *?!!";
			discussion[6][1] = "La prochaine fois j'achete la carte à la taverne";
			discussion[7][1] = "Dans la vie il y a des cactus, dans les murs il y a des CACTUS !!!";
			discussion[8][1] = "AIE AIE AIE";
			discussion[8][1] = "OUILLE";
			discussion[9][1] = "AIE";
			
			discussion[1][4] = "AH Ah AH Ah STAYIN' ALIVE ! STAYIn ALIVE ! ";
			discussion[2][4] = "VOUS NE PASSEREZ PAS !!!";
			discussion[3][4] = "tiens un os ???";
			discussion[4][4] = "Mr Propre nettoie aussi les taches de sang des sols en pierre ! ";
			discussion[5][4] = "ça pue ... et dans tous les sens du terme";
			discussion[6][4] = "Comment ça danger de mort ?";
			discussion[7][4] = "Petit petit petit...";
			discussion[8][4] = "Je ferai mieux de ne pas trop m'attader dans le coin";
			discussion[9][4] = "Une bete rode par ici... je peux le dire j'ai été scout. ";
			
			discussion[1][5] = "Je savais que j'aurais du acheter un duvet";
			discussion[2][5] = "and the wind blows ...";
			discussion[3][5] = "J'aurai du prendre une écharpe et un bonnet ! ";
			discussion[4][5] = "Je vais attrapper froid ... maman va encore me gronder en rentrant ...";
			
	}
	
	// fonction qui simule des messages de perso 
		public String parler(){
			String reponse ="";
			
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(10);
					
			reponse = discussion[randomInt][categorieReponse];

			return reponse;
		}
	
}
