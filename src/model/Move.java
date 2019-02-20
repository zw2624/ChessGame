package model;

public class Move {
    private Player player;
    private Cell from;
    private Cell to;
    private Piece myPiece;
    private Piece Eaten;

    /**
     * Constructor
     */
    public Move(Player player, Cell from, Cell to, Piece myPiece, Piece eaten) {
        this.player = player;
        this.from = from;
        this.to = to;
        this.myPiece = myPiece;
        Eaten = eaten;
    }

    /**
     * Generate Log Info
     */
    public String convertToLog() {
        if (from == null) {
            return "Player " + this.player.nickName + " undid the move";
        }
        char fromHori = (char) ('A' + from.x);
        char toHori = (char) ('A' + to.x);
        char fromVeri = (char) ('1' + from.y);
        char toVeri = (char) ('1' + to.y);
        String msg = "Player " + this.player.nickName + " moved " + this.myPiece.Name + " from " + fromHori + fromVeri + " to " + toHori + toVeri;
        if (this.Eaten != null) {
            msg += ", and ate " + Eaten.Name;
        }
        return msg;
    }

    /**
     * Getters
     */

    public Player getPlayer() {
        return player;
    }

    public Cell getFrom() {
        return from;
    }

    public Cell getTo() {
        return to;
    }

    public Piece getMyPiece() {
        return myPiece;
    }

    public Piece getEaten() {
        return Eaten;
    }
}
