package myproject;

import Controller.GameLoop;
import Model.Game;
import View.GameFrame;
import View.StarterMenu;

import java.io.IOException;

public class MyProject {
    protected Game game;
    protected GameLoop gameLoop;
    public MyProject() throws IOException {

        new StarterMenu();


    }
}
