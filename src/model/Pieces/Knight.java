package model.Pieces;

import model.Board;
import model.Piece;
import model.Player;

import java.awt.image.BufferedImage;

public class Knight extends Piece {

    public Knight(String name, Player player, int x, int y, BufferedImage img) {
        super(name, player, x, y, img);
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        boolean notSameLine = (fromX != toX) & (fromY != toY);
        boolean isThree = (Math.abs(fromX - toX) + Math.abs(fromY - toY)) == 3;
        return notSameLine & isThree;
    }
}
