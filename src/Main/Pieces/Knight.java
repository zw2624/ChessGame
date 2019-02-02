package Main.Pieces;

import Main.Board;
import Main.Piece;

import java.awt.image.BufferedImage;

public class Knight extends Piece {

    public Knight(String name, int player, BufferedImage img) {
        super(name, player, img);
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        boolean notSameLine = (fromX != toX) & (fromY != toY);
        boolean hasMine = grid.getCell(toX, toY).getOwner() == grid.getCell(fromX, fromY).getOwner();
        boolean isThree = (Math.abs(fromX - toX) + Math.abs(fromY - toY)) == 3;
        return notSameLine & (!hasMine) & isThree;
    }
}
