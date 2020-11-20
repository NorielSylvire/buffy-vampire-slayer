package org.ucm.tp1.Logic;
import org.ucm.tp1.Logic.Lists.SlayerList;
import org.ucm.tp1.Logic.Lists.VampireList;
import org.ucm.tp1.Logic.GameObjects.Player;

public class GameObjectBoard {
	
	private Player player;
	private SlayerList slayerList;
	private VampireList vampireList;
	
	public GameObjectBoard(Level l) {
		//inicializar objetos
		this.player = new Player();
		this.slayerList = new SlayerList();
		this.vampireList = new VampireList(l);
	}
	
	public boolean checkWin() {
		boolean win = false;	
		if(vampireList.getvRemaining() == 0 && vampireList.getvAlive() == 0) win = true;	//no v left on the board and remaining
		return win;
	}
	
	public boolean checkLose() {		//vampire about to move and on the first column
		boolean lose = false;	
		for(int i = 0; i < vampireList.getCounter(); i++) {
			if(vampireList.getVampireList()[i].getColumn() == 0 && vampireList.getVampireList()[i].getMove()) lose = true;
		}	
		return lose;
	}
	
	public void update(boolean addCoins){
		if(addCoins) this.player.setCoins(this.player.getCoins()+10);		//add coins
		
		//move vampires if theres no slayer/vampire in front
		for(int j = 0; j < this.vampireList.getCounter(); j++) {
			if(!this.vampireList.checkPos(this.vampireList.getVampireList()[j].getRow(), this.vampireList.getVampireList()[j].getColumn()-1)) {
				this.vampireList.getVampireList()[j].setMove(false);
			}
			if(!this.slayerList.checkPos(this.vampireList.getVampireList()[j].getRow(), this.vampireList.getVampireList()[j].getColumn()-1)) {
				this.vampireList.getVampireList()[j].setMove(false);
			}
		}
		this.vampireList.moveVampires();
	}
	
	public void attack() {
		//attack slayers
		int counter;		//number of shots a vampire will recieve
		for(int j = 0; j < this.vampireList.getCounter(); j++) {
			counter = 0;
			for(int x = 0; x < this.vampireList.getVampireList()[j].getColumn(); x++) {		//check number of slayers in a row
				if(!this.slayerList.checkPos(this.vampireList.getVampireList()[j].getRow(), x)) {
					counter++;
				}
			}
			this.vampireList.getVampireList()[j].setHealth(this.vampireList.getVampireList()[j].getHealth()-counter);		//recieve shots
		}
		//attack vampires
		for(int i = 0; i < this.slayerList.getCounter(); i++) {
			if(this.slayerList.getSlayerList()[i].getDeployed() && !this.vampireList.checkPos(this.slayerList.getSlayerList()[i].getRow(), this.slayerList.getSlayerList()[i].getColumn()+1)) {
				this.slayerList.getSlayerList()[i].setHealth(this.slayerList.getSlayerList()[i].getHealth()-1);		//recieve vampire attack
			}
		}
	}
	
	public boolean addSlayer(int row, int column){
		row--;
		column--;
		boolean added = false;
		if(this.slayerList.getCounter() < 30 && this.player.getCoins() >= 50 && this.slayerList.checkPos(row, column) && this.vampireList.checkPos(row, column)) {
			this.slayerList.addSlayer(row, column);
			this.getPlayer().setCoins(this.getPlayer().getCoins()-50);		//update coins
			added = true;
		}
		return added;
	}
	
	public void addVampire(double rand, int nRows, int nColumns, double frequency){
		//calcular si añadirlo o no
		//calcular en que fila iria
		//añadirlo		
		if(rand <= frequency && this.vampireList.getvRemaining() > 0) {
			int row = (int)(Math.round(rand*100) % nRows);
			if(this.vampireList.checkPos(row, nColumns)) {
				this.vampireList.addVampire(row, nColumns);
			}
		}
	}
	
	public void removeDead(){
		//remove slayers
		this.slayerList.removeDead();
		//remove vampires
		this.vampireList.removeDead();
	}
	
	String searchPos(int row, int column) {		//search object 4 the board
		String object = " ";
		boolean found = false;
		
		//search vampire		
		for (int j = 0; j < this.vampireList.getCounter(); j++) {
			if (this.vampireList.getVampireList()[j].getRow()== row && this.vampireList.getVampireList()[j].getColumn() == column && this.vampireList.getVampireList()[j].getDeployed()) {
				found = true;
				object = "V[" + this.vampireList.getVampireList()[j].getHealth() + "]";
			}
		}
		
		//search slayer
		if(!found)
			for (int k = 0; k < this.slayerList.getCounter(); k++) {
				if (this.slayerList.getSlayerList()[k].getRow() == row && this.slayerList.getSlayerList()[k].getColumn() == column && this.slayerList.getSlayerList()[k].getDeployed()) {
					found = true;
					object = "S[" + this.slayerList.getSlayerList()[k].getHealth() + "]";
				}
			}
		
		return object;
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public SlayerList getSlayerList() {
		return slayerList;
	}
	public void setSlayerList(SlayerList slayerList) {
		this.slayerList = slayerList;
	}
	public VampireList getVampireList() {
		return vampireList;
	}
	public void setVampireList(VampireList vampireList) {
		this.vampireList = vampireList;
	}
	
	
}
