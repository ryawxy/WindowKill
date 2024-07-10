package Model.entity;

import Controller.Constants;
import Controller.Game;
import Model.*;
import java.util.Random;

public class Necropick extends GameObjects implements Movable {
    private static int currentTime;
    private boolean canMove;
    private boolean visible = true;
    private boolean showPortal;

    public Necropick(int x, int y) {
        super(x, y);
        setX(x);
        setY(y);
        setLocalX(x);
        setLocalY(y);
        setLocalFrame(Game.getEpsilon().getLocalFrame());
        setPreviousLocalFrame(Game.getEpsilon().getLocalFrame());
        getLocalFrames().clear();
        getLocalFrames().add(getLocalFrame());
        setHP(10);
    }

    @Override
    public void move() {
        if(canMove) {
            Epsilon epsilon = Game.getEpsilon();
            int epsilonX = Game.getEpsilon().getLocalX();
            int epsilonY = Game.getEpsilon().getLocalY();
            int distance = 80;
            Random random = new Random();

            if(!epsilon.getLocalFrame().equals(getLocalFrame())){
                setLocalFrame(epsilon.getLocalFrame());
            }

          int x =  epsilonX + random.nextInt(2*distance)-distance;
           int y = epsilonY + random.nextInt(2*distance)-distance;

           setLocalX(Math.max(0,Math.min(x,getLocalFrame().getWidth()-getWidth()/2)));
            setLocalY(Math.max(0,Math.min(y,getLocalFrame().getHeight()-getHeight()/2)));


            canMove = false;


        }
    }
    public void visible(){


        currentTime++;

        if ( visible ) {
            if(currentTime>400) {

                visible = false;
                move();
                loadGun();
                canMove = false;
            }
        }
        if(!visible && currentTime>500 && currentTime<=590){

            showPortal = true;

        }
        if (!visible && currentTime >= 600) {

            showPortal = false;
            visible = true;

            currentTime = 0;
            canMove = true;
        }
    }
    @Override
    public boolean isVisible() {
        return visible;
    }

    public void loadGun(){

        for(int i=0; i<8; i++){
            ShotGun shotGun = new ShotGun(getLocalX(),getLocalY());
            shotGun.setLocalFrame(getLocalFrame());
            shotGun.setPreviousLocalFrame(getLocalFrame());
            shotGun.getLocalFrames().clear();
            shotGun.getLocalFrames().add(getLocalFrame());
            getShots().add(shotGun);
        }
    }
    public void shoot(){

        if(visible){

            long shotPerTime = System.currentTimeMillis();
        long lastTime = 0;
        int[] xDirection = new int[]{-1,-1,0,1,1,1,0,1,1};
        int[] yDirection = new int[]{0,-1,-1,-1,0,1,1,1,0};
        for(int i=0;i<getShots().size();i++) {
            shotPerTime++;
            if (shotPerTime - lastTime >= 10 ) {

                getShots().get(i).setLocalFrame(getLocalFrame());
                getShots().get(i).setXVelocity(xDirection[i % 8] * 2);
                getShots().get(i).setYVelocity(yDirection[i % 8] * 2);
                getShots().get(i).move();
                lastTime = shotPerTime;
            } else {
                i--;
            }
        }

        }
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

    public boolean isShowPortal() {
        return showPortal;
    }
}
