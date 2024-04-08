package Model;

import View.GameFrame;

import java.util.ArrayList;

public class ShotGun extends GameObjects implements movable{

    private int width;
    private int height;
    private int xVelocity;
    private int yVelocity;
    private boolean onFire;
    private static final ArrayList<ShotGun> shot = new ArrayList<>();
    private int expansion;
    // time interval that each shot expands the frame


    public ShotGun(int x, int y) {
        super(x, y);

    }


    @Override
    public void move() {
        setX(getX()+xVelocity);
        setY(getY()+yVelocity);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public static ArrayList<ShotGun> getShots(){
        return shot;
    }
    public static void addShot(int x,int y, int height, int width){
        ShotGun shotGun = new ShotGun(x,y);
        shotGun.setWidth(width);
        shotGun.setHeight(height);
        shot.add(shotGun);
    }

    public boolean isOnFire() {
        return onFire;
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
    }

    public int getExpansion() {
        return expansion;
    }

    public void setExpansion(int expansion) {
        this.expansion = expansion;
    }
}
