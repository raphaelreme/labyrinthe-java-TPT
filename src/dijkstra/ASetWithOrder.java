package dijkstra;

import interfaces.VertexInterface;

import java.util.ArrayList;

/*
 * N'a pas d'interet pour dijkstra 
 * mais sert à afficher dans l'ordre les sommets qui ont été parcourus par l'algorithme
 */
public class ASetWithOrder extends ASet{
	
	private ArrayList<VertexInterface> list = new ArrayList<VertexInterface>();
	
	public void add(VertexInterface v){
		super.add(v);
		list.add(v);
	}
	
	public ArrayList<VertexInterface> getList(){ return list; }

}
