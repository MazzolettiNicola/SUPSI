import java.util.Random;
import java.util.Scanner;

public class Match {
    /**
     * Costanti per le opzioni del menu
     */
    private static final int EXIT_OPTION = 0;
    private static final int SHOW_GRID_OPTION = 1;
    private static final int MOVE_PLAYER_OPTION = 2;
    private static final int SHOW_INFO_OPTION = 3;

    /**
     * Costante per il numero massimo di giocatori della partita
     */
    static final int PLAYERS_NUMBER = 2;
    /**
     * Quantità massima di soldi generati sulla griglia
     */
    static final int MAX_MONEY_GENERATED = 10;

    /**
     * Griglia di gioco
     */
    private Grid grid;
    /**
     * Giocatori della partita
     */
    private Player[] players;

    /**
     * Variabile utilizzata per sapere i turni di gioco
     * Viene utilizzata all'interno dell'array players
     */
    private int currentTurn = 0;

    private static Scanner scanner;

    public Match() {
        this.scanner = new Scanner(System.in);
        this.grid = new Grid(PLAYERS_NUMBER);

        players = new Player[PLAYERS_NUMBER];
        players[0] = new Player('X', 0,9);
        players[1] = new Player('Y', 9,0);

        grid.setPlayers(players);
    }

    /**
     * Metodo per stampare il menu del gioco
     */
    public void printMenu(){
        System.out.print("Please choose an option:\n" +
                "0. Exit\n" +
                "1. Show grid\n" +
                "2. Move player\n" +
                "3. Show player info\n" +
                "Choosen option: ");
    }

    /**
     * Metodo per stampare le informazioni dei giocatori in partita
     */
    public void printPlayerInfo(){
        System.out.println("Players info:\n");

        for(int i = 0; i < PLAYERS_NUMBER; i++){
            System.out.println("Player: " + players[i].getPlayerSymbol() + ", money: " + players[i].getPlayerMoney());
        }

        System.out.println();
    }

    /**
     * Metodo per cambiare il turno di gioco
     */
    public void changePlayerTurn(){
        if(currentTurn == PLAYERS_NUMBER-1) currentTurn = 0;
        else currentTurn++;
    }

    /**
     * Metodo per lanciare i dadi
     * @return random, numero generato[1,6]
     */
    public static int rollDice(){
        return new Random().nextInt(6) + 1;
    }

    /**
     * Metodo per richiedere la direzione in cui si vuole il giocatore
     * @return direzione, la direzione scelta
     */
    public static int askDirection(){
        int direction = -1;

        boolean condition = false;
        //TODO: patch the problem of entering a NAN into the scanner
        while(!condition){
            System.out.print("Please enter the direction where you want to move your player,\n" +
                    "Options: 0. North, 1. South, 2. East, 3. West\n" +
                    "Choosen direction: ");

            int dir = scanner.nextInt();
            if(dir >= 0 && dir <= 3){
                direction = dir;
                condition = true;
            }

            System.out.println();
        }

        return direction;
    }

    //Metodo per iniziare la partita
    public void startMatch(){
        System.out.println("Welcome to the game!");
        int option = -1;
        while(option != EXIT_OPTION){
            this.printMenu();

            boolean condition = false;
            while(!condition){
                //TODO: patch the problem of entering a NAN into the scanner
                int op = scanner.nextInt();
                if(op >= 0 && op <= 3){
                    option = op;
                    condition = true;
                }
            }

            if(option == SHOW_GRID_OPTION){
                grid.drawGrid();
            }else if(option == MOVE_PLAYER_OPTION){
                grid.movePlayer(players[currentTurn]);
                this.changePlayerTurn();
            }else if(option == SHOW_INFO_OPTION){
                printPlayerInfo();
            }

            if(grid.checkEndGame()){
                printPlayerInfo();
                System.out.println("Game over!");
                System.exit(0);
            }
        }

        scanner.close();
        System.out.println("Thanks for playing the game, goodbye!");
    }

    public static void main(String[] args) {
        new Match().startMatch();
    }
}
