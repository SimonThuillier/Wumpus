package fichier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;


public class FicIniHandler {
	private String nomFichier; // chemin complet du fichier ini
	private File fichier;
	private FileReader lecteurFichier;
	private FileWriter writerFichier;
	private final int NB_MAX_LU=4096;
	private final int INDEX_DEBUT=0;
	char[] buffer;
	private int nbCaracteres;
	public HashMap<String,String> elements ;
	
	public FicIniHandler(String nomFic) throws IOException{
		nomFichier = nomFic;	
		// lecteurFichier = new BufferedReader();		
		try{
			fichier = new File(nomFichier);
			lecteurFichier = new FileReader(fichier);
			elements = new HashMap<String,String>();
			lecteurFichier.close();
		}
		catch(IOException e){
			throw new IOException();
		}
	}
	
	public void lireFichier(){
		// int resultat = 0;
		int i=0,j=0;
		boolean ligne_lisible = false;
		boolean ligne_commentee = false;
		boolean separateur_passe = false;
		char separateurCleValeur = '=';
		char commentaire = '#';
		String cetteCle="";
		String cetteValeur="";
		
		try{
		lecteurFichier = new FileReader(fichier);
		buffer = new char[NB_MAX_LU];
		nbCaracteres = lecteurFichier.read(buffer,INDEX_DEBUT,NB_MAX_LU);
		lecteurFichier.close();
		if(nbCaracteres>0){
			buffer[nbCaracteres] = '\n';
		}
		else{
			return;
		}
		}
		catch(IOException e){
			System.out.println("lecture impossible");
		}
		
		while(i<nbCaracteres){
			// fin de ligne
			if(buffer[i]=='\n' || buffer[i]=='\r'){
				if (ligne_lisible){
					elements.put(cetteCle, cetteValeur);
				}
				// reinit pr ligne suivante
				ligne_commentee = false;
				ligne_lisible =false;
				separateur_passe= false;
				cetteCle="";
				cetteValeur="";
				j=0;
			}
			else if (j==0 && buffer[i]!=' '){
				if(buffer[i]!=commentaire){
					cetteCle = cetteCle + Character.toString(buffer[i]);
					j++;
				}
				else{
					ligne_commentee = true;
				}
			}
			else if (j>0 && !ligne_commentee ){
				if (! separateur_passe && buffer[i]!=' ' && buffer[i]!=separateurCleValeur){
					cetteCle = cetteCle + Character.toString(buffer[i]);
				}
				else if(separateur_passe && buffer[i]!=' ' && buffer[i]!=separateurCleValeur) {
					cetteValeur = cetteValeur + Character.toString(buffer[i]);
				}
				else if (! separateur_passe && buffer[i]==separateurCleValeur){
					separateur_passe=true;
					ligne_lisible=true;
				}
				j++;
			}
			i++;
		}
	}
	
	// on ecrit les elements de hashmap dans le fichier
	public void ecrireFichier(){
		int i=0,j=0;
		boolean ligne_lisible = false;
		boolean ligne_commentee = false;
		boolean separateur_passe = false;
		boolean fin_ligne_en_cours = false;
		char separateurCleValeur = '=';
		char commentaire = '#';
		String cetteCle="";
		String cetteValeur="";
		try{
			lecteurFichier = new FileReader(fichier);
			buffer = new char[NB_MAX_LU];
			nbCaracteres = lecteurFichier.read(buffer,INDEX_DEBUT,NB_MAX_LU);
			lecteurFichier.close();
			if(nbCaracteres>0){
				buffer[nbCaracteres] = '\n';
			}
			else{
				return;
			}
			}
			catch(IOException e){
				System.out.println("lecture impossible");
			}
		
			String texteFinal = "";
			
			while(i<nbCaracteres){
				// fin de ligne
				if(buffer[i]=='\n' || buffer[i]=='\r'){
					if (! fin_ligne_en_cours){
						if (ligne_lisible){
							cetteCle = cetteCle.trim();
							if (elements.containsKey(cetteCle)){
								cetteCle = cetteCle + "=" + elements.get(cetteCle);
							}
							else{
								cetteCle = cetteCle + "=" + cetteValeur;
							}
						}
						// reinit pr ligne suivante
						ligne_commentee = false;
						ligne_lisible =false;
						separateur_passe= false;
						texteFinal = texteFinal + cetteCle + "\r\n";
						cetteCle="";
						cetteValeur="";
						j=0;
						fin_ligne_en_cours = true;
					}
				}
				else if (j==0 && buffer[i]!=' '){
					fin_ligne_en_cours = false;
					if(buffer[i]!=commentaire){
						cetteCle = cetteCle + Character.toString(buffer[i]);
						j++;
					}
					else{
						fin_ligne_en_cours = false;
						ligne_commentee = true;
						cetteCle = cetteCle + Character.toString(buffer[i]);
					}
				}
				else if (j>0 && !ligne_commentee ){
					if (! separateur_passe && buffer[i]!=' ' && buffer[i]!=separateurCleValeur){
						cetteCle = cetteCle + Character.toString(buffer[i]);
					}
					else if(separateur_passe && buffer[i]!=' ' && buffer[i]!=separateurCleValeur) {
						cetteValeur = cetteValeur + Character.toString(buffer[i]);
					}
					else if (! separateur_passe && buffer[i]==separateurCleValeur){
						separateur_passe=true;
						ligne_lisible=true;
					}
					j++;
				}
				else if (ligne_commentee){
					cetteCle = cetteCle + Character.toString(buffer[i]);
				}
				else{
					cetteCle = cetteCle + Character.toString(buffer[i]);
				}
				
				i++;
			}

			try {
				writerFichier = new FileWriter(fichier);
				writerFichier.write(texteFinal);
				writerFichier.close();
				
			} catch (IOException e) {

			}
			
	}
	
	public void fermerFichier(){
		try{
			lecteurFichier.close();
			writerFichier.close();
		}
		catch(IOException e){
			System.out.println("erreur dans l ecriture du fichier stats");
		}	
	}
	
	
	public void afficherHandler(){
		Iterator it = elements.keySet().iterator();
		String cle;
		
		while(it.hasNext()){
			cle = (String)it.next();
			System.out.println("cle : " + cle + " - valeur : " + elements.get(cle));
		}
		
		
	}
	

}
