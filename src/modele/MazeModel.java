package modele;

import graphique.Frame;
import interfaces.PreviousInterface;
import interfaces.VertexInterface;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.imageio.ImageIO;

import box.MBox;
import dijkstra.ASetWithOrder;
import dijkstra.Dijkstra;
import dijkstra.Maze;
import exception.MazeReadingException;

/*
 * Contient les données du projet.
 * Permet la modification des données et prévient la Frame qui elle-même transmet le message à ses composants)
 */
public class MazeModel extends Observable {

	private Image background;
	private Image wall;
	private Image start;
	private Image arrival;
	private boolean editable;
	private boolean saved;
	
	private File file;
	private Maze m= new Maze();
	private ArrayList<int[]> dijkstra;
	private int speed;
	

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
		dijkstra = new ArrayList<int[]>();
		speed = 0;
		
		/*
		file = "file/labyrinthe2.txt";
		m.initFromTextFile(file);
		PreviousInterface d = Dijkstra.dijkstra(m, m.getStart(),new ASetWithOrder());
		VertexInterface v = m.getEnd();
		
		while (d.getPrev(v) != m.getStart()){
			v = d.getPrev(v);
			MBox m = (MBox)v;
			int[] tab = {m.getI(),m.getJ()};
			dijkstra.add(tab);
		}
		*/
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
	public void setEditable(boolean b){
		editable = b;
	}	
	public void setFile(File f) throws MazeReadingException{
		//on essaye d'abord d'initialiser le maze avant de changer le fichier
		m.initFromTextFile(f); 
		file = f;
	}	
	public void setSpeed(int n){
		speed = n;
	}
	
	
	
	public void save(File f){
		m.saveToTextFile(f);
	}
	
	
	
	public void refresh(){
		this.setChanged();
		this.notifyObservers();
	}
	
	


	
	
	
	
	
}
