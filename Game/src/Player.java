public class Player {
    /**
     * Simbolo del giocatore
     */
    private char playerSymbol;
    /**
     * Soldi accumulati dal giocatore
     */
    private int playerMoney = 0;
    /**
     * Posizione sulla griglia del giocatore
     */
    private int[] playerPosition;
    /**
     * Posizione di default sulla griglia del giocatore
     */
    private int[] defaultPlayerPosition;

    /**
     * Costruttore classe Player
     * @param playerSymbol, simbolo del giocatore
     * @param colonna, colonna di posizionamento del giocatore sulla griglia
     * @param riga, riga di posizionamento del giocatore sulla griglia
     */
    public Player(char playerSymbol, int colonna, int riga) {
        this.playerSymbol = playerSymbol;
        this.setDefaultPlayerPosition(colonna, riga);
        this.setPlayerPosition(colonna, riga);
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    public int getPlayerColumn(){
        return playerPosition[0];
    }

    public int getPlayerRow(){
        return playerPosition[1];
    }

    public int[] getDefaultPlayerPosition() {
        return defaultPlayerPosition;
    }

    public int[] getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Metodo per aggiungere i soldi al giocatore
     */
    public void addPlayerMoney() {
        this.playerMoney++;
    }

    /**
     * Metodo utilizzato per la sottrazione dei soldi di un giocatore
     * @return true, nel caso i soldi vengano sottratti, false nel caso il giocatore non ha più soldi
     */
    public boolean subPlayerMoney(){
        if(playerMoney != 0) playerMoney--;
        else return false;

        return true;
    }

    /**
     * Metodo per impostare la posizione di default del giocatore
     * @param colonna, colonna di default di posizionamento del giocatore
     * @param riga, riga di default di posizionamento del giocatore
     */
    private void setDefaultPlayerPosition(int colonna, int riga){
        int [] position = new int[2];

        if((colonna >= 0 && colonna <= Grid.GRID_SIZE) && (riga >= 0 && riga <= Grid.GRID_SIZE)) {
            position[0] = colonna;
            position[1] = riga;
        }else{
            position[0] = 0;
            position[1] = 1;
        }

        this.defaultPlayerPosition = position;
    }

    /**
     * Metodo per impostare la posizione del giocatore sulla griglia
     * @param colonna, colonna di posizionamento del giocatore
     * @param riga, riga di posizionamento del giocatore
     */
    public void setPlayerPosition(int colonna, int riga) {
        int [] position = new int[2];

        if((colonna >= 0 && colonna <= Grid.GRID_SIZE) && (riga >= 0 && riga <= Grid.GRID_SIZE)) {
            position[0] = colonna;
            position[1] = riga;
        }else{
            position[0] = 0;
            position[1] = 1;
        }

        this.playerPosition = position;
    }
}
