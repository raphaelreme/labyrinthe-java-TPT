package interfaces;


/*
 * Défini le poids minimum d'un chemin de la racine à un sommet v
 */
public interface PiInterface {
	public int getPi(VertexInterface v);
	public void setPi(VertexInterface v, int p);
}
