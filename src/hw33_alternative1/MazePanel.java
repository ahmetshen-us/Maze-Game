package hw33_alternative1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**

 * 
 */
public class MazePanel extends JPanel implements ActionListener {
	// Create all the fields needed
	private Timer timer;
	private final int TIME = 120000;
	private Timer t;
	private Player p;
	private int point = 0;
	private String message = " ";
	private Image apple;
	private final int WIDTH = 20;
	private final int MAX_ROW = 36; // 36 // adjust this value if you change #
									// of rows/columns
	private int[][] maze = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 1, 1, 1, 3, 0, 3, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0,
					0, 0, 1, 1, 1, 0, 0, 3, 0, 0, 1, 1, 1, 1 },
			{ 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0,
					1, 0, 3, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1 },
			{ 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0,
					1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1 },
			{ 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0,
					3, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0,
					1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1 },
			{ 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0,
					1, 1, 0, 3, 0, 0, 0, 1, 0, 3, 0, 1, 1, 1 },
			{ 1, 3, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1,
					1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1,
					0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1 },
			{ 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0,
					1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1 },
			{ 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0,
					1, 1, 0, 0, 1, 1, 1, 1, 3, 0, 1, 0, 1, 1 },
			{ 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0,
					1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1 },
			{ 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1,
					1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 3, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1,
					1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1,
					1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 0, 1, 1, 0, 3, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1,
					1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1 },
			{ 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1,
					1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1 },
			{ 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1,
					1, 1, 3, 0, 0, 1, 1, 1, 3, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1,
					1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 2 },
			{ 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1,
					3, 3, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1 },
			{ 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1,
					0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
			{ 1, 1, 1, 0, 1, 1, 0, 3, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1,
					0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1 },
			{ 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0,
					3, 1, 1, 1, 0, 1, 1, 1, 3, 1, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 3, 0, 0, 1, 1, 1, 0, 1,
					1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1,
					1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1 },
			{ 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0,
					0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
			{ 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1,
					1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1 },
			{ 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 3, 1, 1, 1,
					0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0,
					0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 0, 3, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0,
					1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0,
					1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0,
					1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0,
					0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 3, 0, 0, 1, 1, 1,
					1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	/*
	 * Constructs the MazePanel
	 */
	public MazePanel() {

		addKeyListener(new AL());
		setFocusable(true);
		ImageIcon img = new ImageIcon(this.getClass().getResource("apple.jpg"));
		apple = img.getImage();
		timer = new Timer(25, this);
		timer.start();

		t = new Timer(TIME, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				message = "YOU LOST";

			}
		});
		t.start();

		p = new Player();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		super.paint(g);
		for (int y = 0; y < MAX_ROW; y++) {
			for (int x = 0; x < MAX_ROW; x++) {
				if (getMap(x, y) == 0 || getMap(x, y) == 2) {
					g.setColor(Color.GREEN);
					g.fill3DRect(x * WIDTH, y * WIDTH, WIDTH, WIDTH, true);
				} else if (getMap(x, y) == 1) {
					g.setColor(Color.DARK_GRAY);
					g.fill3DRect(x * WIDTH, y * WIDTH, WIDTH, WIDTH, true);
				} else if (getMap(x, y) == 3) {
					g.drawImage(apple, x * WIDTH, y * WIDTH, null);

				}
			}
		}
		if (getMap(p.getTileX(), p.getTileY()) == 3) {
			// score.setText("Score: " + (point += 5));
			// System.out.println("Score: " + (point += 5));
			point += 5;

			maze[p.getTileY()][p.getTileX()] = 0;

		}

		g.setColor(Color.BLUE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		g.drawString(message, this.getWidth() / 2, this.getHeight() / 2);

		g.setColor(Color.WHITE);
		g.fillOval(p.getTileX() * WIDTH, p.getTileY() * WIDTH, WIDTH, WIDTH);
		g.setColor(Color.BLACK);
		g.fillOval(p.getTileX() * WIDTH + WIDTH / 4, p.getTileY() * WIDTH
				+ WIDTH / 4, WIDTH / 6, WIDTH / 6);
		g.fillOval(p.getTileX() * WIDTH + WIDTH / 2, p.getTileY() * WIDTH
				+ WIDTH / 4, WIDTH / 6, WIDTH / 6);

		g.drawArc(p.getTileX() * WIDTH + WIDTH / 4, p.getTileY() * WIDTH
				+ WIDTH / 4, WIDTH / 2, WIDTH / 2, 190, 160);

	}

	/*
	 * Returns the current position
	 * 
	 * @param x: x position
	 * 
	 * @param y: y position
	 * 
	 * @return return the the current position in the 2d array
	 */
	private int getMap(int x, int y) {
		try {
			return maze[y][x];
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		return 1;

	}

	/*
	 * Returns the point
	 */
	public int getPoint() {
		return point;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (getMap(p.getTileX(), p.getTileY()) == 2) {

			message = "YOU WIN";
		}

		repaint();

	}

	/*
	 * This class handles the key events
	 */
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();

			if (keyCode == KeyEvent.VK_UP) {
				if (!(getMap(p.getTileX(), p.getTileY() - 1) == 1))
					p.move(0, -1);

			} else if (keyCode == KeyEvent.VK_DOWN) {
				if (!(getMap(p.getTileX(), p.getTileY() + 1) == 1))
					p.move(0, 1);
			} else if (keyCode == KeyEvent.VK_LEFT) {
				if (!(getMap(p.getTileX() - 1, p.getTileY()) == 1))
					p.move(-1, 0);
			} else if (keyCode == KeyEvent.VK_RIGHT) {
				if (!(getMap(p.getTileX() + 1, p.getTileY()) == 1))
					p.move(1, 0);
			}

		}

	}
}