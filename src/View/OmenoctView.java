package View;

import Controller.Game;
import Model.Ability;
import Model.Drawable;
import Model.Omenoct;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class OmenoctView implements Drawable {
    private final ArrayList<Omenoct> omenocts = Game.getOmenocts();
    @Override
    public void paint(Graphics2D g) {
        for(Omenoct omenoct : omenocts){
            g.setColor(Color.magenta);
            g.fillPolygon(omenoct.getxPoints(), omenoct.getyPoints(),6);
        }
    }
}
