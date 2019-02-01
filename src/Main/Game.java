package Main;

public class Game {
    private Board myBoard;
    private int turn;

    public Game() {
        this.myBoard = new Board();
        int turn = 0;
    }

    /*To be implemented
     *
     * */
    private Cell selectCell(Cell[] list) {
        return list[0];
    }
}
