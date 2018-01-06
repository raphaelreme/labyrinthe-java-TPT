package dijkstra;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import exception.MazeReadingException;
import box.ABox;
import box.DBox;
import box.EBox;
import box.MBox;
import box.WBox;
import interfaces.GraphInterface;
import interfaces.VertexInterface;


public class Maze implements GraphInterface {
	
	private int length;
	private int width;
	
	//deux modeles en parallèle pour une question de performances dans l'algo de dijkstra et de commodité pour représenter un labyrinthe
	private ArrayList<ArrayList<MBox>> matrice;
	private ArrayList<VertexInterface> listeSommets;
	
	public Maze() {
		length = 0;
		width = 0;
		matrice = null;
		listeSommets = null;
		}
	
	public int getSize() {
		return length*width;
	}
	
	public int getLength(){
		return length;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getWeight(VertexInterface x, VertexInterface y){
		
		//Normalement dans l'utilisation faite par Dijkstra cela n'est jamais le cas ! (x et y sont tjs voisins)
		if (!getNext(x).contains(y)){
			return -1;
		}
		
		return 1;
	}
	
	public ArrayList<VertexInterface> getVertices() {
		//performances ici : ne pas avoir à retranscrire la matrice en liste à chaque appel !
		return listeSommets;
	}

	//dans les 3 fonctions qui suivent il est plus pratique d'avoir une matrice plutot qu'une grosse liste, d'où la modélisation double
	public ArrayList<VertexInterface> getNext(VertexInterface v) {	
		MBox box = (MBox) v;
		int i = box.getI();
		int j = box.getJ();
		ArrayList<VertexInterface> next = new ArrayList<VertexInterface>();
		
		if (i-1>=0 && matrice.get(i-1).get(j).pass())
			next.add(matrice.get(i-1).get(j));
		
		if (i+1<length && matrice.get(i+1).get(j).pass())
			next.add(matrice.get(i+1).get(j));
		
		if (j+1<width && matrice.get(i).get(j+1).pass())
			next.add(matrice.get(i).get(j+1));
		
		if (j-1>=0 && matrice.get(i).get(j-1).pass())
			next.add(matrice.get(i).get(j-1));
		
		
		return next;
		
		
	}
	
	/*
	 * Crée le maze à partir d'un fichier texte
	 * Si le pointeur passé en parametre est null, le maze est réinitialisé
	 */
	public final void initFromTextFile(File file) throws MazeReadingException{
		if (file == null){
			length = 0;
			width = 0;
			matrice = null;
			listeSommets = null;
			return;
		}
		
		BufferedReader text = null;
		try{
			text = new BufferedReader(new FileReader(file));
			this.matrice = new ArrayList<ArrayList<MBox>>();
			this.listeSommets = new ArrayList<VertexInterface>();
			
			String ligne = text.readLine();
			if (ligne == null) {
				throw new MazeReadingException(file.getName(),-1,"Empty file");
			}
			final int m = ligne.length();
			if (m == 0){
				throw new MazeReadingException(file.getName(),0,"Empty ligne");
			}
			
			int i = 0;
			
			
			while (ligne != null) {
				if (m!=ligne.length()) {
					throw new MazeReadingException(file.getName(),i,"Different width");
				}
				ArrayList<MBox> tmp = new ArrayList<MBox>(m);
				for (int j=0; j<m; j++){
					char charj = ligne.charAt(j);
					if (charj == 'E'){
						MBox b = new EBox(this,i,j);
						tmp.add(b);
						listeSommets.add(b);
					} else if (charj == 'D'){
						MBox b = new DBox(this,i,j);
						tmp.add(b);
						listeSommets.add(b);
					} else if (charj == 'W'){
						MBox b = new WBox(this,i,j);
						tmp.add(b);
						listeSommets.add(b);
					} else if (charj == 'A'){
						MBox b = new ABox(this,i,j);
						tmp.add(b);
						listeSommets.add(b);
					} else {
						throw new MazeReadingException(file.getName(),i,"Invalide letter");
					}
				}
				matrice.add(tmp);
				ligne = text.readLine();
				i++;
				
			}
			width = m;
			length = i;
		} catch (FileNotFoundException e){
			System.out.print("File not found");
			System.exit(0);
		} catch (IOException e){
			e.printStackTrace();
			System.exit(0);
		} finally {
			try {text.close();}
			catch (Exception e) {}
		}
		
	}
	
	public final void saveToTextFile(File file){
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
			for (ArrayList<MBox> ligne : matrice){
				String chaine = "";
				for (MBox box : ligne){
					chaine += box.getLetter();
				}
				pw.println(chaine);
			}
		}catch (IOException e){
			e.printStackTrace();
			System.exit(0);
		}finally {
			try {pw.close();}
			catch (Exception e){}
		}
	}
	
	public VertexInterface getStart(){
		//Renvoie la case de départ (la 1ere trouvee)
		
		for (ArrayList<MBox> ligne : matrice){
			for (MBox box : ligne){
				if (box.getLetter() == "D")
					return box;
			}
		}
		return null;
	}
	
	public VertexInterface getEnd(){
		//Renvoie la case de d'arrivee
		for (ArrayList<MBox> ligne : matrice){
			for (MBox box : ligne){
				if (box.getLetter() == "A")
					return box;
			}
		}
		return null;
	}
	
	public String getLetter(int i, int j){
		return ((MBox) matrice.get(i).get(j)).getLetter();
	}
}


