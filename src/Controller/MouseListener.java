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
    private double angle2;
    private double angleDegrees;
    private final double speed = Constants.getShotGunSpeed();
    public MouseListener(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        shotGun = GameFrame.getShotGun();
        epsilon = GameFrame.getEpsilon();

    }

    public void mouseClicked(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();

        angle2 = Math.atan2(mouseY-GameFrame.getEpsilon().getY(),mouseX - GameFrame.getEpsilon().getX());
        double fireX = GameFrame.getEpsilon().getX() + GameFrame.getEpsilon().getRadius() * Math.cos(angle2);
        double fireY = GameFrame.getEpsilon().getY() + GameFrame.getEpsilon().getRadius() * Math.sin(angle2);

        ShotGun.addShot((int) fireX, (int) fireY,Constants.getShotGunHeight(),Constants.getShotGunWidth());

        angle = Math.atan2(mouseY-epsilon.getY(),mouseX-epsilon.getX());
        angleDegrees = Math.toDegrees(angle);
        ShotGun.getShots().getLast().setxVelocity((int) (speed*Math.cos(angle)));
        ShotGun.getShots().getLast().setyVelocity((int) (speed*Math.sin(angle)));
        ShotGun.getShots().getLast().setOnFire(true);

    }

}
