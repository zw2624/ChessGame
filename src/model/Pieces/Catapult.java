package model.Pieces;

import model.Board;
import model.Piece;
import model.Player;

import java.awt.image.BufferedImage;

public class Catapult extends Piece {

    public Catapult(String name, Player player, int x, int y) {
        super(name, player, x, y);
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        boolean moved = (fromX != toX) || (fromY != toY);
        boolean isLine = (fromX == toX) || (fromY == toY);
        boolean hasEnemy = grid.getCell(toX, toY).getOwner() != grid.getCell(fromX, fromY).getOwner() & grid.getCell(toX, toY).getOwner() != 2;
        boolean hasPieceBetween = grid.hasOneBetween(fromX, fromY, toX, toY);
        return moved & hasPieceBetween & isLine & hasEnemy;
    }
}
