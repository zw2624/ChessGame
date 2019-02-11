package view;

import model.Game;

import javax.swing.*;

public class GameView {

    private JFrame window;
    Game g;


    public GameView(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        window = new JFrame("Static Chessboard");
        window.setSize(800, 800);
        
        g = new Game();
        g.myBoard.setup();
        BoardView board = new BoardView(g);
        window.add(board.getChessBoard());
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * main function; Assignment 1.1 display only.
     * @param args
     */
    public static void main(String[] args) {
        new GameView();
    }
}