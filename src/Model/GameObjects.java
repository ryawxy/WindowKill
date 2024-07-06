package Model;

import Model.Entity.ShotGun;

import javax.swing.*;
import java.util.ArrayList;

public class GameObjects extends JLabel {
    private int x;
    private int y;
    private int localX;
    private int localY;
    private int width;
    private int height;
    private double xVelocity;
    private double yVelocity;
    private boolean showCollectibles;
    private ArrayList<Collectible> collectibles = new ArrayList<>();
    private boolean attackByMelee;
    private ArrayList<ShotGun> shots = new ArrayList<>();
    private int HP;
    private JFrame localFrame;
    private JFrame previousLocalFrame;
    private int globalX;
    private int globalY;
    private ArrayList<JFrame> localFrames = new ArrayList<>();
    private boolean dead;

    public GameObjects(int x, int y){

        this.x = x;
        this.y = y;

    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void decreaseHP(int decrement){}

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public boolean isShowCollectibles() {
        return showCollectibles;
    }

    public void setShowCollectibles(boolean showCollectibles) {
        this.showCollectibles = showCollectibles;
    }

    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
    }
    public void invisibleCollectible(){}

    public boolean isAttackByMelee() {
        return attackByMelee;
    }

    public ArrayList<ShotGun> getShots() {
        return shots;
    }

    public int getHP() {
        return HP;
    }

    public int getLocalX() {
        return localX;
    }

    public void setLocalX(int localX) {
        this.localX = localX;
    }

    public int getLocalY() {
        return localY;
    }

    public void setLocalY(int localY) {
        this.localY = localY;
    }

    public JFrame getLocalFrame() {
        return localFrame;
    }

    public void setLocalFrame(JFrame localFrame) {
        this.localFrame = localFrame;
    }

    public JFrame getPreviousLocalFrame() {
        return previousLocalFrame;
    }

    public void setPreviousLocalFrame(JFrame previousLocalFrame) {
        this.previousLocalFrame = previousLocalFrame;
    }

    public int getGlobalX() {
        return getLocalX()+getLocalFrame().getX() ;
    }

    public void setGlobalX(int globalX) {
        this.globalX = globalX;
    }

    public int getGlobalY() {
        return getLocalY()+getLocalFrame().getY();
    }

    public void setGlobalY(int globalY) {
        this.globalY = globalY;
    }

    public ArrayList<JFrame> getLocalFrames() {
        return localFrames;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
