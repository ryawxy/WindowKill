package Model;

import Controller.Game;
import Model.Entity.Trigorath;
import View.GlassFrame;
import View.entityViews.Barricados.BarricadosFrame;

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


                    if(entity instanceof Trigorath) System.out.println("*****************************");

                    entity.setLocalFrame(frame);
                    entity.setPreviousLocalFrame(previousFrame);
//                   System.out.println(entity.getLocalFrame().getTitle());

                    if(entity instanceof Trigorath){


                        int[] globalx = new int[3];
                        int[] globaly = new int[3];
                        for(int i=0;i<3;i++){
                            globalx[i] = ((Trigorath) entity).getLocalxPoints()[i]+entity.getLocalFrame().getX();
                            globaly[i] = ((Trigorath) entity).getLocalyPoints()[i]+entity.getLocalFrame().getY();
                        }

                        for(int i=0;i<3;i++){
                            ((Trigorath) entity).getLocalxPoints()[i] = globalx[i] - entity.getLocalFrame().getX();
                            ((Trigorath) entity).getLocalyPoints()[i] = globaly[i] - entity.getLocalFrame().getY();
                        }
                    }else {
                        entity.setLocalX(globalX - entity.getLocalFrame().getX());
                        entity.setLocalY(globalY - entity.getLocalFrame().getY());
                        Game.getEpsilon().getLocalFrames().remove(previousFrame);
                    }
               }
                if(bounds1.contains(rectangle) && bounds2.contains(rectangle)){
                    Game.getEpsilon().getLocalFrames().add(frame);
//                    entity.setLocalFrame(frame);
//                    entity.setPreviousLocalFrame(previousFrame);
//                    Rectangle overlap = bounds1.intersection(bounds2);
//                    entity.setLocalX(globalX-overlap.x);
//                    entity.setLocalY(globalY-overlap.y);
                }
            }
         }
            }
            public void frameIntersection(JFrame frame1, BarricadosFrame barricadosFrame){





            }


}
