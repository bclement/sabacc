package com.clementscode.sabacc.core.deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import com.clementscode.sabacc.core.deck.SuitCard.Suit;

public class Deck {

	protected ArrayList<Card> cards = new ArrayList<Card>(72);

	public Deck() {
		this(0);
	}

	protected Deck(Deck d) {
		cards.addAll(d.cards);
	}

	public Deck(int shuffleCount) {
		// suited
		for (Suit s : Suit.values()) {
			populateSuit(s);
		}
		// special
		cards.add(new SpecialCard(0));
		cards.add(new SpecialCard(0));
		cards.add(new SpecialCard(-2));
		cards.add(new SpecialCard(-2));
		cards.add(new SpecialCard(-8));
		cards.add(new SpecialCard(-8));
		cards.add(new SpecialCard(-11));
		cards.add(new SpecialCard(-11));
		cards.add(new SpecialCard(-13));
		cards.add(new SpecialCard(-13));
		cards.add(new SpecialCard(-14));
		cards.add(new SpecialCard(-14));
		cards.add(new SpecialCard(-15));
		cards.add(new SpecialCard(-15));
		cards.add(new SpecialCard(-17));
		cards.add(new SpecialCard(-17));
		shuffle(shuffleCount);
	}

	public Deck clone() {
		return new Deck(this);
	}

	public void shuffle(int times) {
		for (int i = 0; i < times; ++i) {
			Collections.shuffle(cards);
		}
	}

	public Card deal() {
		return cards.remove(cards.size() - 1);
	}

	public void add(Card... c) {
		add(Arrays.asList(c));
	}

	public void add(Collection<Card> c) {
		cards.addAll(c);
	}

	protected void populateSuit(Suit s) {
		for (int i = 1; i < 16; ++i) {
			cards.add(new SuitCard(i, s));
		}
	}
}
