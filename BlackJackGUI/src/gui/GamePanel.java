package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	GamePanel() {
		setLayout(new BorderLayout());
		GraphicUtils.setGreenBackground(this);
		
		// Buttons
		addBackButton();
		addGameButtons();
	}
	
	private void addBackButton() {
		// Load image
		ImageIcon img = new ImageIcon("../images/buttons/back.png");
		img = GraphicUtils.getScaledImage(img, 50, 50);
		
		// Create label
		JLabel back = new JLabel(img);
		back.setHorizontalAlignment(JLabel.LEFT);
		
		// Add listener
		back.addMouseListener(new MouseListener() {
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				MainWindow.changeToMenuPanel();
				resize(50,50);
			}

			public void mouseEntered(MouseEvent e) {
				resize(52,52);
			}

			public void mouseExited(MouseEvent e) {
				resize(50,50);
			}
			
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}	
			
			private void resize(int w, int h) {
				ImageIcon img = new ImageIcon("../images/buttons/back.png");
				img = GraphicUtils.getScaledImage(img, w, h);
				back.setIcon(img);
			}	
		});
		
		// Box layout
		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS));
		GraphicUtils.setGreenBackground(box);
		box.add(back);
		
		
		// Add to panel
		add(box, BorderLayout.NORTH);
	}

	private void addGameButtons() {
		JPanel buttonPanel = new JPanel(new GridLayout(1,3));
		GraphicUtils.setGreenBackground(buttonPanel);
		
		// Add hit button
		addHitButton(buttonPanel);
		addStandButton(buttonPanel);
		addBetButton(buttonPanel);
		
		// Add to game panel
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void addHitButton(JPanel panel) {
		// Load images
		ImageIcon img = new ImageIcon("../images/buttons/hit.png");
		img = GraphicUtils.getScaledImage(img, 60, 50);
		
		// Generate label
		JLabel hit = new JLabel();
		hit.setIcon(img);
		hit.setHorizontalAlignment(JLabel.CENTER);
		
		// Add listener
		hit.addMouseListener(new MouseListener() {
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				resize(55, 50);
			}
						
			public void mouseEntered(MouseEvent e) {
				resize(65, 50);
			}

			public void mouseExited(MouseEvent e) {
				resize(60, 50);
			}
			
			public void mouseReleased(MouseEvent e) {
				resize(65, 50);
			}	
			
			public void mouseClicked(MouseEvent e) {}
			
			private void resize(int w, int h) {
				ImageIcon img = new ImageIcon("../images/buttons/hit.png");
		        img = GraphicUtils.getScaledImage(img, w, h);
				hit.setIcon(img);	
			}			
		});
		
		// Add to panel
		panel.add(hit);
	}
	
	private void addStandButton(JPanel panel) {
		// Load images
		ImageIcon img = new ImageIcon("../images/buttons/stand.png");
		img = GraphicUtils.getScaledImage(img, 100, 50);
		
		// Generate label
		JLabel stand = new JLabel();
		stand.setIcon(img);
		stand.setHorizontalAlignment(JLabel.CENTER);
		
		// Add listener
		stand.addMouseListener(new MouseListener() {
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				resize(90, 50);
			}
						
			public void mouseEntered(MouseEvent e) {
				resize(110, 50);
			}

			public void mouseExited(MouseEvent e) {
				resize(100, 50);
			}
			
			public void mouseReleased(MouseEvent e) {
				resize(110, 50);
			}	
			
			public void mouseClicked(MouseEvent e) {}
			
			private void resize(int w, int h) {
				ImageIcon img = new ImageIcon("../images/buttons/stand.png");
		        img = GraphicUtils.getScaledImage(img, w, h);
				stand.setIcon(img);	
			}			
		});
		
		// Add to panel
		panel.add(stand);
	}
	
	private void addBetButton(JPanel panel) {
		JPanel betPanel = new JPanel(new GridLayout(2,1));
		GraphicUtils.setGreenBackground(betPanel);
		
		// Load bet image
		ImageIcon img = new ImageIcon("../images/buttons/bet.png");
		img = GraphicUtils.getScaledImage(img, 60, 50);
		
		// Generate label
		JLabel bet = new JLabel();
		bet.setIcon(img);
		bet.setHorizontalAlignment(JLabel.CENTER);
		
		// Crate bet values buttons
		JPanel betValuePanel = new JPanel(new GridLayout(1,3));
		GraphicUtils.setGreenBackground(betValuePanel);
		addBetValueButton(betValuePanel, 100);
		addBetValueButton(betValuePanel, 250);
		addBetValueButton(betValuePanel, 500);
		
		// Add to bet 
		betPanel.add(bet);
		betPanel.add(betValuePanel);
		
		// add to panel
		panel.add(betPanel);
		
	}
	
	private void addBetValueButton(JPanel panel, int value) {
		// Load bet image
		ImageIcon img = new ImageIcon("../images/buttons/" + Integer.toString(value) + ".png");
		img = GraphicUtils.getScaledImage(img, 60, 50);
		
		// Generate label
		JLabel bet = new JLabel();
		bet.setIcon(img);
		bet.setHorizontalAlignment(JLabel.CENTER);
		
		// Add listener
		bet.addMouseListener(new MouseListener() {
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				resize(55, 50);
			}
						
			public void mouseEntered(MouseEvent e) {
				resize(65, 50);
			}

			public void mouseExited(MouseEvent e) {
				resize(60, 50);
			}
			
			public void mouseReleased(MouseEvent e) {
				resize(60, 50);
			}	
			
			public void mouseClicked(MouseEvent e) {}
			
			private void resize(int w, int h) {
				ImageIcon img = new ImageIcon("../images/buttons/" + Integer.toString(value) + ".png");
				img = GraphicUtils.getScaledImage(img, w, h);
				bet.setIcon(img);	
			}			
		});
		
		panel.add(bet);
	}
	
		
}
