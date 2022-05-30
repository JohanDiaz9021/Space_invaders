package exceptions;



public class SpaceInNickException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public SpaceInNickException() {
		super("There are spaces in the name entered");
	}
}
