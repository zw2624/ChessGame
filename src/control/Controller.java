package control;

import model.Cell;
import model.Game;
import model.Player;
import view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    private Game g;
    private GameView gameView;

    private JButton from;
    private JButton to;
    private Cell source;
    private Cell destin;
    private ArrayList<Cell> possible;


    public Controller(Game g, GameView gameView) {
        this.g = g;
        this.gameView = gameView;
        source = null;
        destin = null;
        addClickSquareListener(new onClickListener());
    }


    private Cell getCell(ActionEvent e) {
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {
                if (gameView.getBoard().getGrid()[i][j] == (JButton) e.getSource()) {
                    return g.myBoard.getCell(j, 7-i);
                }
            }
        }
        return null;
    }

    private JButton getBut(ActionEvent e) {
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {
                JButton JB = gameView.getBoard().getGrid()[i][j];
                if (JB == (JButton) e.getSource()) {
                    return JB;
                }
            }
        }
        return null;
    }

    public void addClickSquareListener(ActionListener onClick) {
        for(int i = 0; i < 8; i ++) {
            for(int j = 0; j < 8; j ++) {
                ((JButton)gameView.getBoard().getGrid()[i][j]).addActionListener(onClick);
            }
        }
    }



    private class onClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Player cur =  g.getPlayer(g.next);
            if (source == null) {
                from = getBut(e);
                source = getCell(e);
                possible = g.myBoard.possibleMove(source);
                System.out.println(possible.size());
                for (int i = 0; i < possible.size(); i++) {
                    Cell c = possible.get(i);
                    gameView.getBoard().paintGrey(c);
                }
            } else {
                for (int i = 0; i < possible.size(); i++) {
                    Cell c = possible.get(i);
                    gameView.getBoard().paintOrigin(c);
                }
                destin = getCell(e);
                to = getBut(e);
                boolean sucessMove = g.myBoard.movePiece(cur, source.x, source.y, destin.x, destin.y);
                if (sucessMove) {
                    to.setIcon(from.getIcon());
                    from.setIcon(null);
                }
                source = null; // set source to null after move
                destin = null;
                from = null;
                to = null;
            }
        }
    }

}
