
package Model;


import Controller.KeyListener;
import Controller.MouseListener;
import View.GameFrame;
import Controller.KeyListener;

import java.io.IOException;


public class Game {

    protected GameFrame gameFrame;
    protected KeyListener keyListener;
    protected MouseListener mouseListener;
    //        protected Intersection intersection;
    private boolean isGameRunning = true;


    public Game() throws IOException {

        gameFrame = new GameFrame();
//            intersection = new Intersection(gameFrame.getMagnet());
        keyListener = new KeyListener(gameFrame);
        mouseListener = new MouseListener(gameFrame);
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
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


