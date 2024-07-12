package Model.entity;

import Controller.Constants;
import Controller.Game;
import Model.*;
import Model.enums.ShopItem;
import view.GlassFrame;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Squarantine extends GameObjects implements Movable {

    private double xVelocity;
    private double yVelocity;
    private int [] xPoints = {0,0,0,0};
    private int [] yPoints = {0,0,0,0};
    private int HP = 10;
    private int collectible;
    private int HPPerAttack;
    // number of HP it costs from epsilon per each attack
    private final Epsilon epsilon;
    private int epsilonXPos;
    private int epsilonYPos;
    private int squarantineXPos;
    private int squarantineYPos;
    private int angle;
    private int speed;
    private Random random;
    private boolean dead;
    private boolean showCollectibles;
    private int timer;
    // show collectibles only for 10 seconds
    private static int HPDecrement = 5;
    private static int HPDecrement2 = 10;
    private final ArrayList<Collectible> collectibles = new ArrayList<>();
    private int localX = getX();
    private int localY = getY();
    private JFrame localFrame = GlassFrame.getINSTANCE();
    private JFrame previousLocalFrame = GlassFrame.getINSTANCE();


    public Squarantine(int x, int y) {
        super(x, y);
        epsilon = Game.getEpsilon();
        initializeCollectibles();
    }

    public double getXVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int[] getxPoints() {
        return xPoints;
    }

    public void setxPoints(int[] xPoints) {
        this.xPoints = xPoints;
    }

    public int[] getyPoints() {
        return yPoints;
    }

    public void setyPoints(int[] yPoints) {
        this.yPoints = yPoints;
    }

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

    public void moveTowardsEpsilon() {

        xVelocity = 0;
        yVelocity = 0;
        double xPoint2 = getX();
        double yPoint2 = getY();
        Point2D point2D = new Point2D.Double(xPoint2,yPoint2);

        epsilonXPos = epsilon.getX();
        epsilonYPos = epsilon.getY();

        squarantineXPos = (xPoints[0]+xPoints[1]+xPoints[2]+xPoints[3])/4;
        squarantineYPos = (yPoints[0]+yPoints[1]+yPoints[2]+yPoints[3])/4;


        angle = (int) Math.atan2(epsilonYPos-squarantineYPos,epsilonXPos-squarantineXPos);

        for(IntersectionPoint point : ObjectsIntersection.getIntersectionPoints()){

            double xPoint = point.getPoint().getX();
            double yPoint = point.getPoint().getY();
            double angle3 =  Math.atan2(getY() - yPoint, getX() - xPoint);
            double distance = point2D.distance(point.getPoint());

            xVelocity = Math.cos(angle3) * Constants.getImpactSpeed((int) distance);
            yVelocity = Math.sin(angle3) * Constants.getImpactSpeed((int) distance);
        }
        this.setxVelocity((int) ((int) (speed * Math.cos(angle))+xVelocity));
        this.setyVelocity((int) ((int) (speed * Math.sin(angle))+yVelocity));


        //if banish item is activated move in the opposite direction
        if(GameInfo.getCurrentShopItem().containsKey(ShopItem.Banish)){
            this.setxVelocity((int) (getXVelocity()*-1));
            this.setyVelocity((int) (getYVelocity()*-1));
        }

        xPoints[0] += (int) getXVelocity();
        xPoints[1] += (int) getXVelocity();
        xPoints[2] += (int) getXVelocity();
        xPoints[3] += (int) getXVelocity();

        yPoints[0] += (int) getYVelocity();
        yPoints[1] += (int) getYVelocity();
        yPoints[2] += (int) getYVelocity();
        yPoints[3] += (int) getYVelocity();

        squarantineXPos += (int) getXVelocity();
        squarantineYPos += (int) getYVelocity();


        this.setX((int) (getX() + getXVelocity()));
        this.setY((int) (getY() + getYVelocity()));
    }
    @Override
    public void move(){
        random = new Random();
        if (random.nextInt(100) < 3) { // 3% chance for acceleration event
            int accelerationFactor = random.nextInt(3) + 1; // Random acceleration between 1 and 3
            while (speed < 5) {
                speed += accelerationFactor;
                moveTowardsEpsilon();
            }
        } else {
            if (speed > Constants.squarantineNormalSpeed()) {
                speed -= Constants.squarantineAcceleration(); // Decelerate if above normal speed
            }
            moveTowardsEpsilon();
        }
    }
    public void decreaseHP(int decrement){

        setHP(getHP()-decrement);
        if(getHP()<=0){
            Game.getSoundPlayer().playSoundEffect("src/Sound/death.wav");
            setDead(true);
            setShowCollectibles(true);

//                getCollectibles().get(0).setX(xPoints[1]);
//                getCollectibles().get(0).setY(yPoints[1]);
//
        }else{
            Game.getSoundPlayer().playSoundEffect("src/Sound/hurt.wav");
        }
    }


    public boolean isShowCollectibles() {
        return showCollectibles;
    }

    public void setShowCollectibles(boolean showCollectibles) {
        this.showCollectibles = showCollectibles;
    }

    public void initializeCollectibles(){

        Collectible collectible1 = new Collectible(xPoints[0],yPoints[0],getLocalFrame());
        collectible1.setRadius(10);

        collectibles.add(collectible1);

    }
    public void invisibleCollectible(){
        if (isShowCollectibles()) {
            setTimer(getTimer() + 1);
            if (getTimer() > 500) {
                setShowCollectibles(false);
            }
        }
    }

    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
    }


    public static void setHPDecrement(int HPDecrement) {
        Squarantine.HPDecrement = HPDecrement;
    }


    public static void setHPDecrement2(int HPDecrement2) {
        Squarantine.HPDecrement2 = HPDecrement2;
    }
    public int getWidth(){

        return xPoints[1]-xPoints[0];
    }
    public int getHeight(){

        return xPoints[1]-xPoints[0];
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public boolean isAttackByMelee() {
        return true;
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
    public JFrame getPreviousLocalFrame() {
        return previousLocalFrame;
    }

    @Override
    public void setPreviousLocalFrame(JFrame previousLocalFrame) {
        this.previousLocalFrame = previousLocalFrame;
    }
}


