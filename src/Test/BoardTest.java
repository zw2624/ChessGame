package Test;

import Main.Game;
import Main.Piece;
import Main.Pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {



    @Test
    void testSetup() {
        Game g = new Game();
        g.myBoard.setup();
        assertTrue(g.myBoard.getCell(0,0).getCurrent().Name.equals("White Rook"));
        assertTrue(g.myBoard.getCell(1,0).getCurrent().Name.equals("White Knight"));
        assertTrue(g.myBoard.getCell(2,0).getCurrent().Name.equals("White Bishop"));
        assertTrue(g.myBoard.getCell(3,0).getCurrent().Name.equals( "White King"));
        assertTrue(g.myBoard.getCell(4,0).getCurrent().Name.equals("White Queen"));
        assertTrue(g.myBoard.getCell(5,0).getCurrent().Name.equals("White Bishop"));
        assertTrue(g.myBoard.getCell(6,0).getCurrent().Name.equals( "White Knight"));
        assertTrue(g.myBoard.getCell(7,0).getCurrent().Name.equals("White Rook"));
        for (int i = 0; i < 8; i++) {
            assertTrue(g.myBoard.getCell(i,1).getCurrent().Name.equals("White Pawn"));
        }

        assertTrue(g.myBoard.getCell(0,7).getCurrent().Name.equals("Black Rook"));
        assertTrue(g.myBoard.getCell(1,7).getCurrent().Name.equals("Black Knight"));
        assertTrue(g.myBoard.getCell(2,7).getCurrent().Name.equals("Black Bishop"));
        assertTrue(g.myBoard.getCell(3,7).getCurrent().Name.equals("Black King"));
        assertTrue(g.myBoard.getCell(4,7).getCurrent().Name.equals("Black Queen"));
        assertTrue(g.myBoard.getCell(5,7).getCurrent().Name.equals("Black Bishop"));
        assertTrue(g.myBoard.getCell(6,7).getCurrent().Name.equals("Black Knight"));
        assertTrue(g.myBoard.getCell(7,7).getCurrent().Name.equals("Black Rook"));
        for (int i = 0; i < 8; i++) {
            assertTrue(g.myBoard.getCell(i,6).getCurrent().Name.equals("Black Pawn"));
        }

    }

    @Test
    void testGetCell() {
        Game g = new Game();

    }

    @Test
    void testFindPiece() {
    }

    @Test
    void testValidMove() {
    }


    @Test
    void testInvalidMove() {
    }
}