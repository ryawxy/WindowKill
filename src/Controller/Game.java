
package Controller;


import Controller.KeyListener;
import Controller.MouseListener;
import Model.Epsilon;
import Model.ShotGun;
import Model.Squarantine;
import Model.Trigorath;
import Sound.SoundPlayer;
import View.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Game {

    private static Epsilon epsilon;
    private static ShotGun shotGun;
    private static ArrayList<Squarantine> squarantines;
    private static ArrayList<Trigorath> trigoraths;
    private static EpsilonView epsilonView;
    private static ShotGunView shotGunView;
    private static TrigorathView trigorathView;
    private static SquarantineView squarantineView;
    private static VertexView vertexView;
    private static GameInfoView gameInfoView;
    protected GamePanel gamePanel;
    protected KeyListener keyListener;
    protected MouseListener mouseListener;
    private static SoundPlayer soundPlayer;

    public Game() throws IOException, AWTException {

       epsilon = new Epsilon(200, 200);
        epsilon.setRadius(Constants.getEpsilonRadius());
       epsilon.setXP(SkillTreeFrame.getCurrentXP());

       shotGun = new ShotGun(Game.epsilon.getxCenter(), Game.epsilon.getyCenter());
        shotGun.setWidth(Constants.getShotGunWidth());
        shotGun.setHeight(Constants.getShotGunHeight());


        squarantines = new ArrayList<>();
        trigoraths = new ArrayList<>();

        epsilonView = new EpsilonView();
        shotGunView = new ShotGunView();
        trigorathView = new TrigorathView();
        squarantineView = new SquarantineView();
        vertexView = new VertexView();
        gameInfoView = new GameInfoView();

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
}
