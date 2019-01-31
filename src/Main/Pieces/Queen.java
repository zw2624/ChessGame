package Main.Pieces;

import Main.Board;
import Main.Piece;

public class Queen extends Piece {

    public Queen() {
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        boolean isCross = Math.abs(fromX - toX) == Math.abs(fromY - toY);
        boolean hasMine = grid.getCell(toX, toY).getOwner() == grid.getCell(fromX, fromY).getOwner();
        return  isCross & (!hasMine);
    }
}
