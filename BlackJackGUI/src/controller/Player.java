package controller;

import java.util.ArrayList;
import deck.Hand;
import deck.Card;
import deck.GameDeck;

public class Player {
	private Hand hand;
	private int money;
	private int bet;
	
	public Player(int money) {
		this.money = money;
		this.hand = new Hand();
		this.bet = 0;
	}
	
	public int getPoints() {
		return hand.getPoints();
	}
	
	public ArrayList<Card> getCards() {
		return hand.getCards();
	}
	
	public int getBet() {
		return bet;
	}
	
	public void resetBet() {
		bet = 0;
	}
	
	void bet(int value) {
		if(value <= money) { 
			bet += value;
			money -= value;
		} else {
			bet += money;
			money = 0;
		}
	}
	
	int getMoney() {
		return money;
	}
	
	void addPrize() {
		money += 2*bet;
		bet = 0;
	}
	
	void doubleBet() {
		bet *= 2;
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
		// Discard hand and reset bet
		hand.discard(deck);
		bet = 0;
	}

}
