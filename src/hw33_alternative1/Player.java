package hw33_alternative1;

public class Player {
	// Create needed fields
	private int tileX, tileY;

	/*
	 * Constructs the Player
	 */
	public Player() {

		tileX = 1;
		tileY = 1;
	}

	/*
	 * @return returns the X Position
	 */
	public int getTileX() {
		return tileX;
	}

	/*
	 * @return returns the Y Position
	 */
	public int getTileY() {
		return tileY;
	}

	/*
	 * Moves the Player
	 * 
	 * @param dx change in x direction
	 * 
	 * @param dy change in y direction
	 */
	public void move(int dx, int dy) {

		tileX += dx;
		tileY += dy;
	}
}
