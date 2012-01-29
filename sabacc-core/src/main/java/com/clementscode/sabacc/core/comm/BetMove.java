package com.clementscode.sabacc.core.comm;

public class BetMove extends Move {

	public enum Type {
		FOLD, RAISE, CALL, SEE, STAY
	};

	protected Type type = Type.STAY;

	protected Integer credits = null;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

}
