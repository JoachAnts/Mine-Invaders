package manager;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Enemy;
import entities.Player;

public class Content {

	private static BufferedImage[][] playerSprites = loadSprites("/Sprites/player_sprites.gif", 10, 16);
	private static BufferedImage[][] enemySprites = loadSprites("/Sprites/enemy_sprites.gif", 8, 8);
	private static BufferedImage stage = loadImage("/Background/Stage.gif");
	private static BufferedImage sky = loadImage("/Background/sky.gif");
	
	private static BufferedImage loadImage(String path) {
		BufferedImage img;
		try {
			img = ImageIO.read(Content.class.getResourceAsStream(path));
		} catch (IOException e) {
			System.out.println("Could not load image");
			e.printStackTrace();
			return null;
		}
		return img;
	}
	
	private static BufferedImage[][] loadSprites(String path, int w, int h) {
		try {
			BufferedImage spriteSheet = ImageIO.read(Content.class.getResourceAsStream(path));
			BufferedImage[][] sprites = new BufferedImage[spriteSheet.getHeight()/h][spriteSheet.getWidth()/w];
			for (int r = 0; r < spriteSheet.getHeight()/h; r++) {
				for (int c = 0; c < spriteSheet.getWidth()/w; c++) {
					sprites[r][c] = spriteSheet.getSubimage(c*w, r*h, w, h);
				}
			}
			return sprites;
		} catch (IOException e) {
			System.out.println("Error loading sprites");
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage getStageImage() {
		return stage;
	}
	
	public static BufferedImage getSkyImage() {
		return sky;
	}
	
	public static BufferedImage getPlayerSprite(int sprite) {
		if (sprite == Player.STANDING_1)
			return playerSprites[2][0];
		else if (sprite == Player.WALKING_RIGHT_1)
			return playerSprites[1][0];
		else if (sprite == Player.WALKING_RIGHT_2)
			return playerSprites[1][1];
		else if (sprite == Player.WALKING_RIGHT_3)
			return playerSprites[1][3];
		else if (sprite == Player.WALKING_RIGHT_4)
			return playerSprites[1][2];
		else if (sprite == Player.WALKING_LEFT_1)
			return playerSprites[0][0];
		else if (sprite == Player.WALKING_LEFT_2)
			return playerSprites[0][1];
		else if (sprite == Player.WALKING_LEFT_3)
			return playerSprites[0][3];
		else if (sprite == Player.WALKING_LEFT_4)
			return playerSprites[0][2];
		return null;
	}
	
	public static BufferedImage getEnemySprite(int sprite) {
		if (sprite == Enemy.NORMAL) {
			return enemySprites[0][0];
		}
		else if (sprite == Enemy.CRASH) {
			return enemySprites[0][1];
		}
		return null;
	}	
	
}
