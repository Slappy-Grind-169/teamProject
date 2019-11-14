package shapes;

import java.awt.Color;
import java.awt.geom.Line2D.Double;

public class Line extends Double {

	private static final long serialVersionUID = 1L;
	private Color c;

	public Line(int x1, int y1, int x2, int y2, Color c) {
		super(x1, y1, x2, y2);
		this.c = c;
	}

	public Line(String line) {
		String[] lines = line.split(",\\s*");
		x1 = Integer.parseInt(lines[0]);
		y1 = Integer.parseInt(lines[1]);
		x2 = Integer.parseInt(lines[2]);
		y2 = Integer.parseInt(lines[3]);
		int rgb = Integer.parseInt(lines[4].strip());
		c = new Color(rgb);

	}

	public Color getDrawColor() {
		return c;
	}

	public String toString() {
		return String.format("%d, %d, %d, %d, %d%n", (int) x1, (int) y1, (int) x2, (int) y2, c.getRGB());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

