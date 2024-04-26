package myproject;

import Controller.GameLoop;

import View.GameInfo;

import View.GamePanel;
import View.Settings.SettingsFrame;
import View.StarterMenu;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class MyProject {
    public MyProject() throws IOException {
        SettingsFrame.setKeyBinding("up",38);
        SettingsFrame.setKeyBinding("down", KeyEvent.VK_DOWN);
        SettingsFrame.setKeyBinding("left",KeyEvent.VK_LEFT);
        SettingsFrame.setKeyBinding("right",KeyEvent.VK_RIGHT);
        SettingsFrame.setKeyBinding("shop",KeyEvent.VK_SPACE);
        SettingsFrame.setKeyBinding("ability",KeyEvent.VK_ENTER);

       new GameInfo();
        new StarterMenu();


    }
}
