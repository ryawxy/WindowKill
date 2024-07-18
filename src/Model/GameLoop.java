package Model;

import Controller.*;
import Model.entity.*;
import Model.entity.blackOrb.BlackOrb;
import Model.enums.Direction;
import view.*;
import myproject.MyProject;
import view.entityViews.wyrm.WyrmFrame;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Random;


public class GameLoop {

    private  Timer timer;
    private Model.FrameSize frameSize;
    private Model.ObjectsIntersection objectsIntersection;
    private Direction intersectionSide;
    private int countTime;
    // amount of time that has passed since the game has started
    private int banishTime;
    //amount of time the has passed since banish item is activated
    private Timer elapsedTimer;
    private static int seconds;
    private static int minutes;
    private boolean wave1Created;
    private boolean wave2Created;
    private boolean wave3Created;

    private static boolean gameDone;
    private final Wave wave = new Wave();
    int deadS;
    int deadT;
    int timeBetweenWave;
    private static boolean win;
    private static boolean lose;
    private boolean hasPlayed;
    //game over sound
    private final String [] option = {"menu"};
    private final SkillTreeController skillTreeController = new SkillTreeController();
    private final ShopController shopController = new ShopController();
    private final FrameIntersection frameIntersection = new FrameIntersection();


    public GameLoop() throws IOException {
    }
    public void elapsedTime(){
        elapsedTimer = new Timer(1000, e -> {
            seconds++;
            if(seconds>=59){
                seconds = 0;
                minutes++;
            }
        });
        elapsedTimer.start();
    }
    public void start(){
        timer = new Timer(1, e -> {

            if (!KeyListener.getPauseGame()) {


//
//                if(!wave1Created) {
//                    WyrmFrame wyrmFrame1 = new WyrmFrame(100, 100);
//                    Necropick necropick1 = new Necropick(300, 300);
//                    Game.getEnemies().add(necropick1);
//                    Game.getNecropicks().add(necropick1);
//                    wave1Created = true;
//                }
                wave.initWave1();
                wave.initWave2();
                wave.initWave3();
                wave.initWave4();
                wave.initWave5();
                try {
                    objectsIntersection = new ObjectsIntersection(Game.getGameFrame());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
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
                      //  lastXP = Game.getEpsilon().getXP();
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

                    frameSize.normalShrinkage();
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
//                            lastXP = Game.getEpsilon().getXP();
                        KeyListener.setKeyPressedNumber(0);
                        KeyListener.setAbilityKeyPressed(false);
                        GameInfo.getCurrentAbility().clear();
//                            SkillTreeFrame.setCurrentXP(Game.getEpsilon().getXP());
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
                    for (ShotGun shotGun : Game.getEpsilonShots()) {
                        shotGun.move();
                    }
                }

                for(int i = 0; i< Game.getSquarantine().size(); i++) {
                    Squarantine squarantine = Game.getSquarantine().get(i);
                    squarantine.move();

                }
                for (int i = 0; i < Game.getTrigoraths().size(); i++) {
                    Trigorath trigorath = Game.getTrigoraths().get(i);
             //      trigorath.move();

                }
                Game.getEpsilon().move();

                for(Vertex vertex : Game.getEpsilon().getVertex()){
                    vertex.move();
                }
                for(Cerberus cerberus : Game.getCerberuses()) cerberus.move();

                objectsIntersection.shotIntersectsEntity();
                objectsIntersection.epsilonIntersectsCollectible();
                objectsIntersection.enemyIntersection();
                objectsIntersection.vertexIntersectsNecropick();
                objectsIntersection.vertexIntersectsOmenoct();
               objectsIntersection.AOEIntersection();
               objectsIntersection.epsilonIntersectsEnemy();
               objectsIntersection.vertexIntersectsOrb();
               objectsIntersection.wyrmInterectsEntity();




                try {
                    frameSize = new FrameSize(Game.getGameFrame());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                //shrinkage starts after 10 seconds
                if (countTime >= 200 ) {

        //            frameSize.normalShrinkage();
                }


                //check if a shot intersects with frame edges
                // if so expansion starts from that side for a second
                // and impact mechanism activates from that point

                for(ShotGun shotGun : Game.getEpsilonShots()){

                    if(shotGun.isVisible()) intersectionSide = objectsIntersection.shotIntersectsFrame(shotGun);
                    if (intersectionSide != null) {

                        if (shotGun.getExpansion() < 5) {
                            frameSize.expand(intersectionSide);
                            shotGun.setExpansion(shotGun.getExpansion() + 1);

                            IntersectionPoint point = new IntersectionPoint(new Point2D.Double(shotGun.getX(), shotGun.getY()),5,false,false,null,shotGun);
                            ObjectsIntersection.getIntersectionPoints().add(point);
                            shotGun.setVisible(false);
                        }
                    }
                }


                skillTreeController.ableToUseAbility();
                skillTreeController.activate();


                for(GameObjects enemy : Game.getEnemies()) enemy.invisibleCollectible();

                countTime++;

                for(int i = 0; i< ObjectsIntersection.getIntersectionPoints().size(); i++){
                    IntersectionPoint point = ObjectsIntersection.getIntersectionPoints().get(i);
                    point.setElapsedTime(point.getElapsedTime()+1);
                    if(point.getElapsedTime()>=point.getTime()){
                        ObjectsIntersection.getIntersectionPoints().remove(point);
                    }
                }
                shopController.activate();
                ShopController.canUSe();



                for(Necropick necropick : Game.getNecropicks()){
                    necropick.visible();
                    necropick.shoot();

//                        for(ShotGun shotGun : necropick.getShots()){
//                            if(objectsIntersection.shotIntersectsFrame(shotGun)!=null) shotGun.setVisible(false);
//                        }
                }

                for(Omenoct omenoct : Game.getOmenocts()){
                    omenoct.changeFrame();
                    omenoct.move();
                    omenoct.shoot();

                }

                for(BlackOrb blackOrb : Game.getBlackOrbs()) {
                    if (blackOrb.getSpot() == null) {
                        Random random = new Random();
                        int index = random.nextInt(Game.getBlackOrbs().size());
                        Game.getBlackOrbs().get(index).avalanche();
                    }
                }
                for(Archmire archmire : Game.getArchmires()){

//                    if(archmire.isDead() && archmire.isGhost()){
//                        archmire.createGhostArchmire();
//                        archmire.setGhost(false);
//                    }
                    archmire.move();
                    archmire.updateFootprint();
                    for(Footprint footprint : archmire.getFootprint()){
                        for(JFrame frame : Game.getFrames()){
                            if(!frame.equals(footprint.getLocalFrame())) frameIntersection.changeLocalFrame(frame,footprint);
                        }
                    }

                }
                for(GameObjects enemy : Game.getEnemies()){
                    for(ShotGun shotGun : enemy.getShots()){
                        for(JFrame frame : Game.getFrames()){
                            frameIntersection.changeLocalFrame(frame,shotGun);
                        }
                    }
                }

                for(JFrame frame : Game.getFrames()) {
                    if (!frame.equals(Game.getEpsilon().getLocalFrame())) {
                        frameIntersection.changeLocalFrame(frame, Game.getEpsilon());
                    //    System.out.println(Game.getEpsilon().getLocalFrames().size());

                    }
                }
                for(ShotGun shotGun : Game.getEpsilonShots()) {
                    for (JFrame frame : Game.getFrames()) {
                 //       if (!frame.equals(shotGun.getLocalFrame())) {
                            frameIntersection.changeLocalFrame(frame, shotGun);

//                        }
                    }
                }
              for(Vertex vertex : Game.getEpsilon().getVertex()){
                  for(JFrame frame : Game.getFrames()){
                      frameIntersection.changeLocalFrame(frame,vertex);
                  }
              }

                for(JFrame frame : Game.getFrames()) frame.getContentPane().repaint();

                for(WyrmFrame wyrmFrame : Game.getWyrmFrames()){
                    wyrmFrame.getWyrm().move();


                    for(Collectible collectible : wyrmFrame.getWyrm().getCollectibles()){
                        for(JFrame frame : Game.getFrames()){
                            if(!frame.equals(collectible.getLocalFrame())) frameIntersection.changeLocalFrame(frame,collectible);
                        }
                    }
                    wyrmFrame.getWyrm().shot();

                }
//                for(int i=0; i<Game.getArchmires().size();i++){
//                    Archmire archmire = Game.getArchmires().get(i);
//                    if(!archmire.isMitosis()) archmire.createGhostArchmire();
//                }


                for(GameObjects enemy : Game.getEnemies()){
                    for(JFrame frame : Game.getFrames()){
                        if(!frame.equals(enemy.getLocalFrame())){
                            frameIntersection.changeLocalFrame(frame,enemy);
                        }
                    }
                }
        //        BossAttack.projectile();
          //      BossAttack.vomit();
          //      BossAttack.powerPunch();
        //        BossAttack.rapidFire();
        //        BossAttack.annihilator();
        //        BossAttack.quake();
        //        BossAttack.slap();







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
