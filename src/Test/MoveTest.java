package Test;
import model.Game;
import model.Move;
import model.Piece;
import model.Pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class MoveTest {

    @Test
    void testLog() {
        Game g = new Game();
        Piece whiteKing = new King("White King", g.white,3,0);
        g.myBoard.getCell(3,0).setCurrent(whiteKing);
        Piece blackKing = new King("Black King", g.black,3,7);
        g.myBoard.getCell(3,7).setCurrent(blackKing);
        g.myBoard.Kings = new Piece[]{whiteKing, blackKing};

        g.myBoard.movePiece(g.white, 3,0,4,0);
        Move last = g.myBoard.getLastMove();
        assertTrue(last.getPlayer() == g.white);
        assertTrue(last.getMyPiece() == whiteKing);
        assertTrue(last.getEaten() == null);
        assertTrue(last.getFrom() == g.myBoard.getCell(3,0));
        assertTrue(last.getTo() == g.myBoard.getCell(4,0));
        assertEquals(last.convertToLog(), "Player white moved White King from D1 to E1");
    }
}
