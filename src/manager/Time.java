package manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import entities.Stage;
import main.GamePanel;

public class Time {

	private int ticks;
	
	public Time() {
		ticks = 0;
	}
	
	public int getTotalSeconds() {
		return ticks / GamePanel.FPS;
	}
	
	public int getMinutes() {
		return getTotalSeconds() / 60;
	}
	
	public int getSeconds() {
		return getTotalSeconds() % 60;
	}
	
	public String getTime() {
		String time = "";
		if (getSeconds() < 10 && getMinutes() < 10) {
			time += "0" + getMinutes() + ":0" + getSeconds();
		}
		else if (getMinutes() < 10) {
			time += "0" + getMinutes() + ":" + getSeconds();
		}
		else if (getSeconds() < 10) {
			time += getMinutes() + ":0" + getSeconds();
		}
		return time;
	}
	
	public void update() {
		ticks++;
	}
	
	public void drawTime(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		g.drawString(getTime(), Stage.STAGE_HEIGHT/5, GamePanel.GAME_HEIGHT-Stage.STAGE_HEIGHT/5);
	}
}
