package model.Pieces;

import model.Board;
import model.Piece;
import model.Player;

import java.awt.image.BufferedImage;

/**
 * Classic Queen Piece extending Pieces Object
 *
 * Rule:
 * The queen can move any number of squares along a rank, file, or diagonal, but cannot leap over other pieces.
 */
public class Queen extends Piece {

    public Queen(String name, Player player, int x, int y) {
        super(name, player, x, y);
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        boolean moved = (fromX != toX) || (fromY != toY);
        boolean isCross = Math.abs(fromX - toX) == Math.abs(fromY - toY);
        boolean isLine = (fromX == toX) || (fromY == toY);
        return moved & (isCross || isLine);
    }
}
