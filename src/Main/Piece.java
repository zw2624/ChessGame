package Main;

import java.awt.image.BufferedImage;

public abstract class Piece {
    public String Name;
    public Player player;
    public int x;
    public int y;
    private BufferedImage img;

    public Piece(String name, Player player,int x, int y, BufferedImage img) {
        Name = name;
        this.player = player;
        this.x = x;
        this.y = y;
        this.img = img;
        player.addPiece(this);
    }

    public abstract boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY);

    /*
    abstract  canMoveTo(Board grid, int fromX, int fromY);
    */
}
