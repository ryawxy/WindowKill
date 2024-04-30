package myproject;

import Controller.Constants;

import Model.Epsilon;
import Controller.GameInfo;

import View.Settings.SettingsFrame;
import View.StarterMenu;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class MyProject {
    static Epsilon epsilon;
    private static GameInfo gameInfo;
    public MyProject() throws IOException {
        SettingsFrame.setKeyBinding("up",38);
        SettingsFrame.setKeyBinding("down", KeyEvent.VK_DOWN);
        SettingsFrame.setKeyBinding("left",KeyEvent.VK_LEFT);
        SettingsFrame.setKeyBinding("right",KeyEvent.VK_RIGHT);
        SettingsFrame.setKeyBinding("shop",KeyEvent.VK_SPACE);
        SettingsFrame.setKeyBinding("ability",KeyEvent.VK_ENTER);

        epsilon = new Epsilon(200,200);
        epsilon.setRadius(Constants.getEpsilonRadius());
       gameInfo = new GameInfo();
        new StarterMenu();



    }

    public static Epsilon getEpsilon() {
        return epsilon;
    }

    public static GameInfo getGameInfo() {
        return gameInfo;
    }
}
