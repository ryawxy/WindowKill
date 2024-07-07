package Model.entity.smiley;

import Controller.Constants;
import Controller.Game;
import Model.GameObjects;
import Model.Movable;
import Model.entity.Epsilon;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Smiley extends GameObjects implements Movable {

    private int x;
    private int y;
    private int HP = 300;
    private int localX;
    private int localY;
    private JFrame localFrame;
    private boolean dead;
    private boolean getCloser = true;
    private double xVelocity;
    private double yVelocity;
    private double angle;
    private ArrayList<Fire> aoeAttacks = new ArrayList<>();
    public Smiley(int x, int y) {
        super(x, y);
        setX(x);
        setY(y);
        localX = x;
        localY = y;
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
        int SAFE_DISTANCE = 300;

        if(distance > SAFE_DISTANCE && getCloser  ){

            xVelocity = 0;
            yVelocity = 0;

            angle = (int) Math.atan2(epsilonPosition.getY() - smileyPosition.getY(), epsilonPosition.getX() - smileyPosition.getX());

            this.setxVelocity((int) ((int) (3 * Math.cos(angle))+xVelocity));
            this.setyVelocity((int) ((int) (3 * Math.sin(angle))+yVelocity));


            getLocalFrame().setLocation((int) (getLocalFrame().getX()+getxVelocity()), (int) (getLocalFrame().getY()+getyVelocity()));

        }else{

            getCloser = false;
            angle += 0.015;

            int newX = (int) (epsilon.getLocalX()+epsilon.getLocalFrame().getX()+SAFE_DISTANCE*Math.cos(angle)-getLocalFrame().getWidth());
            int newY = (int) (epsilon.getLocalY()+epsilon.getLocalFrame().getY()+SAFE_DISTANCE*Math.sin(angle)-getLocalFrame().getHeight());

            getLocalFrame().setLocation(newX,newY);


        }

    }
    public void aoe(){
        Random random = new Random();
        if(aoeAttacks.size()<6) {
            for (int i = 0; i < 6; i++) {

                int x = random.nextInt(Game.getEpsilon().getLocalFrame().getWidth());
                int y = random.nextInt(Game.getEpsilon().getLocalFrame().getHeight());


                aoeAttacks.add(new Fire(x, y));
            }
        }
    }

    @Override
    public void decreaseHP(int decrement) {
        super.decreaseHP(decrement);
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
    public int getWidth() {
        return Constants.smileySize();
    }

    @Override
    public int getHeight() {
        return Constants.smileySize();
    }

    @Override
    public double getxVelocity() {
        return xVelocity;
    }

    @Override
    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    @Override
    public double getyVelocity() {
        return yVelocity;
    }

    @Override
    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public ArrayList<Fire> getAoeAttacks() {
        return aoeAttacks;
    }
}
