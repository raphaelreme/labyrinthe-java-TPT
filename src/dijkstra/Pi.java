package dijkstra;

import java.util.Hashtable;

import interfaces.PiInterface;
import interfaces.VertexInterface;

final class Pi implements PiInterface{

	Hashtable<VertexInterface,Integer> t = new Hashtable<VertexInterface,Integer>();
	
	
	public int getPi(VertexInterface v) {
		return t.get(v);
	}

	public void setPi(VertexInterface v, int p) {
		t.put(v, new Integer(p));
	}

}
