package shapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;

import shapes.Rectangle;
import game.Game;
import game.Handler;

public class Player extends Rectangle implements MoveableShape {

	private static final long serialVersionUID = -9086638501058842642L;
	private Handler handler;
	private Shape shape;
	private ArrayList<Shape> shapes;
	private Ellipse speaker;

	public Player(int x, int y, int w, int h, Color c, Handler handler) {
		super(x, y, w, h, c);
		this.c = c;
		this.handler = handler;
		setVelX((float) 0.1);
		setVelY((float) 0.1);
	}

	public void tick() {
		if (x - width > Game.WIDTH || x < 0) {
			setVelX(getVelX() * -1);
		}
		if (y - width > Game.HEIGHT || y < 0) {
			setVelY(getVelY() * -1);
		}
		shape = handler.checkCollisions(this);
		if (shapes.size() > 1) {
			if (shape == null) {
				x += getVelX();
				y += getVelY();
			} else {
				handler.removeShape(shape);
			}
		}
		if (shapes.size() == 1 && shapes.get(0).intersects(this)) {
				speaker = (Ellipse) shapes.get(0);
		} else {
			x += getVelX();
			y += getVelY();
		} 
	}

	public void render(Graphics2D g2) {
		g2.setColor(Color.white);
		g2.draw(this);
		if (speaker != null) {
			g2.draw(speaker);
			java.awt.Rectangle r = speaker.getBounds();
			g2.setFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 18));
			g2.setColor(Color.magenta);
			g2.drawString(speaker.speak(), r.x, r.y);
			g2.drawString(respond(), (int)(x + width), (int)y);
		}
	}

	public void stop() {
		setVelX(0);
		setVelY(0);
	}

	public String respond() {
		return "Bison";

	}

	public String toString() {
		return String.format("%s, %d, %d, %d, %d, %d%n", (fill ? "filledPlayer" : "player"), (int) x, (int) y,
				(int) width, (int) height, c.getRGB());
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
}
