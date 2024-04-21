package Controller;

import Model.*;
import View.GameInfo;
import View.GamePanel;
import View.ShopFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Date;


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
    //amount of time the has passed since enemy collision
    private int impactTimer2;
    //amount of time the has passed since collision with epsilon
    private int banishTime;
    //amount of time the has passed since banish item is activated
    private long currentTime;
    private long lastUsed = 0;
    // last time ability key activated
    private boolean canUseAbility;
    private long acesoTimer = 0;
    // last time HP added





    public GameLoop(Game game) throws IOException {
        this.game = game;
        timer = new Timer(2, new ActionListener() {
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

                    intersection.shotIntersectsSquarantine();
                    intersection.shotIntersectsTrigorath();
                //    intersection.shotIntersectsSquarantine();
                    intersection.epsilonIntersectsCollectible();
              //      intersection.epsilonIntersectEnemy();
                    intersection.enemyIntersection();


                    try {
                        frameSize = new FrameSize(game.getGameFrame());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //shrinkage starts after 10 seconds
//                    if (countTime >= 700) {
//                        frameSize.shrink();
//                    }


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

                    if(Intersection.getIntersectionPoint()!=null){
                        impactTimer++;
                    }
                    if(impactTimer>=20){
                        impactTimer = 0;
                        Intersection.setIntersectionPoint(null);
                    }

                    if(Intersection.getIntersectionPoint2()!=null){
                        impactTimer2++;
                    }
                    if(impactTimer2>=20){
                        impactTimer2 = 0;
                        Intersection.setIntersectionPoint2(null);
                    }

                    if(ShopFrame.isBanishItem()){
                        banishTime++;
                    }
                    if(banishTime>=80){
                        banishTime = 0;
                        ShopFrame.setBanishItem(false);
                    }
                    for(Trigorath trigorath:GamePanel.getTrigoraths() ){
                        if(!trigorath.isDead()){
                        Epsilon epsilon = GamePanel.getEpsilon();
                        Polygon trigorath2 = new Polygon(trigorath.getxPoints(), trigorath.getyPoints(),3);
                        if(intersection.checkCollision(epsilon.getxCenter(),epsilon.getyCenter(),epsilon.getRadius(),trigorath2)) {
                            Intersection.setIntersectionPoint2(new Point2D.Double(epsilon.getxCenter(), epsilon.getyCenter()));
                        }
                        }
                    }
                    for(Squarantine squarantine:GamePanel.getSquarantine() ){
                        if(!squarantine.isDead()){
                        Epsilon epsilon = GamePanel.getEpsilon();
                        Polygon squarantine2 = new Polygon(squarantine.getxPoints(), squarantine.getyPoints(),4);
                        if(intersection.checkCollision(epsilon.getxCenter(),epsilon.getyCenter(),epsilon.getRadius(),squarantine2)) {
                            Intersection.setIntersectionPoint2(new Point2D.Double(epsilon.getxCenter(), epsilon.getyCenter()));
                        }
                        }
                    }
                    if(canUseAbility){

                        if(GameInfo.getCurrentAbility().equals(CurrentAbility.Aceso)){
                            currentTime = System.currentTimeMillis();

                                if((currentTime - acesoTimer)/(1000)>=1) {
                                    for(int i=1;i<=KeyListener.getKeyPressedNumber();i++) {
                                        if (GamePanel.getEpsilon().getHP() < 100) {
                                            GamePanel.getEpsilon().setHP(GamePanel.getEpsilon().getHP() + 1);
                                            acesoTimer = currentTime;
                                        }
                                    }

                                }

                        }else if(GameInfo.getCurrentAbility().equals(CurrentAbility.Ares)){
                            Trigorath.setHPDecrement(Trigorath.getHPDecrement()+2*KeyListener.getKeyPressedNumber());
                            Squarantine.setHPDecrement(Trigorath.getHPDecrement()+2*KeyListener.getKeyPressedNumber());
                            canUseAbility = false;
                        }else if(GameInfo.getCurrentAbility().equals(CurrentAbility.Proteus)){
                            GamePanel.getEpsilon().setVertexNumber(GamePanel.getEpsilon().getVertexNumber()+1);
                            canUseAbility = false;
                        }



                    }

                    if(KeyListener.isAbilityKeyPressed()){

                        currentTime = System.currentTimeMillis();

                        if( (currentTime - lastUsed)/(60 * 1000)>=1){
                            if(GamePanel.getEpsilon().getXP()>=100) {
                                canUseAbility = true;
                                GamePanel.getEpsilon().setXP(GamePanel.getEpsilon().getXP() - 100);

                                lastUsed = currentTime;
                            }else{
                                KeyListener.setKeyPressedNumber(KeyListener.getKeyPressedNumber()-1);
                            }
                        }else{
                            KeyListener.setKeyPressedNumber(KeyListener.getKeyPressedNumber()-1);
                        }
                        KeyListener.setAbilityKeyPressed(false);
                    }


                    game.getGameFrame().repaint();
                }
            }
        });
        timer.start();

    }


}
