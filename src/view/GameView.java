package view;

import control.Controller;
import model.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public BoardView getBoard() {
        return board;
    }

    /**
     * main function; Assignment 1.1 display only.
     * @param args
     */
    public static void main(String[] args) {
        GameView view = new GameView();
        Controller c = new Controller(view.g, view);
    }
}