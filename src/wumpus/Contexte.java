package wumpus;
import fichier.FicIniHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

// classe contenant les données contextuelles du projet
public class Contexte {
	
	// taille des fenetres
	public int LARGEUR_FENETRE = 900;
	public int HAUTEUR_FENETRE = 900;
	public int NB_CASES_LIGNE = 5;
	public int NB_CASES_COLONNE = 5;
	public double PROBA_PUIT = 0.1;
	public String TYPE_AVENTURIER = "MARIO";
	public String TEMPLATE_GRAPHIQUE = "VANILLA";
	public int EPAISSEUR_FRONTIERE_CASE = 2;
	public long DELAI_TOUR_SIMULATION=Long.parseLong("1000");
	public int NB_JEU_SIMULATION = 5;
	public int NB_FLECHE = 5;
	public String IA_ACTIVE="IA1";
	public Double COEFF_PERFORMANCE_MIN=2.0;
	public String WUMPUS_ACTIVE="OUI";
	
	private FicIniHandler handler; // on lie le contexte a un fichier ini
	 
	public Contexte(String FicIni) throws IOException{
		handler = new FicIniHandler(System.getProperty("user.dir") + "\\contexte\\" + FicIni);
		handler.lireFichier();
		lireFichierIni();	
	}
	
	// pour realiser l'import à partir d'un fichier ini
	public boolean lireFichierIni(){
		
		Iterator it = handler.elements.keySet().iterator();
		String cle;
		
		while(it.hasNext()){
			cle = (String)it.next();
			switch(cle){
			case  "LARGEUR_FENETRE" :
				LARGEUR_FENETRE=Integer.parseInt( ((String)handler.elements.get(cle)) );
				break;
			case  "HAUTEUR_FENETRE" :
				HAUTEUR_FENETRE=Integer.parseInt( ((String)handler.elements.get(cle)) );
				break;
			case  "NB_CASES_LIGNE" :
				NB_CASES_LIGNE=Integer.parseInt( ((String)handler.elements.get(cle)) );
				break;
			case  "NB_CASES_COLONNE" :
				NB_CASES_COLONNE=Integer.parseInt( ((String)handler.elements.get(cle)) );
				break;
			case  "PROBA_PUIT" :
				PROBA_PUIT=Double.parseDouble( ((String)handler.elements.get(cle)) );
				break;
			case  "TYPE_AVENTURIER" :
				TYPE_AVENTURIER= ((String)handler.elements.get(cle)) ;
				break;
			case  "TEMPLATE_GRAPHIQUE" :
				TEMPLATE_GRAPHIQUE=((String)handler.elements.get(cle)) ;
				break;
			case  "EPAISSEUR_FRONTIERE_CASE" :
				EPAISSEUR_FRONTIERE_CASE=Integer.parseInt( ((String)handler.elements.get(cle)) );
				break;
			case  "DELAI_TOUR_SIMULATION" :
				DELAI_TOUR_SIMULATION=Long.parseLong( ((String)handler.elements.get(cle)) );
				break;	
			case  "NB_JEU_SIMULATION" :
				NB_JEU_SIMULATION=Integer.parseInt( ((String)handler.elements.get(cle)) );
				break;	
			case  "NB_FLECHE" :
				NB_FLECHE=Integer.parseInt( ((String)handler.elements.get(cle)) );
				break;	
			case  "IA_ACTIVE" :
				IA_ACTIVE=((String)handler.elements.get(cle)) ;
				break;
			case  "COEFF_PERFORMANCE_MIN" :
				COEFF_PERFORMANCE_MIN=Double.parseDouble( ((String)handler.elements.get(cle)) );
				break;	
			case  "WUMPUS_ACTIVE" :
				WUMPUS_ACTIVE=((String)handler.elements.get(cle));
				break;		
			default :
				break;
			}
		}
		
		return true;
		}
		
