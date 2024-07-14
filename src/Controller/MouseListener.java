package Controller;

import Model.entity.Epsilon;
import Model.GameInfo;
import Model.enums.ShopItem;
import Model.entity.ShotGun;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseInputAdapter {

    JPanel gamePanel;
    ShotGun shotGun;
    Epsilon epsilon;
    private final double speed = Constants.getShotGunSpeed();
    private static int timer;
    //if empower item is activated, wait a bit for each shot to fire
    private static boolean shootinEmpowerMode;
    public <T extends JPanel> MouseListener(T gamePanel){
        this.gamePanel = gamePanel;
        shotGun = Game.getShotGun();
        epsilon = Game.getEpsilon();

    }

    public void mouseClicked(MouseEvent e) {


        int mouseX = e.getX();
        int mouseY = e.getY();

        double angle2 = Math.atan2(mouseY - Game.getEpsilon().getLocalY(), mouseX - Game.getEpsilon().getLocalX());
        double fireX = Game.getEpsilon().getLocalX() + Game.getEpsilon().getRadius() * Math.cos(angle2);
        double fireY = Game.getEpsilon().getLocalY() + Game.getEpsilon().getRadius() * Math.sin(angle2);
        double angle = Math.atan2(mouseY - epsilon.getLocalY(), mouseX - epsilon.getLocalX());

        if (!GameInfo.getCurrentShopItem().containsKey(ShopItem.Empower)) {
            ShotGun shot = new ShotGun((int) fireX, (int) fireY);
            shot.setLocalFrame(epsilon.getLocalFrame());
            shot.setPreviousLocalFrame(epsilon.getLocalFrame());
            shot.getLocalFrames().clear();
            shot.getLocalFrames().add(shot.getLocalFrame());
            Game.getEpsilonShots().add(shot);


            Game.getEpsilonShots().getLast().setXVelocity((int) (speed * Math.cos(angle)));
            Game.getEpsilonShots().getLast().setYVelocity((int) (speed * Math.sin(angle)));
            Game.getEpsilonShots().getLast().setOnFire(true);


        }else{

            shootinEmpowerMode = true;
            for(int i=0;i<3;i++){
                Game.addShot((int) fireX, (int) fireY, Constants.getShotGunHeight(), Constants.getShotGunWidth());
            }

            for(int j = Game.getEpsilonShots().size()-3; j<= Game.getEpsilonShots().size()-1; j++){


                Game.getEpsilonShots().get(j).setXVelocity((int) (speed * Math.cos(angle)));
                Game.getEpsilonShots().get(j).setYVelocity((int) (speed * Math.sin(angle)));

                Game.getEpsilonShots().get(j).setOnFire(true);

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
