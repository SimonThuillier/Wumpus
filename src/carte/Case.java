package carte;

public class Case {
	
	private int[] position;
	private boolean[] attributsCase;
	private Case[] voisines;
	private boolean aDessiner; // la case doit elle etre dessinée/redessinée ?
	private boolean revelee;
	
	
		// constructeur par défaut de cases : inutile mais bon ^^
	public Case() {
		position = new int[2];
		attributsCase = new boolean[7];
		voisines = new Case[4];
		revelee = false;
		aDessiner = true;
	}
	public Case(int i,int j,boolean aPuit,boolean aBrise,boolean sentMauvais,boolean aLoot,boolean aWumpus,boolean aAgent,boolean revelee) {
		this();
		
		position[0] = i;
		position[1] = j;
		attributsCase[0] = aPuit;
		attributsCase[1] = aBrise;
		attributsCase[2] = sentMauvais;
		attributsCase[3] = aLoot;
		attributsCase[4] = aWumpus;
		attributsCase[5] = aAgent;
		attributsCase[6] = false; // par defaut le wumpus n'est pas mort
		this.revelee = revelee;
	}
	
	// regle de gestion on n'attribue qu'une seule fois les cases voisines ie si elles sont déjà mises en place elle restent en place ! 
	// renvoie 
	public int attribuerCaseVoisine(Case voisine,Direction cetteDirection){
		int res;
		res = 0;
		
		if (this.aCaseVoisine(cetteDirection)){
			res = -1;
		}
		else
		{
			voisines[Direction.Direction2Integer(cetteDirection)] = voisine;
			voisine.attribuerCaseVoisine(this,Direction.Opposee(cetteDirection));
		}
		
		
		return res;	
	}
	
	public boolean aCaseVoisine(Direction cetteDirection){
		boolean res;
		int test;
		Case casetest;
		
		res = true;
		try {
			casetest = voisines[Direction.Direction2Integer(cetteDirection)];
			test = casetest.position[0];
		}
		catch (NullPointerException e)
		{
			res = false;
		}
		return res;	
	}
	// a appeler après test de aCaseVoisine
	public Case CaseVoisine(Direction cetteDirection){
		return voisines[Direction.Direction2Integer(cetteDirection)];
	}
	
	public int geti(){
		return position[0];
	}
	public int getj(){
		return position[1];
	}

	public boolean getaPuit(){
		return attributsCase[0];
	}
	public boolean getaBrise(){
		return attributsCase[1];
	}
	public boolean getsentMauvais(){
		return attributsCase[2];
	}
	public boolean getaLoot(){
		return attributsCase[3];
	}
	public boolean getaWumpus(){
		return attributsCase[4];
	}
	public boolean getaAgent(){
		return attributsCase[5];
	}
	public boolean getRevelee(){
		return revelee;
	}
	public boolean getaWumpusMort(){
		return attributsCase[6];
	}
	public boolean getaDessiner(){
		return aDessiner;
	}
	public void setaPuit(boolean b){
		attributsCase[0] = b;
	}
	public void setaBrise(boolean b){
		attributsCase[1] = b;
	}
	public void setsentMauvais(boolean b){
		attributsCase[2] = b;
	}
	public void setaLoot(boolean b){
		attributsCase[3] = b;
	}
	public void setaWumpus(boolean b){
		attributsCase[4] = b;
	}
	public void setaAgent(boolean b){
		attributsCase[5] = b;
	}
	public void setaWumpusMort(boolean b){
		attributsCase[6] = b;
	}
	public void setRevelee(boolean b){
		revelee = b;
	}
	public void setaDessiner(boolean b){
		aDessiner = b;
	}
	
	public ObservationCase getObservation(){
		ObservationCase observation = new ObservationCase();
		observation.position = new int[2];
		observation.position[0] = this.position[0];
		observation.position[1] = this.position[1];
		observation.attributsCaseObservable = this.attributsCase.clone();
		return observation;
	}
	// classe retournée aux agents afin de leur fournir leur observation; on ne passe jamais de case aux agents pour eviter la triche
	public class ObservationCase {
		public int[] position;
		public boolean[] attributsCaseObservable;
		
		public ObservationCase(){
		}	
		
		public int geti(){
			return position[0];
		}
		public int getj(){
			return position[1];
		}

		public boolean getaPuit(){
			return attributsCaseObservable[0];
		}
		public boolean getaBrise(){
			return attributsCaseObservable[1];
		}
		public boolean getsentMauvais(){
			return attributsCaseObservable[2];
		}
		public boolean getaLoot(){
			return attributsCaseObservable[3];
		}
		public boolean getaWumpus(){
			return attributsCaseObservable[4];
		}
		public boolean getaAgent(){
			return attributsCaseObservable[5];
		}
		public boolean getaWumpusMort(){
			return attributsCase[6];
		}
		
		
	}
	
// fin de la classe	
}
	
	


