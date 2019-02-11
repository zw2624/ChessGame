package model.Pieces;

import model.Board;
import model.Piece;
import model.Player;

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
