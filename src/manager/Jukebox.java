package manager;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Jukebox {
	
	private static Clip c;
	
	public static void playScratch() {
		try {
			Clip c = AudioSystem.getClip();
			AudioInputStream stream = AudioSystem.getAudioInputStream(Jukebox.class.getResource("/Sounds/record_scratch.wav"));
			c.open(stream);
			c.setFramePosition(23000);
			c.start();
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean musicIsPlaying() { 
		if (c != null)
			return c.isRunning();
		return false;
	}
	
	public static synchronized void playMusic() {
		try {
			c = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(Jukebox.class.getResource("/Music/BloodDragon.wav"));
			c.open(inputStream);
			c.setFramePosition(60000);
			c.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void stopMusic() {
		if (c.isRunning())
			c.stop();
		}

	public static void playCrashing() {
		try {
			Clip c = AudioSystem.getClip();
			AudioInputStream stream = AudioSystem.getAudioInputStream(Jukebox.class.getResource("/Sounds/record_scratch.wav"));
			c.open(stream);
			c.setFramePosition(23000);
			c.start();
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
	}

}
