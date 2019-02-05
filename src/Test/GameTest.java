package Test;

import Main.Game;
import Main.Piece;
import Main.Pieces.King;
import Main.Pieces.Knight;
import Main.Pieces.Pawn;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testConstructor() {}

    @Test
    void testGetOpponent() {}

    @Test
    void testTryMove() {}

    @Test
    void testInCheck() {}

    @Test
    void testNotInCheck() {}

    @Test
    void testCheckmate() {}

    @Test
    void testNotCheckmate() {}

    @Test
    void testStalemate() {}

    @Test
    void testNotStalemate() {}


    /*
    Classic Checkmate Example
    Stamma's mate
    White moves to win
    1. Nb4+ Ka1
    2. Kc1 a2
    3. Nc2#
     */
    @Test
    void CheckmateClassic() {
        Game g = new Game();
        Piece blackKing = new King("Black King", g.black, 0, 1, null);
        Piece whiteKing = new King("White King", g.white, 2, 1, null);
        Piece blackPawn = new Pawn("Black Pawn", g.black, 0, 2, null);
        Piece whiteKnight = new Knight("White Knight", g.white, 3, 2, null);
        g.myBoard.getCell(0,1).setCurrent(blackKing);
        g.myBoard.getCell(2,1).setCurrent(whiteKing);
        g.myBoard.getCell(0,2).setCurrent(blackPawn);
        g.myBoard.getCell(3,2).setCurrent(whiteKnight);
        ((Pawn) blackPawn).setFirst(false);
        g.myBoard.Kings[0] = whiteKing;
        g.myBoard.Kings[1] = blackKing;

        System.out.println("White:");
        g.myBoard.movePiece(g.white, 3,2,1,3);
        ArrayList<Piece> ps = g.inCheck(g.black);
        assertNotEquals(0, ps.size());
        assertNotEquals(true, g.isCheckmate(g.black, ps));

        System.out.println("Black:");
        g.myBoard.movePiece(g.black, 0,1,0,0);

        System.out.println("White:");
        g.myBoard.movePiece(g.white, 2,1,2,0);

        ps = g.inCheck(g.black);
        assertEquals(0, ps.size());
        assertNotEquals(true, g.isCheckmate(g.black, ps));

        System.out.println("Black:");
        g.myBoard.movePiece(g.black, 0,2,0,1);

        System.out.println("White:");
        g.myBoard.movePiece(g.white,1,3,2,1);

        ps = g.inCheck(g.black);
        assertNotEquals(0, ps.size());
        assertEquals(true, g.isCheckmate(g.black, ps));
    }

}