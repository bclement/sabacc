package com.clementscode.sabacc.core;

import java.util.ArrayList;

import com.clementscode.sabacc.core.comm.InvalidMoveException;
import com.clementscode.sabacc.core.deck.Card;

public class Player {

	protected ArrayList<Card> hand = new ArrayList<Card>();

	protected int stack;

	protected final int id;

	protected ArrayList<Card> locked = new ArrayList<Card>();

	public Player(int id, int stack) {
		this.id = id;
		this.stack = stack;
	}

	public Player clone() {
		Player rval = new Player(id, stack);
		rval.hand = new ArrayList<Card>(hand);
		rval.locked = new ArrayList<Card>(locked);
		return rval;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public boolean hasCardInHand(Card c) {
		return hand.contains(c);
	}

	public boolean hasCardLocked(Card c) {
		return locked.contains(c);
	}

	public void addCardToHand(Card c) {
		hand.add(c);
	}

	public void lockCard(Card c) throws InvalidMoveException {
		if (!hasCardInHand(c)) {
			String msg = String.format(
					"Player %d doesn't have card '%s' in hand", id, c
							.toString());
			throw new InvalidMoveException(msg);
		}
		hand.remove(c);
		locked.add(c);
	}

	public void unlockCard(Card c) {
		if (!hasCardLocked(c)) {
			return;
		}
		locked.remove(c);
		hand.add(c);
	}

	public void removeFromHand(Card c) throws InvalidMoveException {
		if (!hasCardInHand(c)) {
			String msg = "Player " + id + " doesn't have '" + c + "' in hand";
			throw new InvalidMoveException(msg);
		}
		hand.remove(c);
	}

	public void removeChips(int sum) throws InvalidMoveException {
		if (sum > stack) {
			throw new InvalidMoveException("Player " + id
					+ " doesn't have enough chips");
		}
		stack -= sum;
	}

	public void addChips(int sum) {
		stack += sum;
	}

	public int getStack() {
		return stack;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Card> getLocked() {
		return locked;
	}

}
