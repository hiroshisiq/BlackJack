package controller;

import java.util.ArrayList;
//import deck.Hand;
import deck.Card;
import deck.GameDeck;

public class Ruler {
	private static Dealer dealer;
	private static Player player;
	private static GameDeck gameDeck;
	
	private final static int initialMoney = 2000;
	
	public static void configure(int numberOfDecks) {
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
		player.hit(gameDeck);
	}
	
	public static void playerDouble() {
		
	}
	
	public static ArrayList<Card> getDealerCards() {
		return dealer.getCards();
	}
	
	public static void dealerPlay() {
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
			return 1;
		} else {
			if(dealer.getPoints() <= 21 && dealer.getPoints() >= player.getPoints()) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
