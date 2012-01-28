package com.clementscode.sabacc.core.server;

import java.util.LinkedHashMap;

import com.clementscode.sabacc.core.Dealer;

public abstract class Server {

	protected Dealer dealer;

	protected LinkedHashMap<String, Integer> playerMap = new LinkedHashMap<String, Integer>();

}
