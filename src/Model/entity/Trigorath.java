package Model.entity;

import Controller.Constants;
import Controller.Game;
import Model.*;
import Model.enums.ShopItem;
import View.GlassFrame;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Trigorath extends GameObjects implements Movable {

    private double xVelocity;
    private double yVelocity;
    private int [] xPoints = {0,0,0};
    private int [] yPoints = {0,0,0};
    private int HP = 15;
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
    private int localX = getX();
    private int localY = getY();
    private JFrame localFrame = GlassFrame.getINSTANCE();
    private JFrame previousLocalFrame = GlassFrame.getINSTANCE();

    private static  int HPDecrement = 5;
    private static  int HPDecrement2 = 5;
    private final ArrayList<Collectible> collectibles = new ArrayList<>();

    private int[] localxPoints =  xPoints;
    private int[] localyPoints = yPoints;
    private ArrayList<JFrame> localFrames = new ArrayList<>();
    public Trigorath(int x, int y) {
        super(x,y);

        epsilon = Game.getEpsilon();

        localFrames.add(GlassFrame.getINSTANCE());
        initializeCollectibles();
        localxPoints = xPoints;
        localyPoints = yPoints;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
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

    @Override
    public void move() {

        xVelocity = 0;
        yVelocity = 0;
        if (!isDead()) {

            double xPoint2 = getX();
            double yPoint2 = getY();
            Point2D point2D = new Point2D.Double(xPoint2,yPoint2);


            epsilonXPos = epsilon.getX();
            epsilonYPos = epsilon.getY();

            trigorathXPos = (xPoints[0] + xPoints[1] + xPoints[2]) / 3;
            trigorathYPos = (yPoints[0] + yPoints[1] + yPoints[2]) / 3;

            angle = (int) Math.atan2(epsilonYPos - trigorathYPos, epsilonXPos - trigorathXPos);

            for(IntersectionPoint point : ObjectsIntersection.getIntersectionPoints()){

                double xPoint = point.getPoint().getX();
                double yPoint = point.getPoint().getY();
                double angle3 =  Math.atan2(getY() - yPoint, getX() - xPoint);
                double distance = point2D.distance(point.getPoint());

                xVelocity = Math.cos(angle3) * Constants.getImpactSpeed((int) distance);
                yVelocity = Math.sin(angle3) * Constants.getImpactSpeed((int) distance);
            }

            if (Math.abs(trigorathXPos - epsilonXPos) > 40 && Math.abs(trigorathYPos - epsilonYPos) > 40) {

                speed = Constants.trigorathLongDistanceSpeed();
                this.setxVelocity((int) ((int) (speed * Math.cos(angle))+xVelocity));
                this.setyVelocity((int) ((int) (speed * Math.sin(angle))+yVelocity));



                speed += Constants.trigorathAcceleration();
                if (speed > 8) {
                    speed = 8;
                }
            } else {

                speed = 2;
                this.setxVelocity((int) ((int) (speed * Math.cos(angle))+xVelocity));
                this.setyVelocity((int) ((int) (speed * Math.sin(angle)) + yVelocity));
             //   speed -= Constants.trigorathAcceleration();
                if (speed < 2) {
                    speed = 2;
                }
            }
            //if banish item is activated move in the opposite direction
            if(GameInfo.getCurrentShopItem().containsKey(ShopItem.Banish)){
                this.setxVelocity((int) (getxVelocity()*-1));
                this.setyVelocity((int) (getyVelocity()*-1));
            }

            xPoints[0] += (int) getxVelocity();
            xPoints[1] += (int) getxVelocity();
            xPoints[2] += (int) getxVelocity();


            localxPoints[0] += (int) getxVelocity();
            localxPoints[1] += (int) getxVelocity();
            localxPoints[2] += (int) getxVelocity();

            yPoints[0] += (int) getyVelocity();
            yPoints[1] += (int) getyVelocity();
            yPoints[2] += (int) getyVelocity();

            localyPoints[0] += (int) getyVelocity();
            localyPoints[1] += (int) getyVelocity();
            localyPoints[2] += (int) getyVelocity();


            trigorathXPos += (int) getxVelocity();
            trigorathYPos += (int) getyVelocity();


            this.setX((int) (getX() + getxVelocity()));
            this.setY((int) (getY() + getyVelocity()));

            this.setLocalX((int) (getLocalX() + getxVelocity()));
            this.setLocalY((int) (getLocalY() + getyVelocity()));
        }
    }
    public void decreaseHP(int decrement){

        setHP(getHP()-decrement);
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
    public int getWidth(){

        Point2D vertex1 = new Point2D.Double(xPoints[0],yPoints[0]);
        Point2D vertex2 = new Point2D.Double(xPoints[1],yPoints[1]);
        return (int) 20;

    }
    public int getHeight(){

        Point2D vertex1 = new Point2D.Double(xPoints[0],yPoints[0]);
        Point2D vertex2 = new Point2D.Double(xPoints[1],yPoints[1]);
        return (int) 20;

    }
    public void invisibleCollectible(){
        if(isShowCollectibles()) {
            setTimer(getTimer() + 1);
            if (getTimer() > 300) {
                setShowCollectibles(false);
            }
        }
    }

    @Override
    public int getX() {
        return xPoints[1];
    }

    @Override
    public int getY() {
        return yPoints[0];
    }

    @Override
    public boolean isAttackByMelee() {
        return true;
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

    @Override
    public int getGlobalX() {
        return super.getGlobalX();
    }

    @Override
    public int getGlobalY() {
        return super.getGlobalY();
    }

    public int[] getLocalxPoints() {
        return localxPoints;
    }

    public void setLocalxPoints(int[] localxPoints) {
        this.localxPoints = localxPoints;
    }

    public int[] getLocalyPoints() {
        return localyPoints;
    }

    public void setLocalyPoints(int[] localyPoints) {
        this.localyPoints = localyPoints;
    }

    @Override
    public ArrayList<JFrame> getLocalFrames() {
        return localFrames;
    }
}
