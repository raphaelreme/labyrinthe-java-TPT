package box;

import dijkstra.Maze;

public class DBox extends MBox{
	public DBox(Maze m, int i, int j){
		super(m,i,j);
		letter = "D";
	}
	public boolean pass() {
		return true;
	}
}
