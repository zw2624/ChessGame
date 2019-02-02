package Main.Pieces;

import Main.Board;
import Main.Piece;

import java.awt.image.BufferedImage;

public class Pawn extends Piece {
    private boolean isFirst;

    public Pawn(String name, int player, BufferedImage img) {
        super(name, player, img);
        this.isFirst = true;
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        boolean moved = (fromX != toX) || (fromY != toY);
        boolean hasEnemy = grid.getCell(toX, toY).getOwner() != grid.getCell(fromX, fromY).getOwner();
        boolean isEmpty = grid.getCell(toX, toY).getOwner() == 0;
        boolean canEat = (Math.abs(fromX - toX) == 1) & (toY == fromY + 1) & hasEnemy;
        boolean isNext;
        if (isFirst) {
            isNext = isEmpty & (fromX==toX) & ((toY == fromY + 1) || (toY == fromY + 2));

        } else {
            isNext = isEmpty & (fromX==toX) & (toY == fromY + 1);
        }
        return moved & (isNext || canEat);
    }
}
