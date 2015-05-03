package carte;

import wumpus.Contexte;

import java.util.Random;

import agent.Action;
import agent.Agent;
import agent.AgentAventurier;


// classe gérant le modèle de carte
public class Carte{
	public Contexte contexteCarte;
	private int[] resolution;
	private int[] nbCases;
	private double probaPuit;
	private Case[][] cases;
	private Agent agent;
	private int etatJeu = 0; // en cours, 1 perdu(mort), 2 abandonné, 3 gagné
	private int score;
	private int tour = 1;
	private VueCarte vue;
	private long idVue = -1; // on generera la vue avec un id afin de securiser l'accées aux variables Cases de la classe notamment
	private String messageJeu="";
	private Action derniereAction;
	public StatistiqueCarte stats = new StatistiqueCarte();
	private int nbMaxTours; // on pert automatiquement si trop de tours se sont écoulés : ça permet d'eviter les boucles infinies 

	private Carte () {
		resolution = new int[2];
		nbCases = new int[2];
		probaPuit = 0.000000;
	}
	
	public Carte (int nbPixelx,int nbPixely,Contexte ceContexte) {
		this();
		contexteCarte = ceContexte;
		Random randomGenerator = new Random();
		int randomInt,randomInt2,randomInt3,comptePuit,compteCase,compteCase2;
		int compteCaseBordure,compteCaseBordureElligible;
		boolean lingotAttribue,wumpusAttribue;
		// gestion exceptions d'entrée
		if (ceContexte.NB_CASES_COLONNE<2)
		{
			System.out.println("le nombre de cases horizontal ne peut etre inférieur à 2 ");
			throw new UnsupportedOperationException();
		}
		else if (ceContexte.NB_CASES_LIGNE<2)
		{
			System.out.println("le nombre de cases vertical ne peut etre inférieur à 2");
			throw new UnsupportedOperationException();
		}	
		int i,j,k;
		int compte_x, compte_y;
		int cex, cey;
		resolution[0] = nbPixelx;
		resolution[1] = nbPixely;
		nbCases[0] = ceContexte.NB_CASES_LIGNE; 
		stats.nbCaseI=ceContexte.NB_CASES_LIGNE;
		nbCases[1] = ceContexte.NB_CASES_COLONNE; 
		stats.nbCaseJ=ceContexte.NB_CASES_COLONNE;
		stats.nbCaseTotal=ceContexte.NB_CASES_LIGNE * ceContexte.NB_CASES_COLONNE;
		this.probaPuit = ceContexte.PROBA_PUIT;
		stats.probaPuit=ceContexte.PROBA_PUIT;
		// le score
		score = (nbCases[1]*nbCases[0])/2;
		stats.scoreInitial = score;
		// nb max tours
		nbMaxTours = ((Double)(ceContexte.NB_CASES_LIGNE*ceContexte.NB_CASES_COLONNE*ceContexte.COEFF_PERFORMANCE_MIN)).intValue();
		stats.nbMaxTours=nbMaxTours;
		// generation des cases 
		cases = new Case[nbCases[0]][nbCases[1]];
		// ordre de rangement lignes par  lignes, colonnes par colonnes depuis en haut à gauche
		comptePuit = 0;
		// pour le placement du agent
		compteCaseBordure = nbCases[1]*nbCases[0] - ((nbCases[1]-1)*(nbCases[0]-1)>0?(nbCases[1]-1)*(nbCases[0]-1):0);
		compteCaseBordureElligible = compteCaseBordure;	
		for (i=0;i<nbCases[0];i++)
		{	
			for (j=0;j<nbCases[1];j++)
			{
				// est ce que c'est un puit ?
				randomInt = randomGenerator.nextInt(1000);
				// creation des cases
				if (randomInt < probaPuit*1000){ // si puit
					cases[i][j] = new Case(i,j,true,false,false,false,false,false,false);
					stats.nbPuit++;
					comptePuit++;
					if (i ==0 || i==nbCases[0] || j==0 || j==nbCases[1]){
						compteCaseBordureElligible--;
					}
				}
				else { // si pas puit
					cases[i][j] = new Case(i,j,false,false,false,false,false,false,false);
				}		
			}		
		}
		// on attribue ensuite les cases voisines
		// toutes sauf le bord exterieur
		// 0 en haut,1 a droite,2 en bas, 3 à gauche
		for (i=0;i<nbCases[0];i++)
		{
			for (j=0;j<nbCases[1];j++)
			{
				if (i-1>-1){
					cases[i][j].attribuerCaseVoisine(cases[i-1][j], Direction.Haut);	
				}	
				if (j+1<nbCases[1]){
					cases[i][j].attribuerCaseVoisine(cases[i][j+1], Direction.Droite);	
				}
				if (i+1<nbCases[0]){
					cases[i][j].attribuerCaseVoisine(cases[i+1][j], Direction.Bas);	
				}
				if (j-1>-1){
					cases[i][j].attribuerCaseVoisine(cases[i][j-1], Direction.Gauche);	
				}
			}	
		}
		// on place le lingot et le wumpus ( sur des cases differentes et sans puit) 
		randomInt = randomGenerator.nextInt(nbCases[1]*nbCases[0]-1 - comptePuit);
		randomInt2 = randomGenerator.nextInt(nbCases[1]*nbCases[0]-1 - comptePuit);
		while (randomInt2==randomInt){
			randomInt2 = randomGenerator.nextInt(nbCases[1]*nbCases[0]-1 - comptePuit);
		}
		compteCase = -1;
		lingotAttribue=false;
		// on n'attribue pas de wumpus si le contexte wumpus active n'est pas OUI
		if (contexteCarte.WUMPUS_ACTIVE == "OUI")
		{
			wumpusAttribue=false;
		}
		else{
			wumpusAttribue=true;
			randomInt2=nbCases[1]*nbCases[0];
		}
		i = randomInt/nbCases[1];
		j = randomInt%nbCases[1];
		while (! lingotAttribue || !wumpusAttribue){
			if (!cases[i/nbCases[1]][i%nbCases[1]].getaPuit()){
				compteCase++;
				if (compteCase==randomInt){
					
					cases[i/nbCases[1]][i%nbCases[1]].setaLoot(true);
					lingotAttribue=true;
				}
				if (compteCase==randomInt2){				
					cases[i/nbCases[1]][i%nbCases[1]].setaWumpus(true);
					wumpusAttribue=true;
				}
			}
			i++;
			if (i>(nbCases[1]*nbCases[0]-1)){
				i = 0;
			}
		}
		// on reparcourt pour les effets de zone ( brise et odeur) 
		for (i=0;i<nbCases[0];i++)
		{
			for (j=0;j<nbCases[1];j++)
			{
				for (k=0;k<4;k++){
					if (cases[i][j].aCaseVoisine(Direction.Integer2Direction(k))){
						if (cases[i][j].CaseVoisine(Direction.Integer2Direction(k)).getaPuit()){
							cases[i][j].setaBrise(true);
						}
						if (cases[i][j].CaseVoisine(Direction.Integer2Direction(k)).getaWumpus()){
							cases[i][j].setsentMauvais(true);
						}
					}
				}
			}	
		}	
	//placement de l'agent
	randomInt3 = randomGenerator.nextInt(compteCaseBordureElligible);
	agent = new AgentAventurier(nbCases,ceContexte.TYPE_AVENTURIER,ceContexte.NB_FLECHE);
	stats.nbFlecheInitial=agent.getNbMaxFleches();
	compteCase = -1;
	compteCase2 = 0;
	while (compteCase<randomInt3){
		if (compteCase2 < nbCases[1]){
			i=0;
			j = compteCase2;
			if (!cases[i][j].getaPuit()){
				compteCase++;
			}	
		}
		else if (compteCase2 < nbCases[1] + nbCases[0] - 1){
			j=nbCases[1]-1;
			i = compteCase2 - nbCases[1] +1;
			if (!cases[i][j].getaPuit()){
				compteCase++;
			}	
		}
		else if (compteCase2 < 2*nbCases[1] + nbCases[0] - 2){
			i = nbCases[0]-1;
			j = 2*nbCases[1] + nbCases[0] - 2 -compteCase2 ; 
			if (!cases[i][j].getaPuit()){
				compteCase++;
			}	
		}
		else{
			j = 0;
			i = 2*nbCases[1] + 2*nbCases[0] - 3-compteCase2; 	
			if (!cases[i][j].getaPuit()){
				compteCase++;
			}		
		}
		compteCase2++;
	}
	agent.setPositionCase(i,j);
	agent.setObservationCase(cases[i][j].getObservation());
	cases[i][j].setRevelee(true);
	agent.initialiserIA(contexteCarte.IA_ACTIVE);
	agent.observer();
	}

