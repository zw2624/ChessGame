package view;

import control.Controller;
import model.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GameView includes the a JPanel for board and a Menu Bar.
 */
public class GameView {

    private JFrame window;
    private JMenuBar menuBar;
    private BoardView board;
    private ToolView blackTool;
    private ToolView whiteTool;
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
        this.blackTool = new ToolView("Toolbox for Black", g.black);
        this.whiteTool = new ToolView("Toolbox for White", g.white);
        window.add(board.getChessBoard());
        window.setJMenuBar(menuBar);
        window.add(blackTool, BorderLayout.EAST);
        window.add(whiteTool, BorderLayout.WEST);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }

    public BoardView getBoard() {
        return board;
    }

    public ToolView getBlackTool() {
        return blackTool;
    }

    public ToolView getWhiteTool() {
        return whiteTool;
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