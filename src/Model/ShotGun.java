package Model;

import java.util.ArrayList;

public class ShotGun extends GameObjects implements movable{

    private int width;
    private int height;
    private double xVelocity;
    private double yVelocity;
    private boolean onFire;
    private static final ArrayList<ShotGun> shot = new ArrayList<>();
    private int expansion;
    // time interval that each shot expands the frame
    private boolean visible = true;


    public ShotGun(int x, int y) {
        super(x, y);

    }


    @Override
    public void move() {
        setX((int) (getX()+xVelocity));
        setY((int) (getY()+yVelocity));
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

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
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

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
