package interfaces;


/*
 * D�fini le poids minimum d'un chemin de la racine � un sommet v
 */
public interface PiInterface {
	public int getPi(VertexInterface v);
	public void setPi(VertexInterface v, int p);
}
