package exception;

/*
 * Cette Exception est levée en cas d'erreur dans la forme du fichier texte contenant le Maze.
 */
public class MazeReadingException extends Exception{
	private static final long serialVersionUID = 1L;

	public MazeReadingException(String file, int ligne, String msg){
		super(msg + " at ligne " + ligne + " in file : " + file);
	}
}
