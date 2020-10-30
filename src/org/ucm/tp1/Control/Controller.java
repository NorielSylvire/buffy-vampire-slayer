//He hecho que la función printGame() funcione
package org.ucm.tp1.Control;
import java.util.Scanner;
import org.ucm.tp1.Logic.Game;
import org.ucm.tp1.view.Gameprinter;

public class Controller {

    
    public final String prompt = "Command > ";
    public static final String helpMsg = String.format(
            "Available commands:%n" +
            "[a]dd <x> <y>: add a slayer in position x, y%n" +
            "[h]elp: show this help%n" + 
            "[r]eset: reset game%n" + 
            "[e]xit: exit game%n"+ 
            "[n]one | []: update%n");
    
    public static final String unknownCommandMsg = String.format("Unknown command");
    public static final String invalidCommandMsg = String.format("Invalid command");
    public static final String invalidPositionMsg = String.format("Invalid position");

    private Game game;
    private Scanner scanner;
    private Gameprinter gameprinter;
    
    public Controller(Game game, Scanner scanner) {
        this.game = game;
        this.gameprinter = new Gameprinter(game, game.getLevel().getDim_x(), game.getLevel().getDim_y());
        this.scanner = scanner;
    }
    
    public void  printGame() {
        System.out.println(gameprinter.toString());
    }
    
    public void run() {
        printGame();
    }

}