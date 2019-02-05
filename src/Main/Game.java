package Main;

import java.util.ArrayList;

public class Game {
    public Board myBoard;
    public int next;
    public Player white, black;


    public Game() {
        this.myBoard = new Board(this);
        white = new Player(0, true);
        black = new Player(1, false);
        int next = 0;
    }

    public Player getOpponent(Player p) {
        if (white.getPlayerID() == p.getPlayerID()) {
            return black;
        } else {
            return white;
        }
    }


    public ArrayList<Piece> inCheck(Player me) {
        Player op = getOpponent(me);
        int[] kingpos = {this.myBoard.Kings[me.getPlayerID()].x, this.myBoard.Kings[me.getPlayerID()].y};
        ArrayList<Piece> opPieces = op.getPieces();
        ArrayList<Piece> threats = new ArrayList<Piece>();
        for (int i = 0; i < opPieces.size(); i++) {
            Piece opP = opPieces.get(i);
            if (opP.checkMove(this.myBoard, opP.x, opP.y, kingpos[0], kingpos[1])) {
                threats.add(opP);
            }
        }
        return threats;
    }



    public boolean tryMove(Player me, Piece defender, int tx, int ty) {
        int dx = defender.x;
        int dy = defender.y;
        Cell tcell = myBoard.getCell(tx, ty);
        Cell dcell = myBoard.getCell(dx, dy);
        Piece threat = tcell.getCurrent();
        boolean flag;
        if (threat != null) {
            dcell.removePiece();
            tcell.setCurrent(defender);
            flag = inCheck(me).size() == 0;
            tcell.removePiece();
            tcell.setCurrent(threat);
        } else {
            tcell.setCurrent(defender);
            flag = inCheck(me).size() == 0;
            tcell.removePiece();
        }
        dcell.setCurrent(defender);
        return flag;

    }

    public boolean isStalemate(Player me) {
        ArrayList<Piece> myPieces = me.getPieces();
        for (int i = 0; i < myPieces.size(); i++) {
            Piece p = myPieces.get(i);
            for (int j = 0; j < 8; j ++) {
                for (int k = 0; k < 8; k ++) {
                    if(p.checkMove(this.myBoard, p.x, p.y, j, k)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean isCheckmate(Player me, ArrayList<Piece> threats) {
        ArrayList<Piece> myPieces = me.getPieces();
        int[] kingpos = {this.myBoard.Kings[me.getPlayerID()].x, this.myBoard.Kings[me.getPlayerID()].y};
        if (threats.size() == 1) {
            for (int i = 0; i < myPieces.size(); i++) {
                Piece p = myPieces.get(i);
                if (p.checkMove(this.myBoard, p.x, p.y, kingpos[0], kingpos[1])) {
                    Piece threat = threats.get(0);
                    if (tryMove(me, p, threat.x,threat.y)) {
                        // System.out.println("can eat: " + p.Name);
                        // System.out.println(threat.x + " " + threat.y);
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
                        if (tryMove(me, p, j, k)) {
                            // System.out.println("possible move: " + p.Name);
                            // System.out.println(j + " " + k);
                        }
                        cum = cum || tryMove(me, p, j, k);
                    }
                }
            }
        }

        return !cum;

    }

}
