package model.entity;

import controller.Constants;
import controller.Game;
import model.*;
import model.enums.Side;
import view.GamePanel;
import view.GlassFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Omenoct extends GameObjects implements Movable {
    private  int HP = 20;
    private boolean dead;
    private boolean showCollectibles;
    private final ArrayList<ShotGun> shots = new ArrayList<>();
    private boolean visible = true ;
    private final ArrayList<Collectible> collectibles = new ArrayList<>();
    private int[] xPoints = {0,0,0,0,0,0};
    private int[] yPoints = {0,0,0,0,0,0};
    private double xVelocity;
    private double yVelocity;
    private int xPosition;
    private int yPosition;
    private boolean canMove = true;
    private int timer;
    private Side side;
    private long shotPerTime;
    private int localX = getX();
    private int localY = getY();
    private JFrame localFrame = GlassFrame.getINSTANCE();
    private JFrame previousLocalFrame = GlassFrame.getINSTANCE();

    public Omenoct(int x, int y) {
        super(x, y);
        initializeCollectibles();
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

    @Override
    public boolean isShowCollectibles() {
        return showCollectibles;
    }

    @Override
    public void setShowCollectibles(boolean showCollectibles) {
        this.showCollectibles = showCollectibles;
    }

    public ArrayList<ShotGun> getShots() {
        return shots;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
    }

    @Override
    public void move() {


        double angle3 =  Math.atan2(getY() - yPosition, getX() - xPosition);

        xVelocity = Math.cos(angle3) * 3;
        yVelocity = Math.sin(angle3) * 3;

        for(Integer z : xPoints){
            if (z <= 10 || z >= GamePanel.getFRAME_WIDTH()-10 ) {
                xVelocity = 0;
                yVelocity = 0;
                if(z <= 10) side = Side.LEFT;
                else side = Side.RIGHT;
                break;
            }
        }
        for(Integer y : yPoints){
            if (y <= 10 || y >= GamePanel.getFRAME_HEIGHT()-10) {
                yVelocity = 0;
                xVelocity = 0;
                if(y<=10) side = Side.UP;
                else side = Side.DOWN;
                break;
            }
        }


        xPoints[0] += (int) getxVelocity();
        xPoints[1] += (int) getxVelocity();
        xPoints[2] += (int) getxVelocity();
        xPoints[3] += (int) getxVelocity();
        xPoints[4] += (int) getxVelocity();
        xPoints[5] += (int) getxVelocity();

        yPoints[0] += (int) getyVelocity();
        yPoints[1] += (int) getyVelocity();
        yPoints[2] += (int) getyVelocity();
        yPoints[3] += (int) getyVelocity();
        yPoints[4] += (int) getyVelocity();
        yPoints[5] += (int) getyVelocity();

        setX(xPoints[4]);
        setY(yPoints[4]);


//        setX((int) (getX()+xVelocity));
//        setY((int) (getY()+yVelocity));

    }
    public void chooseSide(){

        if(canMove) {
            Random random = new Random();
            int x = random.nextInt(4);

            if (x == 0) {
                xPosition = GamePanel.getFRAME_WIDTH()/2;
                yPosition = 0;

            } else if (x == 1) {
                xPosition = GamePanel.getFRAME_WIDTH();
                yPosition = GamePanel.getFRAME_HEIGHT()/2;

            } else if (x == 2) {
                xPosition = GamePanel.getFRAME_WIDTH()/2;
                yPosition = GamePanel.getFRAME_HEIGHT();

            } else {
                xPosition = 0;
                yPosition = GamePanel.getFRAME_HEIGHT()/2;

            }
            canMove = false;
        }

    }
    public void decreaseHP(int decrement){
        if(!isDead()){
        setHP(getHP()-decrement);
        if(getHP()<=0){
            setDead(true);
            setShowCollectibles(true);

        }
        for(int i=0;i<8;i++) {
            if (i < 6) {
                getCollectibles().get(i).setX(xPoints[i] + 40 * (i % 2));
                getCollectibles().get(i).setY(yPoints[i] + 80 * (i % 2));
            } else {
                getCollectibles().get(i).setX(xPoints[5] + 80 * (i % 2));
                getCollectibles().get(i).setY(yPoints[4] + 80 * (i % 2));
            }
        }

        }

    }
    public void initializeCollectibles(){

        Collectible collectible1 = new Collectible(xPoints[0],yPoints[0]);
        collectible1.setRadius(10);

        Collectible collectible2 = new Collectible(xPoints[1],yPoints[1]);
        collectible2.setRadius(10);

        Collectible collectible3 = new Collectible(getX()-5,getY()+5);
        collectible3.setRadius(10);

        Collectible collectible4 = new Collectible(getX(),getY()+8);
        collectible4.setRadius(10);

        Collectible collectible5 = new Collectible(getX()+5,getY());
        collectible5.setRadius(10);

        Collectible collectible6 = new Collectible(getX()+8,getY()+5);
        collectible6.setRadius(10);

        Collectible collectible7 = new Collectible(getX()+3,getY()+3);
        collectible7.setRadius(10);

        Collectible collectible8 = new Collectible(getX()+7,getY()+7);
        collectible8.setRadius(10);



       collectibles.add(collectible1);
        collectibles.add(collectible2);
        collectibles.add(collectible3);
        collectibles.add(collectible4);
        collectibles.add(collectible5);
        collectibles.add(collectible6);
        collectibles.add(collectible7);
        collectibles.add(collectible8);
    }
    public void invisibleCollectible(){
        if (isShowCollectibles()) {
            setTimer(getTimer() + 1);
            if (getTimer() > 500) {
                setShowCollectibles(false);
            }
        }
    }
    public void shoot(){

        if(xVelocity==0 && yVelocity==0){
        Epsilon epsilon = Game.getEpsilon();
        if(isVisible()) {


            int[] xDirection = new int[]{-1, -1, 0, 1, 1, 1, 0, 1, 1};
            int[] yDirection = new int[]{0, -1, -1, -1, 0, 1, 1, 1, 0};

            shotPerTime++;

            if (shotPerTime >= 80) {

                Random random = new Random();
                int x = random.nextInt(9);
                ShotGun shotGun = new ShotGun(getX(), getY());
                shotGun.setWidth(Constants.getShotGunWidth());
                shotGun.setHeight(Constants.getShotGunHeight());
                shotGun.setxVelocity(xDirection[x]*2);
                shotGun.setyVelocity(yDirection[x]*2);
                shots.add(shotGun);
                shotPerTime = 0;


            }

        }

        }
        for (ShotGun shotGun : shots) shotGun.move();
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

    @Override
    public double getxVelocity() {
        return xVelocity;
    }

    @Override
    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    @Override
    public double getyVelocity() {
        return yVelocity;
    }

    @Override
    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
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

    public Side getSide() {
        return side;
    }

    @Override
    public int getWidth() {
        return Constants.omenoctWidth();
    }

    @Override
    public int getHeight() {
        return Constants.omenoctWidth();
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

    @Override
    public int getGlobalX() {
        return super.getGlobalX();
    }

    @Override
    public int getGlobalY() {
        return super.getGlobalY();
    }
}
