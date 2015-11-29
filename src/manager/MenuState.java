package manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import entities.Enemy;
import exceptions.InvalidStateException;
import main.GamePanel;

public class MenuState extends GameState {

	private final Color MENU_COLOR = Color.GRAY;
	
	private boolean startIsSelected;
	private boolean quitIsSelected;
	
	public MenuState (GameManager gm) {
		super(gm);
		startIsSelected = true;
		quitIsSelected = false;
	}
	
	public void startGame() {
		try {
			gm.switchState(GameManager.PLAY_STATE);
		} catch (InvalidStateException e) {
			e.printStackTrace();
		}
	}
	public void quitGame() { System.exit(0); }
	
	

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN && startIsSelected) {
			startIsSelected = false;
			quitIsSelected = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP && quitIsSelected) {
			quitIsSelected = false;
			startIsSelected = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER && startIsSelected)
			startGame();
		else if (e.getKeyCode() == KeyEvent.VK_ENTER && quitIsSelected)
			quitGame();
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {e.consume();}

	public void update() {}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);
		g.setColor(MENU_COLOR);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		g.drawString("Mine Invaders", GamePanel.GAME_WIDTH/2-48, GamePanel.GAME_HEIGHT/6);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		g.drawString("Start Game", GamePanel.GAME_WIDTH/2-27, GamePanel.GAME_HEIGHT/2-10);
		g.drawString("Quit", GamePanel.GAME_WIDTH/2-27, GamePanel.GAME_HEIGHT/2+10);
		if (startIsSelected) {
			g.drawImage(Content.getEnemySprite(Enemy.NORMAL), GamePanel.GAME_WIDTH/2-39, GamePanel.GAME_HEIGHT/2-19, null);
		}
		else if (quitIsSelected) {
			g.drawImage(Content.getEnemySprite(Enemy.NORMAL), GamePanel.GAME_WIDTH/2-39, GamePanel.GAME_HEIGHT/2+2, null);
		}
	}
}
