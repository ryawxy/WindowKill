package View.entityViews;

import Controller.Game;
import Model.Collectible;
import Model.Drawable;
import Model.Entity.Trigorath;

import javax.swing.*;
import java.awt.*;

public class TrigorathView implements Drawable {
    private JFrame frame;

    public TrigorathView(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void paint(Graphics2D g) {

        // if trigorath is alive paint it
        // if not show its collectible

        for (int i = 0; i < Game.getTrigoraths().size(); i++) {
            Trigorath trigorath1 = Game.getTrigoraths().get(i);
            if (!trigorath1.isDead()) {
                int[] globalX = new int[3];
                int[] globalY = new int[3];

                for (int j = 0; j < 3; j++) {

                    globalX[j] = trigorath1.getLocalxPoints()[j] + trigorath1.getLocalFrame().getX();
                    globalY[j] = trigorath1.getLocalyPoints()[j] + trigorath1.getLocalFrame().getY();




                }

                Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                        , frame.getWidth(), frame.getHeight());


                Polygon global = new Polygon(globalX, globalY, 3);

                if(trigorath1.getLocalFrames().size()==1) {
                    if (global.intersects(bounds)) {

                        g.setColor(Color.YELLOW);
                        g.drawPolygon(trigorath1.getLocalxPoints(), trigorath1.getLocalyPoints(), 3);
                    }
                }else{
                    g.setColor(Color.YELLOW);

                    int [] xpoint = new int[3];
                    int [] ypoint = new int[3];
                    for(int j=0;j<3;j++){

                        xpoint[j] = globalX[j]-bounds.x;
                        ypoint[j] = globalY[j]-bounds.y;

                    }
                    g.drawPolygon(xpoint,ypoint,3);

                }
            }else {
                    if (trigorath1.isShowCollectibles()) {

                        g.setColor(Color.orange);
                        for (int j = 0; j < trigorath1.getCollectibles().size(); j++) {


                            Collectible collectible = trigorath1.getCollectibles().get(j);
                            g.fillOval(collectible.getX(), collectible.getY(), collectible.getRadius(), collectible.getRadius());

                        }
                    }
                }
            }
        }
    }

