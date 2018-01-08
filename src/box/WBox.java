package box;

import dijkstra.Maze;

public class WBox extends MBox{
	public WBox(Maze m, Integer i, Integer j){
		super(m,i,j);
		letter = "W";
	}
	public boolean pass() {
		return false;
	}
}