package model.Pieces;

import model.Board;
import model.Piece;
import model.Player;

/**
 * Custom Piece by Zihe Wang extending Pieces Object
 * Same as '仕' in Chinese Chess
 * Includes an Boolean isFirst to indicate if that is the first move of the object
 *
 * Rule:
 * 1. can move forward to a square diagonally in front of it on an adjacent file
 * 2. on its first move it can advance one squares along the same file
 */
public class Guard extends Piece {
    private boolean isFirst;

    public Guard(String name, Player player, int x, int y) {
        super(name, player, x, y);
        isFirst = true;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        int direction = this.player.getPlayerID() == 1 ? -1:1;
        boolean oneForward = false;
        if (isFirst) {
            oneForward = (fromX == toX) & (toY == fromY + 1 * direction);
        }
        boolean cross = Math.abs(fromX - toX) == 1 & Math.abs(fromY - toY) == 1;
        return cross || oneForward;
    }
}
