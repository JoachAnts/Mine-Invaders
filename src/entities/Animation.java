package entities;

import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] frames;
	private int numFrames;
	private int frameIndex;
	private BufferedImage currentFrame;
	private int ticks;
	private int delay;
	
	public Animation(BufferedImage[] f, int delay) {
		this.delay = delay;
		frames = f;
		numFrames = frames.length;
		frameIndex = 0;
		currentFrame = frames[frameIndex];
		ticks = 0;
	}
	
	public BufferedImage getFrame() {
		return currentFrame;
	}
	
	public void update() {
		ticks++;
		if (ticks == delay && frameIndex+1 < numFrames) {
			frameIndex++;
			currentFrame = frames[frameIndex];
			ticks = 0;
		} else if (ticks == delay) {
			frameIndex = 0;
			currentFrame = frames[frameIndex];
			ticks = 0;
		}
	}
	
}
