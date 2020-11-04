package org.ucm.tp1.Logic.Lists;
import org.ucm.tp1.Logic.GameObjects.Slayer;

public class SlayerList {
	
	private int counter;	//siguiente slayer
	Slayer[] slayerList;
	
	public SlayerList() {
		this.counter = 0;			//numero de slayers en el tablero
		this.slayerList = new Slayer[28];	//crear array de slayers
		for(int i = 0; i < 30; i++) {			//inicializar slayers
			slayerList[i] = new Slayer();
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
