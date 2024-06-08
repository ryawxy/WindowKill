
package Controller;

import Model.*;
import Sound.SoundPlayer;
import View.*;
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
    private static EpsilonView epsilonView;
    private static ShotGunView shotGunView;
    private static TrigorathView trigorathView;
    private static SquarantineView squarantineView;
    private static VertexView vertexView;
    private static CerberusView cerberusView;
    private static GameInfoView gameInfoView;
    private static NecropickView necropickView;
    private static OmenoctView omenoctView;
    protected GamePanel gamePanel;
    protected KeyListener keyListener;
    protected MouseListener mouseListener;
    private static SoundPlayer soundPlayer;
    private static HashMap<Ability,Integer> skillTreeAbilities;
    private static ArrayList<Ability> currentAbilities;
    private  static ArrayList<Cerberus> cerberuses;
    private static ArrayList<GameObjects> enemies;

    public Game() throws IOException, AWTException {

       epsilon = new Epsilon(200, 200);
        epsilon.setRadius(Constants.getEpsilonRadius());
       epsilon.setXP(SkillTreeFrame.getCurrentXP());

       shotGun = new ShotGun(Game.epsilon.getxCenter(), Game.epsilon.getyCenter());
        shotGun.setWidth(Constants.getShotGunWidth());
        shotGun.setHeight(Constants.getShotGunHeight());

        cerberuses = new ArrayList<>();


        squarantines = new ArrayList<>();
        trigoraths = new ArrayList<>();
        necropicks = new ArrayList<>();
        omenocts = new ArrayList<>();

        enemies = new ArrayList<>();


        epsilonView = new EpsilonView();
        shotGunView = new ShotGunView();
        trigorathView = new TrigorathView();
        squarantineView = new SquarantineView();
        vertexView = new VertexView();
        gameInfoView = new GameInfoView();
        cerberusView = new CerberusView();
        necropickView = new NecropickView();
        omenoctView = new OmenoctView();
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

        OmenoctPanel omenoctPanel = new OmenoctPanel();
        gamePanel = new GamePanel();

        keyListener = new KeyListener(gamePanel);
        mouseListener = new MouseListener(gamePanel);
        soundPlayer = new SoundPlayer();
        soundPlayer.playBackgroundMusic();



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

    public GamePanel getGameFrame() {
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
}
