package graphique;


import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Panel extends JPanel{

	private static final long serialVersionUID = 1L;
	
		private Frame mainWindow;
	
	
	public Panel(Frame f){
		super();
		mainWindow = f;
	}
	public void paintComponent(Graphics g){
		g.drawImage(mainWindow.getModel().getBackground(), 0, 0 , this.getWidth(), this.getHeight(),this);
		if (mainWindow.getModel().getM().getLength() != 0) //si il y a un labyrinthe 
			drawGraph(g);
	}
	
	protected void drawGraph(Graphics g){
		
		int l = mainWindow.getModel().getM().getLength();
		int w = mainWindow.getModel().getM().getWidth();
		/*
		 * dimension d'une case du labyrinthe 
		 * floatant pour mieux s'adapter à la taille du panel
		*/
		float height = this.getHeight()/(float)l;
		float width = this.getWidth()/(float)w;
		
		for (int i=0; i<l; i++){
			for (int j=0; j<w; j++){
				String charIJ = mainWindow.getModel().getM().getLetter(i,j);
				Image img = null;
				if (charIJ == "W"){
					img = mainWindow.getModel().getWall();
				} else if (charIJ == "A"){
					img = mainWindow.getModel().getArrival();
				} else if (charIJ == "D"){
					img = mainWindow.getModel().getStart();
				}
				/*
				 * quelques cases seront de largeur (resp hauteur) width+1 (resp height+1) pour compenser le surplus de pixels
				 * On pourrait aussi faire toutes les cases de meme taille et compenser avec des murs comme dans la 1ere version de cette
				 * fonction mais cela rend moins bien !
				 */
				g.drawImage(img,(int)(j*width),(int)(i*height),(int)width+1,(int)height+1,this);
			}
		}
	}
}
