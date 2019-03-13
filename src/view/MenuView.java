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

        this.history = new JMenu("History");
        this.checkScore = new JMenuItem("Check Scores");
        this.getLog = new JMenuItem("Check Log");
        this.history.add(checkScore);
        this.history.add(getLog);


        JMenu suggest = new JMenu("Help");

        this.add(history);
        this.add(suggest);

    }

    public JMenu getHistory() {
        return history;
    }
}
