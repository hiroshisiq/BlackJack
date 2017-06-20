/* 
 *  Autor:   Anderson Hiroshi de Siqueira 
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
	
	void hitHole(GameDeck deck) {
		// Take another card
		hand.takeCard(deck);
		hand.getCards().get(hand.getCards().size()-1).setIsHole(true);
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
