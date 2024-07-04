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
    private boolean showCollectibles;
    private int timer;
    public BlackOrb(int x, int y) {
        super(x, y);

        localX = x;
        localY = y;
        initializeCollectibles();
    }

    @Override
    public void decreaseHP(int decrement) {

        setHP(getHP()-decrement);
        if(getHP()<=0){
            setDead(true);
            setShowCollectibles(true);
        }
    }
    public void initializeCollectibles(){

        Collectible collectible1 = new Collectible(getX()+20,getY()+70);
        collectible1.setRadius(10);
        Collectible collectible2 = new Collectible(getX()+50,getY()+80);
        collectible2.setRadius(10);
        Collectible collectible3 = new Collectible(getX()+80,getY()+100);
        collectible3.setRadius(10);
        Collectible collectible4 = new Collectible(getX()+20,getY()+100);
        collectible4.setRadius(10);
        Collectible collectible5 = new Collectible(getX()+50,getY()+70);
        collectible5.setRadius(10);

        collectibles.add(collectible1);
        collectibles.add(collectible2);
        collectibles.add(collectible3);
        collectibles.add(collectible4);
        collectibles.add(collectible5);
    }

    @Override
    public void invisibleCollectible() {
        if(isShowCollectibles()) {
            setTimer(getTimer() + 1);
            if (getTimer() > 300) {
                setShowCollectibles(false);
            }
        }
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

    @Override
    public boolean isShowCollectibles() {
        return showCollectibles;
    }

    @Override
    public void setShowCollectibles(boolean showCollectibles) {
        this.showCollectibles = showCollectibles;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
    }

    public void setCollectibles(ArrayList<Collectible> collectibles) {
        this.collectibles = collectibles;
    }
}


