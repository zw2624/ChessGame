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

}