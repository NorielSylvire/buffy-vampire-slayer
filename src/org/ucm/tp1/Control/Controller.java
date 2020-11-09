package org.ucm.tp1.Control;
import java.util.Scanner;
import org.ucm.tp1.Logic.*;
import org.ucm.tp1.view.Gameprinter;


public class Controller {
    private boolean exitGame;
    
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
    private boolean help;
    
    public Controller(Game game, Scanner scanner) {
        this.game = game;
    	this.levelBackup = this.game.getLevel();
        this.scanner = scanner;
        this.seedBackup = this.game.getSeed();
        this.help = false;
        this.exitGame = false;
    }
    
    public void  printGame() {
        System.out.println("Number of cycles: " + game.getCycles());
        System.out.println("Coins: " + game.getGameObjectBoard().getPlayer().getCoins());
        System.out.println("Remaining vampires: " + game.getGameObjectBoard().getVampireList().getvRemaining());
        System.out.println("Vampires on the board: " + game.getGameObjectBoard().getVampireList().getvAlive());
        this.gameprinter = new Gameprinter(game, game.getLevel().getDim_y(), game.getLevel().getDim_x());
        System.out.println(gameprinter);
    }
    
    public void run() {
        String command = "";
        
        while(!exitGame) {
            //clearConsole();
        	do {
            printGame();
            help = false;
            System.out.print(prompt);
            command = scanner.nextLine();
        	}while(!executeCommand(command) || help);
            game.update();
            help = false;
            command = "";
            switch(game.checkEnd()) {
            case 1:
            	exitGame = true;
            	System.out.println("YOU WIN!!");
            	break;
            case 2:
            	exitGame = true;
            	System.out.println("YOU LOSE!!");
            	break;
            	
            }
        }
    }

    public boolean executeCommand(String commandAndArgs) {
        String command = "";
        if (commandAndArgs.length() > 0) command = commandAndArgs.substring(0,1);
        boolean correctCommand = false;

        //Lo se, demasiadas condiciones, pero quiero que el programa sea capaz de manejar todos los posibles casos de entrada
        switch(command) {
            case "h":
            case "H":
                if (commandAndArgs.equalsIgnoreCase("help") || commandAndArgs.length() == 1) {
                    System.out.print(helpMsg);
                    correctCommand = true;
                    help = true;
                }
                else if (commandAndArgs.substring(0,3).equalsIgnoreCase("help") && commandAndArgs.length() != 4) {
                    tooManyArgs();
                }
                else unknownCommand();
                break;
            case "":
            case "n":
            case "N":
            	if (commandAndArgs.length() <= 1) {
            		correctCommand = true;
            		break;
            	}
            	else if (commandAndArgs.equalsIgnoreCase("none")) {
            		correctCommand = true;
                    break;
                }
                else if (commandAndArgs.length() > 4 && commandAndArgs.substring(0,3).equalsIgnoreCase("none")) {
                    tooManyArgs();
                    correctCommand = true;
                    break;
                }
                else unknownCommand();
            	break;
            case "a":
            case "A":
            	String[] pieces = commandAndArgs.split(" ");
            	
                if (pieces[0].length() == 1 || pieces[0].equalsIgnoreCase("add")) {
                	if(addSlayer(commandAndArgs)) correctCommand = true;
                }
                else unknownCommand();
                break;
            case "r":
            case "R":
                if (commandAndArgs.equalsIgnoreCase("reset") || commandAndArgs.length() == 1) {
                    if (confirm()) {
                        resetGame();
                    	correctCommand = true;
                    }
                }
                else if (commandAndArgs.substring(0,4).equalsIgnoreCase("reset") && commandAndArgs.substring(3,4) == " ") {
                    tooManyArgs();
                    correctCommand = true;
                }
                else unknownCommand();
                break;
            case "e":
            case "E":
                if (commandAndArgs.equalsIgnoreCase("exit") || commandAndArgs.length() == 1) {
                    if (confirm()) {
                    	this.exitGame = true;
                    	correctCommand = true;
                    }
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
        return correctCommand;
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

    public boolean addSlayer(String command) {
        int posX, posY;
        boolean validCommand = false;
        String[] pieces = command.split(" ");
        posY = Integer.parseInt(pieces[1]);
        posX = Integer.parseInt(pieces[2]);
        if (posX <= 0 || posX >= game.getLevel().getDim_x() || posY <= 0 || posY > game.getLevel().getDim_y()) {
            System.out.print(invalidCommandMsg + "\nInvalid position.\n");
        }
        else {
            if (!game.getGameObjectBoard().addSlayer(posY, posX)) {
                validCommand = false;
                System.out.println("Could not add slayer in that position. The position is occupied.");
            }
            else validCommand = true;
        }
        return validCommand;
    }
}