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

package deck;

public class Card {
	Rank rank;
	Suit suit;
	boolean isHole = false;
	static int cardStyle = 1;
	
	public void setIsHole(boolean isHole) {
		this.isHole = isHole;
	}
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public static void setCardStyle(int n) {
		cardStyle = n;
	}
	
	public static String getStylePath() {
		return "../images/cardback/cardback" + Integer.toString(cardStyle) + ".png";
	}
	
	public String filePath() {
		if(isHole)
			return "../images/cardback/cardback" + Integer.toString(cardStyle) + ".png";
		else 
			return "../images/cards/" + this.toString() + ".png";
	}

	public String toString() {
		String name = null;
		
		switch (rank) {
		case ACE:
			name = "ace";
			break;
		case TWO:
			name = "2";
			break;
		case THREE:
			name = "3";
			break;
		case FOUR:
			name = "4";
			break;
		case FIVE:
			name = "5";
			break;
		case SIX:
			name = "6";
			break;
		case SEVEN:
			name = "7";
			break;
		case EIGHT:
			name = "8";
			break;
		case NINE:
			name = "9";
			break;
		case TEN:
			name = "10";
			break;
		case JACK:
			name = "jack";
			break;
		case QUEEN:
			name = "queen";
			break;
		case KING:
			name = "king";
			break;
		}
		
		name += "_of_";
		
		switch (suit) {
		case CLUBS:
			name += "clubs";
			break;
		case DIAMONDS:
			name += "diamonds";
			break;
		case HEARTS:
			name += "hearts";
			break;
		case SPADES:
			name += "spades";
			break;
		}
		
		return name;
	}

	
}
