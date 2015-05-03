package fichier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FicStatistiqueHandler {
	private String nomFichier; // nom sans extension
	private File fichier;
	private FileWriter writerFichier;
	private final int NB_MAX_LU=4096;
	private final int INDEX_DEBUT=0;
	char[] buffer;
	private int nbCaracteres;
	private String extension = ".csv";
	
	
	public FicStatistiqueHandler(String nomFichier){
		
		boolean manipFichierOk = false;
		
		this.nomFichier = nomFichier;
		fichier = new File(System.getProperty("user.dir") + "\\statistiques\\" + nomFichier + extension);
		try{manipFichierOk = fichier.createNewFile();}
		catch(IOException e){
				System.out.println("erreur dans la creation du fichier stats");
		}
		if (! manipFichierOk){
			manipFichierOk = fichier.delete();
			try{manipFichierOk = fichier.createNewFile();}
			catch(IOException e){
					System.out.println("erreur dans la creation du fichier stats");
			}				
		}	
		
		
		try{
			writerFichier = new FileWriter(fichier);
		}
		catch(IOException e){
			System.out.println("erreur dans l ecriture du fichier stats");
		}	
		
	}
	
	
	

	public void ecrireFichier(String texte){
		
		try{
			writerFichier.write(texte);
		}
		catch(IOException e){
			System.out.println("erreur dans l ecriture (execution) du fichier stats");
		}	
		
		
		
		
	}
	
	public void fermerFichier(){
		try{
			writerFichier.close();
		}
		catch(IOException e){
			System.out.println("erreur dans l ecriture du fichier stats");
		}	
	}
	
	
}
