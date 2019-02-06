package Test;

import Main.Board;
import Main.Game;
import Main.Piece;
import Main.Pieces.*;
import Main.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testSetup() {
        Game g = new Game();
        Board myBoard = new Board(g);
        myBoard.setup();
        assertTrue(myBoard.getCell(0,0).getCurrent().Name.equals("White Rook"));
        assertTrue(myBoard.getCell(1,0).getCurrent().Name.equals("White Knight"));
        assertTrue(myBoard.getCell(2,0).getCurrent().Name.equals("White Bishop"));
        assertTrue(myBoard.getCell(3,0).getCurrent().Name.equals( "White King"));
        assertTrue(myBoard.getCell(4,0).getCurrent().Name.equals("White Queen"));
        assertTrue(myBoard.getCell(5,0).getCurrent().Name.equals("White Bishop"));
        assertTrue(myBoard.getCell(6,0).getCurrent().Name.equals( "White Knight"));
        assertTrue(myBoard.getCell(7,0).getCurrent().Name.equals("White Rook"));
        for (int i = 0; i < 8; i++) {
            assertTrue(myBoard.getCell(i,1).getCurrent().Name.equals("White Pawn"));
        }

        assertTrue(myBoard.getCell(0,7).getCurrent().Name.equals("Black Rook"));
        assertTrue(myBoard.getCell(1,7).getCurrent().Name.equals("Black Knight"));
        assertTrue(myBoard.getCell(2,7).getCurrent().Name.equals("Black Bishop"));
        assertTrue(myBoard.getCell(3,7).getCurrent().Name.equals("Black King"));
        assertTrue(myBoard.getCell(4,7).getCurrent().Name.equals("Black Queen"));
        assertTrue(myBoard.getCell(5,7).getCurrent().Name.equals("Black Bishop"));
        assertTrue(myBoard.getCell(6,7).getCurrent().Name.equals("Black Knight"));
        assertTrue(myBoard.getCell(7,7).getCurrent().Name.equals("Black Rook"));
        for (int i = 0; i < 8; i++) {
            assertTrue(myBoard.getCell(i,6).getCurrent().Name.equals("Black Pawn"));
        }

    }

    @Test
    void testMoveNothing() {
        System.out.println("Test: selected place is empty");
        Game g = new Game();
        Board board = g.myBoard;
        Boolean success = board.movePiece(g.white, 3, 1,2,3 );
        assertFalse(success);
        assertFalse(board.getCell(3,1).getOwner() == g.white.getPlayerID());
        assertFalse(board.getCell(2,3).getOwner() == g.white.getPlayerID());
    }


    @Test
    void testMoveOthers() {
        System.out.println("Test: move other's piece");
        Game g = new Game();
        Board board = g.myBoard;
        Piece blackPawn = new Pawn("Black Pawn", g.black, 2, 3, null);
        board.getCell(2,3).setCurrent(blackPawn);
        Boolean success = board.movePiece(g.white, 2, 3,2,2 );
        assertFalse(success);
        assertFalse(board.getCell(3,1).getOwner() == g.white.getPlayerID());
        assertFalse(board.getCell(2,3).getOwner() == g.white.getPlayerID());

    }

    @Test
    void testMoveToEmpty() {
        System.out.println("Test: just move to an empty spot");
        Game g = new Game();
        Board board = g.myBoard;
        Piece whiteKnight = new Knight("White Knight", g.white, 3, 1, null);
        board.getCell(3,1).setCurrent(whiteKnight);

        Boolean success = board.movePiece(g.white, 3, 1,2,3 );
        assertTrue(success);
        assertTrue(board.getCell(2,3).getCurrent().Name.equals("White Knight"));
        assertTrue(board.getCell(2,3).getOwner() == g.white.getPlayerID());
        assertTrue(board.getCell(2,3).getOwner() == whiteKnight.player.getPlayerID());
        assertTrue(board.getCell(3,1).getCurrent() == null);
        assertTrue(board.getCell(3,1).getOwner() == 2);
    }

    @Test
    void testMoveOutside() {
        System.out.println("Test: position not in board");
        Game g = new Game();
        Board board = g.myBoard;
        Piece whiteKnight = new Knight("White Knight", g.white, 3, 1, null);
        board.getCell(3,1).setCurrent(whiteKnight);

        Boolean success = board.movePiece(g.white, 3, 1,4,-1 );
        assertFalse(success);
        assertTrue(board.getCell(3,1).getCurrent().Name.equals("White Knight"));
        assertTrue(board.getCell(3,1).getOwner() == whiteKnight.player.getPlayerID());
        assertTrue(board.getCell(3,1).getOwner() == g.white.getPlayerID());
    }

    @Test
    void testMoveInvalid() {
        System.out.println("Test: not a valid move");
        Game g = new Game();
        Board board = g.myBoard;
        Piece whiteKnight = new Knight("White Knight", g.white, 3, 1, null);
        board.getCell(3,1).setCurrent(whiteKnight);

        Boolean success = board.movePiece(g.white, 3, 1,2,4 );
        assertFalse(success);
        assertTrue(board.getCell(3,1).getCurrent().Name.equals("White Knight"));
        assertTrue(board.getCell(3,1).getOwner() == g.white.getPlayerID());
    }

    @Test
    void testMoveOccupied() {
        System.out.println("Test: position already has mine");
        Game g = new Game();
        Board board = g.myBoard;
        Piece whiteKnight = new Knight("White Knight", g.white, 3, 1, null);
        Piece whitePawn = new Pawn("White Pawn", g.white, 2, 3, null);
        board.getCell(3,1).setCurrent(whiteKnight);
        board.getCell(2,3).setCurrent(whitePawn);

        Boolean success = board.movePiece(g.white, 3, 1,2,3 );
        assertFalse(success);
        assertTrue(board.getCell(3,1).getCurrent().Name.equals("White Knight"));
        assertTrue(board.getCell(2,3).getCurrent().Name.equals("White Pawn"));
        assertTrue(board.getCell(2,3).getOwner() == g.white.getPlayerID());
        assertTrue(board.getCell(3,1).getOwner() == g.white.getPlayerID());
    }



    @Test
    void testMoveToKill() {
        System.out.println("Test: position has enemy");
        Game g = new Game();
        Board board = g.myBoard;
        Piece whiteKnight = new Knight("White Knight", g.white, 3, 1, null);
        Piece blackPawn = new Pawn("Black Pawn", g.black, 2, 3, null);
        board.getCell(3,1).setCurrent(whiteKnight);
        board.getCell(2,3).setCurrent(blackPawn);

        Boolean success = board.movePiece(g.white, 3, 1,2,3 );
        assertTrue(success);
        assertTrue(board.getCell(2,3).getCurrent().Name.equals("White Knight"));
        assertTrue(board.getCell(2,3).getOwner() == g.white.getPlayerID());
        assertTrue(board.getCell(2,3).getOwner() == whiteKnight.player.getPlayerID());
        assertTrue(board.getCell(3,1).getCurrent() == null);
        assertTrue(board.getCell(3,1).getOwner() == 2);
        assertFalse(g.black.getPieces().contains(blackPawn));
    }


}