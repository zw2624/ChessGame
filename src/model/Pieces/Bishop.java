package model.Pieces;

import model.Board;
import model.Piece;
import model.Player;

import java.awt.image.BufferedImage;

/**
 * Classic Bishop Piece extending Pieces Object
 *
 * Rule:
 * can move any number of squares diagonally, but cannot leap over other pieces.
 */
public class Bishop extends Piece {

    public Bishop(String name, Player player, int x, int y) {
        super(name, player, x, y);
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        boolean moved = (fromX != toX) & (fromY != toY);
        boolean isCross = Math.abs(fromX - toX) == Math.abs(fromY - toY);
        return  moved & isCross;
    }
}
