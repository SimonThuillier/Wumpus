package graphisme;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Image;
import java.awt.Color;

import java.util.HashMap;

// classe abstraite de gestion des bibliotheques graphiques
public abstract class Graphisme {
	public HashMap<ElementGraphique,Object> elements ;
	
	
	public Graphisme(){
		elements = new HashMap<ElementGraphique,Object>();
	}
	
	public enum ElementGraphique{
		caseInexploree,
		caseNormale,
		casePuit,
		caseOdorante,
		caseBrise,
		caseOdoranteBrise,
		elementLingot,
		agentAventurierHaut,
		agentAventurierDroite,
		agentAventurierBas,
		agentAventurierGauche,
		agentMarioHaut,
		agentMarioDroite,
		agentMarioBas,
		agentMarioGauche,
		agentWumpus,
		agentWumpusMort;
	}

	
	
	
	

}
