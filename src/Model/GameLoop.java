package Model;

import Controller.*;
import Model.entity.*;
import Model.entity.blackOrb.BlackOrbArray;
import Model.enums.ArchmireType;
import Model.enums.BarricadosType;
import Model.enums.Direction;
import view.*;
import view.entityViews.barricados.BarricadosFrame;
import view.entityViews.blackOrb.BlackOrbFrame;
import myproject.MyProject;
import view.entityViews.wyrm.WyrmFrame;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.io.IOException;


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
                        for(Archmire archmire : wave.wave1EasyArchmire){
                            Game.getArchmires().add(archmire);
                            Game.getEnemies().add(archmire);

                        }
                    //    BlackOrbFrame blackOrbFrame = new BlackOrbFrame(200,200);
                   //         BlackOrbArray.createBlackOrbArray(250,150);
              //          BlackOrbArray.createInvisibleFrame();
  //                        WyrmFrame wyrmFrame =   new WyrmFrame(510,300);
 //                       WyrmFrame wyrmFrame1 =   new WyrmFrame(710,600);



//                            Omenoct omenoct = new Omenoct(200,500);
//                            Game.getEnemies().add(omenoct);
//                            Game.getOmenocts().add(omenoct);
//                            Necropick necropick = new Necropick(200,200);
//                            Game.getEnemies().add(necropick);
//                            Game.getNecropicks().add(necropick);

//                        Archmire archmire = new Archmire(500,200, ArchmireType.LARGE);
//                        Game.getEnemies().add(archmire);
//                        Game.getArchmires().add(archmire);
//
                        BarricadosFrame barricadosFrame = new BarricadosFrame(100,300, BarricadosType.T2);


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
                    objectsIntersection = new ObjectsIntersection(Game.getGameFrame());
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
                    if(Game.getEpsilon().getRadius()< GamePanel.getFRAME_WIDTH() && Game.getEpsilon().getRadius()<= GamePanel.getFRAME_HEIGHT()) {
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
                if (countTime >= 50 ) {

            //        frameSize.shrink();
                }


                //check if a shot intersects with frame edges
                // if so expansion starts from that side for a second
                // and impact mechanism activates from that point

                for(ShotGun shotGun : Game.getEpsilonShots()){

                    if(shotGun.isVisible()) intersectionSide = objectsIntersection.shotIntersectsFrame(shotGun);
                    if (intersectionSide != null) {

                        if (shotGun.getExpansion() < 15) {
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

                for(JFrame frame : Game.getFrames()) {
                    if (!frame.equals(Game.getEpsilon().getLocalFrame())) {
                        frameIntersection.changeLocalFrame(frame, Game.getEpsilon());
                    //    System.out.println(Game.getEpsilon().getLocalFrames().size());

                    }
                }
                for(ShotGun shotGun : Game.getEpsilonShots()) {
                    for (JFrame frame : Game.getFrames()) {
                        if (!frame.equals(Game.getEpsilon().getLocalFrame())) {
                            frameIntersection.changeLocalFrame(frame, shotGun);

                        }
                    }
                }
              for(Vertex vertex : Game.getEpsilon().getVertex()){
                  for(JFrame frame : Game.getFrames()){
                      frameIntersection.changeLocalFrame(frame,vertex);
                  }
              }

                for(JFrame frame : Game.getFrames()) frame.getContentPane().repaint();

                for (BlackOrbFrame blackOrbFrame : Game.getBlackOrbFrames()) blackOrbFrame.getContentPane().repaint();

                for(WyrmFrame wyrmFrame : Game.getWyrmFrames()){
                    wyrmFrame.getWyrm().move();

                    for(ShotGun shotGun : wyrmFrame.getWyrm().getShots()){
                        for(JFrame frame : Game.getFrames()){
                            if(!frame.equals(shotGun.getLocalFrame())) frameIntersection.changeLocalFrame(frame,shotGun);
                        }
                    }
                    for(Collectible collectible : wyrmFrame.getWyrm().getCollectibles()){
                        for(JFrame frame : Game.getFrames()){
                            if(!frame.equals(collectible.getLocalFrame())) frameIntersection.changeLocalFrame(frame,collectible);
                        }
                    }
                    wyrmFrame.getWyrm().shot();

                }
//                for(WyrmFrame wyrmFrame : Game.getWyrmFrames()){
//                    for(ShotGun shotGun : wyrmFrame.getWyrm().getShots()){
//                        shotGun.move();
//                    }
//                }

                for(GameObjects enemy : Game.getEnemies()){
                    for(JFrame frame : Game.getFrames()){
                        if(!frame.equals(enemy.getLocalFrame())){
                            frameIntersection.changeLocalFrame(frame,enemy);
                        }
                    }
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
