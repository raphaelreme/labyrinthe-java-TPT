package interfaces;

import java.util.ArrayList;



/*
 * Interface modélisant des graphes (orientés ou non) positifs
 */
public interface GraphInterface {
	
	
	/*
	 * Renvoie la liste des sommets du graphe.
	 * L'ordre est quelconque.
	 */
	public ArrayList<VertexInterface> getVertices();
	
	
	
	/*
	 * Renvoie la liste des sommets voisins du sommet v.
	 * L'ordre est quelconque.
	 */
	public ArrayList<VertexInterface> getNext(VertexInterface v);
	
	
	
	/*
	 * Renvoie le nombre de sommet du graphe.
	 */
	public int getSize();
	
	
	
	/* Cette fonction permet d'obtenir le poids
	 * de l'arête (x,y) dans le graphe.
	 * Si il n'y a pas d'arête, elle renvoie -1.
	 */
	public int getWeight(VertexInterface x, VertexInterface y);
}
