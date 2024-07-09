package Model.entity;

import Controller.Constants;
import Controller.Game;
import Model.Collectible;
import Model.GameObjects;
import Model.Movable;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Wyrm extends GameObjects implements Movable {

    private int HP = 12;
    private JFrame localFrame;
    private int width;
    private int height;
    private boolean dead;
    private double xVelocity;
    private double yVelocity;
    private boolean showCollectibles;
    private final ArrayList<Collectible> collectibles = new ArrayList<>();
    private int localX;
    private int localY;
    private double angle;
    private boolean getCloser = true;
    private int x;
    private int y;
    private JPanel itsPanel;
    private double rotationAngle;
    private long lastTime;
    private final ArrayList<ShotGun> shots = new ArrayList<>();
    private int timer;
    public Wyrm(int x, int y) {
        super(x, y);
        localX = x;
        localY = y;
        initializeCollectibles();
    }

    @Override
    public void move() {


        xVelocity = 0;
        yVelocity = 0;
        Epsilon epsilon = Game.getEpsilon();
        Point2D epsilonPosition = new Point2D.Double(epsilon.getLocalX()+epsilon.getLocalFrame().getX(),
                epsilon.getLocalY()+epsilon.getLocalFrame().getY());
        Point2D wyrmPosition = new Point2D.Double(getLocalX()+getLocalFrame().getX(),getLocalY()+getLocalFrame().getY());

        double distance = epsilonPosition.distance(wyrmPosition);

        if(distance > 250 && getCloser  ){

            xVelocity = 0;
            yVelocity = 0;

            angle = (int) Math.atan2(epsilonPosition.getY() - wyrmPosition.getY(), epsilonPosition.getX() - wyrmPosition.getX());

            this.setXVelocity((int) ((int) (3 * Math.cos(angle))+xVelocity));
            this.setYVelocity((int) ((int) (3 * Math.sin(angle))+yVelocity));


            getLocalFrame().setLocation((int) (getLocalFrame().getX()+ getXVelocity()), (int) (getLocalFrame().getY()+ getYVelocity()));

        }else{

            getCloser = false;
            angle += 0.015;

            int newX = (int) (epsilon.getLocalX()+epsilon.getLocalFrame().getX()+250*Math.cos(angle)-getLocalFrame().getWidth());
            int newY = (int) (epsilon.getLocalY()+epsilon.getLocalFrame().getY()+250*Math.sin(angle)-getLocalFrame().getHeight());

            getLocalFrame().setLocation(newX,newY);


        }

    }
    public void shot(){

        long currentTime = System.currentTimeMillis();



        if(!isDead()) {
            if ((currentTime - lastTime) / 1000 >= 2) {

                lastTime = currentTime;
                Epsilon epsilon = Game.getEpsilon();
                Point2D epsilonPosition = new Point2D.Double(epsilon.getLocalX() + epsilon.getLocalFrame().getX(),
                        epsilon.getLocalY() + epsilon.getLocalFrame().getY());
                Point2D wyrmPosition = new Point2D.Double(getLocalX() + getLocalFrame().getX(), getLocalY() + getLocalFrame().getY());


                double angle = (int) Math.atan2(epsilonPosition.getY() - wyrmPosition.getY(), epsilonPosition.getX() - wyrmPosition.getX());


                int[] xDirection = new int[]{-1, -1, 0, 1, 1, 1, 0, 1, 1};
                int[] yDirection = new int[]{0, -1, -1, -1, 0, 1, 1, 1, 0};


                ShotGun shotGun = new ShotGun(getLocalX(), getLocalY());
                shotGun.setWidth(Constants.getShotGunWidth());
                shotGun.setHeight(Constants.getShotGunHeight());
                shotGun.getLocalFrames().clear();
                shotGun.getLocalFrames().add(localFrame);
                shotGun.setLocalFrame(localFrame);
                Random random = new Random();
                int x = random.nextInt(9);
                shotGun.setxVelocity(xDirection[x] * 3);
                shotGun.setyVelocity(yDirection[x] * 3);

                shots.add(shotGun);


            }
        }

        for (ShotGun shot : shots) shot.move();

    }

    @Override
    public void decreaseHP(int decrement) {

        setHP(getHP()-decrement);
        if(getHP()<=0){
            setDead(true);
            setShowCollectibles(true);
        }
    }
    public void initializeCollectibles(){

        Collectible collectible1 = new Collectible(getX()+60,getY()+60);
        collectible1.setRadius(10);
        Collectible collectible2 = new Collectible(getX()+100,getY()+100);
        collectible2.setRadius(10);

        collectibles.add(collectible1);
        collectibles.add(collectible2);
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
    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
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
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public double getXVelocity() {
        return xVelocity;
    }

    @Override
    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    @Override
    public double getYVelocity() {
        return yVelocity;
    }

    @Override
    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    @Override
    public boolean isShowCollectibles() {
        return showCollectibles;
    }

    @Override
    public void setShowCollectibles(boolean showCollectibles) {
        this.showCollectibles = showCollectibles;
    }

    @Override
    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    public JPanel getItsPanel() {
        return itsPanel;
    }

    public void setItsPanel(JPanel itsPanel) {
        this.itsPanel = itsPanel;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    @Override
    public ArrayList<ShotGun> getShots() {
        return shots;
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

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public int getNumCollectibles() {
        return 2;
    }
}
