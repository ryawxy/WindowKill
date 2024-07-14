package Model;

import Model.entity.ShotGun;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class GameObjects extends JLabel {
    private int x;
    private int y;
    private int localX;
    private int localY;
    private int width;
    private int height;
    private double xVelocity;
    private double yVelocity;
    private boolean showCollectibles;
    private final ArrayList<Collectible> collectibles = new ArrayList<>();
    private boolean attackByMelee;
    private final ArrayList<ShotGun> shots = new ArrayList<>();
    private int HP;
    private JFrame localFrame;
    private JFrame previousLocalFrame;
    private final ArrayList<JFrame> localFrames = new ArrayList<>();
    private boolean dead;
    private int numCollectibles;
    private int timer;

    public GameObjects(int x, int y){

        this.x = x;
        this.y = y;
        setX(x);
        setY(y);
        setLocalX(x);
        setLocalY(y);

    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void decreaseHP(int decrement){
        if(!isDead()) {
            setHP(getHP() - decrement);

            if (getHP() <= 0) {
                setDead(true);
                setShowCollectibles(true);
                showCollectible();


            }
        }
    }

    public double getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public boolean isShowCollectibles() {
        return showCollectibles;
    }

    public void setShowCollectibles(boolean showCollectibles) {
        this.showCollectibles = showCollectibles;
    }

    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
    }

    public boolean isAttackByMelee() {
        return attackByMelee;
    }

    public ArrayList<ShotGun> getShots() {
        return shots;
    }

    public int getHP() {
        return HP;
    }

    public int getLocalX() {
        return localX;
    }

    public void setLocalX(int localX) {
        this.localX = localX;
    }

    public int getLocalY() {
        return localY;
    }

    public void setLocalY(int localY) {
        this.localY = localY;
    }

    public JFrame getLocalFrame() {
        return localFrame;
    }

    public void setLocalFrame(JFrame localFrame) {
        this.localFrame = localFrame;
    }

    public JFrame getPreviousLocalFrame() {
        return previousLocalFrame;
    }

    public void setPreviousLocalFrame(JFrame previousLocalFrame) {
        this.previousLocalFrame = previousLocalFrame;
    }

    public int getGlobalX() {
        return getLocalX()+getLocalFrame().getX() ;
    }

    public int getGlobalY() {
        return getLocalY()+getLocalFrame().getY();
    }

    public ArrayList<JFrame> getLocalFrames() {
        return localFrames;
    }


    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getNumCollectibles() {
        return numCollectibles;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void showCollectible(){

        Random random = new Random();
        int numCollectibles = getNumCollectibles();
        int radius = Math.max(getHeight(),getWidth());

        for(int i=0;i<numCollectibles;i++){
            double angle = 2*Math.PI*random.nextDouble();
            int distance = random.nextInt(radius);

            int x = (int) (getLocalX() + distance*Math.cos(angle));
            int y = (int) (getLocalY() + distance*Math.sin(angle));

            Collectible collectible = new Collectible(x,y,getLocalFrame());
            collectible.setRadius(10);
            getCollectibles().add(collectible);
        }
    }
    public void invisibleCollectible(){
        if (isShowCollectibles()) {
            timer++;
            if (timer > 500) {
                setShowCollectibles(false);
            }
        }
    }
}
