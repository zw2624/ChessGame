package Test;

import model.Board;
import model.Game;
import model.Piece;
import model.Pieces.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    /**
     * Test constructor. checking if it initialized two players
     */
    @Test
    void testConstructor() {
        Game g = new Game();
        assertNotEquals(g.white.getPlayerID(), g.black.getPlayerID());
        assertEquals(g.white.getPieces().size(), 0);
        assertEquals(g.black.getPieces().size(), 0);
    }

    /**
     * Test if we can get opponent player
     */
    @Test
    void testGetOpponent() {
        Game g = new Game();
        assertEquals(g.black, g.getOpponent(g.white));
        assertEquals(g.white, g.getOpponent(g.black));
    }

    @Test
    void testGetPlayer() {
        Game g = new Game();
        assertEquals(g.black, g.getPlayer(1));
        assertEquals(g.white, g.getPlayer(0));
    }


    @Test
    void testRestart() {
        Game g = new Game();
        g.myBoard.setup(false);
        g.myBoard.movePiece(g.white, 0,1,0,2);
        g.restart(false);
        assertTrue(g.myBoard.getCell(0,2).getOwner() == 2); // is empty
    }

    @Test
    void testUndo() {
        Game g = new Game();
        g.myBoard.setup(false);
        g.myBoard.movePiece(g.white, 0,1,0,2);
        g.undo();
        assertTrue(g.myBoard.getCell(0,2).getOwner() == 2); // is empty
    }

    /**
     * Test about inCheck. Check if still incheck after we actually solved the threat.
     */
    @Test
    void testInCheck() {
        Game g = new Game();
        Board board = g.myBoard;
        Piece whiteKnight = new Knight("White Knight", g.white, 3, 2);
        Piece whiteKing = new King("White King", g.white, 2,2);
        Piece blackKing = new King("White Knight", g.black, 0, 0);
        Piece blackPawn = new Pawn("Black Pawn", g.black, 1, 3);
        board.getCell(0,0).setCurrent(blackKing);
        board.getCell(2,2).setCurrent(whiteKing);
        board.getCell(3,2).setCurrent(whiteKnight);
        board.getCell(1,3).setCurrent(blackPawn);
        board.Kings[0] = whiteKing;
        board.Kings[1] = blackKing;

        boolean inCheck = g.inCheck(g.white).size() != 0;
        assertTrue(inCheck);

        board.movePiece(g.white, 3,2,1,3);

        inCheck = g.inCheck(g.white).size() != 0;
        assertFalse(inCheck);
    }


    /**
     * Test if tryMove function undo the move after checking.
     */
    @Test
    void testTryMove() {
        Game g = new Game();
        Board board = g.myBoard;
        Piece whiteKnight = new Knight("White Knight", g.white, 3, 1);
        Piece whiteKing = new King("White King", g.white, 2,2);
        Piece blackKing = new King("White Knight", g.black, 0, 0);
        Piece blackPawn = new Pawn("Black Pawn", g.black, 2, 3);
        board.getCell(0,0).setCurrent(blackKing);
        board.getCell(2,2).setCurrent(whiteKing);
        board.getCell(3,1).setCurrent(whiteKnight);
        board.getCell(2,3).setCurrent(blackPawn);
        board.Kings[0] = whiteKing;
        board.Kings[1] = blackKing;

        boolean success = g.tryMove(g.white, whiteKnight, 2, 3);
        assertTrue(success);
        assertTrue(board.getCell(3,1).getCurrent() == whiteKnight);
        assertTrue(board.getCell(2,3).getCurrent() == blackPawn);
        assertTrue(g.white.getPieces().contains(whiteKnight));
        assertTrue(g.black.getPieces().contains(blackPawn));

    }

    @Test
    void testTrymoveInvalid() {
        Game g = new Game();
        Board board = g.myBoard;
        Piece whiteKnight = new Knight("White Knight", g.white, 3, 1);
        Piece whiteKing = new King("White King", g.white, 2,2);
        Piece blackKing = new King("White Knight", g.black, 0, 0);
        Piece blackPawn = new Pawn("Black Pawn", g.black, 1, 3);
        board.getCell(0,0).setCurrent(blackKing);
        board.getCell(2,2).setCurrent(whiteKing);
        board.getCell(3,1).setCurrent(whiteKnight);
        board.getCell(1,3).setCurrent(blackPawn);
        board.Kings[0] = whiteKing;
        board.Kings[1] = blackKing;

        boolean trial1 = g.tryMove(g.white, whiteKnight, 5, 3);
        assertFalse(trial1);
        boolean trial2 = g.tryMove(g.white, whiteKnight, 5, 0);
        assertFalse(trial2);
    }

    /**
     * Test Checkmate
     */
    @Test
    void testCheckmate() {
        Game g = new Game();
        Board board = g.myBoard;
        Piece whiteRook = new Rook("White Rook", g.white, 7, 7);
        Piece whiteKing = new King("White King", g.white, 3,5);
        Piece blackKing = new King("White Knight", g.black, 3, 7);
        board.getCell(3,7).setCurrent(blackKing);
        board.getCell(3,5).setCurrent(whiteKing);
        board.getCell(7,7).setCurrent(whiteRook);
        board.Kings[0] = whiteKing;
        board.Kings[1] = blackKing;

        ArrayList<Piece> threats = g.inCheck(g.black);
        boolean Checkmate = g.isCheckmate(g.black, threats);
        assertTrue(Checkmate);
    }



    /**
     * Test when actually there is not checkmate
     */
    @Test
    void testNotCheckmate() {
        Game g = new Game();
        Board board = g.myBoard;
        Piece whiteKnight = new Knight("White Knight", g.white, 3, 2);
        Piece whiteKing = new King("White King", g.white, 2,2);
        Piece blackKing = new King("White Knight", g.black, 0, 0);
        Piece blackPawn = new Pawn("Black Pawn", g.black, 1, 3);
        board.getCell(0,0).setCurrent(blackKing);
        board.getCell(2,2).setCurrent(whiteKing);
        board.getCell(3,1).setCurrent(whiteKnight);
        board.getCell(2,3).setCurrent(blackPawn);
        board.Kings[0] = whiteKing;
        board.Kings[1] = blackKing;

        ArrayList<Piece> threats = g.inCheck(g.white);
        boolean Checkmate = g.isCheckmate(g.white, threats);
        assertFalse(Checkmate);
    }


    /**
     * Test Stalemate
     */
    @Test
    void testStalemate() {
        Game g = new Game();
        Board board = g.myBoard;
        Piece whiteQueen = new Queen("White Queen", g.white, 1, 5);
        Piece whiteKing = new King("White King", g.white, 2,6);
        Piece blackKing = new King("Black King", g.black, 0, 7);
        board.getCell(0,7).setCurrent(blackKing);
        board.getCell(2,6).setCurrent(whiteKing);
        board.getCell(1,5).setCurrent(whiteQueen);
        board.Kings[0] = whiteKing;
        board.Kings[1] = blackKing;

        assertTrue(whiteQueen.checkMove(g.myBoard, 1,5,0,6));
        /**assertFalse(blackKing.checkMove(g.myBoard,0,7,0,6));*/
        boolean stalemate = g.isStalemate(g.black);
        assertTrue(stalemate);
    }

    @Test
    void testNoteStalemate() {
        Game g = new Game();
        Board board = g.myBoard;
        Piece whiteKing = new King("White King", g.white, 3,3);
        Piece blackKing = new King("Black King", g.black, 0, 7);
        board.getCell(0,7).setCurrent(blackKing);
        board.getCell(3,3).setCurrent(whiteKing);
        board.Kings[0] = whiteKing;
        board.Kings[1] = blackKing;

        boolean stalemate = g.isStalemate(g.black);
        assertFalse(stalemate);
    }

    /**
     *  Test: if the 'move', 'check' work correctly together.
     *  Also checked if it is right player's turn to move.
     *  This is an example from Wiki:
     *     Stamma's mate
     *     White moves to win
     *     1. Nb4+ Ka1
     *     2. Kc1 a2
     *     3. Nc2#
     */
    @Test
    void GameSimulation() {
        Game g = new Game();
        Piece blackKing = new King("Black King", g.black, 0, 1);
        Piece whiteKing = new King("White King", g.white, 2, 1);
        Piece blackPawn = new Pawn("Black Pawn", g.black, 0, 2);
        Piece whiteKnight = new Knight("White Knight", g.white, 3, 2);
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

        System.out.println("White:");
        int canMakeMoveAgain = g.myBoard.movePiece(g.white, 3,2,1,3);
        assertFalse(canMakeMoveAgain == 1);

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
        System.out.println(ps.get(0).Name);
        assertEquals(true, g.isCheckmate(g.black, ps));
    }

}