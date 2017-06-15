package deck;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> cards;
	
	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	public int getPoints() {
		int points = 0;
		
		if(!cards.isEmpty()) {
			
			// Check for royal
			boolean hasRoyal = false;
			for(Card card : cards) {
				// Check if hand has a royal
				if(card.rank.value == 10 && card.rank != Rank.TEN) {
					hasRoyal = true;
				}					
			}
			
			// Sum points
			for(Card card : cards) {
				if(hasRoyal && card.rank == Rank.ACE) {
					points += 10;					
				} else {
					points += card.rank.value();
				}
			}
		}
		
		return points;
	}
	
	public void takeCard(GameDeck deck) {
		cards.add(deck.takeCard());
	}
	
	public void discard(GameDeck deck) {
		for(int i = cards.size() - 1; i >= 0; i--) {
			deck.discard(cards.get(i));
			cards.remove(i);
		}
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public boolean blackjack() {
		if(cards.size() == 2) {
			// Check for royal and ace
			boolean hasAce = false, hasRoyal = false;
			for(Card card : cards) {
				// Check if hand has a royal
				if(card.rank.value == 10 && card.rank != Rank.TEN) {
					hasRoyal = true;
				}
				
				// Check if hand has an ace
				if(card.rank == Rank.ACE) {
					hasAce = true;
				}
			}
			
			// Has blackjack
			if(hasAce && hasRoyal) {
				return true;
			}
		} 
		
		// Else return false
		return false;
	}
	
}
