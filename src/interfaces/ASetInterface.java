package interfaces;
 
/*
 * interface d'un ensemble simple :
 * on ne peux que lui ajouter des �l�ments et savoir si un �l�ment est dedans.
 */
public interface ASetInterface {
	public boolean isIn(VertexInterface v);
	public void add(VertexInterface v);
}