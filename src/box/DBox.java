package box;

public final class DBox extends MBox{
	public DBox(Integer i, Integer j){
		super(i,j);
		letter = "D";
	}
	public boolean pass() {
		return true;
	}
}
