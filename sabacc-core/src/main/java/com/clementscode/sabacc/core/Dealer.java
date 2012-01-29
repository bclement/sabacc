package com.clementscode.sabacc.core;

import com.clementscode.sabacc.core.comm.Communicator;
import com.clementscode.sabacc.core.comm.DealerCallback;
import com.clementscode.sabacc.core.comm.DrawMove;
import com.clementscode.sabacc.core.comm.GameState;
import com.clementscode.sabacc.core.comm.InvalidMoveException;
import com.clementscode.sabacc.core.deck.Card;
import com.clementscode.sabacc.core.deck.Deck;

public class Dealer implements DealerCallback {

	protected Communicator communicator;

	protected Deck deck = new Deck();

	protected GameState state;

	private boolean winner = false;

	public Dealer(Communicator communicator, GameState state) {
		this.communicator = communicator;
		this.state = state;
	}

	public boolean runHand(Config conf) {
		drawRound(conf);
		betRound(conf);
		return false;
	}

	protected void drawRound(Config conf) {
		for (Player p : state.getPlayers()) {
			communicator.getDrawMove(p.getId(), this);
		}
		switch (conf.getShiftTimes()) {
		case AFTER_DRAWS:
		case AFTER_BOTH:
			shift(conf);
		}
	}

	protected void shift(Config conf) {

	}

	protected void betRound(Config conf) {

	}

	protected Player getPlayer(GameState s, int id) throws InvalidMoveException {
		Player rval = s.getPlayer(id);
		if (rval == null) {
			throw new InvalidMoveException("Player " + id + " not recognized");
		}
		return rval;
	}

	protected void updateState(GameState tmpState, Deck tmpDeck) {
		this.state = tmpState;
		this.deck = tmpDeck;
		communicator.updateState(tmpState);
	}

	public Card drawAndShift(int playerId, DrawMove move)
			throws InvalidMoveException {
		GameState tmpState = state.clone();
		Deck tmpDeck = deck.clone();
		Card rval = null;
		Player p = getPlayer(tmpState, playerId);
		rval = draw(p, move, tmpDeck);
		lock(p, move);
		updateState(tmpState, tmpDeck);
		return rval;
	}

	protected void lock(Player p, DrawMove move) throws InvalidMoveException {
		switch (move.getLockType()) {
		case UNLOCK:
			p.unlockCard(move.getUnlockCard());
			break;
		case TRADE:
			p.unlockCard(move.getUnlockCard());
		case LOCK:
			p.lockCard(move.getLockCard());
			break;
		case NONE:
			break;
		default:
			throw new InvalidMoveException("Unrecognized lock move "
					+ move.getLockType());
		}
	}

	protected Card draw(Player p, DrawMove move, Deck tmpDeck)
			throws InvalidMoveException {
		Card rval;
		switch (move.getType()) {
		case TRADE:
			Card old = move.getCard();
			p.removeFromHand(old);
			tmpDeck.add(old);
		case DRAW:
			rval = tmpDeck.deal();
			p.addCardToHand(rval);
			break;
		case HOLD:
			rval = null;
			break;
		default:
			throw new InvalidMoveException("Unrecognized draw move "
					+ move.getType());
		}
		return rval;
	}
}
