package Model.Entity.BlackOrb;

import Model.Collectible;
import Model.GameObjects;

import javax.swing.*;
import java.util.ArrayList;

public class BlackOrb extends GameObjects {

    private int HP = 30;
    private ArrayList<Collectible> collectibles = new ArrayList<>();
    private JFrame localFrame;
    private boolean dead;
    private int localX;
    private int localY;
    private int globalX;
    private int globalY;
    private ArrayList<Laser> lasers = new ArrayList<>();
    public BlackOrb(int x, int y) {
        super(x, y);

        localX = x;
        localY = y;
    }

    public ArrayList<Laser> getLasers() {
        return lasers;
    }
}
