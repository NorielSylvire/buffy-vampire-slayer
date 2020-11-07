package org.ucm.tp1.Logic;

import java.util.Random;

public class Game {
    private Long seed;
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
        this.level = level;
        this.cycles = 0;
        emptyBoard();
        this.gameObjectBoard = new GameObjectBoard(level);
    }
    
    public double randomGenerator(Long seed) {
    	Random generator = new Random(seed);
    	setSeed(seed++);
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