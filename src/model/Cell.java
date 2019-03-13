package model;

/**
 * Cell Object is the container of the Pieces
 */
public class Cell {
    public int x, y;
    private int Owner;
    private int Shade; /* Color for shaded area showing possible moves*/
    private Piece Current;

    /**
     * constructor of Cell Object
     * @param x the horizontal coordinate value
     * @param y the vertical coordinate value
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        Owner = 2;
        Shade = 0;
        Current = null;
    }

    /**
     * Get the owner of the cell
     * @return the PlayerID or 2 (stands for no owner)
     */
    public int getOwner() {
        return Owner;
    }

    /**
     * Set the owner of the cell
     * @param owner
     */
    public void setOwner(int owner) {
        Owner = owner;
    }

    /**
     * Get Piece currently in the Cell
     * @return Piece Object in the Cell
     */
    public Piece getCurrent() {
        return Current;
    }


    /**
     * Put Piece in the Cell
     */
    public void setCurrent(Piece current) {
        if (current == null) {
            Current = null;
            Owner = 2;
            return;
        }
        current.x = this.x;
        current.y = this.y;
        Current = current;
        Owner = current.player.getPlayerID();
    }

    /**
     * Empty the Cell
     */
    public void removePiece() {
        Current = null;
        Owner = 2;
        Shade =0;
    }

    /**
     * Check if the piece in a cell can get to another cell (x, y)
     * Called only when there is a piece on current cell
     * @param grid the board of the cell
     * @param x int x of the target cell
     * @param y int y of the target cell
     * @return a boolean if that's a valid move
     */
    public boolean canGetTo(Board grid, int x, int y) {
        if (this.Owner == 2) {
            return false;
        }
        Boolean validMove = this.Current.checkMove(grid, this.x, this.y, x, y);
        return validMove;
    }



}
