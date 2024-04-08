package Model;

import Controller.Constants;
import View.GameFrame;

import java.util.Random;

public class Squarantine extends GameObjects implements movable {

    private int xVelocity;
    private int yVelocity;
    private int [] xPoints;
    private int [] yPoints;
    private int HP;
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
    public Squarantine(int x, int y) {
        super(x, y);
        epsilon = GameFrame.getEpsilon();
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

    public int getCollectible() {
        return collectible;
    }

    public void setCollectible(int collectible) {
        this.collectible = collectible;
    }

    public int getHPPerAttack() {
        return HPPerAttack;
    }

    public void setHPPerAttack(int HPPerAttack) {
        this.HPPerAttack = HPPerAttack;
    }

    public int getEpsilonXPos() {
        return epsilonXPos;
    }

    public void setEpsilonXPos(int epsilonXPos) {
        this.epsilonXPos = epsilonXPos;
    }

    public int getEpsilonYPos() {
        return epsilonYPos;
    }

    public void setEpsilonYPos(int epsilonYPos) {
        this.epsilonYPos = epsilonYPos;
    }

    public int getSquarantineXPos() {
        return squarantineXPos;
    }

    public void setSquarantineXPos(int squarantineXPos) {
        squarantineXPos = squarantineXPos;
    }

    public int getSquarantineYPos() {
        return squarantineYPos;
    }

    public void setSquarantineYPos(int squarantineYPos) {
        squarantineYPos = squarantineYPos;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    @Override
    public void move() {

        epsilonXPos = epsilon.getX();
        epsilonYPos = epsilon.getY();

        squarantineXPos = (xPoints[0]+xPoints[1]+xPoints[2]+xPoints[3])/4;
        squarantineYPos = (yPoints[0]+yPoints[1]+yPoints[2]+yPoints[3])/4;

        angle = (int) Math.atan2(epsilonYPos-squarantineYPos,epsilonXPos-squarantineXPos);


        Random random = new Random();
        boolean aggressionMechanic = random.nextInt(15) == 1;
        // create squarantine random aggression mechanism


        if(!aggressionMechanic) {
            speed = Constants.squarantineNormalSpeed();
            this.setxVelocity((int) (speed * Math.cos(angle)));
            this.setyVelocity((int) (speed * Math.sin(angle)));
            speed -= Constants.squarantineAcceleration();
            if(speed<=2){
                speed = 2;
            }
        }else{
            System.out.println(1111);
            this.setxVelocity((int) (speed * Math.cos(angle)));
            this.setyVelocity((int) (speed * Math.sin(angle)));
            speed += Constants.squarantineAcceleration();
            speed += Constants.squarantineAcceleration();
        }



        xPoints[0] += getxVelocity();
        xPoints[1] += getxVelocity();
        xPoints[2] += getxVelocity();
        xPoints[3] += getxVelocity();

        yPoints[0] += getyVelocity();
        yPoints[1] += getyVelocity();
        yPoints[2] += getyVelocity();
        yPoints[3] += getyVelocity();

        squarantineXPos += getxVelocity();
        squarantineYPos += getyVelocity();


        this.setX(getX() + getxVelocity());
        this.setY(getY() + getyVelocity());
    }
}
