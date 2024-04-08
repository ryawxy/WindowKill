package Controller;

import Model.Game;
import Model.ShotGun;
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
                GameFrame.getEpsilon().move();
                try {
                    frameSize = new FrameSize(game.getGameFrame());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frameSize.shrink();

                for (ShotGun shotGun : ShotGun.getShots()) {
                    shotGun.move();

                }
                for(ShotGun shotGun : ShotGun.getShots()){
                    intersectionSide = intersection.shotIntersectsFrame(shotGun);
                    if(intersectionSide!=null) {
                        if (shotGun.getExpansion() < 40) {
                            frameSize.expand(intersectionSide);
                            shotGun.setExpansion(shotGun.getExpansion()+1);
                        }
                    }
//                    if(intersection.shotIntersectsFrame(shotGun)){
//                        if(shotGun.getExpansion()<40){
//                            frameSize.expand();
//                            shotGun.setExpansion(shotGun.getExpansion()+1);
//
//                        }
//                    }
                }












                game.getGameFrame().repaint();
            }
        });
        timer.start();

    }


}
