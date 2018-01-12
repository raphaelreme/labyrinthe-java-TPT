package graphique;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import controller.MazeController;
import modele.MazeModel;

public final class Frame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private final MazeModel model;
	private final MazeController controller;
	
	private final Panel contentPanel;
	private final MenuBar menuBar;


	public Frame(MazeModel model, MazeController controller){
		this.model = model;
		this.controller = controller;
		
		this.setTitle("Maze");
		this.setIconImage(model.getStart());
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
			this.setTitle("Maze - " + model.getFile().getName());
		} else {
			this.setTitle("Maze");
		}
	}
	
	public MazeModel getModel(){
		return model;
	}
	
	public MazeController getController(){
		return controller;
	}

	@Override
	public void dispose(){
		if ((new Saver(this)).save()==0)
			super.dispose();
	}
}
