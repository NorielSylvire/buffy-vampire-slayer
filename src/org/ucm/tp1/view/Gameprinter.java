//He creado la función encodeGame() y sus atributos necesarios para que se pueda imprimir el tablero
//TODO: Modificar el encodeGame() para que pueda distinguir entre distintos tipos de slayer y vampiros, cuando los haya
package org.ucm.tp1.view;
import org.ucm.tp1.Logic.Game;
import org.ucm.tp1.utils.MyStringUtils;

public class Gameprinter {
    
    int numCols; 
    int numRows;
    String[][] board;
    final String space = " ";
    
    public Gameprinter (Game game, int rows, int cols) {
        this.numCols = cols;
    	this.numRows = rows;
        encodeGame(game);
    }
    
    private void encodeGame(Game game) {
		board = new String[numRows][numCols];
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
            	board[i][j] =  game.toStringObjectAt(i, j);
            }
        }
    }
    
     public String toString() {

         int cellSize = 7;
         int marginSize = 2;
         String vDelimiter = "|";
         String hDelimiter = "-";
         String intersect = space;
         String vIntersect = space;
         String hIntersect = "-";
         String corner = space;

         String cellDelimiter = MyStringUtils.repeat(hDelimiter, cellSize);

         String rowDelimiter = vIntersect + MyStringUtils.repeat(cellDelimiter + intersect, numCols-1) + cellDelimiter + vIntersect;
         String hEdge =  corner + MyStringUtils.repeat(cellDelimiter + hIntersect, numCols-1) + cellDelimiter + corner;

         String margin = MyStringUtils.repeat(space, marginSize);
         String lineEdge = String.format("%n%s%s%n", margin, hEdge);
         String lineDelimiter = String.format("%n%s%s%n", margin, rowDelimiter);


         StringBuilder str = new StringBuilder();

         str.append(lineEdge);
         for(int i=0; i<numRows; i++) {
                str.append(margin).append(vDelimiter);
                for (int j=0; j<numCols; j++)
                    str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
                if (i != numRows - 1) str.append(lineDelimiter);
                else str.append(lineEdge);   
         }

         return str.toString();
    }
}