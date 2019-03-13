package model.Pieces;

import model.Board;
import model.Piece;
import model.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Classic Rook Piece extending Pieces Object
 *
 * Rule:
 * A rook can move any number of squares along a rank or file, but cannot leap over other pieces.
 * Along with the king, a rook is involved during the king's castling move.
 */
public class Rook extends Piece {


    public Rook(String name, Player player, int x, int y) {
        super(name, player, x, y);
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        boolean moved = (fromX != toX) || (fromY != toY);
        boolean isLine = (fromX == toX) || (fromY == toY);
        return  moved & isLine;
    }
}
