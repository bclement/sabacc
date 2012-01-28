package com.clementscode.sabacc.core.deck;

public class SuitCard extends Card {

	public enum Suit {
		Sabers, Coins, Flasks, Staves;
	};

	protected final Suit suit;

	public SuitCard(int value, Suit suit) {
		super(value);
		this.suit = suit;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (getValue() < 12 && getValue() > 0) {
			sb.append(getValue());
		} else {
			switch (getValue()) {
			case 12:
				sb.append("Commander");
				break;
			case 13:
				sb.append("Mistress");
				break;
			case 14:
				sb.append("Master");
				break;
			case 15:
				sb.append("Ace");
			default:
				return "Invalid Card";
			}
		}
		sb.append(" of ").append(suit);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuitCard other = (SuitCard) obj;
		if (suit == null) {
			if (other.suit != null)
				return false;
		} else if (!suit.equals(other.suit))
			return false;
		return true;
	}

	@Override
	public boolean equals(Card c) {
		return this.equals((Object) c);
	}

}
