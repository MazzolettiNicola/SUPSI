import java.util.Arrays;
import java.util.Random;

public class Grid {
    /**
     * Larghezza di una singola cella della griglia
     */
    //La larghezza della cella può essere solo dispari, in modo da stampare il carattere in modo corretto
    private static final int CELL_WIDTH = 5;

    /**
     * Passi che vengono compiuti ogni volta che il giocatore si muove
     */
    private static final int STEP = 1;
    /**
     * Giocatori della partita
     */
    private Player[] players;

    /**
     * Costanti per la direzione di movimento
     */
    static final int NORTH = 0;
    static final int SOUTH = 1;
    static final int EAST = 2;
    static final int WEST = 3;

    /**
     * Costanti per le colonne e le righe della griglia
     */
    private static final int COLUMN = 0;
    private static final int ROW = 1;

    /**
     * Array in cui viene memorizzata la griglia del gioco
     */
    private char[][] grid;
    
    /**
     * Grandezza massima della griglia, default 10
     */
    static final int GRID_SIZE = 10;

    /**
     * Costruttore della classe Grid
     * Viene inizializzata la griglia con i personaggi già posizionati
     * Vengono generati i soldi nella griglia di gioco
     */
    public Grid(int playersNumber) {
        grid = new char[this.GRID_SIZE][this.GRID_SIZE];
        //controllo che il numero di giocatori sia maggiore di 0
        if(playersNumber > 0 && playersNumber <= Match.PLAYERS_NUMBER) players = new Player[playersNumber];
        else players = new Player[0];

        initializeGrid();
        generateMoney();
    }

    /**
     * Metodo per inizializzare la griglia
     * La griglia viene riempita la prima volta di spazi
     * Vengono settate le posizioni dei personaggi
     */
    public void initializeGrid(){
        for(int i = 0; i < GRID_SIZE; i++){
            for(int j = 0; j < GRID_SIZE; j++){
                grid[i][j] = ' ';
            }
        }
    }

    /**
     * Metodo per aggiungere le cordinate dei giocatori alla griglia
     * @param players, giocatori da aggiungere alla griglia
     */
    public void setPlayers(Player[] players){
        this.players = players;

        for(int i = 0; i < players.length; i++){
            grid[players[i].getPlayerColumn()][players[i].getPlayerRow()] = players[i].getPlayerSymbol();
        }
    }

    /**
     * Metodo per generare i soldi nella griglia
     */
    public void generateMoney(){
        Random rnd = new Random();
        for(int i = 0; i < Match.MAX_MONEY_GENERATED; i++){
            int randomHeight = rnd.nextInt(GRID_SIZE -1);
            int randomWeight = rnd.nextInt(GRID_SIZE -1);

            //controllo che la cella sia libera e che si possa generare un altra moneta all'interno di essa
            if(grid[randomWeight][randomHeight] == ' ') grid[randomWeight][randomHeight] = '$';
            else i--;
        }
    }

    /**
     * Metodo per controllare se il giocatori si trova su una moneta
     * @param colonna, colonna in cui si trova il giocatore
     * @param riga, riga in cui si trova il giocatore
     * @return true, se le coordinate del giocatore coincidono con quelle di una moneta
     */
    public boolean checkPlayerOnMoney(int colonna, int riga){
        if(grid[colonna][riga] == '$') return true;
        else return false;
    }

    /**
     * Metodo per controllare se bisogna effettuare una sfida tra due giocatori
     * @param playerToCheck, giocatore che ha il turno corrente
     */
    public void check1vs1(Player playerToCheck){
        for(int i = 0; i < players.length; i++){
            if(playerToCheck.equals(players[i])) continue;
            else{
                //controllo che il giocatore(playerToCheck) è nella stessa posizione di un altro giocatore
                if(Arrays.equals(playerToCheck.getPlayerPosition(), players[i].getPlayerPosition())) this.oneVsOne(playerToCheck, players[i]);
            }
        }
    }

    /**
     * Metodo per controllare se la partita deve essere terminata
     * @return true, se la partita è giunta al termine
     */
    public boolean checkEndGame(){
        //controllo se sono rimasti dei soldi nella griglia di gioco
        boolean noMoneyLeft = true;
        for(int i = 0; i < GRID_SIZE; i++){
            for(int j = 0; j < GRID_SIZE; j++){
                if(grid[i][j] == '$') noMoneyLeft = false;
            }
        }

        //se non sono rimasti soldi nella griglia di gioco, controllo che un giocatore abbia tutti i soldi generati
        if(noMoneyLeft) {
            for (int i = 0; i < players.length; i++) {
                if (players[i].getPlayerMoney() == Match.MAX_MONEY_GENERATED) return true;
            }
        }

        return false;
    }

