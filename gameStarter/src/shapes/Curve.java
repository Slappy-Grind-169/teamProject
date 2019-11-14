package shapes;

import java.awt.Color;
import java.awt.geom.Path2D.Double;
import java.util.Scanner;
import java.awt.geom.PathIterator;

public class Curve extends Double {

	private static final long serialVersionUID = 1L;
	private Color c;

	public Curve(String line) {
		Scanner sc = new Scanner(line);
		sc.useDelimiter(",\\s*");
		sc.next();
		int rgb = sc.nextInt();
		c = new Color(rgb);
		moveTo(sc.nextInt(), sc.nextInt());//moving pen to x,y.
		while (sc.hasNextInt()) {//while more points
			lineTo(sc.nextInt(), sc.nextInt());
		}
		sc.close();
	}

	public Curve(int x, int y, Color c) {
		this.c = c;
		moveTo(x, y);
	}

	public Color getDrawColor() {
		return c;
	}

	public String toString() {
		String typeColor = "curve," + c.getRGB();
		String xy = "";
		PathIterator pathIterator = getPathIterator(null);
		double[] coordinates = new double[6];
		while (!pathIterator.isDone()) {
			int type = pathIterator.currentSegment(coordinates);
			if (type == PathIterator.SEG_LINETO || type == PathIterator.SEG_MOVETO) {
				int x = (int) coordinates[0];
				int y = (int) coordinates[1];
				xy += "," + x + "," + y;
			}
			pathIterator.next();
		}
		return String.format("%s%s%n", typeColor, xy);
	}

	public static void main(String[] args) {
		Curve c = new Curve("curve, -16711936, 50, 50, 150, 200, 350, 350, 350, 550");
		System.out.print(c);
	}

}
