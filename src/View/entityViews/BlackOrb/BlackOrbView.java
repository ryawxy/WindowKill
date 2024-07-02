package View.entityViews.BlackOrb;

import Controller.Constants;
import Model.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlackOrbView implements Drawable {

    private BufferedImage image;
    public BlackOrbView(){

        try {
            image = ImageIO.read(new File("src/images/orb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paint(Graphics2D g) {

        g.drawImage(image,45,20, Constants.orbSize(),Constants.orbSize(),null);


    }
}
