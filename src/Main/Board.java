package Main;

public class Board {
    private Cell[][] grid = new Cell[8][8];

    public Board() {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                this.grid[i][j] = new Cell(i, j);
            }
        }


    }

    public Cell[] getMoveAble(int player) {

    }
}
