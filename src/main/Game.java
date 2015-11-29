package main;

import javax.swing.JFrame;

public class Game {

	private final static String TITLE = "Mine Invaders";
	
	public static void main(String[] args) {
		JFrame frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		GamePanel gamePanel = new GamePanel();
		frame.add(gamePanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}

//package main;
//
//import javax.swing.JApplet;
//
//public class Game extends JApplet {
//	
//	private static final long serialVersionUID = 1L;
//
//	public static void main(String[] args) {
//		Game game = new Game();
//		game.init();
//	}
//	
//	public void init() {
//		//game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		//game.setResizable(false);
//		add(new GamePanel());
//		//game.pack();
//		//game.setLocationRelativeTo(null);
//		
//		setVisible(true);
//	}
//	
//	public void start() {
//	}
//	
//	public void stop() {
//		
//	}
//	
//	public void destroy() {
//		
//	}
//	
//}

