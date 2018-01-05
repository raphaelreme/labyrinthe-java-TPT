package dijkstra;

import java.util.HashSet;

import interfaces.ASetInterface;
import interfaces.VertexInterface;

public class ASet implements ASetInterface {
	
	private HashSet<VertexInterface> set = new HashSet<VertexInterface>();
	
	public boolean isIn(VertexInterface v) {
		return set.contains(v);
	}

	public void add(VertexInterface v) {
		set.add(v);
	}
	
}
