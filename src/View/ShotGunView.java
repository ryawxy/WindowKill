package View;

import Controller.Game;
import Model.Drawable;
import Model.entity.Necropick;
import Model.entity.ShotGun;
import Model.entity.Omenoct;
import View.entityViews.wyrm.WyrmFrame;

import javax.swing.*;
import java.awt.*;

public class ShotGunView implements Drawable {
    private JFrame frame;

    public ShotGunView(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void paint(Graphics2D g) {

//        for (ShotGun shotGun1 : Game.getShots()) {
//            if (shotGun1.isOnFire()) {
//                if (shotGun1.isVisible()) {
//                    g.setColor(Color.WHITE);
//
//                    g.fillRect(shotGun1.getX(), shotGun1.getY(),
//                            shotGun1.getWidth(), shotGun1.getHeight());
//                }
//            }
//        }

        for(ShotGun shotGun : Game.getShots()){
            if(shotGun.isVisible()) {
                int x = shotGun.getLocalX() + shotGun.getLocalFrame().getX();
                int y = shotGun.getLocalY() + shotGun.getLocalFrame().getY();
                Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                        , frame.getWidth(), frame.getHeight());
                if (bounds.contains(x, y) || bounds.contains(x + shotGun.getWidth(), y) ||
                        bounds.contains(x, y + shotGun.getHeight()) ||
                        bounds.contains(x + shotGun.getWidth(), y + shotGun.getHeight())) {
                    if(shotGun.getLocalFrames().size()==1){

                        g.setColor(Color.WHITE);
                        g.fillRect(shotGun.getLocalX(), shotGun.getLocalY(), shotGun.getWidth(), shotGun.getHeight());
                    }else {
                        g.setColor(Color.WHITE);
                        g.fillRect(x - bounds.x, y - bounds.y, shotGun.getWidth(), shotGun.getHeight());
                    }
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
        for(WyrmFrame wyrmFrame : Game.getWyrmFrames()){
        for(ShotGun shotGun : wyrmFrame.getWyrm().getShots()){
            if(shotGun.isVisible()) {
                int x = shotGun.getLocalX() + shotGun.getLocalFrame().getX();
                int y = shotGun.getLocalY() + shotGun.getLocalFrame().getY();
                Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                        , frame.getWidth(), frame.getHeight());
                if (bounds.contains(x, y) || bounds.contains(x + shotGun.getWidth(), y) ||
                        bounds.contains(x, y + shotGun.getHeight()) ||
                        bounds.contains(x + shotGun.getWidth(), y + shotGun.getHeight())) {
                    if (shotGun.getLocalFrames().size() == 1) {

                        g.setColor(new Color(0xFFB7B7));
                        g.fillRect(shotGun.getLocalX(), shotGun.getLocalY(), shotGun.getWidth(), shotGun.getHeight());
                    } else {
                        g.setColor(new Color(0xFFB7B7));
                        g.fillRect(x - bounds.x, y - bounds.y, shotGun.getWidth(), shotGun.getHeight());
                    }
                }
            }
            }

        }
    }
}
