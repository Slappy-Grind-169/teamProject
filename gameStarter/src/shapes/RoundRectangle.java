package shapes;

import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.util.Scanner;

public class RoundRectangle extends RoundRectangle2D.Double {

	private static final long serialVersionUID = 1L;
	private boolean fill;
	private Color c;

//do not declare inherited fields	

	public RoundRectangle(int x, int y, int w, int h, int arcW, int arcH, Color c) {
		super(x, y, w, h, arcW, arcH);
		this.c = c;
	}

	public RoundRectangle(int x, int y, int w, int h, int arcW, int arcH, Color c, boolean fill) {
		this(x, y, w, h, arcW, arcH, c);
		this.fill = fill;
	}

	public RoundRectangle(String line) {
		Scanner sc = new Scanner(line);
		sc.useDelimiter(",\\s*");
		String type = sc.next();
		if (type.charAt(0) == 'f')
			fill = true;
		x = sc.nextInt();
		y = sc.nextInt();
		width = sc.nextInt();
		height = sc.nextInt();
		arcwidth = sc.nextInt();
		archeight = sc.nextInt();
		int rgb = sc.nextInt();
		c = new Color(rgb);
		sc.close();
	}

	public Color getDrawColor() {
		return c;
	}

	public boolean isFill() {
		return fill;
	}

	public String toString() {
		return String.format("%s, %d, %d, %d, %d, %d, %d, %d%n", (fill ? "filledRoundRectangle" : "RoundRectangle"),
				(int) x, (int) y, (int) width, (int) height, (int) arcwidth, (int) archeight, c.getRGB());
	}

	public static void main(String[] args) {
		RoundRectangle r = new RoundRectangle("filledRoundRectangle, 407, 247, 144, 109, 50, 50, -13369345"); // fix //
																												// dimensions
		System.out.print(r);

	}
}
