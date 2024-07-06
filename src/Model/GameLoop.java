package Model;

import Controller.*;
import Model.entity.*;
import Model.enums.Direction;

import View.entityViews.barricados.BarricadosFrame;
import View.entityViews.blackOrb.BlackOrbFrame;
import myproject.MyProject;
import View.entityViews.wyrm.WyrmFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;


public class GameLoop {

    private Controller.Game game;
    private  Timer timer;
    private Model.FrameSize frameSize;
    private Model.ObjectsIntersection objectsIntersection;
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
    private FrameIntersection frameIntersection = new FrameIntersection();


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
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!KeyListener.getPauseGame()) {


                    //waves
                    wave.initWave1();
                    if (!wave1Created) {
                        Game.getSoundPlayer().playSoundEffect("src/Sound/wave.wav");

                        if(View.SettingsFrame.getChosenLevel()==0) {
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
//                            BlackOrbArray.createBlackOrbArray(200,150);
//                            BlackOrbArray.createInvisibleFrame();
                          WyrmFrame wyrmFrame =   new WyrmFrame(310,300);
                            Game.getWyrmFrames().add(wyrmFrame);
                            Game.getFrames().add(wyrmFrame);
                            new KeyListener((JPanel) wyrmFrame.getContentPane());
//
//                            for(Barricados barricados : wave.wave1EasyBarricados){
//                            BarricadosFrame barricadosFrame = new BarricadosFrame(300,300, BarricadosType.T2);
//                    //        BarricadosFrame barricadosFrame1 = new BarricadosFrame(500,30, BarricadosType.T2);
//                       //         BarricadosFrame barricadosFrame2 = new BarricadosFrame(500,600, BarricadosType.T2);
//                        //        BarricadosFrame barricadosFrame3 = new BarricadosFrame(1000,300, BarricadosType.T2);
//                             Game.getBarricadosFrames().add(barricadosFrame);
//                 //           Game.getBarricadosFrames().add(barricadosFrame1);
//                       //         Game.getBarricadosFrames().add(barricadosFrame2);
//                        //        Game.getBarricadosFrames().add(barricadosFrame3);
//                            Game.getFrames().add(barricadosFrame);
//                //           Game.getFrames().add(barricadosFrame1);
//                    //            Game.getFrames().add(barricadosFrame2);
//                    //            Game.getFrames().add(barricadosFrame3);
//
//                               Game.getBarricados().add(barricados);
//                //                Game.getEnemies().add(barricados);
//
//                           }
                        }else        if(View.SettingsFrame.getChosenLevel()==1) {
                            for (Squarantine squarantine : wave.wave1MediumSquarantine) {
                                Game.getSquarantine().add(squarantine);
                                Game.getEnemies().add(squarantine);
                            }
                            for (Trigorath trigorath : wave.wave1MediumTrigorath) {
                                Game.getTrigoraths().add(trigorath);
                                Game.getEnemies().add(trigorath);
                            }
                        }else  if(View.SettingsFrame.getChosenLevel()==2) {
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

                                if(View.SettingsFrame.getChosenLevel()==0) {
                                    for (Squarantine squarantine : wave.wave2EasySquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                        Game.getEnemies().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave2EasyTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
                                        Game.getEnemies().add(trigorath);
                                    }
                                }else        if(View.SettingsFrame.getChosenLevel()==1) {
                                    for (Squarantine squarantine : wave.wave2MediumSquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                        Game.getEnemies().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave2MediumTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
                                        Game.getEnemies().add(trigorath);
                                    }
                                }else  if(View.SettingsFrame.getChosenLevel()==2) {
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

                                if(View.SettingsFrame.getChosenLevel()==0) {
                                    for (Squarantine squarantine : Wave.wave3EasySquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                        Game.getEnemies().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave3EasyTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
                                        Game.getEnemies().add(trigorath);
                                    }
                                }else        if(View.SettingsFrame.getChosenLevel()==1) {
                                    for (Squarantine squarantine : wave.wave3MediumSquarantine) {
                                        Game.getSquarantine().add(squarantine);
                                        Game.getEnemies().add(squarantine);
                                    }
                                    for (Trigorath trigorath : wave.wave3MediumTrigorath) {
                                        Game.getTrigoraths().add(trigorath);
                                        Game.getEnemies().add(trigorath);
                                    }
                                }else  if(View.SettingsFrame.getChosenLevel()==2) {
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
                        if(Game.getEpsilon().getRadius()< View.GamePanel.getFRAME_WIDTH() && Game.getEpsilon().getRadius()<= View.GamePanel.getFRAME_HEIGHT()) {
                            Game.getEpsilon().setRadius(Game.getEpsilon().getRadius() + 1);

                        }

                    }
                    if(win && !hasPlayed){
                        Game.getSoundPlayer().playSoundEffect("src/Sound/gameover.wav");
                        hasPlayed = true;
                    }

                    if((View.GamePanel.getFRAME_HEIGHT()<=0 || View.GamePanel.getFRAME_WIDTH()<=0) && win){


                        int purchase = JOptionPane.showOptionDialog(View.GlassFrame.getINSTANCE(),
                                "You won! XP:"+ MyProject.getGameInfo().getXP(), null, JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE, null, option, option[0]);


                        if(purchase == 0){

                         //   GlassFrame.getINSTANCE().closeFrame();

                        new View.StarterMenu();
                            Game.getSoundPlayer().stopBackgroundMusic();

                            wave1Created =  false;
                            wave2Created = false;
                            wave3Created = false;
                            countTime = 0;
                            seconds = 0;
                            minutes = 0;
                            banishTime = 0;
                            win = false;
                                 View.ShopFrame.setHealItem(false);
                            View.ShopFrame.setBanishItem(false);
                            View.ShopFrame.setEmpowerItem(false);
                            skillTreeController.setCanUseAbility(false);
                            skillTreeController.setCurrentTime(0);
                            skillTreeController.setLastUsed(0);
                            skillTreeController.setAcesoTimer(0);
                            deadS = 0;
                            deadT = 0;
                            intersectionSide = null;
                            KeyListener.setPauseGame(true);
                            View.GamePanel.setFRAME_WIDTH(700);
                            View.GamePanel.setFRAME_HEIGHT(700);
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
                        View.GamePanel.setFRAME_WIDTH(0);
                        View.GamePanel.setFRAME_HEIGHT(0);
                        hasPlayed = true;
                    }
                    if( lose ) {

                        frameSize.shrink();
                        View.GamePanel.setFRAME_WIDTH(0);
                        View.GamePanel.setFRAME_HEIGHT(0);
                        int purchase = JOptionPane.showOptionDialog(View.GlassFrame.getINSTANCE(),
                                "You lost :( XP:" + MyProject.getGameInfo().getXP(), null, JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE, null, option, option[0]);

                        if (purchase == 0) {

                            new View.StarterMenu();
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
                                 View.ShopFrame.setHealItem(false);
                            View.ShopFrame.setBanishItem(false);
                            View.ShopFrame.setEmpowerItem(false);
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
                            View.SkillTreeFrame.setCurrentXP(Game.getEpsilon().getXP());
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



                    if (!View.ShopFrame.isEmpowerItem()) {
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




                    try {
                        frameSize = new FrameSize(Game.getGameFrame());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //shrinkage starts after 10 seconds
                    if (countTime >= 50 ) {

                        frameSize.shrink();
                    }


                    //check if a shot intersects with frame edges
                    // if so expansion starts from that side for a second
                    // and impact mechanism activates from that point

                    for(ShotGun shotGun : Game.getShots()){

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
                        necropick.move();
                        necropick.shoot();
                    }

                    for(Omenoct omenoct : Game.getOmenocts()){
                        omenoct.chooseSide();
                        omenoct.move();
                        omenoct.shoot();
                    }
                    for(Archmire archmire : Game.getArchmires()){
                        archmire.move();
                        archmire.fadeFootprint();

                    }

                    for(JFrame frame : Game.getFrames()) {
                        if (!frame.equals(Game.getEpsilon().getLocalFrame())) {
                            frameIntersection.changeLocalFrame(frame, Game.getEpsilon());

                        }
                    }
                    for(ShotGun shotGun : Game.getShots()) {
                        for (JFrame frame : Game.getFrames()) {
                            if (!frame.equals(Game.getEpsilon().getLocalFrame())) {
                                frameIntersection.changeLocalFrame(frame, shotGun);

                            }
                        }
                    }
                    for(Trigorath trigorath : Game.getTrigoraths()) {
                        for (JFrame frame : Game.getFrames()) {
                            if (!frame.equals(Game.getEpsilon().getLocalFrame())) {
                                frameIntersection.changeLocalFrame(frame, trigorath);
                            }
                        }

                    }
                  for(Vertex vertex : Game.getEpsilon().getVertex()){
                      for(JFrame frame : Game.getFrames()){
                          frameIntersection.changeLocalFrame(frame,vertex);
                      }
                  }



             //       Game.getGameFrame().repaint();
                    for(JFrame frame : Game.getFrames()) frame.getContentPane().repaint();
             //       for(InvisibleFrame invisibleFrame : Game.getInvisibleFrames()) invisibleFrame.getContentPane().repaint();

                    for(BarricadosFrame barricadosFrame : Game.getBarricadosFrames()) {


                        barricadosFrame.getContentPane().repaint();

                        barricadosFrame.getBarricados().invisible();
                        barricadosFrame.invisible();


                    }
                    for (BlackOrbFrame blackOrbFrame : Game.getBlackOrbFrames()) blackOrbFrame.getContentPane().repaint();

                    for(WyrmFrame wyrmFrame : Game.getWyrmFrames()){
                        wyrmFrame.getWyrm().move();
                        wyrmFrame.getWyrm().shot();
                    }
                    for(WyrmFrame wyrmFrame : Game.getWyrmFrames()){
                        for(ShotGun shotGun : wyrmFrame.getWyrm().getShots()){
                            shotGun.move();
                        }
                    }
                    for(WyrmFrame wyrmFrame : Game.getWyrmFrames()) {
                        for (ShotGun shotGun : wyrmFrame.getWyrm().getShots()) {
                            for (JFrame frame : Game.getFrames()) {
                           //     if (!frame.equals(Game.getEpsilon().getLocalFrame())) {
                                    frameIntersection.changeLocalFrame(frame, shotGun);
                                }
                       //     }
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
