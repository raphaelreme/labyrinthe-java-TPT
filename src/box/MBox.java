package box;

import interfaces.VertexInterface;
import dijkstra.Maze;

public abstract class MBox implements VertexInterface {
	private int i;
	private int j;
	private Maze m;
	protected String letter;
	
	public MBox(Maze m, Integer i, Integer j) {
		this.m = m;
		this.i = i.intValue();
		this.j = j.intValue();
	}
	
	public abstract boolean pass();
	
	public String getLabel(){
		return "" + i + ", " +j;
	}
	
	public int getI(){
		return i;
	}
	
	public int getJ(){
		return j;
	}
	
	public String getLetter(){
		return letter;
	}
}
