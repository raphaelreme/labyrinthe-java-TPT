package box;

import dijkstra.Maze;

public class WBox extends MBox{
	public WBox(Maze m, int i, int j){
		super(m,i,j);
		letter = "W";
	}
	public boolean pass() {
		return false;
	}
}