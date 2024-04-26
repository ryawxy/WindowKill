package Controller;

import Model.*;
import View.*;
import View.Settings.SettingsFrame;
import myproject.MyProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
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
    private int time=3;
    //amount of time since shooting the next fire
    private int empowerTime;
    //amount of time that has passed since empower item is activated
    private int impactTimer;
    //amount of time the has passed since enemy collision
    private int impactTimer2;
    //amount of time the has passed since collision with epsilon
    private int impactTimer3;
    //amount of time the has passed since enemy collision with shotgun
    private int impactTimer4;
    //amount of time the has passed since frame collision with shotgun
    private int impactTimer5;
    //amount of time the has passed since enemy collision with epsilons vertex
    private int impactTimer6;
    //amount of time the has passed since squarantine
    private int banishTime;
    //amount of time the has passed since banish item is activated
    private long currentTime;
    private long lastUsed = 0;
    // last time ability key activated
    private boolean canUseAbility;
    private long acesoTimer = 0;
    // last time HP added
    private Timer elapsedTimer;
    private static int seconds;
    private static int minutes;
    private boolean wave1Created;
    private boolean wave2Created;
    private boolean wave3Created;


    int deadS;
    int deadT;
    int timeBetweenWave;
    private static boolean win;
    private String [] option = {"menu"};




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

                    //waves
                    Wave.initWave1();
                    if (!wave1Created) {
                        if(SettingsFrame.getChosenLevel()==0) {
                            for (Squarantine squarantine : Wave.wave1EasySquarantine) {
                                GamePanel.getSquarantine().add(squarantine);
                            }
                            for (Trigorath trigorath : Wave.wave1EasyTrigorath) {
                                GamePanel.getTrigoraths().add(trigorath);
                            }
                        }else        if(SettingsFrame.getChosenLevel()==1) {
                            for (Squarantine squarantine : Wave.wave1MediumSquarantine) {
                                GamePanel.getSquarantine().add(squarantine);
                            }
                            for (Trigorath trigorath : Wave.wave1MediumTrigorath) {
                                GamePanel.getTrigoraths().add(trigorath);
                            }
                        }else  if(SettingsFrame.getChosenLevel()==2) {
                            for (Squarantine squarantine : Wave.wave1HardSquarantine) {
                                GamePanel.getSquarantine().add(squarantine);
                            }
                            for (Trigorath trigorath : Wave.wave1HardTrigorath) {
                                GamePanel.getTrigoraths().add(trigorath);
                            }
                        }
                        wave1Created = true;
                        GameInfo.setCurrentWave(1);
                    }


                    //if wave 1 ended create wave2
                    if (!wave2Created) {
                        for (Trigorath trigorath : GamePanel.getTrigoraths()) {
                            if (trigorath.isDead()) {
                                deadT++;

                            }
                        }
                        for (Squarantine squarantine : GamePanel.getSquarantine()) {
                            if (squarantine.isDead()) {
                                deadS++;

                            }
                        }
                    }
                    if (deadT >= GamePanel.getTrigoraths().size() && deadS >= GamePanel.getSquarantine().size()) {
                        timeBetweenWave++;
                        if (timeBetweenWave > 500){
                            GamePanel.getSquarantine().clear();
                        GamePanel.getTrigoraths().clear();
                        //    if(nextWave>=200) {
                        Wave.initWave2();
                        if (!wave2Created && wave1Created) {
                            if(SettingsFrame.getChosenLevel()==0) {
                                for (Squarantine squarantine : Wave.wave2EasySquarantine) {
                                    GamePanel.getSquarantine().add(squarantine);
                                }
                                for (Trigorath trigorath : Wave.wave2EasyTrigorath) {
                                    GamePanel.getTrigoraths().add(trigorath);
                                }
                            }else        if(SettingsFrame.getChosenLevel()==1) {
                                for (Squarantine squarantine : Wave.wave2MediumSquarantine) {
                                    GamePanel.getSquarantine().add(squarantine);
                                }
                                for (Trigorath trigorath : Wave.wave2MediumTrigorath) {
                                    GamePanel.getTrigoraths().add(trigorath);
                                }
                            }else  if(SettingsFrame.getChosenLevel()==2) {
                                for (Squarantine squarantine : Wave.wave2HardSquarantine) {
                                    GamePanel.getSquarantine().add(squarantine);
                                }
                                for (Trigorath trigorath : Wave.wave2HardTrigorath) {
                                    GamePanel.getTrigoraths().add(trigorath);
                                }
                            }
                            wave2Created = true;
                            GameInfo.setCurrentWave(2);
                        }
                        timeBetweenWave = 0;

                    }
                }
                    deadS = 0;
                    deadT =0 ;
                    if(!wave3Created) {
                        for (Trigorath trigorath : GamePanel.getTrigoraths()) {
                            if (trigorath.isDead()) {
                                deadT++;

                            }
                        }
                        for (Squarantine squarantine : GamePanel.getSquarantine()) {
                            if (squarantine.isDead()) {
                                deadS++;

                            }
                        }
                    }
                    if(deadT>=GamePanel.getTrigoraths().size() && deadS>=GamePanel.getSquarantine().size()) {
                        timeBetweenWave++;
                        if (timeBetweenWave > 500) {

                            GamePanel.getSquarantine().clear();
                            GamePanel.getTrigoraths().clear();

                            Wave.initWave3();
                            if (!wave3Created && wave2Created) {

                                if(SettingsFrame.getChosenLevel()==0) {
                                    for (Squarantine squarantine : Wave.wave3EasySquarantine) {
                                        GamePanel.getSquarantine().add(squarantine);
                                    }
                                    for (Trigorath trigorath : Wave.wave3EasyTrigorath) {
                                        GamePanel.getTrigoraths().add(trigorath);
                                    }
                                }else        if(SettingsFrame.getChosenLevel()==1) {
                                    for (Squarantine squarantine : Wave.wave3MediumSquarantine) {
                                        GamePanel.getSquarantine().add(squarantine);
                                    }
                                    for (Trigorath trigorath : Wave.wave3MediumTrigorath) {
                                        GamePanel.getTrigoraths().add(trigorath);
                                    }
                                }else  if(SettingsFrame.getChosenLevel()==2) {
                                    for (Squarantine squarantine : Wave.wave3HardSquarantine) {
                                        GamePanel.getSquarantine().add(squarantine);
                                    }
                                    for (Trigorath trigorath : Wave.wave3HardTrigorath) {
                                        GamePanel.getTrigoraths().add(trigorath);
                                    }
                                }
                                wave3Created = true;
                                GameInfo.setCurrentWave(3);
                            }
                            timeBetweenWave = 0;
                        }
                    }

                    deadS = 0;
                    deadT =0 ;
                    if(wave3Created) {
                        for (Trigorath trigorath : GamePanel.getTrigoraths()) {
                            if (trigorath.isDead()) {
                                deadT++;

                            }
                        }
                        for (Squarantine squarantine : GamePanel.getSquarantine()) {
                            if (squarantine.isDead()) {
                                deadS++;

                            }
                        }
                    }
                    if(deadT>=GamePanel.getTrigoraths().size() && deadS>=GamePanel.getSquarantine().size()){
                        win = true;
                        if(GamePanel.getEpsilon().getRadius()<GamePanel.getFRAME_WIDTH() && GamePanel.getEpsilon().getRadius()<=GamePanel.getFRAME_HEIGHT()) {
                            GamePanel.getEpsilon().setRadius(GamePanel.getEpsilon().getRadius() + 1);

                        }

                    }

                    if(GamePanel.getFRAME_HEIGHT()<=0 && win){
                        int purchase = 0;

                            purchase = JOptionPane.showOptionDialog(GlassFrame.getINSTANCE(),
                                    "You won! XP:"+ GameInfo.getXP(), null, JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.PLAIN_MESSAGE, null, option, option[0]);

                        if(purchase == 0){
                        //    GlassFrame.getINSTANCE().dispose();
                            GameInfo.setCurrentAbility(null);
                            GamePanel.getEpsilon().setHP(0);
                            wave1Created =  false;
                            wave2Created = false;
                            wave3Created = false;
                            countTime = 0;
                            seconds = 0;
                            minutes = 0;
                            banishTime = 0;
                            acesoTimer = 0;
                            win = false;
                            canUseAbility = false;
                            lastUsed =0;
                            deadS = 0;
                            deadT = 0;
                        //    new StarterMenu();



                            //     GlassFrame.setINSTANCE(null);
                            try {
                                new Game();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            } catch (AWTException ex) {
                                throw new RuntimeException(ex);
                            }
//                            try {
//                                new MyProject();
//                            } catch (IOException ex) {
//                                throw new RuntimeException(ex);
//                            }

                        }
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
                for(Vertex vertex : GamePanel.getEpsilon().getVertex()){
                    vertex.move();
                }

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
                    if (countTime >= 700) {
                        frameSize.shrink();
                    }


                    if (empowerTime >= 500) {
                        ShopFrame.setEmpowerItem(false);
                        empowerTime=0;
                    }

                    //check if a shot intersects with frame edges
                    // if so expansion starts from that side for a second
                    // and impact mechanism activates from that point

                    for(ShotGun shotGun : ShotGun.getShots()){
                        if(shotGun.isVisible()) intersectionSide = intersection.shotIntersectsFrame(shotGun);
                        if (intersectionSide != null) {

                            if (shotGun.getExpansion() < 10) {
                                frameSize.expand(intersectionSide);
                                shotGun.setExpansion(shotGun.getExpansion() + 1);
                                Intersection.setIntersectionPoint5(new Point2D.Double(shotGun.getX(), shotGun.getY()));
                                shotGun.setVisible(false);
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
                    if(impactTimer>=10){
                        impactTimer = 0;
                        Intersection.setIntersectionPoint(null);
                    }

                    if(Intersection.getIntersectionPoint2()!=null){
                        impactTimer2++;
                    }

                    if(impactTimer2>=10){
                        impactTimer2 = 0;
                        Intersection.setIntersectionPoint2(null);
                        Intersection.setIntersectionPoint3(null);
                    }
                    if(Intersection.getIntersectionPoint4()!=null){
                        impactTimer3++;
                    }
                    if (impactTimer3>=10){
                        Intersection.setIntersectionPoint4(null);
                        impactTimer3 = 0;
                    }
                    if(Intersection.getIntersectionPoint5()!=null){
                        impactTimer4++;

                    }
                    if(impactTimer4>=20){
                        impactTimer4= 0;
                        Intersection.setIntersectionPoint5(null);
                    }
                    if(Intersection.getIntersectionPoint6()!=null){
                        impactTimer5++;
                    }

                    if(impactTimer5>=10){
                        Intersection.setIntersectionPoint6(null);
                        Intersection.setIntersectionPoint7(null);
                        impactTimer5 = 0;
                    }
                    if(Intersection.getIntersectionPoint8()!=null){
                        impactTimer6++;
                    }
                    if(impactTimer6>=10){
                        Intersection.setIntersectionPoint8(null);
                        impactTimer6 = 0;
                    }

                    if(ShopFrame.isBanishItem()){
                        banishTime++;
                    }
                    if(banishTime>=20){
                        banishTime = 0;
                        ShopFrame.setBanishItem(false);
                    }
                    if(ShopFrame.isHealItem()){
                        GamePanel.getEpsilon().setHP(GamePanel.getEpsilon().getHP()+10);
                        ShopFrame.setHealItem(false);
                    }
                    for(Trigorath trigorath:GamePanel.getTrigoraths() ){
                        if(!trigorath.isDead()){
                        Epsilon epsilon = GamePanel.getEpsilon();
                        Polygon trigorath2 = new Polygon(trigorath.getxPoints(), trigorath.getyPoints(),3);
                            for(Vertex vertex : epsilon.getVertex()) {
                                if(intersection.checkCollision((int)vertex.getxCenter(),(int)vertex.getyCenter(),vertex.getRadius(),trigorath2)) {

                                    Intersection.setIntersectionPoint2(new Point2D.Double(vertex.getxCenter(), vertex.getyCenter()));
                                    Intersection.setIntersectionPoint3(new Point2D.Double(trigorath.getX(),trigorath.getY()));
                                    intersection.checkMeleeAttack();
                                }
                            }
                        if(intersection.checkCollision(epsilon.getxCenter(),epsilon.getyCenter(),epsilon.getRadius(),trigorath2)) {
                            Intersection.setIntersectionPoint2(new Point2D.Double(epsilon.getxCenter(), epsilon.getyCenter()));
                            Intersection.setIntersectionPoint3(new Point2D.Double(trigorath.getX(),trigorath.getY()));
                            intersection.checkMeleeAttack();
                        }

                        }
                    }
                    for(Squarantine squarantine:GamePanel.getSquarantine() ){
                        if(!squarantine.isDead()){
                        Epsilon epsilon = GamePanel.getEpsilon();
                        Polygon squarantine2 = new Polygon(squarantine.getxPoints(), squarantine.getyPoints(),4);
                            for(Vertex vertex : epsilon.getVertex()) {
                                if(intersection.checkCollision((int)vertex.getxCenter(),(int)vertex.getyCenter(),vertex.getRadius(),squarantine2)) {
                                    System.out.println(111);
                                    Intersection.setIntersectionPoint2(new Point2D.Double(vertex.getxCenter(), vertex.getyCenter()));
                                    Intersection.setIntersectionPoint3(new Point2D.Double(squarantine.getX(),squarantine.getY()));
                                    intersection.checkMeleeAttack();
                                }
                            }
                        if(intersection.checkCollision(epsilon.getxCenter(),epsilon.getyCenter(),epsilon.getRadius(),squarantine2)) {
                            Intersection.setIntersectionPoint2(new Point2D.Double(epsilon.getxCenter(), epsilon.getyCenter()));
                            Intersection.setIntersectionPoint3(new Point2D.Double(squarantine.getX(),squarantine.getY()));
                            intersection.checkMeleeAttack();
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
                            GamePanel.getEpsilon().addVertex();


                            canUseAbility = false;
                        }



                    }

                    if(KeyListener.isAbilityKeyPressed()){

                        currentTime = System.currentTimeMillis();

                        if( (currentTime - lastUsed)/(  1000)>=2){
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
        elapsedTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                if(seconds>=59){
                    seconds = 0;
                    minutes++;
                }
            }
        });
        elapsedTimer.start();

    }

    public static int getSeconds() {
        return seconds;
    }

    public static void setSeconds(int seconds) {
        GameLoop.seconds = seconds;
    }

    public static int getMinutes() {
        return minutes;
    }

    public static void setMinutes(int minutes) {
        GameLoop.minutes = minutes;
    }

    public static boolean isWin() {
        return win;
    }

    public static void setWin(boolean win) {
        GameLoop.win = win;
    }
}
