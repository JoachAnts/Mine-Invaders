package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import manager.GameManager;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	public static final int GAME_WIDTH = 128;
	public static final int GAME_HEIGHT = 128;
	public static final int SCALE = 5;
	
	public static final int FPS = 60;
	private final long WAIT_TIME = (long)1000/FPS;
	
	private Dimension size;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage gameImage;
	private Graphics gameGraphics;
	private GameManager gameManager;
	
	public GamePanel() {
		size = new Dimension(GAME_WIDTH*SCALE, GAME_HEIGHT*SCALE);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		setFocusable(true);
		requestFocus();
		
		gameImage = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
		gameGraphics = gameImage.getGraphics();
		gameManager = new GameManager();
		
		addKeyListener(this);
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void tick() {
		gameManager.update();
	}

	public void render() {
		gameManager.drawGame(gameGraphics);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(gameImage, 0, 0, GAME_WIDTH*SCALE, GAME_HEIGHT*SCALE, null);
	}
	
	@Override
	public void run() {
		running = true;
		while (running) {
			long start = System.currentTimeMillis();
			tick();
			render();
			long end = System.currentTimeMillis();
			if (end-start < WAIT_TIME) {
				try {
					Thread.sleep(WAIT_TIME-(end-start));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gameManager.keyIsPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gameManager.keyReleased(e);
	}
	
	public void keyTyped(KeyEvent arg0) {}
}
