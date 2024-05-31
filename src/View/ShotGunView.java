package View;

import Model.Drawable;
import Model.ShotGun;

import java.awt.*;

public class ShotGunView implements Drawable {
    @Override
    public void paint(Graphics2D g) {

        for (ShotGun shotGun1 : ShotGun.getShots()) {
            if (shotGun1.isOnFire()) {
                if (shotGun1.isVisible()) {
                    g.setColor(Color.WHITE);

                    g.fillRect(shotGun1.getX(), shotGun1.getY(),
                            shotGun1.getWidth(), shotGun1.getHeight());
                }
            }
        }
    }
}
