package box;

public final class WBox extends MBox{
	public WBox(Integer i, Integer j){
		super(i,j);
		letter = "W";
	}
	public boolean pass() {
		return false;
	}
}