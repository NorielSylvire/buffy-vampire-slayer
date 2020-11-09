package org.ucm.tp1.Logic.GameObjects;

public class Player {
	//initialize
	private int coins;
	//constructor
	public Player() {
		this.coins = 50;
	}
	//setters&getters
	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
}
