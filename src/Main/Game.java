package Main;

public class Game {
    private Board myBoard;
    private int turn;

    public Game() {
        this.myBoard = new Board();
        int turn = 0;
    }

    public makeMove() {
        int player = this.turn % 2;
        Cell[] Moveable = this.myBoard;
        Cell selectedCell = selectCell(Moveable);
        
        this.turn = this.turn + 1;
    }

    /*To be implemented
    *
    * */
    private Cell selectCell(Cell[] list) {
        return list[0]
    }
}
