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
    public MouseListener(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        shotGun = GameFrame.getShotGun();
        epsilon = GameFrame.getEpsilon();

    }

    public void mouseClicked(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();
        System.out.println(11111);

        angle = Math.atan2(mouseY-epsilon.getY(),mouseX-epsilon.getX());
        angleDegrees = Math.toDegrees(angle);
        shotGun.setxVelocity((int) (speed*Math.cos(angle)));
        shotGun.setyVelocity((int) (speed*Math.sin(angle)));

    }

}
