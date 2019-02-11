package model;

import model.Pieces.*;

public class Board {


    private Game g;
    private Cell[][] grid = new Cell[8][8];
    public int[][] kingsPos;
    public Piece[] Kings;

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
        Kings = new Piece[2];
    }

    /**
     * Set up the board with normal start.
     */
    public void setup() {
        Piece whiteLeftRook = new Rook("White Rook", g.white, 0,0);
        Piece whiteRightRook = new Rook("White Rook", g.white, 7,0);
        Piece whiteLeftKnight = new Knight("White Knight", g.white, 1,0);
        Piece whiteRightKnight = new Knight("White Knight", g.white, 6,0);
        Piece whiteQueen = new Queen("White Queen", g.white, 4,0);
        Piece whiteLeftBishop = new Bishop("White Bishop", g.white,2,0);
        Piece whiteRightBishop = new Bishop("White Bishop", g.white,5,0);
        Piece whiteKing = new King("White King", g.white,3,0);
        this.grid[0][0].setCurrent(whiteLeftRook);
        this.grid[1][0].setCurrent(whiteLeftKnight);
        this.grid[2][0].setCurrent(whiteLeftBishop);
        this.grid[3][0].setCurrent(whiteKing);
        this.grid[4][0].setCurrent(whiteQueen);
        this.grid[5][0].setCurrent(whiteRightBishop);
        this.grid[6][0].setCurrent(whiteRightKnight);
        this.grid[7][0].setCurrent(whiteRightRook);
        for (int i = 0; i < 8; i++) {
            Piece whitePawn = new Pawn("White Pawn", g.white, i,1);
            this.grid[i][1].setCurrent(whitePawn);
        }

        Piece blackLeftRook = new Rook("Black Rook", g.black,0,7);
        Piece blackRightRook = new Rook("Black Rook", g.black,7,7);
        Piece blackLeftKnight = new Knight("Black Knight", g.black,1,7);
        Piece blackRightKnight = new Knight("Black Knight", g.black,6,7);
        Piece blackQueen = new Queen("Black Queen", g.black,4,7);
        Piece blackLeftBishop = new Bishop("Black Bishop", g.black,2,7);
        Piece blackRightBishop = new Bishop("Black Bishop", g.black,5,7);
        Piece blackKing = new King("Black King", g.black,3,7);
        this.grid[0][7].setCurrent(blackLeftRook);
        this.grid[1][7].setCurrent(blackLeftKnight);
        this.grid[2][7].setCurrent(blackLeftBishop);
        this.grid[3][7].setCurrent(blackKing);
        this.grid[4][7].setCurrent(blackQueen);
        this.grid[5][7].setCurrent(blackRightBishop);
        this.grid[6][7].setCurrent(blackRightKnight);
        this.grid[7][7].setCurrent(blackRightRook);
        for (int i = 0; i < 8; i++) {
            Piece blackPawn = new Pawn("Black Pawn", g.white, i, 6);
            this.grid[i][6].setCurrent(blackPawn);
        }

        Kings[0] = whiteKing;
        Kings[1] = blackKing;

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

    public Boolean hasOneBetween(int fromX, int fromY, int toX, int toY) {
        int count = 0;
        boolean isLine = (fromX == toX) || (fromY == toY);
        if (!isLine) return false;
        if (fromX == toX) {
            int small = fromY < toY ? fromY : toY;
            int big = fromY > toY ? fromY : toY;
            if (big - small == 1){
                return false;
            }
            for (int i = small + 1; i < big; i++) {
                Cell cur = this.grid[fromX][i];
                if (cur.getOwner() != 2) {
                    count ++;
                }
            }
        } else {
            int small = fromX < toX ? fromX : toX;
            int big = fromX > toX ? fromX : toX;
            if (big - small == 1){
                return false;
            }
            for (int i = small + 1; i < big; i++) {
                Cell cur = this.grid[i][fromY];
                if (cur.getOwner() != 2) {
                    count ++;
                }
            }
        }
        return  count == 1;
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
    public boolean movePiece(Player p, int fromX, int fromY, int toX, int toY) {
        Cell from = this.getCell(fromX, fromY);
        String description;

        if (g.next != p.getPlayerID()) {
            description = "Not your turn";
            System.out.println(description);
            return false;
        }
        if (from.getOwner() == 2) {
            description = "No piece in the cell";
            System.out.println(description);
            return false;
        }
        if (p.getPlayerID() != from.getOwner()) {
            description = "Not your pieces!";
            System.out.println(description);
            return false;
        }
        if (toX > 7 || toX < 0 || toY > 7 || toY <0) {
            description = "Index out of bound";
            System.out.println(description);
            return false;
        }
        Cell to = this.getCell(toX, toY);
        Piece curPiece = from.getCurrent();
        if (from.canGetTo(this,toX, toY)) {
            boolean hasMine = to.getOwner() == from.getOwner();
            if (hasMine) {
                description = "That cell has your piece!";
                System.out.println(description);
                return false;
            } else {
                if (curPiece instanceof Pawn) {
                    ((Pawn) curPiece).setFirst(false);
                }
                if (curPiece instanceof Guard) {
                    ((Guard) curPiece).setFirst(false);
                }
                if (curPiece instanceof King) {
                    if (!g.tryMove(curPiece.player, curPiece, toX, toY)) {
                        description = "Your King will be in Check!";
                        return false;
                    }
                }
                description = "Move " + curPiece.Name + " to (" + toX + ", " + toY + ")";
                g.getOpponent(p).removePiece(to.getCurrent());
                to.removePiece();
                to.setCurrent(curPiece);
                from.removePiece();
                g.next = g.getOpponent(p).getPlayerID();
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
