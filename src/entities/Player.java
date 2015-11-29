package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import main.GamePanel;
import manager.Content;
import manager.Entity;

public class Player extends Entity {

	private boolean alive;
	private int xPos;
	private int yPos;
	private int xDir;
	private int yDir;
	private Rectangle hitbox;

	public static final int PLAYER_WIDTH = 10;
	public static final int PLAYER_HEIGHT = 16;
	
	public static final int STANDING_1 = 8;
	public static final int WALKING_LEFT_1 = 0;
	public static final int WALKING_LEFT_2 = 1;
	public static final int WALKING_LEFT_3 = 3;
	public static final int WALKING_LEFT_4 = 2;
	public static final int WALKING_RIGHT_1 = 4;
	public static final int WALKING_RIGHT_2 = 5;
	public static final int WALKING_RIGHT_3 = 7;
	public static final int WALKING_RIGHT_4 = 6;
	
	
	private Animation standing;
	private Animation walkingRight;
	private Animation walkingLeft;
	private Animation currentAnimation;
	private final int DELAY = 5;
	
	private boolean jumping;
	private int jumpCount;
	private final int JUMP_LENGTH = 10;
	
	private Stage stage;
	
	public Player(Stage stage, int initX, int initY) {
		alive = true;
		this.stage = stage;
		xPos = initX;
		yPos = initY;
		hitbox = new Rectangle(xPos, yPos, PLAYER_WIDTH, PLAYER_HEIGHT);
		initAnimations();
		jumping = false;
		jumpCount = 0;
	}
	
	public void initAnimations() {
		BufferedImage[] standingFrames = new BufferedImage[1];
		standingFrames[0] = Content.getPlayerSprite(STANDING_1);
		standing = new Animation(standingFrames, DELAY);
		
		BufferedImage[] walkingRightFrames = new BufferedImage[6];
		walkingRightFrames[0] = Content.getPlayerSprite(WALKING_RIGHT_1);
		walkingRightFrames[1] = Content.getPlayerSprite(WALKING_RIGHT_2);
		walkingRightFrames[2] = Content.getPlayerSprite(WALKING_RIGHT_1);
		walkingRightFrames[3] = Content.getPlayerSprite(WALKING_RIGHT_3);
		walkingRightFrames[4] = Content.getPlayerSprite(WALKING_RIGHT_4);
		walkingRightFrames[5] = Content.getPlayerSprite(WALKING_RIGHT_3);
		walkingRight = new Animation(walkingRightFrames, DELAY);
		
		BufferedImage[] walkingLeftFrames = new BufferedImage[6];
		walkingLeftFrames[0] = Content.getPlayerSprite(WALKING_LEFT_1);
		walkingLeftFrames[1] = Content.getPlayerSprite(WALKING_LEFT_2);
		walkingLeftFrames[2] = Content.getPlayerSprite(WALKING_LEFT_1);
		walkingLeftFrames[3] = Content.getPlayerSprite(WALKING_LEFT_3);
		walkingLeftFrames[4] = Content.getPlayerSprite(WALKING_LEFT_4);
		walkingLeftFrames[5] = Content.getPlayerSprite(WALKING_LEFT_3);
		walkingLeft = new Animation(walkingLeftFrames, DELAY);
		
		currentAnimation = standing;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public void move() {
		if (!getHitbox().intersects(stage.getHitbox()) && !jumping)
			yDir = 1;
		else if (!jumping)
			yDir = 0;
		if (xPos+xDir <= GamePanel.GAME_WIDTH-PLAYER_WIDTH && xPos+xDir >= 0) {
			xPos += xDir;
			hitbox.x = xPos;
		}
		if (yPos+yDir <= GamePanel.GAME_HEIGHT-PLAYER_HEIGHT && yPos+yDir >= 0) {
			yPos += yDir;
			hitbox.y = yPos;
		}
	}
	
	public boolean isAlive() { return alive; }
	
	public void die() {
		alive = false;
	}
	
	private void jump() {
		if (jumping && jumpCount < JUMP_LENGTH) {
			yDir = -1;
			jumpCount++;
		} else if (jumpCount >= JUMP_LENGTH) {
			jumping = false;
			jumpCount = 0;
		}
	}
	
	public void keyIsPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D  || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			xDir = 1;
			currentAnimation = walkingRight;
		}
		else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			xDir = -1;
			currentAnimation = walkingLeft;
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			if (getHitbox().intersects(stage.getHitbox())) {
				jumping = true;
				jump();
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			xDir = 0;
			currentAnimation = standing;
		}
		else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			xDir = 0;
			currentAnimation = standing;
		}
	}
	
	public void update() {
		if (alive) {
			jump();
			move();
			currentAnimation.update();
		}
	}
	
	public void drawEntity(Graphics g) {
		if (alive) {
			g.drawImage(currentAnimation.getFrame(), xPos, yPos, PLAYER_WIDTH, PLAYER_HEIGHT, null);
		}
	}

	
}
