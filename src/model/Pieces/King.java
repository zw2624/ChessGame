package model.Pieces;

import model.Board;
import model.Piece;
import model.Player;

import java.awt.image.BufferedImage;

public class King extends Piece {

    public King(String name, Player player, int x, int y) {
        super(name, player, x, y);
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        boolean moved = (fromX != toX) || (fromY != toY);
        boolean oneStep = (Math.abs(fromX - toX) < 2) & (Math.abs(fromY-toY) < 2);
        Player me = this.player;
        int op = this.player.getPlayerID() == 0 ? 1 : 0;
        int[] other = {grid.Kings[op].x, grid.Kings[op].y};
        boolean hasOtherKing = (Math.abs(other[0] - toX) < 2) & (Math.abs(other[1]-toY) < 2);
        return moved & oneStep & (!hasOtherKing);
    }
}
