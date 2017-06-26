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

package deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import controller.Ruler;

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
		
		Ruler.setRemainingCards(this.getDeckSize());
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
		Ruler.setRemainingCards(getDeckSize());
		return tmp;
	}
	
	Card takeCard() {
		if(cards.size() < 1) {
			reset();			
		}
		
		return popCard();
	}
}
