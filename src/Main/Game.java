package Main;

public class Game {
    private Board myBoard;
    private int turn;
    public Player white, black;


    public Game() {
        this.myBoard = new Board();
        white = new Player(1);
        black = new Player(2);
        int turn = 0;
    }

    /*To be implemented
     *
     * */
    private Cell selectCell(Cell[] list) {
        return list[0];
    }
}
