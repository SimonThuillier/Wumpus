package graphisme;

import java.awt.Color;
import java.awt.GradientPaint;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphismeVanilla extends Graphisme {

	public GraphismeVanilla(){
		super();
		instancierGraphismes();
		instancierImages();
	}
	
	private void instancierGraphismes(){
		this.elements.put(ElementGraphique.caseInexploree, new GradientPaint(0, 0, Color.black, 30, 30, Color.black, true) );
		this.elements.put(ElementGraphique.caseNormale, new GradientPaint(0, 0, Color.GRAY, 30, 30, Color.GRAY, true) );
		this.elements.put(ElementGraphique.casePuit, new GradientPaint(0, 0, Color.DARK_GRAY, 30, 30, Color.DARK_GRAY, true) );
		this.elements.put(ElementGraphique.caseOdorante, new GradientPaint(0, 0, Color.GRAY, 30, 30, Color.red, true) );
		this.elements.put(ElementGraphique.caseBrise, new GradientPaint(0, 0, Color.GRAY, 30, 30, Color.BLUE, true) );
		this.elements.put(ElementGraphique.caseOdoranteBrise, new GradientPaint(0, 0, Color.blue, 30, 30, Color.red, true ) );		
	}
	
	private void instancierImages(){
		
		try {
			this.elements.put(ElementGraphique.agentAventurierHaut, ImageIO.read(new File("./images/BM_up.gif")) );
			this.elements.put(ElementGraphique.agentAventurierDroite, ImageIO.read(new File("./images/BM_right.gif")) );
			this.elements.put(ElementGraphique.agentAventurierBas, ImageIO.read(new File("./images/BM_down.gif")) );
			this.elements.put(ElementGraphique.agentAventurierGauche, ImageIO.read(new File("./images/BM_left.gif")) );
			this.elements.put(ElementGraphique.agentMarioHaut, ImageIO.read(new File("./images/mario_haut.gif")) );
			this.elements.put(ElementGraphique.agentMarioDroite, ImageIO.read(new File("./images/mario_droite.gif")) );
			this.elements.put(ElementGraphique.agentMarioBas, ImageIO.read(new File("./images/mario_bas.gif")) );
			this.elements.put(ElementGraphique.agentMarioGauche, ImageIO.read(new File("./images/mario_gauche.gif")) );
			
			this.elements.put(ElementGraphique.elementLingot, ImageIO.read(new File("./images/Gold_Ingot.png")) );
			this.elements.put(ElementGraphique.agentWumpus, ImageIO.read(new File("./images/wumpus.png")) );
			this.elements.put(ElementGraphique.agentWumpusMort, ImageIO.read(new File("./images/wumpus_mort.png")) );
	    } catch (IOException e) {
	      e.printStackTrace();
	    }	
	}

// fin de la classe	
}
