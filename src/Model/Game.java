
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
    //        protected Intersection intersection;
    private boolean isGameRunning = true;


    public Game() throws IOException, AWTException {

        gamePanel = new GamePanel();
//            intersection = new Intersection(gameFrame.getMagnet());
        keyListener = new KeyListener(gamePanel);
        mouseListener = new MouseListener(gamePanel);

//            Robot robot= new Robot();
//            robot.keyPress(KeyEvent.VK_WINDOWS);
//            robot.keyPress(KeyEvent.VK_D);
//            robot.keyRelease(KeyEvent.VK_WINDOWS);
//            robot.keyRelease(KeyEvent.VK_D);
    }

    public GamePanel getGameFrame() {
        return gamePanel;
    }

    public void setGameFrame(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

//        public Intersection getIntersection() {
//            return intersection;
//        }
//
//        public void setIntersection(Intersection intersection) {
//            this.intersection = intersection;
//        }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        isGameRunning = gameRunning;
    }

}


