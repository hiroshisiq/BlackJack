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
		if(bet <= money) {
			money -= bet;
			bet *= 2;		
		}
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
	
	void surrender() {
		money += bet/2;
		bet = 0;
	}

}
