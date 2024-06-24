package Model.Entity;

import Controller.Constants;
import Controller.Game;
import Model.*;

import java.util.ArrayList;
import java.util.Random;

public class Necropick extends GameObjects implements Movable {
    private  int HP = 10;
    private boolean dead;
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
        initializeCollectibles();
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
      //  long currentTime = System.currentTimeMillis();

        currentTime++;

        if ( visible ) {
            if(currentTime>400) {

                visible = false;
                //    lastVisibilityChangeTime = currentTime;
           //     currentTime = 0;
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

        //    Game.getSoundPlayer().playSoundEffect("src/Sound/death.wav");
            setDead(true);
            setShowCollectibles(true);

        //    }

        }else{
          //  Game.getSoundPlayer().playSoundEffect("src/Sound/hurt.wav");
        }
    }

    public int getHP() {
        return HP;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
    public boolean isShowCollectibles() {
        return showCollectibles;
    }

    public void setShowCollectibles(boolean showCollectibles) {
        this.showCollectibles = showCollectibles;
    }
    public void initializeCollectibles(){

        Collectible collectible1 = new Collectible(getX(),getY());
        collectible1.setRadius(10);

        Collectible collectible2 = new Collectible(getX()+ Constants.necropickWidth(),getY()+Constants.necropickWidth());
        collectible2.setRadius(10);

        Collectible collectible3 = new Collectible(getX(),getY()+Constants.necropickWidth());
        collectible3.setRadius(10);

        Collectible collectible4 = new Collectible(getX()+Constants.necropickWidth(),getY());
        collectible4.setRadius(10);

        collectibles.add(collectible1);
        collectibles.add(collectible2);
        collectibles.add(collectible3);
        collectibles.add(collectible4);
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
    public boolean isAttackByMelee() {
        return true;
    }

    @Override
    public int getWidth() {
        return Constants.necropickWidth();
    }

    @Override
    public int getHeight() {
        return Constants.necropickWidth();
    }
}
