package Test;

import Main.Cell;
import Main.Piece;
import Main.Pieces.Queen;
import Main.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {


    /**
     * Test when we put a piece into the cell, if the information updated correctly
     */
    @Test
    void testAboutPiece() {
        Cell c = new Cell(1,1);
        assertTrue(c.getOwner() == 2);
        assertTrue(c.x == 1);
        assertTrue(c.y == 1);
        assertTrue(c.getCurrent()==null);

        Player p = new Player(0, true);
        Piece whiteQueen = new Queen("Queen", p, 0,0,null);
        c.setCurrent(whiteQueen);
        assertTrue(c.getOwner() == whiteQueen.player.getPlayerID());
        assertTrue(c.getCurrent()==whiteQueen);
        assertTrue(whiteQueen.x == c.x);
        assertTrue(whiteQueen.y == c.y);

        c.removePiece();
        assertTrue(c.getOwner() != whiteQueen.player.getPlayerID());
        assertTrue(c.getCurrent()==null);

    }
}