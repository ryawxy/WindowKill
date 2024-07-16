package view.entityViews.smiley;

import Controller.Game;
import Model.Drawable;
import Model.entity.smiley.SmileyPointFinger;
import Model.enums.PunchType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SmileyPointerView implements Drawable {
   private BufferedImage leftImage;
   private BufferedImage rightImage;
   private final JFrame frame;


    public SmileyPointerView(JFrame frame){

        this.frame = frame;
            try {
                leftImage = ImageIO.read(new File("src/images/leftfinger.png"));
                rightImage = ImageIO.read(new File("src/images/rightfinger.png"));
            } catch (IOException e) {
                e.printStackTrace();

            }
        }

    @Override
    public void paint(Graphics2D g) {

        BufferedImage image;
        for (SmileyPointFinger smileyPointFinger : Game.getSmileyPointFingers()) {

            if(smileyPointFinger.getSmileyHandSide().equals(PunchType.LEFT)) image = leftImage;
            else image = rightImage;

            int globalX = smileyPointFinger.getLocalX() + smileyPointFinger.getLocalFrame().getX();
            int globalY = smileyPointFinger.getLocalY() + smileyPointFinger.getLocalFrame().getY();


            Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                    , frame.getWidth(), frame.getHeight());


            if (bounds.contains(globalX, globalY) || bounds.contains(globalX + smileyPointFinger.getWidth(), globalY) ||
                    bounds.contains(globalX, globalY + smileyPointFinger.getHeight()) ||
                    bounds.contains(globalX + smileyPointFinger.getWidth(), globalY + smileyPointFinger.getHeight())) {
                if(smileyPointFinger.getLocalFrames().size() == 1)
                    g.drawImage(image, smileyPointFinger.getLocalX(), smileyPointFinger.getLocalY(),
                            smileyPointFinger.getWidth(), smileyPointFinger.getHeight(), null);
                else g.drawImage(image, (globalX - bounds.x), (globalY - bounds.y),
                        smileyPointFinger.getWidth(), smileyPointFinger.getHeight(), null);

            }
        }
    }
}
