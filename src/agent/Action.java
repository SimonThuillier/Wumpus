package agent;

public enum Action {
	  AllerEnHaut,
	  AllerADroite,
	  AllerEnBas,
	  AllerAGauche,
	  Abandonner,
	  TirerEnHaut,
	  TirerADroite,
	  TirerEnBas,
	  TirerAGauche,
	  Ramasser;
	  
	  
		public static Action Integer2Action(int x) {
	        switch(x) {
	        case 0:
	            return AllerEnHaut;
	        case 1:
	            return AllerADroite;
	        case 2:
	            return AllerEnBas;
	        case 3:
	            return AllerAGauche;    
	        case 4:
	            return Abandonner;
	        case 5:
	            return TirerEnHaut;
	        case 6:
	            return TirerADroite;
	        case 7:
	            return TirerEnBas;
	        case 8:
	            return TirerAGauche;
	        case 9:
	            return Ramasser;      
	        }
	        return null;
	    }
		
		public static int Action2Integer(Action x) {
	        switch(x) {
	        case AllerEnHaut:
	            return 0;
	        case AllerADroite:
	            return 1;
	        case AllerEnBas:
	            return 2;
	        case AllerAGauche:
	            return 3;  
	        case Abandonner:
	            return 4; 
	        case TirerEnHaut:
	            return 5;
	        case TirerADroite:
	            return 6;
	        case TirerEnBas:
	            return 7;
	        case TirerAGauche:
	            return 8;
	        case Ramasser:
	            return 9;      
	        }
	        return -1;
	    } 
	  
	  
	}