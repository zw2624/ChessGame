package Main;

import java.awt.image.BufferedImage;

public abstract class Piece {
    String Name;
    Player player;
    private BufferedImage img;

    public Piece(String name, Player player, BufferedImage img) {
        Name = name;
        this.player = player;
        this.img = img;
        player.addPiece(this);
    }

    public abstract boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY);

    /*
    abstract  canMoveTo(Board grid, int fromX, int fromY);
    */
}
