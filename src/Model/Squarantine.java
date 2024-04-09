package Model;

import Controller.Constants;
import Controller.Intersection;
import View.GamePanel;
import View.ShopFrame;

import java.util.ArrayList;
import java.util.Random;

public class Squarantine extends GameObjects implements movable {

    private int xVelocity;
    private int yVelocity;
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
    private double xVelocity2;
    private double yVelocity2;

    private final ArrayList<Collectible> collectibles = new ArrayList<>();

    public Squarantine(int x, int y) {
        super(x, y);
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

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void moveTowardsEpsilon() {

        epsilonXPos = epsilon.getX();
        epsilonYPos = epsilon.getY();

        squarantineXPos = (xPoints[0]+xPoints[1]+xPoints[2]+xPoints[3])/4;
        squarantineYPos = (yPoints[0]+yPoints[1]+yPoints[2]+yPoints[3])/4;


        angle = (int) Math.atan2(epsilonYPos-squarantineYPos,epsilonXPos-squarantineXPos);

        if(Intersection.getIntersectionPoint()!=null){

            double xPoint = Intersection.getIntersectionPoint().getX();
            double yPoint = Intersection.getIntersectionPoint().getY();
            double angle2 =  Math.atan2(squarantineYPos - yPoint, squarantineXPos - xPoint);
            xVelocity2 = Math.cos(angle2) * Constants.impactSpeed();
            yVelocity2 = Math.sin(angle2) * Constants.impactSpeed();
        }else{
            xVelocity2 = 0;
            yVelocity2 = 0;
        }



        this.setxVelocity((int) ((int) (speed * Math.cos(angle))+xVelocity2));
        this.setyVelocity((int) ((int) (speed * Math.sin(angle))+yVelocity2));


        //if banish item is activated move in the opposite direction
        if(ShopFrame.isBanishItem()){
            this.setxVelocity(getxVelocity()*-1);
            this.setyVelocity(getyVelocity()*-1);
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
    public void decreaseHP(){
        setHP(getHP()-5);
        if(getHP()<=0){
            setDead(true);
            setShowCollectibles(true);




                getCollectibles().get(0).setX(xPoints[1]);
                getCollectibles().get(0).setY(yPoints[1]);



        }
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

        collectibles.add(collectible1);

    }

    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
    }

}


