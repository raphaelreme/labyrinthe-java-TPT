package interfaces;



/*
 * D�fini le p�re (Prev) d'un sommet v dans les chemins obtenus par dijkstra.
 */
public interface PreviousInterface {
	public VertexInterface getPrev(VertexInterface v);
	public void setPrev(VertexInterface v, VertexInterface prev);
}
