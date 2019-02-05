package Main.Pieces;

import Main.Board;
import Main.Piece;
import Main.Player;

import java.awt.image.BufferedImage;

public class King extends Piece {

    public King(String name, Player player, int x, int y, BufferedImage img) {
        super(name, player, x, y, img);
    }

    @Override
    public boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY) {
        //System.out.println("Checking King");
        boolean oneStep = (Math.abs(fromX - toX) < 2) & (Math.abs(fromY-toY) < 2);
        Player me = this.player;
        int[] other = {grid.Kings[me.getPlayerID()].x, grid.Kings[me.getPlayerID()].y};
        boolean noOtherKing = (Math.abs(other[0] - toX) < 2) & (Math.abs(other[1]-toY) < 2);
        return oneStep & noOtherKing;
    }
}
