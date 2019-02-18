package view;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import control.Controller;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * BoardView generates a JPanel Object representing the Chess Board
 */
public class BoardView {
    private Game g;
    private JPanel chessBoard;
    private JButton[][] grid;


    /**
     * Constructor
     * @param g Game Object (will be removed in Assginment1.2)
     */
    public BoardView(Game g) {
        this.g = g;
        chessBoard = new JPanel(new GridLayout(0,9));
        chessBoard.setBackground(Color.white);
        this.init();
    }

    /**
     * Initiate the starting BoardView: draw grid and put pieces on board.
     */
    private void init() {
        grid = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton b = new JButton();
                b.setVisible(true);
                if ((j + i)%2 == 0) {
                    b.setBackground(Color.white);
                } else {
                    b.setBackground(Color.darkGray);
                }
                b.setOpaque(true);
                b.setBorderPainted(false);
                grid[i][j] = b;
            }
        }
        setPieces(g);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0) {
                    chessBoard.add(new JLabel("" + (8 - i), SwingConstants.CENTER));
                }
                chessBoard.add(grid[i][j]);
            }
        }
        chessBoard.add(new JLabel(""));
        for (char c = 'A'; c < 'I'; c++) {
            chessBoard.add(new JLabel(Character.toString(c), SwingConstants.CENTER));
        }
    }

    /**
     * Put the piece on board according to the situation of the game
     * @param g the game being displayed
     */
    public void setPieces(Game g) {
        ArrayList<Piece> allPieces = g.white.getPieces();
        allPieces.addAll(g.black.getPieces());
        int count = 0;
        while (allPieces.size() > count) {
            Piece p = allPieces.get(count);
            grid[7-p.y][p.x].setIcon(new ImageIcon(p.getImg()));
            count ++;
        }
    }

    /**
     * Getter of chessboard
     * @return a JPanel object
     */
    public JPanel getChessBoard(){
        return chessBoard;
    }

    public JButton[][] getGrid() {
        return grid;
    }

    public void paintGrey(Cell c) {
        JButton target = grid[7-c.y][c.x];
        target.setBackground(Color.LIGHT_GRAY);
    }

    public void paintOrigin(Cell c) {
        JButton target = grid[7-c.y][c.x];
        if ((7-c.y + c.x)%2 == 0) {
            target.setBackground(Color.white);
        } else {
            target.setBackground(Color.DARK_GRAY);
        }
    }

}