	public void decrireCarte()
	{
		int i,j;
		System.out.println("carte : {\n");
		System.out.println("\tnbCases en x : " + nbCases[1] + "\n" + "\tnbCases en y : " + nbCases[0]);
		for (i=0;i<nbCases[0];i++)
		{
			for (j=0;j<nbCases[1];j++)
			{
				// cases[i][j].decrireCase();
			}	
		}	
	}
	
	// a appeler par toutes les commandes de controle : action a effectuer avant pour preparer le calcul du tour
	private void preAction(){
		tour++;
		messageJeu="";
	}
	
	// a appeler par toutes les commandes de controle : action a effectuer après : maj statut jeu et score
	private void postAction(){
		// si trop de tour game over
		if (tour>nbMaxTours){
			etatJeu=1;
		}
		
		// actualisation de l'état
		if(cases[agent.geti()][agent.getj()].getaPuit() || 
				( cases[agent.geti()][agent.getj()].getaWumpus() && !cases[agent.geti()][agent.getj()].getaWumpusMort() )	){
			etatJeu=1;
		}
		else if (cases[agent.geti()][agent.getj()].getaLoot() && derniereAction==Action.Ramasser){
			etatJeu=3;
		}
		
		// actualisation du score
		if (etatJeu==0){
			score--;
			// si on arrive sur une case 
		}
		else if (etatJeu==3){
			score+=(nbCases[1]*nbCases[0])/2;
			stats.scoreFinal=score;
			stats.nbTours=tour;
			stats.etatJeuFinal=etatJeu;
		}
		else if (etatJeu==1){
			score-=((nbCases[1]*nbCases[0])/2 + 10);
			stats.scoreFinal=score;
			stats.nbTours=tour;
			stats.etatJeuFinal=etatJeu;
		}
		else if (etatJeu==2){ 
			score-=10;	
		}
		
		agent.setEtat(etatJeu);
		agent.setDerniereAction(derniereAction);
		agent.observer();
		
	}
	public int getEtatJeu(){
		return etatJeu;
	}
	
