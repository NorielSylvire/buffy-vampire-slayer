package org.ucm.tp1.Logic.Lists;
import org.ucm.tp1.Logic.GameObjects.Slayer;

public class SlayerList {
	
	private int counter;	//siguiente slayer
	Slayer[] slayerList;
	
	public SlayerList() {
		this.counter = 0;			//numero de slayers en el tablero
		this.slayerList = new Slayer[30];	//crear array de slayers
		for(int i = 0; i < 30; i++) {			//inicializar slayers
			slayerList[i] = new Slayer();
		}
	}
	
	public boolean checkPos(int row, int column) {		//comprobar si en esa posicion no hay nada
		boolean ok = true;
		for(int i=0; i<counter; i++) {
			if(this.slayerList[i].getDeployed() && this.slayerList[i].getRow() == row && this.slayerList[i].getColumn() == column) {
				ok = false;
			}
		}
		return ok;
	}
	
	public void addSlayer(int row, int column){
		this.slayerList[this.counter].deploySlayer(row, column);		//deploy slayer
		this.counter++;
	}
	
	public void removeDead() {
		for(int i = 0; i < this.counter; i++) {
			if(this.slayerList[i].getHealth() <= 0) {
				this.slayerList[i].setDeployed(false);
			}
		}
	}
	
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public Slayer[] getSlayerList() {
		return slayerList;
	}
	public void setSlayerList(Slayer[] slayerList) {
		this.slayerList = slayerList;
	}
	
}
