package dijkstra;

import java.util.Hashtable;

import interfaces.PreviousInterface;
import interfaces.VertexInterface;

final class Previous implements PreviousInterface {
	
	Hashtable<VertexInterface,VertexInterface> t = new Hashtable<VertexInterface,VertexInterface>();

	public VertexInterface getPrev(VertexInterface v) {
		return t.get(v);
	}

	public void setPrev(VertexInterface v, VertexInterface prev) {
		t.put(v, prev);
	}

}
