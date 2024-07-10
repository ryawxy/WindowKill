package Model.entity;

import Controller.Constants;
import Controller.Game;
import Model.*;
import Model.enums.Side;
import java.util.ArrayList;
import java.util.Random;

public class Omenoct extends GameObjects implements Movable {
    private boolean visible = true;
    private boolean canMove;
    private Side side;
    private double angle;
    private double stuckXVelocity;
    private double stuckYVelocity;
    private boolean canChoose = true;

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


        if((epsilon.getLocalFrame().equals(getLocalFrame())|| epsilon.getLocalFrames().contains(labelFor)) && canChoose) {
            chooseSide();
            canMove = true;
            canChoose = false;
        }


        if(getLocalX()<=0 || getLocalX()+getWidth()>=getLocalFrame().getWidth()||
                getLocalY() <= 0 || getLocalY()+getHeight()>=getLocalFrame().getHeight()){
            if(epsilon.getLocalFrame().equals(getLocalFrame())|| getLocalFrames().contains(epsilon.getLocalFrame())) {

                setXVelocity(0);
                setYVelocity(0);
            }
        }else if(getLocalFrame().equals(epsilon.getLocalFrame())){

            setXVelocity(Math.cos(angle)*Constants.omenoctNormalSpeed());
            setYVelocity(Math.sin(angle)*Constants.omenoctNormalSpeed());
        }

//        if(getXVelocity() == 0 && getYVelocity() == 0){
//            if(side.equals(Side.DOWN) || side.equals(Side.UP)){
//                stuckXVelocity = epsilon.getXVelocity()+epsilon.getxVelocity2();
//            }else{
//                stuckYVelocity = epsilon.getYVelocity()+epsilon.getyVelocity2();
//            }
//        }
//        if(epsilon.getXVelocity()+epsilon.getxVelocity2()>0 && getLocalX()+getWidth()>=getLocalFrame().getWidth())stuckXVelocity=0;
//        if(epsilon.getXVelocity()+epsilon.getxVelocity2()<0 && getLocalX()<=0)stuckXVelocity=0;
//
//        if(epsilon.getYVelocity()+epsilon.getyVelocity2()>0 && getLocalY()+getHeight()>=getLocalFrame().getHeight())stuckYVelocity=0;
//        if(epsilon.getYVelocity()+epsilon.getyVelocity2()<0 && getLocalY()<=0)stuckYVelocity=0;

        setLocalX((int) (getLocalX() + getXVelocity()+stuckXVelocity));
            setLocalY((int) (getLocalY() + getYVelocity()+stuckYVelocity));

            setX((int) (getX() + getXVelocity()+stuckXVelocity));
            setY((int) (getY() + getYVelocity()+stuckYVelocity));

        }

        public void changeFrame(){

        Epsilon epsilon = Game.getEpsilon();
       if(!getLocalFrame().equals(epsilon.getLocalFrame())){

          ArrayList<Side> overlapSides =  FrameIntersection.twoFrameOverlapSide(getLocalFrame(),epsilon.getLocalFrame());
            double angle = Math.atan2(epsilon.getLocalFrame().getY()+ (double) epsilon.getLocalFrame().getHeight() /2-(getLocalY()+getLocalFrame().getY()),
                    epsilon.getLocalFrame().getX()+ (double) epsilon.getLocalFrame().getWidth() /2-(getLocalX()+getLocalFrame().getX()));
            canChoose = true;
          if(overlapSides.contains(Side.UP) ){
              setYVelocity(Math.sin(angle)*Constants.omenoctNormalSpeed());

          }if(overlapSides.contains(Side.DOWN)){
              setYVelocity(Math.sin(angle)*Constants.omenoctNormalSpeed());
            }if(overlapSides.contains(Side.LEFT)){
              setXVelocity(Math.cos(angle)*Constants.omenoctNormalSpeed());
            }if(overlapSides.contains(Side.RIGHT)){
              setXVelocity(Math.cos(angle)*Constants.omenoctNormalSpeed());
            }
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
                shotGun.setXVelocity(xDirection[x] * 2);
                shotGun.setYVelocity(yDirection[x] * 2);
                getShots().add(shotGun);

            }
        }
        for (ShotGun shotGun : getShots()) shotGun.move();
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
}
