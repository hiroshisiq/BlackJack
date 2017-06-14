package controller;

import java.util.ArrayList;

import deck.Card;
import deck.GameDeck;
import deck.Hand;

public class Dealer {
	private Hand hand;
	
	public Dealer() {
		this.hand = new Hand();
	}
	
	public int getPoints() {
		return hand.getPoints();
	}
	
	public ArrayList<Card> getCards() {
		return hand.getCards();
	}
	
	boolean hasBlackjack() {
		return hand.blackjack();
	}
	
	void hit(GameDeck deck) {
		// Take another card
		hand.takeCard(deck);
	}
	
	void startHand(GameDeck deck) {
		// Take two cards
		for(int i = 0; i < 2; i++) {
			hand.takeCard(deck);
		}
	}
	
	void discardHand(GameDeck deck) {
		// Discard hand 
		hand.discard(deck);
	}

}
