
package Controller;


import Controller.KeyListener;
import Controller.MouseListener;
import Sound.SoundPlayer;
import View.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class Game {

    protected GamePanel gamePanel;
    protected KeyListener keyListener;
    protected MouseListener mouseListener;
    private static SoundPlayer soundPlayer;

    public Game() throws IOException, AWTException {


        gamePanel = new GamePanel();
        keyListener = new KeyListener(gamePanel);
        mouseListener = new MouseListener(gamePanel);
        soundPlayer = new SoundPlayer();
        soundPlayer.playBackgroundMusic();

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

    }
    public GamePanel getGameFrame() {
        return gamePanel;
    }

    public static SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }


    }
