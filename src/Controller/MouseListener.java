package Controller;

import Model.Epsilon;
import Model.ShotGun;
import View.GamePanel;
import View.ShopFrame;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseInputAdapter {

    GamePanel gamePanel;
    ShotGun shotGun;
    Epsilon epsilon;
    private int mouseX;
    private int mouseY;
    private double angle;
    private double angle2;
    private double angleDegrees;
    private final double speed = Constants.getShotGunSpeed();
    private static int timer;
    //if empower item is activated, wait a bit for each shot to fire
    public MouseListener(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        shotGun = GamePanel.getShotGun();
        epsilon = GamePanel.getEpsilon();

    }

    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        angle2 = Math.atan2(mouseY - GamePanel.getEpsilon().getY(), mouseX - GamePanel.getEpsilon().getX());
        double fireX = GamePanel.getEpsilon().getX() + GamePanel.getEpsilon().getRadius() * Math.cos(angle2);
        double fireY = GamePanel.getEpsilon().getY() + GamePanel.getEpsilon().getRadius() * Math.sin(angle2);
        angle = Math.atan2(mouseY - epsilon.getY(), mouseX - epsilon.getX());
        angleDegrees = Math.toDegrees(angle);

        if (!ShopFrame.isEmpowerItem()) {
            ShotGun.addShot((int) fireX, (int) fireY, Constants.getShotGunHeight(), Constants.getShotGunWidth());

            ShotGun.getShots().getLast().setxVelocity((int) (speed * Math.cos(angle)));
            ShotGun.getShots().getLast().setyVelocity((int) (speed * Math.sin(angle)));
            ShotGun.getShots().getLast().setOnFire(true);

        }else{

            for(int i=0;i<3;i++){
                ShotGun.addShot((int) fireX, (int) fireY, Constants.getShotGunHeight(), Constants.getShotGunWidth());
            }

            for(int j=1;j<4;j++){


                ShotGun.getShots().get(j).setxVelocity((int) (speed * Math.cos(angle)));
                ShotGun.getShots().get(j).setyVelocity((int) (speed * Math.sin(angle)));

                ShotGun.getShots().get(j).setOnFire(true);

            }
        }
    }

    public static int getTimer() {
        return timer;
    }

    public static void setTimer(int timer) {
        MouseListener.timer = timer;
    }
}
