package org.ucm.tp1.Logic;

import java.util.*;

public class Game {
    private Long seed;
    private Long seedBackup;
    private Level level;
    private int cycles;
    private GameObjectBoard gameObjectBoard;
    
    
    public Game(long seed, Level level) {
        this.seed = seed;
        this.seedBackup = seed;
        this.level = level;
        this.cycles = 0;
        this.gameObjectBoard = new GameObjectBoard(level);
    }
    
    public void update() {
    	gameObjectBoard.update(randomGenerator(seed) >= 0.5);
    	gameObjectBoard.attack();
    	gameObjectBoard.addVampire(randomGenerator(seed), level.getDim_y()-1, level.getDim_x()-1, level.getVampireFrequency());
    	gameObjectBoard.removeDead();
    	cycles++;
    }
    
    public String toStringObjectAt(int row, int column) {
    	return this.gameObjectBoard.searchPos(row, column);
    }
    
    public double randomGenerator(Long seed) {
    	Random generator = new Random(seed);
    	this.seed = (long)generator.nextInt();
    	generator.setSeed(seed);
    	return generator.nextDouble();
    }
    
    public int getCycles()  {
        return cycles;
    }
    
    public void setCycles(int nCycles) {
        this.cycles = nCycles;
    }
    
    public Long getSeedBackup() {
		return seedBackup;
	}

	public void setSeedBackup(Long seedBackup) {
		this.seedBackup = seedBackup;
	}

	public Level getLevel() {
        return level;
    }
    
    public void setLevel(Level newLevel) {
        this.level = newLevel;
    }   
    
    public Long getSeed() {
		return seed;
	}

	public void setSeed(Long seed) {
		this.seed = seed;
	}

	public GameObjectBoard getGameObjectBoard() {
        return gameObjectBoard;
    }
    
    public void setGameObjectBoard (GameObjectBoard newGameObjectBoard) {
        this.gameObjectBoard = newGameObjectBoard;
    }

}