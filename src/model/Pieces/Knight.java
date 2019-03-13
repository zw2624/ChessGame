package model.Pieces;

import model.Board;
import model.Piece;
import model.Player;

import java.awt.image.BufferedImage;

/**
 * Classic Knight Piece extending Pieces Object
 *
 * Rule:
 * moves to any of the closest squares that are not on the same rank, file, or diagonal. (L-shape)
 */
public class Knight extends Piece {

    public Knight(String name, Player player, int x, int y) {
        super(name, player, x, y);
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        boolean notSameLine = (fromX != toX) & (fromY != toY);
        boolean isThree = (Math.abs(fromX - toX) + Math.abs(fromY - toY)) == 3;
        return notSameLine & isThree;
    }
}
