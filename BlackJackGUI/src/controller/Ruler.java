package controller;

import java.util.ArrayList;
import deck.Card;
import deck.GameDeck;

public class Ruler {
	private static Dealer dealer;
	private static Player player;
	private static GameDeck gameDeck;
	private static int cardStyle = 1;
	private static int numberOfDecks = 4;
	private static int remainingCards = 0;
	
	private final static int initialMoney = 2000;
	
	public static void configure() {
		player = new Player(initialMoney);
		dealer = new Dealer();		
		gameDeck = new GameDeck(numberOfDecks);
	}
	
	public static ArrayList<Card> getPlayerCards() {
		return player.getCards();
	}
	
	public static int getPlayerPoints() {
		return player.getPoints();
	}
	
	public static void playerHit() {
		if(player.getPoints() < 21) {
			player.hit(gameDeck);
		}
	}
	
	public static int getBet() {
		return player.getBet();
	}
	
	public static int getMoney() {
		return player.getMoney();
	}
	
	public static void playerBet(int bet) {
		player.bet(bet);
	}
	
	public static void playerDouble() {
		player.doubleBet();
		player.hit(gameDeck);
	}
	
	public static boolean playerCanDouble() {
		return (player.getBet()/2 <= player.getMoney())? true : false;
	}
	
	public static void playerAddPrize() {
		player.addPrize();
	}
	
	public static ArrayList<Card> getDealerCards() {
		return dealer.getCards();
	}
	
	public static void dealerInitHand() {
		dealer.hit(gameDeck);
		dealer.hitHole(gameDeck);
	}
	
	public static void dealerPlay() {
		// Show all cards
		for(int i = 0; i < dealer.getCards().size(); i ++) {
			dealer.getCards().get(i).setIsHole(false);
		}
		
		if(player.getPoints() <= 21) {
			while(player.getPoints() > dealer.getPoints()) {
				dealer.hit(gameDeck);
			}
		}
	}
	
	public static boolean dealerHasBlackjack() {
		return dealer.hasBlackjack();
	}
	
	// Evaluate
	public static int evaluateWinner() {		
		// Return 1 if dealer win and 0 if player win 
		if(dealer.hasBlackjack() || player.getPoints() > 21) {
			player.resetBet();
			return 1;
		} else {
			if(dealer.getPoints() <= 21 && dealer.getPoints() >= player.getPoints()) {
				player.resetBet();
				return 1;
			} else {
				player.addPrize();
				return 0;
			}
		}
	}
	
	public static boolean hasMoney() {
		if(player.getMoney() == 0) 
			return false;
		else 
			return true;
	}
	
	public static void discartHands() {
		player.discardHand(gameDeck);
		dealer.discardHand(gameDeck);
	}
	
	public static void nextCardStyle() {
		if(cardStyle == 7)
			cardStyle = 1;
		else
			cardStyle++;
		
		Card.setCardStyle(cardStyle);
	}
	
	public static void prevCardStyle() {
		if(cardStyle == 1)
			cardStyle = 7;
		else
			cardStyle--;
		
		Card.setCardStyle(cardStyle);
	}
	
	public static String getNumOfDecks() {
		return Integer.toString(numberOfDecks);
	}
	
	// Next number of decks
	public static void nextNOD() {
		if(numberOfDecks == 8)
			numberOfDecks = 1;
		else
			numberOfDecks++;
	}
	
	// Previous number of decks
	public static void prevNOD() {
		if(numberOfDecks == 1)
			numberOfDecks = 8;
		else
			numberOfDecks--;
	}
	
	public static String getRemainingCards() {
		return Integer.toString(remainingCards);
	}
	
	public static void setRemainingCards(int n) {
		remainingCards = n;
	}
}
