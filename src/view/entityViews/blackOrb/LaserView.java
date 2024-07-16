package view.entityViews.blackOrb;

import Controller.Game;
import Model.Drawable;
import Model.entity.blackOrb.BlackOrb;
import Model.entity.blackOrb.Laser;

import javax.swing.*;
import java.awt.*;


public class LaserView implements Drawable {
    private final JFrame frame;

    public LaserView(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void paint(Graphics2D g) {


            for (BlackOrb blackOrb : Game.getBlackOrbs()) {
                for (int i = 0; i < blackOrb.getLasers().size(); i++) {

                    Laser laser = blackOrb.getLasers().get(i);
                    Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                            , frame.getWidth(), frame.getHeight());

                    int globalX = (int) (laser.getLocalX() + laser.getBlackOrb1().getLocalFrame().getX());
                    int globalY = (int) (laser.getLocalY() + laser.getBlackOrb1().getLocalFrame().getY());
                    if (laser.isVisible()) {
                        if (bounds.contains(globalX, globalY) || bounds.contains(globalX + laser.getWidth(), globalY) ||
                                bounds.contains(globalX, globalY + laser.getWidth()) ||
                                bounds.contains(globalX + laser.getWidth(), globalY + laser.getHeight())) {
                            if (laser.getLocalFrames().size() == 1) {

                                g.drawImage(laser.getImage(), (int) laser.getStart().getX(), (int) laser.getStart().getY(),
                                        laser.getWidth(),laser.getHeight() ,null);

                            } else{

                                g.drawImage(laser.getImage(), (globalX - bounds.x), (globalY - bounds.y),laser.getImage().getWidth(null),laser.getImage().getHeight(null), null);
                                g.setColor(new Color(0,0,0));

                            }

                        }
                    }
                }
            }
        }
    }

