package view.entityViews.smiley;

import Controller.Constants;
import Model.Drawable;
import Model.entity.smiley.SmileyPointFinger;
import Model.enums.SmileyHandSide;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SmileyPointerView implements Drawable {

   private SmileyPointFinger smileyPointFinger;
   private BufferedImage image;


    public SmileyPointerView(SmileyPointFinger smileyPointFinger){
        this.smileyPointFinger = smileyPointFinger;
        if(smileyPointFinger.getSmileyHandSide().equals(SmileyHandSide.LEFT)){
            try {
                image = ImageIO.read(new File("src/images/leftfinger.png"));
            } catch (IOException e) {
                e.printStackTrace();

            }
        }else{
            try {
                image = ImageIO.read(new File("src/images/rightfinger.png"));
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
    @Override
    public void paint(Graphics2D g) {

        g.drawImage(image,30,50, Constants.smileyPointerWidth(),Constants.smileyPointerHeight(),null);

    }
}
