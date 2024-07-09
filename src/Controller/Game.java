
package Controller;

import Model.*;
import Model.entity.*;
import Model.enums.Ability;
import sound.SoundPlayer;
import view.*;
import view.entityViews.*;
import view.entityViews.barricados.BarricadosFrame;
import view.entityViews.blackOrb.BlackOrbFrame;
import view.entityViews.blackOrb.InvisibleFrame;
import view.entityViews.wyrm.WyrmFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Game {

    private static final ArrayList<ShotGun> shot = new ArrayList<>();
    private static Epsilon epsilon;
    private static ShotGun shotGun;
    private static ArrayList<Squarantine> squarantines;
    private static ArrayList<Trigorath> trigoraths;
    private static ArrayList<Necropick> necropicks;
    private static ArrayList<Omenoct> omenocts;
    private static ArrayList<Archmire> archmires;
    private static ArrayList<Barricados> barricados;
    private static view.entityViews.EpsilonView epsilonView;
    private static view.ShotGunView shotGunView;
    private static view.entityViews.TrigorathView trigorathView;
    private static view.entityViews.SquarantineView squarantineView;
    private static view.entityViews.VertexView vertexView;
    private static view.entityViews.CerberusView cerberusView;
    private static view.GameInfoView gameInfoView;
    private static view.entityViews.NecropickView necropickView;
    private static view.entityViews.OmenoctView omenoctView;
    private static view.entityViews.ArchmireView archmireView;

    protected static GamePanel gamePanel;
    protected KeyListener keyListener;
    protected MouseListener mouseListener;
    private static SoundPlayer soundPlayer;
    private static HashMap<Ability,Integer> skillTreeAbilities;
    private static ArrayList<Ability> currentAbilities;
    private  static ArrayList<Cerberus> cerberuses;
    private static ArrayList<GameObjects> enemies;
    private static ArrayList<view.entityViews.barricados.BarricadosFrame> barricadosFrames = new ArrayList<>();
    private static ArrayList<view.entityViews.blackOrb.BlackOrbFrame> blackOrbFrames = new ArrayList<>();
    private static ArrayList<JFrame> frames = new ArrayList<>();
    private static ArrayList<InvisibleFrame> invisibleFrames = new ArrayList<>();
    private static ArrayList<Model.entity.blackOrb.BlackOrb> blackOrbs = new ArrayList<>();
    private static ArrayList<WyrmFrame> wyrmFrames = new ArrayList<>();
    public Game() throws IOException, AWTException {

        frames.add(view.GlassFrame.getINSTANCE());
       epsilon = new Epsilon(100, 100);
        epsilon.setRadius(Constants.getEpsilonRadius());
       epsilon.setXP(view.SkillTreeFrame.getCurrentXP());

       shotGun = new ShotGun(Game.epsilon.getxCenter(), Game.epsilon.getyCenter());
        shotGun.setWidth(Constants.getShotGunWidth());
        shotGun.setHeight(Constants.getShotGunHeight());

        cerberuses = new ArrayList<>();


        squarantines = new ArrayList<>();
        trigoraths = new ArrayList<>();
        necropicks = new ArrayList<>();
        omenocts = new ArrayList<>();
        archmires = new ArrayList<>();
        barricados = new ArrayList<>();

        enemies = new ArrayList<>();


       // epsilonView = new EpsilonView();
//        shotGunView = new ShotGunView();
   //     trigorathView = new TrigorathView();
        squarantineView = new view.entityViews.SquarantineView();
       // vertexView = new VertexView();
        gameInfoView = new view.GameInfoView();
        cerberusView = new CerberusView();
        necropickView = new NecropickView();
    //    omenoctView = new OmenoctView();
        archmireView = new ArchmireView();

        currentAbilities = new ArrayList<>();

        skillTreeAbilities = new HashMap<>();
        skillTreeAbilities.put(Ability.Ares,0);
        skillTreeAbilities.put(Ability.Astrape,0);
        skillTreeAbilities.put(Ability.Cerberus,0);
        skillTreeAbilities.put(Ability.Aceso,0);
        skillTreeAbilities.put(Ability.Melampus,0);
        skillTreeAbilities.put(Ability.Chiron,0);
        skillTreeAbilities.put(Ability.Proteus,0);
        skillTreeAbilities.put(Ability.Dolus,0);
        skillTreeAbilities.put(Ability.Empusa,0);


        gamePanel = new GamePanel();

        keyListener = new KeyListener(gamePanel);
        mouseListener = new MouseListener(gamePanel);
        soundPlayer = new SoundPlayer();
        soundPlayer.playBackgroundMusic();



    }
    public static <T extends JFrame>  void addFrame(ArrayList<T> frames, T frame){

        frames.add(frame);
    }

    public static Epsilon getEpsilon() {
        return epsilon;
    }

    public static ShotGun getShotGun(){
        return shotGun;
    }

    public static ArrayList<Squarantine> getSquarantine() {
        return squarantines;
    }

    public static ArrayList<Trigorath> getTrigoraths(){
        return trigoraths;
    }

    public static ArrayList<ShotGun> getShots(){
        return shot;
    }

    public static void addShot(int x, int y, int height, int width){
        ShotGun shotGun = new ShotGun(x,y);
        shotGun.setWidth(width);
        shotGun.setHeight(height);
        shot.add(shotGun);
    }

    public static GamePanel getGameFrame() {
        return gamePanel;
    }

    public static SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    public static EpsilonView getEpsilonView() {
        return epsilonView;
    }

    public static ShotGunView getShotGunView() {
        return shotGunView;
    }

    public static TrigorathView getTrigorathView() {
        return trigorathView;
    }

    public static SquarantineView getSquarantineView() {
        return squarantineView;
    }

    public static VertexView getVertexView() {
        return vertexView;
    }

    public static GameInfoView getGameInfoView() {
        return gameInfoView;
    }

    public static ArrayList<Cerberus> getCerberuses() {
        return cerberuses;
    }

    public static CerberusView getCerberusView() {
        return cerberusView;
    }

    public static ArrayList<GameObjects> getEnemies() {
        return enemies;
    }

    public static NecropickView getNecropickView() {
        return necropickView;
    }

    public static ArrayList<Necropick> getNecropicks() {
        return necropicks;
    }

    public static ArrayList<Omenoct> getOmenocts() {
        return omenocts;
    }

    public static OmenoctView getOmenoctView() {
        return omenoctView;
    }

    public static ArrayList<Archmire> getArchmires() {
        return archmires;
    }

    public static ArchmireView getArchmireView() {
        return archmireView;
    }

    public static ArrayList<Barricados> getBarricados() {
        return barricados;
    }

    public static ArrayList<BarricadosFrame> getBarricadosFrames() {
        return barricadosFrames;
    }

    public static ArrayList<JFrame> getFrames() {
        return frames;
    }

    public static void setFrames(ArrayList<JFrame> frames) {
        Game.frames = frames;
    }

    public static ArrayList<BlackOrbFrame> getBlackOrbFrames() {
        return blackOrbFrames;
    }

    public static ArrayList<InvisibleFrame> getInvisibleFrames() {
        return invisibleFrames;
    }

    public static ArrayList<Model.entity.blackOrb.BlackOrb> getBlackOrbs() {
        return blackOrbs;
    }

    public static ArrayList<WyrmFrame> getWyrmFrames() {
        return wyrmFrames;
    }
}
