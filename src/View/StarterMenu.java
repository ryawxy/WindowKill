package View;

import Controller.GameLoop;
import Model.Game;
import View.Settings.SettingsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class StarterMenu extends JFrame {
    private final int MENU_WIDTH = 700;
    private final int MENU_HEIGHT = 700;
    private final Dimension SCREEN_SIZE = new Dimension(MENU_WIDTH,MENU_HEIGHT);
    private final ImageIcon image;
    private final JLabel label;

    JButton start;
    JButton skillTree;
    JButton settings;
    JButton tutorial;
    private Game game;
    private GameLoop gameLoop;

    public StarterMenu(){


        this.setSize(SCREEN_SIZE);
        this.setTitle("Window Kill");
        this.setLocationRelativeTo(null);
//        this.setLayout(null);
        this.setUndecorated(true);


        image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/Images/menu.jpg")));
        label = new JLabel(image);
        label.setSize(SCREEN_SIZE);
        this.add(label);

        //start Button
        start = new JButton("Start");
        Font fontStyle = new Font("Magneto",Font.BOLD,44);
        start.setFont(fontStyle);
        Color fontClr = Color.WHITE;
        start.setForeground(fontClr);
        Color backClr = Color.BLACK;
        start.setBackground(backClr);
        start.setBorderPainted(false);
        //  start.setContentAreaFilled(false);
        start.setFocusPainted(false);
        start.setBounds(200,100,300,80);
        this.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    game = new Game();
                    gameLoop = new GameLoop(game);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //skill-tree Button
        skillTree = new JButton("Skill Tree");
        skillTree.setFont(fontStyle);
        skillTree.setForeground(fontClr);
        skillTree.setBackground(backClr);
        skillTree.setBorderPainted(false);
        skillTree.setFocusPainted(false);
        skillTree.setBounds(200,210,300,80);
//        this.add(skillTree);

        //settings Button
        settings = new JButton("Settings");
        settings.setFont(fontStyle);
        settings.setForeground(fontClr);
        settings.setBackground(backClr);
        settings.setBorderPainted(false);
        settings.setFocusPainted(false);
        settings.setBounds(200,320,300,80);
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsFrame();
                dispose();
            }
        });
//        this.add(settings);

        //tutorial Button
        tutorial = new JButton("Tutorial");
        tutorial.setFont(fontStyle);
        tutorial.setForeground(fontClr);
        tutorial.setBackground(backClr);
        tutorial.setBorderPainted(false);
        tutorial.setFocusPainted(false);
        tutorial.setBounds(200,430,300,80);
//        this.add(tutorial);

        this.setLayout(null);
        this.add(start);
        this.add(skillTree);
        this.add(settings);
        this.add(tutorial);


        this.setVisible(true);
    }
}
