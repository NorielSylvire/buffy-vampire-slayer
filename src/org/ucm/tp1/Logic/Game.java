//He añadido atributos a la clase Game para que pueda ser printada
package org.ucm.tp1.Logic;

import java.util.Arrays;

public class Game {
    private Long seed;
    private Level level;
    private short[][] board = new short[8][8];
    /* 0 = Nada
     * 1 = Slayer
     * 2 = Vampiro
     */
    
    public Game(long seed, Level level) {
        this.seed = seed;
        this.level = level;
        for (int i = 0; i < level.getDim_x(); ++i) {
            for (int j = 0; j < level.getDim_y(); ++j) {
                board[i][j] = 0;
            }
        }
    }
    
    public short[][] getBoard() {
        return board;
    }
    
    public Level getLevel() {
        return level;
    }
}