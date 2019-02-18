package view;

import control.Controller;
import model.Player;
import control.Controller;

import javax.swing.*;

public class ToolView extends JToolBar {
    public String name;
    private Player player;
    private boolean canUndo;
    public JButton undo;
    public JButton restart;
    public JButton forfeit;

    public ToolView(String n, Player p) {
        this.name = n;
        this.player = p;
        this.canUndo = false;
        undo = new JButton("undo");
        restart = new JButton("restart");
        forfeit = new JButton("forfeit");
        this.add(forfeit);
        this.add(undo);
        this.add(restart);
    }
}
