package view.entityViews.barricados;

import controller.Constants;
import model.Drawable;
import model.enums.BarricadosType;

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
    }

    @Override
    public void paint(Graphics2D g) {

        g.drawImage(image, 0, 0, Constants.barricadosWidth(), Constants.barricadosWidth(), null);

    }
}
