package View;

import Controller.Game;
import Model.Drawable;
import Model.Entity.Necropick;
import Model.Entity.ShotGun;
import Model.Entity.Omenoct;

import java.awt.*;

public class ShotGunView implements Drawable {
    @Override
    public void paint(Graphics2D g) {

        for (ShotGun shotGun1 : Game.getShots()) {
            if (shotGun1.isOnFire()) {
                if (shotGun1.isVisible()) {
                    g.setColor(Color.WHITE);

                    g.fillRect(shotGun1.getX(), shotGun1.getY(),
                            shotGun1.getWidth(), shotGun1.getHeight());
                }
            }
        }
        for(Necropick necropick : Game.getNecropicks()){
            if(!necropick.isDead()){
            if(necropick.isVisible()){
            for(ShotGun shotGun : necropick.getShots()) {
                g.setColor(new Color(186, 206, 163));
                if (shotGun.isVisible()) {
                    g.fillRect(shotGun.getX(), shotGun.getY(),
                            shotGun.getWidth(), shotGun.getHeight());
                }
            }
            }

            }
        }
        for(Omenoct omenoct : Game.getOmenocts()){
            if(!omenoct.isDead()){
                if(omenoct.isVisible()){
                    for(ShotGun shotGun : omenoct.getShots()) {
                        g.setColor(new Color(255, 151, 215));
                        if (shotGun.isVisible()) {
                            g.fillRect(shotGun.getX(), shotGun.getY(),
                                    shotGun.getWidth(), shotGun.getHeight());
                        }
                    }
                }

            }
        }
    }
}