	public int getTour(){
		return tour;
	}
	
	public int getMaxTour(){
		return nbMaxTours;
	}
	
	public int getNbFleches(){
		return agent.getNbFleches();
	}
	
	public int getNbMaxFleches(){
		return agent.getNbMaxFleches();
	}
	
	public String getMessageAventurier(){
		String msg = "";
		msg = ((AgentAventurier)agent).parler();
		return msg;
	}
	
	public String getMessageJeu(){
		if (messageJeu.compareTo("")==0){
			if (etatJeu == 0){
				messageJeu = "Jeu en cours";
	    	}
	    	else if (etatJeu == 1){
	    		messageJeu = "Perdu ! ";
	    	}
	    	else if (etatJeu == 2){
	    		messageJeu = "Vous avez abandonné ! ";
	    	}
	    	else if (etatJeu == 3){
	    		messageJeu = "Gagné !";
	    	}
		}
    	return messageJeu;
	}
	
	public int getScore(){
		return score;
	}
	
	public void abandonner(){
		if (etatJeu==0){
			preAction();
			etatJeu = 2;
			stats.nbAbandon=1;
			postAction();
			reveler();
		}
	}
	
	public void ramasser(){
		if (etatJeu == 0){
			preAction();
			derniereAction = Action.Ramasser;
			stats.nbRamasse=1;
			postAction();
		}
	}
	
	private void avancerAgent(Direction sens){
		stats.nbMouvement++;
		agent.setOrientation(sens);
		if (!cases[agent.geti()][agent.getj()].aCaseVoisine(sens)){
			agent.setBloque(true);
			stats.nbMouvementBloque++;
		}
		else{
			// actualisation de l'ancienne case perso
			cases[agent.geti()][agent.getj()].setaDessiner(true);
			cases[agent.geti()][agent.getj()].setaAgent(false);
			// le perso avance
			agent.setObservationCase(cases[agent.geti()][agent.getj()].CaseVoisine(sens).getObservation());
			agent.setPositionCase(cases[agent.geti()][agent.getj()].CaseVoisine(sens).geti(),cases[agent.geti()][agent.getj()].CaseVoisine(sens).getj());
			// actualisation de la nouvelle case perso
			if (! cases[agent.geti()][agent.getj()].getRevelee()){
				cases[agent.geti()][agent.getj()].setRevelee(true);
				stats.nbCaseVisitee++;
			}
			cases[agent.geti()][agent.getj()].setaAgent(true);
			cases[agent.geti()][agent.getj()].setaDessiner(true);
			agent.setBloque(false);
		}
	}
	private void allerA(Direction direction){
		if (etatJeu == 0){
			preAction();
			avancerAgent(direction);
			postAction();
		}
	}
	public void allerEnHaut(){
		allerA(Direction.Haut);
		derniereAction = Action.AllerEnHaut;
	}
	public void allerADroite(){
		allerA(Direction.Droite);
		derniereAction = Action.AllerADroite;
	}
	public void allerEnBas(){
		allerA(Direction.Bas);
		derniereAction = Action.AllerEnBas;
	}	
	public void allerAGauche(){
		allerA(Direction.Gauche);
		derniereAction = Action.AllerAGauche;
	}
	
