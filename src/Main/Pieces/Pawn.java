package Main.Pieces;

import Main.Board;
import Main.Piece;
import Main.Player;

import java.awt.image.BufferedImage;

public class Pawn extends Piece {
    private boolean isFirst;

    public Pawn(String name, Player player, int x, int y, BufferedImage img) {
        super(name, player, x, y, img);
        this.isFirst = true;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        boolean moved = (fromX != toX) || (fromY != toY);
        boolean isBlack = this.player.getPlayerID() == 1;
        boolean hasEnemy = grid.getCell(toX, toY).getOwner() != grid.getCell(fromX, fromY).getOwner() & grid.getCell(toX, toY).getOwner() != 2;
        boolean isEmpty = grid.getCell(toX, toY).getOwner() == 2;
        boolean canEat;
        boolean isNext;
        if (isFirst) {
            if (!isBlack) {
                isNext = isEmpty & (fromX==toX) & ((toY == fromY + 1) || (toY == fromY + 2));
                canEat = (Math.abs(fromX - toX) == 1) & (toY == fromY + 1) & hasEnemy;
            } else {
                isNext = isEmpty & (fromX==toX) & ((toY == fromY - 1) || (toY == fromY - 2));
                canEat = (Math.abs(fromX - toX) == 1) & (toY == fromY - 1) & hasEnemy;
            }

        } else {
            if (!isBlack) {
                isNext = isEmpty & (fromX==toX) & (toY == fromY + 1);
                canEat = (Math.abs(fromX - toX) == 1) & (toY == fromY + 1) & hasEnemy;
            } else  {
                isNext = isEmpty & (fromX==toX) & (toY == fromY - 1);
                canEat = (Math.abs(fromX - toX) == 1) & (toY == fromY - 1) & hasEnemy;
            }
        }
        return moved & (isNext || canEat);
    }
}
