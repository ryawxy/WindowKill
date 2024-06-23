package Model;

import Controller.*;
import Model.omenoct.Omenoct;
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
    private int index= Game.getShots().size()-3;
    //balls shooting one by on in empower mode
    private int time=3;
    //amount of time since shooting the next fire
    private int empowerTime;
    //amount of time that has passed since empower item is activated
    private int banishTime;
    //amount of time the has passed since banish item is activated
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
    private SkillTreeController skillTreeController = new SkillTreeController();
    private ShopController shopController = new ShopController();

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
                                Game.getEnemies().add(squarantine);

                            }
                            for (Trigorath trigorath : wave.wave1EasyTrigorath) {
                                Game.getTrigoraths().add(trigorath);
                                Game.getEnemies().add(trigorath);
                            }
                            for(Necropick necropick : wave.wave1EasyNecropicks){
                                Game.getNecropicks().add(necropick);
                                Game.getEnemies().add(necropick);
                            }
                            for(Omenoct omenoct : wave.wave1EasyOmenoct){
                                Game.getOmenocts().add(omenoct);
                                Game.getEnemies().add(omenoct);

                            }
                        }else        if(SettingsFrame.getChosenLevel()==1) {
                            for (Squarantine squarantine : wave.wave1MediumSquarantine) {
                                Game.getSquarantine().add(squarantine);
                                Game.getEnemies().add(squarantine);
                            }
                            for (Trigorath trigorath : wave.wave1MediumTrigorath) {
                                Game.getTrigoraths().add(trigorath);
                                Game.getEnemies().add(trigorath);
                            }
                        }else  if(SettingsFrame.getChosenLevel()==2) {
                            for (Squarantine squarantine : wave.wave1HardSquarantine) {
                                Game.getSquarantine().add(squarantine);
                                Game.getEnemies().add(squarantine);
                            }
                            for (Trigorath trigorath : wave.wave1HardTrigorath) {
                                Game.getTrigoraths().add(trigorath);
                                Game.getEnemies().add(trigorath);
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
                            Game.getEnemies().clear();
                            //    if(nextWave>=200) {
                            wave.initWave2();
                            if (!wave2Created && wave1Created) {
                                Game.getSoundPlayer().playSoundEffect("src/Sound/wave.wav");

                                if(SettingsFrame.getChosenLevel()==0) {
                                    for (Squarantine squarantine : wave.wave2EasySquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                        Game.getEnemies().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave2EasyTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
                                        Game.getEnemies().add(trigorath);
                                    }
                                }else        if(SettingsFrame.getChosenLevel()==1) {
                                    for (Squarantine squarantine : wave.wave2MediumSquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                        Game.getEnemies().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave2MediumTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
                                        Game.getEnemies().add(trigorath);
                                    }
                                }else  if(SettingsFrame.getChosenLevel()==2) {
                                    for (Squarantine squarantine : wave.wave2HardSquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                        Game.getEnemies().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave2HardTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
                                        Game.getEnemies().add(trigorath);
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
                            Game.getEnemies().clear();

                            wave.initWave3();
                            if (!wave3Created && wave2Created) {
                                Game.getSoundPlayer().playSoundEffect("src/Sound/wave.wav");

                                if(SettingsFrame.getChosenLevel()==0) {
                                    for (Squarantine squarantine : Wave.wave3EasySquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                        Game.getEnemies().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave3EasyTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
                                        Game.getEnemies().add(trigorath);
                                    }
                                }else        if(SettingsFrame.getChosenLevel()==1) {
                                    for (Squarantine squarantine : wave.wave3MediumSquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                        Game.getEnemies().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave3MediumTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
                                        Game.getEnemies().add(trigorath);
                                    }
                                }else  if(SettingsFrame.getChosenLevel()==2) {
                                    for (Squarantine squarantine : wave.wave3HardSquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                        Game.getEnemies().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave3HardTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
                                        Game.getEnemies().add(trigorath);
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
                            win = false;
                                 ShopFrame.setHealItem(false);
                            ShopFrame.setBanishItem(false);
                            ShopFrame.setEmpowerItem(false);
                            skillTreeController.setCanUseAbility(false);
                            skillTreeController.setCurrentTime(0);
                            skillTreeController.setLastUsed(0);
                            skillTreeController.setAcesoTimer(0);
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
                            GameInfo.getCurrentAbility().clear();
                            Game.getSquarantine().clear();
                            Game.getTrigoraths().clear();
                            Game.getEnemies().clear();

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
                            skillTreeController.setAcesoTimer(0);
                            win = false;
                                 ShopFrame.setHealItem(false);
                            ShopFrame.setBanishItem(false);
                            ShopFrame.setEmpowerItem(false);
                            skillTreeController.setCanUseAbility(false);
                            skillTreeController.setCurrentTime(0);
                            skillTreeController.setLastUsed(0);
                            deadS = 0;
                            deadT = 0;
                            intersectionSide = null;
                            KeyListener.setPauseGame(true);

                            gameDone = true;
                            lastXP = Game.getEpsilon().getXP();
                            KeyListener.setKeyPressedNumber(0);
                            KeyListener.setAbilityKeyPressed(false);
                            GameInfo.getCurrentAbility().clear();
                            SkillTreeFrame.setCurrentXP(Game.getEpsilon().getXP());
                            Game.getEpsilon().getVertex().clear();
                            Game.getSquarantine().clear();
                            Game.getTrigoraths().clear();
                            Game.getEnemies().clear();
                            hasPlayed = false;
                            lose = false;
                            timer.stop();
                            elapsedTimer.stop();

                        }
                    }



                    if (!ShopFrame.isEmpowerItem()) {
                        for (ShotGun shotGun : Game.getShots()) {
                            shotGun.move();
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
                    for(Cerberus cerberus : Game.getCerberuses()) cerberus.move();

                    intersection.shotIntersectsEntity();
                    intersection.epsilonIntersectsCollectible();
                    intersection.enemyIntersection();
                    intersection.getIntersectionPoint();
                    intersection.vertexIntersectsNecropick();
                    intersection.vertexIntersectsOmenoct();




                    try {
                        frameSize = new FrameSize(game.getGameFrame());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //shrinkage starts after 10 seconds
                    if (countTime >= 700 ) {

                      //  frameSize.shrink();
                    }


                    //check if a shot intersects with frame edges
                    // if so expansion starts from that side for a second
                    // and impact mechanism activates from that point

                    for(ShotGun shotGun : Game.getShots()){
                        if(shotGun.isVisible()) intersectionSide = intersection.shotIntersectsFrame(shotGun);
                        if (intersectionSide != null) {

                            if (shotGun.getExpansion() < 20) {
                                frameSize.expand(intersectionSide);
                                shotGun.setExpansion(shotGun.getExpansion() + 1);

                                IntersectionPoint point = new IntersectionPoint(new Point2D.Double(shotGun.getX(), shotGun.getY()),10,false,false,null,shotGun);
                                Intersection.getIntersectionPoints().add(point);
                                shotGun.setVisible(false);
                            }
                        }
                    }

                    skillTreeController.ableToUseAbility();
                    skillTreeController.activate();


                    for(GameObjects enemy : Game.getEnemies()) enemy.invisibleCollectible();

                    countTime++;

                    for(int i=0; i<Intersection.getIntersectionPoints().size();i++){
                        IntersectionPoint point = Intersection.getIntersectionPoints().get(i);
                        point.setElapsedTime(point.getElapsedTime()+1);
                        if(point.getElapsedTime()>=point.getTime()){
                            Intersection.getIntersectionPoints().remove(point);
                        }
                    }
                    shopController.activate();
                    ShopController.canUSe();

                    for(Trigorath trigorath: Game.getTrigoraths() ) {
                        if (!trigorath.isDead()) {
                            VTCollision = false;
                            Epsilon epsilon = Game.getEpsilon();
                            Polygon trigorath2 = new Polygon(trigorath.getxPoints(), trigorath.getyPoints(), 3);
                            for (Vertex vertex : epsilon.getVertex()) {
                                if (intersection.checkCollision((int) vertex.getxCenter(), (int) vertex.getyCenter(), vertex.getRadius(), trigorath2)) {
                                    VTCollision = true;
                                    trigorath.decreaseHP(5);
                                    IntersectionPoint point = new IntersectionPoint(new Point2D.Double(trigorath.getX(), trigorath.getY()),10,false,true,vertex,trigorath);
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
                                IntersectionPoint point = new IntersectionPoint(new Point2D.Double(trigorath3.getX(), trigorath3.getY()),30,true,false,trigorath3,epsilon);
                                Intersection.getIntersectionPoints().add(point);
                                boolean melee = point.isMeleeAttack();
                                epsilon.decreaseHP(EnemyType.Trigorath,melee);

                            }
                        }
                    }
                    for(Omenoct omenoct: Game.getOmenocts() ) {
                        if (!omenoct.isDead()) {

                            Epsilon epsilon = Game.getEpsilon();
                            Polygon trigorath2 = new Polygon(omenoct.getxPoints(), omenoct.getyPoints(), 6);
                            //   if (!VTCollision) {
                            if (intersection.checkCollision(epsilon.getxCenter(), epsilon.getyCenter(), epsilon.getRadius(), trigorath2)) {
                                IntersectionPoint point = new IntersectionPoint(new Point2D.Double(omenoct.getX(), omenoct.getY()),30,true,false,omenoct,epsilon);
                                Intersection.getIntersectionPoints().add(point);
                                boolean melee = point.isMeleeAttack();
                                epsilon.decreaseHP(EnemyType.Omenoct,melee);

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
                                    squarantine.decreaseHP(10);
                                    IntersectionPoint point = new IntersectionPoint(new Point2D.Double(squarantine.getX(), squarantine.getY()),10,false,true,vertex,squarantine);
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
                                IntersectionPoint point = new IntersectionPoint(new Point2D.Double(squarantine3.getX(), squarantine3.getY()),10,true,false,epsilon,squarantine3);
                                Intersection.getIntersectionPoints().add(point);
                                boolean melee = point.isMeleeAttack();
                                epsilon.decreaseHP(EnemyType.Squarantine,melee);
                            }
                        }

                    }



                    for(Necropick necropick : Game.getNecropicks()){
                        necropick.visible();
                        necropick.move();
                        necropick.shoot();
                    }

                    for(Omenoct omenoct : Game.getOmenocts()){
                        omenoct.chooseSide();
                        omenoct.move();
                        omenoct.shoot();
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
