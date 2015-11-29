package manager;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import exceptions.InvalidStateException;

public class GameManager {
	
	private GameState[] gameStates;
	private final int NUM_STATES = 3;
	private int currentState;
	
	public static final int MENU_STATE = 0;
	public static final int PLAY_STATE = 1;
	public static final int GAME_OVER_STATE = 2;
	
	public GameManager() {
		gameStates = new GameState[NUM_STATES];
		currentState = MENU_STATE;
		gameStates[MENU_STATE] = new MenuState(this);
	}
	
	public void switchState(int newState) throws InvalidStateException { 
		if (newState < 0 || newState > NUM_STATES-1)
			throw new InvalidStateException();
		if (newState == MENU_STATE)
			gameStates[newState] = new MenuState(this);
		else if (newState == PLAY_STATE)
			gameStates[newState] = new PlayState(this);
		else if (newState == GAME_OVER_STATE)
			gameStates[newState] = new GameOverState(this, ((PlayState) gameStates[PLAY_STATE]).getFinalTime());
		currentState = newState;
	}
	
	public void keyIsPressed(KeyEvent e) {
		gameStates[currentState].keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		gameStates[currentState].keyReleased(e);
	}
	
	public void update() {
		gameStates[currentState].update();
	}
	
	public void drawGame(Graphics g) {
		gameStates[currentState].draw(g);
	}
}
