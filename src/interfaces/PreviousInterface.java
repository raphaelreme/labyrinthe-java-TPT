package interfaces;



/*
 * Défini le père d'un sommet v dans l'arborescence obtenues par dijkstra.
 */
public interface PreviousInterface {
	public VertexInterface getPrev(VertexInterface v);
	public void setPrev(VertexInterface v, VertexInterface prev);
}
