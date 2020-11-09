package org.ucm.tp1.Logic;

import java.util.*;

public class Game {
    private Long seed;
    private Long seedBackup;
    private Level level;
    private int cycles;
    private short[][] board = new short[8][8];
    /* 0 = Nada
     * 1 = Slayer
     * 2 = Vampiro
     */
    private GameObjectBoard gameObjectBoard;
    
    
    public Game(long seed, Level level) {
        this.seed = seed;
        this.seedBackup = seed;
        this.level = level;
        this.cycles = 0;
        emptyBoard();
        this.gameObjectBoard = new GameObjectBoard(level);
    }
    
    public void update() {
    	int i = 0;
    	gameObjectBoard.update(randomGenerator(seed) >= 0.5);
    	gameObjectBoard.attack();
    	//add vampires
    	boolean added = false;
    	/*while (i < level.getDim_y() && !added) {
    		if (randomGenerator(seed) <= (level.getVampireFrequency()/level.getDim_y())) {
    			added = gameObjectBoard.addVampire(level.getDim_x()-1, i);
    			board[level.getDim_x()-1][i] = 2;
    			i++;
    		}
    	}*/
    	gameObjectBoard.newaddVampire(randomGenerator(seed), level.getDim_y(), level.getDim_x(), level.getVampireFrequency());
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
    
    public short[][] getBoard() {
        return board;
    }
    
    public void setBoard (short[][] newBoard) {
        this.board = newBoard;
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
    
    public void emptyBoard() {
        for (int i = 0; i < level.getDim_x(); ++i) {
            for (int j = 0; j < level.getDim_y(); ++j) {
                board[i][j] = 0;
            }
        }
    }
}