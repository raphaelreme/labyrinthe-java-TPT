package box;

import interfaces.VertexInterface;

public abstract class MBox implements VertexInterface {
	private int i;
	private int j;
	protected String letter;
	
	public MBox(Integer i, Integer j) {
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
