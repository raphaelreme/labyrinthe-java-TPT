package dijkstra;

import interfaces.VertexInterface;

import java.util.ArrayList;

/*
 * N'a pas d'interet pour dijkstra,
 * mais sert � afficher dans l'ordre les sommets qui ont �t� parcourus par l'algorithme.
 */
public final class ASetWithOrder extends ASet{
	
	private ArrayList<VertexInterface> list = new ArrayList<VertexInterface>();
	
	@Override
	public void add(VertexInterface v){
		super.add(v);
		list.add(v);
	}
	
	public ArrayList<VertexInterface> getList(){
		return list; 
	}

}
