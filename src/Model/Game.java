
package Model;


import Controller.KeyListener;
import Controller.MouseListener;
import View.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class Game {

    protected GamePanel gamePanel;
    protected KeyListener keyListener;
    protected MouseListener mouseListener;

    public Game() throws IOException, AWTException {
//                    Robot robot= new Robot();
//            robot.keyPress(KeyEvent.VK_WINDOWS);
//            robot.keyPress(KeyEvent.VK_D);
//            robot.keyRelease(KeyEvent.VK_WINDOWS);
//            robot.keyRelease(KeyEvent.VK_D);

        gamePanel = new GamePanel();
        keyListener = new KeyListener(gamePanel);
        mouseListener = new MouseListener(gamePanel);

    }
    public GamePanel getGameFrame() {
        return gamePanel;
    }


}


