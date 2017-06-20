/* 
 *  Autor:   Anderson Hiroshi de Siqueira 
 *  N USP:   9313197
 *  Subject: OOP - SCC0504 
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  
 */

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
		ImageIcon icon = new ImageIcon("../images/icon/card.png");		
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
