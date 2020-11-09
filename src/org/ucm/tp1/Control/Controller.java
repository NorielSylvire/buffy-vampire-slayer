package org.ucm.tp1.Control;
import java.util.Scanner;
import org.ucm.tp1.Logic.*;
import org.ucm.tp1.view.Gameprinter;


public class Controller {
    private boolean exitGame = false;
    
    public final String prompt = "Command > ";
    public static final String helpMsg = String.format(
            "Available commands:%n" +
            "[a]dd <x> <y>: add a slayer in position x, y%n" +
            "[h]elp: show this help%n" + 
            "[r]eset: reset game%n" + 
            "[e]xit: exit game%n"+ 
            "[n]one | []: update%n");
    
    public static final String unknownCommandMsg = String.format("Unknown command.");
    public static final String invalidCommandMsg = String.format("Invalid command.");
    public static final String invalidPositionMsg = String.format("Invalid position.");
    public static final String confirmationMsg = String.format("Are you sure? (y/n)");
    public static final String tooManyArgsMsg = String.format("You introduced more arguments than necessary.");

    private Game game;
    private Scanner scanner;
    private Gameprinter gameprinter;
    private long seedBackup;
    private Level levelBackup;
    
    public Controller(Game game, Scanner scanner) {
        this.game = game;
    	this.levelBackup = this.game.getLevel();
        this.scanner = scanner;
        this.seedBackup = this.game.getSeed();
    }
    
    public void  printGame() {
        System.out.println("Number of cycles: " + game.getCycles());
        System.out.println("Coins: " + game.getGameObjectBoard().getPlayer().getCoins());
        System.out.println("Remaining vampires: " + game.getGameObjectBoard().getVampireList().getvRemaining());
        System.out.println("Vampires on the board: " + game.getGameObjectBoard().getVampireList().getvAlive());
        this.gameprinter = new Gameprinter(game, game.getLevel().getDim_x(), game.getLevel().getDim_y());
        System.out.println(gameprinter);
    }
    
    public void run() {
        String command = "";
        
        while(!exitGame) {
            //clearConsole();
            printGame();
            System.out.print(prompt);
            command = scanner.nextLine();
            executeCommand(command);
            game.update();
            command = "";
        }
    }


    public void executeCommand(String commandAndArgs) {
        String command = "";
        if (commandAndArgs.length() > 0) command = commandAndArgs.substring(0,1);

        //Lo se, demasiadas condiciones, pero quiero que el programa sea capaz de manejar todos los posibles casos de entrada
        switch(command) {
            case "h":
            case "H":
                if (commandAndArgs.equalsIgnoreCase("help") || commandAndArgs.length() == 1) {
                    System.out.print(helpMsg);
                }
                else if (commandAndArgs.substring(0,3).equalsIgnoreCase("help") && commandAndArgs.length() != 4) {
                    tooManyArgs();
                }
                else unknownCommand();
                break;
            case "":
            case "n":
            case "N":
            	if (commandAndArgs.length() <= 1) break;
            	else if (commandAndArgs.equalsIgnoreCase("none")) {
                    break;
                }
                else if (commandAndArgs.length() > 4 && commandAndArgs.substring(0,3).equalsIgnoreCase("none")) {
                    tooManyArgs();
                    break;
                }
                else unknownCommand();
            	break;
            case "a":
            case "A":
            	String[] pieces = commandAndArgs.split(" ");
            	
                if (pieces[0].length() == 1 || pieces[0].equalsIgnoreCase("add")) addSlayer(commandAndArgs);
                else unknownCommand();
                break;
            case "r":
            case "R":
                if (commandAndArgs.equalsIgnoreCase("reset") || commandAndArgs.length() == 1) {
                    if (confirm()) {
                        resetGame();
                    }
                }
                else if (commandAndArgs.substring(0,4).equalsIgnoreCase("reset") && commandAndArgs.substring(3,4) == " ") {
                    tooManyArgs();
                }
                else unknownCommand();
                break;
            case "e":
            case "E":
                if (commandAndArgs.equalsIgnoreCase("exit") || commandAndArgs.length() == 1) {
                    if (confirm()) this.exitGame = true;
                }
                else if (commandAndArgs.substring(0,4).equalsIgnoreCase("exit") &&
                         commandAndArgs.substring(3,4).contentEquals(" ")) {
                    tooManyArgs();
                }
                else unknownCommand();
                break;
            default:
                unknownCommand();
        }
    }

    public void unknownCommand() {
        System.out.print(unknownCommandMsg + " Please try again.\n");
        System.out.print(helpMsg);
    }
    
    public void tooManyArgs() {
        System.out.print(tooManyArgsMsg);
        System.out.print(helpMsg);
    }
    
    public boolean confirm() {
        System.out.print(confirmationMsg);
        System.out.print("\n" + prompt);
        String input = scanner.nextLine();
        boolean ret;
        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            ret = true;
        }
        else if (!input.equalsIgnoreCase("n") && !input.equalsIgnoreCase("no")) {
            unknownCommand();
            ret = false;
        }
        else ret = false;
        
        return ret;
    }
    
    public void resetGame() {
    	this.game = new Game(seedBackup, levelBackup);
    }

    public void addSlayer(String command) {
        int posX, posY;
        String[] pieces = command.split(" ");

        if (pieces[0].length() == 1) {
            posX = Integer.parseInt(pieces[1]);
            posY = Integer.parseInt(pieces[2]);
            game.getGameObjectBoard().addSlayer(posX, posY);
        }
        else if (pieces[0].length() == 3) {
            posX = Integer.parseInt(pieces[1]);
            posY = Integer.parseInt(pieces[2]);
            game.getGameObjectBoard().addSlayer(posX, posY);
        }
        else System.out.println(invalidCommandMsg);
    }
}