package com.clementscode.sabacc.core.deck;

public class SpecialCard extends Card {

	public SpecialCard(int value) {
		super(value);
	}

	@Override
	public String toString() {
		switch (getValue()) {
		case 0:
			return "Idiot";
		case -2:
			return "Queen of Air and Darkness";
		case -8:
			return "Endurance";
		case -11:
			return "Balance";
		case -13:
			return "Demise";
		case -14:
			return "Moderation";
		case -15:
			return "The Evil One";
		case -17:
			return "The Star";
		default:
			return "Invalid Card";
		}
	}

	@Override
	public boolean equals(Card c) {
		if (c == null) {
			return false;
		}
		if (!(c instanceof SpecialCard)) {
			return false;
		}
		return c.value == this.value;
	}
}
