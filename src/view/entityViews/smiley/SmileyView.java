package view.entityViews.smiley;

import Model.Drawable;
import Model.entity.smiley.Smiley;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SmileyView implements Drawable {

    private Smiley smiley;
    private BufferedImage image;

    public SmileyView(Smiley smiley){
        this.smiley = smiley;

        try {
            image = ImageIO.read(new File("src/images/smiley.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    @Override
    public void paint(Graphics2D g) {

        g.drawImage(image,smiley.getX(),smiley.getY(), smiley.getWidth(),smiley.getHeight(),null);

    }
}
