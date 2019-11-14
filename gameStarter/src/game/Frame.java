package game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame extends JFrame {
	private static final long serialVersionUID = 2721843806653873180L;

	public Frame(Game game, int w, int h) {
		super("Game Engine - Adventure; Action - Single, or Multiplayer");
		setMaximumSize(new Dimension(w, h));
		setMinimumSize(new Dimension(w, h));
		setPreferredSize(new Dimension(w, h));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(game);
		setResizable(false);
		setVisible(true);
		game.start();
	}

}
