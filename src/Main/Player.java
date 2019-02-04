package Main;

import java.util.ArrayList;

public class Player {
    public boolean first;
    private int playerID;
    public boolean isCheckmate;
    public boolean isStalemate;
    private ArrayList<Piece> livePieces;

    public Player(int ID, boolean isFirst) {
        this.first = isFirst;
        this.playerID = ID;
        this.isCheckmate = false;
        this.isStalemate = false;
        this.livePieces = new ArrayList<Piece>();
    }


    public int getPlayerID() {
        return playerID;
    }

    public void addPiece(Piece e) {
        livePieces.add(e);
    }

    public ArrayList<Piece> getPieces() {
        return livePieces;
    }

}
