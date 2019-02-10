package view;

import model.Game;

import javax.swing.*;

public class GameView {

    private JFrame window;

    public GameView(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        window = new JFrame("Static Chessboard");
        window.setSize(1200, 1200);
        
        Game g = new Game();
        BoardView board = new BoardView(g);
        window.add(board.getChessBoard());
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initializeMenu(){

    }

    public static void main(String[] args) {
        new GameView();
    }
}