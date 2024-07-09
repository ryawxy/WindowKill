package Model.entity;

import Controller.Constants;
import Controller.Game;
import Model.*;
import java.util.ArrayList;
import java.util.Random;

public class Necropick extends GameObjects implements Movable {
    private boolean showCollectibles;
    private final ArrayList<ShotGun> shots = new ArrayList<>();
    private static int currentTime;
    private boolean canMove;
    private boolean visible = true ;
    private final ArrayList<Collectible> collectibles = new ArrayList<>();
    private int timer;
    private long shotPerTime;
    public Necropick(int x, int y) {
        super(x, y);
        setX(x);
        setY(y);
        setLocalX(x);
        setLocalY(y);
        setLocalFrame(Game.getEpsilon().getLocalFrame());
        setPreviousLocalFrame(Game.getEpsilon().getLocalFrame());
        setHP(10);
    }

    @Override
    public void move() {
        if(canMove) {
            Epsilon epsilon = Game.getEpsilon();
            int epsilonX = Game.getEpsilon().getX();
            int epsilonY = Game.getEpsilon().getY();
            int[] x = new int[]{-1, 0, 1, 0};
            int[] y = new int[]{0, -1, 0, 1};
            Random random = new Random();
            int randomDirection = random.nextInt(4);

            setX(epsilonX + x[randomDirection] *3* epsilon.getRadius());
            setY(epsilonY + y[randomDirection] *3* epsilon.getRadius());
            canMove = false;
            loadGun();

        }
    }
    public void visible(){

        currentTime++;

        if ( visible ) {
            if(currentTime>400) {

                visible = false;
            }
        }
        if (!visible && currentTime >= 600) {

            visible = true;
            currentTime = 0;
            canMove = true;
        }
    }
    @Override
    public boolean isVisible() {
        return visible;
    }

    public void decreaseHP(int decrement){

        setHP(getHP()-decrement);
        if(getHP()<=0){
            setDead(true);
            setShowCollectibles(true);
            showCollectible();
        }
    }
    public boolean isShowCollectibles() {
        return showCollectibles;
    }

    public void setShowCollectibles(boolean showCollectibles) {
        this.showCollectibles = showCollectibles;
    }

    public void loadGun(){

        for(int i=0; i<8; i++){
            ShotGun shotGun = new ShotGun(getX(),getY());
            shotGun.setWidth(Constants.getShotGunWidth());
            shotGun.setHeight(Constants.getShotGunHeight());
            shots.add(shotGun);
        }
    }
    public void shoot(){

        if(isVisible()){

            shotPerTime = System.currentTimeMillis();
        long lastTime = 0;
        int[] xDirection = new int[]{-1,-1,0,1,1,1,0,1,1};
        int[] yDirection = new int[]{0,-1,-1,-1,0,1,1,1,0};
        for(int i=0;i<shots.size();i++) {
            shotPerTime++;
            if (shotPerTime - lastTime >= 10 ) {

                shots.get(i).setxVelocity(xDirection[i % 8] * 2);
                shots.get(i).setyVelocity(yDirection[i % 8] * 2);
                shots.get(i).move();
                lastTime = shotPerTime;
            } else {
                i--;
            }
        }

        }
    }
    public void invisibleCollectible(){
        if (isShowCollectibles()) {
            setTimer(getTimer() + 1);
            if (getTimer() > 500) {
                setShowCollectibles(false);
            }
        }
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
    }

    public ArrayList<ShotGun> getShots() {
        return shots;
    }

    @Override
    public int getWidth() {
        return Constants.necropickWidth();
    }

    @Override
    public int getHeight() {
        return Constants.necropickWidth();
    }
    @Override
    public int getNumCollectibles() {
        return 4;
    }
}
