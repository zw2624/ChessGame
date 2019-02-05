package Test;

import Main.Game;
import Main.Piece;
import Main.Pieces.King;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    /* Piece Test does not test:
        1. the spot is not empty
        2
    */

    @Test
    void testKing() {
        Game g = new Game();
        Piece blackKing = new King("Black King", g.black, 3, 5, null);

        // legal moves
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  3, 4));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  3, 2));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  2, 2));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  2, 3));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  2, 4));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  4, 2));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  4, 3));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  4, 4));

        // illegal moves: has

    }

    @Test
    void testKnight() {

    }

    @Test
    void testQueen() {

    }

    @Test
    void testPawn() {

    }

    @Test
    void testRook() {

    }

    @Test
    void testBishop() {

    }

}