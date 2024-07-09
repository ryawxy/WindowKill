package Model.entity;

import Controller.Constants;
import Controller.Game;
import Model.*;
import Model.enums.Side;
import java.util.ArrayList;
import java.util.Random;

public class Omenoct extends GameObjects implements Movable {
    private final ArrayList<ShotGun> shots = new ArrayList<>();
    private boolean visible = true;
    private boolean canMove = true;
    private Side side;
    private double angle;

    public Omenoct(int x, int y) {
        super(x, y);
        setX(x);
        setY(y);
        setLocalX(x);
        setLocalY(y);
        setLocalFrame(Game.getEpsilon().getLocalFrame());
        setPreviousLocalFrame(Game.getEpsilon().getLocalFrame());
        getLocalFrames().add(getLocalFrame());
        setHP(20);
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
    public void move() {

            Epsilon epsilon = Game.getEpsilon();

            if(getLocalX()<=0) side = Side.LEFT;
            if(getLocalX()+getWidth()>=getLocalFrame().getWidth()) side = Side.RIGHT;
            if(getLocalY() <= 0) side = Side.UP;
            if(getLocalY()+getHeight()>=getLocalFrame().getHeight()) side = Side.DOWN;

        if(getLocalX()<=0 || getLocalX()+getWidth()>=getLocalFrame().getWidth()||
                getLocalY() <= 0 || getLocalY()+getHeight()>=getLocalFrame().getHeight()){
            if(epsilon.getLocalFrame().equals(getLocalFrame())) {

                setxVelocity(0);
                setyVelocity(0);
            }
        }else if(getLocalFrame().equals(epsilon.getLocalFrame())){

            setxVelocity(Math.cos(angle)*Constants.omenoctNormalSpeed());
            setyVelocity(Math.sin(angle)*Constants.omenoctNormalSpeed());
        }
            setLocalX((int) (getLocalX() + getxVelocity()));
            setLocalY((int) (getLocalY() + getyVelocity()));

            setX((int) (getX() + getxVelocity()));
            setY((int) (getY() + getyVelocity()));

        }

        public void changeFrame(){
        Epsilon epsilon = Game.getEpsilon();
       if(!getLocalFrame().equals(epsilon.getLocalFrame())){

          ArrayList<Side> overlapSides =  FrameIntersection.twoFrameOverlapSide(getLocalFrame(),epsilon.getLocalFrame());
            double angle = Math.atan2(epsilon.getLocalFrame().getY()+epsilon.getLocalY()-(getLocalY()+getLocalFrame().getY()),
                    epsilon.getLocalFrame().getX()+epsilon.getLocalX()-(getLocalX()+getLocalFrame().getX()));

          if(overlapSides.contains(Side.UP) ){
              setyVelocity(Math.sin(angle)*Constants.omenoctNormalSpeed());

          }if(overlapSides.contains(Side.DOWN)){
              setyVelocity(Math.sin(angle)*Constants.omenoctNormalSpeed());
            }if(overlapSides.contains(Side.LEFT)){
              setxVelocity(Math.cos(angle)*Constants.omenoctNormalSpeed());
            }if(overlapSides.contains(Side.RIGHT)){
              setxVelocity(Math.cos(angle)*Constants.omenoctNormalSpeed());
            }
        }else{

           chooseSide();
       }
        }


    public void chooseSide(){

        if(canMove) {

            Random random = new Random();
            int x = random.nextInt(4);

            int yPosition;
            int xPosition;
            if (x == 0) {
                xPosition = getLocalFrame().getWidth()/2 ;
                yPosition = 0;
                side = Side.UP;


            } else if (x == 1) {
                xPosition = getLocalFrame().getWidth()/2;
                yPosition = getLocalFrame().getHeight();
                side = Side.DOWN;


            } else if (x == 2) {
                xPosition = getLocalFrame().getWidth();
                yPosition = getLocalFrame().getHeight()/2;
                side = Side.RIGHT;


            } else {
                xPosition = 0;
                yPosition = getLocalFrame().getHeight()/2;
                side = Side.LEFT;


            }
            canMove = false;
            angle = Math.atan2(getLocalY()  - yPosition, getLocalX()  - xPosition);

        }
    }
    public void shoot() {

        if (isVisible()) {
            int[] xDirection = new int[]{-1, -1, 0, 1, 1, 1, 0, 1, 1};
            int[] yDirection = new int[]{0, -1, -1, -1, 0, 1, 1, 1, 0};

            if (Math.random()<0.03) {

                Random random = new Random();
                int x = random.nextInt(9);
                ShotGun shotGun = new ShotGun(getLocalX() + getWidth() / 2, getLocalY() + getHeight() / 2);

                shotGun.setWidth(Constants.getShotGunWidth());
                shotGun.setHeight(Constants.getShotGunHeight());
                shotGun.setxVelocity(xDirection[x] * 2);
                shotGun.setyVelocity(yDirection[x] * 2);
                shots.add(shotGun);

            }

        }
        for (ShotGun shotGun : shots) shotGun.move();
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
    public int getNumCollectibles() {
        return 8;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
