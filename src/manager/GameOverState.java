package manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import exceptions.InvalidStateException;
import main.GamePanel;

public class GameOverState extends GameState {

	String finalTime;
	
	public GameOverState(GameManager gm, String finalTime) {
		super(gm);
		this.finalTime = finalTime;
	}
	
	public void restartGame() {
		try {
			gm.switchState(GameManager.PLAY_STATE);
		} catch (InvalidStateException e) {
			e.printStackTrace();
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			//Restart game, i.e. switch to new PlayState
			restartGame();
		}
	}
	public void keyReleased(KeyEvent e) {e.consume();}
	public void keyTyped(KeyEvent e) {e.consume();}
	
	public void update() {
		
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);
		g.setColor(Color.GRAY);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		g.drawString("Game Over!", GamePanel.GAME_WIDTH/2-42, GamePanel.GAME_HEIGHT/2-15);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		g.drawString("Final Time: " + finalTime, GamePanel.GAME_WIDTH/2-58, GamePanel.GAME_HEIGHT/2+5);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		g.drawString("Press enter to restart", GamePanel.GAME_WIDTH/2-50, GamePanel.GAME_HEIGHT/2+20);
	}

	

}
