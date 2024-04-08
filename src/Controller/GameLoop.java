package Controller;

import Model.Game;
import Model.ShotGun;
import Model.Trigorath;
import View.GamePanel;

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
        timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    intersection = new Intersection(game.getGameFrame());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

//                for(int i=0;i<GameFrame.getSquarantine().size();i++) {
//                    Squarantine squarantine = GameFrame.getSquarantine().get(i);
//                    squarantine.move();
//                }
                for(int i = 0; i< GamePanel.getTrigoraths().size(); i++) {
                    Trigorath trigorath = GamePanel.getTrigoraths().get(i);
                    trigorath.move();
                }
                GamePanel.getEpsilon().move();

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
                intersection.shotIntersectsSquarantine();
                intersection.shotIntersectsTrigorath();
                intersection.shotIntersectsSquarantine();
                intersection.epsilonIntersectsCollectible();
                intersection.epsilonIntersectsEnemy();

                //if trigorath is dead show its collectibles for 10 seconds
                for(Trigorath trigorath : GamePanel.getTrigoraths()){
                    if(trigorath.isShowCollectibles()){
                        trigorath.setTimer(trigorath.getTimer()+1);
                        if(trigorath.getTimer()>300){
                            trigorath.setShowCollectibles(false);
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
