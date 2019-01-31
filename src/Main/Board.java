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

    public void setGrid(int player, int x, int y, Piece P) {
        Cell cell = this.grid[x][y];
        cell.setCurrent(P);
        cell.setOwner(player);
    }
/*
    public void setUp() {
        Cell[][] grid = this.grid;
        this.setGrid(1,0, 0, );

    }
*/
    public Cell getCell(int x, int y) {
        return grid[x][y];
    }
}
