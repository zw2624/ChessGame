package Main;

import java.util.ArrayList;

public class Game {
    public Board myBoard;
    public int turn;

    public Player white, black;


    public Game() {
        this.myBoard = new Board(this);
        white = new Player(1, true);
        black = new Player(2, false);
        int turn = 0;
    }

    public Player getOpponent(Player p) {
        if (white.getPlayerID() == p.getPlayerID()) {
            return black;
        } else {
            return white;
        }
    }

    public boolean ischeckmate(Player p) {
        Player op = getOpponent(p);
        int[] kingpos = this.myBoard.kingsPos[p.getPlayerID()];
        ArrayList<Piece> opPieces = op.getPieces();
        for (int i = 0; i < opPieces.size(); i++) {
            Piece opP = opPieces.get(i);
            if (opP.checkMove(this.myBoard, opP.x, opP.y, kingpos[0], kingpos[1])) {
                return true;
            }
        }
        return false;
    }





    private Cell selectCell(Cell[] list) {
        return list[0];
    }
}
