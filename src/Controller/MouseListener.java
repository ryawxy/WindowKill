package Controller;

import Model.Entity.Epsilon;
import Model.GameInfo;
import Model.enums.ShopItem;
import Model.Entity.ShotGun;
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
    private static int timer;
    //if empower item is activated, wait a bit for each shot to fire
    private static boolean shootinEmpowerMode;
    public MouseListener(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        shotGun = Game.getShotGun();
        epsilon = Game.getEpsilon();

    }

    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        angle2 = Math.atan2(mouseY - Game.getEpsilon().getY(), mouseX - Game.getEpsilon().getX());
        double fireX = Game.getEpsilon().getX() + Game.getEpsilon().getRadius() * Math.cos(angle2);
        double fireY = Game.getEpsilon().getY() + Game.getEpsilon().getRadius() * Math.sin(angle2);
        angle = Math.atan2(mouseY - epsilon.getY(), mouseX - epsilon.getX());
        angleDegrees = Math.toDegrees(angle);

        if (!GameInfo.getCurrentShopItem().containsKey(ShopItem.Empower)) {
            Game.addShot((int) fireX, (int) fireY, Constants.getShotGunHeight(), Constants.getShotGunWidth());

            Game.getShots().getLast().setxVelocity((int) (speed * Math.cos(angle)));
            Game.getShots().getLast().setyVelocity((int) (speed * Math.sin(angle)));
            Game.getShots().getLast().setOnFire(true);

        }else{

            shootinEmpowerMode = true;
            for(int i=0;i<3;i++){
                Game.addShot((int) fireX, (int) fireY, Constants.getShotGunHeight(), Constants.getShotGunWidth());
            }

            for(int j = Game.getShots().size()-3; j<= Game.getShots().size()-1; j++){


                Game.getShots().get(j).setxVelocity((int) (speed * Math.cos(angle)));
                Game.getShots().get(j).setyVelocity((int) (speed * Math.sin(angle)));

                Game.getShots().get(j).setOnFire(true);

            }
        }
    }


    public static int getTimer() {
        return timer;
    }

    public static void setTimer(int timer) {
        MouseListener.timer = timer;
    }

    public static boolean isShootinEmpowerMode() {
        return shootinEmpowerMode;
    }

    public static void setShootinEmpowerMode(boolean shootinEmpowerMode) {
        MouseListener.shootinEmpowerMode = shootinEmpowerMode;
    }
}
