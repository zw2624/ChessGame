package model.Pieces;

import model.Board;
import model.Piece;
import model.Player;

import java.awt.image.BufferedImage;


/**
 * Classic Pawn Piece extending Pieces Object
 * Includes an Boolean isFirst to indicate if that is the first move of the object
 *
 * Rule:
 * 1. can move forward to the unoccupied square immediately in front of it on the same file
 * 2. on its first move it can advance two squares along the same file
 * 3. can capture an opponent's piece on a square diagonally in front of it on an adjacent file
 */
public class Pawn extends Piece {
    private boolean isFirst;

    public Pawn(String name, Player player, int x, int y) {
        super(name, player, x, y);
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
