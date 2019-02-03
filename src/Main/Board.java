package Main;

public class Board {


    private Cell[][] grid = new Cell[8][8];
    private int[] whiteKing;
    private int[] blackKing;

    public Board() {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                this.grid[i][j] = new Cell(i, j);
            }
        }
        whiteKing = null;
        blackKing = null;
    }

    public void setGrid(int player, int x, int y, Piece P) {
        Cell cell = this.grid[x][y];
        cell.setCurrent(P);
        cell.setOwner(player);
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    public boolean isWhiteKing(int x, int y) {
        return x == whiteKing[0] & y == whiteKing[1];
    }

    public boolean isBlackKing(int x, int y) {
        return x == blackKing[0] & y == blackKing[1];
    }

    public void setWhiteKing(int x, int y) {
        whiteKing[0] = x;
        whiteKing[1] = y;
    }

    public void setBlackKing(int x, int y) {
        blackKing[0] = x;
        blackKing[1] = y;
    }


    public Cell findPiece(String Name) {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j].getCurrent().Name == Name) {
                    return grid[i][j];
                }
            }
        }
        System.out.println("There is no " + Name + " on the board.");
        return null;
    }



    public boolean movePiece(int fromX, int fromY, int toX, int toY) {
        Cell from = this.getCell(fromX, fromY);
        Cell to = this.getCell(toX, toY);
        Piece curPiece = from.getCurrent();
        String description;
        if (curPiece == null) {
            description = "No piece in the cell";
            System.out.println(description);
            return false;
        }
        if (from.canGetTo(this,toX, toY)) {
            boolean hasMine = to.getOwner() == from.getOwner();
            if (hasMine) {
                description = "That cell has our piece!";
                System.out.println(description);
                return false;
            } else {
                if (isBlackKing(fromX, fromY)) {
                    setBlackKing(toX, toY);
                }
                if (isWhiteKing(fromX, fromY)) {
                    setWhiteKing(toX, toY);
                }
                to.removePiece();
                to.setCurrent(curPiece);
                from.removePiece();
                description = "Move " + curPiece.Name + " to (" + toX + ", " + toY + ")";
                System.out.println(description);
                return true;
            }
        } else {
            description = "Not a valid move for " + curPiece.Name;
            System.out.println(description);
            return false;
        }
    }



}