	private void tirerA(Direction direction){
		if (etatJeu == 0 && agent.getnbFleches()>0){
			stats.nbTir++;
			preAction();
			messageJeu = "fleche ds vide";
			if (cases[agent.geti()][agent.getj()].aCaseVoisine(direction)){
				if (cases[agent.geti()][agent.getj()].CaseVoisine(direction).getaWumpus() 
						&& !cases[agent.geti()][agent.getj()].CaseVoisine(direction).getaWumpusMort()){
					cases[agent.geti()][agent.getj()].CaseVoisine(direction).setaWumpusMort(true);
					stats.wumpusMort=1;
					score+=20;
					messageJeu = "fleche dans le mil !";
				}
				agent.setObservationCase(cases[agent.geti()][agent.getj()].CaseVoisine(direction).getObservation());
				if (! cases[agent.geti()][agent.getj()].CaseVoisine(direction).getRevelee()){
					cases[agent.geti()][agent.getj()].CaseVoisine(direction).setRevelee(true);
					stats.nbCaseVisitee++;
				}
				agent.observer();
			}
			agent.tirerFleche();
		}
		else if (etatJeu == 0){
			messageJeu = "plus de fleches ! ";
		}
		postAction();
	}
	public void tirerEnHaut(){
		tirerA(Direction.Haut);
		derniereAction = Action.TirerEnHaut;
	}
	public void tirerADroite(){
		tirerA(Direction.Droite);
		derniereAction = Action.TirerADroite;
	}	
	public void tirerEnBas(){
		tirerA(Direction.Bas);
		derniereAction = Action.TirerEnBas;
	}
	public void tirerAGauche(){
		tirerA(Direction.Gauche);
		derniereAction = Action.TirerAGauche;
	}
	
	public void reveler(){
		for (int i=0;i<nbCases[0];i++)
		{
			for (int j=0;j<nbCases[1];j++)
			{
				cases[i][j].setRevelee(true);	
			}	
		}
	}
	
	// crée l'ia qui va jouer le cas écheant
	public void creerIA(){
		agent.initialiserIA(contexteCarte.IA_ACTIVE);
	}
	
	public void simulerIA(){
		Action monAction;
		monAction = agent.deciderActionSuivante();
		
		if (monAction==Action.Abandonner){
			abandonner();
		}
		else if (monAction==Action.AllerEnHaut){
			allerEnHaut();
		}
		else if (monAction==Action.AllerADroite){
			allerADroite();
		}
		else if (monAction==Action.AllerEnBas){
			allerEnBas();
		}
		else if (monAction==Action.AllerAGauche){
			allerAGauche();
		}
		else if (monAction==Action.TirerEnHaut){
			tirerEnHaut();
		}
		else if (monAction==Action.TirerADroite){
			tirerADroite();
		}
		else if (monAction==Action.TirerEnBas){
			tirerEnBas();
		}
		else if (monAction==Action.TirerAGauche){
			tirerAGauche();
		}
		else if (monAction==Action.Ramasser){
			ramasser();
		}	
	}
	
	// fonction creant la vue de la carte
	public void creerVue(){
		if (idVue==-1){
			Random randomGenerator = new Random();
			idVue = randomGenerator.nextLong();
			vue = new VueCarte(contexteCarte,this,idVue,resolution[0],resolution[1]);
		}
	}
	
	public VueCarte getVue(){
		return vue;
	}
	
	public Case[][] getCases(long id){	
		if (id == idVue){
			return this.cases;
		}
		else {
			return null;
		}
	}
	
	public int[] getNbCases(){
		return nbCases;
	}
	
	public VueCarte getVueCarte(){
		return vue;
	}
	
	public Agent getAgent(long id){
		if (id == idVue){
			return this.agent;
		}
		else {
			return null;
		}
	}
	
	public String getEnteteStatistiqueCarte(){
		String retour;
		if (etatJeu==0){
			retour = "Impossible de retourner les statistique alors que la partie est encore en cours";
		}
		else {
			retour = stats.getEnteteStat();
		}
		
		return retour;
	}
	
	public String getStatistiqueCarte(){
		String retour;
		if (etatJeu==0){
			retour = "Impossible de retourner les statistique alors que la partie est encore en cours";
		}
		else {
			retour = stats.getLigneStat();
		}
		
		return retour;
	}
	
// fin de la classe	
}
