/* 
 *  Author:   Anderson Hiroshi de Siqueira 
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
	private static final int darkFactor = -20;
	JLabel reCLabel = new JLabel();

	GamePanel() {
		setLayout(new BorderLayout());
		GraphicUtils.setGreenBackground(this);
		
		// Generate grid for north buttons
		JPanel northBPanel = new JPanel(new GridLayout(1,2));		
		
		// Buttons
		addBackButton(northBPanel);
		addMuteButton(northBPanel);
		addGameButtons();
		
		// Remaining cards
		showRemaningCards();
		add(reCLabel, BorderLayout.EAST);
		
		// Add to option panel
		add(northBPanel, BorderLayout.NORTH);
		
		firstGame = false;	
	}
	
	private void addBackButton(JPanel panel) {
		// Create label
		JLabel back = new JLabel();
		back.setHorizontalAlignment(JLabel.LEFT);
		
		// Load image
		String imagePath = "../images/buttons/back2.png";
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
				GraphicUtils.playSound("pop");
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
		panel.add(box);
	}
	
	private void addMuteButton(JPanel panel) {
		// Create label
		JLabel label = new JLabel();
		//back.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setAlignmentX(RIGHT_ALIGNMENT);;

		// Load image
		String muteImgPath = "../images/buttons/mute.png";
		String voluImgPath = "../images/buttons/volume.png";
		
		// Checks what image to load
		if(GraphicUtils.isMute())
			GraphicUtils.loadImage(label, muteImgPath, 50, 50, -30);
		else
			GraphicUtils.loadImage(label, voluImgPath, 50, 50, -30);
		
		// Add listener
		label.addMouseListener(new MouseListener() {			
			// Change to game panel
			public void mousePressed(MouseEvent e) {				
				GraphicUtils.changeMuteStatus();
				
				// Checks what image to load
				if(GraphicUtils.isMute())
					GraphicUtils.loadImage(label, muteImgPath, 50, 50, -30);
				else
					GraphicUtils.loadImage(label, voluImgPath, 50, 50, -30);				
			}

			public void mouseEntered(MouseEvent e) {
				// Checks what image to load
				if(GraphicUtils.isMute())
					GraphicUtils.loadImage(label, muteImgPath, 50, 50, -50);
				else
					GraphicUtils.loadImage(label, voluImgPath, 50, 50, -50);
				
				GraphicUtils.playSound("pop");
			}

			public void mouseExited(MouseEvent e) {
				// Checks what image to load
				if(GraphicUtils.isMute())
					GraphicUtils.loadImage(label, muteImgPath, 50, 50, -30);
				else
					GraphicUtils.loadImage(label, voluImgPath, 50, 50, -30);
			}
			
			public void mouseReleased(MouseEvent e) {
				// Checks what image to load
				if(GraphicUtils.isMute())
					GraphicUtils.loadImage(label, muteImgPath, 50, 50, -30);
				else
					GraphicUtils.loadImage(label, voluImgPath, 50, 50, -30);
			}
			
			public void mouseClicked(MouseEvent e) {}
		});
		
		// Box layout
		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
		GraphicUtils.setGreenBackground(box);
		box.add(label);		
		
		// Add to panel
		panel.add(box);
	}

	private void addGameButtons() {
		JPanel buttonPanel = new JPanel(new GridLayout(2,3));
		GraphicUtils.setGreenBackground(buttonPanel);
		
		// Add buttons
		showRemaningCards();
		addHitButton(buttonPanel);
		addDoubleButton(buttonPanel);
		addBetButton(buttonPanel);
		addStandButton(buttonPanel);
		addSurrenderButton(buttonPanel);
		addBetValueButtons(buttonPanel);
		
		// Add to game panel
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void addHitButton(JPanel panel ) {
		// Generate label
		JLabel hit = new JLabel();
		hit.setHorizontalAlignment(JLabel.CENTER);
		
		// Load Images
		String path = "../images/buttons/hit.png";
		int w = 60, h = 50;
		GraphicUtils.loadImage(hit, path, w, h, 0);
		
		// Add listener
		hit.addMouseListener(new MouseListener() {
			// activate button 
			private boolean isButtonActive() {
				return (hasBet == true) && (hasStand == false) && (Ruler.getPlayerPoints() < 21);
			}
			
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				if(isButtonActive()) {  
					// Makes button 70x brighter
					GraphicUtils.loadImage(hit, path, w+1, h, 3*darkFactor);
					
					// Get card and update panel
					Ruler.playerHit();
					addCardGrid();
					
					// Play sound 
					GraphicUtils.playSound("flip");
					
					if(Ruler.getPlayerPoints() > 21) {
						hasStand = true;
						Ruler.dealerPlay();
						
						// checks if player wins and display the message
						if(Ruler.evaluateWinner() == 0) {
							Ruler.playerAddPrize();
							addCardGrid("You win [Click here to continue]");
						}  else {
							addCardGrid("Dealer win [Click here to continue]");
						}
					}
				}
			}
						
			public void mouseEntered(MouseEvent e) {
				// Makes button 20x brighter
				if(isButtonActive()) {
					GraphicUtils.loadImage(hit, path, w, h, darkFactor);
					GraphicUtils.playSound("pop");
				}
			}

			public void mouseExited(MouseEvent e) {
				GraphicUtils.loadImage(hit, path, w, h, 0);
			}
			
			public void mouseReleased(MouseEvent e) {
				if(isButtonActive()) {
					GraphicUtils.loadImage(hit, path, w, h, darkFactor);
				} else {
					GraphicUtils.loadImage(hit, path, w, h, 0);
				}
			}	
			
			public void mouseClicked(MouseEvent e) {}
		});
		
		// Add to panel
		panel.add(hit );
	}
	
	private void addDoubleButton(JPanel panel ) {
		// Generate label
		JLabel button = new JLabel();
		button.setHorizontalAlignment(JLabel.CENTER);
		
		// Load Images
		String path = "../images/buttons/double.png";
		int w = 120, h = 50;
		GraphicUtils.loadImage(button, path, w, h, 0);
		
		// Add listener
		button.addMouseListener(new MouseListener() {
			// activate button 
			private boolean isButtonActive() {
				return (hasBet == true) && (hasStand == false) && Ruler.playerCanDouble()
						&& (Ruler.getPlayerPoints() < 21) && (Ruler.getPlayerCards().size() == 2);
			}
			
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				if(isButtonActive()) {  
					// Makes button 70x brighter
					GraphicUtils.loadImage(button, path, w+1, h, 3*darkFactor);
					Ruler.playerDouble();
					addCardGrid();
					
					// Play sound 
					GraphicUtils.playSound("flip");
					
					// Dealers turn
					hasStand = true;
					Ruler.dealerPlay();
					
					// Evaluate if player wins
					if(Ruler.evaluateWinner() == 0) {
						Ruler.playerAddPrize();
						addCardGrid("You win [Click here to continue]");
					}  else {
						addCardGrid("Dealer win [Click here to continue]");
					}
				}
			}
						
			public void mouseEntered(MouseEvent e) {
				// Makes button 20x brighter
				if(isButtonActive()) {
					GraphicUtils.loadImage(button, path, w, h, darkFactor);
					GraphicUtils.playSound("pop");
				}
			}

			public void mouseExited(MouseEvent e) {
				GraphicUtils.loadImage(button, path, w, h, 0);
			}
			
			public void mouseReleased(MouseEvent e) {
				if(isButtonActive()) {
					GraphicUtils.loadImage(button, path, w, h, darkFactor);
				} else {
					GraphicUtils.loadImage(button, path, w, h, 0);
				}
			}	
			
			public void mouseClicked(MouseEvent e) {}
		});
		
		// Add to panel
		panel.add(button );
	}
	
	private void addStandButton(JPanel panel ) {
		// Generate label
		JLabel stand = new JLabel();
		stand.setHorizontalAlignment(JLabel.CENTER);
		
		// Load Images
		String path = "../images/buttons/stand.png";
		int w = 100, h = 50;
		GraphicUtils.loadImage(stand, path, w, h, 0);
		
		// Add listener
		stand.addMouseListener(new MouseListener() {
			// activate button 
			private boolean isButtonActive() {
				return (hasBet == true && hasStand == false);
			}
			
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				if(isButtonActive()) {
					GraphicUtils.loadImage(stand, path, w+1, h, 3*darkFactor);
					hasStand = true;
					
					// Play sound
					GraphicUtils.playSound("flip");
					
					// Dealer play
					Ruler.dealerPlay();
					
					// If player wins
					if(Ruler.evaluateWinner() == 0) {
						Ruler.playerAddPrize();
						addCardGrid("You win [Click here to continue]");
					}  else {
						addCardGrid("Dealer win [Click here to continue]");
					}					
				}
			}
						
			public void mouseEntered(MouseEvent e) {
				if(isButtonActive()) {
					GraphicUtils.loadImage(stand, path, w, h, darkFactor);
					GraphicUtils.playSound("pop");
				}
			}

			public void mouseExited(MouseEvent e) {
				GraphicUtils.loadImage(stand, path, w, h, 0);
			}
			
			public void mouseReleased(MouseEvent e) {
				if(isButtonActive())
					GraphicUtils.loadImage(stand, path, w, h, darkFactor);
				else 
					GraphicUtils.loadImage(stand, path, w, h, 0);
			}	
			
			public void mouseClicked(MouseEvent e) {}			
		});
		
		// Add to panel
		panel.add(stand );
	}
	
	private void addSurrenderButton(JPanel panel ) {
		// Generate label
		JLabel label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		
		// Load Images
		String path = "../images/buttons/surrender.png";
		int w = 180, h = 50;
		GraphicUtils.loadImage(label, path, w, h, 0);
		
		// Add listener
		label.addMouseListener(new MouseListener() {
			// activate button 
			private boolean isButtonActive() {
				return (hasBet == true && hasStand == false && Ruler.getPlayerCards().size() == 2);
			}
			
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				if(isButtonActive()) {
					GraphicUtils.loadImage(label, path, w+1, h, 3*darkFactor);
					hasStand = true;
					Ruler.playerSurrender();
					addCardGrid("You surrender [Click here to continue]");					
				}
			}
						
			public void mouseEntered(MouseEvent e) {
				if(isButtonActive()) {
					GraphicUtils.loadImage(label, path, w, h, darkFactor);
					GraphicUtils.playSound("pop");
				}
			}

			public void mouseExited(MouseEvent e) {
				GraphicUtils.loadImage(label, path, w, h, 0);
			}
			
			public void mouseReleased(MouseEvent e) {
				if(isButtonActive())
					GraphicUtils.loadImage(label, path, w, h, darkFactor);
				else 
					GraphicUtils.loadImage(label, path, w, h, 0);
			}	
			
			public void mouseClicked(MouseEvent e) {}			
		});
					
		// Add to panel
		panel.add(label);
	}

	private void addBetButton(JPanel panel ) {
		JPanel betPanel = new JPanel();
		GraphicUtils.setGreenBackground(betPanel);
		
		// Generate label
		JLabel bet = new JLabel();
		bet.setHorizontalAlignment(JLabel.CENTER);
		
		// Load Images
		String path = "../images/buttons/bet.png";
		int w = 60, h = 50;
		GraphicUtils.loadImage(bet, path, w, h, 0);
				
		// Add listener 
		bet.addMouseListener(new MouseListener() {
			// activate button 
			private boolean isButtonActive() {
				return (Ruler.getBet() > 0 && hasBet == false);
			}
			
			// Change to game panel
			public void mousePressed(MouseEvent e) {
				if(isButtonActive()) {
					GraphicUtils.loadImage(bet, path, w+1, h, 3*darkFactor);
					Ruler.playerHit();
					Ruler.playerHit();
					Ruler.dealerInitHand();
					hasBet = true;
					addCardGrid();
					
					// Play sound 
					GraphicUtils.playSound("flip");
					
					if(Ruler.dealerHasBlackjack()) {
						hasBet = true;
						hasStand = true;
						Ruler.dealerPlay();
						addCardGrid("Dealer has Blackjack [Click here to continue]");
					}
				}
			}
						
			public void mouseEntered(MouseEvent e) {
				if(isButtonActive()) {
					GraphicUtils.loadImage(bet, path, w, h, darkFactor);
					GraphicUtils.playSound("pop");
				}
			}

			public void mouseExited(MouseEvent e) {
				GraphicUtils.loadImage(bet, path, w, h, 0);
			}
			
			public void mouseReleased(MouseEvent e) {
				if(isButtonActive()) {
					GraphicUtils.loadImage(bet, path, w, h, darkFactor);
				} else {
					GraphicUtils.loadImage(bet, path, w, h, 0);
				}
			}	
			
			public void mouseClicked(MouseEvent e) {}
		});
		
		// add to panel
		panel.add(bet);
	}
	
	private void addBetValueButtons(JPanel panel) {
		// Create bet values buttons
		JPanel betValuePanel = new JPanel(new GridLayout(1,3));
		GraphicUtils.setGreenBackground(betValuePanel);
		addBetValueButton(betValuePanel, 100);
		addBetValueButton(betValuePanel, 250);
		addBetValueButton(betValuePanel, 500);
		
		// add to panel
		panel.add(betValuePanel);
	}
	
	private void addBetValueButton(JPanel panel, int value) {
		// Generate label
		JLabel bet = new JLabel();
		bet.setHorizontalAlignment(JLabel.CENTER);
		
		// Load Images
		String path = "../images/buttons/" + Integer.toString(value) + ".png";
		int w = value > 99 ? 60 : 40, h = 50;
		GraphicUtils.loadImage(bet, path, w, h, 0);		
		
		// Add listener
		bet.addMouseListener(new MouseListener() {
			// activate button 
			private boolean isButtonActive() {	
				return hasBet == false && Ruler.getMoney() > 0;
			}

			// Change to game panel
			public void mousePressed(MouseEvent e) {
				if(isButtonActive()) {
					GraphicUtils.loadImage(bet, path, w+1, h, 3*darkFactor);
					Ruler.playerBet(value);
					addCardGrid();
					
					// Play sound
					GraphicUtils.playSound("chips" + Integer.toString(value));
				}
			}
						
			public void mouseEntered(MouseEvent e) {
				if(isButtonActive()) {
					GraphicUtils.loadImage(bet, path, w, h, darkFactor);
					GraphicUtils.playSound("pop");
				}
			}

			public void mouseExited(MouseEvent e) {
				GraphicUtils.loadImage(bet, path, w, h, 0);
			}
			
			public void mouseReleased(MouseEvent e) {
				if(isButtonActive())
					GraphicUtils.loadImage(bet, path, w+1, h, darkFactor);
				else
					GraphicUtils.loadImage(bet, path, w+1, h, 0);
			}	
			
			public void mouseClicked(MouseEvent e) {}
		});
		
		panel.add(bet);
	}
	
	private void addCardGrid() {
		JPanel cardGrid = new JPanel(new GridLayout(3,1));
		GraphicUtils.setGreenBackground(cardGrid);
		
		// Add cards and display
		showRemaningCards();
		showCards(cardGrid, Ruler.getDealerCards());
		showStatusLabel(cardGrid, "");
		showCards(cardGrid, Ruler.getPlayerCards());
		
		// Add to game panel
		add(cardGrid);
		cardGrid.repaint();
	}
	
	private void addCardGrid(String extraText) {
		JPanel cardGrid = new JPanel(new GridLayout(3,1));
		GraphicUtils.setGreenBackground(cardGrid);
		
		// Add cards and display
		showRemaningCards();
		showCards(cardGrid, Ruler.getDealerCards());
		showStatusLabel(cardGrid, extraText);
		showCards(cardGrid, Ruler.getPlayerCards());
		
		// Add to game panel
		add(cardGrid);
	}
	
	private void showCards(JPanel panel, ArrayList<Card> hand) {		
		ArrayList<JLabel> cards = new ArrayList<JLabel>();
		
		// cards width and height 
		int w = 80,h = 120;
		
		// Load cards
		for(Card card : hand) {
			// Load card image
			ImageIcon img = new ImageIcon(card.filePath());
			img = GraphicUtils.getScaledImage(img, w, h);
			
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
		
		// Generate another box layout
		JPanel box2 = new JPanel();
		box2.setLayout(new BoxLayout(box2, BoxLayout.Y_AXIS));
		GraphicUtils.setGreenBackground(box2);
		box2.add(box);
		
		// Add to panel
		panel.add(box2);		
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
					
					// Play sound
					GraphicUtils.playSound("flip");
					
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
	
	private void showRemaningCards() {
		String text = "Deck Cards " + Ruler.getRemainingCards();
		reCLabel.setText(GraphicUtils.verticalHtml(text));
		
		reCLabel.setHorizontalAlignment(JLabel.CENTER);
		reCLabel.setFont(new Font(reCLabel.getFont().getFontName(), Font.PLAIN, 20));
		reCLabel.setForeground(new Color(0,0,0));		
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
