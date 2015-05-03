package wumpus;

import fenetre.FenetreJeu;
import fenetre.MenuPrincipal;
import fichier.FicIniHandler;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		String FicIni = "contexte.ini";
		
		try {
			Contexte contexteWumpus = new Contexte(FicIni);
			MenuPrincipal Menu = new MenuPrincipal(contexteWumpus);
		}
		catch(IOException e){
			System.out.println("Fichier : " + FicIni + " inexistant dans le dossier contexte du projet");
		}

	}
}
