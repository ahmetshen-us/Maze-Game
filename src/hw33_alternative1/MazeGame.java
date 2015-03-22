package hw33_alternative1;

import javax.swing.JFrame;

public class MazeGame {

	public static void main(String[] args) {
		new MazeGame();
	}

	/*
	 * Constructs the MazeGame
	 */
	public MazeGame() {
		JFrame f = new JFrame();
		f.setTitle("Maze Game");
		f.add(new MazePanel());

		f.setSize(740, 760);

		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
