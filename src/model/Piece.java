package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Piece {
    public String Name;
    public Player player;
    public int x;
    public int y;
    private BufferedImage img;

    public Piece(String name, Player player,int x, int y) {
        Name = name;
        this.player = player;
        this.x = x;
        this.y = y;
        String path = "imgs/" + name + ".png";
        try {
            img = ImageIO.read(getClass().getResource(path));
        } catch (IllegalArgumentException | IOException e) {
            System.out.println("exception");
            img = null;
        }
        player.addPiece(this);
    }

    /**
     * Set the image
     * @param i the buffered image
     */
    public void setImg(BufferedImage i) {
        this.img = i;
    }

    /**
     * Get image of the Piece
     * @return the buffered image
     */
    public BufferedImage getImg() {
        return img;
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
