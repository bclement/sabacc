package com.clementscode.sabacc.core.comm;

import com.clementscode.sabacc.core.deck.Card;

public class DrawMove extends Move {

	public enum Type {
		DRAW, TRADE, HOLD
	};

	public enum LockType {
		LOCK, UNLOCK, TRADE, NONE
	};

	protected Card card;

	protected Type type = Type.HOLD;

	protected LockType lockType = LockType.NONE;

	protected Card lockCard;

	protected Card unlockCard;

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

	public LockType getLockType() {
		return lockType;
	}

	public void setLockType(LockType lockType) {
		this.lockType = lockType;
	}

	public Card getLockCard() {
		return lockCard;
	}

	public void setLockCard(Card lockCard) {
		this.lockCard = lockCard;
	}

	public Card getUnlockCard() {
		return unlockCard;
	}

	public void setUnlockCard(Card unlockCard) {
		this.unlockCard = unlockCard;
	}

}
