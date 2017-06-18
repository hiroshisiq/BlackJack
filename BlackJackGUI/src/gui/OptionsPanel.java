package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Ruler;
import deck.Card;

@SuppressWarnings("serial")
public class OptionsPanel extends JPanel{
	OptionsPanel() {
		setLayout(new BorderLayout());
		GraphicUtils.setGreenBackground(this);
		
		// Buttons
		addBackButton();
		addOptionsButtons();
	}
	
	private void addBackButton() {
		// Create label
		JLabel back = new JLabel();
		back.setHorizontalAlignment(JLabel.LEFT);
		
		// Load image
		String imagePath = "images/buttons/back2.png";
		GraphicUtils.loadImage(back, imagePath, 50, 50, 0);
		
		// Add listener
		back.addMouseListener(new MouseListener() {			
			// Change to game panel
			public void mousePressed(MouseEvent e) {				
				MainWindow.changeToMenuPanel();
				GraphicUtils.loadImage(back, imagePath, 50, 50, 0);				
			}

			public void mouseEntered(MouseEvent e) {
				GraphicUtils.loadImage(back, imagePath, 50, 50, -20);
			}

			public void mouseExited(MouseEvent e) {
				GraphicUtils.loadImage(back, imagePath, 50, 50, 0);
			}
			
			public void mouseReleased(MouseEvent e) {
				GraphicUtils.loadImage(back, imagePath, 50, 50, 0);
			}
			
			public void mouseClicked(MouseEvent e) {}
		});
		
		// Box layout
		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS));
		GraphicUtils.setGreenBackground(box);
		box.add(back);		
		
		// Add to panel
		add(box, BorderLayout.NORTH);
	}
	
	private void addOptionsButtons() {
		// Generate a box layout 
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(2,1));
		GraphicUtils.setGreenBackground(grid);
		
		// Add options
		addCardStyleOp(grid);
		addNODecksOp(grid);
						
		// Add to panel
		add(grid, BorderLayout.CENTER);
	}
	
	private void addCardStyleOp(JPanel panel) {
		JPanel flow = new JPanel();
		flow.setLayout(new FlowLayout());
		flow.setAlignmentX(CENTER_ALIGNMENT);
		GraphicUtils.setGreenBackground(flow);
		
		// Generate label
		JLabel cs = new JLabel(); // card style 
		JLabel la = new JLabel(); // left arrow
		JLabel cb = new JLabel(); // card back
		JLabel ra = new JLabel(); // right arrow
		
		// Alignment
		cs.setHorizontalAlignment(JLabel.CENTER);
		la.setHorizontalAlignment(JLabel.CENTER);
		cb.setHorizontalAlignment(JLabel.CENTER);
		ra.setHorizontalAlignment(JLabel.CENTER);
				
		// Load Images
		int w = 50, h = 50,darkFactor = -40; // Arrows constants
		String pathCS = "images/etc/cs.png";
		GraphicUtils.loadImage(cs, pathCS, 200, 50, 0);
		String pathLA = "images/buttons/larrow.png";
		GraphicUtils.loadImage(la, pathLA, w, h, 0);
		String pathCB = Card.getStylePath();
		GraphicUtils.loadImage(cb, pathCB, 100, 150, 0);
		String pathRA = "images/buttons/rarrow.png";
		GraphicUtils.loadImage(ra, pathRA, w, h, 0);
		
		// Add listeners
		la.addMouseListener(new MouseListener() {
			String path = pathLA; 
			
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				GraphicUtils.loadImage(la, path, w, h+1, 2*darkFactor);
				Ruler.prevCardStyle();
				GraphicUtils.loadImage(cb, Card.getStylePath(), 100, 150, 0);
			}
						
			public void mouseEntered(MouseEvent e) {
				GraphicUtils.loadImage(la, path, w, h, darkFactor);
			}

			public void mouseExited(MouseEvent e) {
				GraphicUtils.loadImage(la, path, w, h, 0);
			}
			
			public void mouseReleased(MouseEvent e) {
				GraphicUtils.loadImage(la, path, w, h, darkFactor);
			}	
			
			public void mouseClicked(MouseEvent e) {}
		});
		
		ra.addMouseListener(new MouseListener() {
			String path = pathRA; 
			
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				GraphicUtils.loadImage(ra, path, w, h+1, 2*darkFactor);
				Ruler.nextCardStyle();
				GraphicUtils.loadImage(cb, Card.getStylePath(), 100, 150, 0);
			}
						
			public void mouseEntered(MouseEvent e) {
				GraphicUtils.loadImage(ra, path, w, h, darkFactor);
			}

			public void mouseExited(MouseEvent e) {
				GraphicUtils.loadImage(ra, path, w, h, 0);
			}
			
			public void mouseReleased(MouseEvent e) {
				GraphicUtils.loadImage(ra, path, w, h, darkFactor);
			}	
			
			public void mouseClicked(MouseEvent e) {}
		});
		
		// Add to flow layout panel
		flow.add(cs);
		flow.add(la);
		flow.add(cb);
		flow.add(ra);
		
		panel.add(flow);
	}
	
	private void addNODecksOp(JPanel panel) {
		JPanel flow = new JPanel();
		flow.setLayout(new FlowLayout());
		flow.setAlignmentX(CENTER_ALIGNMENT);
		GraphicUtils.setGreenBackground(flow);
		
		// Generate label
		JLabel nd = new JLabel(); // number of decks label 
		JLabel la = new JLabel(); // left arrow
		JLabel nu = new JLabel(); // number
		JLabel ra = new JLabel(); // right arrow
		
		// Alignment
		nd.setHorizontalAlignment(JLabel.CENTER);
		la.setHorizontalAlignment(JLabel.CENTER);
		nu.setHorizontalAlignment(JLabel.CENTER);
		ra.setHorizontalAlignment(JLabel.CENTER);
				
		// Load Images
		int w = 50, h = 50,darkFactor = -40; // Arrows constants
		String pathND = "images/etc/nodecks.png";
		GraphicUtils.loadImage(nd, pathND, 300, 50, 0);
		String pathLA = "images/buttons/larrow.png";
		GraphicUtils.loadImage(la, pathLA, 50, 50, 0);
		String pathNU = getNumberPath();
		GraphicUtils.loadImage(nu, pathNU, 60, 80, 0);
		String pathRA = "images/buttons/rarrow.png";
		GraphicUtils.loadImage(ra, pathRA, 50, 50, 0);
		
		// Add listeners
		la.addMouseListener(new MouseListener() {
			String path = pathLA; 
			
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				GraphicUtils.loadImage(la, path, w, h+1, 2*darkFactor);
				Ruler.prevNOD();
				GraphicUtils.loadImage(nu, getNumberPath(), 60, 80, 0);
			}
						
			public void mouseEntered(MouseEvent e) {
				GraphicUtils.loadImage(la, path, w, h, darkFactor);
			}

			public void mouseExited(MouseEvent e) {
				GraphicUtils.loadImage(la, path, w, h, 0);
			}
			
			public void mouseReleased(MouseEvent e) {
				GraphicUtils.loadImage(la, path, w, h, darkFactor);
			}	
			
			public void mouseClicked(MouseEvent e) {}
		});
		
		ra.addMouseListener(new MouseListener() {
			String path = pathRA; 
			
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				GraphicUtils.loadImage(ra, path, w, h+1, 2*darkFactor);
				Ruler.nextNOD();
				GraphicUtils.loadImage(nu, getNumberPath(), 60, 80, 0);
			}
						
			public void mouseEntered(MouseEvent e) {
				GraphicUtils.loadImage(ra, path, w, h, darkFactor);
			}

			public void mouseExited(MouseEvent e) {
				GraphicUtils.loadImage(ra, path, w, h, 0);
			}
			
			public void mouseReleased(MouseEvent e) {
				GraphicUtils.loadImage(ra, path, w, h, darkFactor);
			}	
			
			public void mouseClicked(MouseEvent e) {}
		});
		
		// Add to flow layout panel
		flow.add(nd);
		flow.add(la);
		flow.add(nu);
		flow.add(ra);
		
		panel.add(flow);
	}
	
	private String getNumberPath() {
		return "images/etc/" + Ruler.getNumOfDecks() + ".png";
	}

}
