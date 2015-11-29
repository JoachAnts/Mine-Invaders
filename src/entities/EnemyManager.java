package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class EnemyManager {

	private ArrayList<Enemy> enemies;
	
	private int spawnGap;
	
	private int ticks;
	
	private int maxEnemies;
	
	private Random random;
	private Stage stage;
	private Player player;
	
	public EnemyManager(int spawnGap, int max, Stage stage, Player player) {
		this.spawnGap = spawnGap;
		maxEnemies = max;
		this.stage = stage;
		this.player = player;
		ticks = 0;
		random = new Random();
		enemies = new ArrayList<>();
	}
	
	public void setSpawnGap(int s) { spawnGap = s; }
	
	public int getNumberOfEnemies() { return enemies.size(); }
	
	public void update() {
		ticks++;
		java.util.Iterator<Enemy> iterator = enemies.iterator();
		while (iterator.hasNext()) {
			Enemy nextEnemy = iterator.next();
			if (!nextEnemy.isDestroyed()) {
				nextEnemy.update();
			}
			else {
				iterator.remove();
			}
			if (nextEnemy.intersects(player.getHitbox()) && !nextEnemy.isCrashing()) {
				player.die();
			}
		}
		//Enemies spawn faster every certain number of ticks
		if (ticks % 150 == 0 && spawnGap > 1)
			setSpawnGap(spawnGap-1);
		//Spawn enemy every spawnGap ticks if there is room
		if (enemies.size() < maxEnemies && ticks % spawnGap == 0) {
			enemies.add(new Enemy(random.nextInt(GamePanel.GAME_WIDTH), -8, stage));
		}
	}
	
	public void draw(Graphics g) {
		for (Enemy enemy: enemies) {
			enemy.drawEntity(g);
		}
	}
}
