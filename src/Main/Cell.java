package Main;

public class Cell {
    private int x, y;
    private int Owner;
    private int Shade; /* Color for shaded area showing possible moves*/
    private int isMoveable;
    private Piece Current;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        Owner = 0;
        Shade = 0;
        Current = null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOwner() {
        return Owner;
    }

    public void setOwner(int owner) {
        Owner = owner;
    }

    public Piece getCurrent() {
        return Current;
    }

    public void setCurrent(Piece current) {
        Current = current;
    }

    /*
    check if this cell can get to (x, y)
     */
    public boolean canGetTo(Board grid, int x, int y) {
        if (this.Owner == 0) {
            return false;
        } else {
            return this.Current.checkMove(grid, this.x, this.y, x, y);
        }
    }



}
