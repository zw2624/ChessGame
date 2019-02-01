package Main.Pieces;

import Main.Board;
import Main.Piece;

public class Bishop extends Piece {

    public Bishop() {
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        boolean moved = (fromX != toX) & (fromY != toY);
        boolean isCross = Math.abs(fromX - toX) == Math.abs(fromY - toY);
        boolean hasMine = grid.getCell(toX, toY).getOwner() == grid.getCell(fromX, fromY).getOwner();
        return  moved & isCross & (!hasMine);
    }
}
