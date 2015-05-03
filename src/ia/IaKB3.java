package ia;
import carte.Case.ObservationCase;
import agent.Action;

// definition de notre Ia implementant l'interface generique IA
public class IaKB3 implements Ia {


	
	public IaKB3 (int[] nbCases){
		// TODO
	}
	
	// fonction d'obersvation
	public void observer(Integer[] positionActuelle,ObservationCase observationCaseActuelle){
		// TODO	
	}
	
	// fonction de décision de l'Action suivante
	public Action deciderActionSuivante(){
		// Par défaut l'ia abandonne
		Action ActionSuivante = Action.Abandonner;
		return ActionSuivante;
		
		// TODO
	}
	
	// fonction qui determine les messages du personnage 
		public String parler(){
			
			String reponse ="a definir ...";
			return reponse;
			
			// TODO
		}
	
}
