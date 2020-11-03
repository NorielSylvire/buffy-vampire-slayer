package org.ucm.tp1.Logic.Lists;
import org.ucm.tp1.Logic.GameObjects.Vampire;
import org.ucm.tp1.Logic.Level;

public class VampireList {
	private int vRemaining;	//restar cada vez que aparezca uno
	private int counter;	//vampiros que han aparecido
	Vampire[] vampireList;
	
	public VampireList(Level l) {
		this.vRemaining = l.getNumberOfVampires();			//numero de vampiros en esa dificultad
		this.vampireList = new Vampire[this.vRemaining];	//crear array de vampiros
		for(int i = 0; i < this.vRemaining; i++) {			//inicializar vampiros
			vampireList[i] = new Vampire();
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
	
	
}
