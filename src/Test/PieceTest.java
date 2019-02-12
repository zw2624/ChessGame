package Test;

import model.Game;
import model.Piece;
import model.Pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    /**
     * Piece Test does not test:
        1. the spot is not empty
        2. out of boundary
       These test are included and tested in board logic.
    */

    @Test
    void testPieceBasic() {
        Game g = new Game();
        Piece whiteKnight = new Knight("white Knight", g.white, 3,3);
        assertNotNull(whiteKnight.getImg());
        whiteKnight.setImg(null);
        assertNull(whiteKnight.getImg());

        Piece notExist = new Knight("not exist", g.white, 3,3);
        assertNull(notExist.getImg());
    }

    @Test
    void testCatapultValid() {
        Game g = new Game();
        Piece whiteCata = new Catapult("White Catapult", g.white, 0, 1);
        Piece whitePawn = new Pawn("White Pawn", g.white, 0, 2);
        Piece whitePawn2 = new Pawn("White Pawn", g.white, 1, 1);
        Piece blackKnight = new Knight("Black Knight", g.black, 0, 5);
        Piece blackKnight2 = new Knight("Black Knight", g.black, 2, 1);
        g.myBoard.getCell(0,1).setCurrent(whiteCata);
        g.myBoard.getCell(0,2).setCurrent(whitePawn);
        g.myBoard.getCell(1,1).setCurrent(whitePawn2);
        g.myBoard.getCell(0,5).setCurrent(blackKnight);
        g.myBoard.getCell(2,1).setCurrent(blackKnight2);
        assertTrue(whiteCata.checkMove(g.myBoard, 0,1,0,5));
        assertTrue(whiteCata.checkMove(g.myBoard, 0,1,2,1));
    }

    @Test
    void testCatapultInvalid() {
        Game g = new Game();
        Piece blackCata = new Catapult("Black Catapult", g.black, 0, 1);
        Piece whitePawn = new Pawn("White Pawn", g.white, 0, 2);
        Piece whitePawn2 = new Pawn("White Pawn", g.white, 1, 1);
        Piece blackKnight = new Knight("Black Knight", g.black, 0, 5);
        Piece whiteBishop= new Knight("White Bishop", g.white, 0, 6);
        g.myBoard.getCell(0,1).setCurrent(blackCata);
        g.myBoard.getCell(0,2).setCurrent(whitePawn);
        g.myBoard.getCell(1,1).setCurrent(whitePawn2);
        g.myBoard.getCell(0,5).setCurrent(blackKnight);
        g.myBoard.getCell(0,6).setCurrent(whiteBishop);
        assertFalse(blackCata.checkMove(g.myBoard, 0,1,0,2));
        assertFalse(blackCata.checkMove(g.myBoard, 0,1,1,1));
        assertFalse(blackCata.checkMove(g.myBoard, 0,1,0,5));
        assertFalse(blackCata.checkMove(g.myBoard,0,1,0,6));
        assertFalse(blackCata.checkMove(g.myBoard,0,1,1,6));

    }

    @Test
    void testGuardFirstMove() {
        Game g = new Game();
        Piece whiteGuard = new Guard("White Guard", g.white, 0, 1);
        g.myBoard.getCell(0,1).setCurrent(whiteGuard);
        assertTrue(((Guard) whiteGuard).isFirst());
        assertTrue(whiteGuard.checkMove(g.myBoard, 0,1,0,2));
        assertTrue(whiteGuard.checkMove(g.myBoard,0,1,1,2));
    }

    @Test
    void testGuardAfterFirstMove() {
        Game g = new Game();
        Piece whiteGuard = new Guard("White Guard", g.white, 1, 1);
        g.myBoard.getCell(1,1).setCurrent(whiteGuard);
        g.myBoard.movePiece(g.white, 1,1,1,2);
        assertFalse(((Guard) whiteGuard).isFirst());
        assertFalse(whiteGuard.checkMove(g.myBoard,1,1,1,2));
        assertTrue(whiteGuard.checkMove(g.myBoard, 1,1,0,2));
        assertTrue(whiteGuard.checkMove(g.myBoard,1,1,2,2));
    }

    @Test
    void testGuardInvalid() {
        Game g = new Game();
        Piece whiteGuard = new Guard("White Guard", g.white, 2, 1);
        g.myBoard.getCell(2,1).setCurrent(whiteGuard);
        assertFalse(whiteGuard.checkMove(g.myBoard,2,1,4,2));
        assertFalse(whiteGuard.checkMove(g.myBoard, 2,1,0,2));
        assertFalse(whiteGuard.checkMove(g.myBoard,2,1,2,4));
    }


    @Test
    void testKingValid() {
        Game g = new Game();
        Piece whiteKing = new King("White King", g.white, 5, 3);
        Piece blackKing = new King("Black King", g.black, 3, 3);
        g.myBoard.Kings[1] = blackKing;
        g.myBoard.Kings[0] = whiteKing;
        g.myBoard.getCell(3,3).setCurrent(blackKing);
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  3, 4));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  3, 2));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  2, 2));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  2, 3));
        assertTrue(blackKing.checkMove(g.myBoard, 3, 3,  2, 4));
    }

    @Test
    void testKingInvalid(){
        Game g = new Game();
        Piece blackKing = new King("Black King", g.black, 3, 3);
        Piece whiteKing = new King("White King", g.white, 5, 3);
        g.myBoard.Kings[0] = whiteKing;
        g.myBoard.Kings[1] = blackKing;
        g.myBoard.getCell(3,3).setCurrent(blackKing);
        g.myBoard.getCell(5,3).setCurrent(whiteKing);
        assertFalse(blackKing.checkMove(g.myBoard, 3, 3,  4, 2));
        assertFalse(blackKing.checkMove(g.myBoard, 3, 3,  4, 3));
        assertFalse(blackKing.checkMove(g.myBoard, 3, 3,  4, 4));
    }

    @Test
    void testKnightValid() {
        Game g = new Game();
        Piece blackKnight = new Knight("Black Knight", g.black, 3,3);
        g.myBoard.getCell(3,3).setCurrent(blackKnight);
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  1, 2));
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  1, 4));
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  2, 1));
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  2, 5));
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  4, 1));
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  4, 5));
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  5, 2));
        assertTrue(blackKnight.checkMove(g.myBoard, 3, 3,  5, 4));
    }

    @Test
    void testKnightInvalid() {
        Game g = new Game();
        Piece blackKnight = new Knight("Black Knight", g.black, 3,3);
        g.myBoard.getCell(3,3).setCurrent(blackKnight);
        assertFalse(blackKnight.checkMove(g.myBoard, 3, 3,  3, 4));
        assertFalse(blackKnight.checkMove(g.myBoard, 3, 3,  4, 4));
        assertFalse(blackKnight.checkMove(g.myBoard, 3, 3,  2, 3));
    }

    @Test
    void testPawnFirstMove() {
        Game g = new Game();
        Piece whitePawn = new Pawn("White Pawn", g.white, 5,1);
        g.myBoard.getCell(5,1).setCurrent(whitePawn);
        assertTrue(((Pawn) whitePawn).isFirst());
        assertTrue(whitePawn.checkMove(g.myBoard, 5, 1,  5, 2));
        assertTrue(whitePawn.checkMove(g.myBoard, 5, 1,  5, 3)); // Can go two block if first move
    }

    @Test
    void testPawnAfterFirstMove() {
        Game g = new Game();
        Piece whitePawn = new Pawn("White Pawn", g.white, 0,1);
        g.myBoard.getCell(0,1).setCurrent(whitePawn);
        g.myBoard.movePiece(g.white, 0,1,0,2);
        assertTrue(whitePawn.checkMove(g.myBoard, 0, 2,  0, 3));
        assertFalse(whitePawn.checkMove(g.myBoard, 0, 2,  0, 4));

    }

    @Test
    void testPawnBlackGoBackward() {
        Game g = new Game();
        Piece blackPawn = new Pawn("Black Pawn", g.black, 0,6);
        g.myBoard.getCell(0,6).setCurrent(blackPawn);
        assertTrue(blackPawn.checkMove(g.myBoard, 0, 6,  0, 5));
        assertFalse(blackPawn.checkMove(g.myBoard, 0, 6,  0, 7));
    }

    @Test
    void testPawnEnPassant() {
        Game g = new Game();
        Piece whitePawn = new Pawn("White Pawn", g.white, 5,1);
        Piece blackPawn1 = new Pawn("Black Pawn", g.black, 4,2);
        Piece blackPawn2 = new Pawn("Black Pawn", g.black, 6,2);
        g.myBoard.getCell(4,2).setCurrent(blackPawn1);
        g.myBoard.getCell(6,2).setCurrent(blackPawn2);
        assertTrue(whitePawn.checkMove(g.myBoard, 5, 1,  4, 2));
        assertTrue(whitePawn.checkMove(g.myBoard, 5, 1,  6, 2));
    }

    @Test
    void testPawnInvalid() {
        Game g = new Game();
        Piece whitePawn = new Pawn("White Pawn", g.white, 5,1);
        g.myBoard.getCell(5,1).setCurrent(whitePawn);
        assertFalse(whitePawn.checkMove(g.myBoard, 5, 1,  5, 5));
        assertFalse(whitePawn.checkMove(g.myBoard, 5, 1,  4, 1));
        assertFalse(whitePawn.checkMove(g.myBoard, 5, 1,  4, 2));
        assertFalse(whitePawn.checkMove(g.myBoard, 5, 1,  5, 0)); // Can't Go back

        // illegal moves (can't move even has enemy ahead)
        Piece blackPawn0 = new Pawn("Black Pawn", g.black, 5,2);
        g.myBoard.getCell(5,2).setCurrent(blackPawn0);
        assertFalse(whitePawn.checkMove(g.myBoard, 5, 1,  5, 2));
    }




    @Test
    void testRookValid() {
        Game g = new Game();
        Piece blackRook = new Rook("Black Rook", g.black, 3,3);
        g.myBoard.getCell(3,3).setCurrent(blackRook);
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  3, 0));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  3, 1));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  3, 2));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  3, 4));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  3, 5));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  3, 6));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  3, 7));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  0, 3));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  1, 3));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  2, 3));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  4, 3));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  5, 3));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  6, 3));
        assertTrue(blackRook.checkMove(g.myBoard, 3, 3,  7, 3));
    }

    @Test
    void testRookInvalid() {
        Game g = new Game();
        Piece blackRook = new Rook("Black Rook", g.black, 3,3);
        g.myBoard.getCell(3,3).setCurrent(blackRook);
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  1, 2));
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  1, 4));
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  2, 1));
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  2, 5));
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  0, 0));
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  1, 1));
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  2, 2));
        assertFalse(blackRook.checkMove(g.myBoard, 3, 3,  4, 4));
    }

    @Test
    void testBishopValid() {
        Game g = new Game();
        Piece blackBishop = new Bishop("Black Bishop", g.black, 3, 3);
        g.myBoard.getCell(3, 3).setCurrent(blackBishop);
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3, 0, 0));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3, 1, 1));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3, 2, 2));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3, 4, 4));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3, 5, 5));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3, 6, 6));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3, 7, 7));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3, 6, 0));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3, 5, 1));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3, 4, 2));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3, 2, 4));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3, 1, 5));
        assertTrue(blackBishop.checkMove(g.myBoard, 3, 3, 0, 6));
    }

    @Test
    void testBishopInvalid() {
        Game g = new Game();
        Piece whiteBishop = new Bishop("White Bishop", g.white, 3, 3);
        g.myBoard.getCell(3, 3).setCurrent(whiteBishop);
        assertFalse(whiteBishop.checkMove(g.myBoard, 3, 3,  4, 1));
        assertFalse(whiteBishop.checkMove(g.myBoard, 3, 3,  4, 5));
        assertFalse(whiteBishop.checkMove(g.myBoard, 3, 3,  5, 2));
        assertFalse(whiteBishop.checkMove(g.myBoard, 3, 3,  5, 4));
        assertFalse(whiteBishop.checkMove(g.myBoard, 3, 3,  0, 3));
        assertFalse(whiteBishop.checkMove(g.myBoard, 3, 3,  1, 3));
        assertFalse(whiteBishop.checkMove(g.myBoard, 3, 3,  2, 3));
        assertFalse(whiteBishop.checkMove(g.myBoard, 3, 3,  4, 3));
    }

    @Test
    void testQueenValid() {
        Game g = new Game();
        Piece blackQueen = new Queen("Black Queen", g.black, 3, 3);
        g.myBoard.getCell(3, 3).setCurrent(blackQueen);
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 0, 0));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 1, 1));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 2, 2));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 4, 4));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 5, 5));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 6, 6));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 7, 7));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 6, 0));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 5, 1));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 4, 2));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 2, 4));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 1, 5));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 0, 6));

        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 0, 3));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 1, 3));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 2, 3));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 4, 3));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 5, 3));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 6, 3));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 7, 3));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 3, 0));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 3, 1));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 3, 2));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 3, 4));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 3, 5));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 3, 6));
        assertTrue(blackQueen.checkMove(g.myBoard, 3, 3, 3, 7));
    }

    @Test
    void testQueenInvalid() {
        Game g = new Game();
        Piece whiteQueen = new Queen("White Queen", g.white, 3,3);
        g.myBoard.getCell(3,3).setCurrent(whiteQueen);
        assertFalse(whiteQueen.checkMove(g.myBoard, 3, 3,  1, 2));
        assertFalse(whiteQueen.checkMove(g.myBoard, 3, 3,  1, 4));
        assertFalse(whiteQueen.checkMove(g.myBoard, 3, 3,  2, 1));
        assertFalse(whiteQueen.checkMove(g.myBoard, 3, 3,  2, 5));
        assertFalse(whiteQueen.checkMove(g.myBoard, 3, 3,  4, 1));
        assertFalse(whiteQueen.checkMove(g.myBoard, 3, 3,  4, 5));
        assertFalse(whiteQueen.checkMove(g.myBoard, 3, 3,  5, 2));
        assertFalse(whiteQueen.checkMove(g.myBoard, 3, 3,  5, 4));
    }

}