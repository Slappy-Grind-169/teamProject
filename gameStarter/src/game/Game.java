package game;
//http://blog.slapware.eu/game-engine/preface/
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import shapes.Player;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 7580815534084638412L;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = WIDTH;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	
	public Game() {
		handler = new Handler();
		addKeyListener(new GameKeyListener(handler));
		new Frame(this, WIDTH, HEIGHT);
		handler.addMover(new Player(0, 0, 32, 32, Color.white, handler));
		requestFocusInWindow();
	}


	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
		g2.setColor(Color.black);
		g2.fillRect(0,  0,  WIDTH, HEIGHT);
		handler.render(g2);
		
		g2.dispose();
		bs.show();
	}

	private void tick() {
		handler.tick();
		
	}
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}	
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.;
		double ns = 1e8 / amountOfTicks;
		double delta = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
		}	
	}

	public static void main(String[] args) {
		new Game();
	}

}
