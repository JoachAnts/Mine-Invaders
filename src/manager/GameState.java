package manager;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class GameState implements KeyListener {

	protected GameManager gm;
	
	public GameState(GameManager gm) {
		this.gm = gm;
	}
	
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	public abstract void update();
	public abstract void draw(Graphics g);
	
}
