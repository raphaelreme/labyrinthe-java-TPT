package graphique;



import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import modele.MazeModel;

public class Frame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	MazeModel model = new MazeModel();
	
	Panel contentPanel;
	MenuBar menuBar;


	public Frame(){
		this.setTitle("Maze");
		this.setIconImage(model.getStart());
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPanel = new Panel(this);
		menuBar = new MenuBar(this);
		
		model.addObserver(this);
		
		
		this.setJMenuBar(menuBar);
		this.setContentPane(contentPanel);
		this.setVisible(true);
		
		
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		contentPanel.notifyForUpdate();
		menuBar.notifyForUpdate();
		//Rajout du nom de fichier courant au titre
		if (model.getFile()!=null){
			this.setTitle("Maze - " + model.getFile().substring(model.getFile().lastIndexOf("\\") + 1));
		}
	}
	
	public MazeModel getModel(){
		return model;
	}
	
}
