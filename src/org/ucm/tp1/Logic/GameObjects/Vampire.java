package org.ucm.tp1.Logic.GameObjects;

public class Vampire {
	private int health;
	private int fireRate;
	private int damage;
	private int row;
	private int column;
	private boolean move;		//indica si le toca moverse ese turno o no
	private boolean deployed;
	
	public Vampire(){
		this.health = 3;
        this.fireRate = 1;
        this.damage = 1;
        this.move = false;
        this.deployed = false;
	}
    public void deployVampire(int row, int column) {
    	this.column = row;
    	this.row = column;
    	this.deployed = true;
    }
	public void moveForward() {
		this.setColumn(this.getColumn()-1);
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getFireRate() {
		return fireRate;
	}
	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public boolean getMove() {
		return move;
	}
	public void setMove(boolean move) {
		this.move = move;
	}
	public boolean getDeployed() {
		return deployed;
	}
	public void setDeployed(boolean deployed) {
		this.deployed = deployed;
	}
}
