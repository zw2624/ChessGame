package model;

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

    /**
     * Checking if this is a valid move for the Piece
     * @param grid the board the Piece is in
     * @param fromX int x of starting position
     * @param fromY int y of starting position
     * @param toX int x of target position
     * @param toY int y of target position
     * @return a boolean if it is a valid move
     */
    public abstract boolean checkMove(Board grid, int fromX, int fromY, int toX, int toY);

    /*
    abstract  canMoveTo(Board grid, int fromX, int fromY);
    */
}
