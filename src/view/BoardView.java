package view;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardView {
    private Game g;
    private JPanel chessBoard;
    private JButton[][] grid;

    public BoardView(Game g) {
        this.g = g;
        chessBoard = new JPanel(new GridLayout(0,9));
        chessBoard.setBackground(Color.white);
        this.init();
    }

    private void init() {
        grid = new JButton[8][8];
        /*Insets buttonMargin = new Insets(0, 0, 0, 0);*/

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton b = new JButton();
                b.setVisible(true);
                if ((j + i)%2 == 0) {
                    b.setBackground(Color.white);
                } else {
                    b.setBackground(Color.gray);
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

    private void setPieces(Game g) {
        ArrayList<Piece> allPieces = g.white.getPieces();
        allPieces.addAll(g.black.getPieces());
        int count = 0;
        while (allPieces.size() > count) {
            Piece p = allPieces.get(count);
            if (p.getImg() != null) {System.out.println("null!");}
            grid[7-p.y][p.x].setIcon(new ImageIcon(p.getImg()));
            count ++;
        }
    }

    public JPanel getChessBoard(){
        return chessBoard;
    }

}
