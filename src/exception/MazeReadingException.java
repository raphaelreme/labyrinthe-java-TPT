package exception;

public class MazeReadingException extends Exception{
	private static final long serialVersionUID = 1L;

	public MazeReadingException(String file, int ligne, String msg){
		super(msg + " in ligne " + ligne + " in file : " + file);
	}
}
