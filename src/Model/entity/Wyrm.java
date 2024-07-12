package Model.entity;

import Controller.Constants;
import Controller.Game;
import Model.Collectible;
import Model.GameObjects;
import Model.Movable;
import Model.ObjectsIntersection;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class Wyrm extends GameObjects implements Movable {
    private double angle = 0.015;
    private boolean getCloser = true;
    private int collisionNumber;
    public Wyrm(int x, int y) {
        super(x, y);

        setPreviousLocalFrame(getLocalFrame());
        getLocalFrames().add(getLocalFrame());
        setHP(12);

    }

    @Override
    public void move() {


        if(getHP()>0) {
            setXVelocity(0);
            setYVelocity(0);

            Epsilon epsilon = Game.getEpsilon();
            Point2D epsilonPosition = new Point2D.Double(epsilon.getLocalX() + epsilon.getLocalFrame().getX(),
                    epsilon.getLocalY() + epsilon.getLocalFrame().getY());
            Point2D wyrmPosition = new Point2D.Double(getLocalX() + getLocalFrame().getX(), getLocalY() + getLocalFrame().getY());

            double distance = epsilonPosition.distance(wyrmPosition);

            if (distance > 250 && getCloser) {

                setXVelocity(0);
                setYVelocity(0);

                angle = (int) Math.atan2(epsilonPosition.getY() - wyrmPosition.getY(), epsilonPosition.getX() - wyrmPosition.getX());

                this.setXVelocity((int) ((int) (3 * Math.cos(angle)) + getXVelocity()));
                this.setYVelocity((int) ((int) (3 * Math.sin(angle)) + getYVelocity()));

                setLocalX((int) (getLocalX() + getXVelocity()));
                setLocalY((int) (getLocalY() + getYVelocity()));


                getLocalFrame().setLocation((int) (getLocalFrame().getX() + getXVelocity()), (int) (getLocalFrame().getY() + getYVelocity()));

            } else {

                getCloser = false;

                if(collisionNumber%2==0) angle += 0.015;
                else angle -= 0.015;

//                if(ObjectsIntersection.wyrmInterectsEntity()){
//                    if(angle>0){
//                        angle = -0.015;
//                    }else if(angle<=0){
//                        angle = 0.015;
//                    }
//
//                    ObjectsIntersection.setWyrmCollision(false);
//                }
//                if(angle>0) angle+=0.015;
//                if(angle<0) angle-=0.015;



                int newX = (int) (epsilon.getLocalX() + epsilon.getLocalFrame().getX() + 250 * Math.cos(angle) - getLocalFrame().getWidth());
                int newY = (int) (epsilon.getLocalY() + epsilon.getLocalFrame().getY() + 250 * Math.sin(angle) - getLocalFrame().getHeight());

                setLocalX(newX - getLocalFrame().getX());
                setLocalY(newY - getLocalFrame().getY());


                getLocalFrame().setLocation(newX, newY);


            }
        }

    }
    public void shot(){

        if(!isDead()) {
            if (Math.random()<0.02) {

                int[] xDirection = new int[]{-1, -1, 0, 1, 1, 1, 0, 1, 1};
                int[] yDirection = new int[]{0, -1, -1, -1, 0, 1, 1, 1, 0};


                ShotGun shotGun = new ShotGun(getLocalX(), getLocalY());
                shotGun.setWidth(Constants.getShotGunWidth());
                shotGun.setHeight(Constants.getShotGunHeight());
                shotGun.getLocalFrames().clear();
                shotGun.getLocalFrames().add(getLocalFrame());
                shotGun.setLocalFrame(getLocalFrame());
                shotGun.setPreviousLocalFrame(getLocalFrame());
                Random random = new Random();
                int x = random.nextInt(9);
                shotGun.setXVelocity(xDirection[x] * 5);
                shotGun.setYVelocity(yDirection[x] * 5);

                getShots().add(shotGun);


            }
        }

        for (ShotGun shot : getShots()) shot.move();

    }

    @Override
    public void showCollectible() {

        Random random = new Random();
        int numCollectibles = getNumCollectibles();
        int radius = Math.max(getHeight(),getWidth());

        for(int i=0;i<numCollectibles;i++){
            double angle = 2*Math.PI*random.nextDouble();
            int distance = random.nextInt(radius);

            int x = (int) (getLocalFrame().getX()/2 + distance*Math.cos(angle));
            int y = (int) (getLocalFrame().getY()/2 + distance*Math.sin(angle));


            Collectible collectible = new Collectible(x,y,Game.getEpsilon().getLocalFrame());
            collectible.setRadius(10);
            getCollectibles().add(collectible);
        }
    }

    @Override
    public int getWidth() {
        return Constants.wyrmWidth();
    }
    @Override
    public int getHeight() {
        return Constants.wyrmHeight();
    }
    public void setItsPanel(JPanel itsPanel) {
    }
    @Override
    public int getNumCollectibles() {
        return 2;
    }

    public void setCollisionNumber(int collisionNumber) {
        this.collisionNumber = collisionNumber;
    }

    public int getCollisionNumber() {
        return collisionNumber;
    }
}
