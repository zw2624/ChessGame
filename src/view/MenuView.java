package view;

import javax.swing.*;

public class MenuView extends JMenuBar {

    public JMenu history;
    public JMenuItem checkScore;
    public JMenuItem getLog;

    /**
     * Constructor
     */
    public MenuView() {
        super();
        JMenu newGame = new JMenu("New Game");
        JMenuItem vsAI = new JMenuItem("Against Computer");
        JMenuItem vsHU = new JMenuItem("Against Other");
        newGame.add(vsAI);
        newGame.add(vsHU);

        this.history = new JMenu("History");
        this.checkScore = new JMenuItem("Check Scores");
        this.getLog = new JMenuItem("Check Log");
        this.history.add(checkScore);
        this.history.add(getLog);


        JMenu suggest = new JMenu("Help");

        this.add(newGame);
        this.add(history);
        this.add(suggest);

    }

    public JMenu getHistory() {
        return history;
    }
}