    /**
     * Metodo che genera la sfida tra due giocatori
     * @param playerA, playerA della sfida
     * @param playerB, playerB della sfida
     */
    public void oneVsOne(Player playerA, Player playerB){
        boolean condition = false;
        while (!condition){
            int rollDiceA = Match.rollDice();
            System.out.println("Player " + playerA.getPlayerSymbol() + ": " + rollDiceA);

            int rollDiceB = Match.rollDice();
            System.out.println("Player " + playerB.getPlayerSymbol() + ": " + rollDiceB);

            if(rollDiceA > rollDiceB){
                playerA.addPlayerMoney();
                //controllo che al playerB si possano sottrare i soldi, se non posso sottrare una moneta al giocatore, la partita termina
                if(!playerB.subPlayerMoney()){
                    System.out.println("Il giocatore " + playerA.getPlayerSymbol() + " vince la partita");
                    System.exit(0);
                }else{
                    //nel caso i soldi vengano sottratti, resetto la posizione del giocatoreB alla sua posizione di default,
                    //se il giocatore si trova già su quella posizione, setto la sua posizione a quella di default del playerA
                    int[] playerBPosition = new int[2];
                    playerBPosition[COLUMN] = playerB.getPlayerColumn();
                    playerBPosition[ROW] = playerB.getPlayerRow();
                    grid[playerBPosition[COLUMN]][playerBPosition[ROW]] = ' ';

                    //controllo se il giocatore si trova già sulla sua posizione di default
                    if(Arrays.equals(playerBPosition, playerB.getDefaultPlayerPosition())) playerB.setPlayerPosition(playerA.getDefaultPlayerPosition()[COLUMN], playerA.getDefaultPlayerPosition()[ROW]);
                    else playerB.setPlayerPosition(playerB.getDefaultPlayerPosition()[COLUMN], playerB.getDefaultPlayerPosition()[ROW]);
                    condition = true;
                }
            }else if(rollDiceA < rollDiceB){
                playerB.addPlayerMoney();
                //controllo che al playerA si possano sottrare i soldi, se non posso sottrare una moneta al giocatore, la partita termina
                if(!playerA.subPlayerMoney()){
                    System.out.println("Il giocatore " + playerB.getPlayerSymbol() + " vince la partita");
                    System.exit(0);
                }else{
                    int[] playerAPosition = new int[2];
                    playerAPosition[COLUMN] = playerA.getPlayerColumn();
                    playerAPosition[ROW] = playerA.getPlayerRow();
                    grid[playerAPosition[COLUMN]][playerAPosition[ROW]] = ' ';

                    //se il player si trova sulle sue coordinate di default,
                    //vengono settate le cordinate di default dell'altro giocatore come posizione del player
                    if(Arrays.equals(playerAPosition, playerA.getDefaultPlayerPosition())) playerA.setPlayerPosition(playerB.getDefaultPlayerPosition()[COLUMN], playerB.getDefaultPlayerPosition()[ROW]);
                    else playerA.setPlayerPosition(playerA.getDefaultPlayerPosition()[COLUMN], playerA.getDefaultPlayerPosition()[ROW]);
                    condition = true;
                }
            }else{
                //rilancio i dadi nel caso il risultato dei due lanci precedenti sia uguale
                System.out.println("Dice is going to be rerolled");
            }
        }
    }

    /**
     * Metodo che viene utilizzato per muovere i giocatori un passo alla volta
     * @param player, giocatore che si vuole muovere
     */
    public void movePlayer(Player player){
        int pColonne = player.getPlayerColumn();
        int pRighe = player.getPlayerRow();

        int steps = Match.rollDice();
        System.out.println("Dice roll: " + steps);

        //eseguo il ciclo tante volte quanto il valore del dado lanciato
        for(int i = 1; i <= steps; i++) {
            //richiedo la direzione ogni passo del giocatore
            int direction = Match.askDirection();
            grid[pColonne][pRighe] = ' ';

            if (direction == NORTH) {
                if(pRighe == 0) pRighe = (pRighe - STEP) + GRID_SIZE;
                else pRighe -= STEP;
            } else if (direction == SOUTH) {
                if (pRighe == GRID_SIZE -1) pRighe = (pRighe + STEP) - GRID_SIZE;
                else pRighe += STEP;
            } else if (direction == EAST) {
                if(pColonne == GRID_SIZE -1) pColonne = (pColonne + STEP) - GRID_SIZE;
                else pColonne += STEP;
            } else if (direction == WEST) {
                if(pColonne == 0) pColonne = (pColonne - STEP) + GRID_SIZE;
                else pColonne -= STEP;
            }

            //controllo se il giocatore si trova su una moneta
            if(checkPlayerOnMoney(pColonne, pRighe)) {
                player.addPlayerMoney();
                grid[pColonne][pRighe] = ' ';
            }

            player.setPlayerPosition(pColonne, pRighe);
            this.check1vs1(player);

            this.drawGrid();
            if(checkEndGame()) break;
        }
    }

    /**
     * Il metodo disegna la griglia di gioco
     */
    public void drawGrid(){
        //inserisco tutti i giocatori nell'array della griglia
        for(int i = 0; i < players.length; i++){
            grid[players[i].getPlayerColumn()][players[i].getPlayerRow()] = players[i].getPlayerSymbol();
        }

        for(int i = 0; i <= GRID_SIZE; i++){
            int riga = 0;

            for(int j = 0; j <= GRID_SIZE *(CELL_WIDTH+1); j++){
                if(j%(CELL_WIDTH+1) != 0) System.out.print("-");
                else if(j == GRID_SIZE *(CELL_WIDTH+1) && i != GRID_SIZE){
                    System.out.print("|\n");
                    for(int k = 0; k <= GRID_SIZE *(CELL_WIDTH+1); k++){
                        if(k%(CELL_WIDTH+1) == 0) System.out.print("|");
                        else if(k%(CELL_WIDTH-2)==0){
                            System.out.print(grid[riga][i]);
                            riga++;
                        }else System.out.print(" ");
                    }
                }else System.out.print("|");
            }
            System.out.println();
        }
        System.out.println();
    }
}
