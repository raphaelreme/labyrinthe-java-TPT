/*
Attention, ce projet utilise l'encodage ISO-8859-1
 */

import controller.MazeController;
import modele.MazeModel;
import graphique.Frame;

public class Main {

	public static void main(String[] args){
		
		
		MazeModel m = new MazeModel();
		MazeController c = new MazeController(m);
		
		Frame f = new Frame(m,c);
		
		
	}

}
