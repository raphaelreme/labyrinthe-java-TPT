package graphique;


import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import modele.MazeModel;

public class Frame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	MazeModel model = new MazeModel();
	Panel contentPanel;


	public Frame(){
		this.setTitle("Maze");
		this.setIconImage(model.getStart());
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPanel = new Panel(this);
		
		model.addObserver(this);
		
		
		this.setContentPane(contentPanel);

		this.setVisible(true);
		
		
	}


	@Override
	public void update(Observable arg0, Object arg1) {

		
		
	}
	
	public MazeModel getModel(){
		return model;
	}
	
}
