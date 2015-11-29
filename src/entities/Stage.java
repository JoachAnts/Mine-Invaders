package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.GamePanel;
import manager.Content;

public class Stage {

	private Rectangle stage;
	
	public static final int STAGE_WIDTH = GamePanel.GAME_WIDTH;
	public static final int STAGE_HEIGHT = 16;
	
	public Stage() {
		stage = new Rectangle(0, GamePanel.GAME_HEIGHT-STAGE_HEIGHT, STAGE_WIDTH, STAGE_HEIGHT); 
	}
	
	public Rectangle getHitbox() {
		return stage;
	}
	
	public void drawStage(Graphics g) {
		g.drawImage(Content.getStageImage(), 0, GamePanel.GAME_HEIGHT-STAGE_HEIGHT*2, STAGE_WIDTH, STAGE_HEIGHT*2, null);
	}
}
