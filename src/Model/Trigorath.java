package Model;

import Controller.Constants;
import Controller.Intersection;
import View.GamePanel;

import java.util.ArrayList;

public class Trigorath extends GameObjects implements movable {

    private int xVelocity;
    private int yVelocity;
    private int [] xPoints = {0,0,0};
    private int [] yPoints = {0,0,0};
    private int HP = 15;
    private int HPPerAttack;
    // number of HP it costs from epsilon per each attack
    private final Epsilon epsilon;
    private int epsilonXPos;
    private int epsilonYPos;
    private int trigorathXPos;
    private int trigorathYPos;
    private int angle;
    private int speed;
    private boolean dead;
    private boolean showCollectibles;
    private int timer;
    // show collectibles only for 10 seconds
    private final ArrayList<Collectible> collectibles = new ArrayList<>();
    public Trigorath(int x, int y) {
        super(x,y);

        epsilon = GamePanel.getEpsilon();

        initializeCollectibles();
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
    public int getHPPerAttack() {
        return HPPerAttack;
    }

    public void setHPPerAttack(int HPPerAttack) {
        this.HPPerAttack = HPPerAttack;
    }

    @Override
    public void move() {

        if (!isDead()) {
            epsilonXPos = epsilon.getX();
            epsilonYPos = epsilon.getY();

            trigorathXPos = (xPoints[0] + xPoints[1] + xPoints[2]) / 3;
            trigorathYPos = (yPoints[0] + yPoints[1] + yPoints[2]) / 3;

            angle = (int) Math.atan2(epsilonYPos - trigorathYPos, epsilonXPos - trigorathXPos);

            int distance = (int) Math.sqrt(Math.pow(trigorathYPos - epsilonYPos, 2) +
                    Math.pow(trigorathXPos - epsilonXPos, 2));
            if (Math.abs(trigorathXPos - epsilonXPos) > 40 && Math.abs(trigorathYPos - epsilonYPos) > 40) {

                speed = Constants.trigorathLongDistanceSpeed();
                this.setxVelocity((int) (speed * Math.cos(angle)));
                this.setyVelocity((int) (speed * Math.sin(angle)));
                speed += Constants.trigorathAcceleration();
                if (speed > 8) {
                    speed = 8;
                }
            } else {

                this.setxVelocity((int) (speed * Math.cos(angle)));
                this.setyVelocity((int) (speed * Math.sin(angle)));
                speed -= Constants.trigorathAcceleration();
                if (speed < 2) {
                    speed = 2;
                }
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

    public void decreaseHP(){
        setHP(getHP()-5);
        if(getHP()<=0){
            setDead(true);
            setShowCollectibles(true);

            getCollectibles().get(0).setX(xPoints[0]);
            getCollectibles().get(0).setY(yPoints[0]);

            getCollectibles().get(1).setX(xPoints[1]+30);
            getCollectibles().get(1).setY(yPoints[1]+20);

        }
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isShowCollectibles() {
        return showCollectibles;
    }

    public void setShowCollectibles(boolean showCollectibles) {
        this.showCollectibles = showCollectibles;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
    public void initializeCollectibles(){

        Collectible collectible1 = new Collectible(xPoints[0],yPoints[0]);
        collectible1.setRadius(10);

        Collectible collectible2 = new Collectible(xPoints[1],yPoints[1]);
        collectible2.setRadius(10);

        collectibles.add(collectible1);
        collectibles.add(collectible2);
    }

    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
    }
}
