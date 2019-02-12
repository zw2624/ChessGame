package model;

import java.util.ArrayList;

/**
 * Player Object contains player information
 * including ID, who is first, and live Pieces
 */
public class Player {
    public boolean first;
    private int playerID;
    private ArrayList<Piece> livePieces;

    /**
     * Constructor
     * @param ID
     * @param isFirst
     */
    public Player(int ID, boolean isFirst) {
        this.first = isFirst;
        this.playerID = ID;
        this.livePieces = new ArrayList<Piece>();
    }

    /**
     * Get the ID of the player
     * @return 0 for white player, 1 for black player
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Remove a piece of a player
     * @param e the piece that is removed (usually it was eaten by opponent)
     */
    public void removePiece(Piece e) {
        livePieces.remove(e);
    }

    /**
     * Add a piece of a player. Called only when setting up the board.
     * @param e the piece that is added
     */
    public void addPiece(Piece e) {
        livePieces.add(e);
    }

    /**
     * Return live pieces of the player
     * @return an ArrayList containing all live pieces
     */
    public ArrayList<Piece> getPieces() {
        return livePieces;
    }

}
