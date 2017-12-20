package dijkstra;
import java.io.BufferedReader;
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
	
	public int getWeight(VertexInterface x, VertexInterface y){
		
		//Normalement dans l'utilisation faite par Dijkstra cela n'est jamais le cas ! (x et y sont tjs voisins)
		if (!getNext(x).contains(y)){
			return -1;
		}
		
		return 1;
	}
	
	public ArrayList<VertexInterface> getVertices() {
		return listeSommets;
	}

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
	
	public final void initFromTextFile(String fileName){
		BufferedReader text = null;
		try{
			text = new BufferedReader(new FileReader(fileName));
			this.matrice = new ArrayList<ArrayList<MBox>>();
			this.listeSommets = new ArrayList<VertexInterface>();
			
			String ligne = text.readLine();
			if (ligne == null) {
				throw new MazeReadingException(fileName,-1,"Fichier vide");
			}
			final int m = ligne.length();
			int i = 0;
			
			
			while (ligne != null) {
				if (m!=ligne.length()) {
					throw new MazeReadingException(fileName,i,"Largeur inégale");
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
						throw new MazeReadingException(fileName,i,"Lettre invalide");
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
		}  catch (MazeReadingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(0);
		} finally {
			try {text.close();}
			catch (Exception e) {}
		}
		
	}
	
	public final void saveToTextFile(String Name){
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(Name);
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
}


