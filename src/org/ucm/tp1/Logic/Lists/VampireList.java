package org.ucm.tp1.Logic.Lists;
import org.ucm.tp1.Logic.GameObjects.Vampire;
import org.ucm.tp1.Logic.Level;

public class VampireList {
	private int vRemaining;	//restar cada vez que aparezca uno
	private int vAlive;		//v on board
	private int counter;	//vampiros que han aparecido
	Vampire[] vampireList;
	
	public VampireList(Level l) {
		this.counter = 0;
		this.vAlive = 0;
		this.vRemaining = l.getNumberOfVampires();			//numero de vampiros en esa dificultad
		this.vampireList = new Vampire[this.vRemaining];	//crear array de vampiros
		for(int i = 0; i < this.vRemaining; i++) {			//inicializar vampiros
			vampireList[i] = new Vampire();
		}
	}

	public boolean checkPos(int row, int column) {		//comprobar si en esa posicion no hay nada
		boolean ok = true;
		for(int i=0; i<counter; i++) {
			if(this.vampireList[i].getDeployed() && this.vampireList[i].getRow() == row && this.vampireList[i].getColumn() == column) {
				ok = false;
			}
		}
		return ok;
	}
	
	public void addVampire(int row, int column){
		this.vampireList[this.counter].deployVampire(row, column);		//deploy slayer
		this.counter++;
		this.vAlive++;
		this.vRemaining--;
	}
	
	public void moveVampires() {
		for(int i = 0; i < this.counter; i++) {
			if(vampireList[i].getDeployed()) {
				if(vampireList[i].getMove()) vampireList[i].moveForward();		//move if true
				vampireList[i].setMove(!vampireList[i].getMove());		//change move
			}
		}
	}
	
	public void removeDead() {
		for(int i = 0; i < this.counter; i++) {
			if(this.vampireList[i].getHealth() <= 0 && this.vampireList[i].getDeployed()) {
				this.vampireList[i].setDeployed(false);
				this.vampireList[i].setHealth(3);
				this.vAlive--;
			}
		}
	}
	
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public int getvRemaining() {
		return vRemaining;
	}
	public void setvRemaining(int vRemaining) {
		this.vRemaining = vRemaining;
	}
	public Vampire[] getVampireList() {
		return vampireList;
	}
	public void setVampireList(Vampire[] vampireList) {
		this.vampireList = vampireList;
	}
	public int getvAlive() {
		return vAlive;
	}
	public void setvAlive(int vAlive) {
		this.vAlive = vAlive;
	}
		
}
