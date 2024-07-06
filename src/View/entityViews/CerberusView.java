package View.entityViews;

import Controller.Game;
import Model.entity.Cerberus;
import Model.Drawable;

import java.awt.*;
import java.util.ArrayList;

public class CerberusView implements Drawable {

    private ArrayList<Cerberus> cerberuses = Game.getCerberuses();

    @Override
    public void paint(Graphics2D g) {

        g.setColor(Color.PINK);
        for(Cerberus cerberus : cerberuses){
            g.drawOval(cerberus.getX(),cerberus.getY(),cerberus.getRadius(),cerberus.getRadius());
        }
    }
}
