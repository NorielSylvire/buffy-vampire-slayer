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
	
	
	public void update(){
		this.player.setCoins(this.player.getCoins()+10);
	}
	public void addSlayer(int row, int column, Level l){
		if(this.slayerList.getCounter() < 28 && this.player.getCoins() >= 50 && column != l.getDim_x() && this.slayerList.checkPos(row, column) && this.vampireList.checkPos(row, column)) {
			this.slayerList.getSlayerList()[this.slayerList.getCounter()].deploySlayer(row, column);		//deploy slayer
			this.slayerList.setCounter(this.slayerList.getCounter()+1);		//update slayer counter
			this.getPlayer().setCoins(this.getPlayer().getCoins()-50);		//update coins
		}		
	}
	public void addVampire(){
		
	}
	public void removeDead(){
		//remove slayers
		for(int i = 0; i < this.slayerList.getCounter(); i++) {
			if(this.slayerList.getSlayerList()[i].getHealth() <= 0) {
				this.slayerList.getSlayerList()[i].setDeployed(false);
			}
		}
		//remove vampires
		for(int i = 0; i < this.vampireList.getCounter(); i++) {
			if(this.vampireList.getVampireList()[i].getHealth() <= 0) {
				this.vampireList.getVampireList()[i].setDeployed(false);
			}
		}
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
