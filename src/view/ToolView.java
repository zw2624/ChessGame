package view;

import control.Controller;
import model.Player;
import control.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
        try {
            Image undoImg = ImageIO.read(getClass().getResource("./resource/undo.jpg"));
            Image restartImg = ImageIO.read(getClass().getResource("./resource/restart.png"));
            Image forfeitImg = ImageIO.read(getClass().getResource("./resource/forfeit.jpeg"));
            undoImg = undoImg.getScaledInstance(40,40,java.awt.Image.SCALE_SMOOTH);
            restartImg = restartImg.getScaledInstance(40,40,java.awt.Image.SCALE_SMOOTH);
            forfeitImg = forfeitImg.getScaledInstance(40,40,java.awt.Image.SCALE_SMOOTH);
            undo.setIcon(new ImageIcon(undoImg));
            restart.setIcon(new ImageIcon(restartImg));
            forfeit.setIcon(new ImageIcon(forfeitImg));
        } catch (IOException e) {
            // ignore
        }

        this.add(forfeit);
        this.add(undo);
        this.add(restart);
    }
}
