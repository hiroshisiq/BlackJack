package gui;

import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainWindow {
	private static JFrame window;
	private static MenuPanel menu;
	private static GamePanel game;
	private static OptionsPanel options;
	
	private MainWindow() {
		configureMainWindow();
	}
	
	private void configureMainWindow() {
		// Main window basic settings
		window = new JFrame();
		loadIconAndTitle(window);
		configureSize(window);
		Container contPane = window.getContentPane(); 
		
		// Create menu and options panel
		menu = new MenuPanel();
		options = new OptionsPanel();
		
		// Add initial panel
		contPane.add(menu);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	private void loadIconAndTitle(JFrame window) {
		window.setTitle("BlackJack (XorEdition)");
		ImageIcon icon = new ImageIcon("images/icon/card.png");		
		window.setIconImage(icon.getImage());
	}
	
	private void configureSize(JFrame window) {
		window.setSize(800, 600);
		window.setResizable(false);
	}
	
	static void changeToGamePanel() {
		window.getContentPane().remove(menu);
		window.getContentPane().add(game = new GamePanel());
		
		game.reset();
		
		window.setVisible(true);
		window.repaint();
	}
	
	static void changeToOptionsPanel() {
		window.getContentPane().remove(menu);
		window.getContentPane().add(options);
				
		window.setVisible(true);
		window.repaint();
	}
	
	static void changeToMenuPanel() {
		window.getContentPane().removeAll();
		window.getContentPane().add(menu);
		window.setVisible(true);
		window.repaint();
	}
	
	public static void main(String [] args) {
		@SuppressWarnings("unused")
		MainWindow mainWindow = new MainWindow();
	}
}
