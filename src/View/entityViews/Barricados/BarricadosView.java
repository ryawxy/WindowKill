package View.entityViews.Barricados;

import Controller.Constants;
import Model.Drawable;
import Model.enums.BarricadosType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BarricadosView implements Drawable {

    private BufferedImage image;
    BarricadosType barricadosType;

    public BarricadosView(BarricadosType barricadosType) {

        this.barricadosType = barricadosType;
    }

    @Override
    public void paint(Graphics2D g) {
        if(barricadosType.equals(BarricadosType.T1)) {
            try {
                image = ImageIO.read(new File("src/images/barricados1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                image = ImageIO.read(new File("src/images/barricados2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g.drawImage(image, 0, 0,300, 300, null);
//        g.setColor(Color.RED);
//        g.fillRect(40,40, Constants.barricadosWidth(),Constants.barricadosWidth());
    }
}
