package game;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import shapes.Curve;
import shapes.Ellipse;
import shapes.Line;
import shapes.Player;
import shapes.Polygon2;
import shapes.Rectangle;
import shapes.RoundRectangle;

public class Handler {

	LinkedList<Player> movers;
	private ArrayList<Shape> shapesLevel1;
	private ArrayList<Shape> shapesLevel2;
	private ArrayList<Shape> shapesLevel3;
	private ArrayList<Shape> shapesLevel4;
	private ArrayList<Shape> shapes;
	private int level;

	public Handler() {
		movers = new LinkedList<Player>();
		shapesLevel1 = new ArrayList<Shape>();
		shapesLevel2 = new ArrayList<Shape>();
		shapesLevel3 = new ArrayList<Shape>();
		shapesLevel4 = new ArrayList<Shape>();
		loadLevels();
		level = 4;
		shapes = getShapes(level);
	}

	public void tick() {
		for (int i = 0; i < movers.size(); i++) {
			Player mover = movers.get(i);
			mover.tick();
		}
	}

	public Shape checkCollisions(Player m) {
		if (shapes.size() > 1) {
			Shape s = null;

			for (int i = 0; i < shapes.size(); i++) {
				s = shapes.get(i);
				if (s.intersects(m))
					return s;
			}
		}
		return null;
	}

	public void render(Graphics2D g2) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape s = shapes.get(i);
			if (s instanceof Line) {
				Line o = (Line) s;
				g2.setColor(o.getDrawColor());
			} else if (s instanceof Curve) {
				Curve o = (Curve) s;
				g2.setColor(o.getDrawColor());
			} else if (s instanceof Ellipse) {
				Ellipse o = (Ellipse) s;
				g2.setColor(o.getDrawColor());
				if (o.isFill())
					g2.fill(o);
			} else if (s instanceof Rectangle) {
				Rectangle o = (Rectangle) s;
				g2.setColor(o.getDrawColor());
				if (o.isFill())
					g2.fill(o);
			}else if (s instanceof RoundRectangle) {
				RoundRectangle o = (RoundRectangle) s;
				g2.setColor(o.getDrawColor());
				if (o.isFill())
					g2.fill(o);
			} else if (s instanceof Polygon2) {
				Polygon2 o = (Polygon2) s;
				g2.setColor(o.getDrawColor());
				if (o.isFill())
					g2.fill(o);
			}

			g2.draw(s);
		}

		for (int i = 0; i < movers.size(); i++) {
			Player mover = movers.get(i);
			mover.render(g2);
		}
	}

	public void addMover(Player player) {
		player.setShapes(shapes);
		this.movers.add(player);
	}

	public void removeMover(Player mover) {
		this.movers.remove(mover);
	}

	public void addShape(Shape shape) {
		shapes.add(shape);
	}

	public void removeShape(Shape shape) {
		shapes.remove(shape);
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}
	
	public void addLevelShape(ArrayList<Shape> level, Shape shape) {
		level.add(shape);
	}

	public ArrayList<Shape> getShapes(int level) {
		switch (level) {
		case 1:
			return shapesLevel1;
		case 2:
			return shapesLevel2;
		case 3:
			return shapesLevel3;
		case 4:
			return shapesLevel4;
		default:
			return shapesLevel1;
		}
	}

	private void loadLevels() {
		Scanner sc = null;
		ArrayList<Shape> level;
		for (int i = 1; i < 5; i++) {
			level = getShapes(i);
			try {
				sc = new Scanner(new File("levels/level" + i + ".txt"));
				while (sc.hasNextLine()) {
					String line = sc.nextLine().trim();
					if (line.startsWith("filledCircle") || line.startsWith("circle"))
						addLevelShape(level, new Ellipse(line));
					else if (line.startsWith("curve"))
						addLevelShape(level, new Curve(line));
					else if (line.startsWith("polygon") || line.startsWith("filledPolygon"))
						addLevelShape(level, new Polygon2(line));
					else if (line.startsWith("rectangle") || line.startsWith("filledRectangle"))
						addLevelShape(level, new Rectangle(line));
					else if (line.startsWith("roundRectangle") || line.startsWith("filledRoundRectangle"))
						addLevelShape(level, new RoundRectangle(line));
					else if (line.contains(","))
						addLevelShape(level, new Line(line));
				}
				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
