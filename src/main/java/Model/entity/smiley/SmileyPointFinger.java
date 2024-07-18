package Model.entity.smiley;

import Controller.Constants;
import Controller.Game;
import Model.GameObjects;
import Model.Movable;
import Model.entity.Epsilon;
import Model.entity.ShotGun;
import Model.enums.PunchType;

import java.awt.geom.Point2D;
import java.util.Random;

public class SmileyPointFinger extends GameObjects implements Movable {
   private PunchType punchType;
    private boolean getCloser = true;
    private double angle;

    public SmileyPointFinger(int x, int y) {
        super(x, y);
        setHP(100);
        setPreviousLocalFrame(getLocalFrame());
        getLocalFrames().add(getLocalFrame());
    }

    public void shot(){

        if(!isDead()) {
            if (Math.random()<0.03) {


                int[] xDirection = new int[]{-1, -1, 0, 1, 1, 1, 0, 1, 1};
                int[] yDirection = new int[]{0, -1, -1, -1, 0, 1, 1, 1, 0};


                ShotGun shotGun = new ShotGun(getLocalX(), getLocalY());
                shotGun.setWidth(Constants.getShotGunWidth());
                shotGun.setHeight(Constants.getShotGunHeight());
                shotGun.getLocalFrames().clear();
                shotGun.setLocalFrame(getLocalFrame());
                shotGun.getLocalFrames().add(getLocalFrame());
                shotGun.setPreviousLocalFrame(getLocalFrame());
                Random random = new Random();
                int x = random.nextInt(9);
                shotGun.setXVelocity(xDirection[x] * 3);
                shotGun.setYVelocity(yDirection[x] * 3);

                getShots().add(shotGun);
            }
        }

        for (ShotGun shot : getShots()) shot.move();
    }

    public PunchType getSmileyHandSide() {
        return punchType;
    }

    public void setSmileyHandSide(PunchType punchType) {
        this.punchType = punchType;
    }

    @Override
    public void move() {

        setXVelocity(0);
        setYVelocity(0);
        Epsilon epsilon = Game.getEpsilon();
        Point2D epsilonPosition = new Point2D.Double(epsilon.getLocalX()+epsilon.getLocalFrame().getX(),
                epsilon.getLocalY()+epsilon.getLocalFrame().getY());
        Point2D smileyPosition = new Point2D.Double(getLocalX()+getLocalFrame().getX(),getLocalY()+getLocalFrame().getY());

        double distance = epsilonPosition.distance(smileyPosition);
        int SAFE_DISTANCE = 400;

        if(distance > SAFE_DISTANCE && getCloser  ){

            setXVelocity(0);
            setYVelocity(0);

            angle = (int) Math.atan2(epsilonPosition.getY() - smileyPosition.getY(), epsilonPosition.getX() - smileyPosition.getX());

            this.setXVelocity((int) ((int) (3 * Math.cos(angle))+getXVelocity()));
            this.setYVelocity((int) ((int) (3 * Math.sin(angle))+getYVelocity()));


            getLocalFrame().setLocation((int) (getLocalFrame().getX()+getXVelocity()), (int) (getLocalFrame().getY()+getYVelocity()));

        }else{

            getCloser = false;
            if(punchType.equals(PunchType.LEFT)) angle += 0.015;
            else angle -= 0.015;

            int newX = (int) (epsilon.getLocalX()+epsilon.getLocalFrame().getX()+SAFE_DISTANCE*Math.cos(angle)-getLocalFrame().getWidth());
            int newY = (int) (epsilon.getLocalY()+epsilon.getLocalFrame().getY()+SAFE_DISTANCE*Math.sin(angle)-getLocalFrame().getHeight());

            getLocalFrame().setLocation(newX,newY);

        }
    }

    @Override
    public int getHeight() {
        return Constants.smileyPointerHeight();
    }

    @Override
    public int getWidth() {
        return Constants.smileyPointerWidth();
    }
}
