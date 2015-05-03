package ia;

import agent.Action;

public class IaUtil {
	
	// fonction qui calcule la distance entre deux positions de cases ( en nombre de mouvement pour y aller ) 
		static int distance(Integer[] positionA,Integer[] positionB){
			int distance=0;	
			distance += ((positionA[0]-positionB[0])>=0)?(positionA[0]-positionB[0]):(positionB[0]-positionA[0]);
			distance += ((positionA[1]-positionB[1])>=0)?(positionA[1]-positionB[1]):(positionB[1]-positionA[1]);
			
			return distance;	
		}
		// fonction qui determine si deux positions sont égales
		static boolean positionsEgales(Integer[] positionA,Integer[] positionB){
			boolean egalite=false;	
			
			egalite = positionA[0]==positionB[0] && positionA[1]==positionB[1];
			
			return egalite;	
		}
		
		// fonction determinant la position de la case atteinte par une Action depuis une case initiale
		static Integer[] fonctionTransition(Integer[] positionInitiale,Action cetteAction,Integer[] nbCases){
			Integer[] positionFinale = {-1,-1};
			
			if (cetteAction==Action.Abandonner){
				positionFinale = positionInitiale;
			}
			else if (cetteAction==Action.AllerEnHaut){
				positionFinale[1] = positionInitiale[1];
				positionFinale[0] = (positionInitiale[0]>0)?positionInitiale[0]-1:positionInitiale[0];
			}
			else if (cetteAction==Action.AllerADroite){
				positionFinale[0] = positionInitiale[0];
				positionFinale[1] = (positionInitiale[1]<nbCases[1]-1)?positionInitiale[1]+1:positionInitiale[1];
			}
			else if (cetteAction==Action.AllerEnBas){
				positionFinale[1] = positionInitiale[1];
				positionFinale[0] = (positionInitiale[0]<nbCases[0]-1)?positionInitiale[0]+1:positionInitiale[0];
			}
			else if (cetteAction==Action.AllerAGauche){
				positionFinale[0] = positionInitiale[0];
				positionFinale[1] = (positionInitiale[1]>0)?positionInitiale[1]-1:positionInitiale[1];
			}	
			return positionFinale;
		}
		// fonction determinant l'Action conduisant d'une case A vers une case B : il s'agit de l'inverse de la fonction de transition ci dessus
		// si il n'y a pas de coup unitaire entre ces deux cases la fonction renvoie abandonner
		
		static Action fonctionInversionPosition(Integer[] positionA,Integer[] positionB){
			Action cetteAction = Action.Abandonner;
			
			if (distance(positionA,positionB)==1){
				if (positionA[0]-1==positionB[0]){
					cetteAction = Action.AllerEnHaut;
				}
				else if (positionA[1]+1==positionB[1]){
					cetteAction = Action.AllerADroite;
				}
				if (positionA[0]+1==positionB[0]){
					cetteAction = Action.AllerEnBas;
				}
				else if (positionA[1]-1==positionB[1]){
					cetteAction = Action.AllerAGauche;
				}
			}
			
			return cetteAction;
		}

}
