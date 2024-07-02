package View.entityViews.BlackOrb;

import Controller.Constants;
import Model.Drawable;
import Model.Entity.BlackOrb.BlackOrb;
import Model.Entity.BlackOrb.BlackOrbArray;
import Model.Entity.BlackOrb.Laser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LaserView implements Drawable {
    private BufferedImage image;
    private static int orbSize = Constants.orbSize();
    private BlackOrb blackOrb;

    public LaserView(BlackOrb blackOrb){
        this.blackOrb = blackOrb;
        try {
            image = ImageIO.read(new File("src/images/laser.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paint(Graphics2D g) {


        for(Laser laser : blackOrb.getLasers()){
            double x1 = laser.getStart().getX()+ (double) orbSize /2;
            double y1 = laser.getStart().getY()+ (double) orbSize /2;
            double x2 = laser.getEnd().getX()+ (double) orbSize /2;
            double y2 = laser.getEnd().getY()+ (double) orbSize /2;

            double angle = Math.atan2(y2-y1,x2-x1);
            AffineTransform backup = g.getTransform();
            AffineTransform trans = new AffineTransform();
            trans.translate((x1+x2)/2,(y1+y2)/2);
            trans.rotate(angle);
            trans.translate((double) -image.getWidth() /2, (double) -image.getHeight() /2);
            trans.scale(0.5,0.5);
            g.transform(trans);
            g.drawImage(image,(int) x1,(int) y1,null);
            g.setTransform(backup);



        }
    }
}
