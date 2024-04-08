package Controller;

import Model.Game;
import Model.ShotGun;
import Model.Trigorath;
import View.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GameLoop {

    private Game game;
    private Timer timer;
    private FrameSize frameSize;
    private Intersection intersection;
    private Direction intersectionSide;
    private int countTime;
    // amount of time that has passed since the game has started



    public GameLoop(Game game) throws IOException {
        this.game = game;
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    intersection = new Intersection(game.getGameFrame());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                game.getGameFrame().gettrigorath().move();
                GameFrame.getEpsilon().move();

                try {
                    frameSize = new FrameSize(game.getGameFrame());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                //shrinkage starts after 10 seconds
                if(countTime>=700) {
                    frameSize.shrink();
                }

                for (ShotGun shotGun : ShotGun.getShots()) {
                    shotGun.move();

                }
                //check if a shot intersects with frame edges
                // if so expansion starts from that side for a second
                for(ShotGun shotGun : ShotGun.getShots()){
                    intersectionSide = intersection.shotIntersectsFrame(shotGun);
                    if(intersectionSide!=null) {
                        if (shotGun.getExpansion() < 40) {
                            frameSize.expand(intersectionSide);
                            shotGun.setExpansion(shotGun.getExpansion()+1);
                        }
                    }
                }

                countTime++;

                game.getGameFrame().repaint();
            }
        });
        timer.start();

    }


}
