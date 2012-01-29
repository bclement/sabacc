package com.clementscode.sabacc.core;

public class Config {

	public enum ShiftTimes {
		AFTER_DRAWS, AFTER_BETS, AFTER_BOTH, NEVER
	};

	protected ShiftTimes shiftTimes = ShiftTimes.AFTER_BETS;

	protected int ante = 2;

	protected int minBet = 2;

	protected int maxBet = Integer.MAX_VALUE;

	protected int bombPenalty = 5;

	protected int shiftPercent = 17;

	public ShiftTimes getShiftTimes() {
		return shiftTimes;
	}

	public void setShiftTimes(ShiftTimes shiftTimes) {
		this.shiftTimes = shiftTimes;
	}

	public int getAnte() {
		return ante;
	}

	public void setAnte(int ante) {
		this.ante = ante;
	}

	public int getMinBet() {
		return minBet;
	}

	public void setMinBet(int minBet) {
		this.minBet = minBet;
	}

	public int getMaxBet() {
		return maxBet;
	}

	public void setMaxBet(int maxBet) {
		this.maxBet = maxBet;
	}

	public int getBombPenalty() {
		return bombPenalty;
	}

	public void setBombPenalty(int bombPenalty) {
		this.bombPenalty = bombPenalty;
	}

	public int getShiftPercent() {
		return shiftPercent;
	}

	public void setShiftPercent(int shiftPercent) {
		this.shiftPercent = shiftPercent;
	}

}
