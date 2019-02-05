package Main.Pieces;

import Main.Board;
import Main.Piece;
import Main.Player;

import java.awt.image.BufferedImage;

public class Rook extends Piece {

    public Rook(String name, Player player, int x, int y, BufferedImage img) {
        super(name, player, x, y, img);
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        boolean moved = (fromX != toX) || (fromY != toY);
        boolean isLine = (fromX == toX) || (fromY == toY);
        return  moved & isLine;
    }
}
