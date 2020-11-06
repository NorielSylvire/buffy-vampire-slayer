package org.ucm.tp1.Logic;

import java.util.Arrays;

public class Game {
    private Long seed;
    private Level level;
    private int cycles;
    private int coins;
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
        this.coins = 50;
        emptyBoard();
        this.gameObjectBoard = new GameObjectBoard(level);
    }
    
    public int getCycles()  {
        return cycles;
    }
    
    public void setCycles(int nCycles) {
        this.cycles = nCycles;
    }
    
    public int getCoins() {
        return coins;
    }
    
    public void setCoins(int amount) {
        this.coins = amount;
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