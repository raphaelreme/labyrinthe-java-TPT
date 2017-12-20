package box;

import dijkstra.Maze;

public class ABox extends MBox{
	public ABox(Maze m, int i, int j){
		super(m,i,j);
		letter = "A";
	}
	public boolean pass() {
		return true;
	}
}