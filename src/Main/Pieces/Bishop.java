package Main.Pieces;

import Main.Board;
import Main.Piece;
import Main.Player;

import java.awt.image.BufferedImage;

public class Bishop extends Piece {

    public Bishop(String name, Player player, int x, int y, BufferedImage img) {
        super(name, player, x, y, img);
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        boolean moved = (fromX != toX) & (fromY != toY);
        boolean isCross = Math.abs(fromX - toX) == Math.abs(fromY - toY);
        boolean hasMine = grid.getCell(toX, toY).getOwner() == grid.getCell(fromX, fromY).getOwner();
        return  moved & isCross & (!hasMine);
    }
}
