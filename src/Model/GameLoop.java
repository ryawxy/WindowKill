package Model;

import Controller.*;
import View.*;
import View.Settings.SettingsFrame;
import myproject.MyProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;


public class GameLoop {

    private Game game;
    private  Timer timer;
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
    //amount of time the has passed since squarantines collision
    private int impactTimer7;
    //amount of time the has passed since squarantine and trigorath collision
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
    private static boolean VSCollission;
    private static  boolean VTCollision;
    private static boolean gameDone;
    private Wave wave = new Wave();
    int deadS;
    int deadT;
    int timeBetweenWave;
    private static boolean win;
    private static int lastXP;
    private static boolean lose;
    private boolean hasPlayed;
    //game over sound
    private String [] option = {"menu"};

    public GameLoop(Game game) throws IOException {
        this.game = game;
    //    start();
   //     elapsedTime();

    }
    public void elapsedTime(){
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
    public void start(){
        timer = new Timer(2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (!KeyListener.getPauseGame()) {


                    //waves
                    wave.initWave1();
                    if (!wave1Created) {
                        Game.getSoundPlayer().playSoundEffect("src/Sound/wave.wav");

                        if(SettingsFrame.getChosenLevel()==0) {
                            for (Squarantine squarantine : wave.wave1EasySquarantine) {
                                Game.getSquarantine().add(squarantine);

                            }
                            for (Trigorath trigorath : wave.wave1EasyTrigorath) {
                                Game.getTrigoraths().add(trigorath);
                            }
                        }else        if(SettingsFrame.getChosenLevel()==1) {
                            for (Squarantine squarantine : wave.wave1MediumSquarantine) {
                                Game.getSquarantine().add(squarantine);
                            }
                            for (Trigorath trigorath : wave.wave1MediumTrigorath) {
                                Game.getTrigoraths().add(trigorath);
                            }
                        }else  if(SettingsFrame.getChosenLevel()==2) {
                            for (Squarantine squarantine : wave.wave1HardSquarantine) {
                                Game.getSquarantine().add(squarantine);
                            }
                            for (Trigorath trigorath : wave.wave1HardTrigorath) {
                                Game.getTrigoraths().add(trigorath);
                            }
                        }
                        wave1Created = true;
                        GameInfo.setCurrentWave(1);
                    }

                    try {
                        intersection = new Intersection(game.getGameFrame());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }


                    //if wave 1 ended create wave2
                    if (!wave2Created) {
                        for (Trigorath trigorath : Game.getTrigoraths()) {
                            if (trigorath.isDead()) {
                                deadT++;

                            }
                        }
                        for (Squarantine squarantine : Game.getSquarantine()) {
                            if (squarantine.isDead()) {
                                deadS++;

                            }
                        }
                    }
                    if (deadT >= Game.getTrigoraths().size() && deadS >= Game.getSquarantine().size()) {
                        timeBetweenWave++;
                        if (timeBetweenWave > 500){
                            Game.getSquarantine().clear();
                            Game.getTrigoraths().clear();
                            //    if(nextWave>=200) {
                            wave.initWave2();
                            if (!wave2Created && wave1Created) {
                                Game.getSoundPlayer().playSoundEffect("src/Sound/wave.wav");

                                if(SettingsFrame.getChosenLevel()==0) {
                                    for (Squarantine squarantine : wave.wave2EasySquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave2EasyTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
                                    }
                                }else        if(SettingsFrame.getChosenLevel()==1) {
                                    for (Squarantine squarantine : wave.wave2MediumSquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave2MediumTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
                                    }
                                }else  if(SettingsFrame.getChosenLevel()==2) {
                                    for (Squarantine squarantine : wave.wave2HardSquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave2HardTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
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
                        for (Trigorath trigorath : Game.getTrigoraths()) {
                            if (trigorath.isDead()) {
                                deadT++;

                            }
                        }
                        for (Squarantine squarantine : Game.getSquarantine()) {
                            if (squarantine.isDead()) {
                                deadS++;

                            }
                        }
                    }
                    if(deadT>= Game.getTrigoraths().size() && deadS>= Game.getSquarantine().size()) {
                        timeBetweenWave++;
                        if (timeBetweenWave > 500) {

                            Game.getSquarantine().clear();
                            Game.getTrigoraths().clear();

                            wave.initWave3();
                            if (!wave3Created && wave2Created) {
                                Game.getSoundPlayer().playSoundEffect("src/Sound/wave.wav");

                                if(SettingsFrame.getChosenLevel()==0) {
                                    for (Squarantine squarantine : Wave.wave3EasySquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave3EasyTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
                                    }
                                }else        if(SettingsFrame.getChosenLevel()==1) {
                                    for (Squarantine squarantine : wave.wave3MediumSquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave3MediumTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
                                    }
                                }else  if(SettingsFrame.getChosenLevel()==2) {
                                    for (Squarantine squarantine : wave.wave3HardSquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave3HardTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
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
                        for (Trigorath trigorath : Game.getTrigoraths()) {
                            if (trigorath.isDead()) {
                                deadT++;

                            }
                        }
                        for (Squarantine squarantine : Game.getSquarantine()) {
                            if (squarantine.isDead()) {
                                deadS++;

                            }
                        }
                    }
                    if(deadT>= Game.getTrigoraths().size() && deadS>= Game.getSquarantine().size()){
                        win = true;
                        if(Game.getEpsilon().getRadius()<GamePanel.getFRAME_WIDTH() && Game.getEpsilon().getRadius()<=GamePanel.getFRAME_HEIGHT()) {
                            Game.getEpsilon().setRadius(Game.getEpsilon().getRadius() + 1);

                        }

                    }
                    if(win && !hasPlayed){
                        Game.getSoundPlayer().playSoundEffect("src/Sound/gameover.wav");
                        hasPlayed = true;
                    }

                    if((GamePanel.getFRAME_HEIGHT()<=0 || GamePanel.getFRAME_WIDTH()<=0) && win){


                        int purchase = JOptionPane.showOptionDialog(GlassFrame.getINSTANCE(),
                                "You won! XP:"+ MyProject.getGameInfo().getXP(), null, JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE, null, option, option[0]);


                        if(purchase == 0){

                         //   GlassFrame.getINSTANCE().closeFrame();

                        new StarterMenu();
                            Game.getSoundPlayer().stopBackgroundMusic();

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
                                 ShopFrame.setHealItem(false);
                            ShopFrame.setBanishItem(false);
                            ShopFrame.setEmpowerItem(false);
                            lastUsed =0;
                            deadS = 0;
                            deadT = 0;
                            intersectionSide = null;
                            KeyListener.setPauseGame(true);
                            GamePanel.setFRAME_WIDTH(700);
                            GamePanel.setFRAME_HEIGHT(700);
                            gameDone = true;
                            lastXP = Game.getEpsilon().getXP();
                            KeyListener.setKeyPressedNumber(0);
                            KeyListener.setAbilityKeyPressed(false);
                            GameInfo.setCurrentAbility(null);
                            Game.getSquarantine().clear();
                            Game.getTrigoraths().clear();

                            Game.getEpsilon().getVertex().clear();
                            hasPlayed = false;
                            timer.stop();
                            elapsedTimer.stop();

                        }

                    }

                    if(Game.getEpsilon().getHP()<=0) lose = true;
                    if(lose && !hasPlayed){
                        Game.getSoundPlayer().playSoundEffect("src/Sound/gameover.wav");
                        GamePanel.setFRAME_WIDTH(0);
                        GamePanel.setFRAME_HEIGHT(0);
                        hasPlayed = true;
                    }
                    if( lose ) {

                        frameSize.shrink();
                        GamePanel.setFRAME_WIDTH(0);
                        GamePanel.setFRAME_HEIGHT(0);
                        int purchase = JOptionPane.showOptionDialog(GlassFrame.getINSTANCE(),
                                "You lost :( XP:" + MyProject.getGameInfo().getXP(), null, JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE, null, option, option[0]);

                        if (purchase == 0) {


                            new StarterMenu();
                            Game.getSoundPlayer().stopBackgroundMusic();
                            wave1Created = false;
                            wave2Created = false;
                            wave3Created = false;
                            countTime = 0;
                            seconds = 0;
                            minutes = 0;
                            banishTime = 0;
                            acesoTimer = 0;
                            win = false;
                          //  lose = false;
                                 ShopFrame.setHealItem(false);
                            ShopFrame.setBanishItem(false);
                            ShopFrame.setEmpowerItem(false);
                            canUseAbility = false;
                            lastUsed = 0;
                            deadS = 0;
                            deadT = 0;
                            intersectionSide = null;
                            KeyListener.setPauseGame(true);

                            gameDone = true;
                            lastXP = Game.getEpsilon().getXP();
                            KeyListener.setKeyPressedNumber(0);
                            KeyListener.setAbilityKeyPressed(false);
                            GameInfo.setCurrentAbility(null);
                            SkillTreeFrame.setCurrentXP(Game.getEpsilon().getXP());
                            Game.getEpsilon().getVertex().clear();
                            Game.getSquarantine().clear();
                            Game.getTrigoraths().clear();
                            hasPlayed = false;
                            lose = false;
                            timer.stop();
                            elapsedTimer.stop();



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

                    for(int i = 0; i< Game.getSquarantine().size(); i++) {
                        Squarantine squarantine = Game.getSquarantine().get(i);
                        squarantine.move();

                    }
                    for (int i = 0; i < Game.getTrigoraths().size(); i++) {
                        Trigorath trigorath = Game.getTrigoraths().get(i);
                        trigorath.move();

                    }
                    Game.getEpsilon().move();
                    for(Vertex vertex : Game.getEpsilon().getVertex()){
                        vertex.move();
                    }

                    intersection.shotIntersectsSquarantine();
                    intersection.shotIntersectsTrigorath();
                    //    intersection.shotIntersectsSquarantine();
                    intersection.epsilonIntersectsCollectible();
                    //      intersection.epsilonIntersectEnemy();
                    intersection.enemyIntersection();
                    for(Trigorath trigorath : Game.getTrigoraths()){
                        Polygon trigorath2 = new Polygon(trigorath.getxPoints(),trigorath.getyPoints(),3);
                        for(Squarantine squarantine : Game.getSquarantine()){
                            Polygon squarantine2 = new Polygon(squarantine.getxPoints(),squarantine.getyPoints(),4);
                            intersection.getIntersectionPoint(trigorath2,squarantine2);
                        }
                    }




                    try {
                        frameSize = new FrameSize(game.getGameFrame());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //shrinkage starts after 10 seconds
                    if (countTime >= 700 ) {

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

                            if (shotGun.getExpansion() < 20) {
                                frameSize.expand(intersectionSide);
                                shotGun.setExpansion(shotGun.getExpansion() + 1);

                                IntersectionPoint point = new IntersectionPoint(new Point2D.Double(shotGun.getX(), shotGun.getY()),10);
                                Intersection.getIntersectionPoints().add(point);
                                shotGun.setVisible(false);
                            }
                        }
                    }




                    //if trigorath is dead show its collectibles for 10 seconds
                    for (Trigorath trigorath : Game.getTrigoraths()) {
                        if (trigorath.isShowCollectibles()) {
                            trigorath.setTimer(trigorath.getTimer() + 1);
                            if (trigorath.getTimer() > 300) {
                                trigorath.setShowCollectibles(false);
                            }
                        }
                    }

                    countTime++;

                    for(int i=0; i<Intersection.getIntersectionPoints().size();i++){
                        IntersectionPoint point = Intersection.getIntersectionPoints().get(i);
                        point.setElapsedTime(point.getElapsedTime()+1);
                        if(point.getElapsedTime()>=point.getTime()){
                            Intersection.getIntersectionPoints().remove(point);
                        }
                    }

                    if(ShopFrame.isBanishItem()){
                        banishTime++;
                    }
                    if(banishTime>=40){
                        banishTime = 0;
                        ShopFrame.setBanishItem(false);
                    }
                    if(ShopFrame.isHealItem()){
                        Game.getEpsilon().setHP(Game.getEpsilon().getHP()+10);
                        ShopFrame.setHealItem(false);
                    }
                    for(Trigorath trigorath: Game.getTrigoraths() ) {
                        if (!trigorath.isDead()) {
                            VTCollision = false;
                            Epsilon epsilon = Game.getEpsilon();
                            Polygon trigorath2 = new Polygon(trigorath.getxPoints(), trigorath.getyPoints(), 3);
                            for (Vertex vertex : epsilon.getVertex()) {
                                if (intersection.checkCollision((int) vertex.getxCenter(), (int) vertex.getyCenter(), vertex.getRadius(), trigorath2)) {
                                    VTCollision = true;
                                    trigorath.decreaseHP(true);
                                    IntersectionPoint point = new IntersectionPoint(new Point2D.Double(trigorath.getX(), trigorath.getY()),10);
                                    Intersection.getIntersectionPoints().add(point);
                                }
                            }
                        }
                    }
                    for(Trigorath trigorath3: Game.getTrigoraths() ) {
                        if (!trigorath3.isDead()) {

                            Epsilon epsilon = Game.getEpsilon();
                            Polygon trigorath2 = new Polygon(trigorath3.getxPoints(), trigorath3.getyPoints(), 3);
                            //   if (!VTCollision) {
                            if (intersection.checkCollision(epsilon.getxCenter(), epsilon.getyCenter(), epsilon.getRadius(), trigorath2)) {
                                IntersectionPoint point = new IntersectionPoint(new Point2D.Double(trigorath3.getX(), trigorath3.getY()),10);
                                Intersection.getIntersectionPoints().add(point);
                                epsilon.decreaseHP(EnemyType.Trigorath);

                            }
                        }
                    }


                    for(Squarantine squarantine: Game.getSquarantine() ) {
                        if (!squarantine.isDead()) {
                            VSCollission = false;
                            Epsilon epsilon = Game.getEpsilon();
                            Polygon squarantine2 = new Polygon(squarantine.getxPoints(), squarantine.getyPoints(), 4);
                            for (Vertex vertex : epsilon.getVertex()) {
                                if (intersection.checkCollision((int) vertex.getxCenter(), (int) vertex.getyCenter(), vertex.getRadius(), squarantine2)) {

                                    VSCollission = true;
                                    squarantine.decreaseHP(true);
                                    IntersectionPoint point = new IntersectionPoint(new Point2D.Double(squarantine.getX(), squarantine.getY()),10);
                                    Intersection.getIntersectionPoints().add(point);

                                }
                            }
                        }
                    }
                    for(Squarantine squarantine3: Game.getSquarantine() ){
                        if(!squarantine3.isDead()) {
                            VSCollission = false;
                            Epsilon epsilon = Game.getEpsilon();
                            Polygon squarantine2 = new Polygon(squarantine3.getxPoints(), squarantine3.getyPoints(), 4);
                            //       if (!VSCollission) {
                            if (intersection.checkCollision(epsilon.getxCenter(), epsilon.getyCenter(), epsilon.getRadius(), squarantine2)) {
                                IntersectionPoint point = new IntersectionPoint(new Point2D.Double(squarantine3.getX(), squarantine3.getY()),10);
                                Intersection.getIntersectionPoints().add(point);
                                epsilon.decreaseHP(EnemyType.Squarantine);
                            }
                        }

                    }
                    if(canUseAbility){

                        if(GameInfo.getCurrentAbility().equals(CurrentAbility.Aceso)){
                            currentTime = System.currentTimeMillis();

                            if((currentTime - acesoTimer)/(1000)>=1) {
                                for(int i=1;i<=KeyListener.getKeyPressedNumber();i++) {
                                    if (Game.getEpsilon().getHP() < 100) {
                                        Game.getEpsilon().setHP(Game.getEpsilon().getHP() + 1);
                                        acesoTimer = currentTime;
                                    }
                                }

                            }

                        }else if(GameInfo.getCurrentAbility().equals(CurrentAbility.Ares)){
                            Trigorath.setHPDecrement(Trigorath.getHPDecrement()+2*KeyListener.getKeyPressedNumber());
                            Squarantine.setHPDecrement(Trigorath.getHPDecrement()+2*KeyListener.getKeyPressedNumber());
                            Trigorath.setHPDecrement2(Trigorath.getHPDecrement2()+2*KeyListener.getKeyPressedNumber());
                            Squarantine.setHPDecrement2(Trigorath.getHPDecrement2()+2*KeyListener.getKeyPressedNumber());
                            canUseAbility = false;
                        }else if(GameInfo.getCurrentAbility().equals(CurrentAbility.Proteus)){

                            Game.getEpsilon().setVertexNumber(Game.getEpsilon().getVertexNumber()+1);
                            Game.getEpsilon().addVertex();


                            canUseAbility = false;
                        }



                    }

                    if(KeyListener.isAbilityKeyPressed()){

                        currentTime = System.currentTimeMillis();

                        if( (currentTime - lastUsed)/(  60*1000)>=5){
                            if(MyProject.getGameInfo().getXP()>=100) {
                                canUseAbility = true;
                                MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP()-100);

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

    public static int getSeconds() {
        return seconds;
    }

    public static int getMinutes() {
        return minutes;
    }

    public static boolean isWin() {
        return win;
    }

    public static boolean isGameDone() {
        return gameDone;
    }

    public static boolean isLose() {
        return lose;
    }

}
