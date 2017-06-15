package deck;

public class Card {
	Rank rank;
	Suit suit;
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
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
