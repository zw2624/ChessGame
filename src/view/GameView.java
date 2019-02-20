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
    private MenuView menuBar;
    private BoardView board;
    private ToolView blackTool;
    private ToolView whiteTool;
    String playerWhite;
    String playerBlack;
    Game g;

    /**
     * Constructor
     */
    public GameView(String name1, String name2, Boolean custom){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        window = new JFrame("Static Chessboard");
        window.setSize(800, 1000);
        
        this.g = new Game(name1, name2);
        g.myBoard.setup(custom);
        this.board = new BoardView(g);
        this.menuBar = new MenuView();
        this.blackTool = new ToolView("Toolbox for Black", g.black);
        this.whiteTool = new ToolView("Toolbox for White", g.white);
        window.add(board.getChessBoard());
        window.setJMenuBar(menuBar);
        window.add(blackTool, BorderLayout.NORTH);
        window.add(whiteTool, BorderLayout.SOUTH);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }

    public BoardView getBoard() {
        return board;
    }

    public MenuView getMenuBar() {
        return menuBar;
    }

    public ToolView getBlackTool() {
        return blackTool;
    }

    public ToolView getWhiteTool() {
        return whiteTool;
    }

    /**
     * main function; Assignment 1.2 display only.
     * @param args
     */
    public static void main(String[] args) {
        String playerWhite = JOptionPane.showInputDialog("What is the name for the Light side?");
        String playerBlack = JOptionPane.showInputDialog("What is the name for the Dark side?");
        int cus = JOptionPane.showConfirmDialog(null, "Do you want to use custom pieces?", "Please select", JOptionPane.YES_NO_OPTION);
        Boolean custom = cus == JOptionPane.YES_OPTION;
        GameView view = new GameView(playerWhite, playerBlack, custom);
        Controller c = new Controller(view.g, view);
    }
}