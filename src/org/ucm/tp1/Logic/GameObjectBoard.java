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
	
	
	public void update(boolean addCoins){
		if(addCoins) this.player.setCoins(this.player.getCoins()+10);
		this.vampireList.moveVampires();
	}
	
	public void attack() {
		//attack slayers
		int counter;		//number of shots a vampire will recieve
		for(int j = 0; j < this.vampireList.getCounter(); j++) {
			counter = 0;
			for(int x = 1; x < this.vampireList.getVampireList()[j].getColumn(); x++) {		//check number of slayers in a row
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
		boolean added = false;
		if(this.slayerList.getCounter() < 28 && this.player.getCoins() >= 50 && this.slayerList.checkPos(row, column) && this.vampireList.checkPos(row, column)) {
			this.slayerList.addSlayer(row, column);
			this.getPlayer().setCoins(this.getPlayer().getCoins()-50);		//update coins
			added = true;
		}
		return added;
	}
	
	public boolean addVampire(int row, int column){
		boolean added = false;
		if(this.vampireList.getvRemaining() > 0 && this.vampireList.checkPos(row, column)) {
			this.vampireList.addVampire(row, column);
			added = true;
		}
		return added;
	}
	
	public void removeDead(){
		//remove slayers
		this.slayerList.removeDead();
		//remove vampires
		this.vampireList.removeDead();
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
