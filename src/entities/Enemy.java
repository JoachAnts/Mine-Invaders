package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import manager.Content;

public class Enemy extends Entity {

	public static final int NORMAL = 0;
	public static final int CRASH = 1;
	
	private Animation current;
	private Animation animation;
	private Animation crash;
	
	private final int ENEMY_WIDTH = 8;
	private final int ENEMY_HEIGHT = 8;
	
	private boolean isCrashing;
	private boolean destroyed;
	
	private Stage stage;
	
	private int crashTicks;
	private int x;
	private int y;
	private int i;
	private int j;
	private Rectangle hitbox;
	
	public Enemy(int x, int y, Stage stage) {
		destroyed = false;
		isCrashing = false;
		this.x = x;
		this.y = y;
		i = 0;
		j = 1;
		hitbox = new Rectangle(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
		this.stage = stage;
		initAnimations();
	}
	
	public void initAnimations() {
		BufferedImage[] frames = new BufferedImage[1];
		frames[0] = Content.getEnemySprite(NORMAL);
		animation = new Animation(frames, 10);
		
		BufferedImage[] crashFrames = new BufferedImage[1];
		crashFrames[0] = Content.getEnemySprite(CRASH);
		crash = new Animation(crashFrames, 10);
		
		current = animation;
	}
	
	public Rectangle getHitbox() { return hitbox; }
	
	public boolean intersects(Rectangle r) { return getHitbox().intersects(r); }
	
	public boolean isDestroyed() { return destroyed; }
	public boolean isCrashing() { return isCrashing; }
	
	public void move() {
		x += i;
		hitbox.x = x;
		y += j;
		hitbox.y = y;
	}
	
	public void update() {
		if (!destroyed && !isCrashing)
			move();
		if (stage.getHitbox().intersects(getHitbox()) && !isCrashing) {
			isCrashing = true;
			//TODO Jukebox.playCrashing();
			//Set crash animation
			current = crash;
			crashTicks = 0;
		}
		if (isCrashing) {
			crashTicks++;
			if (crashTicks >= 10) {
				destroyed = true;
			}
		}
	}

	public void drawEntity(Graphics g) {
		if (!destroyed) {
			g.drawImage(current.getFrame(), x, y, ENEMY_WIDTH, ENEMY_HEIGHT, null);
		}
	}

}
