package org.ucm.tp1.Logic.GameObjects;

public class Slayer {
    private static int cost;
    private int health; 
    private int fireRate;
    private int damage;
    private int row;
    private int column;
	private boolean deployed;

    public Slayer() {
        this.health = 3;
        this.fireRate = 1;
        this.damage = 1;
        this.deployed = false;
    }
    static {
    	cost = 50;
    }

    /* TODO When new types of damage are added, such as area or diagonal,
     * create an enum with the different types. */

    public static int getCost() {
        return cost;
    }
    public static void setCost(int newCost) {
        cost = newCost;
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
	public boolean getDeployed() {
		return deployed;
	}
	public void setDeployed(boolean deployed) {
		this.deployed = deployed;
	}
    public static final String usageMsg = "Usage: [a]dd <x> <y>";

}