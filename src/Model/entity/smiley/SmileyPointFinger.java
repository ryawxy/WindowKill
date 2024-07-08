package Model.entity.smiley;

import Controller.Constants;
import Controller.Game;
import Model.GameObjects;
import Model.Movable;
import Model.entity.Epsilon;
import Model.entity.ShotGun;
import Model.enums.SmileyHandSide;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class SmileyPointFinger extends GameObjects implements Movable {
   private SmileyHandSide smileyHandSide;
    private int x;
    private int y;
    private int HP = 100;
    private int localX;
    private int localY;
    private JFrame localFrame;
    private boolean dead;
    private final ArrayList<ShotGun> shots = new ArrayList<>();
    private long lastTime;
    private boolean getCloser = true;
    private double xVelocity;
    private double yVelocity;
    private double angle;

    public SmileyPointFinger(int x, int y) {
        super(x, y);
        setX(x);
        setY(y);
        localX = x;
        localY = y;
    }

    public void shot(){
        long currentTime = System.currentTimeMillis();

        if(!isDead()) {
            if ((currentTime - lastTime) / 1000 >= 1) {

                lastTime = currentTime;

                int[] xDirection = new int[]{-1, -1, 0, 1, 1, 1, 0, 1, 1};
                int[] yDirection = new int[]{0, -1, -1, -1, 0, 1, 1, 1, 0};


                ShotGun shotGun = new ShotGun(getLocalX(), getLocalY());
                shotGun.setWidth(Constants.getShotGunWidth());
                shotGun.setHeight(Constants.getShotGunHeight());
                shotGun.getLocalFrames().clear();
                shotGun.getLocalFrames().add(localFrame);
                shotGun.setLocalFrame(localFrame);
                Random random = new Random();
                int x = random.nextInt(9);
                shotGun.setxVelocity(xDirection[x] * 3);
                shotGun.setyVelocity(yDirection[x] * 3);

                shots.add(shotGun);
            }
        }

        for (ShotGun shot : shots) shot.move();
    }

    @Override
    public void decreaseHP(int decrement) {
        super.decreaseHP(decrement);
    }

    public SmileyHandSide getSmileyHandSide() {
        return smileyHandSide;
    }

    public void setSmileyHandSide(SmileyHandSide smileyHandSide) {
        this.smileyHandSide = smileyHandSide;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public int getLocalX() {
        return localX;
    }

    @Override
    public void setLocalX(int localX) {
        this.localX = localX;
    }

    @Override
    public int getLocalY() {
        return localY;
    }

    @Override
    public void setLocalY(int localY) {
        this.localY = localY;
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
    public boolean isDead() {
        return dead;
    }

    @Override
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public ArrayList<ShotGun> getShots() {
        return shots;
    }

    @Override
    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    @Override
    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    @Override
    public void move() {

        xVelocity = 0;
        yVelocity = 0;
        Epsilon epsilon = Game.getEpsilon();
        Point2D epsilonPosition = new Point2D.Double(epsilon.getLocalX()+epsilon.getLocalFrame().getX(),
                epsilon.getLocalY()+epsilon.getLocalFrame().getY());
        Point2D smileyPosition = new Point2D.Double(getLocalX()+getLocalFrame().getX(),getLocalY()+getLocalFrame().getY());

        double distance = epsilonPosition.distance(smileyPosition);
        int SAFE_DISTANCE = 400;

        if(distance > SAFE_DISTANCE && getCloser  ){

            xVelocity = 0;
            yVelocity = 0;

            angle = (int) Math.atan2(epsilonPosition.getY() - smileyPosition.getY(), epsilonPosition.getX() - smileyPosition.getX());

            this.setxVelocity((int) ((int) (3 * Math.cos(angle))+xVelocity));
            this.setyVelocity((int) ((int) (3 * Math.sin(angle))+yVelocity));


            getLocalFrame().setLocation((int) (getLocalFrame().getX()+getxVelocity()), (int) (getLocalFrame().getY()+getyVelocity()));

        }else{

            getCloser = false;
            if(smileyHandSide.equals(SmileyHandSide.LEFT)) angle += 0.015;
            else angle -= 0.015;

            int newX = (int) (epsilon.getLocalX()+epsilon.getLocalFrame().getX()+SAFE_DISTANCE*Math.cos(angle)-getLocalFrame().getWidth());
            int newY = (int) (epsilon.getLocalY()+epsilon.getLocalFrame().getY()+SAFE_DISTANCE*Math.sin(angle)-getLocalFrame().getHeight());

            getLocalFrame().setLocation(newX,newY);

        }
    }
}
