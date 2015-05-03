package carte;

public class StatistiqueCarte {

	public int nbCaseTotal = 0;
	public int nbCaseI = 0;
	public int nbCaseJ = 0;
	public double probaPuit = 0.0;
	public int nbPuit = 0;
	public int nbFlecheInitial = 0;
	public int scoreInitial = 0;
	public int etatJeuFinal = 0;
	public int nbTours = 0;
	public int nbMaxTours = 0;
	public int scoreFinal = 0;
	public int nbCaseVisitee = 0;
	public int wumpusMort = 0;
	public int nbMouvement= 0;
	public int nbMouvementBloque = 0;
	public int nbTir = 0;
	public int nbRamasse = 0;
	public int nbAbandon = 0;
	public double tempsMoyenObservationIA = 0;
	public double tempsMoyenDecisionIA = 0;
	
	public StatistiqueCarte(){
		
	}
	
	public String getLigneStat(){
		String ligne="";
		
		ligne = ligne + nbCaseTotal + ";";
		ligne = ligne + nbCaseI + ";";
		ligne = ligne + nbCaseJ + ";";
		ligne = ligne + probaPuit + ";";
		ligne = ligne + nbPuit + ";";
		ligne = ligne + nbFlecheInitial + ";";
		ligne = ligne + scoreInitial + ";";
		ligne = ligne + ((etatJeuFinal==3)?"GAGNE":(etatJeuFinal==2)?"ABANDON":"PERDU") + ";";
		ligne = ligne + nbTours + ";";
		ligne = ligne + nbMaxTours + ";";
		ligne = ligne + scoreFinal + ";";
		ligne = ligne + nbCaseVisitee + ";";
		ligne = ligne + wumpusMort + ";";
		ligne = ligne + nbMouvement + ";";
		ligne = ligne + nbMouvementBloque + ";";
		ligne = ligne + nbTir + ";";
		ligne = ligne + nbRamasse + ";";
		ligne = ligne + nbAbandon + ";";
		ligne = ligne + tempsMoyenObservationIA + ";";
		ligne = ligne + tempsMoyenDecisionIA + ";";

		return ligne;
	}
	
	public String getEnteteStat(){
		String ligne="";
		
		ligne = ligne + "nbCaseTotal" + ";";
		ligne = ligne + "nbCaseI" + ";";
		ligne = ligne + "nbCaseJ" + ";";
		ligne = ligne + "probaPuit" + ";";
		ligne = ligne + "nbPuit" + ";";
		ligne = ligne + "nbFlecheInitial" + ";";
		ligne = ligne + "scoreInitial" + ";";
		ligne = ligne + "etatJeuFinal" + ";";
		ligne = ligne + "nbTours" + ";";
		ligne = ligne + "nbMaxTours" + ";";
		ligne = ligne + "scoreFinal" + ";";
		ligne = ligne + "nbCaseVisitee" + ";";
		ligne = ligne + "wumpusMort" + ";";
		ligne = ligne + "nbMouvement" + ";";
		ligne = ligne + "nbMouvementBloque" + ";";
		ligne = ligne + "nbTir" + ";";
		ligne = ligne + "nbRamasse" + ";";
		ligne = ligne + "nbAbandon" + ";";
		ligne = ligne + "tempsMoyenObservationIA" + ";";
		ligne = ligne + "tempsMoyenDecisionIA" + ";";

		return ligne;
	}
	
}
