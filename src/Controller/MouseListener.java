package Controller;

import Model.Epsilon;
import Model.ShotGun;
import View.GamePanel;
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
    public MouseListener(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        shotGun = GamePanel.getShotGun();
        epsilon = GamePanel.getEpsilon();

    }

    public void mouseClicked(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();

        angle2 = Math.atan2(mouseY- GamePanel.getEpsilon().getY(),mouseX - GamePanel.getEpsilon().getX());
        double fireX = GamePanel.getEpsilon().getX() + GamePanel.getEpsilon().getRadius() * Math.cos(angle2);
        double fireY = GamePanel.getEpsilon().getY() + GamePanel.getEpsilon().getRadius() * Math.sin(angle2);

        ShotGun.addShot((int) fireX, (int) fireY,Constants.getShotGunHeight(),Constants.getShotGunWidth());

        angle = Math.atan2(mouseY-epsilon.getY(),mouseX-epsilon.getX());
        angleDegrees = Math.toDegrees(angle);
        ShotGun.getShots().getLast().setxVelocity((int) (speed*Math.cos(angle)));
        ShotGun.getShots().getLast().setyVelocity((int) (speed*Math.sin(angle)));
        ShotGun.getShots().getLast().setOnFire(true);

    }

}
