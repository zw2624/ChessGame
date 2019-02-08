package view;

import javax.swing.*;

public class GameView {

    public GameView(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        JFrame window = new JFrame("Static Chessboard");
        window.setSize(800, 800);

        BoardView board = new BoardView();
        window.add(board);

        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new GameView();
    }
}