package dijkstra;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import exception.MazeReadingException;
import box.ABox;
import box.DBox;
import box.EBox;
import box.MBox;
import box.WBox;
import interfaces.GraphInterface;
import interfaces.VertexInterface;

/*
 * Impl�mentation de GraphInterface pour un labyrinthe.
 * D�doublement des donn�es pour augmenter l'efficacit� de l'algorithme de dijkstra.
 */
public final class Maze implements GraphInterface {
	
	private int length;
	private int width;
	
	//deux modeles en parall�le pour une question de performances dans l'algo de dijkstra et de commodit� pour repr�senter un labyrinthe
	private ArrayList<ArrayList<MBox>> matrice;
	private ArrayList<VertexInterface> listeSommets;
	
	public Maze() {
		emptyInit();
		}
	
	@Override
	public int getSize() {
		return length*width;
	}
	
	public int getLength(){
		return length;
	}
	
	public int getWidth(){
		return width;
	}
	
	@Override
	public int getWeight(VertexInterface x, VertexInterface y){
		
		//Normalement dans l'utilisation faite par Dijkstra cela n'est jamais le cas ! (x et y sont tjs voisins)
		if (!getNext(x).contains(y)){
			return -1;
		}
		
		return 1;
	}
	
	@Override
	public ArrayList<VertexInterface> getVertices() {
		//performances ici : ne pas avoir � retranscrire la matrice en liste � chaque appel !
		return listeSommets;
	}

	//dans les fonctions qui suivent il est plus pratique d'avoir une matrice plutot qu'une grosse liste, d'o� la mod�lisation double
	@Override
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
	
	public void emptyInit(){
		emptyInit(10,10);
	}
	
	public void emptyInit(int length, int width){
		this.length = length;
		this.width = width;
		this.matrice = new ArrayList<ArrayList<MBox>>(length);
		this.listeSommets = new ArrayList<VertexInterface>(length*width);
		
		for (int i=0; i<length; i++){
			ArrayList<MBox> tmp = new ArrayList<MBox>(width);
			for(int j=0; j<width; j++){
				EBox e = new EBox(i,j);
				tmp.add(e);
				this.listeSommets.add(e);
			}
			this.matrice.add(tmp);
		}
	}
	
	
	
	/*
	 * Cr�e le maze � partir d'un fichier texte
	 * Si le pointeur pass� en parametre est null, le maze est reinitialis�
	 */
	public void initFromTextFile(File file) throws MazeReadingException{
		if (file == null){
			emptyInit();
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
						MBox b = new EBox(i,j);
						tmp.add(b);
						listeSommets.add(b);
					} else if (charj == 'D'){
						MBox b = new DBox(i,j);
						tmp.add(b);
						listeSommets.add(b);
					} else if (charj == 'W'){
						MBox b = new WBox(i,j);
						tmp.add(b);
						listeSommets.add(b);
					} else if (charj == 'A'){
						MBox b = new ABox(i,j);
						tmp.add(b);
						listeSommets.add(b);
					} else {
						throw new MazeReadingException(file.getName(),i,"Invalid letter");
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
	
	public void saveToTextFile(File file){
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
	
	/*
	 * Renvoie la case de d�part (la 1ere trouvee)
	 */
	public VertexInterface getStart(){
		for (ArrayList<MBox> ligne : matrice){
			for (MBox box : ligne){
				if (box.getLetter() == "D")
					return box;
			}
		}
		return null;
	}
	
	/*
	 * Renvoie la case de d'arrivee
	 */
	public VertexInterface getEnd(){
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

	/*
	 * Change la box en i,j par une autre.
	 */
	public void change(int i, int j){
		/* Pas top, trop de code pour une petite modification � cause des diff�rentes classes de Box et
		 * du double mod�le.
		 */
		MBox box = matrice.get(i).get(j);
		MBox newBox = null;

		int k = listeSommets.indexOf(box);

		ArrayList<Class<?>> tab = new ArrayList<Class<?>>();
		tab.add(DBox.class); tab.add(ABox.class); tab.add(EBox.class); tab.add(WBox.class); tab.add(DBox.class);

		Class<?> c = tab.get(tab.indexOf(box.getClass())+1);
		Class<?>[] types = {Integer.class,Integer.class};

		try {
			Constructor<?> ct = c.getConstructor(types);
			newBox = (MBox)ct.newInstance(i,j);

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		matrice.get(i).set(j, newBox);
		listeSommets.set(k, newBox);
	}

	public boolean isValid(){
		/*
		 * Un maze ne peut qu'�tre cr�e via les methode init du maze 
		 * Ce qui r�duit les tests � faire.
		 */
		
		//1er cas : pas de Maze
		if (length == 0){
			return false;
		}
		
		//1 case d�part et 1 case arriv�e.
		int c1=0, c2=0;
		for (ArrayList<MBox> ligne : matrice){
			for (MBox box : ligne){
				if (box.getLetter() == "D"){
					c1++;
					if (c1 == 2) {
						return false;
					}
				}
				if (box.getLetter() == "A"){
					c2++;
					if (c2 == 2) {
						return false;
					}
				}
			}
		}
		
		if (c1 == 0 || c2 ==0){
			return false;
		}
		return true;
	}
	
}


