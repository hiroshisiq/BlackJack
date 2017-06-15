package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Ruler;
import deck.Card;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private boolean hasBet = false;
	private boolean hasStand = false;
	private boolean firstGame;

	
	GamePanel() {
		setLayout(new BorderLayout());
		GraphicUtils.setGreenBackground(this);
		
		// Buttons
		addBackButton();
		addGameButtons();
		
		firstGame = false;	
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
				if(hasBet == true && hasStand == false) {  
					Ruler.playerHit();
					addCardGrid();
				}
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
				if(hasBet == true && hasStand == false) {
					hasStand = true;
					Ruler.dealerPlay();
					
					// If player wins
					if(Ruler.evaluateWinner() == 0) {
						Ruler.playerAddPrize();
						addCardGrid("You win [Click to continue]");
					}  else {
						addCardGrid("Dealer win [Click to continue]");
					}					
				}
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
		
		// Add listener 
		bet.addMouseListener(new MouseListener() {
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				resize(55, 50);
				if(Ruler.getBet() > 0 && hasBet == false) {
					Ruler.playerHit();
					Ruler.playerHit();
					Ruler.dealerInitHand();
					hasBet = true;
					addCardGrid();
				}
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
				ImageIcon img = new ImageIcon("../images/buttons/bet.png");
				img = GraphicUtils.getScaledImage(img, w, h);
				bet.setIcon(img);	
			}			
		});
		
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
				if(hasBet == false) {
					Ruler.playerBet(value);
					addCardGrid();
				}
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
	
	private void addCardGrid() {
		JPanel cardGrid = new JPanel(new GridLayout(3,1));
		GraphicUtils.setGreenBackground(cardGrid);
		
		// Add cards and display
		showCards(cardGrid, Ruler.getDealerCards());
		showStatusLabel(cardGrid, "");
		showCards(cardGrid, Ruler.getPlayerCards());
		
		// Add to game panel
		add(cardGrid);
	}
	
	private void addCardGrid(String extraText) {
		JPanel cardGrid = new JPanel(new GridLayout(3,1));
		GraphicUtils.setGreenBackground(cardGrid);
		
		// Add cards and display
		showCards(cardGrid, Ruler.getDealerCards());
		showStatusLabel(cardGrid, extraText);
		showCards(cardGrid, Ruler.getPlayerCards());
		
		// Add to game panel
		add(cardGrid);
	}
	
	private void showCards(JPanel panel, ArrayList<Card> hand) {
		ArrayList<JLabel> cards = new ArrayList<JLabel>();
		
		for(Card card : hand) {
			// Load card image
			ImageIcon img = new ImageIcon(card.filePath());
			img = GraphicUtils.getScaledImage(img, 70, 100);
			
			// Generate label
			JLabel cardLabel = new JLabel();
			cardLabel.setIcon(img);
			cardLabel.setHorizontalAlignment(JLabel.CENTER);
			
			cards.add(cardLabel);
		}
		
		// Generate a box layout 
		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS));
		GraphicUtils.setGreenBackground(box);
		
		// Add cards images to box 
		for(JLabel cardLabel : cards) {
			box.add(cardLabel);
		}
		
		// Add to panel
		panel.add(box);		
	}
		
	private void showStatusLabel(JPanel panel, String text) {
		// Configure status label
		JLabel status = new JLabel();
		status.setText("<html><body><center>" + text + "<br>" 
				+ "Your money: "+ Ruler.getMoney() 
				+ " Bet: " + Ruler.getBet() 
				+ "</center></body></html>");
		status.setHorizontalAlignment(JLabel.CENTER);
		status.setFont(new Font(status.getFont().getFontName(), Font.PLAIN, 30));
		status.setForeground(new Color(0,0,0));
		
		// Add listener
		status.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				if(hasBet == true && hasStand == true) {
					hasBet = false;
					hasStand = false;
					Ruler.discartHands();
					addCardGrid();
					gameRepaint();
					
					status.setText("<html><body><center>" + text + "<br>" 
							+ "Your money: "+ Ruler.getMoney() 
							+ " Bet: " + Ruler.getBet() 
							+ "</center></body></html>");
					
					if(Ruler.hasMoney() == false) {
						JOptionPane.showMessageDialog(status,
							    "You have no money left!",
							    "Blackjack Alert",
							    JOptionPane.PLAIN_MESSAGE);
						
						MainWindow.changeToMenuPanel();
						firstGame = false;
					}
					
					status.repaint();
				}							
			}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		// Add to panel
		panel.add(status);
	}
	
	void gameRepaint() {
		repaint();
	}
	
	void reset() {
		if(firstGame == false) {
			addCardGrid();
		}	
	}
	
}
