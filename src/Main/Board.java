package Main;

import Main.Pieces.*;

import java.util.List;

public class Board {


    private Game g;
    private Cell[][] grid = new Cell[8][8];
    public int[][] kingsPos;

    public Board(Game game) {
        this.g = game;
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                this.grid[i][j] = new Cell(i, j);
            }
        }
        kingsPos = new int[][] {{3, 0},{3, 7}};
    }

    public void setup() {
        Piece whiteLeftRook = new Rook("White Rook", g.white, 0,0, null);
        Piece whiteRightRook = new Rook("White Rook", g.white, 7,0, null);
        Piece whiteLeftKnight = new Knight("White Knight", g.white, 1,0, null);
        Piece whiteRightKnight = new Knight("White Knight", g.white, 6,0, null);
        Piece whiteQueen = new Queen("White Queen", g.white, 4,0, null);
        Piece whiteLeftBishop = new Bishop("White Bishop", g.white,2,0, null);
        Piece whiteRightBishop = new Bishop("White Bishop", g.white,5,0, null);
        Piece whiteKing = new King("White King", g.white,3,0, null);
        this.grid[0][0].setCurrent(whiteLeftRook);
        this.grid[1][0].setCurrent(whiteLeftKnight);
        this.grid[2][0].setCurrent(whiteLeftBishop);
        this.grid[3][0].setCurrent(whiteKing);
        this.grid[4][0].setCurrent(whiteQueen);
        this.grid[5][0].setCurrent(whiteRightBishop);
        this.grid[6][0].setCurrent(whiteRightKnight);
        this.grid[7][0].setCurrent(whiteRightRook);
        for (int i = 0; i < 8; i++) {
            Piece whitePawn = new Pawn("White Pawn", g.white, i,1, null);
            this.grid[i][1].setCurrent(whitePawn);
        }

        Piece blackLeftRook = new Rook("Black Rook", g.black,0,7, null);
        Piece blackRightRook = new Rook("Black Rook", g.black,7,7, null);
        Piece blackLeftKnight = new Knight("Black Knight", g.black,1,7, null);
        Piece blackRightKnight = new Knight("Black Knight", g.black,6,7, null);
        Piece blackQueen = new Queen("Black Queen", g.black,4,7, null);
        Piece blackLeftBishop = new Bishop("Black Bishop", g.black,2,7, null);
        Piece blackRightBishop = new Bishop("Black Bishop", g.black,5,7, null);
        Piece blackKing = new King("Black King", g.black,3,7, null);
        this.grid[0][7].setCurrent(blackLeftRook);
        this.grid[1][7].setCurrent(blackLeftKnight);
        this.grid[2][7].setCurrent(blackLeftBishop);
        this.grid[3][7].setCurrent(blackKing);
        this.grid[4][7].setCurrent(blackQueen);
        this.grid[5][7].setCurrent(blackRightBishop);
        this.grid[6][7].setCurrent(blackRightKnight);
        this.grid[7][7].setCurrent(blackRightRook);
        for (int i = 0; i < 8; i++) {
            Piece blackPawn = new Pawn("Black Pawn", g.white, i, 6, null);
            this.grid[i][6].setCurrent(blackPawn);
        }



    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    public boolean isWhiteKing(int x, int y) {
        return x == kingsPos[0][0] & y == kingsPos[0][1];
    }

    public boolean isBlackKing(int x, int y) {
        return x == kingsPos[1][0] & y == kingsPos[1][1];
    }

    public void setWhiteKing(int x, int y) {
        kingsPos[0][0] = x;
        kingsPos[0][1] = y;
    }

    public void setBlackKing(int x, int y) {
        kingsPos[1][0] = x;
        kingsPos[1][1] = y;
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
