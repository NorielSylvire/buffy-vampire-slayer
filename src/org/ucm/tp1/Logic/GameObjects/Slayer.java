package org.ucm.tp1.Logic.GameObjects;

public class Slayer {
    private int cost;
    private int health; 
    private int fireRate;
    private int damage;
    private int row;
    private int column;

    public Slayer(int row, int column) {
        this.cost = 50;
        this.health = 3;
        this.fireRate = 1;
        this.damage = 1;
        this.row = row;
        this.column = column;
    }


    /* TODO When new types of damage are added, such as area or diagonal,
     * create an enum with the different types. */

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
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
    public static final String usageMsg = "Usage: [a]dd <x> <y>";

}