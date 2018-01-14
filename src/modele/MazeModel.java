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
 * Contient les données du projet.
 * Permet la modification des données et prévient la Frame qui elle-même transmet le message à ses composants)
 */
public final class MazeModel extends Observable {

	private final Image background;
	private final Image wall;
	private final Image start;
	private final Image arrival;
	
	private boolean running;
	private boolean editable;
	private boolean saved;
	private boolean paused;
	
	private File file;
	private final Maze maze = new Maze();
	private ArrayList<int[]> dijkstra;
	private int speed;
	

	public MazeModel(){
		/*
		 * Les images ne sont chargées qu'une seule fois : ici. Cela permet de modifier les fichiers facilement.
		 */
		start = loadImage("depart.png");
		arrival = loadImage("arrivee.png");
		background = loadImage("fond.jpg");
		wall = loadImage("mur.png");
		
		running = false;
		editable = false;
		saved = true;
		paused = false;
		
		file = null;
		dijkstra = new ArrayList<int[]>();
		speed = 0;
	}
	
	
	/*
	 * Chargement des images situées dans file.
	 */
	private static Image loadImage(String nom){
		Image img = null;
		try{
			img = ImageIO.read(new File("file/"+nom));
		} catch (IOException e){
			e.printStackTrace();
			System.out.println("Erreur dans le chargement des images");
			System.exit(0);
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
	public void setSaved(boolean b) {
		saved = b;
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
	
	public void changeSize(int length, int width){
		maze.emptyInit(length,width);
	}
	
	public void changeMaze(int i, int j){
		maze.change(i,j);
	}
	
	
	
	public void refresh(){
		this.setChanged();
		this.notifyObservers();
	}
	
	
}
