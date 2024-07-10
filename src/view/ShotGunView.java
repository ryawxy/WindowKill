package view;

import Controller.Game;
import Model.Drawable;
import Model.GameObjects;
import Model.entity.Necropick;
import Model.entity.ShotGun;
import Model.entity.Omenoct;
import Model.entity.Wyrm;

import javax.swing.*;
import java.awt.*;

public class ShotGunView implements Drawable {
    private JFrame frame;

    public ShotGunView(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void paint(Graphics2D g) {

        for(ShotGun shotGun : Game.getEpsilonShots()){
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
        g.setColor(new Color(255, 151, 215));
        for(GameObjects enemy : Game.getEnemies()){
            if(!enemy.isDead()){
                if(enemy.isVisible()){
                    for(ShotGun shotGun : enemy.getShots()) {
                        if(shotGun.isVisible()){
                        int x = shotGun.getLocalX() + shotGun.getLocalFrame().getX();
                        int y = shotGun.getLocalY() + shotGun.getLocalFrame().getY();
                        Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                                , frame.getWidth(), frame.getHeight());
                        if (bounds.contains(x, y) || bounds.contains(x + shotGun.getWidth(), y) ||
                                bounds.contains(x, y + shotGun.getHeight()) ||
                                bounds.contains(x + shotGun.getWidth(), y + shotGun.getHeight())) {
                            if (shotGun.getLocalFrames().size() == 1) {


                                if(enemy instanceof Necropick) g.setColor(new Color(186, 206, 163));
                                if(enemy instanceof Omenoct) g.setColor(new Color(255, 151, 215));
                                if(enemy instanceof Wyrm) g.setColor(new Color(0xFFB7B7));
                                g.fillRect(shotGun.getLocalX(), shotGun.getLocalY(), shotGun.getWidth(), shotGun.getHeight());
                            } else {

                                g.fillRect(x - bounds.x, y - bounds.y, shotGun.getWidth(), shotGun.getHeight());
                            }
                        }
                        }

                    }
                }

            }
        }

    }
}
