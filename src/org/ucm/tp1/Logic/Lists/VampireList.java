package org.ucm.tp1.Logic.Lists;
import org.ucm.tp1.Logic.GameObjects.Vampire;
import org.ucm.tp1.Logic.Level;

public class VampireList {
	private int counter;	//restar cada vez que aparezca uno
	Vampire[] vampireList;
	
	public VampireList(Level l) {
		this.counter = l.getNumberOfVampires();			//numero de vampiros en esa dificultad
		this.vampireList = new Vampire[this.counter];	//crear array de vampiros
		for(int i = 0; i < this.counter; i++) {			//inicializar vampiros
			vampireList[i] = new Vampire();
		}
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Vampire[] getVampireList() {
		return vampireList;
	}

	public void setVampireList(Vampire[] vampireList) {
		this.vampireList = vampireList;
	}
	
	
}
