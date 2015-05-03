package carte;

public enum Direction {	
	Haut,
	Droite,
	Bas,
	Gauche;
	
	public static Direction Integer2Direction(int x) {
        switch(x) {
        case 0:
            return Haut;
        case 1:
            return Droite;
        case 2:
            return Bas;
        case 3:
            return Gauche;    
        }
        return null;
    }
	
	public static int Direction2Integer(Direction x) {
        switch(x) {
        case Haut:
            return 0;
        case Droite:
            return 1;
        case Bas:
            return 2;
        case Gauche:
            return 3;    
        }
        return -1;
    }
	
	public static Direction Opposee(Direction x){
		switch(x) {
		case Haut:
            return Bas;
        case Droite:
            return Gauche;
        case Bas:
            return Haut;
        case Gauche:
            return Droite;     
        }
        return null;
    }
	
}
