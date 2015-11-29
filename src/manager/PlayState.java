package manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.GamePanel;
import entities.EnemyManager;
import entities.Player;
import entities.Stage;
import exceptions.InvalidStateException;

public class PlayState extends GameState {

	private Time time;
	
	private Stage stage;
	private Player player;
	private EnemyManager enemies;
	
	private final int MAX_ENEMIES = 10;
	private final int INITIAL_ENEMY_SPEED = 15;
	
	private boolean paused;
	
	private String finalTime;
	
	public PlayState(GameManager gm) {
		super(gm);
		paused = false;
		time = new Time();
		stage = new Stage();
		player = new Player(stage, GamePanel.GAME_WIDTH/2-Player.PLAYER_WIDTH/2, GamePanel.GAME_HEIGHT-(Player.PLAYER_HEIGHT + Stage.STAGE_HEIGHT));
		enemies = new EnemyManager(INITIAL_ENEMY_SPEED, MAX_ENEMIES, stage, player);
		finalTime = "";
	}

	public void keyPressed(KeyEvent e) {
		player.keyIsPressed(e);
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			paused = !paused;
	}

	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}
	
	public void keyTyped(KeyEvent e) {e.consume();}
	
	public void drawBG(Graphics g) {
		g.drawImage(Content.getSkyImage(), 0, 0, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT-Stage.STAGE_HEIGHT, null);
	}
	
	public String getFinalTime() { return finalTime; }
	
	private void gameOver() {
		Jukebox.stopMusic();
		Jukebox.playScratch();
		try {
			gm.switchState(GameManager.GAME_OVER_STATE);
		} catch (InvalidStateException e) {
			e.printStackTrace();
		}		
	}
	
	public void drawNumberOfEnemies(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		g.drawString("Enemies: " + enemies.getNumberOfEnemies(), GamePanel.GAME_WIDTH-55, GamePanel.GAME_HEIGHT-3);
	}

	public void update() {
		if (player.isAlive() && !paused) {
			time.update();
			player.update();
			enemies.update();
		}
		else if (paused)
			return;
		else if (!player.isAlive()) {
			finalTime = time.getTime();
			gameOver();			
		}
		if (!Jukebox.musicIsPlaying() && player.isAlive())
			Jukebox.playMusic();
	}

	public void draw(Graphics g) {
		drawBG(g);
		stage.drawStage(g);
		player.drawEntity(g);
		enemies.draw(g);
		drawNumberOfEnemies(g);
		time.drawTime(g);
		if (paused) {
			g.setColor(Color.BLACK);
			g.drawString("PAUSED", GamePanel.GAME_WIDTH/2-25, GamePanel.GAME_HEIGHT/2);
		}
	}

}
