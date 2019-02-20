package model;

import model.Pieces.*;

import java.util.ArrayList;


/**
 * Board contains infomation of the chessborad
 * including Cells and have direct access to two King Objects
 */
public class Board {


    private Game g;
    private Cell[][] grid = new Cell[8][8];
    public Piece[] Kings;
    private Move lastMove;

    /**
     * Constructor of Board Object
     * @param game
     */
    public Board(Game game) {
        this.g = game;
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                this.grid[i][j] = new Cell(i, j);
            }
        }
        lastMove = null;
        Kings = new Piece[2];
    }

    public Move getLastMove() {
        return lastMove;
    }

    /**
     * Set up the board with normal start.
     */
    public void setup(boolean custom) {
        Piece whiteLeftRook = new Rook("White Rook", g.white, 0,0);
        Piece whiteRightRook = new Rook("White Rook", g.white, 7,0);
        Piece whiteLeftKnight = new Knight("White Knight", g.white, 1,0);
        Piece whiteRightKnight = new Knight("White Knight", g.white, 6,0);
        Piece whiteQueen = new Queen("White Queen", g.white, 3,0);
        Piece whiteLeftBishop = new Bishop("White Bishop", g.white,2,0);
        Piece whiteRightBishop = new Bishop("White Bishop", g.white,5,0);
        Piece whiteKing = new King("White King", g.white,4,0);
        this.grid[0][0].setCurrent(whiteLeftRook);
        this.grid[1][0].setCurrent(whiteLeftKnight);
        this.grid[2][0].setCurrent(whiteLeftBishop);
        this.grid[4][0].setCurrent(whiteKing);
        this.grid[3][0].setCurrent(whiteQueen);
        this.grid[5][0].setCurrent(whiteRightBishop);
        this.grid[6][0].setCurrent(whiteRightKnight);
        this.grid[7][0].setCurrent(whiteRightRook);

        Piece blackLeftRook = new Rook("Black Rook", g.black,0,7);
        Piece blackRightRook = new Rook("Black Rook", g.black,7,7);
        Piece blackLeftKnight = new Knight("Black Knight", g.black,1,7);
        Piece blackRightKnight = new Knight("Black Knight", g.black,6,7);
        Piece blackQueen = new Queen("Black Queen", g.black,3,7);
        Piece blackLeftBishop = new Bishop("Black Bishop", g.black,2,7);
        Piece blackRightBishop = new Bishop("Black Bishop", g.black,5,7);
        Piece blackKing = new King("Black King", g.black,4,7);
        this.grid[0][7].setCurrent(blackLeftRook);
        this.grid[1][7].setCurrent(blackLeftKnight);
        this.grid[2][7].setCurrent(blackLeftBishop);
        this.grid[4][7].setCurrent(blackKing);
        this.grid[3][7].setCurrent(blackQueen);
        this.grid[5][7].setCurrent(blackRightBishop);
        this.grid[6][7].setCurrent(blackRightKnight);
        this.grid[7][7].setCurrent(blackRightRook);

        Kings[0] = whiteKing;
        Kings[1] = blackKing;

        if (custom) {
            Piece blackCata1 = new Catapult("Black Cannon", g.black, 1, 6);
            Piece whiteCata1 = new Catapult("White Cannon", g.white, 1, 1);
            Piece blackCata2 = new Catapult("Black Cannon", g.black, 6, 6);
            Piece whiteCata2 = new Catapult("White Cannon", g.white, 6, 1);
            this.grid[1][6].setCurrent(blackCata1);
            this.grid[1][1].setCurrent(whiteCata1);
            this.grid[6][6].setCurrent(blackCata2);
            this.grid[6][1].setCurrent(whiteCata2);

            Piece whiteGaurd1 = new Guard("White Guard", g.white, 3, 1);
            Piece whiteGaurd2 = new Guard("White Guard", g.white, 4, 1);
            Piece blackGaurd1 = new Guard("Black Guard", g.black, 3, 6);
            Piece blackGaurd2 = new Guard("Black Guard", g.black, 4, 6);
            this.grid[3][1].setCurrent(whiteGaurd1);
            this.grid[4][1].setCurrent(whiteGaurd2);
            this.grid[3][6].setCurrent(blackGaurd1);
            this.grid[4][6].setCurrent(blackGaurd2);

            Piece whitePawn1 = new Pawn("White Pawn", g.white, 0, 1);
            Piece whitePawn2 = new Pawn("White Pawn", g.white, 2, 1);
            Piece whitePawn3 = new Pawn("White Pawn", g.white, 5, 1);
            Piece whitePawn4 = new Pawn("White Pawn", g.white, 7, 1);
            Piece blackPawn1 = new Pawn("Black Pawn", g.black, 0, 6);
            Piece blackPawn2 = new Pawn("Black Pawn", g.black, 2, 6);
            Piece blackPawn3 = new Pawn("Black Pawn", g.black, 5, 6);
            Piece blackPawn4 = new Pawn("Black Pawn", g.black, 7, 6);
            this.grid[0][1].setCurrent(whitePawn1);
            this.grid[2][1].setCurrent(whitePawn2);
            this.grid[5][1].setCurrent(whitePawn3);
            this.grid[7][1].setCurrent(whitePawn4);
            this.grid[0][6].setCurrent(blackPawn1);
            this.grid[2][6].setCurrent(blackPawn2);
            this.grid[5][6].setCurrent(blackPawn3);
            this.grid[7][6].setCurrent(blackPawn4);
        } else {
            for (int i = 0; i < 8; i++) {
                Piece whitePawn = new Pawn("White Pawn", g.white, i,1);
                this.grid[i][1].setCurrent(whitePawn);
            }
            for (int i = 0; i < 8; i++) {
                Piece blackPawn = new Pawn("Black Pawn", g.black, i, 6);
                this.grid[i][6].setCurrent(blackPawn);
            }
        }


    }


    /**
     * get a cell on board
     * @param x int x of wanted Cell
     * @param y int y of wanted Cell
     * @return Corresponding Cell Object
     */
    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    /**
     * Check if there is only one piece between two places in a line
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @return a boolean if there is only one
     */
    public boolean hasOneBetween(int fromX, int fromY, int toX, int toY) {
        boolean isLine = (fromX == toX) || (fromY == toY);
        if (!isLine) return false;
        int big;
        int small;
        int fix;
        boolean flag;
        if (fromX == toX) {
            small = fromY < toY ? fromY : toY;
            big = fromY > toY ? fromY : toY;
            fix = fromX;
            flag = true;
        } else {
            big = fromX > toX ? fromX : toX;
            small = fromX < toX ? fromX : toX;
            fix = fromY;
            flag = false;
        }
        if (big - small == 1){
            return false;
        }
        return forLoopCheckLine(flag, fix, small, big) == 1;
    }


    /**
     * Check if there is no piece between two places in a diagonal line
     * @return a boolean if there none
     */
    public boolean forLoopCheckDiag(int fromX, int fromY, int toX, int toY){
        if (Math.abs(fromX - toX) == 1) return true;
        if (fromX < toX && fromY < toY) {
            return checkDiagEmpty(fromX + 1, fromY + 1, toX - 1, toY - 1);
        } else if (fromX > toX && fromY > toY) {
            return checkDiagEmpty(fromX - 1, fromY - 1, toX + 1, toY + 1);
        } else if (fromX < toX && fromY > toY) {
            return checkDiagEmpty(fromX + 1, fromY - 1, toX - 1, toY + 1);
        } else if (fromX > toX && fromY < toY) {
            return checkDiagEmpty(fromX - 1, fromY + 1, toX + 1, toY - 1);
        }
        return false;
    }

    /**
     * Helper function, check if a diagonal line is clear (all cells empty)
     * @return a boolean
     */
    public boolean checkDiagEmpty(int fromX, int fromY, int toX, int toY) {
        if (fromX == toX) return this.grid[fromX][fromY].getOwner() == 2;
        if (this.grid[fromX][fromY].getOwner() == 2 & this.grid[toX][toY].getOwner() == 2) {
            if (Math.abs(fromX - toX) == 1) return true;
            if (fromX < toX && fromY < toY) {
                return checkDiagEmpty(fromX + 1, fromY + 1, toX - 1, toY - 1);
            } else if (fromX > toX && fromY > toY) {
                return checkDiagEmpty(fromX - 1, fromY - 1, toX + 1, toY + 1);
            } else if (fromX < toX && fromY > toY) {
                return checkDiagEmpty(fromX + 1, fromY - 1, toX - 1, toY + 1);
            } else if (fromX > toX && fromY < toY) {
                return checkDiagEmpty(fromX - 1, fromY + 1, toX + 1, toY - 1);
            }
        }
        return false;

    }

    /**
     * helper function
     * @param isX
     * @param fix
     * @param small
     * @param big
     * @return number of occupied cells
     */
    public int forLoopCheckLine(Boolean isX, int fix, int small, int big) {
        int count = 0;
        for (int i = small+1; i < big; i++) {
            if (isX) {
                if (this.getCell(fix, i).getOwner() != 2) count ++;
            } else {
                if (this.getCell(i, fix).getOwner() != 2) count ++;
            }
        }
        return count;
    }

    /**
     * Check if the move is leaping over other pieces.
     * Also checked if moving in line or diagonally
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @return
     */
    public boolean checkLeap(int fromX, int fromY, int toX, int toY) {
        boolean isXLine = fromX == toX;
        boolean isYLine = fromY == toY;
        boolean isDiag = Math.abs(fromX - toX) == Math.abs(fromY - toY);
        int small;
        int big;
        int fix;
        boolean flag;
        if (!isDiag & !isXLine & !isYLine) return true;
        if (isDiag) {
            return forLoopCheckDiag(fromX, fromY, toX, toY);
        }
        if (isXLine) {
            small = fromY < toY ? fromY : toY;
            big = fromY > toY ? fromY : toY;
            fix = fromX;
            flag = true;
        } else {
            big = fromX > toX ? fromX : toX;
            small = fromX < toX ? fromX : toX;
            fix = fromY;
            flag = false;
        }
        if (big - small == 1){
            return true;
        }
        return forLoopCheckLine(flag, fix, small, big) == 0;

    }

    /**
     * Generate possible moves for a pieces
     * @param cur the cell containing the Piece.
     * @return an ArrayList contains all possible target cells
     */
    public ArrayList<Cell> possibleMove(Cell cur) {
        ArrayList<Cell> ret = new ArrayList<>();
        int fromX = cur.x;
        int fromY = cur.y;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boolean b1 = cur.canGetTo(this, i, j);
                boolean hasMine = cur.getOwner() == this.getCell(i,j).getOwner();
                boolean notLeap = this.checkLeap(fromX, fromY, i, j);
                if (b1 & !hasMine & notLeap) {
                    ret.add(this.getCell(i,j));
                }
            }
        }
        return ret;
    }



    /**
     * move piece on board
     * @param p the player moving the piece
     * @param fromX int x of selected cell
     * @param fromY int y of selected cell
     * @param toX int x of target cell
     * @param toY int y of target cell
     * @return a boolean if the move is made.
     */
    public int movePiece(Player p, int fromX, int fromY, int toX, int toY) {
        Cell from = this.getCell(fromX, fromY);
        if (g.next != p.getPlayerID()) {
            return 9;
        }
        if (from.getOwner() == 2) {
            return 8;
        }
        if (p.getPlayerID() != from.getOwner()) {
            return 7;
        }
        if (toX > 7 || toX < 0 || toY > 7 || toY <0) {
            return 6;
        }
        Cell to = this.getCell(toX, toY);
        Piece curPiece = from.getCurrent();

        if (from.canGetTo(this,toX, toY)) {
            boolean hasMine = to.getOwner() == from.getOwner();
            boolean noleap = this.checkLeap(fromX, fromY, toX, toY);
            if (!noleap & !(curPiece instanceof Catapult)) return 5;
            if (hasMine) {
                return 4;
            } else {
                if (curPiece instanceof Pawn) {
                    ((Pawn) curPiece).setFirst(false);
                }
                if (curPiece instanceof Guard) {
                    ((Guard) curPiece).setFirst(false);
                }
                System.out.println("Start verify move: ");
                if (!g.tryMove(curPiece.player, curPiece, toX, toY)) {
                    return 3;
                }
                System.out.println("End verify move: ");
                lastMove = new Move(p, from, to, curPiece, to.getCurrent());
                g.moveHistory.add(lastMove);

                g.getOpponent(p).removePiece(to.getCurrent());
                to.removePiece();
                to.setCurrent(curPiece);
                from.removePiece();
                g.next = g.getOpponent(p).getPlayerID();

                return 1;
            }
        } else {
            return 0;
        }
    }



}
