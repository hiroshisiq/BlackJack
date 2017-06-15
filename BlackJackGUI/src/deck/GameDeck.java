package deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameDeck {
	private ArrayList<Card> cards   = new ArrayList<Card>();
	private ArrayList<Card> discard = new ArrayList<Card>();
	
	public GameDeck(int number) {
		// Generate the game deck from standard decks
		for(int i = number; i > 0; i--) {
			StandardDeck tmpDeck = new StandardDeck();
			cards.addAll(tmpDeck.getCards());
			tmpDeck = null;
		}
		
		// shuffle deck
		if (number >= 1) {
			shuffle();
		}
	}
	
	public void discard(Card card) {
		discard.add(card);
	}
	
	public int getDeckSize() {
		return cards.size();
	}
	
	private void shuffle() {
		long seed = System.nanoTime();
		Collections.shuffle(cards, new Random(seed));
	}
	
	private void reset() {
		cards.addAll(discard);
		discard.clear();
		System.gc();
		shuffle();
	}
	
	private Card popCard() {
		Card tmp = cards.get(cards.size() - 1);
		cards.remove(cards.size() - 1);
		return tmp;
	}
	
	Card takeCard() {
		if(cards.size() < 1) {
			reset();			
		}
		
		return popCard();
	}
}
