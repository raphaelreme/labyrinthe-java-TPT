package modele;

import graphique.Frame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

import dijkstra.Maze;

public class MazeModel extends Observable {

	private Image background;
	private Image wall;
	private Image start;
	private Image arrival;
	private boolean editable;
	private boolean saved;
	
	private String file;
	private Maze m= new Maze();
	
	
	
	public MazeModel(){
		/*
		 * Les images ne sont chargées qu'une seule fois : ici. Cela permet de modifier les noms des fichiers facilement.
		 */
		start = loadImage("depart.png");
		arrival = loadImage("arrivee.png");
		background = loadImage("fond.jpg");
		wall = loadImage("mur.png");
		editable = false;
		saved = false;
		
		file = null;	
	}
	
	
	/*
	 * Charge des images situées dans file.
	 */
	private static Image loadImage(String nom){
		Image img = null;
		try{
			img = ImageIO.read(new File("file/"+nom));
		} catch (IOException e){
			e.printStackTrace();
			System.out.println("Erreur dans le chargement des images");
		}
		return img;
	}
	
	
	//Getters
	public Image getBackground() {
		return background;
	}
	public Image getWall() {
		return wall;
	}
	public Image getStart() {
		return start;
	}
	public Image getArrival() {
		return arrival;
	}
	public boolean isEditable(){
		return editable;
	}
	public boolean isSaved(){
		return saved;
	}
	public Maze getM() {
		return m;
	}
	public String getFile() {
		return file;
	}
	
	
	public void changeFile(String f){
		file = f;
		initMaze();
	}
	
	public void initMaze(){
		m.initFromTextFile(file);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setEditable(boolean b){
		editable = b;
		this.setChanged();
		this.notifyObservers();
	}
	
	


	
	
	
	
	
}
