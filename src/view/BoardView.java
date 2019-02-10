package view;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import model.*;
import javax.swing.*;
import java.awt.*;

public class BoardView {
    private JPanel chessBoard;
    private JButton[][] grid;

    public BoardView(Game g) {
        chessBoard = new JPanel(new GridLayout(0,9));
        chessBoard.setBackground(Color.white);
        grid = new JButton[8][8];
        /*Insets buttonMargin = new Insets(0, 0, 0, 0);*/

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton b = new JButton();
                b.setVisible(true);
                if ((j + i)%2 == 0) {
                    b.setBackground(Color.white);
                } else {
                    b.setBackground(Color.black);
                }
                grid[i][j] = b;
            }
        }

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

    public JPanel getChessBoard(){
        return chessBoard;
    }

}
