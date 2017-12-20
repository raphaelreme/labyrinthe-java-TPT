package graphique;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class Panel extends JPanel{

	private static final long serialVersionUID = 1L;
	
		private Frame MainWindow;
		private LayoutManager lm;
	
	
	public Panel(Frame f){
		super();
		MainWindow = f;
		lm = new GridLayout();
		
	}
	public void paintComponent(Graphics g){
		g.drawImage(MainWindow.getModel().getBackground(), 0, 0 , this.getWidth(), this.getHeight(),this);
		drawGraph(g);
	}
	
	private void drawGraph(Graphics g){
		
	}
}
