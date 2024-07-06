package View.entityViews;

import Controller.Game;
import Model.Drawable;
import Model.Entity.Epsilon;

import javax.swing.*;
import java.awt.*;

public class EpsilonView implements Drawable {

    private final Epsilon epsilon = Game.getEpsilon();
    private JFrame frame;

    public <T extends JFrame> EpsilonView(T frame) {
        this.frame = frame;
    }

    @Override
    public void paint(Graphics2D g) {


        Epsilon epsilon = Game.getEpsilon();
//        int globalX = epsilon.getX() + epsilon.getPreviousLocalFrame().getX();
//        int globalY = epsilon.getY() + epsilon.getPreviousLocalFrame().getY();
        int globalX = epsilon.getGlobalX();
        int globalY = epsilon.getGlobalY();


        Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                , frame.getWidth(), frame.getHeight());


        if (bounds.contains(globalX, globalY) || bounds.contains(globalX + epsilon.getRadius(), globalY) ||
                bounds.contains(globalX, globalY + epsilon.getRadius()) ||
                bounds.contains(globalX + epsilon.getRadius(), globalY + epsilon.getRadius())) {
            if (epsilon.getLocalFrames().size() == 1) {
                g.setColor(Color.WHITE);
                g.drawOval((int) (epsilon.getLocalX()), (int) (epsilon.getLocalY()), epsilon.getRadius(), epsilon.getRadius());


//            g.setColor(Color.WHITE);
//            g.drawOval(epsilon.getX(), epsilon.getY(), epsilon.getRadius(), epsilon.getRadius());

            } else {
                g.setColor(Color.WHITE);
            g.drawOval((int) (globalX - bounds.x), (int) (globalY - bounds.y), epsilon.getRadius(), epsilon.getRadius());
        }
    }
    }
}
