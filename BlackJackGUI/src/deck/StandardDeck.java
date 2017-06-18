package deck;

import java.util.ArrayList;

class StandardDeck {
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	StandardDeck() {
		// Generate a standard 52 card deck
		for(Suit suit : Suit.values()) {
			for(Rank rank : Rank.values()) {
				Card tmpCard = new Card(rank, suit);
				cards.add(tmpCard);
			}
		}
	}
	
	ArrayList<Card> getCards() {
		// Return a standard deck
		return cards;
	}
}
