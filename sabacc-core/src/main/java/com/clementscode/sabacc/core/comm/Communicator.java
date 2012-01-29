package com.clementscode.sabacc.core.comm;

public interface Communicator {

	public void getDrawMove(int player, DealerCallback callback);

	public void getBetMove(int player, DealerCallback callback);

	public void updateState(GameState state);

	public void shift(int player, Shift shift);

}
