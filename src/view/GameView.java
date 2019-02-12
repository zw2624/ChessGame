package view;

import model.Game;

import javax.swing.*;

/**
 * GameView includes the a JPanel for board and a Menu Bar.
 */
public class GameView {

    private JFrame window;
    private JMenuBar menuBar;
    private BoardView board;
    Game g;


    /**
     * Constructor
     */
    public GameView(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        window = new JFrame("Static Chessboard");
        window.setSize(800, 800);
        
        this.g = new Game();
        g.myBoard.setup();
        this.board = new BoardView(g);
        this.menuBar = new MenuView();

        window.add(board.getChessBoard());
        window.setJMenuBar(menuBar);
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