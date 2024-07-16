package view.entityViews.smiley;

import Controller.Game;
import Model.Drawable;
import Model.entity.smiley.Smiley;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SmileyView implements Drawable {

    private BufferedImage image;
    private final JFrame frame;

    public SmileyView(JFrame frame){

        this.frame = frame;

        try {
            image = ImageIO.read(new File("src/images/smiley.png"));
        } catch (IOException ignored) {

        }
    }
    @Override
    public void paint(Graphics2D g) {

        for (Smiley smiley : Game.getSmilies()) {
            int globalX = smiley.getLocalX() + smiley.getLocalFrame().getX();
            int globalY = smiley.getLocalY() + smiley.getLocalFrame().getY();


            Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                    , frame.getWidth(), frame.getHeight());


            if (bounds.contains(globalX, globalY) || bounds.contains(globalX + smiley.getWidth(), globalY) ||
                    bounds.contains(globalX, globalY + smiley.getHeight()) ||
                    bounds.contains(globalX + smiley.getWidth(), globalY + smiley.getHeight())) {

                if(smiley.getLocalFrames().size() == 1)
                    g.drawImage(image, smiley.getX(), smiley.getY(), smiley.getWidth(), smiley.getHeight(), null);

                else g.drawImage(image, (globalX - bounds.x), (globalY - bounds.y), smiley.getWidth(), smiley.getHeight(), null);
            }
        }
    }
}
