package Main.Pieces;

import Main.Board;
import Main.Piece;

public class Knight extends Piece {
    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
