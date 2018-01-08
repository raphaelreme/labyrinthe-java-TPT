package modele;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.imageio.ImageIO;

import dijkstra.Maze;
import exception.MazeReadingException;

/*
 * Contient les donn�es du projet.
 * Permet la modification des donn�es et pr�vient la Frame qui elle-m�me transmet le message � ses composants)
 */
public class MazeModel extends Observable {

	private Image background;
	private Image wall;
	private Image start;
	private Image arrival;
	
	private boolean running;
	private boolean editable;
	private boolean saved;
	private boolean paused;
	
	private File file;
	private Maze maze = new Maze();
	private ArrayList<int[]> dijkstra;
	private int speed;
	

	public MazeModel(){
		/*
		 * Les images ne sont charg�es qu'une seule fois : ici. Cela permet de modifier les fichiers facilement.
		 */
		start = loadImage("depart.png");
		arrival = loadImage("arrivee.png");
		background = loadImage("fond.jpg");
		wall = loadImage("mur.png");
		
		running = false;
		editable = false;
		saved = false;
		paused = false;
		
		file = null;
		dijkstra = new ArrayList<int[]>();
		speed = 0;
	}
	
	
	/*
	 * Charge des images situ�es dans file.
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
	public boolean isRunning(){
		return running;
	}
	public boolean isEditable(){
		return editable;
	}
	public boolean isSaved(){
		return saved;
	}
	public boolean isPaused() {
		return paused;
	}
	public boolean isValidMaze(){
		return maze.isValid();
	}
	public Maze getMaze() {
		return maze;
	}
	public File getFile() {
		return file;
	}
	public ArrayList<int[]> getDijkstra(){
		return dijkstra;
	}
	public int getSpeed(){
		return speed;
	}
	
	
	//Setters
	public void setRunning(boolean b){
		running = b;
	}
	public void setEditable(boolean b){
		editable = b;
	}
	public void setPaused(boolean b) {
		paused = b;
	}
	public void setFile(File f) throws MazeReadingException{
		//on essaye d'abord d'initialiser le maze avant de changer le fichier
		maze.initFromTextFile(f); 
		file = f;
	}	
	public void setSpeed(int n){
		speed = n;
	}
	
	public void resetDijkstra(){
		dijkstra = new ArrayList<int[]>();
	}
	public void addDijkstra(int[] t){
		dijkstra.add(t);
	}
	

	
	
	
	public void save(File f){
		maze.saveToTextFile(f);
	}
	
	
	
	public void refresh(){
		this.setChanged();
		this.notifyObservers();
	}
	
	
}
