package com.clementscode.sabacc.core.comm;

import java.util.ArrayList;
import java.util.HashMap;

import com.clementscode.sabacc.core.Player;

public class GameState {

	protected ArrayList<Player> players;

	protected Integer winner;

	protected int handPot;

	protected int sabaccPot;

	private transient HashMap<Integer, Integer> _playerMap;

	public Player getPlayer(int id) {
		if (_playerMap == null) {
			if (players == null) {
				return null;
			}
			_playerMap = new HashMap<Integer, Integer>(players.size());
			for (int i = 0; i < players.size(); ++i) {
				Player p = players.get(i);
				_playerMap.put(p.getId(), i);
			}
		}
		Integer index = _playerMap.get(id);
		return index != null ? players.get(index) : null;
	}

	public GameState clone() {
		GameState rval = new GameState();
		rval.setHandPot(handPot);
		rval.setWinner(winner);
		rval.setSabaccPot(sabaccPot);
		rval.players = new ArrayList<Player>(players.size());
		for (Player p : players) {
			rval.players.add(p);
		}
		rval._playerMap = _playerMap;
		return rval;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public int getHandPot() {
		return handPot;
	}

	public void setHandPot(int handPot) {
		this.handPot = handPot;
	}

	public int getSabaccPot() {
		return sabaccPot;
	}

	public void setSabaccPot(int sabaccPot) {
		this.sabaccPot = sabaccPot;
	}

	public Integer getWinner() {
		return winner;
	}

	public void setWinner(Integer winner) {
		this.winner = winner;
	}

}