		// pour ecrire dans le fichier ini
		public boolean EcrireFichierIni(){
			Iterator it = handler.elements.keySet().iterator();
			String cle;
			while(it.hasNext()){
				cle = (String)it.next();
				switch(cle){
				case  "LARGEUR_FENETRE" :
					handler.elements.put(cle,((Integer)LARGEUR_FENETRE).toString()) ;
					break;
				case  "HAUTEUR_FENETRE" :
					handler.elements.put(cle,((Integer)HAUTEUR_FENETRE).toString()) ;
					break;
				case  "NB_CASES_LIGNE" :
					handler.elements.put(cle,((Integer)NB_CASES_LIGNE).toString()) ;
					break;
				case  "NB_CASES_COLONNE" :
					handler.elements.put(cle,((Integer)NB_CASES_COLONNE).toString()) ;
					break;
				case  "PROBA_PUIT" :
					handler.elements.put(cle,((Double)PROBA_PUIT).toString()) ;
					break;
				case  "TYPE_AVENTURIER" :
					handler.elements.put(cle,TYPE_AVENTURIER) ;
					break;
				case  "TEMPLATE_GRAPHIQUE" :
					handler.elements.put(cle,TEMPLATE_GRAPHIQUE) ;
					break;
				case  "EPAISSEUR_FRONTIERE_CASE" :
					handler.elements.put(cle,((Integer)EPAISSEUR_FRONTIERE_CASE).toString()) ;
					break;
				case  "DELAI_TOUR_SIMULATION" :
					handler.elements.put(cle,((Long)DELAI_TOUR_SIMULATION).toString()) ;
					break;	
				case  "NB_JEU_SIMULATION" :
					handler.elements.put(cle,((Integer)NB_JEU_SIMULATION).toString()) ;
					break;	
				case  "NB_FLECHE" :
					handler.elements.put(cle,((Integer)NB_FLECHE).toString()) ;
					break;	
				case  "IA_ACTIVE" :
					handler.elements.put(cle,IA_ACTIVE) ;
					break;
				case  "COEFF_PERFORMANCE_MIN" :
					handler.elements.put(cle,((Double)COEFF_PERFORMANCE_MIN).toString()) ;
					break;
				case  "WUMPUS_ACTIVE" :
					handler.elements.put(cle,WUMPUS_ACTIVE) ;
					break;		
				default :
					break;
				}
			}
		handler.ecrireFichier();	
		return true;
	}
		
		// pour ecrire seulement certaines valeurs dans le fichier ini
		public boolean EcrireFichierIni(String cetteCle){
			Iterator it = handler.elements.keySet().iterator();
			String cle;
			while(it.hasNext()){
				cle = (String)it.next();
				if(cle == cetteCle){
					switch(cle){
					case  "LARGEUR_FENETRE" :
						handler.elements.put(cle,((Integer)LARGEUR_FENETRE).toString()) ;
						break;
					case  "HAUTEUR_FENETRE" :
						handler.elements.put(cle,((Integer)HAUTEUR_FENETRE).toString()) ;
						break;
					case  "NB_CASES_LIGNE" :
						handler.elements.put(cle,((Integer)NB_CASES_LIGNE).toString()) ;
						break;
					case  "NB_CASES_COLONNE" :
						handler.elements.put(cle,((Integer)NB_CASES_COLONNE).toString()) ;
						break;
					case  "PROBA_PUIT" :
						handler.elements.put(cle,((Double)PROBA_PUIT).toString()) ;
						break;
					case  "TYPE_AVENTURIER" :
						handler.elements.put(cle,TYPE_AVENTURIER) ;
						break;
					case  "TEMPLATE_GRAPHIQUE" :
						handler.elements.put(cle,TEMPLATE_GRAPHIQUE) ;
						break;
					case  "EPAISSEUR_FRONTIERE_CASE" :
						handler.elements.put(cle,((Integer)EPAISSEUR_FRONTIERE_CASE).toString()) ;
						break;
					case  "DELAI_TOUR_SIMULATION" :
						handler.elements.put(cle,((Long)DELAI_TOUR_SIMULATION).toString()) ;
						break;	
					case  "NB_JEU_SIMULATION" :
						handler.elements.put(cle,((Integer)NB_JEU_SIMULATION).toString()) ;
						break;
					case  "NB_FLECHE" :
						handler.elements.put(cle,((Integer)NB_FLECHE).toString()) ;
						break;
					case  "IA_ACTIVE" :
						handler.elements.put(cle,IA_ACTIVE) ;
						break;
					case  "COEFF_PERFORMANCE_MIN" :
						handler.elements.put(cle,((Double)COEFF_PERFORMANCE_MIN).toString()) ;
						break;	
					case  "WUMPUS_ACTIVE" :
						handler.elements.put(cle,WUMPUS_ACTIVE) ;
						break;	
					default :
						break;
					}
				}
			}
			handler.ecrireFichier();		
		return true;
	}	
		
		
		
		public void changerFichierContexte(String FicIni) throws IOException{
			handler.fermerFichier();
			handler = new FicIniHandler(System.getProperty("user.dir") + "\\contexte\\" + FicIni);
		}
	

}
