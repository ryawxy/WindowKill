package Model;

import Controller.Constants;
import Controller.Intersection;
import Controller.Game;
import View.GamePanel;
import View.ShopFrame;

import java.awt.geom.Point2D;
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
    private double xVelocity9;
    private double yVelocity9;
    private static  int HPDecrement = 5;
    private static  int HPDecrement2 = 5;

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

            double xPoint2 = getX();
            double yPoint2 = getY();
            Point2D point2D = new Point2D.Double(xPoint2,yPoint2);


            epsilonXPos = epsilon.getX();
            epsilonYPos = epsilon.getY();

            trigorathXPos = (xPoints[0] + xPoints[1] + xPoints[2]) / 3;
            trigorathYPos = (yPoints[0] + yPoints[1] + yPoints[2]) / 3;

            angle = (int) Math.atan2(epsilonYPos - trigorathYPos, epsilonXPos - trigorathXPos);

            if(Intersection.getIntersectionPoint()!=null){

                double xPoint = Intersection.getIntersectionPoint().getX();
                double yPoint = Intersection.getIntersectionPoint().getY();
                double angle2 =  Math.atan2(trigorathYPos - yPoint, trigorathXPos - xPoint);


                double distance = point2D.distance(Intersection.getIntersectionPoint());
                if(distance>=30) {
                    xVelocity2 = Math.cos(angle2) * 4;
                    yVelocity2 = Math.sin(angle2) * 4;
                }else if(distance>=20 && distance<30){
                    xVelocity2 = Math.cos(angle2) * 5;
                    yVelocity2 = Math.sin(angle2) * 5;
                }else if (distance>=10 && distance<=20){
                    xVelocity2 = Math.cos(angle2) * 6;
                    yVelocity2 = Math.sin(angle2) * 6;
                }
            }else{
                xVelocity2 = 0;
                yVelocity2 = 0;
            }

            if(Intersection.getIntersectionPoint2()!=null){


                double xPoint = Intersection.getIntersectionPoint2().getX();
                double yPoint = Intersection.getIntersectionPoint2().getY();
                double angle3 =  Math.atan2(trigorathYPos - yPoint, trigorathXPos - xPoint);

                double distance = point2D.distance(Intersection.getIntersectionPoint2());
                if(distance>=30) {
                    xVelocity3 = Math.cos(angle3) * 4;
                    yVelocity3 = Math.sin(angle3) * 4;
                }else if(distance>=20 && distance<30){
                    xVelocity3 = Math.cos(angle3) * 5;
                    yVelocity3 = Math.sin(angle3) * 5;
                }else if (distance>=10 && distance<=20){
                    xVelocity3 = Math.cos(angle3) * 6;
                    yVelocity3 = Math.sin(angle3) * 6;
                }
            }else{
                xVelocity3 = 0;
                yVelocity3 = 0;
            }
            if(Intersection.getIntersectionPoint4()!=null){

                double xPoint = Intersection.getIntersectionPoint4().getX();
                double yPoint = Intersection.getIntersectionPoint4().getY();
                double angle3 =  Math.atan2(trigorathYPos - yPoint, trigorathXPos - xPoint);

                double distance = point2D.distance(Intersection.getIntersectionPoint4());

                if(distance>=30) {
                    xVelocity4 = Math.cos(angle3) * 4;
                    yVelocity4 = Math.sin(angle3) * 4;
                }else if(distance>=20 && distance<30){
                    xVelocity4 = Math.cos(angle3) * 5;
                    yVelocity4 = Math.sin(angle3) * 5;
                }else if (distance>=10 && distance<=20){
                    xVelocity4 = Math.cos(angle3) * 6;
                    yVelocity4 = Math.sin(angle3) * 6;
                }
            }else{
                xVelocity4 = 0;
                yVelocity4 = 0;
            }
            if(Intersection.getIntersectionPoint6()!=null){

                double xPoint = Intersection.getIntersectionPoint6().getX();
                double yPoint = Intersection.getIntersectionPoint6().getY();
                double angle3 =  Math.atan2(trigorathYPos - yPoint, trigorathXPos - xPoint);

                double distance = point2D.distance(Intersection.getIntersectionPoint6());

                if(distance>=30) {
                    xVelocity6 = Math.cos(angle3) * 4;
                    yVelocity6 = Math.sin(angle3) * 4;
                }else if(distance>=20 && distance<30){
                    xVelocity6 = Math.cos(angle3) * 5;
                    yVelocity6 = Math.sin(angle3) * 5;
                }else if (distance>=10 && distance<=20){
                    xVelocity6 = Math.cos(angle3) * 6;
                    yVelocity6 = Math.sin(angle3) * 6;
                }
            }else{
                xVelocity6 = 0;
                yVelocity6 = 0;
            }
            if(Intersection.getIntersectionPoint5()!=null){

                double xPoint = Intersection.getIntersectionPoint5().getX();
                double yPoint = Intersection.getIntersectionPoint5().getY();
                double angle3 =  Math.atan2(trigorathYPos - yPoint, trigorathXPos - xPoint);

                double distance = point2D.distance(Intersection.getIntersectionPoint5());

                if(distance>=30) {
                    xVelocity5 = Math.cos(angle3) * 4;
                    yVelocity5 = Math.sin(angle3) * 4;
                }else if(distance>=20 && distance<30){
                    xVelocity5 = Math.cos(angle3) * 5;
                    yVelocity5 = Math.sin(angle3) * 5;
                }else if (distance>=10 && distance<=20){
                    xVelocity5 = Math.cos(angle3) * 6;
                    yVelocity5 = Math.sin(angle3) * 6;
                }
            }else{
                xVelocity5 = 0;
                yVelocity5 = 0;
            }
            if(Intersection.getIntersectionPoint8()!=null){

                double xPoint = Intersection.getIntersectionPoint8().getX();
                double yPoint = Intersection.getIntersectionPoint8().getY();
                double angle3 =  Math.atan2(trigorathYPos - yPoint, trigorathXPos - xPoint);

                double distance = point2D.distance(Intersection.getIntersectionPoint8());

                if(distance>=30) {
                    xVelocity8 = Math.cos(angle3) * 4;
                    yVelocity8 = Math.sin(angle3) * 4;
                }else if(distance>=20 && distance<30){
                    xVelocity8 = Math.cos(angle3) * 5;
                    yVelocity8 = Math.sin(angle3) * 5;
                }else if (distance>=10 && distance<=20){
                    xVelocity8 = Math.cos(angle3) * 6;
                    yVelocity8 = Math.sin(angle3) * 6;
                }
            }else{
                xVelocity8 = 0;
                yVelocity8 = 0;
            }
            if(Intersection.getIntersectionPoint9()!=null){

                double xPoint = Intersection.getIntersectionPoint9().getX();
                double yPoint = Intersection.getIntersectionPoint9().getY();
                double angle3 =  Math.atan2(trigorathYPos - yPoint, trigorathXPos - xPoint);

                double distance = point2D.distance(Intersection.getIntersectionPoint9());

                if(distance>=30) {
                    xVelocity9 = Math.cos(angle3) * 4;
                    yVelocity9 = Math.sin(angle3) * 4;
                }else if(distance>=20 && distance<30){
                    xVelocity9 = Math.cos(angle3) * 5;
                    yVelocity9 = Math.sin(angle3) * 5;
                }else if (distance>=10 && distance<=20){
                    xVelocity9 = Math.cos(angle3) * 6;
                    yVelocity9 = Math.sin(angle3) * 6;
                }
            }else{
                xVelocity9 = 0;
                yVelocity9 = 0;
            }

            if (Math.abs(trigorathXPos - epsilonXPos) > 40 && Math.abs(trigorathYPos - epsilonYPos) > 40) {

                speed = Constants.trigorathLongDistanceSpeed();
                this.setxVelocity((int) ((int) (speed * Math.cos(angle))+xVelocity2+xVelocity3+xVelocity4+xVelocity5+xVelocity6+xVelocity8+xVelocity9));
                this.setyVelocity((int) ((int) (speed * Math.sin(angle))+yVelocity2+yVelocity3+yVelocity4+yVelocity5+yVelocity6+yVelocity8+yVelocity9));



                speed += Constants.trigorathAcceleration();
                if (speed > 8) {
                    speed = 8;
                }
            } else {

                speed = 2;
                this.setxVelocity((int) ((int) (speed * Math.cos(angle))+xVelocity2+xVelocity3+xVelocity4+xVelocity5+xVelocity6+xVelocity8+xVelocity9));
                this.setyVelocity((int) ((int) (speed * Math.sin(angle)) + yVelocity2+yVelocity3+yVelocity4+yVelocity5+yVelocity6+yVelocity8+yVelocity9));
             //   speed -= Constants.trigorathAcceleration();
                if (speed < 2) {
                    speed = 2;
                }
            }
            //if banish item is activated move in the opposite direction
            if(ShopFrame.isBanishItem()){
                this.setxVelocity(getxVelocity()*-1);
                this.setyVelocity(getyVelocity()*-1);
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

    public void decreaseHP(boolean meleeAttack){
        if(!meleeAttack) setHP(getHP()-(HPDecrement));
        else{
            setHP(getHP()-HPDecrement2);
        }
        if(getHP()<=0){
            setDead(true);
            setShowCollectibles(true);
            Game.getSoundPlayer().playSoundEffect("src/Sound/death.wav");


            for(int i=0;i<getCollectibles().size();i++){

                getCollectibles().get(i).setX(xPoints[i]+40*(i%2));
                getCollectibles().get(i).setY(yPoints[i]+80*(i%2));

            }

        }else{
            Game.getSoundPlayer().playSoundEffect("src/Sound/hurt.wav");
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

    public static int getHPDecrement() {
        return HPDecrement;
    }

    public static void setHPDecrement(int HPDecrement) {
        Trigorath.HPDecrement = HPDecrement;
    }

    public static int getHPDecrement2() {
        return HPDecrement2;
    }

    public static void setHPDecrement2(int HPDecrement2) {
        Trigorath.HPDecrement2 = HPDecrement2;
    }
}
