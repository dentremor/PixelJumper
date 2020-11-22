package de.se2projekt.main;

import de.se2projekt.gfx.Screen;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
/**
 * Die Klasse repraesentiert das Spiel. Dort befindet sich die GameLoop
 * und der Frame, auf dem das Spiel angezeigt wird.
 *
 * @author Cazim Ukela
 *
 */

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;


	public static final int WIDTH = 400;
	public static final int HEIGHT = 220;
	public static final int BLOCK_SIZE = 16;

	private final int UPS = 60;

	private boolean running;
	private int scaleFactor;
	private JFrame frame;
	private GameManager gameManager;
	private Screen screen;

	/**
	 * [Konstruktor] Initialisiert den Scale Faktor und die Anzahl der Spieler.
	 */
	public Game(int scaleFactor) {
		this.scaleFactor = scaleFactor;

		frame = new JFrame();
		setMinimumSize(new Dimension(WIDTH * scaleFactor, HEIGHT * scaleFactor));
		setMaximumSize(new Dimension(WIDTH * scaleFactor, HEIGHT * scaleFactor));
		setPreferredSize(new Dimension(WIDTH * scaleFactor, HEIGHT * scaleFactor));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		requestFocus();
	}

	/**
	 * GameLoop. Überschreibt die Methode run();
	 */
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerUpdate = 1000000000D / UPS;

		int updates = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		init();


		while (running) {
			long nowTime = System.nanoTime();
			delta += (nowTime - lastTime) / nsPerUpdate;

			lastTime = nowTime;
			boolean shouldRender = false;

			while (delta >= 1) {
				updates++;
				update();
				delta -= 1;
				shouldRender = true;
			}

			if (shouldRender) {
				render();
				frames++;
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				frames = 0;
				updates = 0;
			}
		}
	}

	/**
	 * Startet den GameLoop
	 */
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}

	/**
	 * Stoppt den GameLoop
	 */
	public synchronized void stop() {
		running = false;
	}

	/**
	 * Ruft Updatemethode im GameManager auf
	 */
	public void update() {
		//TODO: Update
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g2d.setTransform(AffineTransform.getScaleInstance(scaleFactor, scaleFactor));

		g.clearRect(0, 0, getWidth(), getHeight());
		gameManager.render(g,screen);
		g.dispose();
		bs.show();
	}

	/**
	 * Initialisiert den Screen, GameManager und die Audio
	 */
	private void init() {
		screen = new Screen();
		gameManager = new GameManager();
	}
}


