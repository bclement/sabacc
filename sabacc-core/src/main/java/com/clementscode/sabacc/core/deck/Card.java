package com.clementscode.sabacc.core.deck;

public abstract class Card {

	protected final int value;

	public abstract String toString();

	public abstract boolean equals(Card c);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	public Card(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (value != other.value)
			return false;
		return true;
	}

}
