package interfaces;


/*
 * D�fini le poids minimum des chemins allant de la racine � un sommet v
 */
public interface PiInterface {
	public int getPi(VertexInterface v);
	public void setPi(VertexInterface v, int p);
}
