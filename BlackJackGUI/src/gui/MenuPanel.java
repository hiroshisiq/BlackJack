package gui;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Ruler;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel {
	
	MenuPanel () {
		// Use grid layout with two rows
		setLayout(new GridLayout(2,1));
		
		// Set the title and background images 
		addTitleImage();
		addBackgroundImage();
		addButtons();
	}
	
	private void addTitleImage() {
		// Load image
		ImageIcon image = new ImageIcon("../images/etc/bjtitle.png");
		
		// Create label and set image
		JLabel label = new JLabel();
		label.setIcon(image);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		// Add label to first line of the panel
		add(label);
	}
	
	private void addBackgroundImage() {
		// Load image
		//ImageIcon image = new ImageIcon("../images/etc/cgreen0.png");
		
		GraphicUtils.setGreenBackground(this);
	}

	void addButtons() {
		// Create button panel
		JPanel bPanel = new JPanel(new GridLayout(3,1));
		GraphicUtils.setGreenBackground(bPanel);
		
		// Load images
		ImageIcon startImage = new ImageIcon("../images/buttons/startb.png");
		startImage = GraphicUtils.getScaledImage(startImage, 100, 50);
		
		ImageIcon optionsImage = new ImageIcon("../images/buttons/optionsb.png");
		optionsImage = GraphicUtils.getScaledImage(optionsImage, 140, 50);
		
		ImageIcon exitImage = new ImageIcon("../images/buttons/exitb.png");
		exitImage = GraphicUtils.getScaledImage(exitImage, 80, 50);
		
		// Create label and set image
		JLabel startLabel = new JLabel();
		startLabel.setIcon(startImage);
		startLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel optionsLabel = new JLabel();
		optionsLabel.setIcon(optionsImage);
		optionsLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel exitLabel = new JLabel();
		exitLabel.setIcon(exitImage);
		exitLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// Add to panel
		bPanel.add(startLabel);
		bPanel.add(optionsLabel);
		bPanel.add(exitLabel);
		
		// Add listeners 
		startLabel.addMouseListener(new MouseListener() {
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				resize(100,50);
				Ruler.configure();
				MainWindow.changeToGamePanel();
			}

			public void mouseEntered(MouseEvent e) {
				resize(120,60);
			}

			public void mouseExited(MouseEvent e) {
				resize(100, 50);
			}
			
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			
			private void resize(int w, int h) {
				ImageIcon startImage = new ImageIcon("../images/buttons/startb.png");
				startImage = GraphicUtils.getScaledImage(startImage, w, h);
				startLabel.setIcon(startImage);
			}
		});
		
		optionsLabel.addMouseListener(new MouseListener() {
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				resize(140,50);
				MainWindow.changeToOptionsPanel();
			}
						
			public void mouseEntered(MouseEvent e) {
				resize(168,60);
			}

			public void mouseExited(MouseEvent e) {
				resize(140, 50);
			}
			
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}	
			
			private void resize(int w, int h) {
				ImageIcon optionsImage = new ImageIcon("../images/buttons/optionsb.png");
				optionsImage = GraphicUtils.getScaledImage(optionsImage, w, h);
				optionsLabel.setIcon(optionsImage);
			}
		});
		
		exitLabel.addMouseListener(new MouseListener() {
			// Quit
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}

			// Increases image size
			public void mouseEntered(MouseEvent e) {
				resize(96,60);
			}

		    // Change image back to original size
			public void mouseExited(MouseEvent e) {
				resize(80,50);
			}

			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			
			private void resize(int w, int h) {
				ImageIcon exitImage = new ImageIcon("../images/buttons/exitb.png");
				exitImage = GraphicUtils.getScaledImage(exitImage, w, h);
			    exitLabel.setIcon(exitImage);
			}
		});
		
		// Add button panel to menu panel
		this.add(bPanel);
	}
	

}
