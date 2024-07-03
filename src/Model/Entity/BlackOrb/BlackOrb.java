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
    private final ArrayList<Laser> lasers = new ArrayList<>();
    public BlackOrb(int x, int y) {
        super(x, y);

        localX = x;
        localY = y;
    }

    @Override
    public void decreaseHP(int decrement) {

        setHP(getHP()-decrement);
        if(getHP()<=0) setDead(true);
    }

    public ArrayList<Laser> getLasers() {
        return lasers;
    }

    @Override
    public JFrame getLocalFrame() {
        return localFrame;
    }

    @Override
    public void setLocalFrame(JFrame localFrame) {
        this.localFrame = localFrame;
    }

    @Override
    public int getGlobalX() {
        return super.getGlobalX();
    }

    @Override
    public int getGlobalY() {
        return super.getGlobalY();
    }

    @Override
    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}


