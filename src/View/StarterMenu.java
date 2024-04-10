package View;

import Controller.GameLoop;
import Model.Game;
import View.Settings.SettingsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
    JButton exit;
    private Game game;
    private GameLoop gameLoop;

    public StarterMenu(){


        this.setSize(SCREEN_SIZE);
        this.setTitle("Window Kill");
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
//        this.setLayout(null);


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
        start.setBounds(200,80,300,80);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Robot robot= null;
                try {
                    robot = new Robot();
                } catch (AWTException ex) {
                    throw new RuntimeException(ex);
                }
//                robot.keyPress(KeyEvent.VK_WINDOWS);
//            robot.keyPress(KeyEvent.VK_D);
//            robot.keyRelease(KeyEvent.VK_WINDOWS);
//            robot.keyRelease(KeyEvent.VK_D);
                try {
                    game = new Game();
                    gameLoop = new GameLoop(game);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (AWTException ex) {
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
        skillTree.setBounds(200,190,300,80);
//        this.add(skillTree);

        //settings Button
        settings = new JButton("Settings");
        settings.setFont(fontStyle);
        settings.setForeground(fontClr);
        settings.setBackground(backClr);
        settings.setBorderPainted(false);
        settings.setFocusPainted(false);
        settings.setBounds(200,300,300,80);
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
        tutorial.setBounds(200,410,300,80);
//        this.add(tutorial);
        //exit Button
        exit = new JButton("Exit");
        exit.setFont(fontStyle);
        exit.setForeground(fontClr);
        exit.setBackground(backClr);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setBounds(200,520,300,80);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });

        this.setLayout(null);
        label.add(start);
        label.add(skillTree);
        label.add(settings);
        label.add(tutorial);
        label.add(exit);


        this.setVisible(true);
    }
}
