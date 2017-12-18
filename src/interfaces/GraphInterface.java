package interfaces;

import java.util.ArrayList;

public interface GraphInterface {
	public ArrayList<VertexInterface> getVertices();
	public ArrayList<VertexInterface> getNext(VertexInterface v);
	public int getSize();
	public int getDis(VertexInterface x, VertexInterface y);
}
