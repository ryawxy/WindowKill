package Model;

import Controller.Game;
import Model.entity.Epsilon;
import Model.entity.Omenoct;
import Model.enums.Direction;
import Model.enums.Side;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FrameIntersection {

    public FrameIntersection(){}
    public  void changeLocalFrame(JFrame frame, GameObjects entity) {

        Rectangle bounds1 = new Rectangle(entity.getLocalFrame().getX(),
                entity.getLocalFrame().getY(), entity.getLocalFrame().getWidth()
                , entity.getLocalFrame().getHeight());

        Rectangle bounds2 = new Rectangle(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());

        JFrame previousFrame = entity.getLocalFrame();

            int globalX = (int) (entity.getLocalX() + entity.getLocalFrame().getX());
            int globalY = (int) (entity.getLocalY() + entity.getLocalFrame().getY());

            Rectangle rectangle = new Rectangle(globalX, globalY, entity.getWidth(), entity.getHeight());
            if (bounds1.intersects(bounds2)) {
                if (bounds2.intersects(rectangle)) {
                    if (!bounds1.contains(rectangle)) {

                        entity.setLocalFrame(frame);
                        entity.setPreviousLocalFrame(previousFrame);
                        entity.setLocalX(globalX - entity.getLocalFrame().getX());
                        entity.setLocalY(globalY - entity.getLocalFrame().getY());
                        entity.getLocalFrames().remove(previousFrame);
                        if(entity instanceof Epsilon){
                            for(Omenoct omenoct : Game.getOmenocts()) omenoct.setCanMove(true);
                        }
                    } else if (bounds1.contains(rectangle) && bounds2.contains(rectangle)) {
                        entity.getLocalFrames().add(frame);

                    }
                }

            }
        }
    public static ArrayList<Direction> overlapSides(JFrame frame){

        ArrayList<Direction> sides = new ArrayList<>();

        for(JFrame frame2 : Game.getFrames()){

            if(frame2.getX()+frame2.getWidth()>=frame.getX() && frame2.getX()<frame.getX())  sides.add(Direction.LEFT);
            if(frame2.getX()<=frame.getX()+frame.getWidth() && frame2.getX()+frame2.getWidth()>frame.getX()+frame.getWidth()) sides.add(Direction.RIGHT);
            if(frame2.getY()+frame2.getHeight()>=frame.getY() && frame2.getY()<frame.getY()) sides.add(Direction.UP);
            if(frame2.getY()<=frame.getY()+frame.getHeight() && frame2.getY()+frame2.getHeight()>frame.getY()+frame.getHeight()) sides.add(Direction.DOWN);

        }
        return sides;
    }
    public static ArrayList<Side> twoFrameOverlapSide(JFrame frame1, JFrame frame2){

        ArrayList<Side> sides = new ArrayList<>();

        if(frame2.getX()+frame2.getWidth()<=frame1.getX())  sides.add(Side.LEFT);
        if(frame2.getX()<=frame1.getX()+frame1.getWidth() ) sides.add(Side.RIGHT);
        if(frame2.getY()+frame2.getHeight()>=frame1.getY()) sides.add(Side.UP);
        if(frame2.getY()<=frame1.getY()+frame1.getHeight() ) sides.add(Side.DOWN);

        return sides;
    }



}
