package Model;

import Controller.Game;
import Model.entity.Trigorath;
import Model.enums.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FrameIntersection {

    public FrameIntersection(){


    }
    public  void changeLocalFrame(JFrame frame, GameObjects entity) {

        Rectangle bounds1 = new Rectangle(entity.getLocalFrame().getX(), entity.getLocalFrame().getY(), entity.getLocalFrame().getWidth()
                , entity.getLocalFrame().getHeight());


        Rectangle bounds2 = new Rectangle(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());

        JFrame previousFrame = entity.getLocalFrame();

        if (entity instanceof Trigorath) {


            int[] globalx = new int[3];
            int[] globaly = new int[3];

            for (int i = 0; i < 3; i++) {
                globalx[i] = ((Trigorath) entity).getLocalxPoints()[i] + entity.getLocalFrame().getX();
                globaly[i] = ((Trigorath) entity).getLocalyPoints()[i] + entity.getLocalFrame().getY();
            }
            Polygon trigorath = new Polygon(globalx,globaly,3);

            if (bounds1.intersects(bounds2)) {

                if (trigorath.intersects(bounds2)) {


                    if (!trigorath.intersects(bounds1)) {


                        entity.setLocalFrame(frame);
                        entity.setPreviousLocalFrame(previousFrame);

                        for (int i = 0; i < 3; i++) {
                            ((Trigorath) entity).getLocalxPoints()[i] = globalx[i] - entity.getLocalFrame().getX();
                            ((Trigorath) entity).getLocalyPoints()[i] = globaly[i] - entity.getLocalFrame().getY();
                        }
                    }
                }
            }
        } else {

            int globalX = (int) (entity.getLocalX() + entity.getLocalFrame().getX());
            int globalY = (int) (entity.getLocalY() + entity.getLocalFrame().getY());

            //    System.out.println(globalX);

            Rectangle rectangle = new Rectangle(globalX, globalY, entity.getWidth(), entity.getHeight());
            if (bounds1.intersects(bounds2)) {


                if (bounds2.intersects(rectangle)) {


                    if (!bounds1.contains(rectangle)) {

                        entity.setLocalFrame(frame);
                        entity.setPreviousLocalFrame(previousFrame);
                        entity.setLocalX(globalX - entity.getLocalFrame().getX());
                        entity.setLocalY(globalY - entity.getLocalFrame().getY());
                        entity.getLocalFrames().remove(previousFrame);

                    } else if (bounds1.contains(rectangle) && bounds2.contains(rectangle)) {
                        entity.getLocalFrames().add(frame);
                    }
                }

            }
        }
    }
    public static ArrayList<Direction> overlapSides(JFrame frame){

        ArrayList<Direction> sides = new ArrayList<>();
        Rectangle frameBounds = new Rectangle(frame.getX(),frame.getY(),frame.getWidth(),frame.getHeight());
        for(JFrame frame2 : Game.getFrames()){
            Rectangle frame2Bounds = new Rectangle(frame2.getX(),frame2.getY(),frame2.getWidth(),frame2.getHeight());

            if(frame2Bounds.intersects(frameBounds)) {

                if (frame2.getX() + frame2.getWidth() >= frame.getX() && frame2.getX() < frame.getX())
                    sides.add(Direction.LEFT);
                if (frame2.getX() <= frame.getX() + frame.getWidth() && frame2.getX() + frame2.getWidth() > frame.getX() + frame.getWidth())
                    sides.add(Direction.RIGHT);
                if (frame2.getY() + frame2.getHeight() >= frame.getY() && frame2.getY() < frame.getY())
                    sides.add(Direction.UP);
                if (frame2.getY() <= frame.getY() + frame.getHeight() && frame2.getY() + frame2.getHeight() > frame.getY() + frame.getHeight())
                    sides.add(Direction.DOWN);

            }
        }
        return sides;
            }
    }

