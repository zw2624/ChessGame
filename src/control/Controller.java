package control;

import model.Cell;
import model.Game;
import model.Piece;
import model.Player;
import view.GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Contro
 */

public class Controller {
    private Game g;
    private GameView gameView;

    private JButton from;
    private JButton to;
    private Cell source;
    private Cell destin;
    private ArrayList<Cell> possible;


    /**
     * Constructor
     */
    public Controller(Game g, GameView gameView) {
        this.g = g;
        this.gameView = gameView;
        source = null;
        destin = null;
        addClickSquareListener(new onClickListener());
        addToolListener();
        addMenuListener();
    }


    /**
     * get the cell user clicked
     */
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

    /**
     * get the button user clicked
     */
    private JButton getBtn(ActionEvent e) {
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

    /**
     * add listener for chessboard
     */
    public void addClickSquareListener(ActionListener onClick) {
        for(int i = 0; i < 8; i ++) {
            for(int j = 0; j < 8; j ++) {
                ((JButton)gameView.getBoard().getGrid()[i][j]).addActionListener(onClick);
            }
        }
    }

    /**
     * add listener for toolbox
     */
    public void addToolListener() {
        gameView.getWhiteTool().forfeit.addActionListener(new forfeitListener(g.white));
        gameView.getBlackTool().forfeit.addActionListener(new forfeitListener(g.black));
        gameView.getWhiteTool().undo.addActionListener(new undoListener(g.white));
        gameView.getBlackTool().undo.addActionListener(new undoListener(g.black));
        gameView.getWhiteTool().restart.addActionListener(new restartListener(g.white));
        gameView.getBlackTool().restart.addActionListener(new restartListener(g.black));
    }

    /**
     * add listener for menu
     */
    public void addMenuListener() {
        gameView.getMenuBar().checkScore.addActionListener(new scoreListener(g));
        gameView.getMenuBar().getLog.addActionListener(new logListener(g));
    }

    /**
     * Chess Board Listeners
     */

    private class onClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Player cur =  g.getPlayer(g.next);
            if (source == null) {
                from = getBtn(e);
                source = getCell(e);
                possible = g.myBoard.possibleMove(source);
                System.out.println(possible.size());
                for (int i = 0; i < possible.size(); i++) {
                    Cell c = possible.get(i);
                    gameView.getBoard().paintGrey(c);
                }
            } else {
                destin = getCell(e);
                to = getBtn(e);
                int sucessMove = g.myBoard.movePiece(cur, source.x, source.y, destin.x, destin.y);
                String msg = null;
                System.out.println("Generating message");
                switch (sucessMove){
                    case 9:
                        msg = "This is " + cur.nickName + "'s turn";
                        break;
                    case 8:
                        msg = "You selected an empty cell.";
                        break;
                    case 7:
                        msg = "This is not your piece!";
                        break;
                    case 6:
                        msg = "Index out of bound";
                        break;
                    case 5:
                        msg = "You can't leap over other piece!";
                        break;
                    case 4:
                        msg = "The target cell has your piece.";
                        break;
                    case 3:
                        msg = "This move will put your king in check!";
                        break;
                    case 0:
                        msg = "This is not a valid move.";
                        break;
                    case 1:
                        msg = null;
                        break;
                }
                if (msg == null) {
                    to.setIcon(from.getIcon());
                    from.setIcon(null);
                } else {
                    JOptionPane.showMessageDialog(null, msg, "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
                source = null; // set source to null after move
                destin = null;
                from = null;
                to = null;
                for (int i = 0; i < possible.size(); i++) {
                    Cell c = possible.get(i);
                    gameView.getBoard().paintOrigin(c);
                }
                if (msg == null) {
                    Player op = g.getOpponent(cur);
                    //System.out.println("Opponent is " + op.nickName);
                    ArrayList<Piece> threats = g.inCheck(op);
                    if (threats.size() != 0) {
                        //for (int i = 0; i < threats.size(); i++) {
                        //    System.out.println(threats.get(i).Name);
                        //}
                        boolean checkMate = g.isCheckmate(op, threats);
                        if (checkMate) {
                            msg = "Checkmate! " + cur.nickName + " wins!";
                            JOptionPane.showMessageDialog(null, msg, "Warning", JOptionPane.INFORMATION_MESSAGE);
                            cur.addWinNum();
                            op.addLoseNum();
                            newGamePop();
                            return;
                        }
                    } else  {
                        if (g.isStalemate(cur)) {
                            msg = "Stalemate! This is a draw!";
                            JOptionPane.showMessageDialog(null, msg, "Warning", JOptionPane.INFORMATION_MESSAGE);
                            cur.addDrawNum();
                            op.addDrawNum();
                            int cus = JOptionPane.showConfirmDialog(null, "Do you want to use custom pieces?", "New Game", JOptionPane.YES_NO_OPTION);
                            Boolean custom = cus == JOptionPane.YES_OPTION;
                            g.restart(false);
                            gameView.getBoard().refresh();
                            return;
                        }
                    }
                }
                return;
            }
        }
    }


    /**
     * Tool Box Listeners
     */

    private class forfeitListener implements ActionListener {

        private Player player;

        public forfeitListener(Player player) {
            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            player.addLoseNum();
            g.getOpponent(player).addWinNum();
            newGamePop();
        }
    }

    private class undoListener implements ActionListener {

        private Player player;

        public undoListener(Player player) {
            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(player.getPlayerID() + " is undoing");
            if (g.next == player.getPlayerID()) {
                String msg = "You can not undo a move if your opponent already moved. Please make a new move";
                JOptionPane.showMessageDialog(null, msg, "Warning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            g.undo();
            gameView.getBoard().refresh();
        }
    }

    private class restartListener implements ActionListener {

        private Player player;

        public restartListener(Player player) {
            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String msg = g.getOpponent(player).nickName + ", do you agree to restart?";
            int res = JOptionPane.showConfirmDialog(null, msg, "Restart Request", JOptionPane.YES_NO_OPTION);
            Boolean agree = res == JOptionPane.YES_OPTION;
            if (agree) {
                player.addDrawNum();
                g.getOpponent(player).addDrawNum();
                newGamePop();
            }
        }
    }

    /**
     * Pop a window asking if users want to use custom pieces
     */
    private void newGamePop() {
        int cus = JOptionPane.showConfirmDialog(null, "Do you want to use custom pieces?", "New Game", JOptionPane.YES_NO_OPTION);
        Boolean custom = cus == JOptionPane.YES_OPTION;
        g.restart(custom);
        gameView.getBoard().refresh();
    }

    /**
     * Menu Bar Listeners
     */

    private class scoreListener implements ActionListener {

        Game g;

        public scoreListener(Game g) {
            this.g = g;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int blackWin = g.black.getWinNum();
            int blackDraw = g.black.getDrawNum();
            int blackLose = g.black.getLoseNum();
            int whiteWin = g.white.getWinNum();
            int whiteDraw = g.white.getDrawNum();
            int whiteLose = g.white.getLoseNum();
            Object[][] rows = {
                    {g.black.nickName, blackWin, blackDraw, blackLose},
                    {g.white.nickName, whiteWin, whiteDraw, whiteLose}
            };
            Object[] cols = {
                    "Name","Wins","Draws","Loses"
            };
            JTable table = new JTable(rows, cols);
            JOptionPane.showMessageDialog(null, new JScrollPane(table));
        }
    }

    private class logListener implements ActionListener {

        Game g;

        public logListener(Game g) {
            this.g = g;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextArea ret = new JTextArea();
            String msg = "Log for this game \n";
            for (int i = 0; i < g.moveHistory.size(); i++) {
                msg += g.moveHistory.get(i).convertToLog();
                msg += "\n";
            }
            ret.setText(msg);
            JOptionPane.showMessageDialog(null, ret);
        }
    }

}
