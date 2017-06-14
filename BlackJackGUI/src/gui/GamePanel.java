package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	GamePanel() {
		setLayout(new BorderLayout());
		GraphicUtils.setGreenBackground(this);
		addBackButton();
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
				resize(55,55);
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
		
		// Add to panel
		add(back, BorderLayout.NORTH);
	}

}
