package interfaces;
 
/*
 * interface d'un ensemble simple :
 * on ne peux que lui ajouter des éléments et savoir si un élément est dedans.
 */
public interface ASetInterface {
	public boolean isIn(VertexInterface v);
	public void add(VertexInterface v);
}