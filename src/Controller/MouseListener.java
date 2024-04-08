package Controller;

import Model.Epsilon;
import Model.ShotGun;
import View.GameFrame;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseInputAdapter {

    GameFrame gameFrame;
    ShotGun shotGun;
    Epsilon epsilon;
    private int mouseX;
    private int mouseY;
    private double angle;
    private double angleDegrees;
    private final double speed = 8;
    private static boolean onFire;
    private static int shotNumber = 0;
    public MouseListener(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        shotGun = GameFrame.getShotGun();
        epsilon = GameFrame.getEpsilon();

    }

    public void mouseClicked(MouseEvent e){
        //  onFire = true;
        mouseX = e.getX();
        mouseY = e.getY();


        ShotGun.addShot(GameFrame.getEpsilon().getX(),GameFrame.getEpsilon().getY(),3,8);
        // shotNumber++;
        //  System.out.println(shotNumber);



        //    System.out.println(shotNumber);

        //  System.out.println(ShotGun.getShots().size()-1);


        angle = Math.atan2(mouseY-epsilon.getY(),mouseX-epsilon.getX());
        angleDegrees = Math.toDegrees(angle);
        ShotGun.getShots().getLast().setxVelocity((int) (speed*Math.cos(angle)));
        ShotGun.getShots().getLast().setyVelocity((int) (speed*Math.sin(angle)));
        ShotGun.getShots().getLast().setOnFire(true);



    }

    public static boolean isOnFire() {
        return onFire;
    }

    public static void setOnFire(boolean onFire) {
        MouseListener.onFire = onFire;
    }
    public static int getShotNumber(){
        return shotNumber;
    }
}
