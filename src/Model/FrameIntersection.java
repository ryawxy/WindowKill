package Model;

import Controller.Game;
import View.GlassFrame;

import javax.swing.*;
import java.awt.*;

public class FrameIntersection {

    public FrameIntersection(){


    }
    public  void changeLocalFrame(JFrame frame, GameObjects entity){


        JFrame previousFrame = entity.getLocalFrame();
        Rectangle bounds1 = new Rectangle(entity.getLocalFrame().getX(),entity.getLocalFrame().getY(),entity.getLocalFrame().getWidth()
        ,entity.getLocalFrame().getHeight());


        Rectangle bounds2 = new Rectangle(frame.getX(),frame.getY(),frame.getWidth(),frame.getHeight());

        int globalX = (int) (entity.getLocalX()+ entity.getLocalFrame().getX());
        int globalY = (int) (entity.getLocalY()+ entity.getLocalFrame().getY());

    //    System.out.println(globalX);

        Rectangle rectangle = new Rectangle(globalX,globalY, entity.getWidth(),entity.getHeight());
        if(bounds1.intersects(bounds2)) {


                if( bounds2.intersects(rectangle)){


                if(!bounds1.contains(rectangle)){
//                    System.out.println("*****************************");

                    Game.getEpsilon().getLocalFrames().add(frame);
                    Game.getEpsilon().getLocalFrames().remove(previousFrame);
                    entity.setLocalFrame(frame);
                    entity.setPreviousLocalFrame(previousFrame);
//                   System.out.println(entity.getLocalFrame().getTitle());



                    entity.setLocalX(globalX-entity.getLocalFrame().getX());
                    entity.setLocalY(globalY-entity.getLocalFrame().getY());
               }
//                if(bounds1.contains(rectangle) && bounds2.contains(rectangle)){
//                    entity.setLocalFrame(frame);
//                    entity.setPreviousLocalFrame(previousFrame);
//                    Rectangle overlap = bounds1.intersection(bounds2);
//                    entity.setLocalX(globalX-overlap.x);
//                    entity.setLocalY(globalY-overlap.y);
//                }
            }
         }
            }


}
