package graphique;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Panel extends JPanel{

	private static final long serialVersionUID = 1L;

	private Frame mainWindow;

	//taille des cases du labyrinthe
	private float boxHeight;
	private float boxWidth;


	public Panel(Frame f){
		super();
		mainWindow = f;

		//pas de labyrinthe
		boxHeight = 0;
		boxWidth = 0;

		this.addMouseListener(new PanelMouseListener());
	}

	public void paintComponent(Graphics g){
		g.drawImage(mainWindow.getModel().getBackground(), 0, 0 , this.getWidth(), this.getHeight(),this);
		if (mainWindow.getModel().getMaze().getLength() != 0) //si il y a un labyrinthe
			drawMaze(g);
	}

	protected void drawMaze(Graphics g){

		int l = mainWindow.getModel().getMaze().getLength();
		int w = mainWindow.getModel().getMaze().getWidth();
		/*
		 * dimension d'une case du labyrinthe
		 * floatant pour mieux s'adapter ? la taille du panel
		 */
		boxHeight = this.getHeight()/(float)l;
		boxWidth = this.getWidth()/(float)w;

		for (int i=0; i<l; i++){
			for (int j=0; j<w; j++){
				String charIJ = mainWindow.getModel().getMaze().getLetter(i,j);
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
				 * (le pixel de trop est generalement ecras? par la case suivante)
				 * On pourrait aussi faire toutes les cases de meme taille et compenser avec des murs comme dans la 1ere version de cette
				 * fonction mais cela rend moins bien !
				 */
				g.drawImage(img,(int)(j*boxWidth),(int)(i*boxHeight),(int)boxWidth+1,(int)boxHeight+1,this);
			}
		}


		drawDijkstra(g);
	}

	public void drawDijkstra(Graphics g){
		g.setColor(Color.RED);
		for (int[] tab:mainWindow.getModel().getDijkstra()){
			int i = tab[0], j = tab[1];
			g.fillOval((int)(j*boxWidth), (int)(i*boxHeight), (int)boxWidth, (int)boxHeight);
		}
	}

	public void notifyForUpdate(){
		this.repaint();
	}



	private class PanelMouseListener extends MouseAdapter{

		@Override
		public void mousePressed(MouseEvent e) {
			if (!mainWindow.getModel().isEditable()) {return;}

			int j =(int)(e.getX()/boxWidth), i = (int)(e.getY()/boxHeight);
			mainWindow.getModel().getMaze().change(i,j);
			mainWindow.getModel().setSaved(false);
			mainWindow.getModel().refresh();
		}

	}
}
