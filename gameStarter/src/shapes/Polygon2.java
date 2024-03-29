//package shapes;
//
//import java.awt.Color;
//import java.awt.Polygon;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Polygon2 extends Polygon {
//
//	private static final long serialVersionUID = 1L;
//	private boolean fill;
//	private Color c;
//
//	public Polygon2(String line) {
//		Scanner sc = new Scanner(line);
//		sc.useDelimiter(",\\s*");
//		String type = sc.next();
//		if (type.charAt(0) == 'f')// filled/unfilled polygon
//			fill = true;
//		c = new Color(sc.nextInt()); // fill color
//
//		ArrayList<Integer> xpointsPolygon = new ArrayList<Integer>();
//		ArrayList<Integer> ypointsPolygon = new ArrayList<Integer>();
//		while (sc.hasNextInt()) {
//			xpointsPolygon.add(sc.nextInt());
//			ypointsPolygon.add(sc.nextInt());
//		}
//		npoints = xpointsPolygon.size();
//		for (int i = 0; i < xpointsPolygon.size(); i++) {
//			xpoints[i] = xpointsPolygon.get(i);
//		}
//		for (int i = 0; i < ypointsPolygon.size(); i++) {
//			ypoints[i] = ypointsPolygon.get(i);
//		}
//		sc.close();
//	}
//
//	public Polygon2(int[] xpoints, int[] ypoints, int npoints, Color c) {
//		super(xpoints, ypoints, npoints);
//		this.c = c;
//	}
//
//	public Polygon2(int[] xpoints, int[] ypoints, int npoints, Color c, boolean fill) {
//		super(xpoints, ypoints, npoints); 
//		this.fill = fill;
//		this.c = c;
//	}
//
//	public String toString() {
//		String typeColor = (fill ? "filledPolygon," : "polygon,") + c.getRGB();
//		String xy = "";
//		for (int i = 0; i < npoints; i++) {
//			xy += "," + xpoints[i] + "," + ypoints[i];
//		}
//		return String.format("%s%s%n", typeColor, xy);
//	}
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Polygon2 p = new Polygon2("filledPolygon, -16776961, 100, 550, 150, 550, 125, 580"); // fix dimensions
//		Polygon2 p1 = new Polygon2("polygon, -16776961, 100, 550, 150, 550, 125, 580");// fix dimensions
//		System.out.print(p);
//		System.out.print(p1);
//	}
//
//	public Color getDrawColor() {
//		return c;
//	}
//
//	public boolean isFill() {
//		return fill;
//	}
//	
//	
//
//}
//
package shapes;

import java.awt.Color;
import java.awt.Polygon;

public class Polygon2 extends Polygon {

	private static final long serialVersionUID = 1L;
	private Color c;
	private boolean fill;

	public Polygon2(String data) {
//		data.strip();
		String[] d = data.split(",\\s*");
		fill = d[0].charAt(0) == 'f' ? true : false;
		c = new Color(Integer.parseInt(d[1]));
		npoints = (d.length - 2) / 2;
		xpoints = new int[npoints];
		ypoints = new int[npoints];
		for (int i = 0, j = 2; i < npoints; i++, j++) {
			xpoints[i] = Integer.parseInt(d[j++]);
			ypoints[i] = Integer.parseInt(d[j]);
		}
	}

	public Polygon2(int[] xpoints, int[] ypoints, int npoints, Color color, boolean fill2) {
		super(xpoints, ypoints, npoints);
		c = color;
		fill = fill2;
	}

	public String toString() {
		String typeColor = (fill ? "filledPolygon," : "polygon,") + c.getRGB();
		String xy = "";
		for (int i = 0; i < npoints; i++) {
			xy += "," + xpoints[i] + "," + ypoints[i];
		}
		return String.format("%s%s%n", typeColor, xy);
	}

	public boolean isFill() {
		return fill;

	}

	public Color getDrawColor() {
		return c;

	}

	public static void main(String[] args) {
		Polygon2 p = new Polygon2("filledPolygon, -16776961, 475, 325, 48, 48");
		Polygon2 p1 = new Polygon2("polygon, -16711936, 375, 425, 47, 47, 50, 50");
		System.out.print(p);
		System.out.print(p1);
	}

}