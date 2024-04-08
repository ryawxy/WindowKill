package Model;

import Controller.Constants;
import View.GameFrame;

public class Trigorath extends GameObjects implements movable {

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
    private int trigorathXPos;
    private int trigorathYPos;
    private int angle;
    public Trigorath(int x, int y) {
        super(x,y);

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

    @Override
    public void move() {
        boolean checkX = true;
        boolean checkY = true;
        //check if trigorath gets out of frame edge
        epsilonXPos = epsilon.getX();
        epsilonYPos = epsilon.getY();

        trigorathXPos = (xPoints[0]+xPoints[1]+xPoints[2])/3;
        trigorathYPos = (yPoints[0]+yPoints[1]+yPoints[2])/3;

        angle = (int) Math.atan2(epsilonYPos-trigorathYPos,epsilonXPos-trigorathXPos);

        int distance = (int) Math.sqrt(Math.pow(trigorathYPos - epsilonYPos,2)+
                Math.pow(trigorathXPos-epsilonXPos,2));
        if(distance>50) {
            this.setxVelocity((int) (Constants.trigorathLongDistanceSpeed() * Math.cos(angle)));
            this.setyVelocity((int) (Constants.trigorathLongDistanceSpeed() * Math.sin(angle)));
        }else{
            this.setxVelocity((int) (Constants.trigorathNearBySpeed() * Math.cos(angle)));
            this.setyVelocity((int) (Constants.trigorathNearBySpeed() * Math.sin(angle)));
        }



        xPoints[0] += getxVelocity();
        xPoints[1] += getxVelocity();
        xPoints[2] += getxVelocity();

        yPoints[0] += getyVelocity();
        yPoints[1] += getyVelocity();
        yPoints[2] += getyVelocity();

        trigorathXPos += getxVelocity();
        trigorathYPos += getyVelocity();


        this.setX(getX() + getxVelocity());
        this.setY(getY() + getyVelocity());


    }

    public int getTrigorathXPos() {
        return trigorathXPos;
    }

    public void setTrigorathXPos(int trigorathXPos) {
        this.trigorathXPos = trigorathXPos;
    }

    public int getTrigorathYPos() {
        return trigorathYPos;
    }

    public void setTrigorathYPos(int trigorathYPos) {
        this.trigorathYPos = trigorathYPos;
    }
}
