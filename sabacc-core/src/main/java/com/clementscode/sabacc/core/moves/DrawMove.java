package com.clementscode.sabacc.core.moves;

import com.clementscode.sabacc.core.deck.Card;

public class DrawMove extends Move {

	public enum Type {
		DRAW, TRADE, HOLD
	};

	protected Card card;

	protected Type type;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
