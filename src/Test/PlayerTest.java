package Test;

import model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


    /**
     * Test if only one player will make first move
     */
    @Test
    void onlyOneFirst(){
        Game g = new Game();
        assertNotEquals(g.white.first, g.black.first);
    }

    @Test
    void WinLoseDraw(){
        Game g = new Game();
        assertTrue(g.white.getWinNum() == 0);
        assertTrue(g.white.getDrawNum() == 0);
        assertTrue(g.white.getLoseNum() == 0);

        g.white.addDrawNum();
        assertTrue(g.white.getDrawNum() == 1);
        assertTrue(g.black.getDrawNum() == 0);

        g.white.addWinNum();
        assertTrue(g.white.getWinNum() == 1);
        assertTrue(g.black.getLoseNum() == 0);

        g.white.addLoseNum();
        assertTrue(g.white.getWinNum() == 1);
        assertTrue(g.white.getLoseNum() == 1);

    }

    @Test
    void testRemoveAll() {
        Game g = new Game();
        g.myBoard.setup(false);
        g.white.removeAllPiece();
        assertTrue(g.white.getPieces().size() == 0);
    }

}