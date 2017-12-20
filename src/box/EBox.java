package box;

import dijkstra.Maze;

public class EBox extends MBox{
	public EBox(Maze m, int i, int j){
		super(m,i,j);
		letter = "E";
	}
	public boolean pass() {
		return true;
	}
}