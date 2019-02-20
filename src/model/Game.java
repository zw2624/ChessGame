package model;

import model.Pieces.Catapult;
import model.Pieces.Pawn;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Game Object contains all game information
 * Including the chessboard, players and whose turn it is
 */
public class Game {
    public Board myBoard;
    public int next;
    public Player white, black;
    public ArrayList<Move> moveHistory;


    /**
     * Constructor
     */
    public Game(String ... args) {
        this.myBoard = new Board(this);
        String name1 = args.length == 0 ? "white":args[0];
        String name2 = args.length == 0 ? "black":args[1];
        white = new Player(name1, 0, true);
        black = new Player(name2, 1, false);
        moveHistory = new ArrayList<Move>();
        int next = 0;
    }

    /**
     * restart the game
     * @param custom if players want to use custom pieces
     */
    public void restart(boolean custom) {
        this.myBoard = new Board(this);
        this.next = 0;
        white.first = true;
        black.first = false;
        white.removeAllPiece();
        black.removeAllPiece();
        moveHistory.removeAll(moveHistory);
        myBoard.setup(custom);
    }

    /**
     * Undo the previous move
     */
    public void undo() {
        Player cur = getPlayer(this.next);
        Player lastPlayer = getOpponent(getPlayer(this.next));
        this.next = lastPlayer.getPlayerID();
        Move lastMove = myBoard.getLastMove();
        Cell from = lastMove.getFrom();
        Cell to = lastMove.getTo();
        from.setCurrent(lastMove.getMyPiece());
        if (lastMove.getMyPiece() instanceof Pawn) {
            if (Math.abs(from.y - to.y) == 2) {
                ((Pawn) lastMove.getMyPiece()).setFirst(true);
            }
            if (from.y == 1 & lastPlayer.getPlayerID() == 0) {
                ((Pawn) lastMove.getMyPiece()).setFirst(true);
            }
            if (from.y == 6 & lastPlayer.getPlayerID() == 1) {
                ((Pawn) lastMove.getMyPiece()).setFirst(true);
            }
        }
        to.setCurrent(lastMove.getEaten());
        cur.addPiece(lastMove.getEaten());
        this.moveHistory.add(new Move(lastPlayer, null, null, null, null));
    }

    /**
     * Get the opponent of player p
     * @param p Player p
     * @return opponent of player p
     */
    public Player getOpponent(Player p) {
        if (white.getPlayerID() == p.getPlayerID()) {
            return black;
        } else {
            return white;
        }
    }


    /**
     * Get the player
     * @param id PlayerID
     * @return a Player Object with that ID
     */
    public Player getPlayer(int id) {
        if (white.getPlayerID() == id) {
            return white;
        } else {
            return black;
        }
    }


    /**
     * get the pieces that make a Player in check
     * @param me the Player being checked
     * @return an ArrayList contains all pieces that make a Player in check
     */
    public ArrayList<Piece> inCheck(Player me) {
        Player op = getOpponent(me);
        int[] kingpos = {this.myBoard.Kings[me.getPlayerID()].x, this.myBoard.Kings[me.getPlayerID()].y};
        ArrayList<Piece> opPieces = op.getPieces();
        ArrayList<Piece> threats = new ArrayList<Piece>();
        for (int i = 0; i < opPieces.size(); i++) {
            Piece opP = opPieces.get(i);
            if (opP.checkMove(this.myBoard, opP.x, opP.y, kingpos[0], kingpos[1])) {
                boolean notLeap = myBoard.checkLeap(opP.x, opP.y, kingpos[0], kingpos[1]);
                if (notLeap) {
                    threats.add(opP);
                }
            }
        }
        return threats;
    }

    /**
     * try a move and check if inCheck after the move
     * @param me the Player being checked
     * @param defender the Piece used to kill the threat
     * @param tx int x of the threat piece
     * @param ty int y of the threat piece
     * @return a boolean if player can kill the threat after the move
     */
    public boolean tryMove(Player me, Piece defender, int tx, int ty) {
        int dx = defender.x;
        int dy = defender.y;
        Cell tcell = myBoard.getCell(tx, ty);
        Cell dcell = myBoard.getCell(dx, dy);
        Piece threat = tcell.getCurrent();
        boolean flag;
        if (threat != null) {
            dcell.removePiece();
            tcell.removePiece();
            tcell.setCurrent(defender);
            flag = inCheck(me).size() == 0;
            tcell.removePiece();
            tcell.setCurrent(threat);
        } else {
            dcell.removePiece();
            tcell.setCurrent(defender);
            flag = inCheck(me).size() == 0;
            tcell.removePiece();
        }
        dcell.setCurrent(defender);
        return flag;

    }

    /**
     * Check if a player has valid move
     * @param me the player being check
     * @return a boolean if Stalemate
     */
    public boolean isStalemate(Player me) {
        if (inCheck(me).size() != 0) return false;
        ArrayList<Piece> myPieces = me.getPieces();
        for (int i = 0; i < myPieces.size(); i++) {
            Piece p = myPieces.get(i);
            for (int j = 0; j < 8; j ++) {
                for (int k = 0; k < 8; k ++) {
                    if(p.checkMove(this.myBoard, p.x, p.y, j, k)) {
                        if (tryMove(me, p, j, k)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    /**
     * Check if a player has valid move after a in-check
     * @param me the player being check
     * @param threats Pieces that can eat the king.
     * @return a boolean if Checkmate
     */
    public boolean isCheckmate(Player me, ArrayList<Piece> threats) {
        ArrayList<Piece> myPieces = me.getPieces();
        int[] kingpos = {this.myBoard.Kings[me.getPlayerID()].x, this.myBoard.Kings[me.getPlayerID()].y};
        if (threats.size() == 1) {
            for (int i = 0; i < myPieces.size(); i++) {
                Piece p = myPieces.get(i);
                if (p.checkMove(this.myBoard, p.x, p.y, kingpos[0], kingpos[1])) {
                    Piece threat = threats.get(0);
                    if (tryMove(me, p, threat.x,threat.y)) {
                         //System.out.println("can eat: " + p.Name);
                         //System.out.println(threat.x + " " + threat.y);
                        return false;
                    }
                }
            }
        }

        boolean cum = false;
        for (int i = 0; i < myPieces.size(); i++) {
            Piece p = myPieces.get(i);
            for (int j = 0; j < 8; j ++) {
                for (int k = 0; k < 8; k ++) {
                    boolean canMove = p.checkMove(this.myBoard, p.x, p.y, j, k);
                    boolean notMine = myBoard.getCell(j, k).getOwner() != me.getPlayerID();
                    if (canMove & notMine) {
                        //if (tryMove(me, p, j, k)) {
                            // System.out.println("possible move: " + p.Name);
                            // System.out.println(j + " " + k);
                        //}
                        cum = cum || tryMove(me, p, j, k);
                    }
                }
            }
        }

        return !cum;

    }

}
