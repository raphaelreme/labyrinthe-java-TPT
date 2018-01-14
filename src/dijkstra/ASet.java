package dijkstra;

import java.util.HashSet;

import interfaces.ASetInterface;
import interfaces.VertexInterface;

/*
 * Implémentation de ASetInterface avec un HashSet.
 */
class ASet implements ASetInterface {
	
	private HashSet<VertexInterface> set = new HashSet<VertexInterface>();
	
	@Override
	public boolean isIn(VertexInterface v) {
		return set.contains(v);
	}

	@Override
	public void add(VertexInterface v) {
		set.add(v);
	}
	
}
