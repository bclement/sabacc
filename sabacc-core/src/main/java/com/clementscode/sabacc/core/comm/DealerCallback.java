package com.clementscode.sabacc.core.comm;

import com.clementscode.sabacc.core.deck.Card;

public interface DealerCallback {

	public Card drawAndShift(int playerId, DrawMove move)
			throws InvalidMoveException;

}
