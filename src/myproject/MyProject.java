package myproject;

import Controller.GameLoop;
import Model.Game;
import View.GameFrame;
import View.Settings.SettingsFrame;
import View.StarterMenu;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class MyProject {
    protected Game game;
    protected GameLoop gameLoop;
    public MyProject() throws IOException {
        SettingsFrame.setKeyBinding("up",38);
        SettingsFrame.setKeyBinding("down", KeyEvent.VK_DOWN);
        SettingsFrame.setKeyBinding("left",KeyEvent.VK_LEFT);
        SettingsFrame.setKeyBinding("right",KeyEvent.VK_RIGHT);
        SettingsFrame.setKeyBinding("shop",KeyEvent.VK_SPACE);
        //TODO : ability key

        new StarterMenu();


    }
}
