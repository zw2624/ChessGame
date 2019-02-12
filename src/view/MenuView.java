package view;

import javax.swing.*;

public class MenuView extends JMenuBar {

    public MenuView() {
        super();
        JMenu newGame = new JMenu("New Game");
        JMenuItem vsAI = new JMenuItem("Against Computer");
        JMenuItem vsHU = new JMenuItem("Against Other");
        newGame.add(vsAI);
        newGame.add(vsHU);

        JMenu history = new JMenu("Check History");
        JMenu suggest = new JMenu("Suggest Move");

        this.add(newGame);
        this.add(history);
        this.add(suggest);

    }

}
