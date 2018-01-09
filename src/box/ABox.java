package box;

public class ABox extends MBox{
	public ABox(Integer i, Integer j){
		super(i,j);
		letter = "A";
	}
	public boolean pass() {
		return true;
	}
}