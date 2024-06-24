package Model;

import Model.Entity.ShotGun;

import javax.swing.*;
import java.util.ArrayList;

public class GameObjects extends JLabel {
    private int x;
    private int y;
    private int width;
    private int height;
    private double xVelocity;
    private double yVelocity;
    private boolean showCollectibles;
    private ArrayList<Collectible> collectibles = new ArrayList<>();
    private boolean attackByMelee;
    private ArrayList<ShotGun> shots = new ArrayList<>();
    private int HP;

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
}
