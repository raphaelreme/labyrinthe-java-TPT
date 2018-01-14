package interfaces;


/*
 * Défini le poids minimum des chemins allant de la racine à un sommet v
 */
public interface PiInterface {
	public int getPi(VertexInterface v);
	public void setPi(VertexInterface v, int p);
}
