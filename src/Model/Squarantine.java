package Model;

import Controller.Constants;
import Controller.ImpactSpeed;
import Controller.Intersection;
import Controller.KeyListener;
import View.GamePanel;
import View.ShopFrame;

import java.util.ArrayList;
import java.util.HashMap;
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
    private double xVelocity3;
    private double yVelocity3;
    private double xVelocity4;
    private double yVelocity4;
    private double xVelocity5;
    private double yVelocity5;
    private double xVelocity6;
    private double yVelocity6;
    private double xVelocity8;
    private double yVelocity8;
    private static int HPDecrement = 5;

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
        //    double impactSpeed = ImpactSpeed.getImpactspeed(this);
            xVelocity2 = Math.cos(angle2) * 4;
            yVelocity2 = Math.sin(angle2)*4;
        }else{
            xVelocity2 = 0;
            yVelocity2 = 0;
        }


        if(Intersection.getIntersectionPoint2()!=null){


            double xPoint = Intersection.getIntersectionPoint2().getX();
            double yPoint = Intersection.getIntersectionPoint2().getY();
            double angle3 =  Math.atan2(squarantineYPos - yPoint, squarantineXPos - xPoint);
       //     double impactSpeed = ImpactSpeed.getImpactspeed(this);
            xVelocity3 = Math.cos(angle3) * 4;
            yVelocity3 = Math.sin(angle3) * 4;

        }else{
            xVelocity3 = 0;
            yVelocity3 = 0;
        }
        if(Intersection.getIntersectionPoint4()!=null){

            double xPoint = Intersection.getIntersectionPoint4().getX();
            double yPoint = Intersection.getIntersectionPoint4().getY();
            double angle3 =  Math.atan2(squarantineYPos - yPoint, squarantineXPos - xPoint);
          //  double impactSpeed = ImpactSpeed.getImpactspeed(this);
            xVelocity4 = Math.cos(angle3) * 4;
            yVelocity4 = Math.sin(angle3) * 4;

        }else{
            xVelocity4 = 0;
            yVelocity4 = 0;
        }
        if(Intersection.getIntersectionPoint6()!=null){

            double xPoint = Intersection.getIntersectionPoint6().getX();
            double yPoint = Intersection.getIntersectionPoint6().getY();
            double angle3 =  Math.atan2(squarantineYPos - yPoint, squarantineXPos - xPoint);
         //   double impactSpeed = ImpactSpeed.getImpactspeed(this);
            xVelocity6 = Math.cos(angle3) * 4;
            yVelocity6 = Math.sin(angle3) * 4;

        }else{
            xVelocity6 = 0;
            yVelocity6 = 0;
        }
        if(Intersection.getIntersectionPoint5()!=null){


            double xPoint = Intersection.getIntersectionPoint5().getX();
            double yPoint = Intersection.getIntersectionPoint5().getY();
            double angle3 =  Math.atan2(squarantineYPos - yPoint, squarantineXPos - xPoint);
       //     double impactSpeed = ImpactSpeed.getImpactspeed(this);
            xVelocity5 = Math.cos(angle3) * 4;
            yVelocity5 = Math.sin(angle3) * 4;

        }else{
            xVelocity5 = 0;
            yVelocity5 = 0;
        }
        if(Intersection.getIntersectionPoint8()!=null){


            double xPoint = Intersection.getIntersectionPoint8().getX();
            double yPoint = Intersection.getIntersectionPoint8().getY();
            double angle3 =  Math.atan2(squarantineYPos - yPoint, squarantineXPos - xPoint);
            //     double impactSpeed = ImpactSpeed.getImpactspeed(this);
            xVelocity8 = Math.cos(angle3) * 4;
            yVelocity8 = Math.sin(angle3) * 4;

        }else{
            xVelocity8 = 0;
            yVelocity8 = 0;
        }
        this.setxVelocity((int) ((int) (speed * Math.cos(angle))+xVelocity2+xVelocity3+xVelocity4+xVelocity5+xVelocity6+xVelocity8));
        this.setyVelocity((int) ((int) (speed * Math.sin(angle))+yVelocity2+yVelocity3+yVelocity4+yVelocity5+yVelocity6+yVelocity8));


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
        setHP(getHP()-(HPDecrement));
        System.out.println(getHP());
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

    public static int getHPDecrement() {
        return HPDecrement;
    }

    public static void setHPDecrement(int HPDecrement) {
        Squarantine.HPDecrement = HPDecrement;
    }
}


