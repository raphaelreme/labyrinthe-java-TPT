package box;

public final class EBox extends MBox{
	public EBox(Integer i, Integer j){
		super(i,j);
		letter = "E";
	}
	public boolean pass() {
		return true;
	}
}