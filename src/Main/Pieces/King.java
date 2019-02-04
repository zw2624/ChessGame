package Main.Pieces;

import Main.Board;
import Main.Piece;
import Main.Player;

import java.awt.image.BufferedImage;

public class King extends Piece {
    public King(String name, Player player, BufferedImage img) {
        super(name, player, img);
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
