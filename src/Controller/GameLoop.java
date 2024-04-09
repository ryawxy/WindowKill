package Controller;

import Model.Game;
import Model.ShotGun;
import Model.Squarantine;
import Model.Trigorath;
import View.GamePanel;
import View.ShopFrame;

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
    private int index=ShotGun.getShots().size()-3;
    //balls shooting one by on in empower mode
    private int time;
    //amount of time since shooting the next fire
    private int empowerTime;
    //amount of time that has passed since empower item is activated
    private int impactTimer;
    //amount of time the has passed since collision


    public GameLoop(Game game) throws IOException {
        this.game = game;
        timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!KeyListener.getPauseGame()) {
                    try {
                        intersection = new Intersection(game.getGameFrame());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (!ShopFrame.isEmpowerItem()) {
                        for (ShotGun shotGun : ShotGun.getShots()) {
                            shotGun.move();

                        }
                    } else {
                        empowerTime++;

                        if (MouseListener.isShootinEmpowerMode()) {

                            time++;

                            for (int i = 0; i <= index; i++) {
                                ShotGun.getShots().get(i).move();
                            }
                            if (time >= 2) {
                                if (index <= ShotGun.getShots().size() - 2) {
                                    index++;
                                    time = 0;
                                }
                            }
                        }
                    }

                for(int i=0;i<GamePanel.getSquarantine().size();i++) {
                    Squarantine squarantine = GamePanel.getSquarantine().get(i);
                    squarantine.move();
                }
                for (int i = 0; i < GamePanel.getTrigoraths().size(); i++) {
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
                    if (countTime >= 700) {
                        frameSize.shrink();
                    }


                    if (empowerTime >= 500) {
                        ShopFrame.setEmpowerItem(false);
                        empowerTime=0;
                    }

                    //check if a shot intersects with frame edges
                    // if so expansion starts from that side for a second
                    for (ShotGun shotGun : ShotGun.getShots()) {
                        intersectionSide = intersection.shotIntersectsFrame(shotGun);
                        if (intersectionSide != null) {
                            if (shotGun.getExpansion() < 20) {
                                frameSize.expand(intersectionSide);
                                shotGun.setExpansion(shotGun.getExpansion() + 1);
                            }
                        }
                    }
                    intersection.shotIntersectsSquarantine();
                    intersection.shotIntersectsTrigorath();
                    intersection.shotIntersectsSquarantine();
                    intersection.epsilonIntersectsCollectible();
                 //   intersection.epsilonIntersectsEnemy();

                    //if trigorath is dead show its collectibles for 10 seconds
                    for (Trigorath trigorath : GamePanel.getTrigoraths()) {
                        if (trigorath.isShowCollectibles()) {
                            trigorath.setTimer(trigorath.getTimer() + 1);
                            if (trigorath.getTimer() > 300) {
                                trigorath.setShowCollectibles(false);
                            }
                        }
                    }

                    countTime++;
                    intersection.enemyIntersection();
                    if(Intersection.getIntersectionPoint()!=null){
                        impactTimer++;
                    }
                    if(impactTimer>=20){
                        impactTimer = 0;
                        Intersection.setIntersectionPoint(null);
                    }

                    game.getGameFrame().repaint();
                }
            }
        });
        timer.start();

    }


}
