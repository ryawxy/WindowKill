package View;

import Controller.Game;
import Controller.GameLoop;
import Controller.KeyListener;
import View.Settings.SettingsFrame;
import myproject.MyProject;

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
    private Robot robot ;
    JButton start;
    JButton skillTree;
    JButton settings;
    JButton tutorial;
    JButton exit;
    JButton XP;
    private Game game;
    private GameLoop gameLoop;

    public StarterMenu(){


//        Robot robot = null;
//        try {
//            robot = new Robot();
//        } catch (AWTException ex) {
//            throw new RuntimeException(ex);
//        }
//        robot.keyPress(KeyEvent.VK_WINDOWS);
//        robot.keyPress(KeyEvent.VK_D);
//        robot.keyRelease(KeyEvent.VK_WINDOWS);
//        robot.keyRelease(KeyEvent.VK_D);

        this.setSize(SCREEN_SIZE);
        this.setTitle("Window Kill");
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);

        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }


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

//                Window[] windows = Window.getWindows();
//                for(Window window : windows){
//                    if(window instanceof  JFrame){
//                        JFrame frame = (JFrame) window;
//                        Component focusOwner = frame.getFocusOwner();
//                        if(focusOwner!=null && !focusOwner.getClass().getName().contains("Window Kill") ){
//                            try {
//                                robot = new Robot();
//                            } catch (AWTException ex) {
//                                throw new RuntimeException(ex);
//                            }
//                            robot.keyPress(KeyEvent.VK_WINDOWS);
//                            robot.keyPress(KeyEvent.VK_D);
//                            robot.keyRelease(KeyEvent.VK_WINDOWS);
//                            robot.keyRelease(KeyEvent.VK_D);
//                        }
//
//                    }
//                }

//                try {
//                    robot = new Robot();
//                } catch (AWTException ex) {
//                    throw new RuntimeException(ex);
//                }
//                robot.keyPress(KeyEvent.VK_WINDOWS);
//                robot.keyPress(KeyEvent.VK_D);
//                robot.keyRelease(KeyEvent.VK_WINDOWS);
//                robot.keyRelease(KeyEvent.VK_D);

                dispose();
                try {

                    game = new Game();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (AWTException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    gameLoop = new GameLoop(game);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                if (!GameLoop.isGameDone()){
//                    Robot robot = null;
//                    try {
//                        robot = new Robot();
//                    } catch (AWTException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                    robot.keyPress(KeyEvent.VK_WINDOWS);
//                    robot.keyPress(KeyEvent.VK_D);
//                    robot.keyRelease(KeyEvent.VK_WINDOWS);
//                    robot.keyRelease(KeyEvent.VK_D);

                            gameLoop.start();
                            gameLoop.elapsedTime();


                        KeyListener.setPauseGame(false);


            }else{

                        KeyListener.setPauseGame(false);

                        GamePanel.setFRAME_WIDTH(700);
                        GamePanel.setFRAME_HEIGHT(700);
                        GlassFrame.getINSTANCE().getContentPane().setLocation(450,100);

                    GamePanel.getEpsilon().setHP(100);
                        gameLoop.start();
                        gameLoop.elapsedTime();
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
        skillTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SkillTreeFrame();
                dispose();
            }
        });
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

            XP = new JButton("XP:" + MyProject.getGameInfo().getXP());

         fontStyle = new Font("Magneto",Font.BOLD,20);
        XP.setFont(fontStyle);
        XP.setForeground(fontClr);
        XP.setBackground(backClr);
        XP.setBorderPainted(false);
        XP.setFocusPainted(false);
        XP.setBounds(200,620,300,60);


        this.setLayout(null);
        label.add(start);
        label.add(skillTree);
        label.add(settings);
        label.add(tutorial);
        label.add(exit);
        label.add(XP);


        this.setVisible(true);
    }
}
