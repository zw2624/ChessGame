package Test;

import Main.Game;
import Main.Piece;
import Main.Pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    /* Piece Test does not test:
        1. the spot is not empty
        2. out of boundary
       These test are included and tested in board logic.
    */

    @Test
    void testKing() {
        Game g = new Game();
        Piece blackKing = new King("Black King", g.black, 3, 3, null);
        Piece whiteKing = new King("White King", g.white, 5, 3, null);
        g.myBoard.Kings[0] = whiteKing;
        g.myBoard.Kings[1] = blackKing;
        g.myBoard.getCell(3,3).setCurrent(blackKing);
        g.myBoard.getCell(5,3).setCurrent(whiteKing);

        // legal moves
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  3, 4));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  3, 2));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  2, 2));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  2, 3));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  2, 4));

        // illegal moves: has another King around
        assertFalse(blackKing.checkMove(g.myBoard, 3, 3,  4, 2));
        assertFalse(blackKing.checkMove(g.myBoard, 3, 3,  4, 3));
        assertFalse(blackKing.checkMove(g.myBoard, 3, 3,  4, 4));
    }

    @Test
    void testKnight() {
        Game g = new Game();
        Piece blackKnight = new Knight("Black Knight", g.black, 3,3,null);
        g.myBoard.getCell(3,3).setCurrent(blackKnight);

        // legal moves
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  1, 2));
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  1, 4));
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  2, 1));
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  2, 5));
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  4, 1));
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  4, 5));
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  5, 2));
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  5, 4));

        // illegal moves
        assertFalse(blackKnight.checkMove(g.myBoard, 3, 3,  3, 4));
        assertFalse(blackKnight.checkMove(g.myBoard, 3, 3,  4, 4));
        assertFalse(blackKnight.checkMove(g.myBoard, 3, 3,  2, 3));
    }

    @Test
    void testPawn() {
        Game g = new Game();
        Piece whitePawn = new Pawn("White Pawn", g.white, 5,1, null);
        g.myBoard.getCell(5,1).setCurrent(whitePawn);

        // first moves
        assertTrue(whitePawn.checkMove(g.myBoard, 5, 1,  5, 2));
        assertTrue(whitePawn.checkMove(g.myBoard, 5, 1,  5, 3));


        // illegal moves (including "goes back")
        assertFalse(whitePawn.checkMove(g.myBoard, 5, 1,  5, 5));
        assertFalse(whitePawn.checkMove(g.myBoard, 5, 1,  4, 1));
        assertFalse(whitePawn.checkMove(g.myBoard, 5, 1,  4, 2));
        assertFalse(whitePawn.checkMove(g.myBoard, 5, 1,  5, 0));

        // illegal moves (can't move even has enemy ahead)
        Piece blackPawn0 = new Pawn("Black Pawn", g.black, 5,2, null);
        g.myBoard.getCell(5,2).setCurrent(blackPawn0);
        assertFalse(whitePawn.checkMove(g.myBoard, 5, 1,  5, 2));

        // Pawns en passant
        Piece blackPawn1 = new Pawn("Black Pawn", g.black, 4,2, null);
        Piece blackPawn2 = new Pawn("Black Pawn", g.black, 6,2, null);
        g.myBoard.getCell(4,2).setCurrent(blackPawn1);
        g.myBoard.getCell(6,2).setCurrent(blackPawn2);
        assertTrue(whitePawn.checkMove(g.myBoard, 5, 1,  4, 2));
        assertTrue(whitePawn.checkMove(g.myBoard, 5, 1,  6, 2));

        // Cannot go two cell after first move
        Piece whitePawn2 = new Pawn("White Pawn", g.white, 0,1, null);
        g.myBoard.getCell(0,1).setCurrent(whitePawn2);
        g.myBoard.movePiece(g.white, 0,1,0,2);
        assertTrue(whitePawn.checkMove(g.myBoard, 0, 1,  0, 3));
        assertFalse(whitePawn.checkMove(g.myBoard, 0, 1,  0, 4));

    }

    @Test
    void testRook() {
        Game g = new Game();
        Piece blackRook = new Rook("Black Rook", g.black, 3,3,null);
        g.myBoard.getCell(3,3).setCurrent(blackRook);

        // legal moves
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  0, 3));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  1, 3));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  2, 3));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  4, 3));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  5, 3));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  6, 3));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  7, 3));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  3, 0));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  3, 1));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  3, 2));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  3, 4));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  3, 5));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  3, 6));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  3, 7));

        // illegal moves
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  0, 0));
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  1, 1));
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  2, 2));
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  4, 4));

        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  1, 2));
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  1, 4));
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  2, 1));
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  2, 5));


    }

    @Test
    void testBishop() {
        Game g = new Game();
        Piece blackBishop = new Bishop("Black Bishop", g.black, 3,3,null);
        g.myBoard.getCell(3,3).setCurrent(blackBishop);


        // legal moves
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3,  0, 0));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3,  1, 1));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3,  2, 2));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3,  4, 4));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3,  5, 5));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3,  6, 6));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3,  7, 7));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3,  6, 0));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3,  5, 1));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3,  4, 2));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3,  2, 4));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3,  1, 5));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3,  0, 6));


        // illegal moves
        assertFalse(blackBishop.checkMove(g.myBoard, 3, 3,  0, 3));
        assertFalse(blackBishop.checkMove(g.myBoard, 3, 3,  1, 3));
        assertFalse(blackBishop.checkMove(g.myBoard, 3, 3,  2, 3));
        assertFalse(blackBishop.checkMove(g.myBoard, 3, 3,  4, 3));

        assertFalse(blackBishop.checkMove(g.myBoard, 3, 3,  4, 1));
        assertFalse(blackBishop.checkMove(g.myBoard, 3, 3,  4, 5));
        assertFalse(blackBishop.checkMove(g.myBoard, 3, 3,  5, 2));
        assertFalse(blackBishop.checkMove(g.myBoard, 3, 3,  5, 4));
    }

    @Test
    void testQueen() {
        Game g = new Game();
        Piece blackQueen = new Queen("Black Queen", g.black, 3,3,null);
        g.myBoard.getCell(3,3).setCurrent(blackQueen);


        // legal moves
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  0, 0));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  1, 1));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  2, 2));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  4, 4));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  5, 5));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  6, 6));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  7, 7));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  6, 0));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  5, 1));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  4, 2));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  2, 4));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  1, 5));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  0, 6));

        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  0, 3));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  1, 3));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  2, 3));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  4, 3));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  5, 3));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  6, 3));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  7, 3));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  3, 0));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  3, 1));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  3, 2));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  3, 4));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  3, 5));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  3, 6));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3,  3, 7));

        // illegal moves
        assertFalse(blackQueen.checkMove(g.myBoard, 3, 3,  1, 2));
        assertFalse(blackQueen.checkMove(g.myBoard, 3, 3,  1, 4));
        assertFalse(blackQueen.checkMove(g.myBoard, 3, 3,  2, 1));
        assertFalse(blackQueen.checkMove(g.myBoard, 3, 3,  2, 5));
        assertFalse(blackQueen.checkMove(g.myBoard, 3, 3,  4, 1));
        assertFalse(blackQueen.checkMove(g.myBoard, 3, 3,  4, 5));
        assertFalse(blackQueen.checkMove(g.myBoard, 3, 3,  5, 2));
        assertFalse(blackQueen.checkMove(g.myBoard, 3, 3,  5, 4));


    }

}