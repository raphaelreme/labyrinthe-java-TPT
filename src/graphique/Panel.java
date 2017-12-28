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
		//dimension d'une case du labyrinthe 

		float height = this.getHeight()/l;
		float width = this.getWidth()/w;
		
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
				g.drawImage(img,(int)(j*width),(int)(i*height),(int)width+1,(int)height+1,this);
			}
		}
		/*
		 * la taille du panel n'est pas toujours divisible par le nombre de cases !
		 * Il faut rajouter des murs aux extremités pour indiquer qu'on ne peut pas passer par là
		 * (c'est seulement du design, cela n'apparait pas dans le labyrinthe)
		 */
		
		for (int i=0; i<l; i++){
			g.drawImage(mainWindow.getModel().getWall(),(int)(w*width),(int)(i*height),(int)width,(int)height,this);
		}
		for (int j=0; j<=w; j++){
			g.drawImage(mainWindow.getModel().getWall(),(int)(j*width),(int)(l*height),(int)width,(int)height,this);
		}
		
	}
}
